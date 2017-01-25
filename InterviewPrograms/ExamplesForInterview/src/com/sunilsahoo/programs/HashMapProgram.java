package com.sunilsahoo.programs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapProgram {

	public static void main(String[] args){
		
		HashMapProgram program = new HashMapProgram();
		
		HashMap<String, String> employeeManager = new HashMap<>();
		employeeManager.put("A", "X");
		employeeManager.put("B", "X");
		employeeManager.put("C", "Y");
		employeeManager.put("X", "Y");
		Map<String, Integer> employeeCountMap = program.getEmployeePerMgrMap(employeeManager);
		System.out.println(employeeCountMap);
		
	}
	
	Map<String, Integer> getEmployeePerMgrMap(Map<String, String> map){
		Map<String, Integer> employeeCountMap = new HashMap<String, Integer>();
		Iterator<Entry<String,String>> entryIterator = map.entrySet().iterator();
		Entry<String, String> entry;
		while(entryIterator.hasNext()){
			entry = entryIterator.next();
			int employeeCountOfKey = (employeeCountMap.get(entry.getKey()) == null ? 0 : employeeCountMap.get(entry.getKey()));
			int employeeCountBefore = employeeCountMap.get(entry.getValue()) == null ? 0 : employeeCountMap.get(entry.getValue());
			employeeCountMap.put(entry.getValue(), 1+employeeCountOfKey+employeeCountBefore);
		}
		return employeeCountMap;
	}
}
