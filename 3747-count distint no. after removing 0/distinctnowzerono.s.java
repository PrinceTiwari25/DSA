
class Solution {
    public long countDistinct(long n) {
        String s = String.valueOf(n);
        int len = s.length();

        long ans = 0;
        long pow = 1;

        for (int i = 1; i < len; i++) {
            pow *= 9;
            ans += pow;
        }

        pow = 1;
        for (int i = 1; i < len; i++) {
            pow *= 9;
        }

        for (int i = 0; i < len; i++) {
            int digit = s.charAt(i) - '0';

            

            if (digit == 0) {
                return ans;
            }
           ans += (digit - 1) * pow;
            pow /= 9;
        }

        return ans + 1;
    }
}
