package pathFinder;

import java.util.ArrayList;
import java.util.Iterator;

import endPoints.*;

public class Path {

	ArrayList<EndPoint> rout;

	public Path() {
		this.rout = new ArrayList<EndPoint>();
	}

	public void add(EndPoint p) {
		this.rout.add(p);
	}

	public void remove(EndPoint p) {
		this.rout.remove(p);
	}

	public Iterator<EndPoint> iterator() {
		return this.rout.iterator();
	}

}
