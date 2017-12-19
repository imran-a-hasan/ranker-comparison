
/* Imran Hasan
8/29/14
Period 3
OpenFile.java

This class will allow us to easily open text files
both for reading from and writing to. Client classes
will use the static methods.
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OpenFile {
	public static void main (String [] args) {
		System.out.println("\n\n");
		Scanner inFile = OpenFile.openToRead("g.txt");
		PrintWriter outFile = OpenFile.openToWrite("g2.txt");
		String temp = null;
		while(inFile.hasNext()) {
			temp = inFile.nextLine();
			System.out.println(temp);
			outFile.println(temp);
		}
		inFile.close();
		outFile.close();
		System.out.println("\n\n");
	}

	public static Scanner openToRead (String fileString) {
		Scanner fromFile = null;
		File fileName = new File(fileString);
		try {
			fromFile = new Scanner(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("\n\nSorry, but the file could not be found.\n\n");
			System.exit(1);
		}
		return fromFile;
	}

	public static PrintWriter openToWrite (String fileString) {
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(fileString);
		} catch (Exception e) {
			System.out.println("\n\nSorry, but the file could not be created.\n\n");
			System.exit(1);
		}
		return outFile;
	}
}
