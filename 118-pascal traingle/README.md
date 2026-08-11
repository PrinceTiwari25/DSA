# Pascal's Triangle

## LeetCode Problem

[Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/)

---

## Problem

Given an integer `numRows`, return the first `numRows` of Pascal's Triangle.

In Pascal's Triangle:

- The first and last element of every row is `1`.
- Every middle element is the sum of the two elements directly above it.

### Example

**Input:**

```text
numRows = 5
```

**Output:**

```text
[
 [1],
 [1, 1],
 [1, 2, 1],
 [1, 3, 3, 1],
 [1, 4, 6, 4, 1]
]
```

---

## Solution

### Approach: Build Each Row Using the Previous Row

We build Pascal's Triangle **row by row**.

For every new row:

1. Add `1` at the beginning.
2. Calculate the middle elements using the previous row.
3. Add `1` at the end.
4. Add the current row to the answer.

For every middle element:

```text
current[j] = previous[j - 1] + previous[j]
```

The first and last elements are always `1`. :contentReference[oaicite:1]{index=1}

---


## Dry Run

### Input

```text
numRows = 5
```

### Row 1

There are no previous elements.

```text
[1]
```

---

### Row 2

The first and last elements are `1`.

```text
[1, 1]
```

---

### Row 3

Previous row:

```text
[1, 1]
```

Calculate the middle element:

```text
1 + 1 = 2
```

Current row:

```text
[1, 2, 1]
```

---

### Row 4

Previous row:

```text
[1, 2, 1]
```

Calculate the middle elements:

```text
1 + 2 = 3
2 + 1 = 3
```

Current row:

```text
[1, 3, 3, 1]
```

---

### Row 5

Previous row:

```text
[1, 3, 3, 1]
```

Calculate the middle elements:

```text
1 + 3 = 4
3 + 3 = 6
3 + 1 = 4
```

Current row:

```text
[1, 4, 6, 4, 1]
```

---

## Final Answer

```text
[
 [1],
 [1, 1],
 [1, 2, 1],
 [1, 3, 3, 1],
 [1, 4, 6, 4, 1]
]
```

---

## Complexity Analysis

### Time Complexity: O(n²)

**Reason:** We generate all elements of the triangle. The total number of elements across `n` rows is proportional to `n²`. :contentReference[oaicite:2]{index=2}

### Space Complexity: O(n²)

**Reason:** We store all rows of Pascal's Triangle in the result.

---

