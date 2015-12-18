package pathFinder;

import endPoints.EndPoint;
import maps.MapIF;

public class PathPlannerContext {
  MapIF map;
  EndPoint [] endPoints;
  PathPlannerStrategy planner;
  
  public PathPlannerContext (EndPoint dest) {
	  this.map = MapProviderInjector.geMapProvider(dest).getMap();
	  this.endPoints = EndPointProviderInjector.getEndPointProvider().getEndPoints(dest);
	  this.planner = new DummyPathFinder();
  }
  
  public Path findPath(){
	  return planner.findPath(this.map, this.endPoints);
  }
  
}

