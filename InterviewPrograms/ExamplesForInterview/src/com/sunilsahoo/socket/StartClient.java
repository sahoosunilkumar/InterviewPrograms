package com.sunilsahoo.socket;

public class StartClient {
	public static void main(String[] args) {
		Client client = new Client();

		client.readyToReceivPacket();
	}
}
