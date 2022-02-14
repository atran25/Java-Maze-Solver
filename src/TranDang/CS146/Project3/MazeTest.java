package TranDang.CS146.Project3;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MazeTest {

	Maze maze;
	Utility util;
	
	
	/*EXPLANATION OF WHAT THE JUNIT TESTS ARE DOING
	 * We start by creating a maze and initiating it with a size(different size for each JUnit test)
	 * Then we solve the Maze for using DFS and BFS
	 * Next, we write the solution(Both the maze showing the explored cells and the maze showing 
	 * 	the shortest path) of DFS and BFS into 2 text file (BFSMazeX and DFSMazeX, where X = Size of Maze)
	 * Next, we read back the maze in the file into a string and compare that string to the BFS and DFS solution in the program
	 * 
	 * **I have included commented out System.out statements that print out what the assertEqual statements are comparing
	 * */
	
	@Test
	void OneByOneMaze() {
		int size = 2;
		maze = new Maze(size);
		maze.generateMaze();
		maze.solveDFS();
		maze.BFS();

	
		util = new Utility(maze);
		util.writeTo();
		
		String temp = util.readFromDFS(size);
		String fromProgram = maze.displayDFS() + "\n" + "\n" + maze.displayDFSPath();
		assertEquals(temp.trim(), fromProgram.trim());
		
		temp = util.readFromBFS(size);
		fromProgram = maze.displayMazeVisitsBFS() + "\n" + "\n" + maze.displayMazeSolutionBFS();
		assertEquals(temp.trim(), fromProgram.trim());
		
		//To show the maze
//		System.out.println(temp);
//		System.out.println("\n");
//		System.out.println(fromProgram);
//		System.out.println("LengthDFS: " + maze.displayDFSInfo());
//		System.out.println("LengthBFS: " + maze.displayBFSInfo());
		
	}
	
	@Test
	void TwoByTwoMaze() {
		int size = 2;
		maze = new Maze(size);
		maze.generateMaze();
		maze.solveDFS();
		maze.BFS();

	
		util = new Utility(maze);
		util.writeTo();
		
		String temp = util.readFromDFS(size);
		String fromProgram = maze.displayDFS() + "\n" + "\n" + maze.displayDFSPath();
		assertEquals(temp.trim(), fromProgram.trim());
		
		temp = util.readFromBFS(size);
		fromProgram = maze.displayMazeVisitsBFS() + "\n" + "\n" + maze.displayMazeSolutionBFS();
		assertEquals(temp.trim(), fromProgram.trim());
		
		//To show the maze
//		System.out.println(temp);
//		System.out.println("\n");
//		System.out.println(fromProgram);
//		System.out.println("LengthDFS: " + maze.displayDFSInfo());
//		System.out.println("LengthBFS: " + maze.displayBFSInfo());
		
	}
	
	@Test
	void FourByFourMaze() {
		int size = 4;
		maze = new Maze(size);
		maze.generateMaze();
		maze.solveDFS();
		maze.BFS();

	
		util = new Utility(maze);
		util.writeTo();
		
		String temp = util.readFromDFS(size);
		String fromProgram = maze.displayDFS() + "\n" + "\n" + maze.displayDFSPath();
		assertEquals(temp.trim(), fromProgram.trim());
		
		temp = util.readFromBFS(size);
		fromProgram = maze.displayMazeVisitsBFS() + "\n" + "\n" + maze.displayMazeSolutionBFS();
		assertEquals(temp.trim(), fromProgram.trim());
		
		
		
		//To show the maze
//		System.out.println(temp);
//		System.out.println("\n");
//		System.out.println(fromProgram);
//		System.out.println("LengthDFS: " + maze.displayDFSInfo());
//		System.out.println("LengthBFS: " + maze.displayBFSInfo());
		
	}
	
	@Test
	void EightByEightMaze() {
		int size = 8;
		maze = new Maze(size);
		maze.generateMaze();
		maze.solveDFS();
		maze.BFS();

	
		util = new Utility(maze);
		util.writeTo();
		
		String temp = util.readFromDFS(size);
		String fromProgram = maze.displayDFS() + "\n" + "\n" + maze.displayDFSPath();
		assertEquals(temp.trim(), fromProgram.trim());
		
		temp = util.readFromBFS(size);
		fromProgram = maze.displayMazeVisitsBFS() + "\n" + "\n" + maze.displayMazeSolutionBFS();
		assertEquals(temp.trim(), fromProgram.trim());
		
		//To show the maze
//		System.out.println(temp);
//		System.out.println("\n");
//		System.out.println(fromProgram);
//		System.out.println("LengthDFS: " + maze.displayDFSInfo());
//		System.out.println("LengthBFS: " + maze.displayBFSInfo());
		
	}
	
	@Test
	void SixteenBySixteenMaze() {
		int size = 16;
		maze = new Maze(size);
		maze.generateMaze();
		maze.solveDFS();
		maze.BFS();

	
		util = new Utility(maze);
		util.writeTo();
		
		String temp = util.readFromDFS(size);
		String fromProgram = maze.displayDFS() + "\n" + "\n" + maze.displayDFSPath();
		assertEquals(temp.trim(), fromProgram.trim());
		
		temp = util.readFromBFS(size);
		fromProgram = maze.displayMazeVisitsBFS() + "\n" + "\n" + maze.displayMazeSolutionBFS();
		assertEquals(temp.trim(), fromProgram.trim());
		
		//To show the maze
//		System.out.println(temp);
//		System.out.println("\n");
//		System.out.println(fromProgram);
//		System.out.println("LengthDFS: " + maze.displayDFSInfo());
//		System.out.println("LengthBFS: " + maze.displayBFSInfo());
		
	}
	
	@Test
	void ThirtyTwoByThirtyTwoMaze() {
		int size = 32;
		maze = new Maze(size);
		maze.generateMaze();
		maze.solveDFS();
		maze.BFS();

	
		util = new Utility(maze);
		util.writeTo();
		
		String temp = util.readFromDFS(size);
		String fromProgram = maze.displayDFS() + "\n" + "\n" + maze.displayDFSPath();
		assertEquals(temp.trim(), fromProgram.trim());
		
		temp = util.readFromBFS(size);
		fromProgram = maze.displayMazeVisitsBFS() + "\n" + "\n" + maze.displayMazeSolutionBFS();
		assertEquals(temp.trim(), fromProgram.trim());
		
		//To show the maze
//		System.out.println(temp);
//		System.out.println("\n");
//		System.out.println(fromProgram);
//		System.out.println("LengthDFS: " + maze.displayDFSInfo());
//		System.out.println("LengthBFS: " + maze.displayBFSInfo());
		
	}
	
	@Test
	void SixtyFourBySixtyFourMaze() {
		int size = 64;
		maze = new Maze(size);
		maze.generateMaze();
		maze.solveDFS();
		maze.BFS();

	
		util = new Utility(maze);
		util.writeTo();
		
		String temp = util.readFromDFS(size);
		String fromProgram = maze.displayDFS() + "\n" + "\n" + maze.displayDFSPath();
		assertEquals(temp.trim(), fromProgram.trim());
		
		temp = util.readFromBFS(size);
		fromProgram = maze.displayMazeVisitsBFS() + "\n" + "\n" + maze.displayMazeSolutionBFS();
		assertEquals(temp.trim(), fromProgram.trim());
		
		//To show the maze
//		System.out.println(temp);
//		System.out.println("\n");
//		System.out.println(fromProgram);
//		System.out.println("LengthDFS: " + maze.displayDFSInfo());
//		System.out.println("LengthBFS: " + maze.displayBFSInfo());
		
	}
	
	@Test
	void OneTwentyEightByOneTwentyEightMaze() {
		int size = 128;
		maze = new Maze(size);
		maze.generateMaze();
		maze.solveDFS();
		maze.BFS();

	
		util = new Utility(maze);
		util.writeTo();
		
		String temp = util.readFromDFS(size);
		String fromProgram = maze.displayDFS() + "\n" + "\n" + maze.displayDFSPath();
		assertEquals(temp.trim(), fromProgram.trim());
		
		temp = util.readFromBFS(size);
		fromProgram = maze.displayMazeVisitsBFS() + "\n" + "\n" + maze.displayMazeSolutionBFS();
		assertEquals(temp.trim(), fromProgram.trim());
		
		//To show the maze
//		System.out.println(temp);
//		System.out.println("\n");
//		System.out.println(fromProgram);
//		System.out.println("LengthDFS: " + maze.displayDFSInfo());
//		System.out.println("LengthBFS: " + maze.displayBFSInfo());
		
	}
	
	

}
