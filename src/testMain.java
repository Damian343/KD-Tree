import java.awt.Point;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import org.junit.Before;

public class testMain {
	Tree tree;
	/**
	 * creates kd tree structure
	 * @param args
	 */
	public static void main(String[] args) {
		Tree tree = new Tree();
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add( new Point(2,3));
		pointList.add( new Point(5,4));
		pointList.add( new Point(9,6));
		pointList.add( new Point(4,7));
		pointList.add( new Point(8,1));
		pointList.add( new Point(7,2));
		tree.createTree(pointList);

	}
	/**
	 * checks to see if tree can be traversed in preorder fashion
	 */
	@org.junit.Test
	public void test1() {
		System.out.println("preorder test: ");
		ArrayList<Point> pointTest = new ArrayList<Point>();
		pointTest.add( new Point(7,2));
		pointTest.add( new Point(5,4));
		pointTest.add( new Point(2,3));
		pointTest.add( new Point(4,7));
		pointTest.add( new Point(9,6));
		pointTest.add( new Point(8,1));
		assertEquals(tree.preorder(tree.root), pointTest);
	}
	/**
	 * checks to see if the minimal value function works
	 */
	@org.junit.Test
	public void test2() {
		System.out.println("minVal test: ");
		Node test = new Node(new Point(2,4), false);
		assertEquals(tree.minVal(), test);
	}
	/**
	 * checks to see if the get neighbors function works
	 */
	@org.junit.Test
	public void test3() {
		System.out.println("get neighbors test: ");
		ArrayList<Point> pointTest = new ArrayList<Point>();
		pointTest.add(new Point(2,3));
		pointTest.add(new Point(4,7));
		assertEquals(tree.getNeighbors(new Point(5,4)), pointTest);
	}
	

}
