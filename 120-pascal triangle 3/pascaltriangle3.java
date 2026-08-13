class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        // Store the last row
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        // Move from bottom to top
        for (int i = n - 2; i >= 0; i--) {

            for (int j = 0; j <= i; j++) {

                // Current value + minimum of two children
                dp[j] = triangle.get(i).get(j)
                        + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }
}
