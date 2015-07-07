public class Solution {
    private static final int MAX_LEN = 512;

    public List<String> testReaderN(String s, StreamTokenizer tokenizer, List<Double> metrics) throws IOException {
        ReaderN readerN = new ReaderN(new Reader4(s));
        List<String> result = new ArrayList<String>();

        int cnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < cnt; i++) {
            char[] buffer = new char[MAX_LEN];
            int n = Serializer.deserializeInt(tokenizer);
            double startTime = System.nanoTime();
            int size = readerN.read(buffer, n);
            metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
            result.add(new String(buffer, 0, size));
        }
        return result;
    }
}