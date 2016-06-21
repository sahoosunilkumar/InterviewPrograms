package com.sunilsahoo.designprinciple.solid;
/**
 * 
 * @author sunilkumarsahoo
 * 
 * One class should have one and only one responsibility
 * 
 * The Single Responsibility Principle (SRP) states that there should never be more than one reason for a class to change. This means that every class, or similar structure, in your code should have only one job to do.
Everything in the class should be related to that single purpose, i.e. be cohesive. It does not mean that your classes should only contain one method or property.
There can be a lot of members as long as they relate to the single responsibility. It may be that when the one reason to change occurs, multiple members of the class may need modification. It may also be that multiple classes will require updates.

 *
 */
public class SingleResponsibilityPrincipleExample {
public static void main(String[] args){
	//wrong way of implementation of SRP, As login, logut and shopping list related task is performed in Login class. It brakes single responsibility principle
	LoginWrong lw = new LoginWrong();
	lw.doLogin();
	lw.doLogout();
	lw.showShoppingList();
	//right way of implementing SRP
	Login login = new Login();
	login.doLogin();
	
	Logout logout = new Logout();
	logout.doLogout();
	
	ShoppingList list = new ShoppingList();
	list.showShoppingList();
}
}
class LoginWrong{
	public void doLogin(){
		//TODO perform login
	}
	public void doLogout(){
		//TODO perform logout
	}
	public void showShoppingList(){
		//TODO perform logout
	}
}
/**
 * create login class to perform only login related stuff
 * @author sunilkumarsahoo
 *
 */
class Login{
	public void doLogin(){
		//TODO perform login
	}
}
/**
 * create logout class to perform only logout related stuff
 * @author sunilkumarsahoo
 *
 */
class Logout{
	public void doLogout(){
		//TODO perform logout
	}
}

/**
 * create shoppinglist class to perform only shoppinglist related stuff
 * @author sunilkumarsahoo
 *
 */
class ShoppingList{
	public void showShoppingList(){
		//TODO perform logout
	}
}
