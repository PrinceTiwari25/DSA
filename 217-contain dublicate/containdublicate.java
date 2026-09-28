import java.util.HashSet;

class containdublicate {
    public boolean containsDuplicate(int[] nums) {

        HashSet<Integer> set = new HashSet<>(nums.length * 2);

        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }

        return false;
    }
}