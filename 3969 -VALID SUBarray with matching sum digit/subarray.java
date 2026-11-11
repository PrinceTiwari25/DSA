class Solution {
    public int countValidSubarrays(int[] nums, int x) {
        int n = nums.length;
        long[] prefix = new long[n + 1];

        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int count = 0;

        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {

                long sum = prefix[r + 1] - prefix[l];

                int firstDigit = Long.toString(sum).charAt(0) - '0';
                int lastDigit = (int) (sum % 10);

                if (firstDigit == x && lastDigit == x) {
                    count++;
                }
            }
        }

        return count;
    }
}