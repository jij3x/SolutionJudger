public class Solution {
    private static final int MAX_LEN = 512;

    public List<String> testReaderN(String s, StreamTokenizer tokenizer) throws IOException {
        ReaderN readerN = new ReaderN(new Reader4(s));
        List<String> result = new ArrayList<String>();

        int cnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < cnt; i++) {
            char[] buffer = new char[MAX_LEN];
            int size = readerN.read(buffer, Serializer.deserializeInt(tokenizer));
            result.add(new String(buffer, 0, size));
        }
        return result;
    }
}