import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		int[] ans = new int [100001];
		
		for (int i = 1; i <= 100000; i++) {
			int s = i;
			for (int x = i; x > 0; x /= 10)
				s += x % 10;
			if (s <= 100000 && (ans[s] == 0 || ans[s] > i))
				ans[s] = i;
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int T = Integer.parseInt(in.readLine()), t = 0; t < T; t++) 
			System.out.println(ans[Integer.parseInt(in.readLine())]);
	}
}
