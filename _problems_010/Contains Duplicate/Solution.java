public class Solution {
    public boolean containsDuplicate(int[] num) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < num.length; i++) {
            if (s.contains(num[i])) return true;
            s.add(num[i]);
        }
        return false;
    }
}