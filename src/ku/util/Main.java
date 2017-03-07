package ku.util;

import java.io.IOException;
import java.util.Arrays;

/**
 * Application class.
 * @author Taweerat Chaiman
 *
 */
public class Main {

	/**
	 * Application method.
	 * @param args as arguments
	 * @throws IOException as Exception from Application.
	 */
	public static void main(String[] args) throws IOException {
		CSVReader reader = new CSVReader("students.csv");
		while(true) {
			System.out.println(Arrays.toString(reader.next()));
		}
		
	}

}
