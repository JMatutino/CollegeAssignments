package ics311;

import java.util.ArrayList;
import java.util.Random;

public class RBTreeRunTest {
	ArrayList<String> data;
	RedBlackDynamicSet rbTree;
	TimeData timer;
	
	public RBTreeRunTest(ArrayList<String> newData, TimeData timer){
		data = newData;
		rbTree = new RedBlackDynamicSet("Red Black Tree");
		this.timer = timer;
	}
	
	
	public TimeData startTests(){
		long start = 0;
		long end = 0;
		long total = 0;
		long max = 0;
		long min = 0;
		
		
		//Insertion
		for(int i = 0;i < data.size();i++){
			start = System.nanoTime();
			rbTree.insert(data.get(i), i);
			end = System.nanoTime();
			//Accumulate total
			total = total + (end - start);
			//Keep track of max
			if(max < (end -start))
				max = (end - start);
			//Get a min
			if(min == 0)
				min = end - start;
			if(min > end-start)
				min = end -start;
		}
		timer.insertMin = min;
		timer.insertMax = max;
		timer.insertAvg = total /data.size();
		
		
		//Retrieve
		//Reset variables
		start = 0;
		end = 0;
		total = 0;
		max = 0;
		min = 0;
		Random rand = new Random();
		int randIndex1 =rand.nextInt(data.size());
		int randIndex2 =rand.nextInt(data.size());
		int randIndex3 =rand.nextInt(data.size());
		int randIndex4 =rand.nextInt(data.size());
		int randIndex5 =rand.nextInt(data.size());
		int randIndex6 =rand.nextInt(data.size());
		int randIndex7 =rand.nextInt(data.size());
		int randIndex8 =rand.nextInt(data.size());
		int randIndex9 =rand.nextInt(data.size());
		int randIndex10 =rand.nextInt(data.size());
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex1));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex2));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex3));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex4));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex5));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex6));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex7));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex8));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex9));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.retrieve(data.get(randIndex10));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		timer.retrieveMin = min;
		timer.retrieveMax = max;
		timer.retrieveAvg = total / 10;
		
		
		
		
		//Successor
		//Reset variables
		start = 0;
		end = 0;
		total = 0;
		max = 0;
		min = 0;
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex1));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex2));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex3));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex4));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex5));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex6));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex7));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex8));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex9));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.successor(data.get(randIndex10));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		timer.succMin = min;
		timer.succMax = max;
		timer.succAvg = total / 10;
		
		
		//Predecessor
		//Reset variables
		start = 0;
		end = 0;
		total = 0;
		//max = 0;
		//min = 0;
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex1));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex2));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex3));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex4));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex5));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex6));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex7));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex8));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex9));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.predecessor(data.get(randIndex10));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		
		timer.predMin =min;
		timer.predMax = max;
		timer.predAvg = total/10;
		
		
		
		
		//Minimum
		//Reset variables
		start = 0;
		end = 0;
		total = 0;
		max = 0;
		min = 0;
		
		start = System.nanoTime();
		rbTree.minimum();
		end = System.nanoTime();
		total = end - start;
		
		timer.minTotal = total;
		
		
		//Maximmum
		//Reset variables
		start = 0;
		end = 0;
		total = 0;
		max = 0;
		min = 0;
		
		start = System.nanoTime();
		rbTree.maximum();
		end = System.nanoTime();
		total = end - start;
		
		timer.maxTotal = total;
		
		
		
		//Delete
		//reset variables
		start = 0;
		end = 0;
		total = 0;
		max = 0;
		min = 0;
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex1));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex2));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex3));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex4));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex5));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex6));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex7));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex8));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex9));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		start = System.nanoTime();
		rbTree.delete(data.get(randIndex10));
		end = System.nanoTime();
		//Accumulate total
		total = total + (end - start);
		//Keep track of max
		if(max < (end -start))
			max = (end - start);
		//Get a min
		if(min == 0)
			min = end - start;
		if(min > end-start)
			min = end -start;
		
		timer.deleteMin = min;
		timer.deleteMax = max;
		timer.deleteAvg = total / 10;
		
		return timer;
	}

}
