class InStream

  @@bin_size = 65536

  def initialize(inp)
    @inp = inp
    @buffer = @inp.read(@@bin_size * 2)
    @idx = 0
  end

  def get_token
    start =  -1
    loop do
      if (@buffer.length - @idx < @@bin_size && !@inp.eof?)
        @buffer << @inp.read(@@bin_size)
        @buffer << ' ' if @inp.eof?

        if @idx > @@bin_size
          @buffer = @buffer[@idx..-1]
          @idx = 0
        end
      end

      (@idx...@buffer.length).each do |i|
        if (@buffer[i] == ' ' || @buffer[i] == "\n")
          next if start == -1

          @idx = i
          return @buffer[start...i]
        end
        start = i if start == -1
      end
    end
  end

  def get_qtoken
    start =  -1
    loop do
      if (@buffer.length - @idx < @@bin_size && !@inp.eof?)
        @buffer << @inp.read(@@bin_size)
        @buffer << ' ' if @inp.eof?

        if @idx > @@bin_size
          @buffer = @buffer[@idx..-1]
          @idx = 0
        end
      end

      (@idx...@buffer.length).each do |i|
        if (@buffer[i] == '"')
          if start == -1
            start = i + 1
          else
            @idx = i + 1
            return @buffer[start...i]
          end
        end
      end
    end
  end

end
