package maps;

public class DefaultMapImpl implements MapIF {
	private int[][] map;

	public DefaultMapImpl() {
		this.map = new int[10][10];
		// load map from file
	}

	public DefaultMapImpl(int n, int m) {
		this.map = new int[n][m];
		// load map from file
	}

	@Override
	public boolean isPassable(int x, int y, int z) {
		// TODO Auto-generated method stub
		return (this.map[x][y] == 0);
	}
}
