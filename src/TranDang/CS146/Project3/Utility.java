package TranDang.CS146.Project3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Utility {
	
	Maze myMaze;
	Scanner in;
	PrintWriter out;
	
	public Utility(Maze inputtedMaze) {
		myMaze = inputtedMaze;
	}
	
	/*
	 * Simple utility that writes the DFS and BFS maze solutions into 2 separate text files
	 * */
	public void writeTo() {
		int sizeOfMaze = myMaze.getRow();
		try {
			//Sets the name of DFS maze
			String nameOfFile = "DFSmaze" + sizeOfMaze + ".txt";
			File myFile = new File(nameOfFile);
			out = new PrintWriter(myFile);
			out.println(myMaze.displayDFS());
			out.print("\n");
			out.print(myMaze.displayDFSPath());
			out.close();
			
			nameOfFile = "BFSmaze" + sizeOfMaze + ".txt";
			myFile = new File(nameOfFile);
			out = new PrintWriter(myFile);
			out.println(myMaze.displayMazeVisitsBFS());
			out.print("\n");
			out.print(myMaze.displayMazeSolutionBFS());
			out.close();
			
		} catch (Exception e) {
			System.out.println("Error: writeTo");
		}
	}
	
	//Simple read method to read the DFS maze solution text file back into a string
	public String readFromDFS(int sizeOfMaze) {
		String maze = "";
		try {
			String nameOfFile = "DFSmaze" + sizeOfMaze + ".txt";
			File myFile = new File(nameOfFile);
			in = new Scanner(myFile);
			while(in.hasNextLine()) {
				maze += in.nextLine();
				maze += "\n";
			}
			
		} catch (Exception e) {
			System.out.println("Error: readFrom " + sizeOfMaze + " DFS");
			System.out.println(e.getStackTrace());
		}
		return maze;
	}
	
	//Simple read method to read the BFS maze solution text file back into a string
	public String readFromBFS(int sizeOfMaze) {
		String maze = "";
		try {
			String nameOfFile = "BFSmaze" + sizeOfMaze + ".txt";
			File myFile = new File(nameOfFile);
			in = new Scanner(myFile);
			while(in.hasNextLine()) {
				maze += in.nextLine();
				maze += "\n";
			}
			
		} catch (Exception e) {
			System.out.println("Error: readFrom " + sizeOfMaze + " BFS");
			System.out.println(e.getStackTrace());
		}
		return maze;
	}
}
