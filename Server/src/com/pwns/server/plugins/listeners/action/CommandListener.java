package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.player.Player;

public interface CommandListener {
    public void onCommand(String command, String[] args, Player player);
}
