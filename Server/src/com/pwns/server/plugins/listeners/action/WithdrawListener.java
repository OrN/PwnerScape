package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.player.Player;

/**
 * This interface is called when a user withdraws an item from the bank
 *
 * @author Peeter
 */
public interface WithdrawListener {
    /**
     * Called when a user withdraws an item
     */
    public void onWithdraw(Player p, int itemID, int amount);
}
