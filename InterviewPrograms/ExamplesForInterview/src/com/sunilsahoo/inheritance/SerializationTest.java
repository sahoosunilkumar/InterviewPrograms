package com.sunilsahoo.inheritance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationTest{
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
	SerializationTest test = new SerializationTest();
	Location location = new Location(1,2,3,16);
	System.out.println(location+" object : "+location.x+" "+location.y+" "+location.z+" "+location.id);
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialization.txt"));
	test.writeObject(oos, location);
	System.out.println("..READING NOW..");
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialization.txt"));
	Location location1 = test.readObject(ois);
	System.out.println(location1+" object : "+location1.x+" "+location1.y+" "+location1.z+" "+location1.id);
}

private void writeObject(ObjectOutputStream oos, Location location) throws IOException {
    // default serialization
//    oos.defaultWriteObject();
    // write the object
    oos.writeInt(location.x);
    oos.writeInt(location.y);
    oos.writeInt(location.z);
    oos.writeInt(location.id);
}

private Location readObject(ObjectInputStream ois) throws
ClassNotFoundException, IOException {
    // default deserialization
    ois.defaultReadObject();
	int x = ois.readInt();
	int y = ois.readInt();
	int z = ois.readInt();
	int id = ois.readInt();
    return new Location(x, y, z, id);
}
}

class Location implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7587285502318207028L;
	public int x, y, z, id;
	Location(int x, int y, int z, int id){
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
	}
}
