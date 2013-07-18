package superMarketBST;

public class BTNode {
	
	private Person data;
	private BTNode left,right,parent;
	
	//Constructor Methods
	public BTNode(Person p,BTNode parentNode) {
		data = p;
		parent=parentNode;
	}//End Constructor Method
	
	public BTNode(Person p) {
		this(p,null);
	}//End Constructor Method
	
	//Mutator and Accessory Methods
	
	public Person getData ( ) { 
		return data ; 
	}//End getData Method
	
	public void setData ( Person p ) { 
		 data = p ; 
	}//End setData Method
	
	public BTNode getLeft () { 
		return left ; 
	}//End getLeft Method
	
	public void setLeft ( BTNode L ) {
		left = L ;
	}//End setLeft Method
	
	public BTNode getRight ( ) {
		return right ; 
	}//End getRight Method
	
	public void setRight ( BTNode R ) { 
		right = R ; 
	}//End setRight Method
	
	public BTNode getParent () { 
		return parent ; 
	}//End getParent Method
	
	public void setParent ( BTNode P ) { 
		parent = P ; 
	}//End setParent Method
	
	public boolean has2Children() {
		if (hasLeft() && hasRight()) return true;
		else return false;
	}
	
	public boolean noChildren() {
		if (hasLeft() || hasRight()) return false;
		else return true;
	}
	
	public boolean hasLeft() {
		if (left!=null) return true;
		else return false;
	}
	
	public boolean hasRight() {
		if (right!=null) return true;
		else return false;
	}
	
	public String toString() {
		String s=""+data+"\n\tParent: ";
		if(parent!=null){s+=parent.getData();}
		s+="\n\tLeft: ";
		if(left!=null){s+=left.getData();}
		s+="\n\tRight: ";
		if(right!=null){s+=right.getData();}
		s+="\n";
		return s;
	}
	
}//End BSNode Class