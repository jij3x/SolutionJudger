class TwoSum
    def initialize
        @table = Hash.new 0
    end

    def add(number)
        @table[number] += 1
    end

    def find(value)
        @table.each do |k, v|
            y = value - k
            if y == k
                if v >= 2
                    return true
                end
            elsif @table.key?(y)
                return true
            end
        end
        false
    end
end