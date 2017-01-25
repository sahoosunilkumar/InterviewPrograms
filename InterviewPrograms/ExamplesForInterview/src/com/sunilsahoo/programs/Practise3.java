package com.sunilsahoo.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sunilsahoo.algorithm.Utility;


public class Practise3 {
	
	public static void main(String[] args){
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
		// TODO Auto-generated method stub
		System.out.println("comparing : "+this);
		return this.name.equals(((PersonTest)obj).name);
	}
	
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		System.out.println("hashcode : "+this);
//		return this.name.hashCode();
//	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+" : "+this.age;
	}
	
	
}
