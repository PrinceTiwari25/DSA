# Triangle

## LeetCode Problem

[Triangle](https://leetcode.com/problems/triangle/)

---

## Problem

Given a triangle array, return the **minimum path sum** from top to bottom.

For each step, you may move to an adjacent number in the row below.

### Example

**Input:**

```text
[
    [2],
   [3,4],
  [6,5,7],
 [4,1,8,3]
]
```

**Output:**

```text
11
```

### Explanation

The minimum path is:

```text
2 → 3 → 5 → 1
```

Sum:

```text
2 + 3 + 5 + 1 = 11
```

---

## Solution

### Optimal Approach: Bottom-Up Dynamic Programming

Instead of starting from the top and trying every possible path, we start from the **bottom of the triangle**.

For every element, we calculate the minimum path sum from that element to the bottom.

For example:

```text
        2
       3 4
      6 5 7
     4 1 8 3
```

Start from the last row:

```text
4 1 8 3
```

For the row above:

```text
6 → min(4,1) + 6 = 7
5 → min(1,8) + 5 = 6
7 → min(8,3) + 7 = 10
```

So:

```text
7 6 10
```

Next row:

```text
3 → min(7,6) + 3 = 9
4 → min(6,10) + 4 = 10
```

So:

```text
9 10
```

Finally:

```text
2 → min(9,10) + 2 = 11
```

Therefore, the answer is:

```text
11
```



## Dry Run

### Input

```text
[
    [2],
   [3,4],
  [6,5,7],
 [4,1,8,3]
]
```

### Step 1: Start with the last row

```text
dp = [4, 1, 8, 3]
```

---

### Step 2: Process `[6,5,7]`

For `6`:

```text
6 + min(4,1)
= 6 + 1
= 7
```

For `5`:

```text
5 + min(1,8)
= 5 + 1
= 6
```

For `7`:

```text
7 + min(8,3)
= 7 + 3
= 10
```

Now:

```text
dp = [7, 6, 10, 3]
```

---

### Step 3: Process `[3,4]`

For `3`:

```text
3 + min(7,6)
= 3 + 6
= 9
```

For `4`:

```text
4 + min(6,10)
= 4 + 6
= 10
```

Now:

```text
dp = [9, 10, 10, 3]
```

---

### Step 4: Process `[2]`

```text
2 + min(9,10)
= 2 + 9
= 11
```

Now:

```text
dp = [11, 10, 10, 3]
```

### Final Answer

```text
11
```

---

## Complexity Analysis

### Time Complexity: O(n²)

**Reason:** We visit every element of the triangle once.

### Space Complexity: O(n)

**Reason:** We use a single `dp` array containing one row of values.

---

## Key Takeaway

The main idea is:

```text
Current value + minimum of the two children
```

For every element:

```text
dp[j] = triangle[i][j] + min(dp[j], dp[j + 1])
```

Work **from bottom to top** so that when we process an element, we already know the minimum path sums of its two children.

```text
        2
       / \
      3   4
     / \ / \
    6  5 7
```

For `2`:

```text
2 + min(path through 3, path through 4)
```

So the minimum path is:

```text
2 → 3 → 5 → 1 = 11
```
