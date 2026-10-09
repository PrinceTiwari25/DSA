import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    void solve(int[] nums, int i, List<Integer> list, List<List<Integer>> ans) {

       
        if (i == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        
        list.add(nums[i]);
        solve(nums, i + 1, list, ans);

        
        list.remove(list.size() - 1);
        solve(nums, i + 1, list, ans);
    }
}