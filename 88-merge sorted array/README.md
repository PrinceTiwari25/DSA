# Merge Sorted Array

## LeetCode Problem

[LeetCode 88 — Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)

---

## Problem

You are given two sorted integer arrays `nums1` and `nums2`.

- `nums1` has enough space at the end to store all elements of `nums2`.
- `m` represents the number of valid elements in `nums1`.
- `n` represents the number of elements in `nums2`.

Merge `nums2` into `nums1` so that `nums1` becomes sorted.

The merge must be done **in-place**.

### Example

**Input:**

```text
nums1 = [1,2,3,0,0,0]
m = 3

nums2 = [2,5,6]
n = 3
```

**Output:**

```text
[1,2,2,3,5,6]
```

---

## Optimal Approach: Three Pointers from the Back

Since `nums1` already contains its valid elements at the beginning and has empty spaces at the end, we merge from the **back**.

Use three pointers:

```text
i = m - 1
j = n - 1
k = m + n - 1
```

Where:

```text
i → last valid element of nums1
j → last element of nums2
k → last position of nums1
```

### Why Start from the Back?

If we start from the front, we may overwrite the existing elements of `nums1`.

Starting from the back allows us to use the empty positions safely.

---

## Steps

Compare:

```text
nums1[i] and nums2[j]
```

### If `nums1[i] > nums2[j]`

Place `nums1[i]` at position `k`:

```text
nums1[k] = nums1[i]
```

Then:

```text
i--
k--
```

### Otherwise

Place `nums2[j]` at position `k`:

```text
nums1[k] = nums2[j]
```

Then:

```text
j--
k--
```

Continue until all elements of `nums2` are placed.



---

## Dry Run

### Input

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]
```

Initially:

```text
i = 2 → 3
j = 2 → 6
k = 5
```

### Step 1

Compare:

```text
3 vs 6
```

`6` is larger.

```text
nums1[5] = 6
```

Array:

```text
[1,2,3,0,0,6]
```

---

### Step 2

Compare:

```text
3 vs 5
```

`5` is larger.

```text
[1,2,3,0,5,6]
```

---

### Step 3

Compare:

```text
3 vs 2
```

`3` is larger.

```text
[1,2,3,3,5,6]
```

---

### Step 4

Compare:

```text
2 vs 2
```

Place `2` from `nums2`:

```text
[1,2,2,3,5,6]
```

Now all elements of `nums2` are placed.

### Final Answer

```text
[1,2,2,3,5,6]
```

---

## Complexity Analysis

### Time Complexity: O(m + n)

Each element is processed at most once.

### Space Complexity: O(1)

The merge is performed directly inside `nums1`, so no extra array is required.

---

## Key Takeaway

The main trick is:

```text
Merge from the BACK
```

Use:

```text
i → nums1
j → nums2
k → final position
```

Pattern:

```text
Compare largest elements
        ↓
Put larger element at k
        ↓
Move pointer backward
```

```text
nums1[i] > nums2[j]
        ↓
nums1[k] = nums1[i]

Otherwise
        ↓
nums1[k] = nums2[j]
```

### Remember

> **When merging sorted arrays in-place, start from the end so existing elements in `nums1` are not overwritten.**

**Three Pointers → O(m+n) Time | O(1) Space**
