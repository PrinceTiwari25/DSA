class Solution {
    public int longestNiceSubarray(int[] nums) {
        int i =0; 
        int usedbit =0 ;
        int maxlength = 0;

        for(int j =0;j<nums.length;j++){
            while((usedbit & nums[j])!=0){
                usedbit ^= nums[i];
                i++;
            }
            usedbit |=nums[j];
            maxlength =Math.max(maxlength,j-i+1);
        }
        return maxlength;
    }
}