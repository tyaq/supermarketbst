package superMarketBST;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SuperMarket {
	static boolean running =true;
	static int peopleServed = 0;
	public static void main(String args[]) throws Exception {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("Please enter the number of customers needing to be served.");
		String custString=br.readLine();
		int cust= Integer.parseInt(custString);
		System.out.println("Please enter the number of registers serving customers.");
		String regString=br.readLine();
		int reg= Integer.parseInt(regString);
		
		long start=System.nanoTime();
		//Creates Registers and Customers
		Feeder shop = new Feeder(cust,reg);
		shop.enQ("initial");
		new Thread(shop).start();
		for (int i=0;i<Register.getRegisters().size();i++){
			System.out.println(Register.getRegisters().get(i).getName()+": \n"
		+Register.getRegisters().get(i).getIndex());
		}
		
		//Pauses here until program is finished
		while (running){
			//Make this arg equal to the previous one
			if(peopleServed>=cust) setRunning(false);
		}
		
		
		long end = System.nanoTime();
		System.out.println("Serving took "+ String.format("%.2f",((end-start)/1000000000.))+" Seconds");
		for (int i=0;i<Register.getRegisters().size();i++){
			System.out.println(Register.getRegisters().get(i).getName()+": "
		+Register.getRegisters().get(i));
		}//Close for
		System.out.println("Feeder: "+Feeder.getTheFeeder().getShoppers());
		System.exit(0);
		
	}//Close main method
	
	public static int getPeopleServed(){
		return peopleServed;
	}
	
	public static void served(){
		peopleServed++;
	}
	
	public static boolean getRunning(){
		return running;
	}
	
	public static void setRunning(Boolean run){
		running=run;
	}
}//Close super market class