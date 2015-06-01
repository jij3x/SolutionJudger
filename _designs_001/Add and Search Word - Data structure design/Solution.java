public class Solution {
    private int nextIntFromStream(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private String nextStringFromStream(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }

    public List<Boolean> testWordDictionary(StreamTokenizer tokenizer) throws IOException {
        WordDictionary wd = new WordDictionary();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = nextIntFromStream(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (nextIntFromStream(tokenizer)) {
            case 0:
                wd.addWord(nextStringFromStream(tokenizer));
                break;
            case 1:
                result.add(wd.search(nextStringFromStream(tokenizer)));
                break;
            }
        }
        return result;
    }
}