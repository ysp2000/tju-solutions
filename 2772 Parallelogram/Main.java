import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(), t = 0; t < T; t++) {
			int xA = nextInt();
			int yA = nextInt();
			int xC = nextInt();
			int yC = nextInt();
			int xB = nextInt();
			int yB = nextInt();
			int xD = nextInt();
			int yD = nextInt();
			boolean ac = vec(xA - xB, yA - yB, xC - xD, yC - yD) == 0;
			boolean bd = vec(xC - xB, yC - yB, xD - xA, yD - yA) == 0;
			boolean its = vec(xC - xA, yC - yA, xD - xB, yD - yB) != 0;
			boolean ans = ac && bd && its;
			out.println(ans ? "Yes" : "No");
		}
		
		out.close();
	}
	
	int vec(int x1, int y1, int x2, int y2) {
		return x1 * y2 - x2 * y1;
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
	
	String nextLine() throws IOException {
		st = new StringTokenizer("");
		return in.readLine();
	}
	
	boolean EOF() throws IOException {
		while (!st.hasMoreTokens()) {
			String s = in.readLine();
			
			if (s == null) {
				return true;
			}
			
			st = new StringTokenizer(s);
		}
		
		return false;
	}
}
