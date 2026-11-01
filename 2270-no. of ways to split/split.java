class Solution {
    public int waysToSplitArray(int[] nums) {
        long sum = 0;
        for(int num:nums){
            sum+= num;
        }
        long leftsum = 0;
        
        int splitcount=0;
        for(int i =0 ;i<nums.length-1;i++){
            leftsum+= nums[i];
            long rightsum = sum-leftsum;
            if(leftsum>=rightsum){
                splitcount++;
            }

        }
        return splitcount;
        
    }
}