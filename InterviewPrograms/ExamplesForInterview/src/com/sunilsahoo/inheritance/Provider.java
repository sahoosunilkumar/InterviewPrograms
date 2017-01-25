package com.sunilsahoo.inheritance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

 abstract public class Provider {

//	public String getName() {
//		return "Sunil";
//	}
	 abstract String getName();

	private static HashMap<String, Object> classMap = new HashMap<>();

	private static HashMap<String, Set<Class>> listenerMap = new HashMap<>();

	private static void register(Class classInst, Class target) {
		try {
			Set<Class> listenerSet = null;
			if (classMap.get(classInst.getName()) == null) {
				classMap.put(classInst.getName(), classInst.newInstance());
			}else{
				listenerSet = listenerMap.get(classInst.getName());
			}
			if (listenerSet == null) {
				listenerSet = new HashSet<>(2);
			}
			listenerSet.add(target);
			listenerMap.put(classInst.getName(), listenerSet);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// classMap.put(key, value)
		System.out.println("after register - classmap : " + classMap
				+ " listener map : " + listenerMap);
	}

	public static void unregister(Class classInst, Class target) {
		Set<Class> listenerSet = listenerMap.get(classInst.getName());
		if (listenerSet != null) {
			listenerSet.remove(target);
		}
		if ((listenerSet == null) || listenerSet.isEmpty()) {
			listenerMap.remove(classInst.getName());
			classMap.remove(classInst.getName());
		}
		System.out.println("after unregister - classmap : " + classMap
				+ " listener map : " + listenerMap);
	}

	public static <T> T getInstance(Class classInst, Class target) {
		register(classInst, target);
		return (T) classMap.get(classInst.getName());
	}
}
