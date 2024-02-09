import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Firefighting {
	static int numV;
	static int size = 2000;
	static int defVal = 99999;
	static char[][] array = new char[size][size];
	static int[][] weightedGraph = new int[size][size];
	static int[][] twoDarr = new int [size][size];
	static LinkedList<String> lines = new LinkedList<>();
	static ArrayList<Character> vertices = new ArrayList<>();
	
	public static void menu() {
		System.out.println("[1] Select map \n[2] Find shortest path \n[3] Exit ");
		System.out.print("Input choice: ");
	}
	
	public static void readFile(String filename) {
		try {
			File file = new File(filename);
			Scanner fileReader = new Scanner(file);
			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				lines.add(data);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found!!!");
		}
	}
	
	public static void processMap() {
		int count = 0;
		int col;
		int row = 0;
		
		System.out.println("\n       GIVEN MAP       ");
		System.out.println("===========================");
		
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length(); j++) {
				array[i][j] = lines.get(i).charAt(j);
				if (Character.isLetterOrDigit(lines.get(i).charAt(j))) {
					vertices.add(lines.get(i).charAt(j));
					numV++;
				}
			}
		}
		
		for (int i = 0; i < numV; i++) {
			for (int j = 0; j < numV; j++) {
				weightedGraph[i][j] = 0;
			}
		}
		
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length(); j++) {
				System.out.print(array[i][j]);
				if (Character.isLetterOrDigit(array[i][j])) {
					col = vertices.indexOf(array[i][j]);
					if ((array[i][j + 1] == '*')) {
						while (array[i][j + 1 + count] == '*')
							count++;
					}
					if (Character.isLetterOrDigit(array[i][j + 1 + count])) {
						if (vertices.contains(array[i][j + 1 + count]))
							row = vertices.indexOf(array[i][j + 1 + count]);
					}
					if (weightedGraph[col][row] == 0 && col != row) {
						weightedGraph[col][row] = count;
						weightedGraph[row][col] = count;
						count = 0;
					}
				}
			}
			System.out.println();
		}
		row = 0;
		int count1 = 0;
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length(); j++) {
				if (Character.isLetterOrDigit(array[i][j])) {
					col = vertices.indexOf(array[i][j]);
					if (array[i + 1][j] == '*') {
						while (array[i + 1 + count1][j] == '*')
							count1++;
					}
					if (Character.isLetterOrDigit(array[i + 1 + count1][j])) {
						row = vertices.indexOf(array[i + 1 + count1][j]);
					}
					if (weightedGraph[col][row] == 0 && col != row) {
						weightedGraph[col][row] = count1;
						weightedGraph[row][col] = count1;
						count1 = 0;
					}
				}
			}
		}
		for (int i = 0; i < numV; i++) {
			for (int j = 0; j < numV; j++) {
				if (weightedGraph[i][j] == 0 && i != j) {
					weightedGraph[i][j] = defVal;
				}
			}
		}
		
		System.out.println("===========================");
	}
	
	static int[][] floydAlgo(int[][] graph, ArrayList<Character> vertices) {
		int i, j, k;
		int n = vertices.size();
		int[][] travel = graph;

		int[][] matrix = new int[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				if (i != j)
					matrix[i][j] = j + 1;
		}
		for (k = 0; k < n; k++) {
			for (j = 0; j < n; j++) {
				for (i = 0; i < n; i++) {
					if (travel[i][k] + travel[k][j] < travel[i][j]) {
						travel[i][j] = travel[i][k] + travel[k][j];
						matrix[i][j] = matrix[i][k];
					}
				}
			}
		}
		return matrix;
	}

	static void results(char start, String end, int[][] travel, int[][] twoDarr, ArrayList<Character> vertices) {
		System.out.println();
		int u, v;
		char endChar = end.charAt(0);
		
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				if (i != j) {
					u = i + 1;
					v = j + 1;
					char b = vertices.get(u - 1);
					char a;
					
					StringBuilder path = new StringBuilder("" + b);
					
					do {
						u = twoDarr[u - 1][v - 1];
						a = vertices.get(u - 1);
						path.append(", ").append(a);
					} while (u != v);
					
					char c = vertices.get(v - 1);
					String c1 = "" + c;
					
					if (!(vertices.contains(start) && vertices.contains(endChar))) {
						System.out.println("Points were not found");
						return;
					}
					if (start == endChar) {
						int index = vertices.indexOf(endChar);
						System.out.println("Path: \n" + endChar + "\nDistance: \n" + travel[index][index]);
						return;
					}
					if ((start == b && path.toString().contains(end) && end.equals(c1))) 
						System.out.println("Path: \n" + path + "\nDistance: \n" + travel[i][j] + "km");
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		menu();
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();

		do{switch (input) {
			case 1 :
					lines.clear();
					numV = 0;
					vertices.clear();
				
					System.out.print("\nYou may choose from the following:\nmap1v2.txt\nmap2v2.txt\nmap3v2.txt\nEnter map filename: ");
					String inputFile = scanner.next();
					readFile(inputFile);
					processMap();
					
					twoDarr = floydAlgo(weightedGraph, vertices);
					System.out.println();
					menu();
					input = scanner.nextInt();
					break;
					
				case 2:
					System.out.print("\nEnter start point: ");
					String start = scanner.next();
					System.out.print("Enter end point: ");
					String end = scanner.next();
					char startChar = start.charAt(0);
					results(startChar, end, weightedGraph, twoDarr, vertices);
					System.out.println();
					menu();
					input = scanner.nextInt();
					break;
					
				case 3 :
					System.out.println("Exiting Program...");
					break;
					
				default:
					System.out.println("Input a valid choice: ");
					menu();
					input = scanner.nextInt();
			}
		} while (input != 3);
		System.out.println("Exiting Program...");
		scanner.close();
	}
}