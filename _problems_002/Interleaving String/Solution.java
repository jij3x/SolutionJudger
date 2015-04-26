public class Solution {
    private final int MAX_LEN = 110;
    int[][] canInterleave = new int[MAX_LEN][MAX_LEN];
    public boolean dfs(String a, String b, String c, int i, int j)
    {
        if (canInterleave[i][j] != -1) {
            return canInterleave[i][j] == 1;
        }
        
        if(i+j==c.length()) {
            canInterleave[i][j] = 1;
            return true;
        }
       
        if(i<a.length()&&a.charAt(i) == c.charAt(i+j))
        {
            if(dfs(a,b,c,i+1,j)) {
                canInterleave[i][j] = 1;
                return true;
            }
        }

        if(j<b.length()&&b.charAt(j)==c.charAt(i+j))
        {
            if(dfs(a,b,c,i,j+1)) {
                canInterleave[i][j] = 1;
                return true;
            }
        }
        
        canInterleave[i][j] = 0;
        return false;
    }
   
    public boolean isInterleave(String a, String b, String c)
    {
        if(a==null || b==null || c==null || a.length()+b.length()!=c.length())
            return false;
            
        for (int i = 0; i < MAX_LEN; i++) {
            for (int j = 0; j < MAX_LEN; j++) {
                canInterleave[i][j] = -1;
            }
        }
       
        return dfs(a,b,c,0,0);
       
    }
}
