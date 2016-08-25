package ics311;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class runTest {
	public static void main(String[] args){
		ArrayList<String> data = new ArrayList<String>();
		String line = "";
		TimeData timer = new TimeData();
		
		 
		try{
			Scanner scan = new Scanner(new File(args[0]));
			while(scan.hasNextLine()){
				line = scan.nextLine();
				data.add(line);
			}
			
			scan.close();
		}
		catch(IOException e)
		{
			System.err.println("Cannot read from file" + e.getMessage());
		}
		
		//Red Black Tree
		TimeData rbTimer = new TimeData();
		RBTreeRunTest rbtTest = new RBTreeRunTest(data, rbTimer);
		rbTimer = rbtTest.startTests();
		
		//Binary Search Tree
		TimeData bsTimer = new TimeData();
		BSTRunTest bstTest = new BSTRunTest (data,bsTimer);
		bsTimer = bstTest.startTests();
		
		//Skip List
		TimeData slTimer = new TimeData();
		SLRunTest slTest = new SLRunTest(data,slTimer);
		slTimer = slTest.startTests();
		
		//Doubly Linked List
		TimeData dlTimer = new TimeData();
		DLLRunTest dllTest = new DLLRunTest(data,dlTimer);
		dlTimer = dllTest.startTests();
		
		System.out.println("Size: "+data.size());
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("            |             Insert            |            Retrieve           |              Pred             |               Succ            |    Min   |     Max     |             Delete            |");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"); 
		for(int i =0;i<4;i++){
			if(i == 0)
				System.out.print("Linked List ");
			if(i == 1)
				System.out.print("Skip List   ");
			if(i == 2)
				System.out.print("Binary Tree ");
			if(i == 3)
				System.out.print("RB Tree     ");
			for(int j =0;j<7;j++){
				System.out.print("|");
				
				//Linked List
				if(i == 0){
					switch(j){
					//Insert
					case 0:
						System.out.printf("%9d /%9d /%9d",dlTimer.insertMin,dlTimer.insertAvg,dlTimer.insertMax);
						break;

						//Retrieve
					case 1:
						System.out.printf("%9d /%9d /%9d",dlTimer.retrieveMin,dlTimer.retrieveAvg,dlTimer.retrieveMax);
						break;

						//Pred
					case 2:
						System.out.printf("%9d /%9d /%9d",dlTimer.predMin,dlTimer.predAvg,dlTimer.predMax);
						break;
						//Succ
					case 3:
						System.out.printf("%9d /%9d /%9d",dlTimer.succMin,dlTimer.succAvg,dlTimer.succMax);
						break;

						//Min
					case 4:
						System.out.printf("%10d",dlTimer.minTotal);
						break;

						//Max
					case 5:
						System.out.printf("%13d",dlTimer.maxTotal);
						break;

						//Delete
					case 6:
						System.out.printf("%9d /%9d /%9d",dlTimer.deleteMin,dlTimer.deleteAvg,dlTimer.deleteMax);
						break;
					default:
						System.out.print("INVALID");
					}
				}
				
				if(i == 1){
					switch(j){
					//Insert
					case 0:
						System.out.printf("%9d /%9d /%9d",slTimer.insertMin,slTimer.insertAvg,slTimer.insertMax);
						break;

						//Retrieve
					case 1:
						System.out.printf("%9d /%9d /%9d",slTimer.retrieveMin,slTimer.retrieveAvg,slTimer.retrieveMax);
						break;

						//Pred
					case 2:
						System.out.printf("%9d /%9d /%9d",slTimer.predMin,slTimer.predAvg,slTimer.predMax);
						break;
						//Succ
					case 3:
						System.out.printf("%9d /%9d /%9d",slTimer.succMin,slTimer.succAvg,slTimer.succMax);
						break;

						//Min
					case 4:
						System.out.printf("%10d",slTimer.minTotal);
						break;

						//Max
					case 5:
						System.out.printf("%13d",slTimer.maxTotal);
						break;

						//Delete
					case 6:
						System.out.printf("%9d /%9d /%9d",slTimer.deleteMin,slTimer.deleteAvg,slTimer.deleteMax);
						break;
					default:
						System.out.print("INVALID");
					}
				}//End Skip List
				
				if(i == 2){
					switch(j){
					//Insert
					case 0:
						System.out.printf("%9d /%9d /%9d",bsTimer.insertMin,bsTimer.insertAvg,bsTimer.insertMax);
						break;

						//Retrieve
					case 1:
						System.out.printf("%9d /%9d /%9d",bsTimer.retrieveMin,bsTimer.retrieveAvg,bsTimer.retrieveMax);
						break;

						//Pred
					case 2:
						System.out.printf("%9d /%9d /%9d",bsTimer.predMin,bsTimer.predAvg,bsTimer.predMax);
						break;
						//Succ
					case 3:
						System.out.printf("%9d /%9d /%9d",bsTimer.succMin,bsTimer.succAvg,bsTimer.succMax);
						break;

						//Min
					case 4:
						System.out.printf("%10d",bsTimer.minTotal);
						break;

						//Max
					case 5:
						System.out.printf("%13d",bsTimer.maxTotal);
						break;

						//Delete
					case 6:
						System.out.printf("%9d /%9d /%9d",bsTimer.deleteMin,bsTimer.deleteAvg,bsTimer.deleteMax);
						break;
					default:
						System.out.print("INVALID");
					}
				}//End Binary Tree
				
				if(i == 3){
					switch(j){
					//Insert
					case 0:
						System.out.printf("%9d /%9d /%9d",rbTimer.insertMin,rbTimer.insertAvg,rbTimer.insertMax);
						break;

						//Retrieve
					case 1:
						System.out.printf("%9d /%9d /%9d",rbTimer.retrieveMin,rbTimer.retrieveAvg,rbTimer.retrieveMax);
						break;

						//Pred
					case 2:
						System.out.printf("%9d /%9d /%9d",rbTimer.predMin,rbTimer.predAvg,rbTimer.predMax);
						break;
						//Succ
					case 3:
						System.out.printf("%9d /%9d /%9d",rbTimer.succMin,rbTimer.succAvg,rbTimer.succMax);
						break;

						//Min
					case 4:
						System.out.printf("%10d",rbTimer.minTotal);
						break;

						//Max
					case 5:
						System.out.printf("%13d",rbTimer.maxTotal);
						break;

						//Delete
					case 6:
						System.out.printf("%9d /%9d /%9d",rbTimer.deleteMin,rbTimer.deleteAvg,rbTimer.deleteMax);
						break;
					default:
						System.out.print("INVALID");
					}
				}//End Red Black Tree
			}
			System.out.println("|");
			
		}
		
		
	}
}
