class Solution
  def removeDuplicates(arr)
    return 0 if arr.nil? or arr.empty?

    len = 1
    (1...arr.length).each do |i|
      if arr[len - 1] != arr[i]
        arr[len] = arr[i]
        len += 1
      end
    end
    len
  end
end
