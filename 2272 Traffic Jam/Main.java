import java.io.*;
import java.util.*;
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
	
	int vNum;
	int[] len = new int [10];
	int[] row = new int [10];
	int[] col = new int [10];
	int[] dir = new int [10]; // 1 = vertical, 0 = horizontal
	int[] dr = { 0, 1 };
	int[] dc = { 1, 0 };
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int test = 1;
		int[][] map = new int [6][6];
		
		for (vNum = nextInt(); vNum > 0; vNum = nextInt()) {
			for (int i = 0; i < 6; i++) {
				char[] row = nextLine().toCharArray();
				
				for (int j = 0; j < 6; j++) {
					char c = row[j];
					map[i][j] = (c == '.' ? -1 : (c == 'x' ? vNum - 1 : c - 'a'));
				}
				
//				System.out.println(Arrays.toString(map[i]));
			}
			
//			System.out.println();
			
			for (int v = 0; v < vNum; v++) {
				read(v, map);
			}
			
//			System.out.println(Arrays.toString(len));
//			System.out.println(Arrays.toString(row));
//			System.out.println(Arrays.toString(col));
//			System.out.println(Arrays.toString(dir));
			int[] pos = new int [vNum];
			
			for (int v = 0; v < vNum; v++) {
				pos[v] = dir[v] == 1 ? row[v] : col[v];
			}
			
			short ans = bfs(getNum(pos));
			
			if (ans != -1) {
				out.println("Scenario #" + test++ + " requires " + ans + " moves.");
			} else {
				out.println("You are trapped in scenario #" + test++ + ".");
			}
		}
		
		out.close();
	}

	void read(int v, int[][] map) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (map[i][j] == v) {
					row[v] = i;
					col[v] = j;
					len[v] = 2;
					dir[v] = 0;
					
					if (j == 5 || (i < 5 && map[i + 1][j] == map[i][j])) {
						dir[v] = 1;
						
						if (i < 4 && map[i + 2][j] == map[i][j]) {
							len[v] = 3;
						}
					} else if (j < 4 && map[i][j + 2] == map[i][j]) {
						len[v] = 3;
					}
					
					return;
				}
			}
		}
	}
	
	int getNum(int[] pos) {
		int res = 0;
		int pow = 1;
		
		for (int i = 0; i < vNum; i++) {
			res += pow * pos[i];
			pow *= 5;
		}
		
		return res;
	}
	
	int MAXS = 9765625;
	int MAXQ = 100000;
	int[] q = new int [MAXQ];
	int qSz;
	int qP;
	short tick = 1;
	short[] used = new short [MAXS];
	short[] step = new short [MAXS];
	int[][] cMap = new int [6][6];
	int[] cPos = new int [10];
	
	short bfs(int baseState) {
		tick++;
		used[baseState] = tick;
		step[baseState] = 0;
		qSz = 0;
		qP = 0;
		q[qSz++] = baseState;
		
		while (qP != qSz) {
			int state = q[qP++];
			
			if (qP == MAXQ) {
				qP = 0;
			}
			
			getPositions(state);
			
			if (cPos[vNum - 1] == 4) {
				return step[state];
			}
			
			buildMap();
			
//			for (int i = 0; i < 6; i++) {
//				for (int j = 0; j < 6; j++) {
//					int c = cMap[i][j];
//					
//					System.out.print(c == -1 ? ' ' : (c == vNum - 1 ? 'x' : ((char) (c + 'a'))));
//				}
//				
//				System.out.println();
//			}
//			
//			System.out.println("--------------");
			
			for (int v = 0; v < vNum; v++) {
				int old = cPos[v];
				
				while (true) {
					cPos[v]++;
					int nc = cPos[v] + len[v] - 1;
					
					if (nc < 6 && (dir[v] == 1 ? cMap[nc][col[v]] == -1 : cMap[row[v]][nc] == -1)) {
						relax(getNum(cPos), step[state]);
					} else {
						break;
					}
				}
				
				cPos[v] = old;
				
				while (true) {
					cPos[v]--;
					
					if (cPos[v] >= 0 && (dir[v] == 1 ? cMap[cPos[v]][col[v]] == -1 : cMap[row[v]][cPos[v]] == -1)) {
						relax(getNum(cPos), step[state]);
					} else {
						break;
					}
				}
				
				cPos[v] = old;
			}
		}
		
		return -1;
	}
	
	void relax(int state, short steps) {
		if (used[state] != tick) {
			used[state] = tick;
			q[qSz++] = state;
			step[state] = (short) (1 + steps);
			
			if (qSz == MAXQ) {
				qSz = 0;
			}
		}
	}

	void getPositions(int state) {
		for (int i = 0; i < vNum; i++) {
			cPos[i] = state % 5;
			state /= 5;
		}
	}
	
	void buildMap() {
		for (int i = 0; i < 6; i++) {
			fill(cMap[i], -1);
		}
		
		for (int v = 0; v < vNum; v++) {
			int d = dir[v];
			int cr = d == 1 ? cPos[v] : row[v];
			int cc = d == 1 ? col[v] : cPos[v];
			
			for (int i = 0; i < len[v]; i++) {
				cMap[cr + i * dr[d]][cc + i * dc[d]] = v;
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
	
	String nextLine() throws IOException {
		st = new StringTokenizer("");
		return in.readLine();
	}
}
