# LC 453 — Minimum Moves to Equal Array Elements

## 🔗 LeetCode

https://leetcode.com/problems/minimum-moves-to-equal-array-elements/

---

## 📝 Question

Given an integer array `nums`, return the **minimum number of moves** required to make all array elements equal.

In one move, you can increment **`n - 1` elements by 1**, where `n` is the size of the array.

### Example

```text
Input:
nums = [1,2,3]

Output:
3
```

### Explanation

We can make all elements equal to `3`:

```text
[1,2,3]

Move 1 → [2,3,3]
Move 2 → [3,4,3]
Move 3 → [4,4,4]
```

So the minimum number of moves is:

```text
3
```

---

# 🚀 Approach to Solve

The important observation is:

> Increasing `n - 1` elements by `1` is equivalent to decreasing the remaining one element by `1`.

So instead of thinking about increasing many elements, we can think about:

**Making every element equal to the smallest element.**

For example:

```text
nums = [1,2,3]
```

The minimum element is:

```text
min = 1
```

To make all elements equal to `1`:

```text
1 → needs 0 moves
2 → needs 1 move
3 → needs 2 moves
```

Total:

```text
0 + 1 + 2 = 3
```

Therefore:

```text
moves = Σ(num - min)
```

---

## Steps

### Step 1 — Find the minimum element

Traverse the entire array and find:

```text
min = smallest element
```

### Step 2 — Calculate the required moves

For every element:

```text
moves += num - min
```

Why?

Because `num - min` tells us how far that element is from the smallest element.

### Step 3 — Return `moves`

The total difference gives the minimum number of moves.

---

# 🔍 Dry Run

## Input

```text
nums = [1,2,3]
```

---

### Step 1 — Find Minimum

Initially:

```text
min = nums[0]
    = 1
```

Traverse the array:

```text
num = 1

min = min(1,1)
    = 1
```

Next:

```text
num = 2

min = min(1,2)
    = 1
```

Next:

```text
num = 3

min = min(1,3)
    = 1
```

Therefore:

```text
min = 1
```

---

### Step 2 — Calculate Moves

Initially:

```text
moves = 0
```

Now calculate `num - min` for every element.

### For `num = 1`

```text
moves += 1 - 1
      = 0
```

So:

```text
moves = 0
```

### For `num = 2`

```text
moves += 2 - 1
      = 1
```

So:

```text
moves = 1
```

### For `num = 3`

```text
moves += 3 - 1
      = 2
```

So:

```text
moves = 3
```

---

## 📊 Dry Run Table

| `num` | `min` | `num - min` | `moves` |
|------:|------:|------------:|--------:|
| 1 | 1 | 0 | 0 |
| 2 | 1 | 1 | 1 |
| 3 | 1 | 2 | 3 |

Therefore:

```text
Answer = 3
```

---

# 🧠 Key Idea

Instead of actually performing every move, find the **smallest element** and calculate how much larger every other element is compared to it.

```text
moves = (nums[0] - min)
      + (nums[1] - min)
      + ...
      + (nums[n-1] - min)
```

For:

```text
[1,2,3]
```

```text
(1-1) + (2-1) + (3-1)
= 0 + 1 + 2
= 3
```

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We traverse the array twice.

### Space Complexity

```text
O(1)
```

Only `min` and `moves` variables are used.

---

# 🔑 Takeaway

**LC 453 — Minimum Moves to Equal Array Elements**

```text
Find minimum
     ↓
For every number:
moves += num - minimum
     ↓
Return moves
```

**Time: O(n) | Space: O(1)**