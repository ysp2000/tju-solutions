import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
	
		new Main().run();
	}
	
	int pos;
	char[] exp;
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int T = Integer.parseInt(in.readLine()); T --> 0;) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			StringBuffer sb = new StringBuffer();
			while (tok.hasMoreTokens())
				sb.append(tok.nextToken());
			sb.append("#");
			exp = sb.toString().toCharArray();
			pos = 0;
			Item e = E();
			out.println(e);
		}
		out.close();
	}
	
	Item E() {
		Item res = T();
		for (char op = exp[pos++]; op == '+' || op == '-'; op = exp[pos++])
			res = new Item(res, op, T());
		return res;
	}

	Item T() {
		char op = exp[pos++];
		Item res = op == '(' ? E() : new Item(null, op, null);
		return res;
	}

	class Item {
		char op;
		Item left;
		Item right;
		
		Item(Item left, char op, Item right) {
			this.left = left;
			this.op = op;
			this.right = right;
		}
		
		@Override
		public String toString() {
			if (op == '+')
				return left + "+" + right;
			else if (op == '-')
				return left + "-" + (('A' <= right.op && right.op <= 'Z') ? right : ("(" + right + ")"));
			return "" + op;
		}
	}
}
