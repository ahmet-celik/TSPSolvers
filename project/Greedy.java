import java.io.IOException;
import java.util.LinkedList;
/**
 * Implements Greedy Method for TSP Problem
 * @author Ahmet Çelik 2009400111
 *
 */
public class Greedy extends TSPSolvers {
	/**
	 * Number of cities
	 */
	private int n;
	/**
	 * Latest list that holds path of traveller
	 */
	private LinkedList<Integer> list;
	/**
	 * Unvisited cities for now 
	 */
	private LinkedList<Integer> notConnected;
	/**
	 * Constructs Greedy Method
	 * @param n Number of cities
	 * @param distances Distances array
	 */
	public Greedy(int n){
		this.n=n;
		list = new LinkedList<Integer>();
		list.add(new Integer(0));
		notConnected = new LinkedList<Integer>();
		for(int i=1;i<n;i++){
			notConnected.add(new Integer(i));
		}
	}
	/**
	 * Implements required method from TSPSolvers.
	 * @throws IOException 
	 */
	public void find() throws IOException{
		while(!notConnected.isEmpty()){
			Integer cand = closest(list.getLast());
			notConnected.remove(cand);
			list.addLast(cand);
		}
		list.addLast(new Integer(0));	   
		TSPSolvers.printPretty(list,"gm",n);

	}
	/**
	 * Finds closest city to the last added city into the path.
	 * @param n1 City that is investigated
	 * @return closest city to it from notConnected list
	 */
	private int closest(int n1){
		int min=-1;
		double minValue = Double.MAX_VALUE;
		for(int n:notConnected){
			if(Main.distances[n1][n]<minValue){//Main is a valid main class that has distances array
				minValue=Main.distances[n1][n];
				min=n;
			}
		}
		return min;
	}
}

