class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
         HashMap<Integer,Integer> map = new HashMap<>();

         for(int i =0; i<nums.length;i++){
            map.put(nums[i],i);
         }

         for(int[] operation:operations){
            int oldvalue = operation[0];
            int newvalue = operation[1];

            int index = map.get(oldvalue);
            nums[index] = newvalue;
            map.remove(oldvalue);
            map.put(newvalue,index);
 
         }
         return nums;
    }
}