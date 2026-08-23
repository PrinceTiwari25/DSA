# Set Mismatch

## LeetCode Problem

[Set Mismatch](https://leetcode.com/problems/set-mismatch/)

---

## Problem

You have a set of integers `1` to `n`.

Due to an error:

- One number appears **twice**.
- One number is **missing**.

Find the number that occurs twice and the number that is missing.

### Example

**Input:**

```text
nums = [1,2,2,4]
```

Here:

```text
Repeating Number = 2
Missing Number = 3
```

**Output:**

```text
[2,3]
```

---

## Solution

### Optimal Approach: Cyclic Sort / Index Placement

Since the numbers are from:

```text
1 to n
```

each number `x` should ideally be placed at:

```text
index = x - 1
```

For example:

```text
Number 1 → index 0
Number 2 → index 1
Number 3 → index 2
Number 4 → index 3
```

We rearrange the array so that every number tries to reach its correct index.

### Steps

1. Traverse the array.
2. For every number `nums[i]`, calculate its correct index:

```text
correctIndex = nums[i] - 1
```

3. If the number is not at its correct position, swap it with the number at its correct position.
4. If the correct position already contains the same number, we have found the duplicate.
5. After rearranging, traverse the array again.
6. If:

```text
nums[i] != i + 1
```

then:
- `nums[i]` is the **repeating number**
- `i + 1` is the **missing number**



---

## Dry Run

### Input

```text
nums = [1,2,2,4]
```

Correct positions should be:

```text
1 → index 0
2 → index 1
3 → index 2
4 → index 3
```

Initially:

```text
[1,2,2,4]
```

### Step 1

`1` is already at index `0`.

```text
[1,2,2,4]
 ↑
correct
```

Move forward.

---

### Step 2

`2` is already at index `1`.

```text
[1,2,2,4]
   ↑
correct
```

Move forward.

---

### Step 3

Current number:

```text
2
```

Its correct index is:

```text
2 - 1 = 1
```

But index `1` already contains `2`.

So we cannot place another `2` there.

This tells us:

```text
Repeating = 2
```

---

### Step 4

`4` belongs at index:

```text
4 - 1 = 3
```

It is already there.

Array remains:

```text
[1,2,2,4]
```

---

## Find Missing Number

Now compare every position with its expected value:

```text
Index:     0   1   2   3
Expected:  1   2   3   4
Actual:    1   2   2   4
                    ↑
```

At index `2`:

```text
Actual = 2
Expected = 3
```

Therefore:

```text
Repeating = 2
Missing = 3
```

### Final Answer

```text
[2,3]
```

---

## Complexity Analysis

### Time Complexity: O(n)

We traverse the array a constant number of times.

```text
O(n)
```

### Space Complexity: O(1)

We modify the input array in-place and use only a few variables.

```text
O(1)
```

---

## Key Takeaway

Because the numbers are from `1` to `n`:

```text
Number → Correct Index

1 → 0
2 → 1
3 → 2
4 → 3
```

So use:

```text
correctIndex = nums[i] - 1
```

After placing the numbers:

```text
nums[i] != i + 1
        ↓
nums[i] = Repeating Number
i + 1   = Missing Number
```

**Cyclic Sort / Index Placement → O(n) Time | O(1) Space**
