public class Solution {
    private static final int MAX_LEN = 512;

    private int nextIntFromStream(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public List<String> testReaderN(String s, StreamTokenizer tokenizer) throws IOException {
        ReaderN readerN = new ReaderN(new Reader4(s));
        List<String> result = new ArrayList<String>();

        int cnt = nextIntFromStream(tokenizer);
        for (int i = 0; i < cnt; i++) {
            char[] buffer = new char[MAX_LEN];
            int size = readerN.read(buffer, nextIntFromStream(tokenizer));
            result.add(new String(buffer, 0, size));
        }
        return result;
    }
}