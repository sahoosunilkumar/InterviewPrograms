package com.sunilsahoo.designpatterns;

/**
 * Type: Structural 
 * Intent :
 * • Provide a surrogate or placeholder for another
 * object to control access to it. • Use an extra level of indirection to
 * support distributed, controlled, or intelligent access. • Add a wrapper and
 * delegation to protect the real component from undue complexity. 
 * Problem :
 * You need to support resource-hungry objects, and you do not want to instantiate
 * such objects unless and until they are actually requested by the client.
 * 
 * There are four common situations in which the Proxy pattern is applicable. 1.
 * A virtual proxy is a placeholder for "expensive to create" objects. The real
 * object is only created when a client first requests/accesses the object. 2. A
 * remote proxy provides a local representative for an object that resides in a
 * different address space. This is what the "stub" code in RPC and CORBA
 * provides. 3. A protective proxy controls access to a sensitive master object.
 * The "surrogate" object checks that the caller has the access permissions
 * required prior to forwarding the request. 4. A smart proxy interposes
 * additional actions when an object is accessed. Typical uses include: o
 * Counting the number of references to the real object so that it can be freed
 * automatically when there are no more references (aka smart pointer), o
 * Loading a persistent object into memory when it's first referenced, o
 * Checking that the real object is locked before it is accessed to ensure that
 * no other object can change it.
 * 
 * @author sunilkumarsahoo
 *
 */
// 5. To support plug-compatibility between
// the wrapper and the target, create an interface
interface Image {
	void display();
}

class RealImage implements Image {
	private String fileName;

	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}

	@Override
	public void display() {
		System.out.println("Displaying " + fileName);
	}

	private void loadFromDisk(String fileName) {
		System.out.println("Loading " + fileName);
	}
}

class ProxyImage implements Image {
	private RealImage realImage;
	private String fileName;

	public ProxyImage(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void display() {
		if (realImage == null) {
			realImage = new RealImage(fileName);
		}
		realImage.display();
	}
}

// Use the ProxyImage to get object of RealImage class when required.
public class ProxyExample {
	public static void main(String[] args) {
		Image image = new ProxyImage("test_10mb.jpg");
		// image will be loaded from disk
		image.display();
		System.out.println("");
		// image will not be loaded from disk
		image.display();
	}
}
