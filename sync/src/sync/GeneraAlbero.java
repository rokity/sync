package sync;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneraAlbero implements Runnable
{
	String filename ;
	Node<String> result;
	Node<String> radice;
	public ArrayList<ArrayList<Node<String>>> livelli ;

	GeneraAlbero(String f,ArrayList<ArrayList<Node<String>>>  l)
	{
		filename = f;
		livelli = l;
	}
	
	@Override
	public void run() {
		synchronized (this) {	
			try {
				String current = new File( "." ).getCanonicalPath();
				List<Node<String>> foglie = new ArrayList<>();
				File a = new File(current.concat(this.filename));
				try {
					Scanner x = new Scanner(a);
					radice = new Node<String>(x.next(),0);
					addNodoToArray(radice);
					Node<String> root = radice;
					while(x.hasNext())			
					{
						String value = x.next();
						try {
							Double primo = Double.parseDouble(value);
							Double secondo = Double.parseDouble(x.next());
							Calcola calc = new Calcola(root,primo,secondo);
							root.setData(calc.getResultToString());
							if(foglie.indexOf(root.getParent())==-1)
								foglie.add(root.getParent());
							//root.addChild(new Node<String>(value));
						}catch(NumberFormatException e)
						{
							try
							{
								Double.parseDouble(root.getData());
								if(root.getParent()!=null)
									root = root.getParent();		
								while(root.getChildren().size()==2)
									root = root.getParent();
								
								Node<String> operatore = root.addChild(new Node<String>(value,root.livello+1));
								addNodoToArray(operatore);
								root = operatore;
							}
							catch(NumberFormatException ee)
							{ 
								if(root.getChildren().size()==0)
								{
									Node<String> operatore = root.addChild(new Node<String>(value,root.livello+1));										
									addNodoToArray(operatore);
									root = operatore;
								}	
								else
								{
									if(root.getParent()!=null)
										root = root.getParent();		
									while(root.getChildren().size()==2)
										root = root.getParent();
									
									Node<String> operatore = root.addChild(new Node<String>(value,root.livello+1));
									addNodoToArray(operatore);
									root = operatore;
								}
							}

						}
					}
				x.close();
				result = radice;
				notify();

			} catch (FileNotFoundException e) {					
				e.printStackTrace();
			} 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void addNodoToArray(Node<String> nodo)
	{
		while(nodo.livello>=livelli.size())										
			livelli.add(new ArrayList<Node<String>>());											
		livelli.get(nodo.livello).add(nodo);
	}
}	
