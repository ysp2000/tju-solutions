import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int T = Integer.parseInt(in.readLine()), t = 0; t < T; t++) {
			char[] num = in.readLine().toCharArray();
			int dig = 0;
			for (int i = num.length - 1; i >= 0; i--)
				dig = (num[i] - '0') + (dig >= 5 ? 1 : 0);
			System.out.print(dig);
			for (int i = 1; i < num.length; i++)
				System.out.print('0');
			System.out.println();
		}
	}
}
