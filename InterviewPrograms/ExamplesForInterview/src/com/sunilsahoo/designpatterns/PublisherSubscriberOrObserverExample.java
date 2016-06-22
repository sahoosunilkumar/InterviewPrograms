package com.sunilsahoo.designpatterns;

/**
 * Type: Behavioral
What it is:
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PublisherSubscriberOrObserverExample {
	public static void main(String[] args) {
		LeanplumAnalyticsImpl lpAnalytics = new LeanplumAnalyticsImpl();
		AnalyticsManager.addAnalyticsEngine(lpAnalytics);
		OmnitureAnalyticsImpl omnitureAnalytics = new OmnitureAnalyticsImpl();
		omnitureAnalytics.init("init omniture");
		AnalyticsManager.addAnalyticsEngine(omnitureAnalytics);
		Map<String, Object> properties = new HashMap<>();
		properties.put("Login", "Sunil");
		AnalyticsManager.track(properties);
		AnalyticsManager.removeAnalyticsEngine(lpAnalytics);
		System.out.println("After removing leanplum analytics ------");
		AnalyticsManager.track(properties);
	}

}

interface IAnalyticsInterface {

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

/**
 * This is the class responsible for adding, remove, disable analytics engine
 * and send track.
 */
class AnalyticsManager {
	/**
	 * Map to store all registered analytics engines.
	 */
	private static HashMap<IAnalyticsInterface, Boolean> interfaces = new HashMap<IAnalyticsInterface, Boolean>();

	/**
	 * Adds analytics engine.
	 *
	 * @param iAnalyticsInterface
	 */
	public static void addAnalyticsEngine(IAnalyticsInterface iAnalyticsInterface) {
		interfaces.put(iAnalyticsInterface, true);
	}

	/**
	 * Removes analytics engine.
	 *
	 * @param iAnalyticsInterface
	 */
	public static void removeAnalyticsEngine(IAnalyticsInterface iAnalyticsInterface) {
		interfaces.remove(iAnalyticsInterface);
	}

	/**
	 * Disables analytics engine.
	 *
	 * @param iAnalyticsInterface
	 */
	public static void disableAnalyticsEngine(IAnalyticsInterface iAnalyticsInterface) {
		interfaces.put(iAnalyticsInterface, false);
	}

	/**
	 * Sends track event to all registered enabled analytics.
	 *
	 * @param properties
	 */
	public static void track(Map<String, Object> properties) {
		Set<Entry<IAnalyticsInterface, Boolean>> entrySet = interfaces.entrySet();
		for (Entry<IAnalyticsInterface, Boolean> entry : entrySet) {
			if (entry.getValue()) {
				entry.getKey().trackEvent(properties);
			}
		}
	}
}

class OmnitureAnalyticsImpl implements IAnalyticsInterface {

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

class LeanplumAnalyticsImpl implements IAnalyticsInterface {

	@Override
	public void init(String context) {
		// TODO Auto-generated method stub
		System.out.println("LeanplumAnalyticsImpl init implimented");

	}

	@Override
	public void trackEvent(Map<String, Object> properties) {
		// TODO Auto-generated method stub
		System.out.println("LeanplumAnalyticsImpl trackEvent implimented " + properties);

	}

}
