public class ReaderN {
    private Reader4 reader4;
    private int offset, bufsize;

    ReaderN(Reader4 reader4) {
        this.reader4 = reader4;
        this.offset = 0;
        this.bufsize = 0;
    }

    private char[] buffer = new char[4];

    public int read(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false;
        while (!eof && readBytes < n) {
            int sz = (bufsize > 0) ? bufsize : reader4.read(buffer);
            if (bufsize == 0 && sz < 4)
                eof = true;
            int bytes = Math.min(n - readBytes, sz);
            System.arraycopy(buffer, offset, buf, readBytes, bytes);
            offset = (offset + bytes) % 4;
            bufsize = sz - bytes;
            readBytes += bytes;
        }
        return readBytes;
    }
}
