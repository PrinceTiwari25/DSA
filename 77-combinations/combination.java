
import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        comb(1, n, k, new ArrayList<>(), ans);
        return ans;
    }

    void comb(int i, int n, int k, List<Integer> list,
              List<List<Integer>> ans) {

        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (i > n || list.size() + (n - i + 1) < k) {
            return;
        }

       
        list.add(i);
        comb(i + 1, n, k, list, ans);

       
        list.remove(list.size() - 1);
        comb(i + 1, n, k, list, ans);
    }
}
