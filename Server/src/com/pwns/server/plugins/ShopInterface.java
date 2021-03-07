package com.pwns.server.plugins;

import com.pwns.server.model.Shop;

public interface ShopInterface {
	
	public Shop[] getShops();
	
	public boolean isMembers();
}