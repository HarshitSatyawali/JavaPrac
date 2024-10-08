
class Solution {
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public int partition(int[] nums, int left, int right, int pivotAt, int target) {
        swap(nums, right, pivotAt);
        int pivot = nums[right];
        int pivIdx = left;
        boolean alreadySorted = true;
        for (int i = left; i < right; ++i) {
            if (alreadySorted && i < (nums.length - 1) && nums[i + 1] < nums[i])
                alreadySorted = false;
            if (nums[i] <= pivot) {
                swap(nums, i, pivIdx);
                ++pivIdx;
            }
        }
        swap(nums, pivIdx, right);
        if (alreadySorted) {
            if (target <= right && target >= left) {
                return target;
            } else if (target > right) {
                return right;
            } else {
                return left;
            }
        }
        return pivIdx;
    }
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        int lastPivot = Integer.MIN_VALUE;
        while (true) {
            int pivotAt = l + ((int) (Math.random() * (r - l)));
            if (nums[pivotAt] == lastPivot) {
                for (int j = l; j <= r; ++j) {
                    if (nums[j] != lastPivot) {
                        pivotAt = j;
                        break;
                    }
                }
            }
            int pivIdx = partition(nums, l, r, pivotAt, nums.length - k);
            if (pivIdx == (nums.length - k))
                return nums[pivIdx];
            if (pivIdx < (nums.length - k)) {
                l = pivIdx + 1;
            } else {
                r = pivIdx - 1;
            }
            lastPivot = nums[pivIdx];
        }
    }
}
