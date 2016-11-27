import java.io.*;
import java.util.*;
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
	
	int[] f = new int [10];
	int[] r = new int [10];
	QNum[] q = new QNum [100];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int n = nextInt(); n > 0; n = nextInt()) {
			int m = nextInt();
			for (int i = 0; i < n; i++)
				f[i] = nextInt();
			for (int i = 0; i < m; i++)
				r[i] = nextInt();
			int sz = 0;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					q[sz++] = new QNum(r[j], f[i]);
			if (sz <= 1) {
				out.println("0.00");
			} else {
				sort(q, 0, sz);
				QNum ans = new QNum(0, 1);
				for (int i = 1; i < sz; i++)
					ans = max(ans, q[i].div(q[i - 1]));
				out.println(ans);
			}
		}
		
		out.close();
	}
	
	QNum max(QNum a, QNum b) {
		return a.compareTo(b) <= 0 ? b : a;
	}
	
	class QNum implements Comparable<QNum> {
		int num;
		int den;
		
		public QNum(int num, int den) {
			this.num = num;
			this.den = den;
		}
		
		QNum div(QNum q) {
			return new QNum(num * q.den, den * q.num);
		}
		
		@Override
		public int compareTo(QNum q) {
			return num * q.den - q.num * den;
		}
		
		@Override
		public String toString() {
			return String.format(Locale.US, "%.2f", num / (double) den);
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
