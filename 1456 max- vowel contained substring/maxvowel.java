class Solution { 
    public int maxVowels(String s, int k) { 
        int i = 0, j = 0; 
        int res = 0; 
        int window = 0; 

        
        for (; j < k; j++) { 
            window += isvowel(s.charAt(j)); 
        } 
        
        
        res = window; 

        
        while (j < s.length()) { 
            window -= isvowel(s.charAt(i)); 
            window += isvowel(s.charAt(j)); 
            res = Math.max(res, window); 
            j++; 
            i++; 
        } 

        return res; 
    } 

   
    private int isvowel(char c) { 
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0; 
    } 
}
