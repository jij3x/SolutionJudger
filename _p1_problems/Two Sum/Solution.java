public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            // if there exist two same numbers, the latter will override the
            // earlier one.
            mp.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            int x = target - numbers[i];
            if (mp.containsKey(x)) {
                if ((int) mp.get(x) != i) {
                    int[] ret = new int[2];
                    ret[0] = i + 1;
                    ret[1] = mp.get(x) + 1;
                    return ret;
                }
            }
        }
        return new int[2];
    }
}
