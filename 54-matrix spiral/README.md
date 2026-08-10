# Spiral Matrix

## LeetCode Problem

[Spiral Matrix](https://leetcode.com/problems/spiral-matrix/)

---

## Problem

Given an `m x n` matrix, return all elements of the matrix in **spiral order**.

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
[1, 2, 3, 6, 9, 8, 7, 4, 5]
```

---

## Solution

### Approach: Boundary Traversal

We use four boundaries to traverse the matrix layer by layer:

- `top` → first row
- `bottom` → last row
- `left` → first column
- `right` → last column

We traverse in four directions:

1. **Left → Right**
2. **Top → Bottom**
3. **Right → Left**
4. **Bottom → Top**

After each traversal, we move the corresponding boundary inward.

### Algorithm

1. Traverse the top row from `left` to `right`, then increase `top`.
2. Traverse the right column from `top` to `bottom`, then decrease `right`.
3. Traverse the bottom row from `right` to `left`, then decrease `bottom`.
4. Traverse the left column from `bottom` to `top`, then increase `left`.
5. Repeat until all elements are visited.





## Dry Run

### Input

```text
[
 [1, 2, 3],
 [4, 5, 6],
 [7, 8, 9]
]
```

Initial boundaries:

```text
top = 0
bottom = 2
left = 0
right = 2
```

### 1. Left → Right

```text
1 → 2 → 3
```

```text
ans = [1, 2, 3]
```

Update:

```text
top = 1
```

### 2. Top → Bottom

```text
6 → 9
```

```text
ans = [1, 2, 3, 6, 9]
```

Update:

```text
right = 1
```

### 3. Right → Left

```text
8 → 7
```

```text
ans = [1, 2, 3, 6, 9, 8, 7]
```

Update:

```text
bottom = 1
```

### 4. Bottom → Top

```text
4
```

```text
ans = [1, 2, 3, 6, 9, 8, 7, 4]
```

Update:

```text
left = 1
```

### 5. Inner Element

Only `5` remains:

```text
ans = [1, 2, 3, 6, 9, 8, 7, 4, 5]
```

### Final Answer

```text
[1, 2, 3, 6, 9, 8, 7, 4, 5]
```

---

## Complexity Analysis

### Time Complexity: O(m × n)

**Reason:** Every element of the matrix is visited exactly once.

### Space Complexity: O(m × n)

**Reason:** The result list stores all elements of the matrix.

---

