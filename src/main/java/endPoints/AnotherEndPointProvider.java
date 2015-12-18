package endPoints;

public class AnotherEndPointProvider implements EndPointProviderIF {
	
	public AnotherEndPointProvider() {
	}
	
	@Override
	public EndPoint[] getEndPoints(EndPoint dest) {
		EndPoint start = new EndPoint(dest.getX() - 20, dest.getY() - 20, dest.getZ() - 20);
		return new EndPoint[] { start, dest };
	}
}
