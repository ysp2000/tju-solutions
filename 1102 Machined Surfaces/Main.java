// East Central North America 1995
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		for (int n = Integer.parseInt(in.readLine()); n > 0; n = Integer.parseInt(in.readLine())) {
			int ans = 0;
			int min = 100;
			
			for (int i = 0; i < n; i++) {
				char[] s = in.readLine().toCharArray();
				
				int cur = 0;
				
				for (int j = 0; j < 25; j++) {
					if (s[j] == ' ') {
						cur++;
					}
				}
				
				ans += cur;
				min = Math.min(min, cur);
			}
			
			out.println(ans - n * min);
		}
		
		out.close();
	}
}
