package com.sunilsahoo.programs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileCopy {
	private static final int DEFAULT_BUFFER_SIZE = 1024*4;
	public static void main(String[] args){
		String sourcePath="/Users/sunilkumarsahoo/Desktop/lovuhoney.mp3";
		String destinationPath="/Users/sunilkumarsahoo/Desktop/lovuhoney1.mp3";
		
		FileCopy fileCopy = new FileCopy();
		fileCopy.copy(sourcePath, destinationPath, "00:00:38", "00:03:42", "00:03:42");
//		fileCopy.copy(sourcePath, destinationPath, (int)new File(sourcePath).length()-1024, (int)new File(sourcePath).length());
	}

//	private void copy(String sourcePath, String destinationPath, int startIndex, int endIndex){
//		System.out.println("inside copy startIndex : "+startIndex+" endIndex : "+endIndex);
//		while(startIndex<endIndex){
//			FileInputStream fis = null;
//			FileOutputStream fos = null;
//			ByteBuffer buffer = null;
//			if(startIndex>=endIndex){
//				return;
//			}
//			try{
//			fis = new FileInputStream(sourcePath);
//			int availableLength = (endIndex-startIndex);
//			buffer = ByteBuffer.wrap(new byte[DEFAULT_BUFFER_SIZE<availableLength ? DEFAULT_BUFFER_SIZE : availableLength]);
//			System.out.println("startIndex : "+startIndex+" endIndex : "+endIndex);
//			FileChannel fc = fis.getChannel();
//			fc.position(startIndex);
//			int readCount = fc.read(buffer);
//			if(readCount == -1){
//				return;
//			}
//			
////			System.out.println("Data : "+new String(buffer.array()));
//			buffer.flip();
//			fos = new FileOutputStream(destinationPath, true);
//			fos.getChannel().write(buffer, startIndex);
//
//			startIndex+=buffer.capacity();
//			}catch(Exception ex){
//				ex.printStackTrace();
//				break;
//			}finally{
//				try{
//					fis.close();
//					fos.getChannel().close();
//					fos.flush();
//					fos.close();
//					buffer.clear();
//				}catch(Exception ex){
//					
//				}
//			}
//		}
//	}
	
	private void copy(String sourcePath, String destinationPath, int startIndex, int endIndex){
		System.out.println("inside copy startIndex : "+startIndex+" endIndex : "+endIndex);
		int outDirStartIndex = 0;
		while(startIndex<endIndex){
			FileInputStream fis = null;
			FileOutputStream fos = null;
			ByteBuffer buffer = null;
			if(startIndex>=endIndex){
				return;
			}
			try{
			fis = new FileInputStream(sourcePath);
			int availableLength = (endIndex-startIndex);
			buffer = ByteBuffer.wrap(new byte[DEFAULT_BUFFER_SIZE<availableLength ? DEFAULT_BUFFER_SIZE : availableLength]);
//			System.out.println("startIndex : "+startIndex+" endIndex : "+endIndex+" length : "+buffer.array().length);
			FileChannel fc = fis.getChannel();
			fc.position(startIndex);
			int readCount = fc.read(buffer);
			if(readCount == -1){
				return;
			}
			
//			System.out.println("Data : "+new String(buffer.array()));
			buffer.flip();
			fos = new FileOutputStream(destinationPath, true);
			fos.getChannel().write(buffer, outDirStartIndex);
			outDirStartIndex+=buffer.capacity();
			startIndex+=buffer.capacity();
			}catch(Exception ex){
				ex.printStackTrace();
				break;
			}finally{
				try{
					fis.close();
					fos.getChannel().close();
					fos.flush();
					fos.close();
					buffer.clear();
				}catch(Exception ex){
					
				}
			}
		}
	}
	
	private void copy(String sourcePath, String destinationPath, String startDuration, String endDuration, String totalDuration){
		new File(destinationPath).delete();
		int startIndex=0, endIndex=0;
		int totalDurationInt = getDurationInSeconds(totalDuration);
		int startDurationInt = getDurationInSeconds(startDuration);
		int endDurationInt = getDurationInSeconds(endDuration);
		
		long length = new File(sourcePath).length();
		System.out.println("file length : "+length);
		double bytesPerSec = length/totalDurationInt;
		startIndex = (int) (startDurationInt*bytesPerSec);
		endIndex = (int) (endDurationInt * bytesPerSec);
//		endIndex = (int) length;
		copy(sourcePath, destinationPath, startIndex, endIndex);
	}
	
	private int getDurationInSeconds(String duration){
		String[] durationArr = duration.split(":");
		return Integer.valueOf(durationArr[0])*3600+ Integer.valueOf(durationArr[1])*60+Integer.valueOf(durationArr[2]);
		
	}
}
