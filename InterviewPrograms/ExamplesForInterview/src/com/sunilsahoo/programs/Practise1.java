package com.sunilsahoo.programs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.swing.text.Utilities;

public class Practise1 {
	public static void main(String args[]) {
		Practise1 practise = new Practise1();

		int x = 5;
		int y = 7;
		practise.swap(x, y);
		System.out.println("x : "+x+" y : "+y);
		
		int arr[] = { 25, 36 };
		long sum = practise.countSum(arr);
		System.out.println("sum : " + sum);
		practise.missingWords("I am using hackerrank to improve programming", "am hackerrank to improve");
		int[] numbers = { 2, 3, 7, 4, 1, 5, 6, 8, 8, 9 };
		practise.findDuplicateNumber(numbers);
		int[] numbersMultipleDuplicateArr = { 2, 3, 3, 1, 5, 4, 2, 7, 8, 9, 10,
				9, 7, 11 };
		practise.findDuplicateNumbers(numbersMultipleDuplicateArr);

		Emp emp1 = new Emp(23);
		Emp emp2 = new Emp(24);
		Emp emp3 = new Emp(25);
		Emp emp4 = new Emp(26);
		Emp emp5 = new Emp(27);
		HashSet<Emp> hs = new HashSet<Emp>();
		hs.add(emp1);
		hs.add(emp2);
		hs.add(emp3);
		hs.add(emp4);
		hs.add(emp5);

		System.out.println("HashSet Size--->>>" + hs.size());
		System.out.println(
				"hs.contains( new Emp(25))--->>>" + hs.contains(new Emp(25)));
		System.out.println(
				"hs.remove( new Emp(24)--->>>" + hs.remove(new Emp(24)));
		System.out.println("Now HashSet Size--->>>" + hs.size());
	}
	
	private void swap(int value1, int value2){
        value1 = value1+ value2;
        value2 = value1 - value2;
        value1 = value1 - value2;
        System.out.println(value1+" : "+value2);
    }
	
	
	static String[] missingWords1(String s, String t) {
        if(s == null){
			return null;
		}
		if(t == null){
			return s.split(" ");
		}
        
		String sentenceWordArr[] = s.split(" ");
		String matchingWordArr[] = t.split(" ");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < sentenceWordArr.length; i++) {
			boolean isMatched = false;
			for (int j = 0; j < matchingWordArr.length; j++) {
				if (sentenceWordArr[i].equals(matchingWordArr[j])) {
					isMatched = true;
                    matchingWordArr[j] = "";
					break;
				}
			}
			if (!isMatched) {
				list.add(sentenceWordArr[i]);
			}
		}
		String[] missingWordsArr = new String[list.size()];
		list.toArray(missingWordsArr);
        return missingWordsArr;
	}
	
	
	static String[] missingWords(String s, String t) {
        if(s == null){
			return null;
		}
		if(t == null){
			return s.split(" ");
		}
        
		String sentenceWordArr[] = s.split(" ");
		String matchingWordArr[] = t.split(" ");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < sentenceWordArr.length; i++) {
			boolean isMatched = false;
			for (int j = 0; j < matchingWordArr.length; j++) {
				if (sentenceWordArr[i].equals(matchingWordArr[j])) {
					isMatched = true;
                    matchingWordArr[j] = "";
					break;
				}
			}
			if (!isMatched) {
				list.add(sentenceWordArr[i]);
			}
		}
		String[] missingWordsArr = new String[list.size()];
		list.toArray(missingWordsArr);
		System.out.println(list);
        return missingWordsArr;
	}

	static long countSum(int[] numbers) {
		long sum = 0;
		for (int input : numbers) {
			sum = sum + oddDivisorSum(input);
		}
		return sum;

	}

	/*
	 * calculate odd divisor of provided number
	 */
	private static long oddDivisorSum(int input) {
		int maxDivisor = (int) Math.sqrt(input);
		long sum = 0;
		for (int i = 1; i <= maxDivisor; i++) {
			if (input % i == 0) {
				if (isOdd(i)) {
					sum += i;
				}
				int remain = input / i;
				if (remain != i && isOdd(remain))
					sum += remain;
			}
		}
		return sum;
	}

	private static boolean isOdd(int number) {
		return number % 2 != 0;
	}

	private void findDuplicateNumber(int[] numbers) {
		int actualSum = 0;
		int expectedSum = numbers.length * (numbers.length + 1) / 2;
		for (int i = 0; i < numbers.length; i++) {
			actualSum += numbers[i];
		}
		int duplicateNumber = numbers.length - (expectedSum - actualSum);
		System.out.println("Duplicate Number is :" + duplicateNumber);
	}

	private void findDuplicateNumbers(int[] numbers) {
		int absVal;
		System.out.println("Duplicates available are : ");
		for (int i = 0; i < numbers.length; i++) {
			absVal = Math.abs(numbers[i]);
			numbers[absVal] = -numbers[absVal];
			if (numbers[absVal] > 0) {
				System.out.print(Math.abs(numbers[i]) + " ");
			}
		}
	}
}

class Emp {
	private int age;

	public Emp(int age) {
		super();
		this.age = age;
	}

	public int hashCode() {
		return age;
	}

	public boolean equals(Object obj) {
		boolean flag = false;
		Emp emp = (Emp) obj;
		if (emp.age == age)
			flag = true;
		return flag;
	}
}
