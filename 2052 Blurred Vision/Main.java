import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int[][] src = new int [10][10];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		while (nextToken().equals("START")) {
			int H = nextInt();
			int W = nextInt();
			
			for (int i = 0; i < H; i++) {
				char[] row = nextToken().toCharArray();
				for (int j = 0; j < W; j++)
					src[i][j] = row[j] - '0';
			}
			
			for (int i = 1; i < H; i++) {
				for (int j = 1; j < W; j++)
					out.print((src[i - 1][j - 1] + src[i][j - 1] + src[i - 1][j] + src[i][j]) >> 2);
				out.println();
			}
			
			nextToken();
		}
		
		out.close();
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
