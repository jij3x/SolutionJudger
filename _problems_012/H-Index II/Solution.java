public class Solution {
    public int hIndex(int[] citations) {
        int[] memo1 = new int[citations.length + 1];
        for (int num : citations)
            memo1[Math.min(num, citations.length)]++;
        for (int i = citations.length, t = 0; i > 0; i--) {
            if ((t += memo1[i]) >= i)
                return i;
        }
        return 0;
    }
}
