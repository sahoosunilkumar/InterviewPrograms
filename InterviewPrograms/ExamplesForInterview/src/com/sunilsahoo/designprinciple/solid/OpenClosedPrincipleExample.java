package com.sunilsahoo.designprinciple.solid;


/**
 * 
 * Software components should be open for extension, but closed for modification
 * 
 * The Open-Closed Principle (OCP) states that classes should be open for extension but closed for modification. “Open to extension” means that you should design your classes so that new functionality can be added as new requirements are generated. “Closed for modification” means that once you have developed a class you should never modify it, except to correct bugs.
These two parts of the principle appear to be contradictory. However, if you correctly structure your classes and their dependencies, you can add functionality without editing existing source code.
Generally you achieve this by referring to abstractions for dependencies, such as interfaces or abstract classes, rather than using concrete classes. Functionality can be added by creating new classes that implement the interfaces.
Applying OCP to your projects limits the need to change source code once it has been written, tested and debugged. This reduces the risk of introducing new bugs to existing code, leading to more robust software.
Another side effect of the use of interfaces for dependencies is reduced coupling and increased flexibility.

 * @author sunilkumarsahoo
 *
 */
public class OpenClosedPrincipleExample {

	public static void main(String[] args){
		//OCP failed case
		boolean compareById = false;
		Compare c = new Compare();
		if(compareById){
		c.compareById();
		}else{
		c.compareByName();
		}
		
		//OCP pass case
		CompareByListByName compName = new CompareByListByName();
		compName.onCompare();
		
		CompareByListById compId = new CompareByListById();
		compId.onCompare();
	}
}


//==== WRONG WAY OF IMPLEMENTING OCP START ====
class Compare {

	public void compareByName(){
		//TODO comparebyname
		System.out.println("compareByName");
	}
	public void compareById(){
		//TODO comparebyid
		System.out.println("compareById");
	}
}
//==== WRONG WAY OF IMPLEMENTING OCP START ====

//==== RIGHT WAY OF IMPLEMENTING OCP START ====	

interface ICompare{
	public void onCompare();
}

 class CompareByListByName  implements ICompare {
    @Override
    public void onCompare() {
    	System.out.println("compare by name");
    	//TODO perform name comparision stuff
    }
}

 class CompareByListById  implements ICompare {
	    @Override
	    public void onCompare() {
	    	System.out.println("compare by id");
	    	//TODO perform name comparision stuff
	    }
	}
 
//==== RIGHT WAY OF IMPLEMENTING OCP END ====	
