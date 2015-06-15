$LOAD_PATH << "."

require "solution"
require "scanf"

solution = Solution.new
scanf("%d").first.times do

  puts solution.my_sqrt(9)

end
