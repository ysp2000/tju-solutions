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
	
	int pNum;
	int[] px = new int [20];
	int[] py = new int [20];
	int[] ph = new int [20];
	int[] pl = new int [20];
	int lNum;
	int[] lx = new int [50];
	int[] ly = new int [50];
	int[] ll = new int [50];
	int dp;
	int dl;
	int[][] graph = new int [20][20];
	int[] set = new int [20];
	boolean[] used = new boolean [20];

	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int TESTNUM = nextInt();
		
		for (int test = 0; test < TESTNUM; test++) {
			pNum = nextInt();
			
			for (int i = 0; i < pNum; i++) {
				px[i] = nextInt();
				py[i] = nextInt();
				ph[i] = nextInt();
			}
			
			lNum = nextInt();
			
			for (int i = 0; i < lNum; i++) {
				lx[i] = nextInt();
				ly[i] = nextInt();
				ll[i] = nextInt();
			}
			
			dp = nextInt() - 1;
			dl = nextInt();
			
			/* build graph */
			for (int i = 0; i < pNum; i++) {
				fill(graph[i], 0, pNum, -1);
			}
			
			for (int l = 0; l < lNum; l++) {
				int clx = lx[l];
				int cly = ly[l];
				int cll = ll[l];
				
				int i = -1;
				int j = -1;
				
				for (i = 0; i < pNum; i++) {
					if (px[i] + 1 == clx && py[i] <= cly && cly <= py[i] + ph[i]) {
						break;
					}
				}
				
				for (j = 0; j < pNum; j++) {
					if (clx + cll == px[j] && py[j] <= cly && cly <= py[j] + ph[j]) {
						break;
					}
				}
				
				graph[i][j] = graph[j][i] = max(graph[i][j], cly);
			}
			
			fill(pl, 0, pNum, 0);
			fill(used, 0, pNum, false);
			int sz = 0;
			set[sz++] = 0;
			used[0] = true;
			int ct = 0;
			boolean found = false;
			boolean work = true;
			int lowestTop = py[0];
			
			if (dl <= py[dp] + ph[dp]) {
				while (work) {
					int lowestLink = -1;
					
					for (int i = 0; i < sz; i++) {
						int cp = set[i];
						
						for (int j = 0; j < pNum; j++) {
							if (lowestLink < graph[cp][j] && graph[cp][j] <= py[cp] + ph[cp] - pl[cp]) {
								lowestLink = graph[cp][j];
							}
						}
					}
	
					if (lowestLink < dl && used[dp]) {
						work = false;
						
						if (dl > lowestTop) {
							found = true;
							
							for (int i = 0; i < sz; i++) {
								int cp = set[i];
								
								if (py[cp] + ph[cp] - pl[cp] > dl) {
									ct += py[cp] + ph[cp] - pl[cp] - dl;
								}
							}
						}
					} else {
						int csz = sz;
						
						for (int i = 0; i < csz; i++) {
							int cp = set[i];
							
							if (py[cp] + ph[cp] - pl[cp] > lowestLink) {
								int delta = py[cp] + ph[cp] - pl[cp] - lowestLink;
								ct += delta;
								pl[cp] += delta;
							}
							
							for (int j = 0; j < pNum; j++) {
								if (graph[cp][j] == lowestLink) {
									graph[cp][j] = graph[j][cp] = -1;
									
									if (!used[j]) {
										set[sz++] = j;
										used[j] = true;
										lowestTop = max(lowestTop, py[j]);
									}
								}
							}
						}
					}
					
					if (lowestLink < lowestTop) {
						work = false;
					}
				}
			}
			
			out.println(found ? ct : "No Solution");
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
