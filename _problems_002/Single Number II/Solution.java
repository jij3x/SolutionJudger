public class Solution {
    public int singleNumber(int[] A) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            int cnt;
            if (count.get(A[i]) == null) {
                cnt = 1;
            } else {
                cnt = count.get(A[i]) + 1;
            }
            count.put(A[i], cnt);
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
}