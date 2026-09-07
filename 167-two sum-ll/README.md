# LC 167 — Two Sum II: Input Array Is Sorted

## 🔗 LeetCode

https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

---

# 📝 Question

Given a **1-indexed array of integers** `numbers` that is already sorted in **non-decreasing order**, find two numbers such that they add up to a specific `target`.

Return the indices of the two numbers.

### Important Points

- The array is **1-indexed**, so the first element has index `1`.
- There is exactly **one solution**.
- The same element cannot be used twice.
- The array is sorted in non-decreasing order.

---

## Example

```text
Input:
numbers = [2,7,11,15]
target = 9

Output:
[1,2]
```

### Explanation

```text
numbers[1] + numbers[2]
= 2 + 7
= 9
```

Therefore, the answer is:

```text
[1,2]
```

---

# 🚀 Optimal Approach — Two Pointers

Because the array is already **sorted**, we can solve the problem using two pointers.

Take:

```text
i = 0
j = numbers.length - 1
```

So initially:

```text
i → first element
j → last element
```

For:

```text
numbers = [2,7,11,15]
```

we have:

```text
 i              j
 ↓              ↓
[2, 7, 11, 15]
```

---

# 🔄 Pointer Movement

Calculate:

```text
sum = numbers[i] + numbers[j]
```

There are three cases.

### Case 1 — Sum is Smaller

If:

```text
sum < target
```

We need a **larger sum**.

Since the array is sorted, move the left pointer forward:

```text
i++
```

---

### Case 2 — Sum is Greater

If:

```text
sum > target
```

We need a **smaller sum**.

Since the array is sorted, move the right pointer backward:

```text
j--
```

---

### Case 3 — Sum Equals Target

If:

```text
sum == target
```

We have found the answer.

Return:

```text
[i + 1, j + 1]
```

The `+1` is necessary because LeetCode uses **1-based indexing**.

---

# 🔍 Dry Run

### Input

```text
numbers = [2,7,11,15]
target = 9
```

Indices:

```text
0   1   2   3
↓   ↓   ↓   ↓
[2,  7, 11, 15]
```

Initially:

```text
i = 0
j = 3
```

---

## Step 1

```text
numbers[i] = 2
numbers[j] = 15
```

Calculate:

```text
sum = 2 + 15
    = 17
```

Compare:

```text
17 > 9
```

The sum is too large.

So move the right pointer:

```text
j--
```

Now:

```text
i = 0
j = 2
```

---

## Step 2

```text
numbers[i] = 2
numbers[j] = 11
```

Calculate:

```text
sum = 2 + 11
    = 13
```

Compare:

```text
13 > 9
```

Again, the sum is too large.

Move the right pointer:

```text
j--
```

Now:

```text
i = 0
j = 1
```

---

## Step 3

```text
numbers[i] = 2
numbers[j] = 7
```

Calculate:

```text
sum = 2 + 7
    = 9
```

Compare:

```text
9 == 9
```

Target found! ✅

Current **0-based** indices:

```text
i = 0
j = 1
```

But the question requires **1-based** indices.

Therefore:

```text
i + 1 = 1
j + 1 = 2
```

---

# ✅ Final Answer

```text
[1,2]
```

Because:

```text
numbers[1] + numbers[2]
= 2 + 7
= 9
```

---

# 🧠 Why Two Pointers Work

The array is sorted:

```text
[2, 7, 11, 15]
 ↑              ↑
smallest       largest
```

If the sum is too large:

```text
2 + 15 = 17
```

Moving the left pointer would make the sum even larger.

So we move the **right pointer**.

If the sum is too small:

```text
2 + 7 = 9
```

If it were smaller than the target, we would move the **left pointer** to get a larger value.

This allows us to eliminate many unnecessary combinations.

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Each pointer moves through the array at most once.

### Space Complexity

```text
O(1)
```

Only two pointers and a few variables are used.

---

# 🔑 Key Takeaway

For a **sorted array**, remember:

```text
sum < target
     ↓
   i++

sum > target
     ↓
   j--

sum == target
     ↓
  Answer
```

### One-Line Memory Trick

> **Small sum → move left pointer right.  
> Large sum → move right pointer left.**

**LC 167 = Sorted Array + Two Pointers + O(n) Time + O(1) Space**