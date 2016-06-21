package com.sunilsahoo.designpatterns.question.database;

public interface IDatabaseConnection {
	void initConnection();
	void releaseConnection();
}
