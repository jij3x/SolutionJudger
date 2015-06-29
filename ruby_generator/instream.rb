class InStream

  @@bin_size = 65536

  def initialize(inp)
    @inp = inp
    @buffer = @inp.read(@@bin_size * 2)
    @idx = 0
  end

  def step
    if (@buffer.length - @idx < @@bin_size && !@inp.eof?)
      @buffer << @inp.read(@@bin_size)
      @buffer << ' ' if @inp.eof?
    end
    @idx += 1
  end

  def get_token
    step while (@buffer[@idx] == ' ' || @buffer[@idx] == "\n")
    s = @idx
    step while (@buffer[@idx] != ' ' && @buffer[@idx] != "\n")

    r = @buffer[s...@idx]
    if @idx > @@bin_size
      @buffer = @buffer[@idx..-1]
      @idx = 0
    end
    r
  end

  def get_qtoken
    step while (@buffer[@idx] != '"')
    s = (@idx += 1)
    step while (@buffer[@idx] != '"')

    r = @buffer[s...@idx]
    @idx += 1
    if @idx > @@bin_size
      @buffer = @buffer[@idx..-1]
      @idx = 0
    end
    r
  end

  private :step

end
