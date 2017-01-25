package com.sunilsahoo.programs;

import java.util.HashSet;

public class Practise1 {
public static void main(String args[]){
	Practise1 practise = new Practise1();
	int[] numbers = {2,3,7,4,1,5,6,8,8,9};
	practise.findDuplicateNumber(numbers);
	int[] numbersMultipleDuplicateArr = {2,3,3,1,5,4,2,7,8,9,10,9,7,11};
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
	
	System.out.println("HashSet Size--->>>"+hs.size());
	System.out.println("hs.contains( new Emp(25))--->>>"+hs.contains(new Emp(25)));
	System.out.println("hs.remove( new Emp(24)--->>>"+hs.remove( new Emp(24)));
	System.out.println("Now HashSet Size--->>>"+hs.size());
}


private void findDuplicateNumber(int[] numbers){
	int actualSum = 0;
	int expectedSum = numbers.length*(numbers.length+1)/2;
	for(int i=0; i<numbers.length; i++){
	actualSum +=numbers[i];	
	}
	int duplicateNumber = numbers.length-(expectedSum-actualSum);
	System.out.println("Duplicate Number is :"+duplicateNumber);
}

private void findDuplicateNumbers(int[] numbers){
	int absVal;
	System.out.println("Duplicates available are : ");
	for(int i=0; i<numbers.length; i++){
		absVal = Math.abs(numbers[i]);
		numbers[absVal] = -numbers[absVal];
		if(numbers[absVal]>0){
			System.out.print(Math.abs(numbers[i])+" ");
		}
	}
}
}
class Emp 
{
	private int age ;
	
	public Emp( int age )
	{
		super();
		this.age = age;
	}
	
	public int hashCode()
	{
		return age;
	}
	
	public boolean equals( Object obj )
	{
		boolean flag = false;
		Emp emp = ( Emp )obj;
		if( emp.age == age )
			flag = true;
		return flag;
	}
}



