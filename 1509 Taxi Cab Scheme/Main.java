import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;

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
	
	int MAXV = 500;
	int vNum;
	int[] begin = new int [MAXV];
	int[] end = new int [MAXV];
	int[] x1 = new int [MAXV];
	int[] y1 = new int [MAXV];
	int[] x2 = new int [MAXV];
	int[] y2 = new int [MAXV];
	int[] xm = new int [MAXV];
	int[] ym = new int [MAXV];
	int[] tm = new int [MAXV];
	int tick = 1;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int TESTNUM = nextInt();
		
		for (int test = 1; test <= TESTNUM; test++) {
			vNum = nextInt();
			
			for (int i = 0; i < vNum; i++) {
				begin[i] = getTime(nextToken());
				x1[i] = nextInt();
				y1[i] = nextInt();
				x2[i] = nextInt();
				y2[i] = nextInt();
				end[i] = begin[i] + abs(x2[i] - x1[i]) + abs(y2[i] - y1[i]);
			}
			
			fill(xm, 0, vNum, -1);
			fill(ym, 0, vNum, -1);
//			fill(tm, 0, vNum, 0);
//			tick = 1;
			
			/* Max matching */
			for (int i = 0; i < vNum; i++) {
				if (xm[i] == -1) {
					proc(i);
					tick++;
				}
			}
			
			int mm = 0;
			
			for (int i = 0; i < vNum; i++) {
				if (xm[i] != -1) {
					mm++;
				}
			}
			
			out.println(vNum - mm);
		}
		
		
		out.close();
	}
	
	boolean proc(int x) {
		if (tm[x] == tick) {
			return false;
		}
		
		tm[x] = tick;
		
		for (int y = 0; y < vNum; y++) {
			if (end[x] + abs(x2[x] - x1[y]) + abs(y2[x] - y1[y]) < begin[y] && (ym[y] == -1 || proc(ym[y]))) {
				xm[x] = y;
				ym[y] = x;
				return true;
			}
		}
		
		return false;
	}

	int getTime(String s) {
		StringTokenizer tok = new StringTokenizer(s, ":");
		return Integer.parseInt(tok.nextToken()) * 60 + Integer.parseInt(tok.nextToken());
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
