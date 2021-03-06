import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		PrintWriter out = new PrintWriter(System.out);

		for (int n = Integer.parseInt(in.readLine()); n > 0; n = Integer.parseInt(in.readLine())) {
			out.println(Integer.parseInt(new StringBuffer(Integer.toBinaryString(n)).reverse().toString(), 2));
		}
		
		out.close();
	}
}
