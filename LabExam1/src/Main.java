import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    private Character entrance;
    private Character exit;
    private ArrayList<Deque> seats = new ArrayList<>();
    private ArrayList<Deque> seatsCopy = new ArrayList<>();

    //customer counter
    private int counter = 0;

    //max number of seats in all rows of the cinema
    public int maxSeats() {
        int rows = seats.size(); //number of rows
        int max = 0;
        int arr[] = new int[rows];
        for (int a = 0; a < rows; a++) {
            Deque row = seats.get(a);
            int rowCapacity = row.size(); //number of seats per row
            arr[a] = rowCapacity;
        }
        for (int cur :  arr){
            max = Math.max(max,cur);
        }
        return max;
    }

    //used to read the json file
    public void fileRead(String fileName) {
        JSONParser jParser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {
            Object obj = jParser.parse(reader);
            JSONObject jObj = (JSONObject) obj;

            String jEntrance = (String) jObj.get("entrance");
            String jExit = (String) jObj.get("exit");

            entrance = jEntrance.charAt(0);
            exit = jExit.charAt(0);

            JSONArray rows = (JSONArray) jObj.get("rows");
            ArrayList<Integer> seatsEachRow = new ArrayList<>();

            for (Object o : rows) {
                seatsEachRow.add((int) (long) o);
            }

            for (Integer rowMax : seatsEachRow) {
                Deque row = new Deque(rowMax);
                seats.add(row);
            }

            for (Integer rowMax : seatsEachRow) {
                Deque row = new Deque(rowMax);
                seatsCopy.add(row);
            }

        } catch (FileNotFoundException e) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input valid file name: ");
            fileName = sc.nextLine();
            fileRead(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //returns fileName for fileRead
    public String file(){
        Scanner inputFile = new Scanner(System.in);
        System.out.print("Input .json file name: ");
        String fileName = inputFile.nextLine();
        return fileName;
    }

    //display seats of the cinema
    public void printSeats() {
        for (Deque row : seats) {
            System.out.print("|\t");
            int rowCapacity = row.size();
            for (int j = 0; j < rowCapacity; j++) {
                if (row.get(j) == null) {
                    System.out.print("-\t|\t");
                } else {
                    System.out.print(row.get(j) + "\t|\t");
                }
            }
            System.out.println();
        }
    }

    //place customer in their seats
    public void placeCus(char name, int rowChoice) {
        int rows = seats.size(); //number of rows
        int arr[] = new int[rows];
        for (int a = 0; a < rows; a++) {
            Deque row = seats.get(a);
            int rowCapacity = row.size(); //number of seats per row
            arr[a] = rowCapacity;
        }
        Scanner input = new Scanner(System.in);

        for(int i = 0; i <arr.length; i++) {
            for (int j = 0; j < seats.size(); j++) {
                Deque seatArr = seats.get(j);
                while (seatArr.contain(name)) {
                    System.out.println("Invalid input");
                    System.out.print("Input another name: ");
                    String nameStr = input.nextLine();
                    name = nameStr.charAt(0);
                    while (!Character.isLetter(name)) {
                        System.out.print("Please input a valid name: ");
                        String nameStr1 = input.nextLine();
                        name = nameStr1.charAt(0);
                    }
                }
            }
        }
        if (entrance == 'r') {
            Deque rowChosen = seats.get(rowChoice - 1);
            rowChosen.insertRear(name);
        } else if (entrance == 'l') {
            Deque rowChosen = seats.get(rowChoice - 1);
            rowChosen.insertFront(name);
        }
    }

    //prints exit plan
    public void printExit(int max) {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < seatsCopy.size(); j++) {
                Deque seatArr = seatsCopy.get(j);
                if (!seatArr.isEmpty()) {
                    if (exit == 'r') {
                        System.out.print(seatArr.getRear());
                        seatArr.deleteRear();
                    } else if (exit == 'l'){
                        System.out.print(seatArr.getFront());
                        seatArr.deleteFront();
                    }
                }
            }
            System.out.println();
        }
    }

    public void copyPlaceCus(char name, int rowChosen){
    	if(entrance == 'l') {
    		if(entrance == 'r') {
    		    Deque rowCopy = seatsCopy.get(rowChosen -1);
    		    rowCopy.insertFront(name);
    			}
    			else if (entrance == 'l') {
    		    	Deque rowCopy = seatsCopy.get(rowChosen -1);
    		    	rowCopy.insertRear(name);
    			}
    		}else {
    			if(entrance == 'r') {
    		    	Deque rowCopy = seatsCopy.get(rowChosen -1);
    		    	rowCopy.insertRear(name);
    			}else if (entrance == 'l') {
    		    	Deque rowCopy = seatsCopy.get(rowChosen -1);
    		    	rowCopy.insertFront(name);
    			}
    		}
    }

    public void seatCopy() {
        for (int i = 1; i < seats.size()+1; i++){
            Deque rows = seats.get(i-1);
            for(int j = 0; j < rows.size(); j++){
                Character copy = rows.get(j);
                if(copy != null){
                    copyPlaceCus(copy, i);
                }
            }
        }
    }

    public void displayCopy(){
        for (Deque rows : seatsCopy) {
            System.out.print("|\t");
            int rowCapacity = rows.size();
            for (int j = 0; j < rowCapacity; j++) {
                if (rows.get(j) == null) {
                    System.out.print("-\t|\t");
                } else {
                    System.out.print(rows.get(j) + "\t|\t");
                }
            }
            System.out.println();
        }
    }

    public void Menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Input a customer\n[2] Print exit plan\n[3] Exit");
        System.out.println();
        System.out.println("Entrance is at the " + entrance);
        System.out.println("Exit is at the " + exit);
        System.out.println();
        System.out.print("Choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.print("Input customer name: ");
                String nameInput = input.next();
                char name = nameInput.charAt(0);

                if (!Character.isLetter(name)) {
                    System.out.println("Please input a valid name.");
                    Menu();
                } //checks if customer name is a valid input: letter (a-z, A-z)

                printSeats();
                System.out.print("Input choice of row: ");
                int rowChoice = input.nextInt();
                while ((rowChoice > seats.size()) || (rowChoice < 0)) {
                    System.out.print("Invalid choice. Choose a different row number: ");
                    rowChoice = input.nextInt();
                }//checks if row choice is valid

                System.out.println("Here's your seat: ");
                placeCus(name,rowChoice);
                counter++;
                printSeats();
                Menu();
            }
            case 2 -> {
                printSeats();
                if (counter != 0){
                    System.out.println("Exit Plan: ");
                    seatCopy();
                    printExit(maxSeats());
                    System.out.print("Continue? y or n: ");
                    char con = input.next().charAt(0);
                    while (con == 'y'){
                        Menu();
                        seatCopy();
                        displayCopy();
                    }
                    if(con == 'n'){
                        System.out.println("Program Closed.");
                        System.exit(0);
                    }
                }else if (counter == 0){//cinema is empty ask for insertion of customer
                    System.out.println("The cinema is empty. Try adding customers first.");
                    Menu();
                }
            }
            case 3 -> {
                System.out.println("Program Closed.");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid input!");
                Menu();
            }
        }
        input.close();
    }

    public static void main(String[] args) {
        Main driver = new Main();
        driver.fileRead(driver.file());
        driver.Menu();
    }
}
