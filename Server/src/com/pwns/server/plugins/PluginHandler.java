package com.pwns.server.plugins;

import static org.apache.logging.log4j.util.Unbox.box;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.pwns.server.util.ClassEnumerator;
import com.pwns.server.event.custom.ShopRestockEvent;
import com.pwns.server.model.Shop;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.plugins.listeners.action.DropListener;
import com.pwns.server.plugins.listeners.executive.DropExecutiveListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pwns.server.Server;

/**
 * Initiates plug-ins that implements some listeners
 * 
 * @author Peeter, design idea xEnt
 * @author n0m - changes made:
 * Plugins are now loaded from external jar
 * Plugins are now reloadable.
 * No longer need to add paths to plugins.
 */
public final class PluginHandler {

	/**
	 * The asynchronous logger.
	 */
	private static final Logger LOGGER = LogManager.getLogger();

	public static PluginHandler pluginHandler = null;

	private Object defaultHandler = null;

	private URLClassLoader urlClassLoader;

	public static PluginHandler getPluginHandler() {
		if (pluginHandler == null) {
			pluginHandler = new PluginHandler();
		}
		return pluginHandler;
	}

	private Map<String, Set<Object>> actionPlugins = new HashMap<String, Set<Object>>();
	private Map<String, Set<Object>> executivePlugins = new HashMap<String, Set<Object>>();
	private ExecutorService executor = Executors.newSingleThreadExecutor();

	private List<Class<?>> knownInterfaces = new ArrayList<Class<?>>();

	private Map<String, Class<?>> queue = new ConcurrentHashMap<String, Class<?>>();
	public static boolean reloading;

	public boolean blockDefaultAction(final String interfce, final Object[] data) {
		return blockDefaultAction(interfce, data, true);
	}

	/**
	 * 
	 * @param interfce
	 * @param data
	 * @param callAction
	 * @return
	 */

	public boolean blockDefaultAction(final String interfce,
			final Object[] data, final boolean callAction) {
		if(reloading) {
			for(Object o : data) {
				if(o instanceof Player) {
					((Player) o).message("Plugins are being updated, please wait.");
				}
			}
			return false;
		}
		boolean shouldBlock = false, flagStop = false;
		queue.clear();
		if (executivePlugins.containsKey(interfce + "ExecutiveListener")) {
			for (final Object c : executivePlugins.get(interfce
					+ "ExecutiveListener")) {
				try {
					final Class<?>[] dataClasses = new Class<?>[data.length];
					int i = 0;
					for (final Object o : data) {
						dataClasses[i++] = o.getClass();
					}
					final Method m = c.getClass().getMethod("block" + interfce,
							dataClasses);
					shouldBlock = (Boolean) m.invoke(c, data);
					if (shouldBlock) {
						queue.put(interfce, c.getClass());
						flagStop = true;
					} else if(queue.size() > 1) {

					}
					else if (queue.isEmpty()) {
						queue.put(interfce, defaultHandler.getClass());
					}
				} catch (final Exception e) {
					LOGGER.catching(e);
				}
			}
		}

		if (callAction) {
			handleAction(interfce, data);
		}
		return flagStop; // not sure why it matters if its false or true
	}


	public Map<String, Set<Object>> getActionPlugins() {
		return actionPlugins;
	}

	public Map<String, Set<Object>> getExecutivePlugins() {
		return executivePlugins;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public List<Class<?>> getKnownInterfaces() {
		return knownInterfaces;
	}

	public void handleAction(final String interfce, final Object[] data) {
		if(reloading) {
			return;
		}
		if (actionPlugins.containsKey(interfce + "Listener")) {
			for (final Object c : actionPlugins.get(interfce + "Listener")) {
				try {
					final Class<?>[] dataClasses = new Class<?>[data.length];
					int i = 0;
					for (final Object o : data) {
						dataClasses[i++] = o.getClass();
					}

					final Method m = c.getClass().getMethod("on" + interfce,
							dataClasses);
					boolean go = false;

					if (queue.containsKey(interfce)) {
						for (final Class<?> clz : queue.values()) {
							if (clz.getName().equalsIgnoreCase(
									c.getClass().getName())) {
								go = true;
								LOGGER.info("Executing with : " + clz.getName());
								break;
							}
						}
					} else {
						go = true;
					}

					if (go) {
						final FutureTask<Integer> task = new FutureTask<Integer>(
								new Callable<Integer>() {
									@Override
									public Integer call() throws Exception {
										try {
											m.invoke(c, data);
										} catch (Exception cme) {
											LOGGER.catching(cme);
										}
										return 1;
									}
								});
						getExecutor().execute(task);
					}
				} catch (final Exception e) {
					System.err.println("Exception at plugin handling: ");
					LOGGER.catching(e);
				}
			}
		}
	}

	public void initPlugins() throws Exception {

		final Map<String, Object> loadedPlugins = new HashMap<String, Object>();
		List<Class<?>> loadedClassFiles = ClassEnumerator.getPackageClasses(PluginHandler.class.getPackage());
		List<Class<?>> actionClassFiles = ClassEnumerator.getPackageClasses(DropListener.class.getPackage());
		List<Class<?>> executiveClassFiles = ClassEnumerator.getPackageClasses(DropExecutiveListener.class.getPackage());

		for (final Class<?> interfce : actionClassFiles) {
			final String interfceName = interfce.getName().substring(interfce.getName().lastIndexOf(".") + 1);
			knownInterfaces.add(interfce);
			for (final Class<?> plugin : loadedClassFiles) {
				if (!interfce.isAssignableFrom(plugin) || interfce == plugin) {
					continue;
				}
				Object instance = plugin.getConstructor().newInstance();
				if(instance instanceof DefaultHandler && defaultHandler == null) {
					defaultHandler = instance;
					continue;
				}
				if (instance instanceof ShopInterface) {
					final ShopInterface it = (ShopInterface) instance;

					for (final Shop s : it.getShops()) {
						World.getWorld().getShops().add(s);
						Server.getServer().getEventHandler().add(new ShopRestockEvent(s));
					}
				}
				if (loadedPlugins.containsKey(instance.getClass().getName())) {
					instance = loadedPlugins.get(instance.getClass().getName());
				} else {
					loadedPlugins.put(instance.getClass().getName(), instance);
					if (instance instanceof QuestInterface) {
						final QuestInterface q = (QuestInterface) instance;
						try {
							World.getWorld().registerQuest(q);
						} catch (final Exception e) {
							LOGGER.error("Error registering quest " + q.getQuestName());
							LOGGER.catching(e);
							continue;
						}
					}
				}
				if (actionPlugins.containsKey(interfceName)) {
					final Set<Object> data = actionPlugins.get(interfceName);
					data.add(instance);
					actionPlugins.put(interfceName, data);
				} else {
					final Set<Object> data = new HashSet<Object>();
					data.add(instance);
					actionPlugins.put(interfceName, data);
				}
			}
		}
		for (final Class<?> interfce : executiveClassFiles) {
			final String interfceName = interfce.getName().substring(
					interfce.getName().lastIndexOf(".") + 1);
			knownInterfaces.add(interfce);
			for (final Class<?> plugin : loadedClassFiles) {
				if (!interfce.isAssignableFrom(plugin) || interfce == plugin) {
					continue;
				}
				Object instance = plugin.newInstance();
				if (loadedPlugins.containsKey(instance.getClass().getName())) {
					instance = loadedPlugins.get(instance.getClass().getName());
				} else {
					loadedPlugins.put(instance.getClass().getName(), instance);

					if (Arrays.asList(instance.getClass().getInterfaces())
							.contains(QuestInterface.class)) {
						final QuestInterface q = (QuestInterface) instance;
						try {
							World.getWorld().registerQuest(
									(QuestInterface) instance);
						} catch (final Exception e) {
							LOGGER.error(
									"Error registering quest "
											+ q.getQuestName());
							LOGGER.catching(e);
							continue;
						}
					}
				}

				if (executivePlugins.containsKey(interfceName)) {
					final Set<Object> data = executivePlugins.get(interfceName);
					data.add(instance);
					executivePlugins.put(interfceName, data);
				} else {
					final Set<Object> data = new HashSet<Object>();
					data.add(instance);
					executivePlugins.put(interfceName, data);
				}
			}
		}
		LOGGER.info("Loaded {}", box(World.getWorld().getQuests().size()) + " Quests.");
		LOGGER.info("Loaded total of {}", box(loadedPlugins.size()) + " plugin handlers.");
	}

	public void unload() throws IOException {
		reloading = true;
		urlClassLoader.close();
		executor.shutdown();
		World.getWorld().getQuests().clear();
		World.getWorld().getShops().clear();

		queue.clear();
		actionPlugins.clear();
		executivePlugins.clear();
		knownInterfaces.clear();

		actionPlugins = new HashMap<String, Set<Object>>();
		executivePlugins = new HashMap<String, Set<Object>>();
		executor = Executors.newCachedThreadPool();
		knownInterfaces = new ArrayList<Class<?>>();
		queue = new ConcurrentHashMap<String, Class<?>>();
		defaultHandler = null;
		System.gc();
	}

	public void reload() throws Exception {
		initPlugins();
		reloading = false;
	}
}