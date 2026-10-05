import java.util.HashMap;

class Solution {
    public int minMirrorPairDistance(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>(nums.length * 2);

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {

            Integer index = map.get(nums[i]);

            if (index != null) {
                result = Math.min(result, i - index);
            }

            map.put(reverse(nums[i]), i);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int reverse(int num) {
        int rev = 0;

        while (num > 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }

        return rev;
    }
}