import java.io.*;
import java.math.BigInteger;

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

	int MAX = 64 + 1;
	BigInteger[] pow2 = new BigInteger [MAX];
	BigInteger[] pow3 = new BigInteger [MAX];
	BigInteger ONE = BigInteger.ONE;
	BigInteger TWO = BigInteger.valueOf(2);
	BigInteger THREE = BigInteger.valueOf(3);
	int[] ans = new int [MAX];
	
	void run() throws IOException {
		pow2[0] = ONE;
		pow3[0] = ONE;
		
		for (int i = 1; i < MAX; i++) {
			pow2[i] = pow2[i - 1].multiply(TWO);
			pow3[i] = pow3[i - 1].multiply(THREE);
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		for (String s = in.readLine(); s.charAt(0) != '0'; s = in.readLine()) {
			BigInteger num = new BigInteger(s);
			int sz = 0;
			for (int k = MAX - 1; k >= 0; k--)
				if (num.compareTo(pow2[k]) > 0) {
					ans[sz++] = k;
					num = num.subtract(pow2[k]);
				}
			out.print("{");
			for (int i = sz - 1; i >= 0; i--) {
				if (i < sz - 1)
					out.print(',');
				out.print(' ');
				out.print(pow3[ans[i]]);
			}
			out.print(" }");
			out.println();
		}
		
		out.close();
	}
}
