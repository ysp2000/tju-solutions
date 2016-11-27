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

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int MAX = 50;
	
	int OK = 0;
	int BOARD = 1;
	int ITSELF = 2;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		Worm w = new Worm();
		
		for (int N = nextInt(); N > 0; N = nextInt()) {
			char[] path = nextToken().toCharArray();
			w.initialize();
			int ans = OK;
			int cnt = 0;
			for (char step : path) {
				cnt++;
				if ((ans = w.move(dir(step))) != OK)
					break;
			}
			out.print("The worm ");
			if (ans == OK) {
				out.print("successfully made all ");
				out.print(cnt);
				out.println(" moves.");
			} else {
				out.print("ran ");
				out.print(ans == BOARD ? "off the board " : "into itself ");
				out.print("on move ");
				out.print(cnt);
				out.println('.');
			}
		}
		
		out.close();
	}

	// dirs       N   E   S   W
	int[] dr = { -1,  0,  1,  0 };
	int[] dc = {  0,  1,  0, -1 };
	
	int dir(char c) {
		if (c == 'N')
			return 0;
		if (c == 'E')
			return 1;
		if (c == 'S')
			return 2;
		if (c == 'W')
			return 3;
		throw new RuntimeException("Bad character");
	}
	
	class Cell {
		int r;
		int c;
		
		void set(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	class Worm {
		Cell[] body = new Cell [20];
		int head = 0;
		int tail = 0;
		
		Worm() {
			for (int i = 0; i < 20; i++)
				body[i] = new Cell();
		}
		
		void initialize() {
			for (int i = 0; i < 20; i++)
				body[i].set(25, 11 + i);
			tail = 0;
			head = 19;
		}
		
		int move(int d) {
			int nr = body[head].r + dr[d];
			int nc = body[head].c + dc[d];
			if (++head == 20)
				head = 0;
			if (++tail == 20)
				tail = 0;
			body[head].r = nr;
			body[head].c = nc;
			if (nr < 1 || nr > MAX || nc < 1 || nc > MAX)
				return BOARD;
			for (int k = 0, i = tail; k < 19; k++) {
				if (nr == body[i].r && nc == body[i].c)
					return ITSELF;
				if (++i == 20)
					i = 0;
			}
			return OK;
		}
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
