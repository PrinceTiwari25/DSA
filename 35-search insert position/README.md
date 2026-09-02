# LC 35 — Search Insert Position

## 🔗 Problem Link

https://leetcode.com/problems/search-insert-position/

---

## 📝 Question

Given a **sorted array of distinct integers** `nums` and a `target` value:

- If `target` exists in the array, return its index.
- If `target` does not exist, return the index where it should be inserted so that the array remains sorted.

### Example 1

```text
Input:
nums = [1,3,5,6]
target = 5

Output:
2
```

Because `5` is present at index `2`.

---

### Example 2

```text
Input:
nums = [1,3,5,6]
target = 2

Output:
1
```

Because inserting `2` at index `1` gives:

```text
[1,2,3,5,6]
   ↑
 index 1
```

---

### Example 3

```text
Input:
nums = [1,3,5,6]
target = 7

Output:
4
```

Because `7` should be inserted at the end:

```text
[1,3,5,6,7]
         ↑
       index 4
```

---

# 🚀 Optimal Approach — Binary Search / Lower Bound

Since the array is already **sorted**, we can use **Binary Search**.

This problem follows the **Lower Bound** pattern.

## What is Lower Bound?

Lower Bound means:

> Find the **first index** where the element is greater than or equal to the target.

```text
nums[index] >= target
```

For example:

```text
nums   = [1,3,5,6]
target = 4
```

Check:

```text
1 < 4
3 < 4
5 >= 4  ← first element >= 4
6 >= 4
```

Therefore:

```text
Lower Bound = index 2
```

And `4` should be inserted at index `2`.

---

# 💡 Main Idea

We maintain two pointers:

```text
left  → beginning of search space
right → end of search space
```

Then calculate the middle index:

```text
mid
```

There are two important cases.

---

## Case 1: Middle Element >= Target

```text
nums[mid] >= target
```

This means `mid` **could be our answer**.

But there might be an earlier valid position on the left.

Therefore:

```text
Save mid as possible answer
        ↓
Move LEFT
```

---

## Case 2: Middle Element < Target

```text
nums[mid] < target
```

The middle element is too small.

Because the array is sorted, everything before it is also too small.

Therefore:

```text
Move RIGHT
```

---

# 🔍 Dry Run

Consider:

```text
nums = [1,3,5,6]
target = 4
```

Array with indices:

```text
Index:    0   1   2   3
          ↓   ↓   ↓   ↓
nums =   [1,  3,  5,  6]
```

We need to find the first position where:

```text
nums[index] >= 4
```

---

## Step 1

Initially:

```text
left  = 0
right = 3
```

Middle:

```text
mid = 1
```

Element:

```text
nums[1] = 3
```

Compare:

```text
3 < 4
```

`3` is too small.

Therefore, the answer must be on the **right**.

```text
left = 2
```

Search space becomes:

```text
[1,3 | 5,6]
       ↑
   search here
```

---

## Step 2

Now:

```text
left  = 2
right = 3
```

Middle:

```text
mid = 2
```

Element:

```text
nums[2] = 5
```

Compare:

```text
5 >= 4
```

So index `2` is a **possible answer**.

```text
answer = 2
```

But we want the **first** position where:

```text
nums[index] >= target
```

Therefore, we try searching further left.

```text
right = 1
```

---

## Step 3

Now:

```text
left  = 2
right = 1
```

We have:

```text
left > right
```

So Binary Search stops.

---

# ✅ Final Answer

```text
2
```

Why?

Because `4` should be inserted here:

```text
Before:

[1,3,5,6]
     ↑
   index 2
```

After inserting:

```text
[1,3,4,5,6]
     ↑
     4
```

The array remains sorted.

---

# 🧠 Another Dry Run — Target Exists

```text
nums = [1,3,5,6]
target = 5
```

We need:

```text
first element >= 5
```

Check:

```text
1 < 5
3 < 5
5 >= 5  ✅
```

Therefore:

```text
answer = 2
```

Notice that Lower Bound uses:

```text
>=
```

not just:

```text
>
```

So if the target already exists, its position can be returned.

---

# 🧠 Another Case — Target Is Largest

```text
nums = [1,3,5,6]
target = 7
```

Every element is smaller than `7`:

```text
1 < 7
3 < 7
5 < 7
6 < 7
```

So `7` should be inserted after all elements:

```text
[1,3,5,6,7]
         ↑
```

Answer:

```text
4
```

which is equal to:

```text
nums.length
```

---

# ⏱️ Complexity Analysis

## Time Complexity

```text
O(log n)
```

Binary Search eliminates half of the search space after every step.

For example:

```text
16 → 8 → 4 → 2 → 1
```

---

## Space Complexity

```text
O(1)
```

We only need a few variables.

---

# 🔑 Key Takeaway

The most important condition is:

```text
nums[mid] >= target
```

If true:

```text
Possible Answer
      ↓
Save position
      ↓
Move LEFT
```

If false:

```text
nums[mid] < target
      ↓
Too small
      ↓
Move RIGHT
```

---

# 🧠 Remember This Pattern

```text
              nums[mid]
                  ↓
          Compare with target
             /         \
            /           \
 nums[mid] >= target   nums[mid] < target
          ↓                    ↓
   Possible Answer          Too Small
          ↓                    ↓
      Move LEFT            Move RIGHT
```

### One-Line Memory Trick

> **LC 35 asks for the first position where `nums[index] >= target`.**

```text
LC 35 Search Insert Position
          =
      Lower Bound
          =
First index where nums[i] >= target
```

**Binary Search → O(log n) Time | O(1) Space**
