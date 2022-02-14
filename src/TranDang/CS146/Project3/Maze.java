package TranDang.CS146.Project3;

/**
 
 * A program that will automatically generate and solve mazes. 
 * Using DFS and BFS to solve it.
 * 
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Maze 
{
	Random r = new Random();							//For direction choosing
	
	public int[][] maze;                                // Stores 0 all walls intact, 1 if any wall broken per vertices
	public int[][] mazeLocation;                        // Stores the location of each vertices, 0 to n of vertices
	private int row;                                     // Stores the dimensions of the maze
	private int vertices;                                // Stores the total vertices
	private ArrayList<LinkedList<Integer>> adjList;      // Stores the adjacency list of the vertices
	
	//For DFS
	private int[] dfsVisited;                            // Stores the DFS visit time for each vertices
	private int dfsVisitedCells;                         // Stores # of visited cells in DFS                         
	private Stack<Integer> prevPos;                      // Stores the path for DFS
	
	//For BFS
	private ArrayList<Integer> orderVisitedBFS;			// Stores the order of nodes visited by BFS
	private ArrayList<Integer> solutionBFS;				// Stores the solution path for BFS
	
	/**
	 * Constructs a Maze (2D Array) and represents it as an adjacency list of linked lists
	 * Sets each vertices to 0, for all walls intact
	 * Sets Maze location w/ corresponding vertices position in the adjacency list
	 * @param r : Rows
	 */
	public Maze(int r)
	{
		this.r.setSeed(5);
		
		row = r;
		maze = new int[row][row];
		mazeLocation = new int[row][row];
		vertices = row*row;
		adjList = new ArrayList<LinkedList<Integer>>();
		
		for(int i = 1; i <= vertices; i++)
		{
			adjList.add(new LinkedList<Integer>());
		}
		
		for(int x=0; x<maze.length; x++) 
		{
			for(int y=0; y<maze[x].length; y++) 
				maze[x][y] = 0;
		}
		
		int i = 0;
		for(int x=0; x<mazeLocation.length; x++) 
		{
			for(int y=0; y<mazeLocation[x].length; y++) 
			{
				mazeLocation[x][y] = i;
				i++;
			}
		}
		
		dfsVisited = new int[vertices];
		for(int n=0; n<dfsVisited.length; n++)
			dfsVisited[n] = -1;
		dfsVisitedCells = 0;
		prevPos = new Stack<Integer>();
	}
	
	/**
	 * Sets the adjacency list of the graph
	 * @param adjList - An ArrayList of LinkedLists of the adjacent values
	 */
	public void setGraph(ArrayList<LinkedList<Integer>> adjList)
	{
		this.adjList = adjList;
	}
	
	/**
	 * Generates a Maze, represented as a undirected adjacency list
	 * Checked each vertice's neighbors, and added neighbors with no walls broken into an ArrayList
	 * Picked a random direction from the ArrayList, and updated adjacency list in the Maze
	 * Updated vertice as a broken wall, and made it the current cell
	 */
	public void generateMaze()
	{
		Stack<Integer> cellStack = new Stack<Integer>();
		int x =0, y = 0;
		int visitedCells = 1; 
		maze[x][y] = 1;
		
		while(visitedCells < vertices)
		{
//			System.out.println(visitedCells);
//			System.out.println("x: " + x + "   y: " + y + "first ");
			ArrayList<String> possibleDir = new ArrayList<String>();
			if (x-1 >= 0 && maze[x-1][y] == 0)
				possibleDir.add("N");
			
			if (x+1 < row && maze[x+1][y] == 0)
				possibleDir.add("S");
			
			if (y-1 >= 0 && maze[x][y-1] == 0)
				possibleDir.add("W");
			
			if (y+1 < row && maze[x][y+1] == 0)
				possibleDir.add("E");
			
			if (possibleDir.size() != 0)
			{
				
				String dir = direction(possibleDir);
//				System.out.println("x: " + x + "   y: " + y);
//				System.out.println(dir + " ----------------- ");
				
				
				switch(dir){
				case "N":
					maze[x-1][y] = 1;
					cellStack.push(mazeLocation[x][y]);
					adjList.get(mazeLocation[x][y]).add(mazeLocation[x-1][y]);
					adjList.get(mazeLocation[x-1][y]).add(mazeLocation[x][y]);
					x--;
					break;
				case "S":
					maze[x+1][y] = 1;
					cellStack.push(mazeLocation[x][y]);
					adjList.get(mazeLocation[x][y]).add(mazeLocation[x+1][y]);
					adjList.get(mazeLocation[x+1][y]).add(mazeLocation[x][y]);
					x++;
					break;
				case "E":
					maze[x][y+1] = 1;
					cellStack.push(mazeLocation[x][y]);
					adjList.get(mazeLocation[x][y]).add(mazeLocation[x][y+1]);
					adjList.get(mazeLocation[x][y+1]).add(mazeLocation[x][y]);
					y++;
					break;
				case "W": 
					maze[x][y-1] = 1;
					cellStack.push(mazeLocation[x][y]);
					adjList.get(mazeLocation[x][y]).add(mazeLocation[x][y-1]);
					adjList.get(mazeLocation[x][y-1]).add(mazeLocation[x][y]);
					y--;
					break;
				}
				visitedCells++;
			}
			else
			{
				int target = cellStack.pop();
//				System.out.println("target: " + target);
				for(int i=0; i<mazeLocation.length; i++) 
				{
//					System.out.println(i + ": " + mazeLocation.length);
					for(int j=0; j<mazeLocation[x].length; j++) 
					{	
//						System.out.println(j + ": " +mazeLocation[x].length);
//						System.out.println("x: " + x + "   y: " + y);
						if (target == mazeLocation[i][j])
						{
							x = i;
							y = j;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Return random direction from given list of directions
	 * @param array : List of possible directions
	 * @return a random direction
	 */
	public String direction(ArrayList<String> array)
	{
		int dir = r.nextInt(array.size());
		String direc = new String();
		
		direc = array.get(dir);
		
		return direc;
	}
	
	
	/**
	 * Display's Adjacency List
	 * @return a String with the list
	 */
	public String displayAdjList()
	{
		String print = "";
		for(int i=0; i<adjList.size(); i++)
		{
			print += i + " - " + adjList.get(i) + "\n";
		}
		
		print += "\n";
		return print;
	}
	
	/**
	 * Displays the generated Maze
	 * @return a String representation of the Maze
	 */
	public String displayMaze()
	{
		String printMaze = "+ ";
		
		for(int x=1; x<row; x++)
			printMaze += "+-";
		printMaze += "+\n";
			
		
		ArrayList<String> walls = new ArrayList<String>();
		ArrayList<String> floors = new ArrayList<String>();
		
		for(int x=0; x<mazeLocation.length; x++)
		{
			String wall = "";
			String floor = "";
			wall += "| ";
			for(int y=0; y<mazeLocation[x].length; y++)
			{
				if(y+1 != row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x][y+1]))
					wall += "  ";
				else if (y+1 == row)
					wall += "|";
				else 
					wall += "| ";
				
				if((x+1 < row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x+1][y])))
					floor += "+ ";
				else
					floor += "+-";
			}
			floor += "+";
			
			walls.add(wall);
			if (x != row-1)
			{
				floors.add(floor);
			}
		}
		
		for(int x=0; x<walls.size(); x++)
		{
			printMaze += walls.get(x) + "\n";
			if (x != walls.size()-1)
				printMaze += floors.get(x) + "\n";
		}
		
		for(int x=0; x<row-1; x++)
			printMaze += "+-";
		printMaze += "+ +";
			
		return printMaze;
	}
	
	/***************************************************************************************************
	 *  Depth First Search part here
	 ***************************************************************************************************/
	/**
	 * Solve's the maze through Depth First Search algorithm via it's adjacency list
	 */
	public void solveDFS()
	{
		int time = 0;
		
		int position = 0;
		dfsVisited[position] = time;
		time++;
		dfsVisitedCells++;
		while (position != (vertices-1))
		{
			LinkedList<Integer> adjVert = adjList.get(position);
			
			boolean allVisited = true;    //Assume all positions are visited
			Collections.sort(adjVert);    //Sort adjacency list
			for(Integer n : adjVert)      //For all adjacent vertices
			{
				if (dfsVisited[n] == -1)  //If the vertex has not been visited
				{
					allVisited = false;      //Mark all visited as false
					dfsVisitedCells++;       //Increment # of visited cells
					dfsVisited[n] = time;    //The new position now carries a time visited
					time++;
					if (time == 10)
						time = 0;
					
					prevPos.push(position);  //The previous position will be the current position
					position = n;            //The new position is the unvisited position
					break;
				}
			}
			
			if(allVisited == true) //However if all were visited
			{
				position = prevPos.pop();    //The position becomes the previous position
			}
		}
		
		prevPos.push(vertices-1);
	}
	
	/**
	 * Display's the maze with visited time - Depth First Search
	 * @return a String representation of the maze
	 */
	public String displayDFS()
	{
		String printMaze = "+ ";
		
		for(int x=1; x<row; x++)
			printMaze += "+-";
		printMaze += "+\n";
			
		
		ArrayList<String> walls = new ArrayList<String>();
		ArrayList<String> floors = new ArrayList<String>();
		
		for(int x=0; x<mazeLocation.length; x++)
		{
			String wall = "";
			String floor = "";
			if(dfsVisited[mazeLocation[x][0]] != -1)
				wall += "|" + dfsVisited[mazeLocation[x][0]];
			else
				wall += "| ";
			for(int y=0; y<mazeLocation[x].length; y++)
			{
				if(y+1 != row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x][y+1]))
				{
					if (dfsVisited[mazeLocation[x][y+1]] != -1)
						wall += " " + dfsVisited[mazeLocation[x][y+1]];
					else
						wall += "  ";
				}
				else if (y+1 == row)
					wall += "|";
				else 
				{
					if (dfsVisited[mazeLocation[x][y+1]] != -1)
						wall += "|" + dfsVisited[mazeLocation[x][y+1]];
					else
						wall += "| ";
				}
				if((x+1 < row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x+1][y])))
					floor += "+ ";
				else
					floor += "+-";
			}
			floor += "+";
			
			walls.add(wall);
			if (x != row-1)
			{
				floors.add(floor);
			}
		}
		
		for(int x=0; x<walls.size(); x++)
		{
			printMaze += walls.get(x) + "\n";
			if (x != walls.size()-1)
				printMaze += floors.get(x) + "\n";
		}
		
		for(int x=0; x<row-1; x++)
			printMaze += "+-";
		printMaze += "+ +";
		
		return printMaze;
	}
	
	/**
	 * Display's the maze with the correct path - Depth First Search
	 * @return a String representation of the maze
	 */
	public String displayDFSPath()
	{
		String printMaze = "+ ";
		
		for(int x=1; x<row; x++)
			printMaze += "+-";
		printMaze += "+\n";
			
		
		ArrayList<String> walls = new ArrayList<String>();
		ArrayList<String> floors = new ArrayList<String>();
		
		for(int x=0; x<mazeLocation.length; x++)
		{
			String wall = "";
			String floor = "";
			if(prevPos.search(mazeLocation[x][0]) != -1)
				wall += "|#";
			else
				wall += "| ";
			
			for(int y=0; y<mazeLocation[x].length; y++)
			{
				if(y+1 != row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x][y+1]))
				{
					if (prevPos.search(mazeLocation[x][y+1]) != -1)
						wall += " #";
					else
						wall += "  ";
				}
				else if (y+1 == row)
					wall += "|";
				else 
				{
					if (prevPos.search(mazeLocation[x][y+1]) != -1)
						wall += "|#";
					else
						wall += "| ";
				}
				if((x+1 < row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x+1][y])))
					floor += "+ ";
				else
					floor += "+-";
			}
			floor += "+";
			
			walls.add(wall);
			if (x != row-1)
			{
				floors.add(floor);
			}
		}
		
		for(int x=0; x<walls.size(); x++)
		{
			printMaze += walls.get(x) + "\n";
			if (x != walls.size()-1)
				printMaze += floors.get(x) + "\n";
		}
		
		for(int x=0; x<row-1; x++)
			printMaze += "+-";
		printMaze += "+ +";
			
		return printMaze;
	}
	
	/**
	 * Contains the info, such as path, length, and number of visited cells
	 * @return a String representation of the DFS information 
	 */
	public String displayDFSInfo()
	{
		int length = 0;
		
		Stack<Integer> tempStack = (Stack<Integer>) prevPos.clone(); //Clone the stack so it doesn't mess up stack of Maze class
		StringBuilder infoOutput = new StringBuilder("");
		
		while (!tempStack.empty()) {
			
			infoOutput.insert(0, tempStack.pop().toString() + " ");
			
			length++;
		}
		
		infoOutput.insert(0, "Cell Path (Starts from 0): ");
		infoOutput.append("\n");
		infoOutput.append("Length of path: " + length + "\n");
		infoOutput.append("Visited cells: " + dfsVisitedCells + "\n");

		return infoOutput.toString();
	}
	
	/***************************************************************************************************
	 *  Breath First Search part here
	 ***************************************************************************************************/
	/*
	 * BFS to visit cells and find the shortest path solution to the maze.
	 * Prints the order of cells visited and the shortest path. 
	 */
	public void BFS()
	{
		boolean[] visited = new boolean[vertices];	//keep track of visited nodes
		int[] previous = new int[vertices]; 		//vertex at index was visited after element at index
		for(int i = 0; i < visited.length; i++)
		{
			visited[i] = false;
			previous[i] = -1;
		}
		
		ArrayList<Integer> orderOfNodesVisited = new ArrayList<>(); //keep track of order of nodes visited
		ArrayList<Integer> queue = new ArrayList<>(); 				//nodes to visit
		
		visited[0] = true; //we always enter maze at upper left hand corner
		queue.add(0); 
		
		boolean exit = false; 	//for ending while when exit found
		
		//int previousCount = 0;
		while(queue.size() != 0)
		{
			int exploredCell = queue.remove(0);	//the node whose neighbors to explore
			orderOfNodesVisited.add(exploredCell); 
			
			LinkedList<Integer> neighborsToExplore = adjList.get(exploredCell);	//the neighbors to explore
			
			if(exit)
			{
				orderOfNodesVisited.add(vertices - 1); 
				break;
			}
			
			//for(int i = neighborsToExplore.size() - 1; i >= 0; i --) //Either for statement works, just choose whichever you prefer
			for (int i = 0 ; i < neighborsToExplore.size(); i++)
			{
				int neighbor = neighborsToExplore.get(i);
				if(visited[neighbor] == false)
				{
					visited[neighbor] = true;
					previous[neighbor] = exploredCell;
					queue.add(neighbor);
					
				}
				if(neighbor == vertices - 1)
				{
					exit = true;
					break;
				}
				
			}//end for
		
		}//end while
			
		ArrayList<Integer> shortestPath = new ArrayList<>();	//shortest path to solution
//		for(int i: previous)
//		{
//			System.out.print(" " +i);
//		}

		int i = previous.length - 1;
		while (i != 0) 
		{
//			System.out.println(i);
			if(i == -1)
			{
				break;
			}
			shortestPath.add(i);
			i = previous[i];
			
		}

		shortestPath.add(vertices - 1);
		Collections.sort(shortestPath);

		orderVisitedBFS = orderOfNodesVisited;
		solutionBFS = shortestPath;
		
	}//end BFS()
	
	/**
	 * Displays the shortest path solution to the maze.
	 * @return the string representation of the graph with the path
	 */
	public String displayMazeSolutionBFS()
	{
		String printMaze = "+ ";
		
		for(int x=1; x<row; x++)
			printMaze += "+-";
		printMaze += "+\n";
			
		
		ArrayList<String> walls = new ArrayList<String>();
		ArrayList<String> floors = new ArrayList<String>();
		
		for(int x=0; x<mazeLocation.length; x++)
		{
			String wall = "";
			String floor = "";
			if(x == 0)
			{
				wall += "|#";
			}
			else
			{
				if(solutionBFS.contains(x * row))
				{
					wall += "|#";
				}
				else
				{
					wall += "| ";
				}
			}
			
			for(int y=0; y<mazeLocation[x].length; y++)
			{
				if(y+1 != row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x][y+1]))
				{
					if(solutionBFS.contains(mazeLocation[x][y]) || solutionBFS.contains(mazeLocation[x][y+1]))
					{
						wall += " #";
					}
					else
					{
						wall += "  ";
					}
					
				}
				else if (y+1 == row)
					wall += "|";
				else 
					if(solutionBFS.contains(mazeLocation[x][y] + 1)) 
					{
						wall += "|#";
					}
					else
					{
						wall += "| ";
					}
					
				
				if((x+1 < row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x+1][y])))
					floor += "+ ";
				else
					floor += "+-";
			}
			floor += "+";
			
			walls.add(wall);
			if (x != row-1)
			{
				floors.add(floor);
			}
		}
		
		for(int x=0; x<walls.size(); x++)
		{
			printMaze += walls.get(x) + "\n";
			if (x != walls.size()-1)
				printMaze += floors.get(x) + "\n";
		}
		
		for(int x=0; x<row-1; x++)
			printMaze += "+-";
		printMaze += "+ +";
			
		return printMaze;
	}
	
	/**
	 * Display the order of cells visited in the graph with
	 * @return the string representation of the cells visited
	 */
	public String displayMazeVisitsBFS()
	{
		String printMaze = "+ ";
		
		for(int x=1; x<row; x++)
			printMaze += "+-";
		printMaze += "+\n";
			
		
		ArrayList<String> walls = new ArrayList<String>();
		ArrayList<String> floors = new ArrayList<String>();
		for(int x=0; x<mazeLocation.length; x++)
		{
			int order = orderVisitedBFS.indexOf(mazeLocation[x][0]) % 10;
			
			String wall = "";
			String floor = "";

			if(order < 0)
			{
				wall += "| ";
			}
			else
				wall += "|" + order;

			order = 0;
			
			for(int y=0; y<mazeLocation[x].length; y++)
			{
				if(y+1 != row)
				{
					order = orderVisitedBFS.indexOf(mazeLocation[x][y+1]) % 10;
				}
				else
				{
					order = orderVisitedBFS.indexOf(mazeLocation[x][y]) % 10;
				}
				

				if(y+1 != row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x][y+1]))
				{
					if(order < 0)
					{
						wall += "  ";
					}
					else
						wall += " " + order;
				}
					
				else if (y+1 == row)
					wall += "|";
				else 
				{
					if(order < 0)
					{
						wall += "| ";
					}
					else
						wall += "|" + order;
				}	
					
				if((x+1 < row && adjList.get(mazeLocation[x][y]).contains(mazeLocation[x+1][y])))
					floor += "+ ";
				else
					floor += "+-";
			}
			floor += "+";
			
			walls.add(wall);
			if (x != row-1)
			{
				floors.add(floor);
			}
		}
		
		for(int x=0; x<walls.size(); x++)
		{
			printMaze += walls.get(x) + "\n";
			if (x != walls.size()-1)
				printMaze += floors.get(x) + "\n";
		}
		
		for(int x=0; x<row-1; x++)
			printMaze += "+-";
		printMaze += "+ +";
			
		return printMaze;
	}
	
	/**
	 * Contains the info, such as path, length, and number of visited cells
	 * @return a String representation of the BFS information 
	 */
	public String displayBFSInfo()
	{
		int length = 0;
		
		
		Stack<Integer> tempStack = (Stack<Integer>) prevPos.clone(); //Clone the stack so it doesn't mess up stack of Maze class
		StringBuilder infoOutput = new StringBuilder("");
		
		while (!tempStack.empty()) {
			
			infoOutput.insert(0, tempStack.pop().toString() + " ");
			
			length++;
		}
		
		infoOutput.insert(0, "Cell Path (Starts from 0): ");
		infoOutput.append("\n");
		infoOutput.append("Length of path: " + length + "\n");
		infoOutput.append("Visited cells: " + orderVisitedBFS.size() + "\n");

		return infoOutput.toString();
	}
	
	public int getRow() {
		return row;
	}
}
