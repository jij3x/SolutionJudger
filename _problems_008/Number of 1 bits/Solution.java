public class Solution {
    // you need treat n as an unsigned value
    public int hammingWeight(int n) {
        return BitSet.valueOf(new long[] { n & (-1L >>> 32) }).cardinality();
    }
}
