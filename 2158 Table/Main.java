import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.*;

import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("1.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

//	char VERTICAL = 179;
//	char HORIZONTAL = 196;
//	char CENTRAL = 197;
//	char NORTH = 194;
//	char NORTH_EAST = 191;
//	char EAST = 180;
//	char SOUTH_EAST = 217;
//	char SOUTH = 193;
//	char SOUTH_WEST = 192;
//	char WEST = 195;
//	char NORTH_WEAST = 218;
	
	char VERTICAL = '³';
	char HORIZONTAL = 'Ä';
	char CENTRAL = 'Å';
	char NORTH = 'Â';
	char NORTH_EAST = '¿';
	char EAST = '´';
	char SOUTH_EAST = 'Ù';
	char SOUTH = 'Á';
	char SOUTH_WEST = 'À';
	char WEST = 'Ã';
	char NORTH_WEAST = 'Ú';
	
	String DELIM = "" + VERTICAL + HORIZONTAL + CENTRAL + NORTH + NORTH_EAST + EAST + SOUTH_EAST + SOUTH + SOUTH_WEST + WEST + NORTH_WEAST;
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "windows-1251"));
		in.readLine();
		while (in.ready()) {
			
		}
	}


	class Cell {
		List<String> lines = new ArrayList<String>();
		int width = 0;
		
		void add(String s) {
			lines.add(s);
			width = Math.max(width, s.length());
		}
	}
}
