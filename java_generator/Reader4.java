public class Reader4 {
    private final int BUF_SZ = 4;
    private int idx;
    private final char[] data;

    Reader4(String s) {
        idx = 0;
        data = s.toCharArray();
    }

    int read4(char[] buf) {
        int bytes = Math.min(data.length - idx, BUF_SZ);
        System.arraycopy(data, idx, buf, 0, bytes);
        idx += bytes;
        return bytes;
    }
}
