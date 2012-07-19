import java.io.IOException;
import java.util.LinkedList;
/**
 *  Bonus TSPSolver. Implement idea is cheap insertion to initial the largest triangle.
 *  O(n^3) complexity for finding initial largest triangle and cheap insertions.
 * @author Ahmet Çelik 2009400111
 *
 */
public class Triangle extends TSPSolvers{
	/**
	 * Number of cities
	 */
    private int n;
    /**
     * Path of latest route
     */
    private LinkedList<Integer> path = new LinkedList<Integer>();
    /**
     * Cities not connected to the path yet
     */
    private LinkedList<Integer> notC= new LinkedList<Integer>();
    /**
     * Constructs Triangle method to solve 
     * @param N
     * @exception IllegalArgumentException
     */
    public Triangle(int N){
    	if(N<3) throw new IllegalArgumentException();
    	n=N;   	
    	int n1=-1;
    	int n2=-1;
    	double MAX=-1;
    	int m1=-1;
    	for(int m=0;m<n-2;m++){// tries all possible triangles
	    	for(int i=0;i<n-1;i++){
	    		if(m!=i){//to not construct invalid edge
		    	    for(int j=i+1;j<n;j++){
		    	    	if(m!=j){//to not construct invalid edge
			    	    	double cMX=Main.distances[m][i]+Main.distances[m][j]+Main.distances[i][j];
			    	    	if(cMX>MAX){
			    	    		n1=i;
			    	    		n2=j;
			    	    		MAX=cMX;
			    	    		m1=m;
			    	    	}
		    	    	}
		    	    }
	    		}
	    	}
    	}	
    	for(int i=0;i<n;i++){
    		notC.add(i);
    	}
    	notC.remove(new Integer(m1));
    	path.add(m1); path.add(n1); path.add(n2); path.add(m1); 
    	notC.remove(new Integer(n1)); notC.remove(new Integer(n2));
   }
    /**
     * Makes cheap insertions to path
     */
    private void insert(){
    	int now=-1; //the city that will be inserted
    	int sucIn=-1;//insertion index
    	double min=Double.MAX_VALUE;
    	for(int nc:notC){   		
    		for(int i=0;i<path.size()-1;i++){
    			int n1=path.get(i); 
    			int n2 = path.get(i+1);
    			double res=(Main.distances[n1][nc]+Main.distances[n2][nc])-Main.distances[n1][n2];
    			if(res<min){
    				now=nc; sucIn=(i+1); min=res;
    			}
    		}
    	}
    	path.add(sucIn,now);
    	notC.remove(new Integer(now));
    }
    /**
     * Implements required method to be a TSPSolvers
     */
    public void find() throws IOException{
    	while(!notC.isEmpty()){
    		insert();
    	}
    	path.remove(path.size()-1); // removes last, needed to rotate circular path such first city is in front
    	while(path.get(0)!=0){ //rotates
    		path.add(path.remove(0));
    	}
    	path.add(0); //completes circular path adding first city to end
    	TSPSolvers.printPretty(path, "triangle", n);
    }
     
}
