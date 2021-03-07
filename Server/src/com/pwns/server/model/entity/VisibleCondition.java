package com.pwns.server.model.entity;

import com.pwns.server.model.entity.player.Player;

public abstract class VisibleCondition {
	public abstract boolean isVisibleTo(Entity entity, Player observer);
}
