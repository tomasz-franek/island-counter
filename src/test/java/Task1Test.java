import com.sun.media.sound.InvalidDataException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

public class Task1Test {

	private Task1 task1;
	private ArrayList<IslandPoint> points;
	@Before

	public void initializeTask1(){
		task1 = new Task1();
		points = new ArrayList<>();
	}

	@Test
	public void markWholeIsland_onlyWater_nothingMarked() throws InvalidDataException {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{0,0,0}
		};

		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{0,0,0},
				{0,0,0},
				{0,0,0}
		}));

	}

	@Test
	public void markWholeIsland_upperLeftCornerMarked_islandMarked() throws InvalidDataException {
		int[][] map = {
				{1,0,0},
				{0,1,0},
				{0,0,0}
		};

		points.add(new IslandPoint(0,0));
		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{2,0,0},
				{0,2,0},
				{0,0,0}
		}));

	}

	@Test
	public void markWholeIsland_upperRightCornerMarked_islandMarked() throws InvalidDataException {
		int[][] map = {
				{0,1,1},
				{0,0,0},
				{0,0,0}
		};

		points.add(new IslandPoint(1,0));
		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{0,2,2},
				{0,0,0},
				{0,0,0}
		}));

	}

	@Test
	public void markWholeIsland_lowerRightCornerMarked_islandMarked() throws InvalidDataException {
		int[][] map = {
				{0,0,1},
				{0,1,0},
				{0,0,1}
		};

		points.add(new IslandPoint(2,2));
		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{0,0,2},
				{0,2,0},
				{0,0,2}
		}));

	}

	@Test
	public void markWholeIsland_lowerLeftCornerMarked_islandMarked() throws InvalidDataException {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{1,0,0}
		};

		points.add(new IslandPoint(0,2));
		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{0,0,0},
				{0,0,0},
				{2,0,0}
		}));

	}

	@Test
	public void markWholeIsland_islandPointsListIsEmpty_nothingMarkedAsIsland() throws InvalidDataException {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{1,0,0}
		};
		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{0,0,0},
				{0,0,0},
				{1,0,0}
		}));

	}

	@Test
	public void markWholeIsland_oneRow_islandMarkedCorrectly() throws InvalidDataException {
		int[][] map = {
				{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};
		points.add(new IslandPoint(1,0));
		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		}));

	}

	@Test
	public void markWholeIsland_oneColumn_islandMarkedCorrectly() throws InvalidDataException {
		int[][] map = {
				{0},
				{0},
				{0},
				{1},
				{1},
				{1},
				{1},
		};
		points.add(new IslandPoint(0,6));
		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{0},
				{0},
				{0},
				{2},
				{2},
				{2},
				{2},
		}));

	}

	@Test
	public void markWholeIsland_array1to1_islandMarked() throws InvalidDataException {
		int[][] map = {
				{1}
		};

		points.add(new IslandPoint(0,0));
		task1.markWholeIsland(map,points);
		assertThat(map, is(new int[][]{
				{2}
		}));

	}

	@Test
	public void markWholeIsland_islandPointXCoordinateOutOfTheMap_exceptionIsGenerated() {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{1,0,0}
		};

		points.add(new IslandPoint(-5,0));
		Exception exception = assertThrows(InvalidDataException.class, () -> task1.markWholeIsland(map,points));

		assertThat(exception.getMessage(),is("Invalid x = -5 coordinate for island point"));
	}

	@Test
	public void markWholeIsland_islandPointYCoordinateOutOfTheMap_exceptionIsGenerated() {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{1,0,0}
		};

		points.add(new IslandPoint(0,8));
		Exception exception = assertThrows(InvalidDataException.class, () -> {
			task1.markWholeIsland(map,points);
		});

		assertThat(exception.getMessage(),is("Invalid y = 8 coordinate for island point"));
	}

	@Test
	public void markLandNeighbour_emptyMap_nothingMarked() throws InvalidDataException{
		int [][] map = new int[0][0];
		IslandPoint islandPoint= new IslandPoint(12,12);
		task1.markLandNeighbour(map,points,islandPoint);
		assertThat(map,is(new int[0][0]));
	}

	@Test
	public void markLandNeighbour_upperLeftSelected_neighboursAddedToPoints() throws InvalidDataException{
		int[][] map = {
				{1,1,0},
				{1,1,0},
				{0,0,0}
		};
		IslandPoint islandPoint= new IslandPoint(0,0);
		task1.markLandNeighbour(map,points,islandPoint);
		ArrayList<IslandPoint> referencedList = new ArrayList<>();
		referencedList.add(new IslandPoint(0,0));
		referencedList.add(new IslandPoint(0,1));
		referencedList.add(new IslandPoint(1,0));
		referencedList.add(new IslandPoint(1,1));
		assertTrue(points.containsAll(referencedList));
	}

	@Test
	public void markLandNeighbour_upperRightCornerSelected_neighboursAddedToPoints() throws InvalidDataException{
		int[][] map = {
				{0,0,1},
				{0,0,1},
				{0,0,0},
		};
		IslandPoint islandPoint= new IslandPoint(2,0);
		task1.markLandNeighbour(map,points,islandPoint);
		ArrayList<IslandPoint> referencedList = new ArrayList<>();
		referencedList.add(new IslandPoint(2,0));
		referencedList.add(new IslandPoint(2,1));
		assertTrue(points.containsAll(referencedList));
	}

	@Test
	public void markLandNeighbour_lowerLeftCornerSelected_neighboursAddedToPoints() throws InvalidDataException{
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{0,0,0},
				{0,1,0},
				{1,1,0},
		};
		IslandPoint islandPoint= new IslandPoint(0,4);
		task1.markLandNeighbour(map,points,islandPoint);
		ArrayList<IslandPoint> referencedList = new ArrayList<>();
		referencedList.add(new IslandPoint(1,3));
		referencedList.add(new IslandPoint(1,4));
		referencedList.add(new IslandPoint(0,4));
		assertTrue(points.containsAll(referencedList));
	}

	@Test
	public void markLandNeighbour_lowerRightCornerSelected_neighboursAddedToPoints() throws InvalidDataException{
		int[][] map = {
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,1},
		};
		IslandPoint islandPoint= new IslandPoint(4,2);
		task1.markLandNeighbour(map,points,islandPoint);
		ArrayList<IslandPoint> referencedList = new ArrayList<>();
		referencedList.add(new IslandPoint(4,2));
		assertTrue(points.containsAll(referencedList));
	}

	@Test
	public void markLandNeighbour_islandPointXCoordinateNegative_exceptionIsGenerated() {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{1,0,0}
		};

		IslandPoint islandPoint= new IslandPoint(-10,1);
		Exception exception = assertThrows(InvalidDataException.class, () -> {
			task1.markLandNeighbour(map,points,islandPoint);
		});

		assertThat(exception.getMessage(),is("Invalid x = -10 coordinate for island point"));
	}

	@Test
	public void markLandNeighbour_islandPointXCoordinateBiggerThanMap_nothingMarked() throws InvalidDataException {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{1,0,0}
		};

		IslandPoint islandPoint= new IslandPoint(1000,2);
		task1.markLandNeighbour(map,points,islandPoint);
		assertThat(map, is(new int[][]{
				{0,0,0},
				{0,0,0},
				{1,0,0}
		}));
	}

	@Test
	public void markLandNeighbour_islandPointYCoordinateNegative_exceptionIsGenerated() {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{1,0,0}
		};

		IslandPoint islandPoint= new IslandPoint(0,-100);
		Exception exception = assertThrows(InvalidDataException.class, () -> {
			task1.markLandNeighbour(map,points,islandPoint);
		});

		assertThat(exception.getMessage(),is("Invalid y = -100 coordinate for island point"));
	}

	@Test
	public void markLandNeighbour_islandPointYCoordinateBiggerThanMap_nothingMarked() throws InvalidDataException {
		int[][] map = {
				{0,0,0},
				{0,0,0},
				{1,0,0}
		};

		IslandPoint islandPoint= new IslandPoint(0,1000);
		task1.markLandNeighbour(map,points,islandPoint);
		assertThat(map, is(new int[][]{
				{0,0,0},
				{0,0,0},
				{1,0,0}
		}));
	}
}
