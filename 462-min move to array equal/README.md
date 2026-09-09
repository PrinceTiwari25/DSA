# LC 462 — Minimum Moves to Equal Array Elements II

## 🔗 Question Link

https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/

---

## 📝 Question

Given an integer array `nums`, return the **minimum number of moves** required to make all array elements equal.

In one move, you can **increment or decrement one element by 1**.

### Example

```text
Input:
nums = [1,2,3]

Output:
2
```

### Explanation

Make all elements equal to `2`:

```text
[1,2,3]

1 → 2    : 1 move
2 → 2    : 0 moves
3 → 2    : 1 move
```

Total:

```text
1 + 0 + 1 = 2
```

---

# 🚀 Optimal Approach — Sorting + Median

The key idea is:

> To minimize the total number of moves, make all elements equal to the **median**.

### Step 1 — Sort the array

First sort the array.

Example:

```text
[1,2,3]
```

is already sorted.

For:

```text
[1,10,2,9]
```

after sorting:

```text
[1,2,9,10]
```

---

### Step 2 — Find the Median

The median is the middle element.

For:

```text
[1,2,3]
```

the median is:

```text
2
```

For an even-sized array, either middle value can be used.

For:

```text
[1,2,9,10]
```

the middle values are:

```text
2 and 9
```

Both give the minimum total moves.

---

### Step 3 — Calculate Total Moves

For every element, calculate its distance from the median:

```text
|num - median|
```

Add all these distances.

```text
moves = Σ |num - median|
```

---

# 🔍 Dry Run

### Input

```text
nums = [1,2,3]
```

---

## Step 1 — Sort

```text
[1,2,3]
```

The array is already sorted.

---

## Step 2 — Find Median

Array:

```text
Index:  0  1  2
        ↓  ↓  ↓
       [1, 2, 3]
```

Median:

```text
nums[nums.length / 2]

= nums[3 / 2]

= nums[1]

= 2
```

Therefore:

```text
median = 2
```

---

## Step 3 — Calculate Moves

Initially:

```text
moves = 0
```

### For `num = 1`

```text
|1 - 2| = 1
```

```text
moves = 1
```

### For `num = 2`

```text
|2 - 2| = 0
```

```text
moves = 1
```

### For `num = 3`

```text
|3 - 2| = 1
```

```text
moves = 2
```

---

## 📊 Dry Run Table

| `num` | `median` | `|num - median|` | `moves` |
|------:|---------:|-----------------:|--------:|
| 1 | 2 | 1 | 1 |
| 2 | 2 | 0 | 1 |
| 3 | 2 | 1 | 2 |

Therefore:

```text
Answer = 2
```

---

# 🧠 Why Median?

Consider:

```text
[1,2,3]
```

If we choose `2`:

```text
1 → 2 = 1 move
2 → 2 = 0 moves
3 → 2 = 1 move

Total = 2
```

If we choose `1`:

```text
1 → 1 = 0
2 → 1 = 1
3 → 1 = 2

Total = 3
```

If we choose `3`:

```text
1 → 3 = 2
2 → 3 = 1
3 → 3 = 0

Total = 3
```

So the **median gives the minimum total movement**.

---

# 🔑 Key Takeaway

```text
Sort the array
      ↓
Find the median
      ↓
Calculate |num - median|
      ↓
Add all distances
      ↓
Minimum moves
```

### Remember

> **LC 462 → Median minimizes the sum of absolute differences.**

---

# ⏱️ Complexity

### Time Complexity

```text
O(n log n)
```

Because sorting takes `O(n log n)`.

### Space Complexity

```text
O(1)
```

Auxiliary space, excluding the space used internally by the sorting implementation.
