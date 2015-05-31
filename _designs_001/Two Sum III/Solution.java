public class Solution {
    private int nextIntFromStream(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public List<Boolean> testTwoSumIII(StreamTokenizer tokenizer) throws IOException {
        TwoSum twoSum = new TwoSum();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = nextIntFromStream(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (nextIntFromStream(tokenizer)) {
            case 0:
                twoSum.add(nextIntFromStream(tokenizer));
                break;
            case 1:
                result.add(twoSum.find(nextIntFromStream(tokenizer)));
                break;
            }
        }
        return result;
    }
}