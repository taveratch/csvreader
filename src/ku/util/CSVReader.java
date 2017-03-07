package ku.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CSVReader, read the world.
 * @author Taweerat Chaiman
 *
 */
public class CSVReader implements Iterator<String[]>{
	
	private char delimiter = ',';
	private BufferedReader buffer;
	private String currentLine = "";
	private final String URLPATTERN = "^\\w\\w+://\\S+";
	
	/**
	 * Constructor. accepting InputStream
	 * @param in as InputStream.
	 * @throws IOException 
	 * 
	 */
	public CSVReader(InputStream in) throws IOException {
		openReader(in);
	}
	
	/**
	 * Constructor. Accepting file name.
	 * @param name as file name in String.
	 * @throws IOException 
	 */
	public CSVReader(String name) throws IOException {
		if(name.matches(URLPATTERN)) {
			URL url = new URL(name);
			openReader(url.openStream());
		}else {
			openReader(new FileInputStream("src/" + name));
		}
	}
	
	/**
	 * Create bufferedReader and read the first line.
	 * @param in as InputStream
	 */
	public void openReader(InputStream in) {
		InputStreamReader reader = new InputStreamReader(in);
		this.buffer = new BufferedReader(reader);
		next();
	}
	
	/**
	 * Close the input source.
	 * @throws IOException 
	 */
	public void close() throws IOException {
		this.buffer.close();
	}
	
	/**
	 * Get current delimiter.
	 * @return current delimiter.
	 */
	public char getDelimiter() {
		return this.delimiter;
	}
	
	/**
	 * Set the delimiter.
	 * @param delimiter as new delimiter.
	 */
	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}
	
	/**
	 * Check whether input has the next element or not.
	 * @return true if there is existing next element. Otherwise, false.
	 */
	public boolean hasNext() {
		return this.currentLine != null;
	}
	
	/**
	 * Get the next row of data.
	 * @return the array of next row.
	 */
	public String[] next() {
		try {
			if(hasNext()) {
				String[] temp = this.currentLine.split(this.delimiter + ""); 
				this.currentLine = this.buffer.readLine().trim();
				return temp;
			}else {
				throw new NoSuchElementException();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Do nothing.
	 */
	public void remove() {
		
	}
}
