package com.sunilsahoo.inheritance;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLInput;

public class ExceptionTest implements ExceptionInterface {

	@Override
	public void throwSuperException() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleException() {
		// TODO Auto-generated method stub
		try {

		} catch (Exception ex) {

		}

	}

	@Override
	public void throwSubclassException() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleSubclassException() {
		// TODO Auto-generated method stub
		try {

		} catch (IOException ex) {

		}

	}

	public void handleMulticatchException() {
		try {
			FileInputStream fis = new FileInputStream("name");
			SQLInput input = null;
			input.readArray();

		} catch (IOException | SQLException ex) {

		}
	}

	public void handleMulticatchExceptionWrong() {
		try {
			FileInputStream fis = new FileInputStream("name");
			SQLInput input = null;
			input.readArray();

		} catch (IOException | Exception ex) {

		}
	}

}

interface ExceptionInterface {
	void throwSuperException() throws IOException;

	void handleException() throws IOException;

	void throwSubclassException() throws Exception;

	void handleSubclassException() throws Exception;
}
