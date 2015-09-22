class ReaderN
  def initialize(reader4)
    @reader4 = reader4
  end

  def read(buf, n)
    box4, total_bytes, eof = '', 0, false
    while (!eof and total_bytes < n)
      box_size = @reader4.read(box4)
      eof = true if box_size < 4
      read_size = [n - total_bytes, box_size].min
      buf << box4[0...read_size]
      total_bytes += read_size
    end
    total_bytes
  end
end
