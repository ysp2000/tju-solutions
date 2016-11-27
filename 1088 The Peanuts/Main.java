import java.io.*;
import java.util.*;
import static java.lang.Math.*;
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
	
	int tNum;
	Target[] targets = new Target [2500];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(System.out);
		out = new PrintWriter("output.txt");
		
		for (int T = nextInt(), t = 0; t < T; t++) {
			tNum = 0;
			int N = nextInt();
			int M = nextInt();
			int K = nextInt();
			for (int x = 1; x <= N; x++)
				for (int y = 1; y <= M; y++) {
					int val = nextInt();
					if (val > 0)
						add(x, y, val);
				}
			
			if (tNum == 0) {
				out.println(0);
				continue;
			}
			
			sort(targets, 0, tNum);
			int rest = K;
			int cx = 0;
			int cy = targets[0].y;
			int ans = 0;
			
			for (int i = 0; i < tNum; i++) {
				Target tt = targets[i];
				int dst = abs(tt.x - cx) + abs(tt.y - cy);
				if (dst + 1 + tt.x <= rest) {
					rest -= dst;
					cx = tt.x;
					cy = tt.y;
				} else
					break;
				rest--;
				ans += tt.val;
			}
			out.println(ans);
		}
		
		out.close();
	}
	
	void add(int x, int y, int val) {
		if (targets[tNum] == null)
			targets[tNum] = new Target();
		targets[tNum].x = x;
		targets[tNum].y = y;
		targets[tNum].val = val;
		tNum++;
	}
	
	class Target implements Comparable<Target> {
		int x, y, val;

		@Override
		public int compareTo(Target o) {
			return o.val - val;
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
