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

	int MAXV = 100;
	Tree tree1 = new Tree();
	Tree tree2 = new Tree();
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		mainLoop: for (int T = Integer.parseInt(in.readLine()), t = 0; t < T; t++) {
			tree1.clear();
			tree1.parse((in.readLine() + "$").toCharArray());
			tree2.clear();
			tree2.parse((in.readLine() + "$").toCharArray());
			for (int i = 0; i < tree2.vNum; i++) {
				if (equal(i)) {
					tick++;
					out.println("same");
					continue mainLoop;
				}
			}
			out.println("different");
		}
		out.close();
	}
	
	boolean equal(int v) {
		if (tree1.vNum != tree2.vNum)
			return false;
		if (tree1.cNum[tree1.root] > 0) {
			for (int i = 0; i < tree1.cNum[tree1.root]; i++) {
				tick++;
				if (eqDfs(tree1.root, v, -1, -1, i))
					return true;
			}
			return false;
		}
		return tree1.label[tree1.root] == tree2.label[tree2.root];
	}
	
	int tick = 1;
	int[] used = new int [MAXV];
	
	boolean eqDfs(int v1, int v2, int o1, int o2, int o3) {
		used[v1] = tick;
		if (tree1.label[v1] != tree2.label[v2] || tree1.cNum[v1] != tree2.cNum[v2])
			return false;
		int cn = tree1.cNum[v1];
		int[] ch1 = tree1.children[v1];
		int[] ch2 = tree2.children[v2];
		int[][] m1 = tree1.mat;
		int[][] m2 = tree2.mat;
		for (int i = 1; (o1 == -1) ? (i <= cn) : (i < cn); i++) {
			int c1 = ch1[(i + o1) % cn];
			int c2 = ch2[(i + o2 + o3) % cn];
			if (used[c1] != tick && !eqDfs(c1, c2, m1[c1][v1], m2[c2][v2], 0))
				return false;
		}
		return true;
	}
	
	class Tree {
		int vNum;
		int root;
		char[] label = new char [MAXV];
		int[][] mat = new int [MAXV][MAXV];
		int[] cNum = new int [MAXV];
		int[][] children = new int [MAXV][MAXV];
		
		void clear() {
			vNum = 0;
			root = -1;
			for (int i = 0; i < MAXV; i++)
				fill(mat[i], -1);
			this.clearTree();
		}
		
		void clearTree() {
			fill(cNum, 0);
		}
		
		int pos;
		char[] seq;
		
		void parse(char[] seq) {
			this.seq = seq;
			pos = 0;
			vNum = 0;
			parse();
		}
		
		void parse() {
			if (root == -1)
				root = vNum;
			int v = vNum;
			label[vNum++] = seq[pos++];
			if (seq[pos] == '(') {
				pos++;
				while (seq[pos] != ')') {
					if (seq[pos] == ',')
						pos++;
					add(v, vNum);
					parse();
				}
				pos++;
			}
		}

		void add(int u, int v) {
			children[u][cNum[u]] = v;
			children[v][cNum[v]] = u;
			mat[u][v] = cNum[u]++;
			mat[v][u] = cNum[v]++;
		}
	}
}
