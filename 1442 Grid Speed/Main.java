import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;

public class Main {
	double INF = 1e100;
	double EPS = 1e-6;
	
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
	
	int n;
	double gap;
	double[] hv = new double [10];
	double[] vv = new double [10];
	double[] hlim = new double [10];
	double[] vlim = new double [10];
	double[] dt = new double [11];
	double[] df = new double [11];
	double[][][] dp = new double [10 + 1][10 + 1][10000 + 2];
	double[][][] rt = new double [10 + 1][10 + 1][10000 + 2];
	int minT, maxT;
	
	boolean found = false;
	double t1 = INF;
	double f1 = INF;
	double t2 = INF;
	double f2 = INF;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(), t = 1; t <= T; t++) {
			n = nextInt();
			gap = nextInt();
			
			for (int i = 0; i < n; i++)
				hv[i] = nextInt();
			for (int i = 0; i < n; i++)
				vv[i] = nextInt();
			
			int xs = nextInt() - 1;
			int ys = nextInt() - 1;
			int xt = nextInt() - 1;
			int yt = nextInt() - 1;
			int dx = Integer.signum(xt - xs);
			int dy = Integer.signum(yt - ys);
			minT = nextInt();
			maxT = nextInt();
			
			int N = abs(xt - xs);
			int M = abs(yt - ys);
			
			for (int x = xs, i = 0; i <= N; i++) {
				vlim[i] = vv[x];
				x += dx;
			}
			
			for (int y = ys, j = 0; j <= M; j++) {
				hlim[j] = hv[y];
				y += dy;
			}
			
//			System.out.println(Arrays.toString(vlim));
//			System.out.println(Arrays.toString(hlim));
//			System.out.println();
			
			int MT = (int) (maxT * 10) + 1;
			
			for (int i = 0; i <= N; i++)
				for (int j = 0; j <= M; j++)
					fill(dp[i][j], 0, MT, INF);
			
			for (int i = 1; i <= 10; i++) {
				double v = 5.0 * i;
				dt[i] = 600.0 * gap / v;
				df[i] = gap / (80.0 - 0.03 * v * v);
			}
			
//			System.out.println(Arrays.toString(dt));
			dp[0][0][0] = 0.0;
			rt[0][0][0] = 0.0;
			
			for (int x = 0; x <= N; x++) {
				for (int y = 0; y <= M; y++) {
					for (int tm = 0; tm < MT; tm++) {
						if (dp[x][y][tm] < INF) {
							double ct = rt[x][y][tm];
							double cf = dp[x][y][tm];
							int nx = x + 1;
							if (nx <= N) {
								for (int k = 1; k <= 10; k++) {
									if (5 * k > hlim[y])
										break;
									double rtm = ct + dt[k];
									int ntm = (int) (rtm);
									double nf = cf + df[k];
									if (ntm <= MT) {
										dp[nx][y][ntm] = min(dp[nx][y][ntm], nf);
										rt[nx][y][ntm] = rtm;
//										System.out.println(nx + " " + y + " " + ntm + " " + dp[nx][y][ntm]);
									}
								}
							}
							
							int ny = y + 1;
							if (ny <= M) {
								for (int k = 1; k <= 10; k++) {
									if (5 * k > vlim[x])
										break;
									double rtm = ct + dt[k];
									int ntm = (int) (rtm);
									double nf = cf + df[k];
									if (ntm <= MT) {
										dp[x][ny][ntm] = min(dp[x][ny][ntm], nf);
										rt[x][ny][ntm] = rtm;
//										System.out.println(x + " " + ny + " " + ntm + " " + dp[x][ny][ntm]);
									}
								}
							}
						}
					}
				}
			}
			
			found = false;
			t1 = INF;
			f1 = INF;
			t2 = INF;
			f2 = INF;
			
			for (int i = 0; i <= MT; i++) {
				if (dp[N][M][i] != INF)
					update(i / 10.0, dp[N][M][i]);
			}
			
			out.println("Scenario " + t + ":");
			
			if (found) {
				out.printf(Locale.US, "The earliest  arrival: %d minutes, fuel %.2f gallons%n", (int) ceil(t1), f1);
				out.printf(Locale.US, "The economical travel: %d minutes, fuel %.2f gallons%n", (int) ceil(t2), f2);
			} else {
				out.println("IMPOSSIBLE");
			}
		}
		
		out.close();
	}

	void update(double t, double f) {
		if (minT - EPS < t && t < maxT + EPS) {
			found = true;
			
			if (abs(t1 - t) < EPS && f1 > f || t1 > t) {
				t1 = t;
				f1 = f;
			}
			
			if (abs(f2 - f) < EPS && t2 > t || f2 > f) {
				t2 = t;
				f2 = f;
			}
		}
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
