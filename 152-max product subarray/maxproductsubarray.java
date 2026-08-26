class Solution {
    public int maxProduct(int[] nums) {

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int num = nums[i];

            int newMax = Math.max(
                num,
                Math.max(num * maxProduct,
                         num * minProduct)
            );

            int newMin = Math.min(
                num,
                Math.min(num * maxProduct,
                         num * minProduct)
            );

            maxProduct = newMax;
            minProduct = newMin;

            answer = Math.max(answer, maxProduct);
        }

        return answer;
    }
}
