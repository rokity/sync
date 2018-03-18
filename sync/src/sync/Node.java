package sync;

import java.util.ArrayList;
import java.util.List;


public  class Node<T> {
	 
		private T data = null;
		
		public int livello = -1;
		
		private List<Node<T>> children = new ArrayList<>();
		
		private Node<T> parent = null;
	 
		public Node(T data) {
			this.data = data;
		}
		
		public Node(T data,int l) {
			this.data = data;
			this.livello = l;
		}
		
		public void setLivello(int l)
		{
			this.livello = l;
		}
	 
		public Node<T> addChild(Node<T> child) {
			child.setParent(this);
			this.children.add(child);
			return child;
		}
		
	 
		public void addChildren(List<Node<T>> children) {
			children.forEach(each -> each.setParent(this));
			this.children.addAll(children);
		}
	 
		public List<Node<T>> getChildren() {
			return children;
		}
		
		public boolean hasChildren() {
			if(children!=null)
				return true;
			else
				return false;
		}
		
		/**
		 * Ti calcola la tipologia di nodi che sono contenuti nei figli del nodo
		 * "Double" o "Operator"
		 * @return String- "Double" or "Operator"
		 */
		public String getKindOfChildren()
		{
	    	 	try {
	    	 		Double.parseDouble(String.valueOf(children.get(0).getData()));
	    	 		Double.parseDouble(String.valueOf(children.get(1).getData()));
	    	 		return "Double";
	    	 	}catch(NumberFormatException e)
	    	 	{
	    	 		return "Operator";
	    	 	}
		}
	 
		public T getData() {
			return data;
		}
	 
		public void setData(T data) {
			this.data = data;
		}
	 
		private void setParent(Node<T> parent) {
			this.parent = parent;
		}
	 
		public Node<T> getParent() {
			return parent;
		}
		
		 public static <T> void printTree(Node<T> node, String appender) {
			  System.out.println(appender + node.getData() + " l:"+node.livello );
			  node.getChildren().forEach(each ->  printTree(each, appender + appender));
		 }
	}
