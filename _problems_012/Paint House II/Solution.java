public class Solution {
    public int minCostII(int[][] costs) {
        if(costs.length == 0) return 0;
        int[][] A = new int[2][costs[0].length];
        for(int i=0;i<costs.length;i++){
            // 0 - smallest  1-second smallest index
            int[] arr = findMin(A);
            for(int j=0;j<costs[0].length;j++){
                int tmp = 0;
                if(arr[0] != -1 && arr[0] != j) tmp = A[0][arr[0]];
                else if(arr[1] != -1 && arr[1] != j) tmp = A[0][arr[1]];
                A[1][j] = tmp+costs[i][j];
            }
            for(int j=0;j<A[0].length;j++){
                A[0][j] = A[1][j];
            }
        }

        return min(A);
    }

    public int min(int[][] A){
        int ret = A[0][0];
        for(int i=0;i<A[0].length;i++){
            ret = Math.min(A[0][i],ret);
        }
        return ret;
    }

    // find mininum and second minimun index in A[0][0~n];
    public int[] findMin(int[][] A){
        if(A[0].length == 1) return new int[]{-1,-1};
        int[] ret = new int[]{-1,-1};
        for(int i=0;i<A[0].length;i++){
            if(ret[0] == -1||A[0][i] < A[0][ret[0]]){
                ret[1] = ret[0];
                ret[0] = i;
            }else if(ret[1] == -1 || A[0][i] < A[0][ret[1]]){
                ret[1] = i;
            }
        }
        return ret;
    }
}