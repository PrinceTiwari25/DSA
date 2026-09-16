class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n];
        java.util.Arrays.fill(res, -1);
        
        int idx= 2 * k + 1;
        
       
        if (idx > n) {
            return res;
        }
        
        long winSum = 0;
        
       
        for (int i = 0; i < idx; i++) {
            winSum += nums[i];
        }
        
     
        res[k] = (int) (winSum / idx);
        
        
        for (int i = idx; i < n; i++) {
            winSum = winSum - nums[i - idx] + nums[i];
            
            res[i-k] = (int) (winSum / idx);
        }
        
        return res;
    }
}
