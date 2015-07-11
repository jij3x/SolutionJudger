class Solution
  def testReaderN(s, n)
    reader_n = ReaderN.new(Reader4.new(s))
    buffer = ''
    reader_n.read(buffer, n)
    buffer
  end
end
