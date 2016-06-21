package com.sunilsahoo.designpatterns.question.database;

public abstract class DatabaseQueryAbs implements IDatabaseConnection{
	abstract void query(String query);
	//Template Method
		final public void execute(String query){
			initConnection();
			query(query);
			releaseConnection();
		}

@Override
public void initConnection() {
	// TODO Auto-generated method stub
	System.out.println("Databse Initialized");
}

@Override
public void releaseConnection() {
	// TODO Auto-generated method stub
	System.out.println("Databse Initialized");
}
	
	
}
