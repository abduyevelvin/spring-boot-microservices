package com.java.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Stream;

@SpringBootTest
class EmployeeServiceApplicationTests {

	@Test
	void contextLoads() {

		//System.out.println(groupAnagrams(Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat")));

		//System.out.println(isAnagram("Hello", "hello") ? "Anagrams" : "Not Anagrams");

		findTokens("He is a very very good boy, isn't he?");
	}

	private List<List<String>> groupAnagrams(List<String> words) {
		Map<String, List<String>> map = new HashMap<>();

		for (String word : words) {
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			String key = new String(chars); // Sorted characters as key
			map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
		}

		return new ArrayList<>(map.values());
	}

	private static boolean isAnagram(String a, String b) {
		// Complete the function
		if(a.length() != b.length()) {
			return false;
		}

		var charsA = a.toLowerCase().toCharArray();
		var charsB = b.toLowerCase().toCharArray();
		Arrays.sort(charsA);
		Arrays.sort(charsB);

        return Arrays.equals(charsA, charsB);
    }

	private static void findTokens(String sentence) {
		var str = sentence.replaceFirst("^\\W+", "").split("\\W+");
		StringBuilder result = new StringBuilder(String.valueOf(str.length));

		for(String s : str) {
			result.append(System.lineSeparator())
                  .append(s);
		}

		System.out.println(result);
	}

	private static int removeElement(int[] nums, int val) {
		var removed = Arrays.stream(nums).filter(num -> num == val).boxed().toList();

		Integer[] arr = new Integer[] {100,150,200,300};

		List<Integer> filtered = Arrays.stream(arr)
                                       .filter(item -> item == 200)
                                       .toList();

		return removed.size();
	}

}
