package com.sunilsahoo.designpatterns;

/**
 * Type: Creational
What it is:
Define an interface for creating an object, but let subclasses decide which class to instantiate. Lets a class defer instantiation to subclasses.
 */
import java.util.HashMap;
import java.util.Map;

public class FactoryMethodExample {
	public static void main(String[] args) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("login", "sunil");

		IAnalyticsInterface1 interface1 = AnalyticsFactory
				.getAnalytics("omniture");
		interface1.trackEvent(properties);

		IAnalyticsInterface1 interface2 = AnalyticsFactory
				.getAnalytics("leanplum");
		interface2.trackEvent(properties);
	}

}

class AnalyticsFactory {
	// Factory Method
	public static IAnalyticsInterface1 getAnalytics(String type) {
		if (type.startsWith("omn")) {
			return new OmnitureAnalyticsImpl1();
		} else {
			return new LeanplumAnalyticsImpl1();
		}
	}
}

interface IAnalyticsInterface1 {

	/**
	 * Initializes analytics.
	 * 
	 * @param context
	 */
	void init(String context);

	/**
	 * Sends event to analytics.
	 * 
	 * @param properties
	 */
	void trackEvent(Map<String, Object> properties);
}

class OmnitureAnalyticsImpl1 implements IAnalyticsInterface1 {

	@Override
	public void init(String context) {
		// TODO Auto-generated method stub
		System.out.println("Omniture init implimented");

	}

	@Override
	public void trackEvent(Map<String, Object> properties) {
		// TODO Auto-generated method stub
		System.out.println("Omniture trackEvent implimented " + properties);

	}

}

class LeanplumAnalyticsImpl1 implements IAnalyticsInterface1 {

	@Override
	public void init(String context) {
		// TODO Auto-generated method stub
		System.out.println("LeanplumAnalyticsImpl init implimented");

	}

	@Override
	public void trackEvent(Map<String, Object> properties) {
		// TODO Auto-generated method stub
		System.out.println(
				"LeanplumAnalyticsImpl trackEvent implimented " + properties);

	}

}
