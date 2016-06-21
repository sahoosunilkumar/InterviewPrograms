package com.sunilsahoo.designpatterns;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * Whereas Singleton design pattern introduces single globally accessible object the Multiton
 * pattern defines many globally accessible objects. The client asks for the correct instance from
 * the Multiton by passing an enumeration as parameter.
 * <p>
 * In this example {@link Nazgul} is the Multiton and we can ask single {@link Nazgul} from it using
 * {@link NazgulName}. The {@link Nazgul}s are statically initialized and stored in concurrent hash
 * map.
 *
 */
public class MultitonExample {
	public static void main(String[] args) {
	    System.out.println("KHAMUL=" + Nazgul.getInstance(NazgulName.KHAMUL));
	    System.out.println("MURAZOR=" + Nazgul.getInstance(NazgulName.MURAZOR));
	    System.out.println("DWAR=" + Nazgul.getInstance(NazgulName.DWAR));
	    System.out.println("JI_INDUR=" + Nazgul.getInstance(NazgulName.JI_INDUR));
	    System.out.println("AKHORAHIL=" + Nazgul.getInstance(NazgulName.AKHORAHIL));
	    System.out.println("HOARMURATH=" + Nazgul.getInstance(NazgulName.HOARMURATH));
	    System.out.println("ADUNAPHEL=" + Nazgul.getInstance(NazgulName.ADUNAPHEL));
	    System.out.println("REN=" + Nazgul.getInstance(NazgulName.REN));
	    System.out.println("UVATHA=" + Nazgul.getInstance(NazgulName.UVATHA));
	  }
}

enum NazgulName {

	  KHAMUL, MURAZOR, DWAR, JI_INDUR, AKHORAHIL, HOARMURATH, ADUNAPHEL, REN, UVATHA;

	}
class Nazgul {

	  private static Map<NazgulName, Nazgul> nazguls;

	  private NazgulName name;

	  static {
	    nazguls = new ConcurrentHashMap<>();
	    nazguls.put(NazgulName.KHAMUL, new Nazgul(NazgulName.KHAMUL));
	    nazguls.put(NazgulName.MURAZOR, new Nazgul(NazgulName.MURAZOR));
	    nazguls.put(NazgulName.DWAR, new Nazgul(NazgulName.DWAR));
	    nazguls.put(NazgulName.JI_INDUR, new Nazgul(NazgulName.JI_INDUR));
	    nazguls.put(NazgulName.AKHORAHIL, new Nazgul(NazgulName.AKHORAHIL));
	    nazguls.put(NazgulName.HOARMURATH, new Nazgul(NazgulName.HOARMURATH));
	    nazguls.put(NazgulName.ADUNAPHEL, new Nazgul(NazgulName.ADUNAPHEL));
	    nazguls.put(NazgulName.REN, new Nazgul(NazgulName.REN));
	    nazguls.put(NazgulName.UVATHA, new Nazgul(NazgulName.UVATHA));
	  }

	  private Nazgul(NazgulName name) {
	    this.name = name;
	  }

	  public static Nazgul getInstance(NazgulName name) {
	    return nazguls.get(name);
	  }

	  public NazgulName getName() {
	    return name;
	  }
	}