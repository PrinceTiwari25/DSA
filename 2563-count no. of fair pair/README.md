# LC 2563 — Count the Number of Fair Pairs

## 🔗 Question Link

https://leetcode.com/problems/count-the-number-of-fair-pairs/

---

# 📝 Question

Given an integer array `nums` and two integers `lower` and `upper`, return the number of pairs `(i, j)` such that:

```text
0 <= i < j < nums.length
```

and:

```text
lower <= nums[i] + nums[j] <= upper
```

Such pairs are called **fair pairs**.

### Example

```text
Input:
nums = [0,1,7,4,4,5]
lower = 3
upper = 6

Output:
6
```

The fair pairs are:

```text
(0,3) → 0 + 4 = 4
(0,4) → 0 + 4 = 4
(0,5) → 0 + 5 = 5
(1,2) → 1 + 7 = 8 ❌
(1,3) → 1 + 4 = 5
(1,4) → 1 + 4 = 5
(1,5) → 1 + 5 = 6
(3,4) → 4 + 4 = 8 ❌
(3,5) → 4 + 5 = 9 ❌
(4,5) → 4 + 5 = 9 ❌
```

The valid pairs are:

```text
6 pairs
```

---

# 🚀 Optimal Approach — Sorting + Two Pointers

The condition is:

```text
lower <= nums[i] + nums[j] <= upper
```

Instead of directly counting pairs inside this range, break the problem into two parts.

We calculate:

```text
Number of pairs with sum <= upper
-
Number of pairs with sum < lower
```

Since:

```text
sum < lower
```

is the same as:

```text
sum <= lower - 1
```

we use:

```text
count(nums, upper) - count(nums, lower - 1)
```

---

# 💡 Main Idea

Create a helper function that counts:

> How many pairs have a sum **less than or equal to `target`**?

After sorting the array, use two pointers:

```text
left  → smallest element
right → largest element
```

---

## 🔄 How the Two Pointers Work

Calculate:

```text
nums[left] + nums[right]
```

### Case 1 — Sum <= Target

If:

```text
nums[left] + nums[right] <= target
```

Because the array is sorted, all elements between `left` and `right` are also suitable partners for `nums[left]`.

Therefore, the number of valid pairs is:

```text
right - left
```

Add this to the count:

```text
count += right - left
```

Then move:

```text
left++
```

---

### Case 2 — Sum > Target

If:

```text
nums[left] + nums[right] > target
```

The sum is too large.

Since `nums[right]` is the largest element, decrease it:

```text
right--
```

---

# 🔍 Dry Run

### Input

```text
nums = [0,1,7,4,4,5]
lower = 3
upper = 6
```

---

## Step 1 — Sort

```text
[0,1,7,4,4,5]
```

After sorting:

```text
[0,1,4,4,5,7]
```

The main calculation becomes:

```text
count(nums, 6) - count(nums, 2)
```

Because:

```text
lower - 1 = 3 - 1 = 2
```

---

# Part 1 — Count Pairs With Sum <= 6

```text
target = 6
```

Initially:

```text
left = 0
right = 5
count = 0
```

Array:

```text
       left              right
         ↓                  ↓
[0, 1, 4, 4, 5, 7]
```

### Step 1

```text
0 + 7 = 7
```

Since:

```text
7 > 6
```

Move `right`:

```text
right--
```

---

### Step 2

```text
0 + 5 = 5
```

Since:

```text
5 <= 6
```

All elements from index `1` through `4` can pair with `0`.

Number of pairs:

```text
right - left
= 4 - 0
= 4
```

Add:

```text
count = 4
```

Move:

```text
left++
```

---

### Step 3

```text
1 + 5 = 6
```

Valid.

Number of pairs:

```text
4 - 1 = 3
```

Update:

```text
count = 4 + 3
      = 7
```

Move:

```text
left++
```

---

### Step 4

```text
4 + 5 = 9
```

Too large:

```text
9 > 6
```

Move:

```text
right--
```

---

### Step 5

Now:

```text
4 + 4 = 8
```

Still too large.

Move:

```text
right--
```

Now:

```text
left = 2
right = 2
```

Stop because:

```text
left < right
```

is false.

Therefore:

```text
count(nums, 6) = 7
```

---

# Part 2 — Count Pairs With Sum <= 2

Now:

```text
target = lower - 1
       = 2
```

Array:

```text
[0,1,4,4,5,7]
```

Initially:

```text
left = 0
right = 5
count = 0
```

### Step 1

```text
0 + 7 = 7 > 2
```

Move:

```text
right--
```

Continue decreasing `right`.

Eventually:

```text
0 + 1 = 1 <= 2
```

Now:

```text
right - left
= 1 - 0
= 1
```

So:

```text
count = 1
```

Move:

```text
left++
```

Now:

```text
left = 1
right = 1
```

Stop.

Therefore:

```text
count(nums, 2) = 1
```

---

# 🧮 Final Calculation

We have:

```text
count(nums, upper)
= count(nums, 6)
= 7
```

and:

```text
count(nums, lower - 1)
= count(nums, 2)
= 1
```

Therefore:

```text
7 - 1 = 6
```

---

# ✅ Final Answer

```text
6
```

So there are **6 fair pairs**.

---

# 📊 Important Two-Pointer Rule

For the helper `count(nums, target)`:

```text
nums[left] + nums[right] <= target
                ↓
        count += right - left
                ↓
             left++
```

If:

```text
nums[left] + nums[right] > target
```

then:

```text
right--
```

---

# 🔑 Key Takeaway

The most important trick in this problem is converting:

```text
lower <= sum <= upper
```

into:

```text
count(sum <= upper)
-
count(sum <= lower - 1)
```

So:

```text
Fair Pairs
    =
Pairs ≤ upper
    -
Pairs ≤ lower - 1
```

Then use **sorting + two pointers** to count each part efficiently.

---

# ⏱️ Complexity

### Time Complexity

Sorting:

```text
O(n log n)
```

Each two-pointer traversal:

```text
O(n)
```

Overall:

```text
O(n log n)
```

### Space Complexity

```text
O(1)
```

Auxiliary space, excluding the sorting implementation.

---

## 🧠 Pattern to Remember

```text
Range [lower, upper]
        ↓
count(≤ upper) - count(≤ lower - 1)
        ↓
Sorting
        ↓
Two Pointers
```

**LC 2563 = Range Counting + Sorting + Two Pointers**