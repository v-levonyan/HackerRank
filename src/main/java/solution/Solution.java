package solution;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		String gene = sc.next();
		int ACount = 0;
		int CCount = 0;
		int TCount = 0;
		int GCount = 0;

		int shortestLength = findShortestLength(gene, count);

		System.out.println("Heloooooooo8");


	}

	private static int findShortestLength(String gene, int count) {
		Map<String, List<Range>> charRanges = new HashMap<>();
		Map<String, Integer> charCounts = new HashMap<>();

		List <Range> ranges = findRangesForChar(gene, 'A');
		charRanges.put("A", ranges);
		charCounts.put("A", totalLength(ranges));

		ranges = findRangesForChar(gene, 'C');
		charRanges.put("C", ranges);
		charCounts.put("C", totalLength(ranges));

		ranges = findRangesForChar(gene, 'T');
		charRanges.put("T", ranges);
		charCounts.put("T", totalLength(ranges));

		ranges = findRangesForChar(gene, 'G');
		charRanges.put("G", ranges);
		charCounts.put("G", totalLength(ranges));

		List<String> heavyChars = disBalancedChars(charCounts, count);
		for (String str : heavyChars) {
			System.out.println(str);
		}
		int shortestLength=0;

		return shortestLength;

	}

	private static class Range {
		public int start;
		public int end;
		public char identifier;
        public Range prev;
        public Range next;

		public Range(int start, int end, char identifier) {
			this.start = start;
			this.end = end;
			this.identifier = identifier;
		}

		public int length() {
			return (end - start);
		}

		public String toString() {
			return "(" + this.start + ", " + this.end + ")";

		}


	}

	private static List<Range> findRangesForChar(String s, char ch) {

		List<Range> ranges = new ArrayList<>();

		for (int i = 0; i < s.length(); ++i) {
			int start = 0;
			boolean found = false;
			if (s.charAt(i) == ch) {
				start = i;
				found = true;
			}
			while(i < s.length() && s.charAt(i) == ch) {
				i++;
			}

			if (found) {
				Range range = new Range(start, i, ch);
				ranges.add(range);
			}
		}
		return ranges;
	}
	private static int totalLength(List<Range> ranges) {
		int totalLength = 0;
		for (Range range: ranges) {
			totalLength = totalLength + range.length();
		}

		return totalLength;
	}

	private static List<String> disBalancedChars(Map<String, Integer> charCounts, int totalCount) {
		List<String> heavyChars = new ArrayList<>();
		Iterator it = charCounts.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			int count = (int)pair.getValue();
			if(count > totalCount/4){
				heavyChars.add((String)pair.getKey());
			}
			it.remove();
		}
		return heavyChars;
	}

}















	/*	for (char ch : gene.toCharArray()) {
			switch(String.valueOf(ch)) {
				case "A":
					ACount++;
					break;
				case "C":
					CCount++;
					break;
				case "T":
					TCount++;
					break;
				case "G":
					GCount++;
					break;
			}
		}
		*/
