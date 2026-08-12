# Pascal's Triangle II

## LeetCode Problem

[Pascal's Triangle II](https://leetcode.com/problems/pascals-triangle-ii/)

---

## Problem

Given an integer `rowIndex`, return the `rowIndex`th row of Pascal's Triangle.

The row index is **0-indexed**.

### Example

**Input:**

```text
rowIndex = 3
```

**Output:**

```text
[1, 3, 3, 1]
```

---

## Solution

### Approach: Calculate Only the Required Row

Instead of creating the complete Pascal's Triangle, we calculate only the required row.

We start with:

```text
1
```

Then calculate each next element using the previous element.

The formula is:

```text
value = value * (rowIndex - i + 1) / i
```

This allows us to generate the required row directly without storing the previous rows.

---


## Dry Run

### Input

```text
rowIndex = 4
```

We need the 4th index row:

```text
[1, 4, 6, 4, 1]
```

Initially:

```text
value = 1
ans = [1]
```

### i = 1

```text
value = 1 × (4 - 1 + 1) / 1
      = 1 × 4 / 1
      = 4
```

```text
ans = [1, 4]
```

### i = 2

```text
value = 4 × (4 - 2 + 1) / 2
      = 4 × 3 / 2
      = 6
```

```text
ans = [1, 4, 6]
```

### i = 3

```text
value = 6 × (4 - 3 + 1) / 3
      = 6 × 2 / 3
      = 4
```

```text
ans = [1, 4, 6, 4]
```

### i = 4

```text
value = 4 × (4 - 4 + 1) / 4
      = 4 × 1 / 4
      = 1
```

```text
ans = [1, 4, 6, 4, 1]
```

### Final Answer

```text
[1, 4, 6, 4, 1]
```

---

## Complexity Analysis

### Time Complexity: O(n)

**Reason:** We calculate each element of the required row exactly once.

### Space Complexity: O(n)

**Reason:** We store only the required row in the `ans` list.

---

## Key Takeaway

For **LeetCode 118**, we build the complete Pascal's Triangle.

For **LeetCode 119**, we only need one specific row.

```text
118 → Build all rows
119 → Build only the required row
```

The important formula is:

```text
value = value * (rowIndex - i + 1) / i
```

This allows us to calculate the next element from the previous element efficiently.
