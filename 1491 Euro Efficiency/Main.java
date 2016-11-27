import java.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		
		out.close();
	}

	BufferedReader in;
	PrintWriter out;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		
		
		out.close();
	}
	
	int readIntBuf() throws IOException {
		int n, c, s = 1;
		for (c = in.read(); c < '0' || c > '9'; c = in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return s == 1 ? n : -n;
 	}
	
	int readUnsignedIntBuf() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
	
	long readLongBuf() throws IOException {
		long n, c, s = 1L;
		for (c = in.read(); c < '0' || c > '9'; c = in.read())
			if (c == '-')
				s = -1L;
		for (n = 0L; '0' <= c && c <= '9'; c = in.read())
			n = n * 10L + c - '0';
		return s == 1L ? n : -n;
 	}
	
	long readUnsignedLongBuf() throws IOException {
		long n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0L; '0' <= c && c <= '9'; c = in.read())
			n = n * 10L + c - '0';
		return n;
 	}
	
	static int readInt() throws IOException {
		int n, c, s = 1;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return s == 1 ? n : -n;
 	}
	
	static int readUnsignedInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
	
	static long readLong() throws IOException {
		long n, c, s = 1L;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1L;
		for (n = 0L; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10L + c - '0';
		return s == 1L ? n : -n;
 	}
	
	static long readUnsignedLong() throws IOException {
		long n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0L; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10L + c - '0';
		return n;
 	}
}
