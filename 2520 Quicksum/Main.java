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
		
		for (String s = in.readLine(); s.charAt(0) != '#'; s = in.readLine()) {
			int sum = 0;
			int pos = 0;
			
			for (char c : s.toCharArray()) {
				sum += (c == ' ' ? 0 : (c - 'A' + 1)) * (++pos);
			}
			
			System.out.println(sum);
		}
	}
}
