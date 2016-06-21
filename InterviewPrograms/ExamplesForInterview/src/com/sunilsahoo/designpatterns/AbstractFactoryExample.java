package com.sunilsahoo.designpatterns;

/**
 * Type: Creational
What it is:
Provides an interface for creating families of related or dependent objects without specifying their concrete class.

 * The Abstract Factory pattern provides a way to encapsulate a group of individual factories that have a common theme
 * without specifying their concrete classes. In normal usage, the client software creates a concrete implementation of
 * the abstract factory and then uses the generic interface of the factory to create the concrete objects that are part
 * of the theme. The client does not know (or care) which concrete objects it gets from each of these internal
 * factories, since it uses only the generic interfaces of their products. This pattern separates the details of
 * implementation of a set of objects from their general usage and relies on object composition, as object creation is
 * implemented in methods exposed in the factory interface.
 * <p>
 * The essence of the Abstract Factory pattern is a factory interface ({@link KingdomFactory}) and its implementations (
 * {@link ElfKingdomFactory}, {@link OrcKingdomFactory}). The example uses both concrete implementations to create a
 * king, a castle and an army.
 * 
 * ## Also known as
Kit

## Intent
Provide an interface for creating families of related or dependent
objects without specifying their concrete classes.

![alt text](./etc/abstract-factory_1.png "Abstract Factory")

## Applicability
Use the Abstract Factory pattern when

* a system should be independent of how its products are created, composed and represented
* a system should be configured with one of multiple families of products
* a family of related product objects is designed to be used together, and you need to enforce this constraint
* you want to provide a class library of products, and you want to reveal just their interfaces, not their implementations

## Real world examples

* [javax.xml.parsers.DocumentBuilderFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/parsers/DocumentBuilderFactory.html)

 * 
 */
public class AbstractFactoryExample {

  private King king;

  /**
   * Creates kingdom
   */
  public void createKingdom(final KingdomFactory factory) {
    setKing(factory.createKing());
  }

  King getKing(final KingdomFactory factory) {
    return factory.createKing();
  }

  public King getKing() {
    return king;
  }

  private void setKing(final King king) {
    this.king = king;
  }
  
  
  
  /**
   * Program entry point
   * 
   * @param args
   *          command line args
   */
  public static void main(String[] args) {

    AbstractFactoryExample app = new AbstractFactoryExample();

    System.out.println("Elf Kingdom");
    app.createKingdom(app.new ElfKingdomFactory());
    System.out.println(app.getKing().getDescription());

    System.out.println("\nOrc Kingdom");
    app.createKingdom(app.new OrcKingdomFactory());
    System.out.println(app.getKing().getDescription());

  }
  
  interface King {

	  String getDescription();
	}
  
  interface KingdomFactory {

		//  Castle createCastle();

		  King createKing();

		}
  
  class ElfKingdomFactory implements KingdomFactory {

	  /*public Castle createCastle() {
	    return new ElfCastle();
	  }*/

	  public King createKing() {
	    return new ElfKing();
	  }
	  
  }
  
  
  
  class ElfKing implements King {

	  static final String DESCRIPTION = "This is the Elven king!";

	  @Override
	  public String getDescription() {
	    return DESCRIPTION;
	  }
	}
  
  class OrcKingdomFactory implements KingdomFactory {

	  /*public Castle createCastle() {
	    return new OrcCastle();
	  }*/

	  public King createKing() {
	    return new OrcKing();
	  }

	}
  
  class OrcKing implements King {

	  static final String DESCRIPTION = "This is the Orc king!";

	  @Override
	  public String getDescription() {
	    return DESCRIPTION;
	  }
	}

}
