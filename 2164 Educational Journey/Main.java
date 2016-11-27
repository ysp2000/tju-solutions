import java.io.*;
import java.util.*;
import static java.lang.Math.*;

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
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		for (String s = in.readLine(); s.charAt(0) != '-'; s = in.readLine()) {
			StringTokenizer tok = new StringTokenizer(s);
			out.println(getStr(zeroDet(getTime(tok.nextToken()), getTime(tok.nextToken()), getTime(tok.nextToken()), getTime(tok.nextToken()), getTime(tok.nextToken()))));
		}
		
		out.close();
	}
	
	String getStr(int time) {
		return String.format("%02d:%02d:%02d", time / 3600, time % 3600 / 60, time % 60);
	}
	
	int getTime(String token) {
		StringTokenizer tok = new StringTokenizer(token, ":");
		int h = Integer.parseInt(tok.nextToken());
		int m = Integer.parseInt(tok.nextToken());
		int s = Integer.parseInt(tok.nextToken());
		return 3600 * h + 60 * m + s;
	}
	
	int zeroDet(int t1, int t2, int t3, int t4, int t5) {
		double a1 = t4 - t1;
		double b1 = t4 - t3;
		double a2 = t5 - t2;
		double b2 = t5 - t3;
		return (int) (round((a2 * b1 * t1 - a1 * b2 * t2) / (a2 * b1 - a1 * b2)));
	}
}
