import java.io.*;
import java.util.*;

/* Asia - Tehran 2001 */
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
	
	int sNum;
	int fNum;
	int[] region;
	int[] capacity;
	TreeSet[] FDU;
	Student[] students;
	Student[] ref;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int TESTNUM = nextInt();
		
		for (int test = 0; test < TESTNUM; test++) {
			sNum = nextInt();
			fNum = nextInt();
			students = new Student [sNum];
			ref = new Student [sNum];
			
			for (int i = 0; i < sNum; i++) {
				int r = nextInt();
				int s = nextInt();
				int sz = nextInt();
				ref[i] = students[i] = new Student(r, s, sz);
				
				for (int j = 0; j < sz; j++) {
					students[i].list[j] = nextInt();
				}
			}
			
			region = new int [fNum + 1];
			capacity = new int [fNum + 1];
			FDU = new TreeSet [fNum + 1];
			
			region[0] = -1321387;
			capacity[0] = 1000000;
			FDU[0] = new TreeSet<Student>();
			
			for (int i = 1; i <= fNum; i++) {
				region[i] = nextInt();
				capacity[i] = nextInt();
				FDU[i] = new TreeSet<Student>();
			}
			
			while (sNum > 0) {
				Student cs = students[--sNum];
				int cf = cs.nextFDU();
				FDU[cf].add(cs);
				
				if (FDU[cf].size() > capacity[cf]) {
					cs = (Student) FDU[cf].pollFirst();
					students[sNum++] = cs;
				}
			}
			
			for (int i = 0; i < ref.length; i++) {
				out.println(ref[i].FDU == 0 ? "not accepted" : ref[i].FDU);
			}
			
			if (test < TESTNUM - 1) {
				out.println();
			}
		}
		
		out.close();
	}
	
	class Student implements Comparable<Student> {
		int sRegion;
		int FDU;
		int score;
		int cp;
		int lsz;
		int[] list;
		
		Student(int region, int score, int lsz) {
			this.sRegion = region;
			this.score = score;
			this.lsz = lsz;
			cp = 0;
			list = new int [lsz + 1];
		}

		int nextFDU() {
			return FDU = list[cp++];
		}
		
		boolean isLocal() {
			return sRegion == region[FDU];
		}
		
		@Override
		public int compareTo(Student o) {
			if (this.isLocal() && !o.isLocal()) {
				return 10 * score > 7 * o.score ? 1 : -1;
			} else if (!this.isLocal() && o.isLocal()) {
				return 7 * score < 10 * o.score ? -1 : 1;
			}
			
			return score < o.score ? -1 : 1;
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
