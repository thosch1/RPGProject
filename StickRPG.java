import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StickRPG {
	static Scanner inputFile;
	static PrintWriter outputFile;
	static String saveFileName = "save.txt";
	public int[] playerData = new int[6];
	
	public StickRPG(String saveFileName) throws FileNotFoundException, IOException{
		this.saveFileName = saveFileName;
		File file = new File(saveFileName);
		inputFile = new Scanner(file);
		outputFile = new PrintWriter(new BufferedWriter(new FileWriter(saveFileName)));
	}
	
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	private void readSave() {
		int count = 1, intLine = 0;
		String stringLine;
		while(inputFile.hasNext()) {
			System.out.println("here1");
			stringLine = inputFile.next();
			try {
				intLine = Integer.parseInt(stringLine);
			} catch (NumberFormatException nfe) {
				System.out.println("Error: Line " + count + " must be an integer.");
				System.exit(1);
			}
			playerData[count-1] = intLine;
			count++;
		}
	}
	
	private void resetSave() {
		for (int i = 0; i < playerData.length; i++) {
			outputFile.write(0 + "\n");
		}
		outputFile.close();
	}
	
	public static void main(String[] args) {
		//test
		StickRPG s = null;
		try {
			s = new StickRPG("save.txt");
		} catch (FileNotFoundException fnfe) {
			System.out.println("Input file not found.");
			System.exit(1);
		} catch (IOException ioe) {
			System.out.println("Couldn't open file for output.");
			System.exit(1);
		}
		s.readSave();
		for (int i = 0; i < s.playerData.length; i++) {
			System.out.println(s.playerData[i]);
		}
		inputFile.close();
	}
}
