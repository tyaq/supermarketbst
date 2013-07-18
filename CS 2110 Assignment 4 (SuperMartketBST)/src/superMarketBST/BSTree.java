package superMarketBST;

import java.util.Stack;

public class BSTree {
	
	private BTNode root; // The root of the BST
	private int size; //The number of nodes in BST
	
	public BSTree() {
		root=null;
		size=0;
	}//End Constructor
	
	/**
	 * Adds Person to tree, by running down from the root of the tree.
	 * If they are already in the tree nothing happens.
	 * This method calls to the join() recursive method.
	 * @param Person p
	 */
	public void join(Person p) {
		BTNode last = null;
		root = join(p, root,last);
		size++;
	}//End join method

	/**
	 * Adds Person to the end of the tree,
	 *  by recursively running down to the bottom of the tree.
	 * If they are already in the tree nothing happens.
	 * @param Person p
	 * @param BTNode node
	 * @return
	 */
	private BTNode join(Person p, BTNode node, BTNode last) {
		if (node == null) {//System.out.println("node=null");
		return new BTNode(p,last);}
		//Person nodePerson= node.getData();
		if (p.isLessThan(node.getData())) {//System.out.println("To the left");
		last=node;node.setLeft(join(p, node.getLeft(),last));}
		else if (p.isMoreThan(node.getData())) {//System.out.println("To the right");
		last=node;node.setRight(join(p, node.getRight(),last));}
		return node;
	}//End join Method's recursive call
	
	public void remove(Person p) {
		if(isEmpty()) {
			try {
				throw new Exception("Looking at an OPEN feild I can say " + p
						+ " is not here. (This tree is Empty.)");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//Close if
		if(size==1) {
			root=null;
			size--;
			return;
		}//End if
		remove(p,find(p));
		size--;
	}//End Remove Method
	
	public void remove(Person p,BTNode node) {
		BTNode parent = node.getParent();
		//if (parent==null) {
		//	parent=new BTNode(new Person(0));
		//}
		
		if (node.has2Children()) {//Has Children, so replace with immediately higher
			//System.out.println("Successor: "+getSuccessor(node));
			//BTNode successor=getSuccessor(node);
			//System.out.println("Successor: "+successor);
			setParentChild(p,parent,getSuccessor(node));
		}//End else if
		else if(node.noChildren()) {//Has no children
			setParentChild(p,parent,null);
		}//End case where we have no children
		else if (node.hasLeft()) { //Has no left child, replace with right
			setParentChild(p,parent,node.getLeft());
		}//End case where node has no left child
		else if (node.hasRight()) { //Has no right child,replace with left
			setParentChild(p,parent,node.getRight());
		}//End case where node has no right child
	}//End Remove Method
	
	private void setParentChild(Person p,BTNode parent,BTNode node) {
		if(parent==null){node.setParent(null);root=node;} 
		else {//Normal logic how ever above if is for when removing the root of the tree
		if (p.isLessThan(parent.getData())) {//System.out.println("To the left");
			parent.setLeft(node); }
		else if (p.isMoreThan(parent.getData())) {//System.out.println("To the right");
			parent.setRight(node);}
		}//End else
		if (node!=null) node.setParent(parent);//Set the parent of replacement to parent
	}//End SetParentChild Method
	
	private BTNode getSuccessor(BTNode king) {
		BTNode parent=king;
		BTNode successor=king;
		BTNode current = king.getRight();//Go to larger child
		while (current != null) {
			parent=successor;
			successor=current;
			current= current.getLeft();//get the smallest child for replacement
		}//End while loop
		
		if (successor != king.getRight()) {//Set the successor references to the replaced node.
			parent.setLeft(successor.getRight());
			successor.setRight(king.getRight());
			
			//Sets the parent of the children of the successor to the successor.
			successor.getRight().setParent(successor);
		}//End if prevents infinite loop
		successor.setLeft(king.getLeft());
		
		//Sets the parent of the children of the successor to the successor.
		successor.getLeft().setParent(successor);
		System.out.println("Successor: "+successor);
		return successor;
	}//End getSuccessor method
	/*
	public BTNode remove(Person p) {
		BTNode itr;
		if(!find(p).hasChildren()) {//Has no children
			
		}
		
	}*/
	
	/**
	 * Searches through the tree looking for the Person.
	 * If the tree is empty then nothing happens.
	 * This method calls to the find() recursive method.
	 * @param Person p
	 * @return
	 */
	public BTNode find(Person p) {
		if(isEmpty())
			try {
				throw new Exception("Looking at an OPEN feild I can say " + p
						+ " is not here. (This tree is Empty.)");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return find(p, root);
	}
	
	/**
	 * Searches through the tree, by traveling trough the tree.
	 * If they are not found in the tree then nothing happens.
	 * @param Person p
	 * @param BTNode node
	 * @return Node where person is stored.
	 */
	private BTNode find(Person p,BTNode node) {
		if(node==null)
			try {
				throw new Exception("\"This is not the "
						+ "Person you are looking for.\"(Person not found.)");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(p.matches(node.getData())) return node;
		if(p.isMoreThan(node.getData())) return find(p,node.getRight());
		else	 return find(p,node.getLeft());
	}
	
	public void balance() {//tidyUp() balances tree according to median WIP
		BTNode[] list = new BTNode[size];
		
		
	}
	
	/**
	 * 
	 * @return Boolean telling if tree is empty.
	 */
	public boolean isEmpty() {
		return size==0;
	}
	
	/**
	 * 
	 * @return Size of tree.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Probably don't need this
	 * @param size
	 */
	public void setSize(int size) {
		this.size=size;
	}
	
	/**
	 * Recursively travels entire tree counting for max height.
	 * @param node
	 * @return Height of tree
	 */
	public int getHeight(BTNode node) {
		if (node == null) return -1; //empty tree
		if (node.noChildren()) return 0;
		return 1 + Math.max(getHeight(node.getLeft()),getHeight(node.getRight()));
	}//End height method
	
	/**
	 * Recursively travels entire tree counting up nodes with 2 children.
	 * @param BTNode node
	 * @return int Number of Full Nodes
	 */
	public int getFullNodes(BTNode node) {
		if (node==null) return 0;
		if (node.has2Children()){return(1 + getFullNodes(node.getLeft()) 
										+ getFullNodes(node.getRight()));}
		return (getFullNodes(node.getLeft()) + getFullNodes(node.getRight()));
	}//End getFullNodes Method
	
	public String toString() {
		//String s = treeStats() + printTree();
		//return s;
		String s =treeStats() +"\nTree:\n"+ toString("",root)+"\n";
		return s;
	}//toString Method
	
	private String treeStats() {
		String s="The number of nodes is: "+size+"\n"
				+"The number of edges is: ";
				if(size==0){s+="0";}else{s+=(size-1);}
				s+="\n"
				+"The number of full nodes is: "+getFullNodes(root)+"\n"
				+"The height of the tree is: ";
				if(getHeight(root)<0){s+="Does Not Exist";}else{s+=getHeight(root);}
				s+="\n";
		return s;
	}//Returns answers to problem two Assignment 4
	
	/**
	 * Creates a vertical tree in a string 
	 * @return String with tree info formated
	 */
	private String printTree() {
		Stack globalStack = new Stack();
		String S="";
	      globalStack.push(root);
	      int nBlanks = 64;
	      boolean isRowEmpty = false;
	      S+=
	      "......................................................\n";
	      while(isRowEmpty==false)
	         {
	         Stack localStack = new Stack();
	         isRowEmpty = true;

	         for(int j=0; j<nBlanks; j++)
	            S+=' ';

	         while(globalStack.isEmpty()==false)
	            {
	            BTNode temp = (BTNode)globalStack.pop();
	            if(temp != null)
	               {
	               S+=temp.getData();
	               localStack.push(temp.getLeft());
	               localStack.push(temp.getRight());

	               if(temp.getLeft() != null ||
	                                   temp.getRight() != null)
	                  isRowEmpty = false;
	               }
	            else
	               {
	               S+="--";
	               localStack.push(null);
	               localStack.push(null);
	               }
	            for(int j=0; j<nBlanks*2-2; j++)
	               S+=' ';
	            }  // end while globalStack not empty
	         S+="\n";
	         nBlanks /= 2;
	         while(localStack.isEmpty()==false)
	            globalStack.push( localStack.pop() );
	         }  // end while isRowEmpty is false
	      S+=
	      "......................................................\n";
	      return S;
	}
	
	/**
	 * Creates a horizontal tree in a string
	 * @param String prefix
	 * @param BTNode node
	 * @return String with tree info formated
	 */
	@SuppressWarnings("unused")
	private static String toString(String prefix,BTNode node) {   
		if (node == null) return "";
		String string = prefix + node.getData().toString();
		if (node.getRight() != null)
			string = toString("\t" + prefix, node.getRight()) + "\n" + string;
		
		if (node.getLeft() != null)
			string = string + "\n" + toString("\t" + prefix, node.getLeft());
		return string;
	   }//End method printing tree
}//End BSTree Class
