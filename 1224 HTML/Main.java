import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
			System.setIn(new FileInputStream("test.html"));
		} catch (SecurityException e) {}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		while (in.ready())
			sb.append(in.readLine() + " ");
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer htmlTok = new StringTokenizer(sb.toString(), "<>", true);
		int cnt = 0;
		while (htmlTok.hasMoreTokens()) {
			String ct = htmlTok.nextToken();
			if (ct.equals("<")) {
				if (htmlTok.nextToken().equals("br"))
					out.println();
				else {
					if (cnt > 0)
						out.println();
					out.println("--------------------------------------------------------------------------------");
				}
				htmlTok.nextToken();
				cnt = 0;
			} else {
				StringTokenizer tok = new StringTokenizer(ct);
				while (tok.hasMoreTokens()) {
					String cw = tok.nextToken();
					if ((cnt + cw.length() + 1) > 80) {
						out.println();
						cnt = 0;
					}
					if (cnt > 0)
						out.print(' ');
					out.print(cw);
					cnt += cw.length() + 1;
				}
			}
		}
		if (cnt > 0)
			out.println();
		out.close();
	}
}
