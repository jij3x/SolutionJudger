public class Solution {
    public List<Boolean> testTwoSumIII(StreamTokenizer tokenizer) throws IOException {
        TwoSum twoSum = new TwoSum();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                twoSum.add(Serializer.deserializeInt(tokenizer));
                break;
            case 1:
                result.add(twoSum.find(Serializer.deserializeInt(tokenizer)));
                break;
            }
        }
        return result;
    }
}