class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int windowSum = 0;
        int count = 0;

        // First window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int requiredSum = k * threshold;

        if (windowSum >= requiredSum) {
            count++;
        }

        // Slide the window
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i];
            windowSum -= arr[i - k];

            if (windowSum >= requiredSum) {
                count++;
            }
        }

        return count;
    }
}