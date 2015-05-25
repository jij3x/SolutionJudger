public class Solution {
    private Read4 read4;

    public Solution(Read4 read4) {
        this.read4 = read4
    }

    public int read(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false;
        while (!eof && readBytes < n) {
            int sz = (bufsize > 0) ? bufsize : read4(buffer);
            if (bufsize == 0 && sz < 4) eof = true;
            int bytes = Math.min(n - readBytes, sz);
            System.arraycopy(buffer, offset, buf, readBytes, bytes);
            offset = (offset + bytes) % 4;
            bufsize = sz - bytes;
            readBytes += bytes;
        }
        return readBytes;
    }
}