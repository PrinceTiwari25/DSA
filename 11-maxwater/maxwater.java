class Solution {
    public int maxArea(int[] height) {
        int lp = 0; 
        int rp = height.length-1;
         int maxwater =0;
        

        while(rp>lp){
            int h = Math.min(height[lp],height[rp]);
            int w = rp-lp;
           

          int   currentwater= w*h;
             maxwater = Math.max(maxwater,currentwater);
           if(height[lp]<height[rp]){
            lp++;
           }else{
            rp--;
           }

           
        }
       return maxwater;
    }
}
