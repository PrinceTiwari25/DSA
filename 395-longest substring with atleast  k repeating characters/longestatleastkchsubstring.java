class Solution {
    public int longestSubstring(String s, int k) {

        int maxLength = 0;

        for (int requiredUnique = 1; requiredUnique <= 26; requiredUnique++) {

            int[] freq = new int[256];

            int left = 0;
            int right = 0;

            int unique = 0;
            int atLeastK = 0;

            while (right < s.length()) {

                char ch = s.charAt(right);

                if (freq[ch] == 0) {
                    unique++;
                }

                freq[ch]++;

                if (freq[ch] == k) {
                    atLeastK++;
                }

                while (unique > requiredUnique) {

                    char leftChar = s.charAt(left);

                    if (freq[leftChar] == k) {
                        atLeastK--;
                    }

                    freq[leftChar]--;

                    if (freq[leftChar] == 0) {
                        unique--;
                    }

                    left++;
                }

                if (unique == requiredUnique &&
                    atLeastK == requiredUnique) {

                    maxLength = Math.max(
                        maxLength,
                        right - left + 1
                    );
                }

                right++;
            }
        }

        return maxLength;
    }
}