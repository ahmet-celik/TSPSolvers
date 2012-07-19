import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generates and try all possible paths for given number of cities starting from first city.
 * @author Ahmet Çelik 2009400111
 *
 */
public class ExactEnum  extends TSPSolvers{
	/**
	 * The path that gives minimum total length until now 
	 */
	private int[] best;
	/**
	 * Current path that is in the progress.
	 */
	private int[] now;
	/**
	 * Number of cities
	 */
	private int n;
	/**
	 * Minimum total length until now
	 */
	private  double min= Double.MAX_VALUE;

	/**
	 * Constructor for ExactEnum
	 * @param cities
	 * @param distances
	 */
	public ExactEnum(int cities){
		n=cities;
		now = new int[cities+1];
		best= new int[cities+1];
	}
	/**
	 * Tries all possible (n-1)! paths
	 * @param ar Orginal given path
	 * @param k level of permutation
	 */
	 private void perm(int[] ar,int k ) {
		if(k==n){ // base case compares result of permutation
		   double cost=computeTotalCost(ar);//computeTotalCost is O(n) then O(n)*O((n-1)!)=O(n!)
		   if(cost<min){
			   min=cost;
			   System.arraycopy(ar, 1,best, 1, n-1);
		   }
		}else{
			for (int i = k; i < n; i++) {
				int temp=ar[k];  //makes choice
				ar[k]=ar[i];
				ar[i]=temp;
				perm(ar,k+1);  //explores choice
				temp=ar[k];    //makes un-choice
				ar[k]=ar[i];
				ar[i]=temp;
			}
		}
	}
	 
	/**
	 * Computes total length of given path
	 * @param a The Path
	 * @return Total length of this path
	 */
	private double computeTotalCost(int[] a){
		double cost=0;
		for(int i=0;i<a.length-1;i++){
			cost+=Main.distances[a[i]][a[i+1]];
		}
		return cost;
	}
	
	/**
	 * Implements required method to be a TSPSolver. Writes results the file. 
	 * It has different fileWrites since it uses array rather than LinkedList.
	 * @throws IOException Could not create file
	 */
	public  void find() throws IOException{ 
		for(int i=1;i<n;i++){
			now[i]=i;
		}
	    perm(now,1);	    	   
	    BufferedWriter wr = new BufferedWriter(new FileWriter("opt_tsp_"+n+".txt",true));
	    wr.write(""+computeTotalCost(best)+"\n");
	    wr.write(""+(best[0]+1));
		for(int m=1;m<n+1;m++){
			wr.write("-"+(best[m]+1));
		}  
		wr.write("\n");
		wr.close();
	}
}

