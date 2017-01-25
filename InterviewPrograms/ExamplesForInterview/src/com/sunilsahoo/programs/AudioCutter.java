package com.sunilsahoo.programs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.List;

public class AudioCutter {
	static String path = "/Users/sunilkumarsahoo/Desktop/1920.m4a";
	static String format = "02:09:15,500 --> 02:09:16,830";
	static LinkedHashMap<String,List<Integer>> map = new LinkedHashMap<>();
public static void main(String[] args){
	
	
	try {
		FileReader br = new FileReader(path);
		FileWriter wr = new FileWriter((new File(path+"1")));
		long startAt = 10000;
		long endAt = 2000000;
		long readLength = 0;
		char chArr;
		int newStart = 0;
		while((chArr = (char) br.read()) != -1){
			readLength++;
			if(readLength>=startAt && readLength<endAt){
				wr.append(chArr);
				newStart++;
			}else if(readLength>endAt){
				break;
			}
		}
		br.close();
		wr.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
}
