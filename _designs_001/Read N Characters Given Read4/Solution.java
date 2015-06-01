public class Solution {
    private static final int MAX_LEN = 512;

    public String testReaderN(String s, int n) {
        ReaderN readerN = new ReaderN(new Reader4(s));
        char[] buffer = new char[MAX_LEN];
        int size = readerN.read(buffer, n);
        return new String(buffer, 0, size);
    }
}