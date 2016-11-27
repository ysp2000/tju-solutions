import java.io.*;
import java.util.*;
import static java.util.Arrays.sort;

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
	
	int K;
	int[] S = new int [13];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		boolean blank = false;
		for (K = nextInt(); K > 0; K = nextInt()) {
			if (blank)
				out.println();
			blank = true;
			S = new int [K];
			for (int i = 0; i < K; i++)
				S[i] = nextInt();
			sort(S, 0, K);
			for (int i = 0; i < K; i++)
				for (int j = i + 1; j < K; j++)
					for (int k = j + 1; k < K; k++)
						for (int l = k + 1; l < K; l++)
							for (int m = l + 1; m < K; m++)
								for (int n = m + 1; n < K; n++)
									out.println(S[i] + " " + S[j] + " " + S[k] + " " + S[l] + " " + S[m] + " " + S[n]);
		}
		
		out.close();
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
