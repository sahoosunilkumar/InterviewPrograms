package com.sunilsahoo.designpatterns;

/**
 * When There is no factory method to create object and we create object at
 * calling environment
 * 
 * @author sunilkumarsahoo
 *
 */

public class SimpleFactoryExample {
	public static void main(String[] args) {
		String filename = "Vandemataram.mp4";
		IFileValidator1 filevalidator1 = new AudioValidator1();
		IFileValidator1 filevalidator2 = new VideoValidator1();
		filevalidator1.validate(filename);
		filevalidator2.validate(filename);
	}

}

interface IFileValidator1 {
	void validate(String filname);
}

class AudioValidator1 implements IFileValidator1 {

	@Override
	public void validate(String filname) {
		// TODO Auto-generated method stub
		if (filname.endsWith("mp3")) {
			System.out.println(filname + " is Mp3 file");
		}

	}

}

class VideoValidator1 implements IFileValidator1 {

	@Override
	public void validate(String filname) {
		// TODO Auto-generated method stub
		if (filname.endsWith("mp4")) {
			System.out.println(filname + " is a Vudio file");
		}
	}

}
