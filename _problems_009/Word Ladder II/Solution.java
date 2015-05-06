public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        dict.add(start);
        dict.add(end);
        ArrayList<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> visited = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<String>> exploring = new HashMap<String, ArrayList<String>>();

        LinkedList<String> que = new LinkedList<String>();
        que.add(start);
        visited.put(start, null);
        for (int currLvl = 1, nextLvl = 0; currLvl != 0;) {
            String curr = que.poll();
            if (curr.equals(end)) {
                dfs(curr, new LinkedList<String>(), visited, result);
                return result;
            }

            for (int i = 0; i < curr.length(); i++) {
                StringBuilder buffer = new StringBuilder(curr);
                for (char c = 'a'; c <= 'z'; c++) {
                    buffer.setCharAt(i, c);
                    String newWord = buffer.toString();
                    if (dict.contains(newWord) && !visited.containsKey(newWord) && !newWord.equals(curr)) {
                        ArrayList<String> prevList = exploring.get(newWord);
                        if (prevList == null) {
                            exploring.put(newWord, prevList = new ArrayList<String>());
                            que.add(newWord);
                            nextLvl++;
                        }
                        prevList.add(curr);
                    }
                }
            }

            if (--currLvl == 0) {
                currLvl = nextLvl;
                nextLvl = 0;
                visited.putAll(exploring);
                exploring.clear();
            }
        }
        return result;
    }

    private void dfs(String word, LinkedList<String> path, HashMap<String, ArrayList<String>> visited,
            ArrayList<List<String>> result) {
        path.addFirst(word);
        ArrayList<String> prevList = visited.get(word);
        if (prevList == null) {
            result.add(new ArrayList<String>(path));
        } else {
            for (String prev : prevList) {
                dfs(prev, path, visited, result);
            }
        }
        path.removeFirst();
    }
}
