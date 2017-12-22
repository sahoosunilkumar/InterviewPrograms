package com.sunilsahoo.programs;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Practise {
	int[] charOccurenceArr;
	String inputStr;

	String[] resultArr = new String[4];

	String doesCircleExist(String command) {
		int xCoordinate = 0;
		int yCoordinate = 0;
		int direction = 'N';

		for (int i = 0; i < command.length(); i++) {
			if (command.charAt(i) == 'R') {
				direction = (direction + 1) % 4;
			} else if (command.charAt(i) == 'L') {
				direction = (4 + direction - 1) % 4;
			} else if (command.charAt(i) == 'G') {
				if (direction == 'N')
					yCoordinate++;
				else if (direction == 'E')
					xCoordinate++;
				else if (direction == 'S')
					yCoordinate--;
				else
					xCoordinate--;
			}
		}
		return (xCoordinate == 0 && yCoordinate == 0) ? "YES" : "NO";
	}

	public static void main(String[] args) {
		// Scanner sc;
		// GenTest test = new GenTest();
		// test.setValue(new int[]{4,5});
		// System.out.println(test.getValue());
		// System.out.println(test.getValue() instanceof Integer);
		// System.out.println(test.getValue() instanceof String);
		// System.out.println(test.getValue() instanceof int[]);
		String passwd = "";
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		System.out.println("match : " + passwd.matches(pattern));
		int x = 0;
		int y = 5;
		try {
			int z = y / x;
		} catch (Exception ex) {
			System.out.println("exception");
		}

		Practise practise = new Practise();
		System.out
				.println("circle exists : " + practise.doesCircleExist("RLG"));
		String[] dataArr = practise.findNumbers("Ahello 123,78. !20 pqer12");
		for (String data : dataArr) {
			System.out.println("data :" + data);
		}
		// String str = "bghIXtIk";
		// String regex = "\\w*I[VX]\\w*";
		// Pattern pattern = Pattern.compile(regex);
		// Matcher matcher = pattern.matcher(str);
		//
		// boolean matches = matcher.matches();
		// System.out.println(matches+" "+str.matches(regex));
		System.out.println("Enter the number of nodes in the graph");
		System.out.println(practise.isRedundant("(a) + (b*c)"));
		// String[] str = {"{","[","]","}"};
		try {
			List<Class> list = getClassesForPackage("com.sunilsahoo.algorithm");
			System.out.println(list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(practise.checkBalanced(str));

		// Scanner scanner = new Scanner(System.in);
		//
		//// scanner.useDelimiter(",");
		//
		// while(true){
		// String inputStr = scanner.next();
		// System.out.println(scanner.hasNext()+" new "+inputStr);
		// if(!scanner.hasNext()){
		// break;
		// }
		// }
		//

		System.out.println((5 ^ 6) + " xor value : " + getXor(5, 6));
		int arr[] = { 1, 2, 2, 3, 4, 1 };
		int count = countEvenSum(arr, arr.length);
		System.out.println("count : " + count);
		int[] arr1 = { 3, 5, 8, 9 };
		int[] arr2 = { 18, 20, 15, 30 };
		int diff = 839240823;
		int leftIndexOfResult = 0, rightIndexOfResult = 0;
		int matchNumber = 25;
		int leftIndex = 0, rightndex = arr2.length - 1;
		int tempResult = arr1[leftIndex] + arr2[rightndex] - matchNumber;
		while (leftIndex < arr1.length && rightndex >= 0) {
			tempResult = arr1[leftIndex] + arr2[rightndex] - matchNumber;
			if (tempResult < 0) {
				tempResult = 0 - tempResult;
			}
			if (tempResult < diff) {
				leftIndexOfResult = leftIndex;
				rightIndexOfResult = rightndex;
				diff = arr1[leftIndex] + arr2[rightndex] - matchNumber;
				if (diff < 0) {
					diff = 0 - diff;
				}
			}

			if (arr1[leftIndex] + arr2[rightndex] > matchNumber)
				rightndex--;
			else
				leftIndex++;
		}
		System.out.print(" " + arr1[leftIndexOfResult] + " : "
				+ arr2[rightIndexOfResult]);
	}

	private String[] findNumbers(String str) {
		String[] output;
		List<String> numberList = new ArrayList<>();
		String tempData = "";
		boolean prevVal = false;
		boolean currVal = false;
		int lastIndex = 0;
		for (int i = 0; i < str.length(); i++) {
			currVal = isNumber(str.charAt(i));
			if (currVal) {
				tempData += str.charAt(i);

				lastIndex = i;
			}

			if (prevVal != currVal || i == str.length() - 1) {
				if (i == lastIndex + 1 || i == str.length() - 1) {
					numberList.add(tempData);
					// System.out.println(tempData);
					tempData = "";
				}

			}
			prevVal = currVal;
		}
		output = new String[numberList.size()];
		return numberList.toArray(output);
	}

	private boolean isNumber(char c) {
		return c >= 48 && c <= 57;
	}

	static boolean isRedundant(String expr) {
		Stack<Character> parenthesisStack = new Stack<>();

		for (int i = 0; i < expr.length(); i++) {
			if ('(' == expr.charAt(i)) {
				parenthesisStack.push(expr.charAt(i));
			} else {
				if (isMatching(expr.charAt(i), parenthesisStack.isEmpty() ? ' '
						: parenthesisStack.peek())) {
					if (')' == expr.charAt(i)) {
						parenthesisStack.pop();
					}
				} else {
					return true;
				}

			}
		}
		return (parenthesisStack.isEmpty()) ? false : true;

	}

	static boolean isMatching(char inputChar, char matchAgainst) {
		return (')' == inputChar && '(' == matchAgainst)
				|| ('}' == inputChar && '{' == matchAgainst)
				|| (']' == inputChar && '[' == matchAgainst);
	}

	// static String[] braces(String[] values) {
	// String[] result = new String[values.length];
	// for(int i=0; i<values.length; i++){
	// result[i] = checkBalanced(values[i]);
	// }
	// return result;
	//
	// }

	private boolean isMatching(String inputChar, String matchAgainst) {
		return (")" == inputChar && "(" == matchAgainst)
				|| ("}" == inputChar && "{" == matchAgainst)
				|| ("]" == inputChar && "[" == matchAgainst);
	}

	static int countEvenSum(int arr[], int n) {
		// A temporary array of size 2. temp[0] is
		// going to store count of even subarrays
		// and temp[1] count of odd.
		// temp[0] is initialized as 1 because there
		// a single even element is also counted as
		// a subarray
		int temp[] = { 1, 0 };

		// Initialize count. sum is sum of elements
		// under modulo 2 and ending with arr[i].
		int result = 0, sum = 0;

		// i'th iteration computes sum of arr[0..i]
		// under modulo 2 and increments even/odd count
		// according to sum's value
		for (int i = 0; i <= n - 1; i++) {
			// 2 is added to handle negative numbers
			sum = ((sum + arr[i]) % 2 + 2) % 2;

			// Increment even/odd count
			temp[sum]++;
		}

		// Use handshake lemma to count even subarrays
		// (Note that an even cam be formed by two even
		// or two odd)
		result = result + (temp[0] * (temp[0] - 1) / 2);
		result = result + (temp[1] * (temp[1] - 1) / 2);

		return (result);
	}

	/**
	 * calculates the XOR total run from [0, targetNumber]
	 */
	static int run(int targetNumber) {
		int res[] = { targetNumber, 1, targetNumber + 1, 0 };
		return res[(targetNumber % 4)];
	}

	static int getXor(int startRange, int endRange) {
		return run(endRange) ^ run(startRange - 1);
	}

	private static List<Class> getClassesForPackage(String pckgname)
			throws ClassNotFoundException {
		// This will hold a list of directories matching the pckgname. There may
		// be more than one if a package is split over multiple jars/paths
		ArrayList<File> directories = new ArrayList<File>();
		String packageToPath = pckgname.replace('.', '/');
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}

			// Ask for all resources for the packageToPath
			Enumeration<URL> resources = cld.getResources(packageToPath);
			while (resources.hasMoreElements()) {
				directories.add(new File(URLDecoder
						.decode(resources.nextElement().getPath(), "UTF-8")));
			}
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(pckgname
					+ " does not appear to be a valid package (Null pointer exception)");
		} catch (UnsupportedEncodingException encex) {
			throw new ClassNotFoundException(pckgname
					+ " does not appear to be a valid package (Unsupported encoding)");
		} catch (IOException ioex) {
			throw new ClassNotFoundException(
					"IOException was thrown when trying to get all resources for "
							+ pckgname);
		}

		ArrayList<Class> classes = new ArrayList<Class>();
		// For every directoryFile identified capture all the .class files
		while (!directories.isEmpty()) {
			File directoryFile = directories.remove(0);
			if (directoryFile.exists()) {
				// Get the list of the files contained in the package
				File[] files = directoryFile.listFiles();

				for (File file : files) {
					// we are only interested in .class files
					if ((file.getName().endsWith(".class"))
							&& (!file.getName().contains("$"))) {
						// removes the .class extension
						int index = directoryFile.getPath()
								.indexOf(packageToPath);
						String packagePrefix = directoryFile.getPath()
								.substring(index).replace('/', '.');
						;
						try {
							String className = packagePrefix + '.'
									+ file.getName().substring(0,
											file.getName().length() - 6);
							classes.add(Class.forName(className));
						} catch (NoClassDefFoundError e) {
							// do nothing. this class hasn't been found by the
							// loader, and we don't care.
						}
					} else if (file.isDirectory()) { // If we got to a
														// subdirectory
						directories.add(new File(file.getPath()));
					}
				}
			} else {
				throw new ClassNotFoundException(
						pckgname + " (" + directoryFile.getPath()
								+ ") does not appear to be a valid package");
			}
		}
		return classes;
	}

	public static String convertToString(Set<String> set, String delimeter) {
		if ((set == null) || set.isEmpty()) {
			return "";
		}
		Iterator<String> iterator = set.iterator();
		StringBuffer sb = new StringBuffer();
		boolean isFirstElement = true;
		while (iterator.hasNext()) {
			if (isFirstElement) {
				sb.append(iterator.next());
			} else {
				sb.append(delimeter).append(iterator.next());
			}
			isFirstElement = false;
		}

		return sb.toString();

	}

}

class GenTest<E> {
	private E value;

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}
}