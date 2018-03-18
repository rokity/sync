package senzathread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class senzathread {
    public static void main(String[] args){
    	try {
			String current = new File( "." ).getCanonicalPath();
		printTimeStamp();
		File a = new File(current.concat("/expression.txt"));
		long millis;
		try {
			Scanner x = new Scanner(a);
			 if(x.hasNext())
			{
				String value = x.next();
				try{
					Double result = Double.parseDouble(value);
					System.out.println("Risultato:"+result);
					printTimeStamp();
				}
				catch (NumberFormatException e)
				{
					Double result = thread(x,value);
					System.out.println("Risultato:"+result);
					printTimeStamp();
				}
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
	private static Double thread(Scanner x,String operator)
	{
		if(x.hasNext())
		{
			Double primo = 0.0;
			Double secondo = 0.0;
			String value = x.next();
			try{
				primo = Double.parseDouble(value);
			}
			catch(NumberFormatException e)
			{
				primo = thread(x,value);
				
			}
			if(!x.hasNext())
			{
				return calc(operator,primo,0.0);
			}
			else
			{
				value = x.next();
				try
				{
					secondo = Double.parseDouble(value);
				}
				catch(NumberFormatException e)
				{
					secondo = thread(x,value);
				}
			}
			return calc(operator,primo,secondo);
		}
		else
			return 0.0;
	}
	
	private static Double calc(String operator,Double first,Double second)
	{
		Double result = null;
		if(operator.equals("+"))
			result = first + second;
		if(operator.equals("-"))
			result = first - second;
		if(operator.equals("*"))
			result = first * second;
		if(operator.equals("/"))
			result = first / second;
		return result;
	}
	
	 /**
	  * Stampta il TimeStamp corrente
	  * (Libreria SQL)
	  */
	 public static void printTimeStamp()
	 {
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 System.out.println("TimeStamp:"+timestamp.toString());
	 }
}
