public class Solution {
    public String multiply(String a, String b) {
        if (a.equals("0") || b.equals("0")) return "0";
        int m = a.length(), n = b.length();
        ArrayList<Integer> dig1 = new ArrayList<Integer>();
        ArrayList<Integer> dig2 = new ArrayList<Integer>();
        for (int i = m-1; i >= 0; i--)
            dig1.add(a.charAt(i) - '0');
        for (int i = n-1; i >= 0; i--)
            dig2.add(b.charAt(i) - '0');

        ArrayList<Integer> dig3 = multiply(dig1, dig2);

        StringBuilder ret =  new StringBuilder();
        int sz = (dig3.get(m+n-1) == 0) ? m+n-1 : m+n;
        for (int i = sz-1; i >= 0; i--)
            ret.append(dig3.get(i));

        return ret.toString();
    }

    ArrayList<Integer> multiply(ArrayList<Integer> A, ArrayList<Integer> B) {
        int m = A.size(), n = B.size();
        ArrayList<Integer> C = new ArrayList<Integer>();
        for (int i = 0; i < m+n; i++) {
            C.add(0);
        }
        int carry = 0;
        for (int i = 0; i < m; i++) {
            carry = 0;
            for (int j = 0; j < n; j++) {
                assert(i+j < m+n);
                C.set(i+j, C.get(i+j) + (A.get(i) * B.get(j)) + carry);
                carry = C.get(i+j) / 10;
                C.set(i+j, C.get(i+j) % 10);
            }
            C.set(i+n, carry);
        }
        return C;
    }
}