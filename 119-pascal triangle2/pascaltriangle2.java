class Solution {
    public List<Integer> getRow(int rowIndex) {

        List<Integer> ans = new ArrayList<>();

        long value = 1;
        ans.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            value = value * (rowIndex - i + 1) / i;
            ans.add((int) value);
        }

        return ans;
    }
}
