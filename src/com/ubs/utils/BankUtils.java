package com.ubs.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.ubs.pojo.Instrument;
import com.ubs.pojo.Transaction;

public class BankUtils {
	
	
	public void getTransFromJson(String fName,Map<Integer,Transaction> transMap)
	{
		
		Gson gson = new Gson();
		

	
		try {
			
			Transaction[] trans =  gson.fromJson(new FileReader(fName), Transaction[].class);
			//"F:\\Omkar\\UBS INterview\\1537277231233_Input_Transactions.json"
			
			List<Transaction> myTrans = new ArrayList<Transaction>(Arrays.asList(trans));
			
			for(Transaction t : myTrans)
			{
				transMap.put(t.getTransactionId(), t);
				
			}
			
			//return myTrans;
			
			
			
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public  void getInstruments(String fName,Map<String,Set<Instrument>> instrMap) //List<Instrument>
	{
		
		BufferedReader br = null;
		String line =null;
		String splitLine[];
		
		
		
		
		try
		{
			
			br = new BufferedReader(new FileReader(new File(fName)));
			
			br.readLine();
			
			while((line=br.readLine()) != null)
			{
				
				splitLine = line.split(",");
				Instrument instObj = new Instrument(splitLine[0],  Integer.parseInt(splitLine[1]), splitLine[2].charAt(0), Long.parseLong(splitLine[3]), 0L);
				
				if(instrMap.containsKey(splitLine[0]))
				{
					instrMap.get(splitLine[0]).add(instObj);
				}
				else
				{
					Set<Instrument> instrList = new HashSet<Instrument>();
					instrList.add(instObj);
					instrMap.put(splitLine[0], instrList);
										
				}
				
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void calculatePosition(Map<String, Set<Instrument>> instrMap, Map<Integer,Transaction> transMap )
	{
		
		for(Integer tranId : transMap.keySet())
		{
			
			
			Transaction tObj = transMap.get(tranId);
			
			for(String instrName : instrMap.keySet())
			{
				
				
				if(instrName.equals(tObj.getInstrument()))
				{
										
					if(tObj.getTransactionType() == 'B')
					{
												
						for(Instrument inst : instrMap.get(instrName))
						{
							if(inst.getAccntType() == 'E')
							{
								long qnty = inst.getQnty();
								
								inst.setQnty(qnty+tObj.getTransactionQuantity());
								
							}
							else if(inst.getAccntType() == 'I')
							{
								long qnty = inst.getQnty();
								
								inst.setQnty(qnty-tObj.getTransactionQuantity());
							}						
							
							
						
							
						}
					}
					else if(tObj.getTransactionType() == 'S')
					{
						for(Instrument inst : instrMap.get(instrName))
						{
							if(inst.getAccntType() == 'E')
							{
								long qnty = inst.getQnty();
								
								inst.setQnty(qnty-tObj.getTransactionQuantity());
								
							}
							else if(inst.getAccntType() == 'I')
							{
								long qnty = inst.getQnty();
								
								inst.setQnty(qnty+tObj.getTransactionQuantity());
							}
							
							
						}
					}
					
					break;
				}
			}
		}
		
		
		
		
	}
	
	
	
	public void calculateDelta(Map<String, Set<Instrument>> instrStartOfDay, Map<String, Set<Instrument>> instrMap )
	{
		
		
		for(String instrName : instrMap.keySet())
		{
			
			
			for(Instrument inst : instrMap.get(instrName))
			{
				for(String startInstrName : instrStartOfDay.keySet())
				{
					
					for(Instrument startInst : instrStartOfDay.get(startInstrName))
					{
						if((startInst.getInstruName().equals(inst.getInstruName())) && (startInst.getAccount() == inst.getAccount()))
						{
							
							
							inst.setDelta(inst.getQnty() - startInst.getQnty());
							
							instrMap.get(instrName).add(inst);
						}
					}
					
				}				
				
			}
		}
	}
	
	public void createEODFile(Map<String, Set<Instrument>> instrMap, String fName )
	{
		
		  final String COMMA_DELIMITER = ",";
		  final String NEW_LINE_SEPARATOR = "\n";
		  final String FILE_HEADER = "Instrument,Account,AccountType,Quantity,Delta";

		 
		  BufferedWriter writer = null;
		  
		  try
		  {
		
			writer = new BufferedWriter(new FileWriter(fName));
		    writer.write(FILE_HEADER);
		    writer.write(NEW_LINE_SEPARATOR);


		
				for(String instrName : instrMap.keySet())
				{
					
					
					for(Instrument inst : instrMap.get(instrName))
					{
						StringBuilder sb = new StringBuilder();
						
						sb.append(instrName).append(",").append(inst.getAccount()).append(",").append(inst.getAccntType()).append(",").append(inst.getQnty()).append(",").append(inst.getDelta());
						writer.write(sb.toString());
						writer.write(NEW_LINE_SEPARATOR);
					
						
					}
				}
				
				
				
		
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  finally {
			  
			  try {
				  
				  writer.flush();
				  writer.close();
				  } 
			  catch (IOException e) {
				  System.out.println("Error while flushing/closing fileWriter !!!");
				  e.printStackTrace();
				  }
			  }

	}
	
	
	

}
