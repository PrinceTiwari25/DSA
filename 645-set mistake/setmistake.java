class Solution {
    public int[] findErrorNums(int[] nums) {

        int n = nums.length;

        // Place every number at its correct index
        for (int i = 0; i < n; i++) {

            int correctIndex = nums[i] - 1;

            if (nums[i] != nums[correctIndex]) {

                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;

                i--;
            }
        }

        // Find the wrong position
        for (int i = 0; i < n; i++) {

            if (nums[i] != i + 1) {

                // nums[i] is repeating
                // i + 1 is missing
                return new int[] {
                    nums[i],
                    i + 1
                };
            }
        }

        return new int[] {};
    }
}
