public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        n = ((n & 0xAAAAAAAA) >>> 0x01) | ((n & 0x55555555) << 0x01);
        n = ((n & 0xCCCCCCCC) >>> 0x02) | ((n & 0x33333333) << 0x02);
        n = ((n & 0xF0F0F0F0) >>> 0x04) | ((n & 0x0F0F0F0F) << 0x04);
        n = ((n & 0xFF00FF00) >>> 0x08) | ((n & 0x00FF00FF) << 0x08);
        n = ((n & 0xFFFF0000) >>> 0x10) | ((n & 0x0000FFFF) << 0x10);
        return n;
    }
}