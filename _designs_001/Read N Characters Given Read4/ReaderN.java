public class ReaderN {
    private Reader4 reader4;

    ReaderN(Reader4 reader4) {
        this.reader4 = reader4;
    }

    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        int readBytes = 0;
        boolean eof = false;
        while (!eof && readBytes < n) {
            int sz = reader4.read(buffer);
            if (sz < 4)
                eof = true;
            int bytes = Math.min(n - readBytes, sz);
            System.arraycopy(buffer, 0, buf, readBytes, bytes);
            readBytes += bytes;
        }
        return readBytes;
    }
}
