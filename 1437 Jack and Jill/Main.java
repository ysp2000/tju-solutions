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

	int MAXN = 30;
	int MASK_SIZE = 5;
	
	char[][] map = new char [MAXN + 2][MAXN + 2];
	int rJ, cJ, rj, cj;
	int rS, cS, rs, cs;
	
	int[] dr = { 0, 1, 0, -1, 0 };
	int[] dc = { 0, 0, 1, 0, -1 };
	String[] dn = { "", "S", "E", "N", "W" };

	/* bit mask */
	int ms0 = 0;
	int ms1 = ms0 + MASK_SIZE;
	int ms2 = ms1 + MASK_SIZE;
	int ms3 = ms2 + MASK_SIZE;
	int m0 = (1 << MASK_SIZE) - 1;
	int m1 = m0 << MASK_SIZE;
	int m2 = m1 << MASK_SIZE;
	int m3 = m2 << MASK_SIZE;
	
	/* extBFS for answer */
	int MAX_STATE_NUM = code(30, 30, 30, 30);
	int MAX_QSIZE = 4000000;
	
	int tick = 1;
	int[] used = new int [MAX_STATE_NUM + 1];
	int[] dist = new int [MAX_STATE_NUM + 1]; // contains step code in BFS
	int[] queue = new int [MAX_QSIZE];
	int[] qdist = new int [MAX_QSIZE];  // contains previous state in BFS
	
	/* BFS */
	int[] step = dist;
	int[] qprev = qdist;
	int[] seq = new int [100];
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int r = 0; r <= MAXN + 1; r++)
			fill(map[r], '*');
		boolean blank = false;
		for (int n = Integer.parseInt(in.readLine()); n > 0; n = Integer.parseInt(in.readLine())) {
			if (blank)
				out.println();
			blank = true;
			for (int r = 1; r <= n; r++) {
				char[] row = in.readLine().toCharArray();
				fill(map[r], 0, n + 2, '*');
				for (int c = 1; c <= n; c++) {
					char ch = map[r][c] = row[c - 1];
					if (ch == 'H') {
						rJ = r;
						cJ = c;
					} else if (ch == 'h') {
						rj = r;
						cj = c;
					} else if (ch == 'S') {
						rS = r;
						cS = c;
					} else if (ch == 's') {
						rs = r;
						cs = c;
					}
				}
			}
			fill(map[n + 1], 0, n + 2, '*');

			int firstState = code(rJ, cJ, rj, cj);
			int firstDist = dist(rJ, cJ, rj, cj);
			
			/* extBFS */
			tick++;
			int qH = 0;
			int qT = 0;
			qdist[qT] = firstDist;
			queue[qT++] = firstState;
			used[firstState] = tick;
			dist[firstState] = firstDist;
			int maxDist = -1;
			
			while (qH != qT) {
				int curState = queue[qH];
				int curDist = qdist[qH];
				if (++qH == MAX_QSIZE)
					qH = 0;
				if (curDist < dist[curState])
					continue;
				decode(curState);
				int mdJ = 1;
				int MdJ = 4;
				int mdj = 1;
				int Mdj = 4;
				
				if (rJ == rS && cJ == cS)
					mdJ = MdJ = 0;
				if (rj == rs && cj == cs)
					mdj = Mdj = 0;
				
				if (mdJ == 0 && mdj == 0) {
					maxDist = max(maxDist, curDist);
					continue;
				}
				
				for (int dJ = mdJ; dJ <= MdJ; dJ++) {
					int nrJ = rJ + dr[dJ];
					int ncJ = cJ + dc[dJ];
					int npJ = map[nrJ][ncJ];
					
					if (npJ == '.' || npJ == 'S' || npJ == 'H') {
//					if (npJ == '.' || npJ == 'S') {
						for (int dj = mdj; dj <= Mdj; dj++) {
							int nrj = rj + dr[dj];
							int ncj = cj + dc[dj];
							int npj = map[nrj][ncj];
							
							if (npj == '.' || npj == 's' || npj == 'h') {
//							if (npj == '.' || npj == 's') {
								int newState = code(nrJ, ncJ, nrj, ncj);
								int newDist = min(curDist, dist(nrJ, ncJ, nrj, ncj));
								
								if (used[newState] != tick) {
									used[newState] = tick;
									dist[newState] = newDist;
									queue[qT] = newState;
									qdist[qT] = newDist;
									if (++qT == MAX_QSIZE)
										qT = 0;
								} else if (dist[newState] < newDist) {
									dist[newState] = newDist;
									if (--qH == -1)
										qH = MAX_QSIZE - 1;
									queue[qH] = newState;
									qdist[qH] = newDist;
								}
							}
						}
					}
				}
			}
			
			/* BFS */
			tick++;
			qH = qT = 0;
			qprev[qT] = -1;
			queue[qT++] = firstState;
			used[firstState] = tick;
			int lastIndex = -1;
			
			while (true) {
				int curState = queue[qH];
				int curIndex = qH++;
				decode(curState);
				
				int mdJ = 1;
				int MdJ = 4;
				int mdj = 1;
				int Mdj = 4;
				
				if (rJ == rS && cJ == cS)
					mdJ = MdJ = 0;
				if (rj == rs && cj == cs)
					mdj = Mdj = 0;
				
				if (mdJ == 0 && mdj == 0) {
					lastIndex = curIndex;
					break;
				}
				
				for (int dJ = mdJ; dJ <= MdJ; dJ++) {
					int nrJ = rJ + dr[dJ];
					int ncJ = cJ + dc[dJ];
					int npJ = map[nrJ][ncJ];
					
					if (npJ == '.' || npJ == 'S' || npJ == 'H') {
//					if (npJ == '.' || npJ == 'S') {
						for (int dj = mdj; dj <= Mdj; dj++) {
							int nrj = rj + dr[dj];
							int ncj = cj + dc[dj];
							int npj = map[nrj][ncj];
							
							if (npj == '.' || npj == 's' || npj == 'h') {
//							if (npj == '.' || npj == 's') {
								int newState = code(nrJ, ncJ, nrj, ncj);
								int newDist = dist(nrJ, ncJ, nrj, ncj);
								
								if (newDist >= maxDist && used[newState] != tick) {
									used[newState] = tick;
									queue[qT] = newState;
									qprev[qT] = curIndex;
									step[qT] = stepCode(dJ, dj);
									if (++qT == MAX_QSIZE)
										qT = 0;
								}
							}
						}
					}
				}
			}
			
			int sz = 0;
			for (int i = lastIndex; qprev[i] != -1; i = qprev[i])
				seq[sz++] = step[i];
			out.printf(Locale.US, "%.2f%n", sqrt(maxDist));
			for (int i = sz - 1; i >= 0; i--)
				out.print(dn[(seq[i] & m0) >> ms0]);
			out.println();
			for (int i = sz - 1; i >= 0; i--)
				out.print(dn[(seq[i] & m1) >> ms1]);
			out.println();
		}
		
		out.close();
	}
	
	int dist(int rJ, int cJ, int rj, int cj) {
		return (rJ - rj) * (rJ - rj) + (cJ - cj) * (cJ - cj);
	}
	
	int code(int rJ, int cJ, int rj, int cj) {
		return ((rJ - 1) << ms0) | ((cJ - 1) << ms1) | ((rj - 1) << ms2) | ((cj - 1) << ms3);
	}
	
	void decode(int code) {
		rJ = ((code & m0) >> ms0) + 1;
		cJ = ((code & m1) >> ms1) + 1;
		rj = ((code & m2) >> ms2) + 1;
		cj = ((code & m3) >> ms3) + 1;
	}
	
	int stepCode(int dJ, int dj) {
		return (dJ << ms0) | (dj << ms1);
	}
}
