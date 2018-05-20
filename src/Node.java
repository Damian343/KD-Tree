import java.awt.Point;

public class Node {
	public static final boolean HORIZONTAL = false;
	public static final boolean VERTICAL   = true;
	final Point   POINT;
	final boolean DIR;
	// each node belongs to the maximum size square
	Square square;
	// each node keeps track if theres one above
	Node	above; 
	// as well as below if there if one
	Node	below;
	/**
	*used for when spawning a child node to this node, and square
	*@param p
	*@param dir
	*@param sq
	*/
	public Node(Point p, boolean dir, Square sq){
		this.POINT = new Point (p);
		this.DIR   = dir;
		this.square= new Square(sq);
	}
	/**
	*root node declares a max square,
	*the whole possible array of points
	*@param p
	*@param dir
	*/
	public Node(Point p, boolean dir){
		this(p, dir, Square.maxSquare);
	}
	
	public String toString(){
		return "(" + POINT.x + " , " + POINT.y + ")";
	}
	/**
	*helper class to keep kd tree recursive, 
	*checks if this node is below the passed node
	*@param p
	*/
	public boolean isBelow(Point p){
		if(DIR == VERTICAL){ return p.x < POINT.x; } 
		else 				{ return p.y < POINT.y; }
	}
	/**
	*helper class to check if this 
	*node is above the passed in node
	*@param p
	*/
	public boolean isAbove(Point p){
		if(DIR == VERTICAL){ return p.x >= POINT.x; } 
		else		 { return p.y >= POINT.y; }
	}

	/**
	 * recursive function allowing us to find where to place node
	 * @param p
	 */
	public void insert(Point p){
		//makes sure cant be same point as this one
		if(p.equals(POINT)) { 
			System.err.println("unable to insert point already exists");	
			return; 
		}
		//if this point is below, and there is 
		//not a node below we spawn node below this one
		//else we recurse and keep checking 
		//till a node doesnt have a null below node
		if(isBelow(p)){
			if(below == null) { below = spawnChild(p, true); } 
			else 			  { below.insert(p); }
		//same logic as above but checking for above nodes
		} else {
			if(above == null){ above = spawnChild(p, false); } 
			else		     { above.insert(p); }
		}
	}
	/**
	 * if node is a child of a parent node
	 * @param p
	 * @param below
	 */
	public Node spawnChild(Point p, boolean below){
		Square s = new Square(square);
		 //if direction of this node vertical
		 // and the new node point is below we set the max x of square to the point x
		if(DIR == VERTICAL){ 
			if(below){ s.max.x = p.x; } 
			else     { s.min.x = p.x; }
		} else {
			if(below){ s.max.y = p.y; } 
			else 	 { s.min.y = p.y; }
		}
		//creates new node, with opposite direction of this node
		return new Node(p, !DIR, s);
	}
}
