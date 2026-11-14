

class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0)
            return new ArrayList<>();

        return getLetterCombinations(digits,0);
    }

    String[] codes = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> getLetterCombinations(String digits, int idx){
        if(idx == digits.length()){
            List<String> baseResult = new ArrayList<>();
            baseResult.add("");
            return baseResult;
        }

        List<String> myResult = new ArrayList<>();

        List<String> recResult = getLetterCombinations(digits, idx + 1);
        char ch = digits.charAt(idx);
        String code = codes[ch - '0'];

        for(String recString: recResult){
            for(int i = 0; i < code.length(); i++){
                char chCode = code.charAt(i);
                myResult.add(chCode + recString);
            }
        }

        return myResult;
    }
}
