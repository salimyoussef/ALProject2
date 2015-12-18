package pathFinder;

import endPoints.DummyEndPointProvider;
import endPoints.EndPointProviderIF;

public class EndPointProviderInjector {
  
  public EndPointProviderInjector () { }
  public static EndPointProviderIF getEndPointProvider(){
	  // load from config file
	  return new DummyEndPointProvider();
  }
}

