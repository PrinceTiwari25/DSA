class Solution {

    public long subarrayXor(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // XOR 0 occurs once before the array starts
        map.put(0, 1);

        int prefixXor = 0;
        long count = 0;

        for (int num : nums) {

            prefixXor ^= num;

            int required = prefixXor ^ k;

            if (map.containsKey(required)) {
                count += map.get(required);
            }

            map.put(
                prefixXor,
                map.getOrDefault(prefixXor, 0) + 1
            );
        }

        return count;
    }
}
