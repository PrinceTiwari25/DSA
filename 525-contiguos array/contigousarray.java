class Solution {
    public int findMaxLength(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int prefixSum = 0;
        int maxLength = 0;

        // Sum 0 is present before the array starts
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {

            
            if (nums[i] == 0) {
                prefixSum--;
            } else {
                prefixSum++;
            }

          
            if (map.containsKey(prefixSum)) {

                int length = i - map.get(prefixSum);

                maxLength = Math.max(maxLength, length);

            } else {

                
                map.put(prefixSum, i);
            }
        }

        return maxLength;
    }
}
