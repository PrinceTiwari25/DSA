# LeetCode 2090 — K Radius Subarray Averages

## Question

You are given a **0-indexed** array `nums` of length `n` and an integer `k`.

For each index `i`, calculate the average of the subarray centered at `i` that has radius `k`.

That means the subarray contains:

```text
nums[i-k] ... nums[i] ... nums[i+k]
```

The total number of elements in this subarray is:

```text
2 * k + 1
```

If there are not enough elements on either side of an index, the answer for that index should be `-1`.

Return the resulting array.

### Example

**Input:**

```text
nums = [7,4,3,9,1,8,5,2,6]
k = 3
```

**Output:**

```text
[-1,-1,-1,4,5,4,-1,-1,-1]
```

For example, for index `3`:

```text
[7,4,3,9,1,8,5]
```

Sum:

```text
7 + 4 + 3 + 9 + 1 + 8 + 5 = 37
```

Average:

```text
37 / 7 = 5
```

So:

```text
res[3] = 5
```

## LeetCode Link

https://leetcode.com/problems/k-radius-subarray-averages/

## Approach — Sliding Window

We use the **Sliding Window** technique.

For every valid center index, we need the sum of `2 * k + 1` elements.

Instead of calculating the sum separately for every center, we maintain a running window sum.

### Steps

1. Calculate the window size:

```text
windowSize = 2 * k + 1
```

2. Create the result array and fill it with `-1`.

3. If the window size is greater than the array length, return the result array because no valid average can be calculated.

4. Calculate the sum of the first window of size `2 * k + 1`.

5. The first valid center is at index `k`.

6. Calculate its average and store it in `res[k]`.

7. Slide the window:

   * Remove the element leaving the window.
   * Add the new element entering the window.
   * Calculate the new average.
   * Store it at the corresponding center index.

8. Return the result.

## Code

```java
class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n];
        java.util.Arrays.fill(res, -1);
        
        int idx = 2 * k + 1;
        
        if (idx > n) {
            return res;
        }
        
        long winSum = 0;
        
        for (int i = 0; i < idx; i++) {
            winSum += nums[i];
        }
        
        res[k] = (int) (winSum / idx);
        
        for (int i = idx; i < n; i++) {
            winSum = winSum - nums[i - idx] + nums[i];
            
            res[i - k] = (int) (winSum / idx);
        }
        
        return res;
    }
}
```

## Dry Run

### Input

```text
nums = [7,4,3,9,1,8,5,2,6]
k = 3
```

### Step 1 — Calculate Window Size

```text
windowSize = 2 * k + 1
           = 2 * 3 + 1
           = 7
```

So each average uses **7 elements**.

### Step 2 — Initial Result Array

Initially, every position is `-1`:

```text
res = [-1,-1,-1,-1,-1,-1,-1,-1,-1]
```

The first and last `k` positions cannot have a complete radius-`k` window.

### Step 3 — First Window

The first 7 elements are:

```text
[7, 4, 3, 9, 1, 8, 5]
```

Calculate the sum:

```text
7 + 4 + 3 + 9 + 1 + 8 + 5 = 37
```

Average:

```text
37 / 7 = 5
```

The center of this window is index `3`.

So:

```text
res[3] = 5
```

Result:

```text
[-1,-1,-1,5,-1,-1,-1,-1,-1]
```

### Step 4 — Slide the Window

Remove `7` and add `2`.

```text
Old window:
[7,4,3,9,1,8,5]

New window:
[4,3,9,1,8,5,2]
```

Update sum:

```text
37 - 7 + 2 = 32
```

Average:

```text
32 / 7 = 4
```

Center index:

```text
4
```

So:

```text
res[4] = 4
```

### Step 5 — Slide Again

Remove `4` and add `6`.

```text
Old window:
[4,3,9,1,8,5,2]

New window:
[3,9,1,8,5,2,6]
```

Update sum:

```text
32 - 4 + 6 = 34
```

Average:

```text
34 / 7 = 4
```

Center index:

```text
5
```

So:

```text
res[5] = 4
```

### Final Result

```text
[-1,-1,-1,5,4,4,-1,-1,-1]
```

## Important Note About the Example

For the input:

```text
nums = [7,4,3,9,1,8,5,2,6]
k = 3
```

the correct output is:

```text
[-1,-1,-1,5,4,4,-1,-1,-1]
```

because:

```text
index 3 → (7+4+3+9+1+8+5) / 7 = 5
index 4 → (4+3+9+1+8+5+2) / 7 = 4
index 5 → (3+9+1+8+5+2+6) / 7 = 4
```

## Why Do We Use `long`?

The window sum is stored in:

```java
long winSum
```

instead of `int`.

This is because the sum of many integer values can exceed the range of an `int`.

Using `long` makes the calculation safer.

## Why Is `res` Filled With `-1`?

```java
java.util.Arrays.fill(res, -1);
```

The first `k` and last `k` positions don't have enough elements to form a complete window of size:

```text
2 * k + 1
```

Therefore, they remain `-1`.

## Complexity

### Time Complexity

```text
O(n)
```

The first window takes `O(k)` time, and then every remaining element is processed once while sliding the window.

Overall:

```text
O(n)
```

### Space Complexity

```text
O(n)
```

The result array `res` has size `n`.

Apart from the output array, only a constant amount of extra space is used.

## Key Idea

```text
Window Size = 2k + 1

Initial Window Sum
        ↓
Calculate Average
        ↓
Remove Left Element
        +
Add New Right Element
        ↓
Update Window Sum
        ↓
Calculate Average
        ↓
Slide Again
```

The main optimization is:

```text
New Sum = Old Sum - Removed Element + Added Element
```

This avoids recalculating the entire window sum.

## Final Complexity

```text
Time  : O(n)
Space : O(n)
```
