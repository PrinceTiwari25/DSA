# LeetCode 209 — Minimum Size Subarray Sum

## Question

Given an array of **positive integers** `nums` and a positive integer `target`, find the length of the **smallest contiguous subarray** whose sum is greater than or equal to `target`.

If no such subarray exists, return `0`.

### Example

**Input:**

```text
target = 7
nums = [2,3,1,2,4,3]
```

**Output:**

```text
2
```

### Explanation

The subarrays whose sum is at least `7` include:

```text
[2,3,1,2]     → sum = 8   → length = 4
[3,1,2,4]     → sum = 10  → length = 4
[1,2,4]       → sum = 7   → length = 3
[2,4,3]       → sum = 9   → length = 3
[4,3]         → sum = 7   → length = 2
```

The smallest valid subarray is:

```text
[4,3]
```

Therefore, the answer is:

```text
2
```

## LeetCode Link

https://leetcode.com/problems/minimum-size-subarray-sum/

## Approach — Sliding Window

We use the **Sliding Window** technique.

The goal is to find the **minimum length** subarray whose sum is at least `target`.

Because all elements in `nums` are positive, we can safely shrink the window from the left whenever the current sum becomes greater than or equal to `target`.

### Variables

```text
i                  → left pointer
j                  → right pointer
currentSum         → sum of the current window
minWindowLength    → minimum valid window length
```

### Steps

1. Start with `i = 0`.
2. Move `j` through the array.
3. Add `nums[j]` to `currentSum`.
4. Whenever:

```text
currentSum >= target
```

the current window is valid.

5. Calculate its length:

```text
j - i + 1
```

6. Update the minimum length.
7. Remove `nums[i]` from the sum and move `i` forward.
8. Continue shrinking while the window still satisfies the target.
9. If no valid window was found, return `0`.



## Dry Run

### Input

```text
target = 7
nums = [2,3,1,2,4,3]
```

We need the smallest window whose sum is at least `7`.

---

### Step 1

```text
j = 0
nums[j] = 2
```

Add `2`:

```text
currentSum = 2
```

Since:

```text
2 < 7
```

the window is not valid.

```text
Window = [2]
Length = 1
```

---

### Step 2

```text
j = 1
nums[j] = 3
```

Add `3`:

```text
currentSum = 2 + 3 = 5
```

Since:

```text
5 < 7
```

continue expanding.

```text
Window = [2,3]
```

---

### Step 3

```text
j = 2
nums[j] = 1
```

Add `1`:

```text
currentSum = 5 + 1 = 6
```

Still:

```text
6 < 7
```

Continue.

```text
Window = [2,3,1]
```

---

### Step 4

```text
j = 3
nums[j] = 2
```

Add `2`:

```text
currentSum = 6 + 2 = 8
```

Now:

```text
8 >= 7
```

The window is valid.

Current window:

```text
[2,3,1,2]
```

Length:

```text
3 - 0 + 1 = 4
```

Update:

```text
minWindowLength = 4
```

Now shrink the window.

Remove `nums[i] = 2`:

```text
currentSum = 8 - 2 = 6
i = 1
```

Now:

```text
6 < 7
```

Stop shrinking.

Current window:

```text
[3,1,2]
```

---

### Step 5

```text
j = 4
nums[j] = 4
```

Add `4`:

```text
currentSum = 6 + 4 = 10
```

Now:

```text
10 >= 7
```

Current window:

```text
[3,1,2,4]
```

Length:

```text
4 - 1 + 1 = 4
```

Minimum remains:

```text
minWindowLength = 4
```

Shrink the window.

Remove `3`:

```text
currentSum = 10 - 3 = 7
i = 2
```

The sum is still:

```text
7 >= 7
```

So the smaller window is:

```text
[1,2,4]
```

Length:

```text
4 - 2 + 1 = 3
```

Update:

```text
minWindowLength = 3
```

Shrink again.

Remove `1`:

```text
currentSum = 7 - 1 = 6
i = 3
```

Now:

```text
6 < 7
```

Stop shrinking.

---

### Step 6

```text
j = 5
nums[j] = 3
```

Add `3`:

```text
currentSum = 6 + 3 = 9
```

Now:

```text
9 >= 7
```

Current window:

```text
[2,4,3]
```

Length:

```text
5 - 3 + 1 = 3
```

Minimum remains:

```text
minWindowLength = 3
```

Shrink.

Remove `2`:

```text
currentSum = 9 - 2 = 7
i = 4
```

Still valid:

```text
7 >= 7
```

Current window:

```text
[4,3]
```

Length:

```text
5 - 4 + 1 = 2
```

Update:

```text
minWindowLength = 2
```

Shrink again.

Remove `4`:

```text
currentSum = 7 - 4 = 3
i = 5
```

Now:

```text
3 < 7
```

Stop.

## Final Answer

```text
2
```

The smallest valid subarray is:

```text
[4,3]
```

with:

```text
4 + 3 = 7
```

and length:

```text
2
```

## Why Do We Use `while`?

We use:

```java
while (currentSum >= target)
```

instead of `if`.

This is because after finding a valid window, we want to make it **as small as possible**.

For example:

```text
[1,2,4]
```

has sum:

```text
7
```

It is valid.

We remove `1` and get:

```text
[2,4]
```

Sum:

```text
6
```

Now it is invalid.

So `[1,2,4]` was the smallest valid window at that point.

The `while` loop performs this shrinking automatically.

## Why Does Sliding Window Work?

All elements in `nums` are **positive**.

Therefore:

* Adding an element increases the sum.
* Removing an element decreases the sum.

So when:

```text
currentSum >= target
```

we can safely try removing elements from the left to make the window smaller.

This property is what allows the Sliding Window approach.

## Window Visualization

For:

```text
nums = [2,3,1,2,4,3]
target = 7
```

The window expands:

```text
[2]
[2,3]
[2,3,1]
[2,3,1,2] → sum = 8
```

Now shrink:

```text
[3,1,2] → sum = 6
```

Expand again:

```text
[3,1,2,4] → sum = 10
```

Shrink:

```text
[1,2,4] → sum = 7
[2,4]   → sum = 6
```

Later:

```text
[2,4,3] → sum = 9
[4,3]   → sum = 7
```

The minimum valid window is:

```text
[4,3]
```

## Why Use `Integer.MAX_VALUE`?

Initially, we don't know whether a valid subarray exists.

So we use:

```java
int minWindowLength = Integer.MAX_VALUE;
```

Whenever we find a valid window, we update it.

At the end:

```java
return minWindowLength == Integer.MAX_VALUE
        ? 0
        : minWindowLength;
```

If the value is still `Integer.MAX_VALUE`, no valid subarray exists, so we return `0`.

## Complexity

### Time Complexity

```text
O(n)
```

The `right` pointer moves from left to right once.

The `left` pointer also moves only forward.

Even though there is a nested `while` loop, each element can be removed only once.

Therefore:

```text
Time = O(n)
```

### Space Complexity

```text
O(1)
```

Only a few variables are used.

No additional array, HashSet, or HashMap is required.

Therefore:

```text
Space = O(1)
```

## Key Idea

```text
Expand Window
     ↓
Add nums[right]
     ↓
Is sum >= target?
     ↓
    Yes
     ↓
Record Window Length
     ↓
Remove nums[left]
     ↓
Move left
     ↓
Repeat while sum >= target
```

The most important pattern is:

```text
Expand → Valid → Shrink → Update Answer
```

## Final Complexity

```text
Time  : O(n)
Space : O(1)
```

**Pattern Used:** Sliding Window — Variable Size
