import java.io.*;
import java.util.*;
import static java.util.Arrays.fill;

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
	
	int[] state = new int [10000];
	int[] digs = new int [10000 + 1];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		loop: for (;;) {
			fill(state, -1);
			
			int x = nextInt();
			int y = nextInt();
			
			if (x == 0)
				break;
			
			int a = nextInt();
			int b = nextInt();
			int n = nextInt();
			
			int dsz = 0;
			digs[dsz++] = a;
			digs[dsz++] = b;
			
			int cs = 100 * a + b;
			int cnt = 1;
			state[cs] = cnt++;
			
			int u = 0;
			int v = 0;
			
			for (;;) {
				int c = (y * a + x * b) % 100;
				digs[dsz++] = c;
				
				if (dsz > n) {
					out.println(s(digs[n]));
//					System.out.println(Arrays.toString(Arrays.copyOf(digs, dsz)));
					continue loop;
				}
				
				a = b;
				b = c;
				cs = 100 * a + b;
				
				if (state[cs] < 0) {
					state[cs] = cnt++;
				} else {
					u = state[cs] + 1;
					v = cnt - state[cs];
					break;
				}
			}
			
//			System.out.println("u = " + u + " v = " + v);
//			System.out.println(Arrays.toString(Arrays.copyOf(digs, dsz)));
			out.println(s(digs[u + (n - u) % v]));			
		}
		
		out.close();
	}
	
	String s(int x) {
		return (x < 10 ? "0" : "") + x;
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
