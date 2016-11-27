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
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		try {
			for (;;) {
				int fp = 0;
				int sp = 0;
				for (int i = 0; i < 3; i++)
					fp += getScore(nextDouble(), nextDouble());
				for (int i = 0; i < 3; i++)
					sp += getScore(nextDouble(), nextDouble());
				out.println("SCORE: " + fp + " to " + sp + ", " + (fp == sp ? "TIE." : ("PLAYER " + (fp > sp ? "1" : "2") + " WINS.")));
			}
		} catch (Exception e) {
		}
		
		out.close();
	}
	
	double[] r = { 3, 6, 9, 12, 15 };
	double[] rsqr = { 9, 36, 81, 144, 225 };
	int[] score = { 100, 80, 60, 40, 20 };
	
	int getScore(double x, double y) {
		if (x == -100)
			throw new RuntimeException();
		double dsqr = x * x + y * y;
		for (int i = 0; i < rsqr.length; i++)
			if (dsqr <= rsqr[i])
				return score[i];
		return 0;
	}

	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}
