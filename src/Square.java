import java.awt.Point;

public class Square {
	//create values for the biggest square 
	//we can possibly hold
	static final Point  minVal = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE); // -2^31
	static final Point  maxVal = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE); // 2^31
	static final Square maxSquare = new Square(minVal, maxVal);

	Point max; //point highest up on that square
	Point min; //point at the bottom of square
	/**
	* default contructor, 
	* needs two points to create a square
	* @param p1
	* @param p2
	*/
	public Square(Point p1, Point p2){
		this.min = p1;
		this.max = p2;
	}
	/**
	 * root nodes square
	 * @param s
	 */
	public Square(Square s){
		this(s.min, s.max);
	}
}
