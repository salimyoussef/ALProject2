package pathFinder;

import endPoints.EndPoint;
import maps.MapIF;

public interface PathPlannerStrategy {
	public Path findPath(MapIF map , EndPoint [] endPoints);
}

