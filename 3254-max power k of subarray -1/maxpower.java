class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1]; 
        int consecutive = 1;
        
        for (int right = 0; right < n; right++) {
            if (right > 0 && nums[right] == nums[right - 1] + 1) {
                consecutive++;
            } else if (right > 0) {
                consecutive = 1; 
            }
            
            if (right >= k - 1) {
                int start = right - k + 1;
                if (consecutive >= k) {
                    ans[start] = nums[right];
                } else {
                    ans[start] = -1; 
                }
            }
        }
        return ans;
    }
}
