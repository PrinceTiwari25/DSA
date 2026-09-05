# LC 16 — 3Sum Closest

## 🔗 LeetCode

https://leetcode.com/problems/3sum-closest/

---

# 📝 Question

Given an integer array `nums` of length `n` and an integer `target`, find three integers in `nums` such that their sum is **closest to the target**.

Return the sum of the three integers.

You may assume that each input has exactly one solution.

---

## Example 1

```text
Input:
nums = [-1,2,1,-4]
target = 1

Output:
2
```

### Explanation

The possible three-number sums include:

```text
(-1) + 2 + 1 = 2
(-1) + 2 + (-4) = -3
(-1) + 1 + (-4) = -4
2 + 1 + (-4) = -1
```

The target is:

```text
1
```

The closest sum is:

```text
2
```

because:

```text
|2 - 1| = 1
```

---

# 🚀 Optimal Approach — Sorting + Two Pointers

The optimal approach is based on the **3Sum two-pointer technique**.

### Step 1 — Sort the array

First sort the array.

For the example:

```text
Original:
[-1, 2, 1, -4]

After sorting:
[-4, -1, 1, 2]
```

Sorting allows us to decide how to move the pointers.

---

### Step 2 — Fix One Element

Use a loop to fix the first element.

Call its index `i`.

For every fixed `i`, use two pointers:

```text
j = i + 1
k = last index
```

So we have:

```text
nums[i] + nums[j] + nums[k]
```

---

### Step 3 — Calculate Current Sum

For every combination:

```text
currentSum = nums[i] + nums[j] + nums[k]
```

Compare it with the target.

We want the sum having the smallest difference:

```text
|currentSum - target|
```

Keep this sum as `closestSum`.

---

# 🔄 Pointer Movement

There are three cases.

### Case 1 — Current Sum = Target

```text
currentSum == target
```

This is the best possible answer.

The difference is:

```text
0
```

So immediately return the current sum.

---

### Case 2 — Current Sum < Target

```text
currentSum < target
```

The sum is too small.

Because the array is sorted, increase the sum by moving the left pointer:

```text
j++
```

---

### Case 3 — Current Sum > Target

```text
currentSum > target
```

The sum is too large.

Decrease the sum by moving the right pointer:

```text
k--
```

---

# 🔍 Dry Run — Example 1

### Input

```text
nums = [-1,2,1,-4]
target = 1
```

---

## Step 1 — Sort

```text
[-1, 2, 1, -4]
```

becomes:

```text
[-4, -1, 1, 2]
```

We start with:

```text
closestSum = -4 + (-1) + 1
           = -4
```

---

# Step 2 — Fix `i = 0`

```text
i = 0
nums[i] = -4
```

Pointers:

```text
        i   j       k
        ↓   ↓       ↓
nums = [-4,-1,  1,  2]
```

```text
j = 1
k = 3
```

Calculate:

```text
currentSum = -4 + (-1) + 2
            = -3
```

Compare with target:

```text
target = 1
```

Difference:

```text
|-3 - 1| = 4
```

Previous:

```text
|-4 - 1| = 5
```

So:

```text
closestSum = -3
```

Since:

```text
-3 < 1
```

move `j` forward:

```text
j++
```

---

## Step 3

Now:

```text
i = 0
j = 2
k = 3
```

```text
        i       j   k
        ↓       ↓   ↓
nums = [-4, -1, 1, 2]
```

Calculate:

```text
currentSum = -4 + 1 + 2
            = -1
```

Difference:

```text
|-1 - 1| = 2
```

Previous difference:

```text
|-3 - 1| = 4
```

So:

```text
closestSum = -1
```

Again:

```text
-1 < 1
```

Therefore:

```text
j++
```

Now:

```text
j = 3
k = 3
```

Since:

```text
j < k
```

is false, this two-pointer search ends.

---

# Step 4 — Fix `i = 1`

Now:

```text
i = 1
nums[i] = -1
```

Set:

```text
j = 2
k = 3
```

```text
        i   j   k
        ↓   ↓   ↓
nums = [-4,-1, 1, 2]
```

Calculate:

```text
currentSum = -1 + 1 + 2
            = 2
```

Difference from target:

```text
|2 - 1| = 1
```

Previous closest difference:

```text
|-1 - 1| = 2
```

So update:

```text
closestSum = 2
```

Now:

```text
2 > 1
```

Therefore move the right pointer:

```text
k--
```

Now:

```text
k = 2
```

Since:

```text
j < k
```

is false, this search ends.

---

# Step 5 — Remaining `i`

There are no more useful combinations.

The closest sum found is:

```text
closestSum = 2
```

---

# ✅ Final Answer

```text
2
```

Difference from target:

```text
|2 - 1| = 1
```

Therefore, `2` is the closest possible three-number sum.

---

# ⏱️ Complexity

### Time Complexity

Sorting:

```text
O(n log n)
```

Two-pointer search:

```text
O(n²)
```

Overall:

```text
O(n²)
```

### Space Complexity

Ignoring the space used internally by the sorting implementation:

```text
O(1)
```

---

# 🔑 Key Takeaway

The main idea is:

```text
Sort
  ↓
Fix one element
  ↓
Use two pointers for the remaining two
  ↓
Calculate current sum
  ↓
Keep the closest sum
  ↓
If sum < target → move left pointer
If sum > target → move right pointer
```

### Remember

```text
currentSum < target
        ↓
      j++

currentSum > target
        ↓
      k--

currentSum == target
        ↓
   Return immediately
```

**LC 16 = 3Sum + Sorting + Two Pointers + Closest Difference**
