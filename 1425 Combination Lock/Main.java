import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (;;) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(tok.nextToken());
			int b = Integer.parseInt(tok.nextToken());
			int c = Integer.parseInt(tok.nextToken());
			int d = Integer.parseInt(tok.nextToken());
			if (a + b + c + d == 0)
				break;
			System.out.println((120 + (a >= b ? a - b : (40 - b + a)) + (c >= b ? c - b : (40 - b + c)) + (c >= d ? c - d : (40 - d + c))) * 9);
		}
	}
}
