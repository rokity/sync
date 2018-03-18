package sync;

import java.sql.Timestamp;
import java.util.ArrayList;


public class Sync {
	
	static Node<String> radice = null;
	static int countThreads = 0;
	static ArrayList<ArrayList<Node<String>>> livelli = new ArrayList<ArrayList<Node<String>>>();
	
	public static void main(String[] args)
	{		
		printTimeStamp();
		String filename = "/expression.txt";
	    try {
			GeneraAlbero g = new GeneraAlbero(filename,livelli);
		    Thread t = new Thread(g);
		    t.start();
			t.join();
			countThreads++;
			for(int i = livelli.size()-2;i>-1;i--)
			{
				Resolve r = new Resolve(livelli.get(i));
				Thread tr = new Thread(r);
				tr.start();
				tr.join();
				countThreads++;
			}
			System.out.println("Risultato: "+livelli.get(0).get(0).getData());
			System.out.println("Numero di Thread:"+countThreads);
			printTimeStamp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
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
