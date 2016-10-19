package com.sunilsahoo.thread;

public class ThreadDeadlock {
public static void main(String[] args){
	CashFlow cashFlow1 = new CashFlow("SBI");
	CashFlow cashFlow2 = new CashFlow("HDFC");
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			cashFlow1.deposit(cashFlow2);
		}
	}).start();
new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			cashFlow2.deposit(cashFlow1);
		}
	}).start();
}
}

class CashFlow{
	String atmName;
	public String getName(){
		return atmName;
	}
	CashFlow(String atmName){
		this.atmName = atmName;
	}
	public synchronized void deposit(CashFlow cashFlow){
		String str = String.format("From : %1s Deposited At : %2s", this.atmName, cashFlow.getName());
		System.out.println(str);
		cashFlow.withdraw(this);
	}
	public synchronized void withdraw(CashFlow cashFlow){
		String str = String.format("From : %1s Withdrawn At : %2s", this.atmName, cashFlow.getName());
		System.out.println(str);
	}
}
