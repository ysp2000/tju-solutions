import java.io.*;
import java.util.*;

// very unoptimized ;)

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
	
	int[] rNum = new int [26];
	int[] cNum = new int [26];
	int k;
	char[] s;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int mNum = nextInt();
		
		for (int i = 0; i < mNum; i++) {
			int m = nextToken().charAt(0) - 'A';
			rNum[m] = nextInt();
			cNum[m] = nextInt();
		}
		
		while (in.ready()) {
			k = 0;
			s = in.readLine().toCharArray();
			Item expr = E();
			try {
				out.println(expr.getInfo().op);
			} catch (BadMatrixException e) {
				out.println("error");
			}
		}
		
		out.close();
	}
	
	Item E() {
		Item res = M();
		if (k < s.length && s[k] != ')')
			res = new Item(res, '*', E());
		return res;
	}

	Item M() {
		char c = s[k++];
		if ('A' <= c && c <= 'Z')
			return new Item(null, c, null);
		Item res = E();
		k++;
		return res;
	}

	class Item {
		char op;
		Item left;
		Item right;
		
		Item(Item left, char op, Item right) {
			this.left = left;
			this.op = op;
			this.right = right;
		}
		
		Info getInfo() throws BadMatrixException {
			if (op == '*') {
				Info l = left.getInfo();
				Info r = right.getInfo();
				if (l.c != r.r)
					throw new BadMatrixException();
				return new Info(l.r, r.c, l.op + r.op + l.r * r.c * l.c);
			}
			return new Info(rNum[op - 'A'], cNum[op - 'A'], 0L);
		}
	}
	
	class Info {
		int r;
		int c;
		long op;
		
		Info(int r, int c, long op) {
			this.r = r;
			this.c = c;
			this.op = op;
		}
	}
	
	class BadMatrixException extends Throwable {
		private static final long serialVersionUID = 837622250521558815L;
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
