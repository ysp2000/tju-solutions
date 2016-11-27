import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(28, "A1");
		map.put(85, "A1C2A3B1D2B3C1A2C3D1B2D3");
		map.put(88, "A1B3D2F1G3E2G1F3E1G2E3C2A3B1C3A2C1D3B2D1F2");
		map.put(89, "A1B3C1A2C3D1B2D3E1G2E3C2A3B1D2F1H2F3G1E2G3H1F2H3");
		map.put(111, "A1B3C1A2B4C2A3B1C3A4B2C4");
		map.put(113, "A1B3C1A2B4D3E1C2D4E2C3A4B2D1E3C4A3B1D2E4");
		map.put(114, "A1B3C1A2B4C2D4E2F4D3E1F3D2B1A3C4B2A4C3E4F2D1E3F1");
		map.put(139, "A1B3A5C4D2B1A3B5D4C2B4A2C1D3C5A4B2D1C3D5");
		map.put(140, "A1B3A5C4A3B1D2E4C5A4B2D1C3B5D4E2C1A2B4D5E3C2E1D3E5");
		map.put(166, "A1B3A5C6D4B5D6C4D2B1A3C2B4A2C1D3B2D1C3D5B6A4C5A6");
		map.put(192, "A1B3C1A2C3B1A3C2B4A6C7B5A7C6A5B7C5A4B2C4B6");
		map.put(219, "A1B3C1A2B4C2A3B1C3A4B2C4A5B7C5A6B8C6A7B5C7A8B6C8");
		PrintWriter out = new PrintWriter(System.out);
		for (int t = 1, T = nextInt(); T --> 0;) {
			out.print("Scenario #"); out.print(t++); out.println(':');
			int hash = nextInt() * 27 + nextInt();
			out.println(map.containsKey(hash) ? map.get(hash) : "impossible");
			out.println();
		}
		out.close();
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
