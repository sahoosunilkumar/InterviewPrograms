package com.sunilsahoo.designprinciple.solid;

/**
 * Entities must depend on abstractions not on concretions. It states that the
 * high level module must not depend on the low level module, but they should
 * depend on abstractions. Secondly, abstractions should not depend upon
 * details; details should depend upon abstractions. The idea is that we isolate
 * our class behind a boundary formed by the abstractions it depends on. If all
 * the details behind those abstractions change, then our class is still safe.
 * This helps keep coupling low and makes our design easier to change. DIP also
 * allows us to test things in isolation, details like database are plugins to
 * our system.
 * 
 * 
 * 
 * Here is an example: A program depends on Reader and Writer interfaces that
 * are abstractions, and Keyboard and Printer are details that depend on those
 * abstractions by implementing those interfaces. Here CharCopier is oblivious
 * to the low-level details of Reader and Writer implementations and thus you
 * can pass in any Device that implements the Reader and Writer interface and
 * CharCopier would still work correctly.
 * 
 * 
 * @author sunilkumarsahoo
 *
 */
public class DependeancyInversionPrincipleExample {

}

interface IReader {
	char getchar();
}

interface IWriter {
	void putchar(char c);
}

class Keyboard implements IReader {

	@Override
	public char getchar() {
		// TODO Auto-generated method stub
		return 0;
	}

}

class Printer implements IWriter {

	@Override
	public void putchar(char c) {
		// TODO Auto-generated method stub

	}

}

//
class CharCopier {
	void copy(IReader reader, IWriter writer) {
		char c;
		while ((c = reader.getchar()) != -1) {
			writer.putchar(c);
		}
	}
}
