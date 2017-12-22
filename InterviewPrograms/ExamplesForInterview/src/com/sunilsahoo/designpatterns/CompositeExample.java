package com.sunilsahoo.designpatterns;

import java.util.ArrayList;

/**
 * Type : Structural
 * 
 * Intent : • Compose objects into tree structures to represent whole-part
 * hierarchies. Composite lets clients treat individual objects and compositions
 * of objects uniformly. • Recursive composition •
 * "Directories contain entries, each of which could be a directory." •
 * 1-to-many "has a" up the "is a" hierarchy
 * 
 * Problem : Application needs to manipulate a hierarchical collection of
 * "primitive" and "composite" objects. Processing of a primitive object is
 * handled one way, and processing of a composite object is handled differently.
 * Having to query the "type" of each object before attempting to process it is
 * not desirable.
 * 
 * 
 * @author sunilkumarsahoo
 *
 */
public class CompositeExample {

	public static StringBuffer g_indent = new StringBuffer();

	public static void main(String[] args) {

		// Without Composite
		// DirectoryWOComposite one = new DirectoryWOComposite("dir111"), two =
		// new DirectoryWOComposite("dir222"),
		// thr = new DirectoryWOComposite("dir333");
		// FileWOComposite a = new FileWOComposite("a"), b = new
		// FileWOComposite("b"), c = new FileWOComposite("c"),
		// d = new FileWOComposite("d"), e = new FileWOComposite("e");
		// one.add(a);
		// one.add(two);
		// one.add(b);
		// two.add(c);
		// two.add(d);
		// two.add(thr);
		// thr.add(e);
		// one.ls();

		// With Composite
		g_indent = new StringBuffer();
		Directory oneDir = new Directory("dir111"),
				twoDir = new Directory("dir222"),
				thrDir = new Directory("dir333");
		File aFile = new File("a"), bFile = new File("b"),
				cFile = new File("c"), dFile = new File("d"),
				eFile = new File("e");
		oneDir.add(aFile);
		oneDir.add(twoDir);
		oneDir.add(bFile);
		twoDir.add(cFile);
		twoDir.add(dFile);
		twoDir.add(thrDir);
		thrDir.add(eFile);
		oneDir.ls();
	}
}

class FileWOComposite {
	public FileWOComposite(String name) {
		m_name = name;
	}

	public void ls() {
		System.out.println(CompositeExample.g_indent + m_name);
	}

	private String m_name;
}

class DirectoryWOComposite {
	public DirectoryWOComposite(String name) {
		m_name = name;
	}

	public void add(Object obj) {
		m_files.add(obj);
	}

	public void ls() {
		System.out.println(CompositeExample.g_indent + m_name);
		CompositeExample.g_indent.append("   ");
		for (int i = 0; i < m_files.size(); ++i) {
			Object obj = m_files.get(i);
			// Recover the type of this object
			if (obj.getClass().getName().equals("Directory"))
				((DirectoryWOComposite) obj).ls();
			else
				((FileWOComposite) obj).ls();
		}
		CompositeExample.g_indent
				.setLength(CompositeExample.g_indent.length() - 3);
	}

	private String m_name;
	private ArrayList m_files = new ArrayList();
}

// With composite
// Define a "lowest common denominator"
interface AbstractFile {
	public void ls();
}

// File implements the "lowest common denominator"
class File implements AbstractFile {
	public File(String name) {
		m_name = name;
	}

	@Override
	public void ls() {
		System.out.println(CompositeExample.g_indent + m_name);
	}

	private String m_name;
}

// Directory implements the "lowest common denominator"
class Directory implements AbstractFile {
	public Directory(String name) {
		m_name = name;
	}

	public void add(Object obj) {
		m_files.add(obj);
	}

	@Override
	public void ls() {
		System.out.println(CompositeExample.g_indent + m_name);
		CompositeExample.g_indent.append("   ");
		for (int i = 0; i < m_files.size(); ++i) {
			// Leverage the "lowest common denominator"
			AbstractFile obj = (AbstractFile) m_files.get(i);
			obj.ls();
		}
		CompositeExample.g_indent
				.setLength(CompositeExample.g_indent.length() - 3);
	}

	private String m_name;
	private ArrayList m_files = new ArrayList();
}
