package com.sunilsahoo.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sunilsahoo.algorithm.Utility;


public class Practise3 {
	static final HashMap<Integer, String> BASE7MAP = new HashMap<Integer, String>();
	public static void main(String[] args){
		
		String data1 = "Sunil";
		String constraint = "sk";
		int index = data1.toLowerCase().indexOf(constraint.toString().toLowerCase());
        if (index != -1) {
//        	int index1 = index;
        	int index2 = index+constraint.length();
            String substr1 = data1.substring(0,index);
            String substr2 = data1.substring(index2);
            String substr3 = data1.substring(index, index2);
            System.out.println(index+" : "+index2);
//            String substr3 = data1.substring(beginIndex)
            System.out.println(substr1+"<b>"+substr3+"</b>"+substr2);
            
        }
        
		Set<PersonTest> set = new HashSet<PersonTest>();
		set.add(new PersonTest("sunil", 1));
		set.add(new PersonTest("susil", 2));
		
		System.out.println(set.contains(new PersonTest("susil", 2)));
		
		
		
		List<PersonTest> list = new ArrayList<PersonTest>();
		list.add(set.iterator().next());
		System.out.println(set.contains(list.get(0)));
		Collections.shuffle(list);
		
		Practise3 pr = new Practise3();
		int[] input = {45,23,11,10,98,30};
		int middle = (0+input.length-1)/2;
		pr.mergeParts(0, middle, input.length-1, input);
		System.out.println(Utility.toString(input));
		
		System.out.println(pr.isPalindrome("abdba"));
		
		String[] listSource = {"1","2","3","4"};
		String[] target = {"2","3"};
		int result = pr.findArray(listSource, target);
		System.out.println("Result : "+result);
		
		String str = "1,2,3";
		String[] intArr = str.split(",");
		
		
		BASE7MAP.put(0,"0");
        BASE7MAP.put(1,"a");
        BASE7MAP.put(2,"t");
        BASE7MAP.put(3,"l");
        BASE7MAP.put(4,"s");
        BASE7MAP.put(5,"i");
        BASE7MAP.put(6,"n");
//        int number = Integer.parseInt(inputData);
        String result1 = encode(convertTo(7, 7));
        System.out.println(result1);
		
	}
	private static String encode(String data){
		System.out.println(BASE7MAP);
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<data.length(); i++){
            sb.append(BASE7MAP.get(Integer.parseInt(""+data.charAt(i))));
        }
        return sb.toString();
    }
    private static String convertTo(int number, int base)
	{
	    int quotient = number / base;
	    int remainder = number % base;

	    if (quotient == 0) // base case
	    {
	        return Integer.toString(remainder);      
	    }
	    else
	    {
	        return convertTo(quotient, base) + Integer.toString(remainder);
	    }            
	}
	
	public int findArray(String[] array, String[] subArray)
	{
	    return Collections.indexOfSubList(Arrays.asList(array), Arrays.asList(subArray));
	}
	
	private boolean subArray(List<Integer> listSource, List<Integer> target){
		boolean isMatched = true;
	    int [] indexArray = new int[target.size()];
	    for(int i = 0; i< target.size(); i ++) {
	        indexArray[i] = listSource.indexOf(target.get(i));
	        if ( i !=0 ) {
	            if ((indexArray[i] - indexArray[i-1]) != 1) {
	                isMatched = false;
	                break;
	            }
	        }
	    }
	    System.out.println("isMatched:"+isMatched);
	    return isMatched;
	}
	
	private String isPalindrome(String inputString) {
		char ar[] = inputString.toCharArray();
		int i = -1, j = ar.length;
		while(++i<--j){
			if (ar[i] != ar[j])
				return "NO";
		}
		return "YES";
	}
	
    private void mergeParts(int low, int middle, int high, int input[]){
        int temp[] = new int[high-low+1];
        int i = low;
        int j = middle+1;
        int r = 0;
        while(i <= middle && j <= high){
            if(input[i] <= input[j]){
                temp[r++] = input[i++];
            }else{
                temp[r++] = input[j++];
            }
        }
        while(i <= middle){
            temp[r++] = input[i++];
        }
        
        while(j <= high){
            temp[r++] = input[j++];
        }
        i = low;
        for(int k=0; k < temp.length;){
            input[i++] = temp[k++];
        }
    }


}
class PersonTest{
	String name;
	int age;
	PersonTest(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("comparing : "+this);
		return this.name.equals(((PersonTest)obj).name);
	}
	
//	@Override
//	public int hashCode() {
//		System.out.println("hashcode : "+this);
//		return this.name.hashCode();
//	}
	
	@Override
	public String toString() {
		return this.name+" : "+this.age;
	}
	
	
}
