import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String sa1 = in.readLine();
		String sa2 = in.readLine();
		char[] a1 = sa1.toCharArray();
		char[] a2 = sa2.toCharArray();
		int l = sa1.length();
		char[] t = new char [256];
		for (char i = 0; i < 256; i++)
			t[i] = i;
		for (int i = 0; i < l; i++)
			t[a1[i]] = a2[i];
		out.println(sa2);
		out.println(sa1);
		for (;in.ready(); out.println())
			for (char ch : in.readLine().toCharArray())
				out.print(t[ch]);
		out.close();
	}
}
