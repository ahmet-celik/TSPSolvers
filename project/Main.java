import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class to run TSPSolvers
 * You can use an argument to specify number of cities
 * @author Ahmet Çelik 2009400111
 *
 */
public class Main {
	/**
	 * Distance array for cities
	 */
	protected static double[][] distances = new double[101][101];
	/**
	 * Main method
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner con = new Scanner(new File("input.txt"));
		int[] cityX = new int[101];
		int[] cityY = new int[101];
		while(con.hasNext()){
			int id = con.nextInt();
			cityX[id-1] = con.nextInt();
			cityY[id-1] = con.nextInt();
		}
       for(int i=0;i<101;i++){
    	   for(int j=0;j<101;j++){
    		   if(i!=j){
    			   distances[i][j]=calculateDistance(cityX[i], cityY[i], cityX[j], cityY[j]);
    		   }//else when i==j distance is zero, 
    		    //and in double array it is already zero when it is contructed
    		    //so,no need to compute for it for this case
    	   }
       }
       if(args.length==0){
	       for(int i=6;i<15;i+=2){
	    	   testAll(i);
	       }
	       testAll(40);	 
	       testAll(60);	 
	       testAll(80);	 
	       testAll(101);	
       }else
    	   testAll(Integer.parseInt(args[0]));
	}
	/**
	 * Calculates distance between given two cities
	 * @param x1 x for first city
	 * @param y1 y for first city
	 * @param x2 x for second city
	 * @param y2 y for second city
	 * @return distance between them
	 */
	private static double calculateDistance(int x1,int y1,int x2,int y2){
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	
	/**
	 * Test methods for TSPSolvers 
	 * @param cit Number of cities 
	 * @throws IOException
	 */
	private static void testAll(int cit) throws IOException{
		TSPSolvers[] t = new TSPSolvers[6];
		if(cit<15){
			t[0]= new ExactEnum(cit); 
			t[0].find();
		}                                
		t[1]= new Greedy(cit); 			 t[1].find();
		t[2]= new MST(cit);   			 t[2].find();
		t[3]= new OptSolvers(cit,true);	 t[3].find();
		t[4]= new OptSolvers(cit,false); t[4].find();
		t[5]= new Triangle(cit);		 t[5].find();			
	}
}
