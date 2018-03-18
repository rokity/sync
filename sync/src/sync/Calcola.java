package sync;

import sync.Node;

public  class Calcola 
{
String operator ;
Double first;
Double second;

public Calcola(Node<String> o,Node<String> f,Node<String> s)
{
	operator = o.getData();
	try {
		first = Double.parseDouble(f.getData());
		second = Double.parseDouble(s.getData());
	}catch(NumberFormatException e)
	{
		e.printStackTrace();
	}
}

public Calcola(Node<String> o, Double p, Double s) {
	operator = o.getData();
	first = p;
	second = s;
}

public Double getResult()
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

public String getResultToString() {			
	return getResult().toString();
}

}
