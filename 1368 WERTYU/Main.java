import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	char[] a = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]".toCharArray();
	char[] t = " !\"#$%&;()*+M0,.9`12345678:L<->?@AVXSWDFGUHJKNBIOQEARYCQZTZP][".toCharArray();
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		while (in.ready()) {
			for (char c : in.readLine().toCharArray())
				out.print(t[c - 32]);
			out.println();
		}
		out.close();
	}
}
