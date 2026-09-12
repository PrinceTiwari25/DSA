class Solution {
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        int i = 0;
        int j = skill.length - 1;
        long totalChemistry = 0; 
        
        
        int targetSum = skill[i] + skill[j];
        
        while (i < j) {
            int currentSum = skill[i] + skill[j];
            
            
            if (currentSum != targetSum) {
                return -1;
            }
            
            
            totalChemistry += (long) skill[i] * skill[j];
            
            i++;
            j--;
        }
        
        return totalChemistry;
    }
}
