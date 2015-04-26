public class Solution {
    public String minWindow(String S, String T) {
        int[] needToFind = new int[256];
        int[] hasFound = new int[256];
        int m = T.length(), n = S.length();
        int numUniques = 0;
        for (int i = 0; i < m; i++) {
            char c = T.charAt(i);
            if (needToFind[c] == 0) numUniques++;
            needToFind[c]++;
        }
        int count = 0;
        int j;
        for (j = 0; j < n && count < numUniques; j++) {
            char c = S.charAt(j);
            if (needToFind[c] > 0) {
                hasFound[c]++;
                if (hasFound[c] == needToFind[c]) count++;
            }
        }
        if (count < numUniques) return "";
        int i;
        for (i = 0; i < n; i++) {
            char c = S.charAt(i);
            if (needToFind[c] > 0) {
                if (hasFound[c] == needToFind[c]) break;
                hasFound[c]--;
            }
        }
        int minEnd = j;
        int minStart = i;
        while (j < n) {
            char c = S.charAt(j);
            if (needToFind[c] > 0) {
                hasFound[c]++;
                while (i < n) {
                    char d = S.charAt(i);
                    if (needToFind[d] > 0) {
                        if (hasFound[d] == needToFind[d]) break;
                        hasFound[d]--;
                    }
                    i++;
                }
                if (j-i+1 < minEnd-minStart+1) {
                    minEnd = j+1;
                    minStart = i;
                }
            }
            j++;
        }
        return S.substring(minStart, minEnd);
    }
}
