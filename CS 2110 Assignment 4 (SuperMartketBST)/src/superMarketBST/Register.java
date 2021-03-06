package superMarketBST;

import java.util.ArrayList;

public class Register implements Runnable {
	private ArrayList<Person> q;
	private long serveSpeed;
	private static int slowestSpeed=2000;
	private static int fastestSpeed=500;
	private int length=0;
	private static Register shortestLine=null;
	private static ArrayList<Register> r;
	private String name;
	private BSTree index;
	private static int personNumber=1;
	
	static{
		r=new ArrayList<Register>();
	}
	
	public Register() {
		q = new ArrayList<Person>();
		serveSpeed=(long) (slowestSpeed*Math.random()+fastestSpeed);
		shortestLine=this;
		r.add(this);
		name = "register "+personNumber;
        personNumber++;
        index = new BSTree();
        Thread t = new Thread(this);
        t.setName(name);
        t.start();
	}//Constructor
	
	//run method for thread. Basically always be serving.
	public void run(){
		while (SuperMarket.getRunning()){
			//System.out.println("check empty");
			while(!q.isEmpty()){
				//System.out.println("in deque");
				deQ();
				//System.out.println("out deque");
			}
		}
	}//End run
	/**
	 * 
	 * @return shortestLine
	 */
	//Mutator method
	public static Register getShortestLine(){//allows for getting of shortest line
		return shortestLine;
	}
	
	/**
	 * 
	 * @param p
	 */
	//Put into Queue Class
	public void enQ(Person p){//someone enters the line
		q.add(p);
		index.join(p);
		p.setSpotInLine(length+1);
		p.setRegister(this);
		length++;
		if(q.size()<shortestLine.getLength()) setShortestLine(this);
	}//Close enQ
	
	/**
	 * 
	 */
	public void deQ() {//Cashier serves first person
		if(!q.isEmpty()) {
			//System.out.println("its not empty");
			try {
				//System.out.println("enter sleep");
				Thread.sleep(serveSpeed);
				//System.out.println("exit sleep");
				System.out.print(q.get(0)+ " Served in: ");
				System.out.println(String.format("%.2f", ((double) serveSpeed/1000)) +"s");

			} catch (InterruptedException e) {
				// TODO Tell me it went wrong
				e.printStackTrace();
			};
			//System.out.println("clerical");
			index.find(q.get(0)).getData().setInStore(false);;
			index.remove(q.get(0));
			q.remove(0);
			//System.out.println("removed");
			for(int i=0;i<q.size();i++){
				q.get(i).setSpotInLine(q.get(i).getSpotInLine()-1);
			}//end for
			//System.out.println("shortness found");
			SuperMarket.served();
			System.out.println("\tServed "+ SuperMarket.getPeopleServed()+" People");
			length--;
			//System.out.println("length");
			if(q.size()<shortestLine.getLength()) {
				//System.out.println("in shortness");
				setShortestLine(this);}
			//System.out.println("done deque");
			}//Close check if not empty
	}//Close deQ
	
	/**
	 * 
	 * @param p
	 */
	public void leave(Person p) {//Person leaves line
		p.setSpotInLine(0);
		index.remove(p);
		q.remove(q.indexOf(p));
		length--;
		Feeder.getTheFeeder().getShoppers().add(p);
		if(q.size()<shortestLine.getLength()) {setShortestLine(this);}
	}//Close leave
	
	/**
	 * 
	 * @return q.size()
	 */
	public int getLength() {//Accessory Method
		return q.size();
	}
	
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	public BSTree getIndex() {
		return index;
	}
	
	/**
	 * 
	 * @return r
	 */
	public static ArrayList<Register> getRegisters(){
		return r;
	}
	
	/**
	 * Used when having the shortest line is imperative.
	 */
	public synchronized static void setShortestLine(){//sets shortest line
		//if (q.size()<shortestLine.getLength()) {shortestLine=this;};
		for (int i=0;i<r.size();i++){
			if(r.get(i).getLength()<shortestLine.getLength()){
				shortestLine=r.get(i);
			}
		}
	}
	
	/**
	 * used when a register can identify itself as the shortest line.
	 * 
	 * @param reg
	 */
	public static void setShortestLine(Register reg){
		shortestLine=reg;
	};
	
	public String toString(){
		return q.toString();
	}
}//Close class
