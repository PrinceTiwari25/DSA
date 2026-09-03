# 3Sum

## LeetCode Problem

[3Sum](https://leetcode.com/problems/3sum/)

---

## Problem

Given an integer array `nums`, return all the unique triplets:

```text
nums[i] + nums[j] + nums[k] = 0
```

The indices `i`, `j`, and `k` must be different.

The solution must not contain duplicate triplets.

### Example

**Input:**

```text
nums = [-1, 0, 1, 2, -1, -4]
```

**Output:**

```text
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

Because:

```text
-1 + -1 + 2 = 0
-1 + 0 + 1 = 0
```

---

## Solution

### Optimal Approach: Sorting + Two Pointers

First, sort the array.

```text
[-1, 0, 1, 2, -1, -4]
```

becomes:

```text
[-4, -1, -1, 0, 1, 2]
```

Then use three positions:

- `i` → fixed element
- `left` → starts at `i + 1`
- `right` → starts at the last index

For every fixed `nums[i]`, calculate:

```text
sum = nums[i] + nums[left] + nums[right]
```

### Three Cases

If:

```text
sum < 0
```

The sum is too small, so increase `left`:

```text
left++
```

If:

```text
sum > 0
```

The sum is too large, so decrease `right`:

```text
right--
```

If:

```text
sum == 0
```

We found a valid triplet.

Then move both pointers and skip duplicate values.

### Duplicate Handling

Since the array is sorted, duplicate values are next to each other.

We skip duplicate `i`, `left`, and `right` values so that the result contains only unique triplets.



## Dry Run

### Input

```text
nums = [-1, 0, 1, 2, -1, -4]
```

### Step 1: Sort

```text
[-4, -1, -1, 0, 1, 2]
```

---

### Step 2: Fix `-4`

```text
i = -4
left = -1
right = 2
```

Calculate:

```text
-4 + (-1) + 2 = -3
```

Since:

```text
-3 < 0
```

Move `left` forward.

No valid triplet is found with `-4`.

---

### Step 3: Fix `-1`

```text
i = -1
left = -1
right = 2
```

Calculate:

```text
-1 + (-1) + 2 = 0
```

Found:

```text
[-1, -1, 2]
```

Move both pointers.

Now:

```text
-1 + 0 + 1 = 0
```

Found:

```text
[-1, 0, 1]
```

---

### Step 4: Skip Duplicate `-1`

There are two `-1`s:

```text
[-4, -1, -1, 0, 1, 2]
     ↑   ↑
```

If we fix the second `-1` again, we could generate duplicate triplets.

So:

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

skips it.

---

## Final Answer

```text
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

---

## Complexity Analysis

### Time Complexity: O(n²)

Sorting takes `O(n log n)` and the two-pointer traversal takes `O(n²)` overall.

Therefore:

```text
O(n²)
```

### Space Complexity: O(1)

Ignoring the output, we use only constant extra space.

---

## Key Takeaway

The main pattern is:

```text
Sort
  ↓
Fix one element
  ↓
Use Two Pointers
  ↓
Calculate 3 numbers
```

Remember:

```text
sum < 0  → left++

sum > 0  → right--

sum == 0 → store triplet
```

And always **skip duplicates** to avoid duplicate triplets.