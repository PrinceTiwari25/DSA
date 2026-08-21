# Next Permutation

## LeetCode Problem

[Next Permutation](https://leetcode.com/problems/next-permutation/)

---

## Problem

Given an array of integers `nums`, rearrange the numbers into the **next lexicographically greater permutation** of numbers.

If such an arrangement is not possible, rearrange the numbers into the **lowest possible order**, which means sorting them in ascending order.

The rearrangement must be done **in-place** with constant extra memory.

### Example 1

**Input:**

```text
nums = [1,2,3]
```

**Output:**

```text
[1,3,2]
```

### Example 2

**Input:**

```text
nums = [3,2,1]
```

**Output:**

```text
[1,2,3]
```

---

## Solution

### Optimal Approach: Find Pivot + Swap + Reverse

The next permutation can be found in **three steps**.

### Step 1: Find the Pivot

Start from the right side and find the first index `i` such that:

```text
nums[i] < nums[i + 1]
```

This is the point where we can make the permutation slightly larger.

Example:

```text
[1, 2, 3]
    ↑  ↑
    i i+1
```

Here:

```text
2 < 3
```

So:

```text
pivot = 1
```

---

### Step 2: Find the Next Larger Element

Starting from the right, find the first element greater than the pivot.

For:

```text
[1,2,3]
```

Pivot:

```text
2
```

The next larger element is:

```text
3
```

Swap them:

```text
[1,3,2]
```

---

### Step 3: Reverse the Suffix

Reverse everything after the pivot.

Why?

The part after the pivot is in **descending order**.

We need the smallest possible arrangement after increasing the pivot.

So we reverse it to make it ascending.



## Dry Run

### Input

```text
nums = [1,2,3]
```

### Step 1: Find Pivot

Start from the right:

```text
[1, 2, 3]
   ↑  ↑
   2  3
```

Check:

```text
2 < 3
```

So:

```text
pivot = 1
```

---

### Step 2: Find Next Greater Element

Pivot value:

```text
2
```

From the right:

```text
3 > 2
```

So choose `3`.

Swap:

```text
[1,2,3]
```

becomes:

```text
[1,3,2]
```

---

### Step 3: Reverse Suffix

The suffix after pivot is:

```text
[2]
```

Only one element, so nothing changes.

Final:

```text
[1,3,2]
```

---

## Example 2

### Input

```text
nums = [3,2,1]
```

Find pivot:

```text
3 > 2
2 > 1
```

There is **no pivot**.

This means the array is already the largest possible permutation:

```text
[3,2,1]
```

So we reverse the entire array:

```text
[1,2,3]
```

### Final Answer

```text
[1,2,3]
```

---

## Complexity Analysis

### Time Complexity: O(n)

**Reason:** We scan the array a few times, and each scan takes `O(n)`.

Overall:

```text
O(n)
```

### Space Complexity: O(1)

**Reason:** The array is modified in-place and only a few variables are used.

---

## Key Takeaway

Remember the **3 steps**:

```text
1. Find Pivot
       ↓
2. Find Next Greater Element & Swap
       ↓
3. Reverse Suffix
```

### Important Condition

Find the pivot from the right:

```text
nums[i] < nums[i + 1]
```

Then find from the right:

```text
nums[j] > nums[i]
```

Finally:

```text
Reverse from i + 1 to end
```



**Next Permutation = Pivot + Swap + Reverse**

**Time: O(n) | Space: O(1)**
