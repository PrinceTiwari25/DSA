# Rearrange Array Elements by Sign

## LeetCode Problem

[Rearrange Array Elements by Sign](https://leetcode.com/problems/rearrange-array-elements-by-sign/)

---

## Problem

Given an array `nums` containing an equal number of positive and negative integers, rearrange the array so that:

- Every positive integer is followed by a negative integer.
- The relative order of positive integers remains the same.
- The relative order of negative integers remains the same.
- The array starts with a positive integer.

### Example

**Input:**

```text
nums = [3, 1, -2, -5, 2, -4]
```

**Output:**

```text
[3, -2, 1, -5, 2, -4]
```

---

## Solution

### Approach: Two Pointers

We create a new array `ans` and use two pointers:

Use **even indices for positive numbers** and **odd indices for negative numbers**.

- `pos` → points to the next even index where a positive number should be placed.
- `neg` → points to the next odd index where a negative number should be placed.

Initially:

```text
pos = 0
neg = 1
```

So positive numbers will be placed at:

```text
0, 2, 4, 6...
```

And negative numbers will be placed at:

```text
1, 3, 5, 7...
```

While traversing the original array:

- If the number is positive, place it at `ans[pos]` and increase `pos` by `2`.
- If the number is negative, place it at `ans[neg]` and increase `neg` by `2`.

This automatically maintains the required alternating order.

---



## Dry Run

### Input

```text
nums = [3, 1, -2, -5, 2, -4]
```

Initially:

```text
pos = 0
neg = 1
```

| Element | Type | Position | Array |
|---------|------|----------|-------|
| 3 | Positive | `ans[0]` | `[3, _, _, _, _, _]` |
| 1 | Positive | `ans[2]` | `[3, _, 1, _, _, _]` |
| -2 | Negative | `ans[1]` | `[3, -2, 1, _, _, _]` |
| -5 | Negative | `ans[3]` | `[3, -2, 1, -5, _, _]` |
| 2 | Positive | `ans[4]` | `[3, -2, 1, -5, 2, _]` |
| -4 | Negative | `ans[5]` | `[3, -2, 1, -5, 2, -4]` |

### Final Answer

```text
[3, -2, 1, -5, 2, -4]
```

---

## Complexity Analysis

### Time Complexity: O(n)

**Reason:** We traverse the array exactly once.

### Space Complexity: O(n)

**Reason:** We create a new array `ans` of size `n`.

---

