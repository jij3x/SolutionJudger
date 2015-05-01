public class Solution {
    public class Str implements Comparable {
        String num;
        Str(int n) { num = String.valueOf(n); }
        public int compareTo(Str s) { return (num + s.num).compareTo(s.num + num); }
        public String toString() { return num; }
        @Override
        public int compareTo(Object o) {
            return compareTo((Str)o);
        }
    }
    public String largestNumber(int[] num) {
        Str[] strs = new Str[num.length];
        for (int i = 0; i < num.length; i++) strs[i] = new Str(num[i]);
        Arrays.sort(strs);
        StringBuilder sb = new StringBuilder();
        for (int i = num.length - 1; i >= 0; i--) sb.append(strs[i]);
        String result = sb.toString().replaceFirst("^0+", "");
        if (result.equals("")) return "0"; else return result;
    }
}