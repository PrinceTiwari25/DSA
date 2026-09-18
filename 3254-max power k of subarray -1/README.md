# LeetCode 3254 — Find the Power of K-Size Subarrays I
# LeetCode 3255 - Find the Power of K-Size Subarrays II

## Question

You are given an integer array `nums` and an integer `k`.

For every contiguous subarray of length `k`, find its **power**.

A subarray has power equal to its maximum element if:

* The elements are in **strictly increasing consecutive order**.
* That means every next element is exactly `1` greater than the previous element.

Otherwise, the power of the subarray is `-1`.

Return an array containing the power of every subarray of length `k`.

### Example

**Input:**

```text
nums = [1,2,3,4,3,2,5]
k = 3
```

**Subarrays:**

```text
[1,2,3] → 3
[2,3,4] → 4
[3,4,3] → -1
[4,3,2] → -1
[3,2,5] → -1
```

**Output:**

```text
[3,4,-1,-1,-1]
```

## LeetCode Link

# https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i   
# https://leetcode.com/problems/find-the-power-of-k-size-subarrays-ii/

## Approach — Sliding Window

We use the **Sliding Window** technique.

The important observation is that we don't need to check every element of every window separately.

We maintain a variable:

```text
consecutive
```

which stores the length of the current consecutive increasing sequence.

For two adjacent elements:

```text
nums[right] == nums[right - 1] + 1
```

the sequence is continuing, so we increase `consecutive`.

Otherwise, the consecutive sequence breaks, so we reset:

```text
consecutive = 1
```

Once we have a window of size `k`:

* If `consecutive >= k`, the entire window is consecutive.
* Therefore, its power is `nums[right]`, the maximum element.
* Otherwise, its power is `-1`.

### Main Idea

For a valid window:

```text
[1, 2, 3, 4]
```

the elements increase by exactly `1`.

So:

```text
1 → 2 → 3 → 4
```

The consecutive length is `4`.

If `k = 3`, every window of size `3` inside this sequence is valid.



## Dry Run

### Input

```text
nums = [1,2,3,4,3,2,5]
k = 3
```

We need to find the power of every subarray of size `3`.

---

### Step 1

```text
right = 0
nums[right] = 1
```

There is no previous element.

```text
consecutive = 1
```

Window size is not `3` yet.

---

### Step 2

```text
right = 1
nums[right] = 2
```

Check:

```text
2 == 1 + 1
```

True.

Therefore:

```text
consecutive = 2
```

---

### Step 3

```text
right = 2
nums[right] = 3
```

Check:

```text
3 == 2 + 1
```

True.

Therefore:

```text
consecutive = 3
```

Now we have a window of size `3`:

```text
[1,2,3]
```

Since:

```text
consecutive >= k
3 >= 3
```

the window is valid.

Power:

```text
nums[right] = 3
```

So:

```text
ans[0] = 3
```

---

### Step 4

```text
right = 3
nums[right] = 4
```

Check:

```text
4 == 3 + 1
```

True.

```text
consecutive = 4
```

Current window:

```text
[2,3,4]
```

Since:

```text
consecutive >= k
4 >= 3
```

the window is valid.

Power:

```text
ans[1] = 4
```

---

### Step 5

```text
right = 4
nums[right] = 3
```

Check:

```text
3 == 4 + 1
```

False.

Therefore, the consecutive sequence breaks:

```text
consecutive = 1
```

Current window:

```text
[3,4,3]
```

Since:

```text
consecutive < k
1 < 3
```

the window is invalid.

Therefore:

```text
ans[2] = -1
```

---

### Step 6

```text
right = 5
nums[right] = 2
```

Check:

```text
2 == 3 + 1
```

False.

Reset:

```text
consecutive = 1
```

Current window:

```text
[4,3,2]
```

It is not increasing by `1`.

Therefore:

```text
ans[3] = -1
```

---

### Step 7

```text
right = 6
nums[right] = 5
```

Check:

```text
5 == 2 + 1
```

False.

Reset:

```text
consecutive = 1
```

Current window:

```text
[3,2,5]
```

It is not consecutive.

Therefore:

```text
ans[4] = -1
```

---

## Final Result

```text
[3,4,-1,-1,-1]
```

## Why Does `consecutive >= k` Work?

Suppose:

```text
nums = [1,2,3,4,5]
k = 3
```

When we reach `3`:

```text
consecutive = 3
```

Window:

```text
[1,2,3]
```

Valid.

When we reach `4`:

```text
consecutive = 4
```

Current window:

```text
[2,3,4]
```

It is also valid.

When we reach `5`:

```text
consecutive = 5
```

Current window:

```text
[3,4,5]
```

It is also valid.

So we don't need to reset `consecutive` when the window moves. We only reset it when the increasing consecutive sequence actually breaks.

## Why Is `nums[right]` the Answer?

If a window is valid, its elements look like:

```text
[x, x+1, x+2, ..., x+k-1]
```

Therefore, the elements are strictly increasing.

So the maximum element is always the **last element**:

```text
nums[right]
```

That's why we store:

```java
ans[start] = nums[right];
```

## Complexity

### Time Complexity

```text
O(n)
```

We traverse the array only once.

For every element, we perform constant-time operations.

Therefore:

```text
Time = O(n)
```

### Space Complexity

```text
O(n)
```

We create the result array:

```java
int[] ans = new int[n - k + 1];
```

The extra variables such as `right`, `start`, and `consecutive` require only `O(1)` space.

Therefore, including the output array:

```text
Space = O(n)
```

## Key Idea

```text
Check adjacent elements

nums[right] == nums[right - 1] + 1
                ↓
             True
                ↓
      consecutive++
                ↓
       Window is valid?
                ↓
        consecutive >= k
                ↓
       answer = nums[right]
```

If the condition fails:

```text
nums[right] != nums[right - 1] + 1
                ↓
       consecutive = 1
                ↓
        Window becomes invalid
                ↓
           answer = -1
```

## Final Complexity

```text
Time  : O(n)
Space : O(n)
```

**Pattern Used:** Sliding Window / Consecutive Sequence Tracking
