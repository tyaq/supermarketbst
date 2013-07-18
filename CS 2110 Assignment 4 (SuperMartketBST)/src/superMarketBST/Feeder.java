package superMarketBST;

import java.util.ArrayList;

public class Feeder implements Runnable {
	private ArrayList<Person> shoppers;
	private Person p;
	private Register reg;
	private boolean inital=true;
	private static Feeder theFeeder;
	
	public Feeder(int numberOfShoppers, int numberOfRegisters){
		shoppers = new ArrayList<Person>(numberOfShoppers);
		//creates the number of People class objects that user wants to create
		for(int j = 0; j<numberOfRegisters;j++) {
			reg = new Register();
		}
		for(int i = 0; i < numberOfShoppers; i++) {

		     p = new Person();
		     shoppers.add(p);
		     theFeeder=this;
		}//end for
	}//end constructor
	
	// Always tries to put people in lines
	public void run(){
		while(SuperMarket.getRunning()){
			//System.out.println((!(Feeder.getTheFeeder().getShoppers().isEmpty())));
			//if (!(theFeeder.getShoppers().isEmpty())){
				enQ();
			//}
		}
	}
	
	
	/**
	 * put people in line based on how short it is
	 */
	public void enQ(){
			while(!(shoppers.isEmpty())){
				Register.setShortestLine();
				Register.getShortestLine().enQ(shoppers.get(0));
				shoppers.remove(0);
			}//end while
	}//End method
	
	/**
	 * put people in line based on how short it is, use a initial enQ.
	 */
	public void enQ(String initial){
			while(!(shoppers.isEmpty())){
				Register.setShortestLine();
				Register.getShortestLine().enQ(shoppers.get(0));
				for (int i=0;i<Register.getRegisters().size();i++){
					Register.getRegisters().get(i).getIndex().balance();;
				}//End for
				shoppers.remove(0);
			}//end while
	}//End method
	
	public static Feeder getTheFeeder(){
		return theFeeder;
	}
	
	public ArrayList<Person> getShoppers(){
		return shoppers;
	}
	
	public String toString() {
		return shoppers.toString();
	}
}//end Feeder class
