public class Solution {
    public List<String> addOperators(String num, int target) {
        long t = target;
        List<String> r;
        if(num.length() == 0) r = new ArrayList<String>();
        else r = helper(num, t, 0, 1);
        return r;
    }
    public List<String> helper(String num, long target, int from, long left){
        ArrayList<String> result = new ArrayList<String>();
        List<String> temp;
        long a = 0, t;
        int to = num.length() - 1;
        if(num.charAt(from) == '0') to = Math.min(to, from+1);
        for(int i = from; i < to; i++){
            a = a*10 + (num.charAt(i)-'0');
            t = left * a;
            temp = helper(num, target, i+1, t);            
            for(String s: temp){
                result.add(num.substring(from, i+1) + "*" + s);
            }
            temp = helper(num, target-t, i+1, 1);
            for(String s: temp){
                result.add(num.substring(from, i+1) + "+" + s);
            }
            temp = helper(num, target-t, i+1, -1);
            for(String s: temp){
                result.add(num.substring(from, i+1) + "-" + s);
            }
        }
        if(num.charAt(from) != '0' || num.length() == from+1){
            a = a * 10 + (num.charAt(num.length()-1)-'0');
            if(left * a == target) result.add(num.substring(from));
        }
        return result;
    }
}