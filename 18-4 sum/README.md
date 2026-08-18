# 4Sum

## LeetCode Problem

[4Sum](https://leetcode.com/problems/4sum/)

---

## Problem

Given an integer array `nums` and an integer `target`, return all the unique quadruplets:

```text
nums[a] + nums[b] + nums[c] + nums[d] = target
```

The indices must be different, and the answer must not contain duplicate quadruplets.

### Example

**Input:**

```text
nums = [1,0,-1,0,-2,2]
target = 0
```

**Output:**

```text
[
    [-2,-1,1,2],
    [-2,0,0,2],
    [-1,0,0,1]
]
```

---

## Solution

### Optimal Approach: Sorting + Two Pointers

The 4Sum problem is an extension of the 3Sum approach.

```text
2Sum → Two Pointers
3Sum → Fix 1 element + Two Pointers
4Sum → Fix 2 elements + Two Pointers
```

First, sort the array:

```text
[1,0,-1,0,-2,2]
```

becomes:

```text
[-2,-1,0,0,1,2]
```

Then:

1. Fix the first element using `i`.
2. Fix the second element using `j`.
3. Use two pointers:
   ```text
   left = j + 1
   right = n - 1
   ```
4. Calculate:
   ```text
   sum = nums[i] + nums[j] + nums[left] + nums[right]
   ```
5. If `sum < target`, move `left` forward.
6. If `sum > target`, move `right` backward.
7. If `sum == target`, store the quadruplet.
8. Skip duplicate values to avoid duplicate quadruplets.

### Why Two Pointers Work

Because the array is sorted:

```text
sum < target
      ↓
Need a larger value
      ↓
left++

sum > target
      ↓
Need a smaller value
      ↓
right--
```



---

## Dry Run

### Input

```text
nums = [1,0,-1,0,-2,2]
target = 0
```

### Step 1: Sort

```text
[-2,-1,0,0,1,2]
```

---

### Step 2: Fix `i = -2` and `j = -1`

```text
-2 + (-1) + 0 + 2 = -1
```

Since:

```text
-1 < 0
```

Move `left` forward.

Eventually:

```text
-2 + (-1) + 1 + 2 = 0
```

Found:

```text
[-2,-1,1,2]
```

---

### Step 3: Fix `i = -2` and `j = 0`

We get:

```text
-2 + 0 + 0 + 2 = 0
```

Found:

```text
[-2,0,0,2]
```

---

### Step 4: Fix `i = -1`

Using:

```text
-1 + 0 + 0 + 1 = 0
```

Found:

```text
[-1,0,0,1]
```

---

## Final Answer

```text
[
    [-2,-1,1,2],
    [-2,0,0,2],
    [-1,0,0,1]
]
```

---

## Complexity Analysis

### Time Complexity: O(n³)

We have two loops to fix two elements and then use two pointers.

```text
O(n³)
```

Sorting takes `O(n log n)`, which is smaller than `O(n³)`.

### Space Complexity: O(1)

Ignoring the output, we use only constant extra space.

---

## Key Takeaway

Remember the progression:

```text
2Sum
  ↓
Two Pointers

3Sum
  ↓
Fix 1 element + Two Pointers

4Sum
  ↓
Fix 2 elements + Two Pointers
```

The main pattern:

```text
Sort
  ↓
Fix i
  ↓
Fix j
  ↓
left + right
  ↓
Compare sum with target
```

```text
sum < target  → left++

sum > target  → right--

sum == target → Store quadruplet
```

**Sorting + Two Fixed Elements + Two Pointers = O(n³)**
