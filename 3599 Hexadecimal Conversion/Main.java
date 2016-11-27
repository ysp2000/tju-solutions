import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		if (str.equals("0")) { System.out.println('0'); return; }
		char[] num = str.toCharArray();
		int hsz = num.length;
		int osz = (hsz * 4 + 2) / 3;
		int bsz = osz * 3;
		int[] oct = new int [osz];
		int[] bin = new int [bsz];
		bsz = 0;
		for (int i = hsz - 1; i >= 0; i--)
			for (int h = '0' <= num[i] && num[i] <= '9' ? num[i] - '0' : num[i] - 'A' + 10, d = 0; d < 4; h >>= 1, d++)
				bin[bsz++] = (h & 1);
		for (int i = 0, j = 0, k, v; i < osz; oct[i++] = v)
			for (k = 0, v = 0; k < 3; k++)
				v += bin[j++] * (1 << k);
		if (oct[osz - 1] == 0) osz--;
		PrintWriter out = new PrintWriter(System.out);
		for (int i = osz - 1; i >= 0; i--) out.print(oct[i]); out.println();
		out.close();
	}
}
