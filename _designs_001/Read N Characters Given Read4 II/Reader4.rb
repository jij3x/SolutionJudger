class Reader4
  def initialize(s)
    @data = s
    @buffer_size = 4
    @idx = 0
  end
  
  def read(buf)
    bytes = [@data.length - @idx, @buffer_size].min
    buf.delete!(buf)
    buf << @data[@idx...@idx + bytes]
    @idx += bytes
    bytes
  end
end
