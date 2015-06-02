public class Solution {
    public String shortestPalindrome(String s) {
        if(s.length()<2)
            return s;
        //first process the original string to make it one with odd number of chars
        StringBuilder builder=new StringBuilder();
        builder.append('#');
        for(char c:s.toCharArray()){
            builder.append(c);
            builder.append('#');
        }
        String str=builder.toString();
        //lens is the array storing the length of longest palindrome centered at position i
        int[] lens=new int[str.length()];
        int[] res=new int[2];
        //idx is the center of the palindrome reaches the farthest along str
        int idx=0;
        //max is the edge of that longest palindrome
        int max=1;
        res[0]=idx;
        res[1]=max;
        for(int i=1;i<str.length();i++){
            if(i<max){
                lens[i]=Math.min(max-i,lens[2*idx-i]);
            }
            else
                lens[i]=1;
            while(i-lens[i]>=0&&i+lens[i]<str.length()&&
                str.charAt(i-lens[i])==str.charAt(i+lens[i])){
                    lens[i]+=1;
            }
            if(i+lens[i]>max){
                max=i+lens[i];
                idx=i;
            }
            //The res should be a palindrome starting at position 0 and having the biggest length
            if(max-idx>res[1]-res[0]&&2*idx-max+1==0){
                res[1]=max;
                res[0]=idx;
            }
        }
        StringBuilder result=new StringBuilder();
        for(char c:str.substring(res[1]).toCharArray()){
            if(c!='#')
                result.append(c);
        }
        return result.reverse().append(s).toString();
    }
}