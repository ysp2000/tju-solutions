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
	int vNum;
	short[][] cf = new short [502][502];
	short[][] f = new short [502][502];
	boolean[] boy = new boolean [502];
	int[] height = new int [502];
	int[] music = new int [502];
	int[] sport = new int [502];
	int[] used = new int [502];
	int tick = 1;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(), t = 0; t < T; t++) {
			pNum = nextInt();
			vNum = pNum + 2;
			
			for (int i = 0; i < pNum; i++) {
				height[i] = nextInt();
				boy[i] = nextToken().charAt(0) == 'M';
				music[i] = nextToken().hashCode();
				sport[i] = nextToken().hashCode();
			}
			
			for (int i = 0; i < vNum; i++) {
				fill(cf[i], 0, vNum, (short) 0);
				fill(f[i], 0, vNum, (short) 0);
			}
			
			for (int i = 0; i < pNum; i++) {
				if (boy[i]) {
					cf[0][i + 1] = 1;
					
					for (int j = 0; j < pNum; j++) {
						if (!boy[j] && abs(height[i] - height[j]) <= 40 && music[i] == music[j] && sport[i] != sport[j]) {
							cf[i + 1][j + 1] = 1;
						}
					}
				} else {
					cf[i + 1][vNum - 1] = 1;
				}
			}
			
			tick++;
			
			while (pathFound(0)) {
				tick++;
			}
			
			tick++;
			dfs(0);
			
			short ans = 0;

			for (int i = 1; i < vNum - 1; i++) {
				if (boy[i - 1] && used[i] == tick || !boy[i - 1] && used[i] != tick) {
					ans++;
				}
			}
			
			out.println(ans);
		}
		
		out.close();
	}
	
	boolean pathFound(int v) {
		if (v == vNum - 1) {
			return true;
		}
		
		used[v] = tick;
		
		for (int i = 0; i < vNum; i++) {
			if (used[i] != tick && cf[v][i] > f[v][i] && pathFound(i)) {
				f[v][i]++;
				f[i][v]--;
				return true;
			}
		}
		
		return false;
	}
	
	void dfs(int v) {
		used[v] = tick;
		
		for (int i = 0; i < vNum; i++) {
			if (used[i] != tick && cf[v][i] > f[v][i]) {
				dfs(i);
			}
		}
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
