class Solution {
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Prefix sum 0 occurs once before the array starts
        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            prefixSum += num;

            // Check for previous prefix sum
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            // Store frequency of current prefix sum
            map.put(
                prefixSum,
                map.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }
}
