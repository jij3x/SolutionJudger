public class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length;
        for (int c : citations)
            if (c > --i)
                h += 1;
        return h;
    }
}