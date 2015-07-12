class ReaderN
  def initialize(reader4)
    @reader4 = reader4
    @left_over = ''
  end

  def read(buf, n)
    buf.delete!(buf)
    box4, eof = '', false
    while (!eof and @left_over.length < n)
      box_size = @reader4.read(box4)
      eof = true if box_size < 4
      @left_over << box4
    end

    size = [@left_over.length, n].min
    buf << @left_over[0...size]
    @left_over.slice!(0...size)
    buf.length
  end
end
