public class Solution {
    public List<String> generatePalindromes(String s) {
        int[] map = new int[256];
        for(int i=0;i<s.length();i++){
            map[s.charAt(i)]++;
        }
        int j=0,count=0;
        for(int i=0;i<256;i++){
            if(count== 0 && map[i] %2 == 1){
                j= i;
                count++;
            }else if(map[i] % 2==1){
                return new ArrayList<String>();
            }
        }
        String cur = "";
        if(j != 0){
            cur = ""+ (char)j;
            map[j]--;
        }
        List<String> res = new ArrayList<String>();
        DFS(res,cur,map,s.length());
        return res;
    }

    public void DFS(List<String> res,String cur,int[] map,int len){
        if(cur.length()== len){
            res.add(new String(cur));
        }else {
            for(int i=0;i<map.length;i++){
                if(map[i] <= 0) continue;
                map[i] = map[i] - 2;
                cur = (char)i + cur + (char)i;
                DFS(res,cur,map,len);
                cur = cur.substring(1,cur.length()-1);
                map[i] = map[i]+2;
            }
        }
    }
}