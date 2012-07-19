import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Abstract TSPSolvers class. Defines how a solver should be, provides some static methods.
 * @author Ahmet Çelik 2009400111
 *
 */
public abstract class TSPSolvers {
	/**
	 * Run a TSPSolver method and find results 
	 * @throws IOException 
	 */
	public abstract void find() throws IOException;
	/**
	 * Computes total length of the given path	
	 * @param lst The Path
	 * @param add If it is true, add first city to the front and behind of the path,otherwise does not.
	 * @return Total Length of the Path
	 */
	protected static double computeTotalCost(LinkedList<Integer> lst,boolean add){
		if(add){
			lst.addLast(new Integer(0)); 
			lst.addFirst(new Integer(0));
		}
		double cost = 0;
		for(int i=0;i<lst.size()-1;i++){
			cost+=Main.distances[lst.get(i)][lst.get(i+1)];
		}
		if(add){
			lst.removeLast();
			lst.removeFirst();
		}
		return cost;
	}
	/**
	 * Prints results pretty to given file
	 * @param myList The path
	 * @throws IOException 
	 */
	protected static void printPretty(LinkedList<Integer> myList,String type,int n) throws IOException{
		BufferedWriter wr = new BufferedWriter(new FileWriter(type+"_tsp_"+n+".txt",true));
		wr.write(""+computeTotalCost(myList, false)+"\n");
		wr.write(""+(myList.removeFirst()+1));
		for(Integer i:myList) 
			wr.write("-"+(i+1));
		wr.write("\n");
		wr.close();
		myList.addFirst(new Integer(0));
	}
}
