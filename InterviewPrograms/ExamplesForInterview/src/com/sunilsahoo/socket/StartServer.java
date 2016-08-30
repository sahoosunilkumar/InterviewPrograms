package com.sunilsahoo.socket;

public class StartServer {
	public static void main(String[] args) {
		Server server = new Server();
		server.readyToReceivPacket();
	}
}
