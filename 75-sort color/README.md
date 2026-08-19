# Sort Colors

## LeetCode Problem

[Sort Colors](https://leetcode.com/problems/sort-colors/)

---

## Problem

Given an array `nums` containing only `0`, `1`, and `2`, sort the array **in-place** so that all `0`s come first, followed by all `1`s, and then all `2`s.

You must solve the problem without using the built-in sorting function.

### Example

**Input:**

```text
nums = [2, 0, 2, 1, 1, 0]
```

**Output:**

```text
[0, 0, 1, 1, 2, 2]
```

---

## Solution

### Optimal Approach: Dutch National Flag Algorithm

We use **three pointers**:

```text
low
mid
high
```

They divide the array into four sections:

```text
0s        1s          Unknown          2s
[0 ... low-1] [low ... mid-1] [mid ... high] [high+1 ...]
```

Initially:

```text
low = 0
mid = 0
high = nums.length - 1
```

Now check `nums[mid]`.

### Case 1: `nums[mid] == 0`

`0` belongs at the beginning.

Swap:

```text
nums[low] ↔ nums[mid]
```

Then:

```text
low++
mid++
```

---

### Case 2: `nums[mid] == 1`

`1` belongs in the middle.

So simply:

```text
mid++
```

---

### Case 3: `nums[mid] == 2`

`2` belongs at the end.

Swap:

```text
nums[mid] ↔ nums[high]
```

Then:

```text
high--
```

Do **not** increment `mid` because the element coming from `high` has not been checked yet.



## Dry Run

### Input

```text
[2, 0, 2, 1, 1, 0]
```

Initially:

```text
low = 0
mid = 0
high = 5
```

### Step 1

`nums[mid] = 2`

Swap with `high`:

```text
[0, 0, 2, 1, 1, 2]
```

```text
high--
```

`mid` stays at `0`.

---

### Step 2

`nums[mid] = 0`

Swap with `low`:

```text
[0, 0, 2, 1, 1, 2]
```

Then:

```text
low++
mid++
```

---

### Step 3

`nums[mid] = 0`

Move it to the `0` section:

```text
[0, 0, 2, 1, 1, 2]
```

Then:

```text
low++
mid++
```

---

### Step 4

`nums[mid] = 2`

Swap with `high`:

```text
[0, 0, 1, 1, 2, 2]
```

Then:

```text
high--
```

---

### Step 5

`nums[mid] = 1`

`1` is already in the correct section:

```text
mid++
```

---

### Step 6

`nums[mid] = 1`

Again:

```text
mid++
```

Now:

```text
mid > high
```

Stop.

### Final Answer

```text
[0, 0, 1, 1, 2, 2]
```

---

## Complexity Analysis

### Time Complexity: O(n)

**Reason:** We traverse the array only once.

### Space Complexity: O(1)

**Reason:** We sort the array in-place using only three pointers.

---

## Key Takeaway

Remember these three rules:

```text
0 → swap with low → low++, mid++

1 → mid++

2 → swap with high → high--
```

The most important point:

> When `nums[mid] == 2`, **do not increment `mid`**, because the new element swapped from `high` still needs to be checked.

```text
Dutch National Flag Algorithm
        ↓
Three Pointers
        ↓
One Pass
        ↓
O(n) Time + O(1) Space
```
