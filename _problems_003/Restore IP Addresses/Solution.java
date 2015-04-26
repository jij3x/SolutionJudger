public class Solution {
    ArrayList<String> ret=new ArrayList<String>();
    
    String merge(ArrayList<String> al)
    {
        return al.get(0)+"."+al.get(1)+"."+al.get(2)+"."+al.get(3);
    }
    
    void dfs(String s, int start, ArrayList<String> al)
    {
        if(start==s.length() && al.size()==4)
        {
            ret.add(merge(al));
            return;
        }
        
        if(start==s.length() || al.size()==4)
            return;
            
        for(int i=1;i<=3;i++)
        {
            if(start+i<=s.length() && (i==1 || s.charAt(start)!='0')) 
            {
                String sub=s.substring(start,start+i);
                int num=Integer.parseInt(sub);
                if(num<=255)
                {
                    al.add(sub);
                    dfs(s,start+i,al);
                    al.remove(al.size()-1);
                }
            }
        }
    }
    
    public ArrayList<String> restoreIpAddresses(String s) 
    {
        
        ret.clear();
        ArrayList<String> al=new ArrayList<String>();
        dfs(s,0,al);
        return ret;
    }
}