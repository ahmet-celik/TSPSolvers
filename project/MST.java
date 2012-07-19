import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * Creates MST over first given number cities, and path is made by a DFT over this tree.
 * @author Ahmet Çelik 2009400111
 *
 */
public class MST extends TSPSolvers{
	/**
	 * Root of the tree
	 */
	private MSTNode treeRoot;
	/**
	 * Holds cities that are now in the tree
	 */
	private LinkedList<MSTNode> connectedSet;
	/**
	 * Holds cities that are not now in the tree
	 */
	private LinkedList<MSTNode> notConnectedSet;
	/**
	 * The path that results from DFT over the MST tree
	 */
	private LinkedList<Integer> path;
	/**
	 * Construct MST method
	 * @param n 
	 * @param distances
	 */
	public MST(int n){
		treeRoot = new MSTNode(0);
		path=new LinkedList<Integer>();
		connectedSet = new LinkedList<MSTNode>();
		connectedSet.add(treeRoot);
		notConnectedSet = new LinkedList<MSTNode>();
		for(int i=1;i<n;i++){
			notConnectedSet.add(new MSTNode(i));
		}
	}
	/**
	 * Implements required method to be a TSPSolver
	 * @throws IOException 
	 */
	public void  find() throws IOException{
		makeTree();
		DFT(treeRoot);
		path.addLast(new Integer(0));
		TSPSolvers.printPretty(path,"mst",path.size()-1);
	}
	/**
	 * Makes MST tree with a Prim Algorithm
	 */
	private void makeTree(){
		while(!notConnectedSet.isEmpty()){
			double minDist=Double.MAX_VALUE;
			MSTNode closestOne=null;
			MSTNode toClose=null;
			for(MSTNode con:connectedSet){
				for(MSTNode notCon:notConnectedSet){
					int conn = con.getID();
					int notcon = notCon.getID();
					if(Main.distances[conn][notcon]<minDist){
						minDist=Main.distances[conn][notcon];
						closestOne=notCon;
						toClose=con;
					}
				}				
			}
			notConnectedSet.remove(closestOne);
			connectedSet.addLast(closestOne);
			toClose.addChild(closestOne);
		}
	}
	/**
	 * Travels MST tree with Depth First Search method
	 * @param now Current MSTNode
	 */
	private void DFT(MSTNode now){
		if(now==null) return;
		path.add(now.getID());
		if(now.hasChild()){		
			Iterator<MSTNode> itr = now.childItr();
			while(itr.hasNext()){
				DFT(itr.next());
			}
		}
	}
}

