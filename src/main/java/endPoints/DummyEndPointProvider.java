package endPoints;

public class DummyEndPointProvider implements EndPointProviderIF {

	public DummyEndPointProvider() {
	}

	@Override
	public EndPoint[] getEndPoints(EndPoint dest) {
		EndPoint start = new EndPoint(dest.getX() - 10, dest.getY() - 10, dest.getZ() - 10);
		return new EndPoint[] { start, dest };
	}
}
