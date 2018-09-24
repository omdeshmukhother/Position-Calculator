package com.ubs.pojo;

public class Instrument {
	
	private String instruName;
	private int account;
	private char accntType;
	private long qnty;
	private long delta;		
	
	
	public Instrument(String instruName, int account, char accntType, long qnty, long delta) {
		super();
		this.instruName = instruName;
		this.account = account;
		this.accntType = accntType;
		this.qnty = qnty;
		this.delta = delta;
	}
	
	
	public String getInstruName() {
		return instruName;
	}
	public void setInstruName(String instruName) {
		this.instruName = instruName;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public char getAccntType() {
		return accntType;
	}
	public void setAccntType(char accntType) {
		this.accntType = accntType;
	}
	public long getQnty() {
		return qnty;
	}
	public void setQnty(long qnty) {
		this.qnty = qnty;
	}	
	public long getDelta() {
		return delta;
	}
	public void setDelta(long delta) {
		this.delta = delta;
	}


	
	
	@Override
	public String toString() {
		return "Instrument [instruName=" + instruName + ", account=" + account + ", accntType=" + accntType + ", qnty="
				+ qnty + ", delta=" + delta + "]";
	}


	@Override
	public int hashCode() {
		
		//System.out.println("in hashcode");
		
		int hashcode = 0;
        //hashcode = account*20;
        hashcode += instruName.hashCode();
        
        return hashcode;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		//SSystem.out.println("in equals");
		
		if (obj instanceof Instrument) {
			Instrument ii = (Instrument) obj;
            return (ii.instruName.equals(this.instruName) && ii.account == this.account);
        } else {
            return false;
        }
		
	}
	
	
	
	
	
	
	

}
