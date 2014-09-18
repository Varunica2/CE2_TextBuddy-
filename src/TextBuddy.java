import java.io.*;
import java.util.*;

public class TextBuddy {

	private static ArrayList<String> text = new ArrayList<String>();
	private static String dataFileName;
	private static String operation;
	private static String content;
	private static String WELCOME_MESSAGE = "Welcome to TextBuddy." + dataFileName + "is ready for use.";
	private static LinkedList<String> listTextLoadedFromFile = new LinkedList<String>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		TestTextBuddy();
		
	}	
		
	private static void TestTextBuddy() throws IOException {

		dataFileName = sc.nextLine();
		try {

			FileReader fileReader = new FileReader(dataFileName);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			localDataFromBuffer(bufferReader);
			fileReader.close();
			//String welcome = "Welcome to TextBuddy. " + dataFileName
				//	+ " is ready for use.";
			
			System.out.println(WELCOME_MESSAGE);
			implementCommand();

		} catch (FileNotFoundException e) {

			String notFound = "Error: File " + dataFileName + " not found!";
			System.out.println(notFound);

		}

	}

	private static void implementCommand() {

		command();
		boolean run = true;

		while (run) {

			switch (operation) {

			/*
			 * case "null": run = false; break;
			 */
			case "add":
				add(content);
				break;
			case "clear":
				clear();
				break;
			case "delete":
				delete(content);
				break;
			case "display":
				display(text);
				break;
			case "exit":
				System.exit(0);
				// run = false;
				break;
			case "sort":
				sort();
				break;
			case "search":
				search(content);
				break;
			default:
				printError();
				break;
			}

			saveDataIntoFile();
			command();
		}

	}

	private static void search(String searchWord) {

		for(int i=0; i<text.size(); i++){

		     int foundAtLine = text.get(i).indexOf(searchWord);
		     if(foundAtLine == - 1){
		        System.out.println(searchWord + "not found");
		     }else{
		        System.out.println("Found " + searchWord + " at line "
		        + foundAtLine);
		     }
		}
	}

	private static void sort() {
		Collections.sort(text);
	}

	// returns false only if input is "display"
	private static void command() {

		System.out.print("command:");
		String inputCommand = sc.nextLine();

		try {
			String[] splitCommand = inputCommand.split(" ", 2);
			operation = splitCommand[0];
			content = splitCommand[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			if (checkForDisplay(inputCommand)) {
				operation = "display";

			} else if (checkForExit(inputCommand)) {
				operation = "exit";
				// exit = true;

			} else if (checkForClear(inputCommand)) {
				operation = "clear";

			} else if (checkForSort(inputCommand)) {
				operation = "sort";
			
			} else {
				printError();
			}

		}
	}

	private static boolean checkForSort(String inputCommand) {
		if (inputCommand.equals("sort")){
			return true;
		}
		return false;
	}

	private static boolean checkForDisplay(String inputCommand) {		
		if (inputCommand.equals("display")) {
			return true;
		}
		return false;
	}

	private static boolean checkForExit(String inputCommand) {
		if (inputCommand.equals("exit")) {
			return true;
		}
		return false;
	}

	private static boolean checkForClear(String inputCommand) {
		if (inputCommand.equals("clear")) {
			return true;
		}
		return false;
	}

	
	public static void add(String content) {
		int size = text.size();
		String toBeAdded = size + 1 + "." + content;
		text.add(toBeAdded);

		System.out.println("added to " + dataFileName + '"' + content + '"');
	}

	public static void display(ArrayList<String> text) {
		int size = text.size();
		if (size == 0) {
			System.out.println(dataFileName + " is empty");
		}
		for (int i = 0; i < size; i++) {
			System.out.println(text.get(i));
		}
	}

	public static void delete(String content) {
		int line = Integer.parseInt(content);
		try{
			System.out.println("deleted from " + dataFileName + '"'
				+ text.get(line - 1) + '"');
			text.remove(line - 1);
		}catch(IndexOutOfBoundsException e){
			printError();
		}
	}

	//assumption: File is allowed to be cleared even if it is empty
	public static void clear() {
		text.removeAll(text);
		System.out.println("all content deleted from " + dataFileName);
	}
	
	//assumption: user is to be informed if command entered is invalid.
	private static void printError(){
		System.out.println("ERROR: Invalid command");
	}

	
	private static void localDataFromBuffer(BufferedReader bufferReader) {
		try {
			for (String userInput = bufferReader.readLine(); userInput != null; userInput = bufferReader
					.readLine()) {
				listTextLoadedFromFile.add(userInput);
			}
		} catch (IOException e) {
			String cannotLoad = "Error: Unable to load file " + dataFileName
					+ "!";
			System.out.println(cannotLoad);
		}

	}

	private static void saveDataIntoFile() {
		try {
			FileWriter fWriter = new FileWriter(dataFileName);
			saveData(fWriter);
			fWriter.close();
		} catch (IOException e) {
			String cannotSave = "Error: Unable to save data into "
					+ dataFileName + "!";
			System.out.println(cannotSave);
		}
	}

	private static void saveData(FileWriter fileWriter) {
		try {
			for (int i = 0; i < listTextLoadedFromFile.size(); i++) {
				fileWriter.write((new StringBuilder(String
						.valueOf((String) listTextLoadedFromFile.get(i))))
						.append("\n").toString());
			}

		} catch (Exception e) {
			String cannotSave = "Error: Unable to save data into "
					+ dataFileName + "!";
			System.out.println(cannotSave);
		}
	}



	public static int getBufferSize() {	
		return TextBuddy.text.size();
	}

}
