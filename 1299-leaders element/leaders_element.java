class Solution {
    public int[] replaceElements(int[] arr) {
        int maxi = -1;

        for(int i=arr.length-1;i>=0;i--){
            int temp = arr[i];
            arr[i]= maxi;
            maxi = Math.max(maxi,temp);

        }


        return arr;
    }
}
