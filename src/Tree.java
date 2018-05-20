import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Tree {
	Node root;
	ArrayList<Node> preorder = new ArrayList<Node>();
	/**
	 * Constuctor of the tree, declares root as null
	 */
	public Tree() {
		root = null;
	}
	/**
	 * creates the tree structure depending on the pointList passed
	 * @param pointList
	 */
	public void createTree(ArrayList<Point> pointList) {
		Collections.sort(pointList, new Comparator<Point>() {
			public int compare(Point a, Point b){
				if (a.x < b.x) {
					return -1;
				}
				else if (a.x > b.x) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		int middleIdx = Math.round(pointList.size()/2);
		
		createRoot(pointList, middleIdx);
		createLeftSubtree(pointList, middleIdx);
		createRightSubtree(pointList, middleIdx);
	}
	/**
	 * creates left subtree based on median of pointlist
	 * @param pointList
	 * @param middleIdx
	 */
	public void createLeftSubtree(ArrayList<Point> pointList, int middleIdx) {
		ArrayList<Point> leftSubtree = new ArrayList<Point>();
		int median = Math.round(leftSubtree.size()/2);
		if() {
			for (int i = median; i >= 0; i--) {
				leftSubtree.add(pointList.get(i));
				root.insert(pointList.get(i));
			}
		}
	}
	/**
	 * creates the right subtree depending on median of pointList
	 * @param pointList
	 * @param middleIdx
	 */
	public void createRightSubtree(ArrayList<Point> pointList, int middleIdx) {
		ArrayList<Point> rightSubTree = new ArrayList<Point>();
		for(int i = pointList.size()-1; i > middleIdx; i--){
			Point currPoi = new Point(pointList.get(i));
			rightSubTree.add(currPoi);
			root.insert(currPoi);
		}
	}
	/**
	 * declares root node depending on median of pointlist
	 * @param pointList
	 * @param middleIdx
	 */
	public void createRoot(ArrayList<Point> pointList, int middleIdx) {
		Point median = pointList.get(middleIdx);
		root = new Node(median, Node.VERTICAL);
	}
	
	public Node find(Point p){
		return find(root, p);
	}
	/**
	 * finds node of point P
	 * @param node
	 * @param p
	 */
	public Node find(Node node, Point p){
		//we continue to recurse until either the node is 
		//null in which we hit bottom or the distance
		//between the current nodes point and the
		//wanted point is less then 5 pixals we just
		//return that current node
		if(node == null) { 
			System.err.println("not found");
			return null;
		}
		if(node.POINT.equals(p)) {
			return node; 
		}
		//if passes the base cases, we recurse either
		//on whether or not the wanted point is below
		//or not
		if(node.isBelow(p)){ return find(node.below, p); } 
		else 			   { return find(node.above, p); }
	}
	/**
	 * searches through kd tree using preorder traversal
	 * @param n
	 */
	public ArrayList preorder(Node n){
		if(n == null) return preorder;
		preorder.add(n);
		preorder(n.below);
		preorder(n.above);
		return preorder;
	}
	
	/**
	 * finds the smallest point of kd tree
	 */
	public Node minVal(){
		Node min = null;
		Node n = root;
		while(n.below != null) {
			n = n.below;
			min = n;
		}
		return min;
	}

	 public ArrayList rangeSearch(Point p, Point p2) {

		 ArrayList<Node> range = new ArrayList<Node>();
		 Node n = find(p);
		 Node n2 = find(p2);
		 boolean done = false;
		 Node prevNod = n;
		 Node n1 = n;

		 range.add(n);
		 n = n.below;
		 while(n != null){
			 System.out.println(n);
			if(n == n2){
				n = null;
			}
			prevNod = n;
			n = n.above;
			n1 = n.below;
			range.add(n);
			if(n.below == null && n.above == null){
				n = prevNod;
			}
		 }
		 System.out.println(range);
		 return range;
	 }
	/**
	 * find two closest nodes to point P
	 * @param P
	 */
	public ArrayList getNeighbors(Point p){
		ArrayList<Node> neighbors = new ArrayList<Node>();
		Node n = find(p);
		System.out.println(n);
		if(n == null){
			System.out.println("unable to get neighbors on null node");
		} else {
			neighbors.add(n.below);
			neighbors.add(n.above);
		}
		return neighbors;
	}
}
