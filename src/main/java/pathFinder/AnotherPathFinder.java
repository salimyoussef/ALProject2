package pathFinder;

import endPoints.EndPoint;
import maps.MapIF;

public class AnotherPathFinder implements PathPlannerStrategy {
 
  public AnotherPathFinder () { }
  @Override
  public Path findPath(MapIF map, EndPoint[] endPoints) {
  	Path p = new Path();
  	for (int i =0 ; i < 10 ; i++){
  		p.add(new EndPoint(1,i,0));
  	}
  	return p;
  }
}

