public class Solution {
    public int canCompleteCircuit(int[] A, int[] G) {
        int n = A.length;
        for (int j = 0; j < n; j++) {
            int cum = 0;
    		boolean found = true;
            int k;
			for (k = j; k < j + n && k < 2*n; k++) {
				int i = k % n;
				int net = A[i] - G[i];
				if (cum + net < 0) {
					found = false;
					break;
				} else {
			        cum += net;
				}
			}
			if (found) return j;
			j = k;
		}
		return -1;
    }
}
