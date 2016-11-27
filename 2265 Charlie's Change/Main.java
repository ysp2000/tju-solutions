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
		
		for (int P = nextInt(); P > 0; P = nextInt()) {
			int C1 = nextInt();
			int C2 = nextInt();
			int C3 = nextInt();
			int C4 = nextInt();
			
			int best = -1;
			int bc1 = 0;
			int bc2 = 0;
			int bc3 = 0;
			int bc4 = 0;
			
			if (C1 < P) {
				for (int c1 = P % 5; c1 <= C1; c1 += 5) {
					for (int c4 = 0; c4 <= C4; c4++) {
						int rest = P - c1 - 25 * c4;
						
						if (rest < 0) {
							break;
						}

						if (rest % 5 == 0) {
							rest /= 5;
							
							if (C2 < rest) {
								int c3 = -1;
								int c2 = -1;
								
								if (((rest - C2) & 1) == 0) {
									c2 = C2;
									c3 = (rest - C2) >> 1; 
								} else if (C2 > 0) {
									c2 = C2 - 1;
									c3 = (rest - C2 + 1) >> 1;
								}
								
								if (c3 != -1 && c3 <= C3) {
									int cur = c1 + C2 + c3 + c4;
									
									if (best < cur) {
										best = cur;
										bc1 = c1;
										bc2 = c2;
										bc3 = c3;
										bc4 = c4;
									}
								}
							} else {
								int cur = c1 + rest + c4;
								
								if (best < cur) {
									best = cur;
									bc1 = c1;
									bc2 = rest;
									bc3 = 0;
									bc4 = c4;
								}
							}
						}
					}
				}
			} else {
				best = P;
				bc1 = P;
				bc2 = bc3 = bc4 = 0;
			}
			
			if (best == -1) {
				out.println("Charlie cannot buy coffee.");
			} else {
				out.println("Throw in " + bc1 + " cents, " + bc2 + " nickels, " + bc3 + " dimes, and " + bc4 + " quarters.");
			}
		}
		
		out.close();
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
}
