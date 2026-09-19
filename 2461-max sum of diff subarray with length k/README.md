# LeetCode 2461 — Maximum Sum of Distinct Subarrays With Length K

## Question

You are given an integer array `nums` and an integer `k`.

Find the maximum sum of a subarray of length `k` such that **all elements in the subarray are distinct**.

If no such subarray exists, return `0`.

### Example

**Input:**

```text
nums = [1,5,4,2,9,9,9]
k = 3
```

**Output:**

```text
15
```

### Explanation

The subarrays of length `3` include:

```text
[1,5,4] → sum = 10
[5,4,2] → sum = 11
[4,2,9] → sum = 15
[2,9,9] → duplicate → invalid
[9,9,9] → duplicate → invalid
```

The maximum sum of a subarray containing distinct elements is:

```text
15
```

## LeetCode Link

https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/

## Approach — Sliding Window + HashSet

We use the **Sliding Window** technique along with a **HashSet**.

We need a window of exactly `k` elements where every element is distinct.

### Variables

```text
i       → left pointer of the window
j       → right pointer of the window
sum     → sum of the current window
maxsum  → maximum valid window sum
set     → stores elements currently present in the window
```

### Steps

1. Start with an empty `HashSet`.
2. Move `j` through the array.
3. If `nums[j]` is already present in the set, there is a duplicate.
4. Remove elements from the left until the duplicate is removed.
5. Add `nums[j]` to the set and update `sum`.
6. When the window size becomes exactly `k`:

   * The window contains `k` distinct elements.
   * Update `maxsum`.
7. Remove the leftmost element and move `i` forward so the next window can be formed.

### Important Idea

Instead of calculating the sum of every subarray from scratch, we maintain the current sum:

```text
New Sum = Old Sum - Removed Element + Added Element
```

This makes the solution efficient.

## Code

```java
class Solution {  
    public long maximumSubarraySum(int[] nums, int k) {  
  
        HashSet<Integer> set = new HashSet<>();  
  
        int i = 0;  
        long sum = 0;  
        long maxsum = 0;  
  
        for (int j = 0; j < nums.length; j++) {  
  
            while (set.contains(nums[j])) {  
                set.remove(nums[i]);  
                sum -= nums[i];  
                i++;  
            }  
  
            set.add(nums[j]);  
            sum += nums[j];  
  
            if (j - i + 1 == k) {  
                maxsum = Math.max(maxsum, sum);  
  
                set.remove(nums[i]);  
                sum -= nums[i];  
                i++;  
            }  
        }  
  
        return maxsum;  
    }  
}
```

## Dry Run

### Input

```text
nums = [1,5,4,2,9,9,9]
k = 3
```

We need a window of exactly `3` distinct elements.

---

### Step 1

```text
j = 0
nums[j] = 1
```

`1` is not in the set.

```text
set = [1]
sum = 1
window size = 1
```

Window:

```text
[1]
```

---

### Step 2

```text
j = 1
nums[j] = 5
```

`5` is not in the set.

```text
set = [1,5]
sum = 1 + 5 = 6
window size = 2
```

Window:

```text
[1,5]
```

---

### Step 3

```text
j = 2
nums[j] = 4
```

`4` is not in the set.

```text
set = [1,5,4]
sum = 6 + 4 = 10
window size = 3
```

The window has `k = 3` distinct elements.

```text
[1,5,4]
```

Update:

```text
maxsum = max(0, 10)
       = 10
```

Now remove the leftmost element `1`:

```text
set = [5,4]
sum = 10 - 1 = 9
i = 1
```

---

### Step 4

```text
j = 3
nums[j] = 2
```

`2` is not in the set.

```text
set = [5,4,2]
sum = 9 + 2 = 11
window size = 3
```

Window:

```text
[5,4,2]
```

Update:

```text
maxsum = max(10, 11)
       = 11
```

Remove `5`:

```text
set = [4,2]
sum = 11 - 5 = 6
i = 2
```

---

### Step 5

```text
j = 4
nums[j] = 9
```

`9` is not in the set.

```text
set = [4,2,9]
sum = 6 + 9 = 15
window size = 3
```

Window:

```text
[4,2,9]
```

Update:

```text
maxsum = max(11, 15)
       = 15
```

Remove `4`:

```text
set = [2,9]
sum = 15 - 4 = 11
i = 3
```

---

### Step 6

```text
j = 5
nums[j] = 9
```

`9` is already present in the set.

So we have a duplicate.

Current:

```text
set = [2,9]
sum = 11
```

Remove elements from the left until the duplicate is removed.

Remove `2`:

```text
set = [9]
sum = 11 - 2 = 9
i = 4
```

`9` is still present.

Remove `9`:

```text
set = []
sum = 9 - 9 = 0
i = 5
```

Now add the new `9`:

```text
set = [9]
sum = 9
```

Window:

```text
[9]
```

Size is only `1`, so it is not evaluated yet.

---

### Step 7

```text
j = 6
nums[j] = 9
```

Again, `9` is already present.

Remove the old `9`:

```text
set = []
sum = 0
i = 6
```

Add the new `9`:

```text
set = [9]
sum = 9
```

Window:

```text
[9]
```

Size is `1`, so it is not valid.

---

## Final Result

```text
15
```

The maximum valid subarray is:

```text
[4,2,9]
```

with sum:

```text
4 + 2 + 9 = 15
```

## Why Do We Use a HashSet?

The `HashSet` allows us to quickly check whether an element already exists in the current window.

```java
set.contains(nums[j])
```

Average time:

```text
O(1)
```

This is important because the problem requires every element in the window to be distinct.

## Why Do We Use `while` Instead of `if`?

We use:

```java
while (set.contains(nums[j]))
```

because removing only one element may not always be enough.

We continue removing elements from the left until `nums[j]` is no longer present in the window.

This guarantees that the window contains only distinct elements.

## Why Is `long` Used for `sum`?

The sum of the elements can become larger than the range of an `int`.

Therefore, we use:

```java
long sum
```

and:

```java
long maxsum
```

to safely store large sums.

## Complexity

### Time Complexity

```text
O(n)
```

Although there is a `while` loop inside the `for` loop, the left pointer `i` only moves forward.

Every element is added to and removed from the set at most once.

Therefore, the overall complexity is:

```text
Time = O(n)
```

### Space Complexity

```text
O(k)
```

The `HashSet` stores the elements currently present in the sliding window.

The maximum window size is `k`.

Therefore:

```text
Space = O(k)
```

## Key Idea

```text
          Right Pointer
               ↓
[ 1  5  4  2  9 ]
  ↑
Left Pointer
```

Maintain:

```text
HashSet → distinct elements
sum     → current window sum
i       → left boundary
j       → right boundary
```

When a duplicate appears:

```text
Duplicate Found
      ↓
Move left pointer
      ↓
Remove elements from Set
      ↓
Update Sum
      ↓
Until duplicate is removed
```

When window size becomes `k`:

```text
Distinct + Size k
       ↓
Update maximum sum
       ↓
Remove leftmost element
       ↓
Continue sliding
```

## Final Complexity

```text
Time  : O(n)
Space : O(k)
```

**Pattern Used:** Sliding Window + HashSet
