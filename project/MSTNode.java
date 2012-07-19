import java.util.Iterator;
import java.util.LinkedList;
/**
 * Implements MSTNodes that needed in MST solution
 * @author Ahmet Çelik 2009400111
 *
 */
public class MSTNode {
	/**
	 * City ID
	 */
	private int ID;
	/**
	 * Children of this city, keeps in adding order
	 */
	private LinkedList<MSTNode> child;
	/**
	 * Constructs a MSTNode without any child
	 * @param id
	 */
	protected  MSTNode(int id){
		ID=id;
		child=new LinkedList<MSTNode>();
	}
	/**
	 * 
	 * @return City ID of the MSTNode
	 */
	protected int getID(){
		return ID;
	}
	/**
	 * Adds a child to this MSTNode
	 * @param c Child to be added
	 */
	protected void addChild(MSTNode c){
		child.add(c);
	}
	/**
	 * 
	 * @return an Iterator<MSTNode> over the childs of this MSTNode
	 */
	protected  Iterator<MSTNode> childItr(){
		return child.iterator();
	}
	/**
	 * Checks if this MSTNode has child
	 * @return true if MSTNode has a child
	 */
	protected  boolean hasChild(){
		return !child.isEmpty();
	}
}



