# Reverse Pairs

## LeetCode Problem

[Reverse Pairs](https://leetcode.com/problems/reverse-pairs/)

---

## Problem

Given an integer array `nums`, return the number of **reverse pairs**.

A reverse pair is a pair of indices `(i, j)` such that:

```text
i < j
```

and

```text
nums[i] > 2 * nums[j]
```

### Example

**Input:**

```text
nums = [1,3,2,3,1]
```

**Output:**

```text
2
```

The reverse pairs are:

```text
(3, 1)
(3, 1)
```

Because:

```text
3 > 2 × 1
```

---

## Solution

### Optimal Approach: Merge Sort

A brute-force approach checks every possible pair:

```text
for i
    for j
```

This takes:

```text
O(n²)
```

Instead, we use **Merge Sort** to solve the problem in:

```text
O(n log n)
```

### Main Idea

Merge Sort divides the array into two halves.

After recursively sorting both halves:

```text
Left Half  → sorted
Right Half → sorted
```

We count the reverse pairs between the two halves before merging them.

For every element in the left half, check:

```text
nums[i] > 2 * nums[j]
```

Since both halves are sorted, once the condition becomes false, we can stop for that `i`.

This allows us to count multiple reverse pairs efficiently.

---

## Steps

```text
1. Divide the array into two halves.
2. Count reverse pairs in the left half.
3. Count reverse pairs in the right half.
4. Count reverse pairs between the two halves.
5. Merge the two sorted halves.
```

The total count is:

```text
left pairs + right pairs + cross pairs
```




---

## Dry Run

### Input

```text
nums = [1,3,2,3,1]
```

The array is divided using Merge Sort.

Eventually, we get comparisons between sorted halves.

For example:

```text
Left  = [3]
Right = [1]
```

Check:

```text
3 > 2 × 1
```

```text
3 > 2
```

True, so:

```text
count = 1
```

Another `3` and `1` pair also satisfies:

```text
3 > 2 × 1
```

So:

```text
count = 2
```

Therefore:

```text
Answer = 2
```

---

## Important Part

This condition:

```java
nums[i] > 2L * nums[j]
```

uses `2L` instead of `2`.

This converts the multiplication to `long` and prevents integer overflow for large values.

---

## Why Does `j - (mid + 1)` Work?

Suppose:

```text
Left  = [3, 4]
Right = [1, 2]
```

For `3`:

```text
3 > 2 × 1  ✓
3 > 2 × 2  ✓
```

So `3` forms **2 reverse pairs**.

If `j` has moved two positions:

```text
j - (mid + 1) = 2
```

Therefore, we can count both pairs at once.

---

## Complexity Analysis

### Time Complexity: O(n log n)

Merge Sort divides the array into `log n` levels, and each level processes `n` elements.

```text
O(n log n)
```

### Space Complexity: O(n)

The temporary array used during merging requires:

```text
O(n)
```

---

## Key Takeaway

### Normal Inversion

```text
nums[i] > nums[j]
```

### Reverse Pair

```text
nums[i] > 2 * nums[j]
```

The main pattern is:

```text
Merge Sort
     ↓
Sort both halves
     ↓
Count cross reverse pairs
     ↓
Merge
```

Remember:

```java
while (j <= right && nums[i] > 2L * nums[j]) {
    j++;
}

count += j - (mid + 1);
```

**Merge Sort → O(n log n) Time | O(n) Space**
