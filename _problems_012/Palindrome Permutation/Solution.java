public class Solution {
    public boolean canPermutePalindrome(String s) {
        BitSet bs = new BitSet();
        for (byte b : s.getBytes())
            bs.flip(b);
        return bs.cardinality() < 2;
    }
}