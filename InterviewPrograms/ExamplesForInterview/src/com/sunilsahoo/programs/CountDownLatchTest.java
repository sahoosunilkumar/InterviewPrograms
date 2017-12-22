package com.sunilsahoo.programs;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CountDownLatchTest {
	public static void main(String[] args) {
		ApplicationVarWatcherMap map = new ApplicationVarWatcherMap();
		ApplicationVarWatcher watcher1 = new ApplicationVarWatcher(1);
		ApplicationVarWatcher watcher2 = new ApplicationVarWatcher(2);
		map.set("1", watcher1);
		map.set("2", watcher2);
		System.out.println(map.watcherMap);
		watcher1.onReceived("data1", "value1");

		System.out.println(map.watcherMap);

	}
}

class ApplicationVarWatcher {
	private HashMap<String, String> mappedSettings = new HashMap<>();
	private int watcherCount;

	public ApplicationVarWatcher(int watcherCount) {
		this.watcherCount = watcherCount;
	}

	public synchronized void onReceived(String key, String value) {
		mappedSettings.put(key, value);
	}

	@Override
	public String toString() {
		return mappedSettings == null ? "" : mappedSettings.toString();
	}
}

class ApplicationVarWatcherMap {
	LinkedHashMap<String, ApplicationVarWatcher> watcherMap = new LinkedHashMap<>();

	public void set(String key, ApplicationVarWatcher watcher) {
		watcherMap.put(key, watcher);
	}
}