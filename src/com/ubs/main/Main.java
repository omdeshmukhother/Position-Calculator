package com.ubs.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.ubs.pojo.Instrument;
import com.ubs.pojo.Transaction;
import com.ubs.utils.BankUtils;

public class Main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		
		BankUtils obj = new BankUtils();
		Map<String,Set<Instrument>> instrMap = new HashMap<String,Set<Instrument>>();
		Map<Integer,Transaction> transMap = new HashMap<Integer,Transaction>();
		Map<String, Set<Instrument>> instrStartOfDay = new HashMap<String, Set<Instrument>>();
		
		obj.getInstruments("F:\\Omkar\\UBS INterview\\Input_StartOfDay_Positions.txt",instrMap);
		obj.getInstruments("F:\\Omkar\\UBS INterview\\Input_StartOfDay_Positions.txt",instrStartOfDay);
		obj.getTransFromJson("F:\\Omkar\\UBS INterview\\1537277231233_Input_Transactions.json", transMap);
		
		System.out.println(instrMap);
		System.out.println(transMap);
		
		
		
		
		obj.calculatePosition(instrMap, transMap);
		
		System.out.println("AFTER ALL");
		
		obj.calculateDelta(instrStartOfDay, instrMap);
		
		System.out.println(instrMap);
		
		obj.createEODFile(instrMap, "F:\\Omkar\\UBS INterview\\MyExpectedResult.csv");
		
		

		

	}

}
