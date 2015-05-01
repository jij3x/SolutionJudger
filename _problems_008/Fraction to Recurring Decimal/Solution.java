public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuffer ret = new StringBuffer();
        if (numerator < 0 ^ denominator < 0) ret.append("-");
        long n = Math.abs(Long.valueOf(numerator));
        long d = Math.abs(Long.valueOf(denominator));
        ret.append(String.valueOf(n / d));
        if (n % d == 0) return ret.toString();
        ret.append(".");
        Map<Long, Integer> map = new HashMap<>();
        for (long r = n % d; r != 0; r %= d) {
            if (map.containsKey(r)) {
                ret.insert(map.get(r), "(");
                ret.append(")");
                break;
            }
            map.put(r, ret.length());
            r *= 10;
            ret.append(String.valueOf(r / d));
        }
        return ret.toString();
    }
}