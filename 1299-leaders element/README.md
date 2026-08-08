# Replace Elements with Greatest Element on Right Side

## LeetCode Problem

[Replace Elements with Greatest Element on Right Side](https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/description/)

---

## Problem

Given an array `arr`, replace every element in that array with the **greatest element among the elements to its right**, and replace the last element with `-1`.

Return the resulting array.

### Example

**Input:**

```text
arr = [17, 18, 5, 4, 6, 1]
```

**Output:**

```text
[18, 6, 6, 6, 1, -1]
```

---

## Approach: Traverse from Right to Left

The optimal approach is to traverse the array **from right to left**.

We maintain a variable `maxi` that stores the greatest element found so far on the right side.

### Steps

1. Start from the **last element**.
2. Store the current `maxi`.
3. Replace the current element with `maxi`.
4. Update `maxi` using the original value of the current element.
5. Continue moving towards the left.
6. Set the last element to `-1`.

Since we need the greatest element on the **right**, traversing from right to left allows us to keep track of it efficiently.




## Dry Run

### Input

```text
arr = [17, 18, 5, 4, 6, 1]
```

We traverse from **right to left**.

| i | Current Element | max Before | arr[i] After | max After |
|---|-----------------|------------|--------------|-----------|
| 5 | 1  | -1 | -1 | 1 |
| 4 | 6  | 1  | 1  | 6 |
| 3 | 4  | 6  | 6  | 6 |
| 2 | 5  | 6  | 6  | 6 |
| 1 | 18 | 6  | 6  | 18 |
| 0 | 17 | 18 | 18 | 18 |

### Final Array

```text
[18, 6, 6, 6, 1, -1]
```

---

## Why This Approach?

A simple approach would check all elements to the right for every element.

That would take **O(n²)** time.

Instead, by traversing from right to left and maintaining `maxi`, we find the greatest element on the right in **one traversal**.

Therefore, the optimal solution takes **O(n)** time.

---

## Complexity Analysis

### Time Complexity: O(n)

**Reason:** We traverse the array only once.

### Space Complexity: O(1)

**Reason:** We modify the input array in-place and use only a few extra variables (`maxi` and `temp`).

---

