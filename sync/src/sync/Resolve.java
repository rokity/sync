package sync;

import java.util.ArrayList;

public class Resolve implements Runnable
{
	ArrayList<Node<String>> livello;
	
	/**
	 * Costruttore della classe Resolve
	 * @param l - ArrayList<Node<String>> livello dell'albero binario da risolvere
	 */
	Resolve(ArrayList<Node<String>> l)
	{
		livello = l;
	}
	
	@Override
	public void run() {
		synchronized(this)
		{
			/**
			 * Per ogni nodo mi prendo i due figli (numeri)
			 * e l'operando e creo l'oggetto Calcola
			 * che eseguirà l'operazione.
			 * Dopodichè sostituisco il nodo con il risultato dell'operazione.
			 */
			for(int i=0;i<livello.size();i++)
			{
				Node<String> operatore = livello.get(i);
				Node<String> primo = livello.get(i).getChildren().get(0);
				Node<String> secondo = livello.get(i).getChildren().get(1);					
				Calcola c = new Calcola(operatore,primo,secondo);
				operatore.setData(c.getResultToString());
			}
			notify();
		}
		
	}
	
}