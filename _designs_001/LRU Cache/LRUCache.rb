class LRUCache
  def initialize(cap)
    @h, @cap = Hash.new, cap
  end

  def get(k)
    return -1 if !@h.key?(k)
    v = @h[k]
    @h.delete(k)
    @h[k] = v
  end

  def set(k, v)
    @h.delete(k)
    @h[k] = v

    if (@h.size > @cap)
      @h.reverse_each do |k, v|
        @h.delete(k)
        break
      end
    end
  end
end
