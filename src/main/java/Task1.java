import com.sun.media.sound.InvalidDataException;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
	public static final int LAND = 1;
	public static final int MARKED_ISLAND = 2;
	private static final Logger LOGGER = LoggerFactory.getLogger(Task1.class);

	public static void main(String[] args) {

		int[][] map = {
				{1, 0, 1, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 0, 0, 0},
				{1, 1, 1, 0, 0, 0, 1, 0, 0},
				{1, 1, 0, 0, 0, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 1, 1, 0, 0},
				{0, 0, 1, 0, 0, 0, 0, 0, 0},
				{1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 1, 0, 0}
		};

		Task1 task1 = new Task1();
		try {
			task1.countIslands(map);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	private void countIslands(int[][] map) throws InvalidDataException {
		int countIslands = 0;
		int mapY = map.length - 1;
		int mapX = map[0].length - 1;

		if (mapX != 0 && mapY != 0) {
			for (int x = 0; x <= mapX; x++) {
				for (int y = 0; y <= mapY; y++) {
					if (map[y][x] == LAND) {
						List<IslandPoint> land = new ArrayList<>();
						land.add(new IslandPoint(x, y));
						markWholeIsland(map, land);
						countIslands++;
					}
				}
			}
		}
		System.out.println("Number of islands = " + countIslands);
	}

	public void markWholeIsland(int[][] map, List<IslandPoint> list) throws InvalidDataException {
		if (map.length == 0 || map[0].length == 0) {
			return;
		}
		while (!list.isEmpty()) {
			IslandPoint islandPoint = list.remove(0);
			if (islandPoint.x < 0 || islandPoint.x > map[0].length - 1) {
				throw new InvalidDataException(String.format("Invalid x = %d coordinate for island point", islandPoint.x));
			}
			if (islandPoint.y < 0 || islandPoint.y > map.length - 1) {
				throw new InvalidDataException(String.format("Invalid y = %d coordinate for island point", islandPoint.y));
			}
			map[islandPoint.y][islandPoint.x] = MARKED_ISLAND;
			markLandNeighbour(map, list, islandPoint);
		}
	}

	public void markLandNeighbour(int[][] map, List<IslandPoint> list, IslandPoint element) throws InvalidDataException {
		if (map.length == 0 || map[0].length == 0) {
			return;
		}
		int loopX = Math.min(map[0].length - 1, element.x + 1);
		int loopY = Math.min(map.length - 1, element.y + 1);
		if (loopX < 0 || loopX > map[0].length - 1) {
			throw new InvalidDataException(String.format("Invalid x = %d coordinate for island point", element.x));
		}
		if (loopY < 0 || loopY > map.length - 1) {
			throw new InvalidDataException(String.format("Invalid y = %d coordinate for island point", element.y));
		}
		for (int x = Math.max(0, element.x - 1); x <= loopX; x++) {
			for (int y = Math.max(0, element.y - 1); y <= loopY; y++) {
				if (map[y][x] == LAND) {
					list.add(new IslandPoint(x, y));
				}
			}
		}
	}
}
