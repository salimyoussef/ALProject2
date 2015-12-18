package maps;

import endPoints.*;
public class MapProvider {
  EndPointProviderIF pProvider;
  private EndPoint destPoint;
  
  public MapProvider (EndPoint destPoit) { 
	 // this.pProvider = EndPointProviderInjector.getEndPointProvider();
	  this.pProvider = new DummyEndPointProvider();
	  this.destPoint = destPoit;
  }
  
  public MapIF getMap(){
	  
	  MapIF map = findGoodMap(pProvider.getEndPoints(this.destPoint));
	  return  map;
  }
  
  private MapIF findGoodMap(EndPoint [] epoints){
	  // search for a good map for this coordinates
	  //dummy implementation
	  return new DefaultMapImpl();
  }
}

