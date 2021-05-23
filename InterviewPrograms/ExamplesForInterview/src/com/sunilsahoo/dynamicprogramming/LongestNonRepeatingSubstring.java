package com.sunilsahoo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class LongestNonRepeatingSubstring {
    public static void main(String[] args) {
        LongestNonRepeatingSubstring longestNonRepeatingSubstring = new LongestNonRepeatingSubstring();
        int result =longestNonRepeatingSubstring.lengthOfLongestSubstring1("abcaaabcdbb");
        System.out.println(result);
    }

    /**
     * Sliding window
     * Time complexity : O(2n) = O(n)O(2n)=O(n)
     * Space complexity : O(min(m, n))O(min(m,n)). Same as the previous approach.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] chars = new int[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;

            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            res = Math.max(res, right - left + 1);

            right++;
        }
        return res;
    }
    /**
     * Using Hashmap
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        System.out.println(s);
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
            System.out.println(ans+": "+i+" : "+j+" : "+s.charAt(j)+" : "+map);
        }
        return ans;
    }
}
