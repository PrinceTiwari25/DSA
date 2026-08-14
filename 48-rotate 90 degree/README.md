# Rotate Image

## LeetCode Problem

[Rotate Image](https://leetcode.com/problems/rotate-image/)

---

## Problem

Given an `n x n` 2D matrix, rotate the matrix **90 degrees clockwise**.

You must rotate the matrix **in-place**, which means you cannot create another matrix.

### Example

**Input:**

```text
[
 [1, 2, 3],
 [4, 5, 6],
 [7, 8, 9]
]
```

**Output:**

```text
[
 [7, 4, 1],
 [8, 5, 2],
 [9, 6, 3]
]
```

---

## Solution

### Optimal Approach: Transpose + Reverse

We can rotate the matrix 90 degrees clockwise using two steps:

### Step 1: Transpose the Matrix

Transpose means converting **rows into columns**.

```text
1  2  3
4  5  6
7  8  9
```

After transpose:

```text
1  4  7
2  5  8
3  6  9
```

We do this by swapping:

```text
matrix[i][j] ↔ matrix[j][i]
```

---

### Step 2: Reverse Every Row

Now reverse each row:

```text
1  4  7  →  7  4  1
2  5  8  →  8  5  2
3  6  9  →  9  6  3
```

Final matrix:

```text
7  4  1
8  5  2
9  6  3
```

Therefore:

```text
Transpose + Reverse each row = 90° Clockwise Rotation
```



## Dry Run

### Input

```text
1  2  3
4  5  6
7  8  9
```

### Step 1: Transpose

Swap:

```text
2 ↔ 4
3 ↔ 7
6 ↔ 8
```

Matrix becomes:

```text
1  4  7
2  5  8
3  6  9
```

---

### Step 2: Reverse Each Row

First row:

```text
1  4  7
↓
7  4  1
```

Second row:

```text
2  5  8
↓
8  5  2
```

Third row:

```text
3  6  9
↓
9  6  3
```

---

### Final Answer

```text
7  4  1
8  5  2
9  6  3
```

---

## Complexity Analysis

### Time Complexity: O(n²)

**Reason:** We visit the matrix elements while transposing and reversing each row.

### Space Complexity: O(1)

**Reason:** We modify the matrix in-place and use only temporary variables.

---

## Key Takeaway

For **90° clockwise rotation**:

```text
Original Matrix
      ↓
   Transpose
      ↓
Reverse Every Row
      ↓
Rotated Matrix
```

Remember:

```text
Transpose + Reverse Rows = 90° Clockwise
```
