# Maximum Subarray

## LeetCode Problem

[Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)

---

## Problem

Given an integer array `nums`, find the subarray with the largest sum and return its sum.

A **subarray** is a contiguous part of the array containing at least one element.

### Example

**Input:**

```text
nums = [-2,1,-3,4,-1,2,1,-5,4]
```

**Output:**

```text
6
```

The subarray with the maximum sum is:

```text
[4,-1,2,1]
```

Sum:

```text
4 + (-1) + 2 + 1 = 6
```

---

## Solution

### Optimal Approach: Kadane's Algorithm

We use two variables:

```text
currentSum
maxSum
```

`currentSum` stores the **maximum sum of a subarray ending at the current position**.

For every element, we have two choices:

```text
1. Start a new subarray from nums[i]
2. Continue the previous subarray
```

So we calculate:

```text
currentSum = max(nums[i], currentSum + nums[i])
```

Then update the overall maximum:

```text
maxSum = max(maxSum, currentSum)
```

### Key Idea

If the previous `currentSum` is negative, carrying it forward will only decrease the sum.

So we start a new subarray.

```text
Negative previous sum
        ↓
Discard it
        ↓
Start from current element
```



## Dry Run

### Input

```text
nums = [-2,1,-3,4,-1,2,1,-5,4]
```

Initially:

```text
currentSum = -2
maxSum = -2
```

| Element | `currentSum` | `maxSum` |
|---:|---:|---:|
| -2 | -2 | -2 |
| 1 | 1 | 1 |
| -3 | -2 | 1 |
| 4 | 4 | 4 |
| -1 | 3 | 4 |
| 2 | 5 | 5 |
| 1 | 6 | 6 |
| -5 | 1 | 6 |
| 4 | 5 | 6 |

### Important Steps

When we reach `4`:

```text
currentSum = -2 + 4 = 2
```

But starting fresh gives:

```text
4
```

So:

```text
currentSum = 4
```

Later:

```text
4 + (-1) + 2 + 1 = 6
```

Therefore:

```text
maxSum = 6
```

---

## Final Answer

```text
6
```

Maximum subarray:

```text
[4,-1,2,1]
```

---

## Complexity Analysis

### Time Complexity: O(n)

**Reason:** We traverse the array only once.

### Space Complexity: O(1)

**Reason:** We use only two variables, `currentSum` and `maxSum`.

---

## Key Takeaway

Kadane's Algorithm follows this simple rule:

```text
currentSum =
max(
    current element,
    previous currentSum + current element
)
```

Then:

```text
maxSum = max(maxSum, currentSum)
```

### Remember

> **If the previous sum helps, continue. If it hurts, start fresh.**

```text
Kadane's Algorithm
       ↓
Track current best sum
       ↓
Track overall best sum
       ↓
O(n) Time
O(1) Space
```
