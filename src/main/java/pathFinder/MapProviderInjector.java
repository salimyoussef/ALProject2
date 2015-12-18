package pathFinder;

import endPoints.EndPoint;
import maps.MapProvider;

public class MapProviderInjector {
  //EndPoint dest;
  public MapProviderInjector (/*EndPoint dest*/) {
	  //this.dest=dest;
  }
  public static MapProvider geMapProvider(EndPoint dest){
	  return new MapProvider(dest);
  }
}

