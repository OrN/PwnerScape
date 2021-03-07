package com.pwns.server.login;

import com.pwns.server.model.entity.player.Player;

public class LoginTask implements Runnable {
	
	private final LoginRequest loginRequest;
	private final Player loadedPlayer;
	
	public LoginTask(LoginRequest request, Player loadedPlayer) {
		this.loginRequest = request;
		this.loadedPlayer = loadedPlayer;
	}
	
	@Override
	public void run() {
		loginRequest.loadingComplete(loadedPlayer);
	}
	
}
