package drone;

import java.io.Serializable;

import endPoints.EndPoint;

public interface Moveable extends Serializable{
	// to do 
	// and  other methods
	public void goAhead(EndPoint point);
}
