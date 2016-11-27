import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.util.Map.Entry;
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
	
	int wNum;
	char[][] words = new char [100][];
	List<Integer> pos = new ArrayList<Integer>();
	Random rnd = new Random();
	long[] code = new long [256];
	long BASE = BigInteger.probablePrime(31, rnd).longValue();
	Map<Long, Integer> sp = new HashMap<Long, Integer>();
	Map<Long, Integer> cnt = new HashMap<Long, Integer>();
	Set<Long> set = new HashSet<Long>();

	void run() throws IOException {
		for (int i = 0; i < 256; i++) {
			code[i] = rnd.nextLong();
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		boolean blank = false;
		
		for (wNum = Integer.parseInt(in.readLine()); wNum > 0; wNum = Integer.parseInt(in.readLine())) {
			if (blank) {
				out.println();
			}
			
			blank = true;
			
			int len = 0;
			int l = 1;
			int r = 1;
			
			for (int i = 0; i < wNum; i++) {
				words[i] = in.readLine().toCharArray();
				r = max(r, words[i].length);
			}
			
			while (l <= r) {
				int m = (l + r) >> 1;
				
				if (found(m)) {
					l = m + 1;
					len = m;
				} else {
					r = m - 1;
				}
			}
			
			if (len > 0) {
				List<String> ans = new ArrayList<String>();
				
				for (int p : pos) {
					int w = p % 100;
					int s = p / 100;
					StringBuilder sb = new StringBuilder();
					
					for (int i = 0; i < len; i++) {
						sb.append(words[w][i + s]);
					}
					
					ans.add(sb.toString());
				}
				
				Collections.sort(ans);
				
				for (String s : ans) {
					out.println(s);
				}
			} else {
				out.println("?");
			}
		}
		
		out.close();
	}
	
	boolean found(int len) {
		sp.clear();
		cnt.clear();

		for (int w = 0; w < wNum; w++) {
			char[] word = words[w];
			
			if (word.length < len) {
				continue;
			}

			set.clear();
			long hash = code[word[len - 1]];
			long pow = 1;
			
			for (int i = len - 2; i >= 0; i--) {
				pow *= BASE;
				hash += pow * code[word[i]];
			}
			
			put(hash, w, 0);
			
			for (int i = len; i < word.length; i++) {
				hash -= pow * code[word[i - len]];
				hash *= BASE;
				hash += code[word[i]];
				put(hash, w, i - len + 1);
			}
		}
		
		boolean res = false;
		
		for (Entry<Long, Integer> e : cnt.entrySet()) {
			long key = e.getKey();
			
			if (e.getValue() > (wNum >> 1)) {
				if (!res) {
					res = true;
					pos.clear();
				}
				
				pos.add(sp.get(key));
			}
		}
		
		return res;
	}
	
	void put(long hash, int w, int s) {
		if (!set.contains(hash)) {
			set.add(hash);
			
			if (cnt.containsKey(hash)) {
				cnt.put(hash, cnt.get(hash) + 1);
			} else {
				cnt.put(hash, 1);
				sp.put(hash, 100 * s + w);
			}
		}
	}
}
