class Solution
  def testReaderN(s, inp, metrics)
    metrics[0] = 0
    reader_n, r = ReaderN.new(Reader4.new(s)), []
    Serializer.deserializeInt(inp).times do
      n, buf = Serializer.deserializeInt(inp), ''
      start_time = Time.now
      reader_n.read(buf, n)
      r << buf
      metrics[0] += (Time.now - start_time) * 1000
    end
    r
  end
end
