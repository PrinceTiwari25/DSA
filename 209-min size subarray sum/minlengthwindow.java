class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        int i = 0;
        int currentSum = 0;
        int minWindowLength = Integer.MAX_VALUE;

        for (int j = 0; j < nums.length; j++) {

            currentSum += nums[j];

            while (currentSum >= target) {

                int currentWindow = j - i + 1;

                minWindowLength = Math.min(
                    minWindowLength,
                    currentWindow
                );

                currentSum -= nums[i];
                i++;
            }
        }

        return minWindowLength == Integer.MAX_VALUE
                ? 0
                : minWindowLength;
    }
}