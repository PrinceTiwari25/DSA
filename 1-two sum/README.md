# Two Sum

## LeetCode Problem

[Two Sum](https://leetcode.com/problems/two-sum/)

---

## Problem

Given an array of integers `nums` and an integer `target`, return the **indices of the two numbers** such that they add up to `target`.

You may assume that each input has exactly one solution, and you cannot use the same element twice.

### Example

**Input:**

```text
nums = [2, 7, 11, 15]
target = 9
```

**Output:**

```text
[0, 1]
```

Because:

```text
2 + 7 = 9
```

---

## Solution

### Approach: Two Pointers

The **two-pointer approach requires the array to be sorted**.

Since LeetCode asks for the **original indices**, we first store each number together with its original index.

Then sort the elements based on their values.

We use two pointers:

```text
left  → first element
right → last element
```

For every step:

- If `nums[left] + nums[right] < target` → move `left++`
- If `nums[left] + nums[right] > target` → move `right--`
- If the sum equals `target` → return their original indices.

### Why does this work?

Because the array is sorted:

```text
Smallest ← → Largest
```

If the sum is too small, we need a **larger number**, so move `left`.

If the sum is too large, we need a **smaller number**, so move `right`.

---


## Dry Run

### Input

```text
nums = [2, 7, 11, 15]
target = 9
```

Store values with their original indices:

```text
[2, 0]
[7, 1]
[11, 2]
[15, 3]
```

After sorting:

```text
Value:  2   7   11   15
Index:  0   1    2    3
        ↑            ↑
       left         right
```

---

### Step 1

```text
2 + 15 = 17
```

`17 > 9`, so we need a smaller value.

Move:

```text
right--
```

Now:

```text
Value:  2   7   11   15
        ↑        ↑
       left     right
```

---

### Step 2

```text
2 + 11 = 13
```

`13 > 9`.

Move:

```text
right--
```

Now:

```text
Value:  2   7   11   15
        ↑    ↑
       left right
```

---

### Step 3

```text
2 + 7 = 9
```

We found the target.

Original indices:

```text
2 → index 0
7 → index 1
```

Therefore:

```text
[0, 1]
```

---

## Complexity Analysis

### Time Complexity: O(n log n)

**Reason:** We sort the array in `O(n log n)` and then use two pointers in `O(n)`.

Overall:

```text
O(n log n)
```

### Space Complexity: O(n)

**Reason:** We create an array storing each value along with its original index.

---

## Key Takeaway

The two-pointer pattern is:

```text
          Sorted Array

left →  2   7   11   15  ← right
```

```text
sum < target
    ↓
left++

sum > target
    ↓
right--

sum == target
    ↓
Answer Found
```

### Remember

> **Small sum → move left forward.**  
> **Large sum → move right backward.**

For LeetCode 1, the **HashMap solution is more optimal** at `O(n)` time, but the two-pointer approach is useful for learning the two-pointer technique.
