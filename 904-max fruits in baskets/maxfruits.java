class Solution {
    public int totalFruit(int[] fruits) {

        int[] freq = new int[100001];

        int i = 0;
        int unique = 0;
        int maxlength = 0;

        for (int j = 0; j < fruits.length; j++) {

            if (freq[fruits[j]] == 0) {
                unique++;
            }

            freq[fruits[j]]++;

            while (unique > 2) {

                freq[fruits[i]]--;

                if (freq[fruits[i]] == 0) {
                    unique--;
                }

                i++;
            }

            maxlength = Math.max(maxlength, j - i + 1);
        }

        return maxlength;
    }
}