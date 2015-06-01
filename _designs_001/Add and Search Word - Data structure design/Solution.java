public class Solution {
    public List<Boolean> testWordDictionary(StreamTokenizer tokenizer) throws IOException {
        WordDictionary wd = new WordDictionary();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                wd.addWord(Serializer.deserializeString(tokenizer));
                break;
            case 1:
                result.add(wd.search(Serializer.deserializeString(tokenizer)));
                break;
            }
        }
        return result;
    }
}