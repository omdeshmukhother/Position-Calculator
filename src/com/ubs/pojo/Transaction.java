package com.ubs.pojo;

public class Transaction {
	
	
	private int TransactionId;
	private String Instrument;
	private char TransactionType;
	private long TransactionQuantity;
	
	
	
	@Override
	public String toString() {
		return "Transaction [TransactionId=" + TransactionId + ", Instrument=" + Instrument + ", TransactionType="
				+ TransactionType + ", TransactionQuantity=" + TransactionQuantity + "]";
	}
	
	
	public int getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}
	public String getInstrument() {
		return Instrument;
	}
	public void setInstrument(String instrument) {
		Instrument = instrument;
	}
	public char getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(char transactionType) {
		TransactionType = transactionType;
	}
	public long getTransactionQuantity() {
		return TransactionQuantity;
	}
	public void setTransactionQuantity(long transactionQuantity) {
		TransactionQuantity = transactionQuantity;
	}
	
	
	
	
	

}
