# Binary Search

## LeetCode Problem

[LeetCode 704 — Binary Search](https://leetcode.com/problems/binary-search/)

---

## Problem

Given an array of integers `nums` sorted in ascending order and an integer `target`, return the index of `target`.

If `target` does not exist in the array, return:

```text
-1
```

### Example

**Input:**

```text
nums = [-1,0,3,5,9,12]
target = 9
```

**Output:**

```text
4
```

Because:

```text
nums[4] = 9
```

---

# Optimal Approach: Binary Search

Since the array is already **sorted**, we don't need to check every element.

We look at the **middle element**.

### Three possibilities

```text
1. nums[mid] == target
   → Found the target

2. nums[mid] < target
   → Target must be on the RIGHT

3. nums[mid] > target
   → Target must be on the LEFT
```

Every step removes approximately half of the search space.

---

## Pointers

We use two pointers:

```text
left
right
```

Initially:

```text
left = 0
right = nums.length - 1
```

Calculate:

```text
mid = left + (right - left) / 2
```

---

# Steps

```text
1. Set left = 0.
2. Set right = n - 1.
3. Find the middle index.
4. Compare nums[mid] with target.
5. If equal → return mid.
6. If target is greater → search right half.
7. If target is smaller → search left half.
8. If left becomes greater than right → target doesn't exist.
9. Return -1.
```



---

# Dry Run

### Input

```text
nums = [-1,0,3,5,9,12]
target = 9
```

Array with indices:

```text
Index:  0   1   2   3   4   5
        ↓   ↓   ↓   ↓   ↓   ↓
       -1   0   3   5   9  12
```

Initially:

```text
left = 0
right = 5
```

---

## Step 1

Calculate:

```text
mid = 0 + (5 - 0) / 2
    = 2
```

So:

```text
nums[mid] = nums[2] = 3
```

Compare:

```text
3 vs 9
```

Since:

```text
3 < 9
```

the target must be on the **right side**.

So:

```text
left = mid + 1
left = 3
```

Now:

```text
left = 3
right = 5
```

---

## Step 2

Calculate:

```text
mid = 3 + (5 - 3) / 2
    = 4
```

So:

```text
nums[4] = 9
```

Compare:

```text
9 == 9
```

Found! ✅

Return:

```text
4
```

---

# Visualize Binary Search

```text
[-1, 0, 3, 5, 9, 12]
          ↑
         mid
```

`3 < 9`, so ignore the left side:

```text
[-1, 0, 3] | [5, 9, 12]
               ↑
              search
```

Now middle:

```text
[5, 9, 12]
     ↑
    mid
```

Found `9`.

---

# Why Is It O(log n)?

Suppose there are:

```text
16 elements
```

After every comparison:

```text
16 → 8 → 4 → 2 → 1
```

We keep cutting the search space in half.

Therefore:

```text
Time Complexity = O(log n)
```

---

# Complexity Analysis

### Time Complexity

```text
O(log n)
```

Because the search space is divided by 2 each time.

### Space Complexity

```text
O(1)
```

Only `left`, `right`, and `mid` are used.

---

# Important Conditions

### Target is greater than middle

```java
if (nums[mid] < target) {
    left = mid + 1;
}
```

Search:

```text
RIGHT
```

### Target is smaller than middle

```java
else {
    right = mid - 1;
}
```

Search:

```text
LEFT
```

### Target found

```java
if (nums[mid] == target) {
    return mid;
}
```

---

# 🧠 Key Takeaway

Binary Search works because the array is **sorted**.

Remember:

```text
              middle
                ↓
        ┌───────┴───────┐
        ↓               ↓
     smaller           bigger
        ↓               ↓
      LEFT             RIGHT
```

The complete pattern:

```text
Sorted Array
     ↓
Find Middle
     ↓
Compare Target
     ↓
┌───────────────┐
│               │
Equal         Not Equal
│               │
Found      Eliminate half
                ↓
             Repeat
```

### One-line memory trick

> **If target > middle, go right. If target < middle, go left.**

**Binary Search → O(log n) Time | O(1) Space**
