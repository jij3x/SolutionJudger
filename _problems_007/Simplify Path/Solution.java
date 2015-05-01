// code by gloomyturkey
// http://www.mitbbs.com/article/JobHunting/32026405_0.html
public class Solution {
    public String simplifyPath(String path) {
        String[] segs = path.split("/");
        Deque<String> d = new ArrayDeque<String>();
       
        for (String seg : segs) {
            if (".".equals(seg) || "".equals(seg)) {
                continue;
            }
            else if ("..".equals(seg)) {
                if (!d.isEmpty()) d.removeLast();
            }
            else {
                d.addLast(seg);
            }
        }
       
        StringBuilder builder = new StringBuilder();
        while (!d.isEmpty()) {
            builder.append("/" + d.pollFirst());
        }
       
        return builder.toString().length()==0? "/" : builder.toString();
    }
}