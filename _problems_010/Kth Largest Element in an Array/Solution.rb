class Solution
  def findKthLargest(nums, k)
    k = nums.length - k
    left, right = 0, nums.length - 1
    loop do
      pos = partition(nums, left, right)
      if pos == k
        return nums[k]
      elsif pos > k
        right = pos - 1
      else
        left = pos + 1
      end
    end
  end

  def partition(arr, lo, hi)
    pivot_idx, mid = 0, (lo + hi) / 2
    if arr[lo] <= arr[mid] and arr[mid] <= arr[hi]
      pivot_idx = mid
    elsif arr[mid] <= arr[lo] and arr[lo] <= arr[hi]
      pivot_idx = lo
    else
      pivot_idx = hi
    end

    pivot_val, store_idx = arr[pivot_idx], lo
    arr[pivot_idx], arr[hi] = arr[hi], arr[pivot_idx]
    (lo...hi).each do |i|
      if arr[i] < pivot_val
        arr[i], arr[store_idx] = arr[store_idx], arr[i]
        store_idx += 1
      end
    end
    arr[store_idx], arr[hi] = arr[hi], arr[store_idx]
    store_idx
  end

  private :partition
end