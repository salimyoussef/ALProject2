package maps;

public class AnotherMapImple implements MapIF {
	private int[][] map;

	public AnotherMapImple() {
		this.map = new int[15][15];
		// load map from file
	}

	public AnotherMapImple(int n, int m) {
		this.map = new int[n][m];
		// load map from file
	}

	@Override
	public boolean isPassable(int x, int y, int z) {
		// TODO Auto-generated method stub
		return (this.map[x][y] == 0);
	}
}
