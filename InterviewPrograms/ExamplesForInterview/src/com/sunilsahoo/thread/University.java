package com.sunilsahoo.thread;

public class University extends Thread {
	Output output;

	public University(Output output) {
		this.output = output;
	}

	@Override
	public void run() {
		output.print("University Print.");
		output.scan("University Print.");
	}
}