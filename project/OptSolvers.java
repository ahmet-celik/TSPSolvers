import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/**
 * Implements 2Opt and 3Opt solutions together
 * @author Ahmet Çelik 2009400111
 *
 */
public class OptSolvers extends TSPSolvers {
	/**
	 * Specify this object as either 2opt or 3opt. 
	 * If it is true this is 2opt, otherwise 3opt.
	 */
	boolean isOpt2;
	/**
	 * Holds result of randomPath(bestPath)
	 */
	private double bestResult;
	/**
	 * Holds current path that is analyzed and that is currently the best path
	 */
	private LinkedList<Integer> randomPath;
	/**
	 * Random creator of class
	 */
	private static Random r;
	/**
	 * Number of Cities
	 */
	private int n;

	/**
	 * Constructs a 2opt or 3opt solver
	 * @param n Number of cities
	 * @param isOpt2 If it is true,this is 2Opt, otherwise 3Opt
	 * @param distances Distance array
	 */
	public OptSolvers(int n,boolean isOpt2){//true:2Opt   false:3Opt
		this.n= n; 
		this.isOpt2 = isOpt2;
		r = new Random();
		randomPath = new LinkedList<Integer>();
		
		for(int i=1;i<n;i++){				
			int randomCity = r.nextInt(n-1)+1;
			while(randomPath.contains(randomCity))
				randomCity = r.nextInt(n-1)+1;
			randomPath.add(new Integer(randomCity));
		}
		randomPath.addLast(new Integer(0));
		randomPath.addFirst(new Integer(0));
		bestResult=TSPSolvers.computeTotalCost(randomPath,false);
			
	}
	/**
	 * Implements required method to be a TSPSolvers
	 */
	public void find() throws IOException{//true:2Opt   false:3Opt
		int timesNoModif=0;
		LinkedList<Integer> cur ;
		while(timesNoModif<50){
			cur = new LinkedList<Integer>(randomPath);
			if(isOpt2)
				cur=newSolution2(cur);
			else 
			    cur=newSolution3(cur);
			double res=TSPSolvers.computeTotalCost(cur,false);
			if(res<bestResult){
				bestResult=res;
				randomPath.clear();
				randomPath.addAll(cur);
				timesNoModif=0;
			}else
				timesNoModif++;
		}
		if(isOpt2)
			TSPSolvers.printPretty(randomPath,"2opt",n);
		else 
		    TSPSolvers.printPretty(randomPath,"3opt",n);

	}
	
	/**
	 * Creates a valid random new solution for 2Opt method
	 * @return 
	 */
	private LinkedList<Integer> newSolution2(LinkedList<Integer> cur){
		int[] bounds =generateBigSmall(3);
		reverse(bounds[0]+1,bounds[1]-bounds[0]-1,cur);
		return cur;
	}
	/**
	 * Try all possible four case with randomly selected edges for 3Opt 
	 * and selects best one as a new solution	
	 * @return 
	 */
	private LinkedList<Integer> newSolution3(LinkedList<Integer> cur){
		int[] bond=generateBigSmall(5);
		int block1Size=r.nextInt(bond[1]-bond[0]-4)+2;
		int block2Size=bond[1]-bond[0]-1-block1Size;
		LinkedList<Integer> currentOne = new LinkedList<Integer>(cur);
		LinkedList<Integer> bestOfFour = new LinkedList<Integer>(cur);
		double bestVal = Double.MAX_VALUE;
		for(int i=0;i<4;i++){
			switch(i){
			  case 0:
				  swap(bond[0]+1,bond[1]-1,block2Size,currentOne ); //no need to create currentOne for this case
				  break;
			  case 1:
				  currentOne = new LinkedList<Integer>(cur);//reset currentOne
				  swap(bond[0]+1,bond[1]-1,block2Size,currentOne);
				  reverse(bond[0]+1,block2Size,currentOne); 
				  break;
			  case 2:
				  currentOne = new LinkedList<Integer>(cur);//reset currentOne
				  swap(bond[0]+1,bond[1]-1,block2Size,currentOne);
				  reverse(bond[0]+1+block2Size,block1Size,currentOne); 
				  break;
			  case 3:
				  currentOne = new LinkedList<Integer>(cur);//reset currentOne
				  reverse(bond[0]+1,block1Size,currentOne);
				  reverse(bond[0]+1+block1Size,block2Size,currentOne); 
				  break;
			}
			double tempRes = computeTotalCost(currentOne, false);
			if(tempRes<bestVal){
				bestVal = tempRes;
				bestOfFour = new LinkedList<Integer>(currentOne);
			}
		}
		return bestOfFour;
	}
	/**
	 * Reverse given list part
	 * @param start start place of the list that will be reversed
	 * @param size size of the block that will be reversed
	 * @param lst list that contains this block
	 */
	private void reverse(int start,int size,LinkedList<Integer> lst){
		int big = start+size;
		for(int i=0;i<size/2;i++){
			lst.add(big-i-2,lst.remove(start+i));
			lst.add(start+i,lst.remove(big-i-1));
		}
	}
	/**
	 * Swaps place of two blocks within list
	 * @param start start of first block
	 * @param end end of second block
	 * @param size size of second block
	 * @param lst list contains this blocks
	 */
	private void swap(int start,int end,int size,LinkedList<Integer> lst){
		for(int i=0;i<size;i++){
			lst.add(start,lst.remove(end));
		}
	}
	/**
	 * Generates one big and one small number that determines size of block that will change
	 * @param aperture Determines minimum block size between this two number,different for 2opt and 3opt
	 * @return int[] array that contains big number in indice 1 and small number in indice 0
	 */
	private int[] generateBigSmall(int aperture){
		int[] values = new int[2];
		int n1=r.nextInt(n);
		int n2=r.nextInt(n);
		while(Math.abs(n2-n1)<aperture || Math.abs(n2-n1)==n){
			n2=r.nextInt(n);
			n1=r.nextInt(n);
		}
		values[0] = Math.min(n1, n2);
		values[1] = Math.max(n1, n2);
		return values;
	}
}

