# LC 11 — Container With Most Water

## 🔗 LeetCode

https://leetcode.com/problems/container-with-most-water/

---

# 📝 Question

You are given an integer array `height` where:

- `height[i]` represents the height of a vertical line.
- The width between two lines is the difference between their indices.

Choose **two lines** that, together with the x-axis, form a container that holds the **most water**.

Return the **maximum amount of water** the container can store.

### Example 1

```text
Input:
height = [1,8,6,2,5,4,8,3,7]

Output:
49
```

The two lines with heights `8` and `7` form the container that holds the maximum water.

---

# 🚀 Optimal Approach — Two Pointers

Instead of checking every possible pair of lines, use **two pointers**.

Initialize:

```text
lp = 0
rp = height.length - 1
```

So:

```text
lp → leftmost line
rp → rightmost line
```

---

## 💧 Calculate Water

For every pair of pointers:

### Height of container

The water level is limited by the **shorter line**.

```text
h = min(height[lp], height[rp])
```

### Width of container

```text
w = rp - lp
```

### Current water

```text
currentwater = h × w
```

Update the maximum:

```text
maxwater = max(maxwater, currentwater)
```

---

# 🔄 How Do We Move the Pointers?

This is the most important part.

If:

```text
height[lp] < height[rp]
```

Move the left pointer:

```text
lp++
```

Otherwise:

```text
rp--
```

### Why?

The amount of water depends on:

```text
min(height[lp], height[rp]) × width
```

If the left line is shorter, keeping it while reducing the width cannot give us a better container.

So we move the **shorter line** and try to find a taller one.

---

# 🔍 Dry Run — Example 1

```text
height = [1,8,6,2,5,4,8,3,7]
```

Indices:

```text
 0  1  2  3  4  5  6  7  8
[1, 8, 6, 2, 5, 4, 8, 3, 7]
 ↑                          ↑
lp                          rp
```

Initially:

```text
lp = 0
rp = 8
maxwater = 0
```

---

## Step 1

```text
height[lp] = 1
height[rp] = 7
```

Height:

```text
h = min(1,7) = 1
```

Width:

```text
w = 8 - 0 = 8
```

Water:

```text
currentwater = 1 × 8 = 8
```

Update:

```text
maxwater = 8
```

Since:

```text
height[lp] < height[rp]
1 < 7
```

Move left pointer:

```text
lp++
```

Now:

```text
lp = 1
rp = 8
```

---

## Step 2

```text
height[lp] = 8
height[rp] = 7
```

```text
h = min(8,7) = 7
w = 8 - 1 = 7
```

```text
currentwater = 7 × 7 = 49
```

Update:

```text
maxwater = 49
```

Now:

```text
height[lp] < height[rp]
8 < 7 ❌
```

So move the right pointer:

```text
rp--
```

Now:

```text
lp = 1
rp = 7
```

---

## Step 3

```text
height[lp] = 8
height[rp] = 3
```

```text
h = min(8,3) = 3
w = 7 - 1 = 6
```

```text
currentwater = 3 × 6 = 18
```

`18` is smaller than `49`.

```text
maxwater = 49
```

Since:

```text
8 < 3 ❌
```

Move right:

```text
rp--
```

Now:

```text
lp = 1
rp = 6
```

---

## Remaining Steps

Following the exact same logic:

| `lp` | `rp` | `height[lp]` | `height[rp]` | Width | Water | `maxwater` |
|---:|---:|---:|---:|---:|---:|---:|
| 0 | 8 | 1 | 7 | 8 | 8 | 8 |
| 1 | 8 | 8 | 7 | 7 | **49** | **49** |
| 1 | 7 | 8 | 3 | 6 | 18 | 49 |
| 1 | 6 | 8 | 8 | 5 | 40 | 49 |
| 1 | 5 | 8 | 4 | 4 | 16 | 49 |
| 1 | 4 | 8 | 5 | 3 | 15 | 49 |
| 1 | 3 | 8 | 2 | 2 | 4 | 49 |
| 1 | 2 | 8 | 6 | 1 | 6 | 49 |

Eventually:

```text
rp <= lp
```

The loop stops.

---

# ✅ Final Answer

```text
49
```

The maximum container is formed by:

```text
height[1] = 8
height[8] = 7
```

Width:

```text
8 - 1 = 7
```

Height:

```text
min(8,7) = 7
```

Therefore:

```text
Water = 7 × 7
      = 49
```

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Each pointer moves from one side toward the other only once.

### Space Complexity

```text
O(1)
```

Only a few variables are used.

---

# 🔑 Key Takeaway

Remember the main formula:

```text
Water = min(left height, right height) × (right index - left index)
```

And the main pointer rule:

```text
If left height < right height:
        move left pointer

Else:
        move right pointer
```

### One-Line Memory Trick

> **Calculate water → update maximum → move the shorter line.**

This gives the optimal **Two Pointer** solution in `O(n)` time.
