package com.sunilsahoo.designpatterns;

/**
 * ## Intent Ensure a class only has one instance, and provide a global point of
 * access to it.
 * 
 * ![alt text](./etc/singleton_1.png "Singleton")
 * 
 * ## Applicability Use the Singleton pattern when
 * 
 * there must be exactly one instance of a class, and it must be accessible to
 * clients from a well-known access point when the sole instance should be
 * extensible by subclassing, and clients should be able to use an extended
 * instance without modifying their code
 * 
 * ## Typical Use Case
 * 
 * the logging class managing a connection to a database file manager
 * 
 * ## Real world examples
 * 
 * [java.lang.Runtime#getRuntime()](http://docs.oracle.com/javase/8/docs/api/
 * java/lang/Runtime.html#getRuntime%28%29)
 * 
 * ## Credits
 * 
 * 
 * Singleton pattern ensures that the class can have only one existing instance
 * per Java classloader instance and provides global access to it.
 * <p/>
 * One of the risks of this pattern is that bugs resulting from setting a
 * singleton up in a distributed environment can be tricky to debug, since it
 * will work fine if you debug with a single classloader. Additionally, these
 * problems can crop up a while after the implementation of a singleton, since
 * they may start out synchronous and only become async with time, so you it may
 * not be clear why you are seeing certain changes in behaviour.
 * <p/>
 * There are many ways to implement the Singleton. The first one is the eagerly
 * initialized instance in {@link IvoryTower}. Eager initialization implies that
 * the implementation is thread safe. If you can afford giving up control of the
 * instantiation moment, then this implementation will suit you fine.
 * <p/>
 * The other option to implement eagerly initialized Singleton is enum based
 * Singleton. The example is found in {@link EnumIvoryTower}. At first glance
 * the code looks short and simple. However, you should be aware of the
 * downsides including committing to implementation strategy, extending the enum
 * class, serializability and restrictions to coding. These are extensively
 * discussed in Stack Overflow:
 * http://programmers.stackexchange.com/questions/179386/what-are-the-downsides-
 * of-implementing -a-singleton-with-javas-enum
 * <p/>
 * {@link ThreadSafeLazyLoadedIvoryTower} is a Singleton implementation that is
 * initialized on demand. The downside is that it is very slow to access since
 * the whole access method is synchronized.
 * <p/>
 * Another Singleton implementation that is initialized on demand is found in
 * {@link ThreadSafeDoubleCheckLocking}. It is somewhat faster than
 * {@link ThreadSafeLazyLoadedIvoryTower} since it doesn't synchronize the whole
 * access method but only the method internals on specific conditions.
 * <p/>
 * Yet another way to implement thread safe lazily initialized Singleton can be
 * found in {@link InitializingOnDemandHolderIdiom}. However, this
 * implementation requires at least Java 8 API level to work.
 */
public class SingletonExample {
	public static void main(String[] args) {

		// eagerly initialized singleton
		IvoryTower ivoryTower1 = IvoryTower.getInstance();
		IvoryTower ivoryTower2 = IvoryTower.getInstance();
		System.out.println("ivoryTower1=" + ivoryTower1);
		System.out.println("ivoryTower2=" + ivoryTower2);

		// lazily initialized singleton
		ThreadSafeLazyLoadedIvoryTower threadSafeIvoryTower1 = ThreadSafeLazyLoadedIvoryTower
				.getInstance();
		ThreadSafeLazyLoadedIvoryTower threadSafeIvoryTower2 = ThreadSafeLazyLoadedIvoryTower
				.getInstance();
		System.out.println("threadSafeIvoryTower1=" + threadSafeIvoryTower1);
		System.out.println("threadSafeIvoryTower2=" + threadSafeIvoryTower2);

		// enum singleton
		EnumIvoryTower enumIvoryTower1 = EnumIvoryTower.INSTANCE;
		EnumIvoryTower enumIvoryTower2 = EnumIvoryTower.INSTANCE;
		System.out.println("enumIvoryTower1=" + enumIvoryTower1);
		System.out.println("enumIvoryTower2=" + enumIvoryTower2);

		// double checked locking
		ThreadSafeDoubleCheckLocking dcl1 = ThreadSafeDoubleCheckLocking
				.getInstance();
		System.out.println(dcl1);
		ThreadSafeDoubleCheckLocking dcl2 = ThreadSafeDoubleCheckLocking
				.getInstance();
		System.out.println(dcl2);

		// initialize on demand holder idiom
		InitializingOnDemandHolderIdiom demandHolderIdiom = InitializingOnDemandHolderIdiom
				.getInstance();
		System.out.println(demandHolderIdiom);
		InitializingOnDemandHolderIdiom demandHolderIdiom2 = InitializingOnDemandHolderIdiom
				.getInstance();
		System.out.println(demandHolderIdiom2);
	}
}

enum EnumIvoryTower {

	INSTANCE;

	@Override
	public String toString() {
		return getDeclaringClass().getCanonicalName() + "@" + hashCode();
	}
}

/**
 * The Initialize-on-demand-holder idiom is a secure way of creating a lazy
 * initialized singleton object in Java.
 * <p>
 * The technique is is as lazy as possible and works in all known versions of
 * Java. It takes advantage of language guarantees about class initialization,
 * and will therefore work correctly in all Java-compliant compilers and virtual
 * machines.
 * <p>
 * The inner class is referenced no earlier (and therefore loaded no earlier by
 * the class loader) than the moment that getInstance() is called. Thus, this
 * solution is thread-safe without requiring special language constructs (i.e.
 * volatile or synchronized).
 *
 */
final class InitializingOnDemandHolderIdiom {

	/**
	 * Private constructor.
	 */
	private InitializingOnDemandHolderIdiom() {
	}

	/**
	 * @return Singleton instance
	 */
	public static InitializingOnDemandHolderIdiom getInstance() {
		return HelperHolder.INSTANCE;
	}

	/**
	 * Provides the lazy-loaded Singleton instance.
	 */
	private static class HelperHolder {
		private static final InitializingOnDemandHolderIdiom INSTANCE = new InitializingOnDemandHolderIdiom();
	}
}

/**
 * Singleton class. Eagerly initialized static instance guarantees thread
 * safety.
 */
final class IvoryTower {

	/**
	 * Static to class instance of the class.
	 */
	private static final IvoryTower INSTANCE = new IvoryTower();

	/**
	 * Private constructor so nobody can instantiate the class.
	 */
	private IvoryTower() {
	}

	/**
	 * To be called by user to obtain instance of the class.
	 *
	 * @return instance of the singleton.
	 */
	public static IvoryTower getInstance() {
		return INSTANCE;
	}
}

final class ThreadSafeDoubleCheckLocking {

	private static volatile ThreadSafeDoubleCheckLocking instance;

	/**
	 * private constructor to prevent client from instantiating.
	 */
	private ThreadSafeDoubleCheckLocking() {
		// to prevent instantiating by Reflection call
		if (instance != null) {
			throw new IllegalStateException("Already initialized.");
		}
	}

	/**
	 * Public accessor.
	 *
	 * @return an instance of the class.
	 */
	public static ThreadSafeDoubleCheckLocking getInstance() {
		// local variable increases performance by 25 percent
		// Joshua Bloch "Effective Java, Second Edition", p. 283-284
		ThreadSafeDoubleCheckLocking result = instance;
		if (result == null) {
			synchronized (ThreadSafeDoubleCheckLocking.class) {
				result = instance;
				if (result == null) {
					instance = result = new ThreadSafeDoubleCheckLocking();
				}
			}
		}
		return result;
	}
}

/**
 * Thread-safe Singleton class. The instance is lazily initialized and thus
 * needs synchronization mechanism.
 *
 * Note: if created by reflection then a singleton will not be created but
 * multiple options in the same classloader
 */
final class ThreadSafeLazyLoadedIvoryTower {

	private static ThreadSafeLazyLoadedIvoryTower instance;

	private ThreadSafeLazyLoadedIvoryTower() {
	}

	/**
	 * The instance gets created only when it is called for first time.
	 * Lazy-loading
	 */
	public static synchronized ThreadSafeLazyLoadedIvoryTower getInstance() {

		if (instance == null) {
			instance = new ThreadSafeLazyLoadedIvoryTower();
		}

		return instance;
	}
}
