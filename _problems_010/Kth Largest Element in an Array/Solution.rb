class Solution
  def findKthLargest(nums, k)
    k = nums.length - k
    left, right = 0, nums.length - 1
    loop do
      pos = partition(nums, left, right)
      if pos > k
        right = pos - 1
      elsif pos < k
        left = pos + 1
      else
        return nums[k]
      end
    end
  end

  def partition(nums, lo, hi)
    pivot_idx = lo
    while lo <= hi
      lo += 1 while lo <= hi and nums[lo] <= nums[pivot_idx]
      hi -= 1 while lo <= hi and nums[hi] > nums[pivot_idx]
      break if (lo > hi)
      nums[lo], nums[hi] = nums[hi], nums[lo]
    end
    nums[hi], nums[pivot_idx] = nums[pivot_idx], nums[hi]
    hi
  end

  private :partition
end