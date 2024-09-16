import java.util.HashMap;

class Solution {
    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public int findTheLongestSubstring(String s) {
        HashMap<Character, Integer> vp = new HashMap<>();
        vp.put('a', 0);
        vp.put('e', 1);
        vp.put('i', 2);
        vp.put('o', 3);
        vp.put('u', 4);

        int cur = 0;
        int maxLen = 0;

        HashMap<Integer, Integer> lastOccurred = new HashMap<>();
        lastOccurred.put(0, -1);

        System.out.println("=======================================");
        System.out.println(s);

        for (int r = 0; r < s.length(); r++) {
            char currChar = s.charAt(r);

            System.out.println("i: " + r);
            // Check if vowel
            if (isVowel(currChar)) {
                cur ^= (1 << vp.get(currChar));
                System.out.println( cur + ", " + currChar);
            }

            // Check if state has occurred before
            if (lastOccurred.containsKey(cur)) {
                maxLen = Math.max(maxLen, r - lastOccurred.get(cur));
            } 
            // Store first occurrence of the state
            else {
                lastOccurred.put(cur, r);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        String input1 = "eleetminicoworoep";
        System.out.println("Longest substring length for \"" + input1 + "\": " + sol.findTheLongestSubstring(input1));

        // Example 2
        String input2 = "leetcodeisgreat";
        System.out.println("Longest substring length for \"" + input2 + "\": " + sol.findTheLongestSubstring(input2));

        // Example 3
        String input3 = "bcbcbc";
        System.out.println("Longest substring length for \"" + input3 + "\": " + sol.findTheLongestSubstring(input3));
    }
}
