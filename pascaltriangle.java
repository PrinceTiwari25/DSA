class Solution {
    public List<List<Integer>> generate(int numRows) {

        // Stores all rows
        List<List<Integer>> ans = new ArrayList<>();

        // Create rows one by one
        for (int i = 0; i < numRows; i++) {

            // Create current row
            List<Integer> row = new ArrayList<>();

            // Every row starts with 1
            row.add(1);

            // Calculate middle elements
            for (int j = 1; j < i; j++) {

                row.add(
                    ans.get(i - 1).get(j - 1)
                    + ans.get(i - 1).get(j)
                );
            }

            // Every row except the first ends with 1
            if (i > 0) {
                row.add(1);
            }

            // Add current row to answer
            ans.add(row);
        }

        return ans;
    }
}
