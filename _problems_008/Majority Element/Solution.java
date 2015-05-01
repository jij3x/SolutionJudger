public class Solution {
    public int majorityElement(int[] num) {
        int count = 0, result = 0;
        for (int i : num) {
            if (count == 0) result = i;
            if (result == i) count++;
            else count --;
        }
        return result;
    }
}