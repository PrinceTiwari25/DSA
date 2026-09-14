# LC 1343 — Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

## 🔗 Question Link

https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/

---

# 📝 Question

Given an array of integers `arr`, and two integers `k` and `threshold`.

A subarray of size `k` is considered valid if its **average is greater than or equal to `threshold`**.

Return the number of such subarrays.

### Example

```text
Input:
arr = [2,2,2,2,5,5,5,8]
k = 3
threshold = 4

Output:
3
```

### Explanation

The valid subarrays are:

```text
[2,5,5] → average = 4
[5,5,5] → average = 5
[5,5,8] → average = 6
```

Therefore:

```text
Answer = 3
```

---

# 🚀 Optimal Approach — Fixed-Size Sliding Window

Every subarray must contain exactly `k` elements.

So we use a **Sliding Window of size `k`**.

Instead of calculating the sum of every subarray from scratch, we maintain the sum of the current window.

---

## 💡 Important Observation

The condition is:

```text
average >= threshold
```

Average is:

```text
average = windowSum / k
```

Therefore:

```text
windowSum / k >= threshold
```

Multiply both sides by `k`:

```text
windowSum >= k × threshold
```

So we don't need to calculate the average.

We calculate:

```text
requiredSum = k × threshold
```

Then check:

```text
windowSum >= requiredSum
```

---

# 🔄 Steps

### Step 1 — Calculate the First Window

Take the first `k` elements and calculate their sum.

```text
windowSum
```

Check whether:

```text
windowSum >= requiredSum
```

If yes:

```text
count++
```

---

### Step 2 — Slide the Window

Move the window one position to the right.

For every new window:

```text
Add the new element
Remove the element that left the window
```

So:

```text
windowSum += new element
windowSum -= old element
```

Then check the condition again.

---

# 🔍 Dry Run

### Input

```text
arr = [2,2,2,2,5,5,5,8]
k = 3
threshold = 4
```

Calculate the required sum:

```text
requiredSum = k × threshold
            = 3 × 4
            = 12
```

So a window is valid when:

```text
windowSum >= 12
```

---

## Step 1 — First Window

First `k = 3` elements:

```text
[2,2,2]
```

Sum:

```text
windowSum = 2 + 2 + 2
          = 6
```

Check:

```text
6 >= 12 ❌
```

So:

```text
count = 0
```

---

## Step 2 — Slide Window

Remove the first `2` and add the next `2`.

```text
[2,2,2]
```

Sum:

```text
6
```

Check:

```text
6 >= 12 ❌
```

```text
count = 0
```

---

## Step 3 — Slide Window

Window:

```text
[2,2,5]
```

Sum:

```text
2 + 2 + 5 = 9
```

Check:

```text
9 >= 12 ❌
```

```text
count = 0
```

---

## Step 4 — Slide Window

Window:

```text
[2,5,5]
```

Sum:

```text
2 + 5 + 5 = 12
```

Check:

```text
12 >= 12 ✅
```

So:

```text
count = 1
```

---

## Step 5 — Slide Window

Window:

```text
[5,5,5]
```

Sum:

```text
5 + 5 + 5 = 15
```

Check:

```text
15 >= 12 ✅
```

So:

```text
count = 2
```

---

## Step 6 — Slide Window

Window:

```text
[5,5,8]
```

Sum:

```text
5 + 5 + 8 = 18
```

Check:

```text
18 >= 12 ✅
```

So:

```text
count = 3
```

---

# 📊 Dry Run Table

| Window | Window Sum | Required Sum | Valid? | Count |
|---|---:|---:|:---:|---:|
| `[2,2,2]` | 6 | 12 | ❌ | 0 |
| `[2,2,2]` | 6 | 12 | ❌ | 0 |
| `[2,2,5]` | 9 | 12 | ❌ | 0 |
| `[2,5,5]` | 12 | 12 | ✅ | 1 |
| `[5,5,5]` | 15 | 12 | ✅ | 2 |
| `[5,5,8]` | 18 | 12 | ✅ | 3 |

---

# ✅ Final Answer

```text
3
```

There are **3 subarrays** whose average is greater than or equal to `4`.

---

# 🧠 Why Sliding Window?

Without a sliding window, we would repeatedly calculate the sum of `k` elements.

That can take:

```text
O(n × k)
```

Instead, we calculate the first window once.

For every next window:

```text
Add one element → O(1)
Remove one element → O(1)
```

Therefore, we can process the entire array efficiently.

---

# ⏱️ Complexity Analysis

## Time Complexity — `O(n)`

### First Window

We calculate the sum of the first `k` elements:

```text
O(k)
```

### Remaining Windows

For every remaining element, we:

```text
Add one element → O(1)
Remove one element → O(1)
Check condition → O(1)
```

There are approximately:

```text
n - k
```

remaining elements.

Therefore:

```text
O(k) + O(n - k)
```

Which simplifies to:

```text
O(n)
```

So:

```text
Time Complexity = O(n)
```

---

## Space Complexity — `O(1)`

We only use a few variables:

```text
windowSum
count
requiredSum
```

No extra array or data structure is required.

Therefore:

```text
Space Complexity = O(1)
```

---

# 🔑 Key Takeaway

The main trick is to convert:

```text
average >= threshold
```

into:

```text
windowSum >= k × threshold
```

Then use a **fixed-size Sliding Window**.

```text
First window
     ↓
Calculate sum
     ↓
Check condition
     ↓
Remove old element
     ↓
Add new element
     ↓
Check condition
     ↓
Repeat
```

### One-Line Memory Trick

> **Fixed window of size `k` → maintain its sum → compare with `k × threshold`.**

---

# 📌 Pattern

```text
LC 1343
   ↓
Fixed-Size Sliding Window
   ↓
Maintain Window Sum
   ↓
windowSum >= k × threshold
   ↓
Count valid windows
```

**Time: O(n) | Space: O(1)**