public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        Queue<Integer> cnt = new LinkedList<Integer>();
        Set<String> visited = new HashSet<String>();
        q.add(start);
        cnt.add(1);
        if (start != end) visited.add(start);
        while (!q.isEmpty()) {
            String word = q.poll();
            int dist = cnt.poll();
            if (dist > 1 && word.equals(end)) return dist;
            for (int i = 0; i < word.length(); i++) {
                char[] copy = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++)
                    if (c != word.charAt(i)) {
                        copy[i] = c;
                        String t = new String(copy);
                        if (dict.contains(t) && !visited.contains(t)) {
                            q.add(t);
                            cnt.add(dist+1);
                            visited.add(t);
                        }
                    }
            }
        }
        return 0;
    }
}