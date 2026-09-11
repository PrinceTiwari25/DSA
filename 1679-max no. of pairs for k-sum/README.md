# LC 1679 — Max Number of K-Sum Pairs

## 🔗 Question Link

https://leetcode.com/problems/max-number-of-k-sum-pairs/

---

# 📝 Question

You are given an integer array `nums` and an integer `k`.

In one operation, you can choose two numbers from the array whose sum is equal to `k` and remove them from the array.

Return the **maximum number of operations** you can perform.

### Example

```text
Input:
nums = [1,2,3,4]
k = 5

Output:
2
```

### Explanation

We can make two pairs:

```text
1 + 4 = 5
2 + 3 = 5
```

Therefore:

```text
Answer = 2
```

---

# 🚀 Optimal Approach — Sorting + Two Pointers

First, sort the array.

```text
nums = [1,2,3,4]
```

The array is already sorted:

```text
[1,2,3,4]
 ↑     ↑
 i     j
```

Use two pointers:

```text
i = 0
j = nums.length - 1
```

Where:

```text
i → smallest element
j → largest element
```

Now calculate:

```text
sum = nums[i] + nums[j]
```

There are three cases.

---

## Case 1 — Sum == K

If:

```text
nums[i] + nums[j] == k
```

We found a valid pair.

So:

```text
count++
i++
j--
```

Both elements are used.

---

## Case 2 — Sum > K

If:

```text
nums[i] + nums[j] > k
```

The sum is too large.

Since `nums[j]` is the largest element, move the right pointer:

```text
j--
```

This makes the sum smaller.

---

## Case 3 — Sum < K

If:

```text
nums[i] + nums[j] < k
```

The sum is too small.

Move the left pointer:

```text
i++
```

This makes the sum larger.

---

# 🔍 Dry Run

### Input

```text
nums = [1,2,3,4]
k = 5
```

After sorting:

```text
[1,2,3,4]
```

Initially:

```text
i = 0
j = 3
count = 0
```

---

## Step 1

```text
nums[i] = 1
nums[j] = 4
```

Calculate:

```text
1 + 4 = 5
```

Since:

```text
5 == k
```

We found a pair.

```text
count++
i++
j--
```

Now:

```text
i = 1
j = 2
count = 1
```

Pair:

```text
(1,4)
```

---

## Step 2

```text
nums[i] = 2
nums[j] = 3
```

Calculate:

```text
2 + 3 = 5
```

Again:

```text
5 == k
```

So:

```text
count++
i++
j--
```

Now:

```text
i = 2
j = 1
count = 2
```

The loop stops because:

```text
i >= j
```

---

# 📊 Dry Run Table

| `i` | `j` | `nums[i]` | `nums[j]` | Sum | Action | `count` |
|---:|---:|---:|---:|---:|---|---:|
| 0 | 3 | 1 | 4 | 5 | Pair found → `i++`, `j--` | 1 |
| 1 | 2 | 2 | 3 | 5 | Pair found → `i++`, `j--` | 2 |

---

# ✅ Final Answer

```text
2
```

The two pairs are:

```text
1 + 4 = 5
2 + 3 = 5
```

So the maximum number of operations is:

```text
2
```

---

# 🔑 Key Takeaway

For a **sorted array**:

```text
sum == k
    ↓
count++
i++
j--
```

```text
sum > k
    ↓
j--
```

```text
sum < k
    ↓
i++
```

### One-Line Memory Trick

> **Sum too small → move `i` right.  
> Sum too large → move `j` left.  
> Sum equal → count the pair and move both.**

---

# ⏱️ Complexity

### Time Complexity

```text
O(n log n)
```

Sorting takes `O(n log n)` and the two-pointer traversal takes `O(n)`.

### Space Complexity

```text
O(1)
```

Auxiliary space, excluding the sorting implementation.