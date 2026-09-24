# LeetCode 904 — Fruit Into Baskets

## Question

You are given an integer array `fruits`, where `fruits[i]` represents the type of fruit on the `i-th` tree.

You have two baskets.

Each basket can hold only **one type of fruit**, but there is no limit on the number of fruits of that type.

Starting from any tree, you must pick exactly one fruit from every tree while moving to the right.

You must stop when you encounter a third type of fruit.

Return the **maximum number of fruits** you can collect.

### Example

**Input:**

```text
fruits = [1,2,1]
```

**Output:**

```text
3
```

### Explanation

There are only two types of fruit:

```text
1 → Basket 1
2 → Basket 2
```

So we can collect all three fruits:

```text
[1,2,1]
```

Therefore:

```text
Answer = 3
```

### Another Example

**Input:**

```text
fruits = [0,1,2,2]
```

**Output:**

```text
3
```

The longest valid subarray is:

```text
[1,2,2]
```

It contains only two fruit types: `1` and `2`.

---

## LeetCode Link

https://leetcode.com/problems/fruit-into-baskets/

## Approach — Sliding Window

This problem can be converted into a standard **Sliding Window** problem.

The important condition is:

> The current window can contain at most **2 distinct fruit types**.

We maintain a window `[i ... j]`.

### Variables

```text
i          → left pointer of the window

j          → right pointer of the window

freq       → frequency of each fruit type

unique     → number of different fruit types in the window

maxlength  → maximum valid window length
```

### Steps

1. Start with `i = 0`.
2. Move `j` from left to right.
3. Add `fruits[j]` to the frequency array.
4. If its frequency was `0`, a new fruit type has entered the window.
5. Increase `unique`.
6. If `unique > 2`, the window contains more than two fruit types.
7. Move `i` forward until only two fruit types remain.
8. Calculate the current window length.
9. Update `maxlength`.
10. Continue until the entire array is processed.

---

## Code

```java id="w3b7qz"
class Solution {
    public int totalFruit(int[] fruits) {

        int[] freq = new int[100001];

        int i = 0;
        int unique = 0;
        int maxlength = 0;

        for (int j = 0; j < fruits.length; j++) {

            if (freq[fruits[j]] == 0) {
                unique++;
            }

            freq[fruits[j]]++;

            while (unique > 2) {

                freq[fruits[i]]--;

                if (freq[fruits[i]] == 0) {
                    unique--;
                }

                i++;
            }

            maxlength = Math.max(maxlength, j - i + 1);
        }

        return maxlength;
    }
}
```

# Dry Run

## Input

```text
fruits = [1,2,1,2,3,2,2]
```

We can have at most **2 unique fruit types** in the window.

---

## Step 1

```text
j = 0
fruits[j] = 1
```

Fruit `1` is new.

```text
freq[1] = 1
unique = 1
```

Window:

```text
[1]
```

Length:

```text
0 - 0 + 1 = 1
```

```text
maxlength = 1
```

---

## Step 2

```text
j = 1
fruits[j] = 2
```

Fruit `2` is new.

```text
freq[2] = 1
unique = 2
```

Window:

```text
[1,2]
```

Length:

```text
1 - 0 + 1 = 2
```

```text
maxlength = 2
```

---

## Step 3

```text
j = 2
fruits[j] = 1
```

Fruit `1` already exists.

```text
freq[1] = 2
unique = 2
```

Window:

```text
[1,2,1]
```

Length:

```text
2 - 0 + 1 = 3
```

```text
maxlength = 3
```

---

## Step 4

```text
j = 3
fruits[j] = 2
```

Fruit `2` already exists.

```text
freq[2] = 2
unique = 2
```

Window:

```text
[1,2,1,2]
```

Length:

```text
3 - 0 + 1 = 4
```

```text
maxlength = 4
```

---

## Step 5

```text
j = 4
fruits[j] = 3
```

Fruit `3` is new.

```text
freq[3] = 1
unique = 3
```

Now:

```text
unique > 2
```

So we need to shrink the window.

Current window:

```text
[1,2,1,2,3]
```

### Remove `fruits[i] = 1`

```text
freq[1]--
```

Frequency of `1` becomes:

```text
1
```

It is still present.

Move:

```text
i = 1
```

Window:

```text
[2,1,2,3]
```

Still:

```text
unique = 3
```

### Remove `fruits[i] = 2`

```text
freq[2]--
```

Frequency becomes:

```text
1
```

Still present.

Move:

```text
i = 2
```

Window:

```text
[1,2,3]
```

Still:

```text
unique = 3
```

### Remove `fruits[i] = 1`

```text
freq[1]--
```

Frequency becomes:

```text
0
```

So fruit type `1` is completely removed.

Therefore:

```text
unique--
```

Now:

```text
unique = 2
```

Move:

```text
i = 3
```

Current valid window:

```text
[2,3]
```

Length:

```text
4 - 3 + 1 = 2
```

`maxlength` remains:

```text
4
```

---

## Step 6

```text
j = 5
fruits[j] = 2
```

Fruit `2` already exists.

```text
freq[2]++
```

Window:

```text
[2,3,2]
```

Length:

```text
5 - 3 + 1 = 3
```

```text
maxlength = 4
```

---

## Step 7

```text
j = 6
fruits[j] = 2
```

Fruit `2` already exists.

Window:

```text
[2,3,2,2]
```

Length:

```text
6 - 3 + 1 = 4
```

```text
maxlength = 4
```

---

# Final Answer

```text
4
```

One of the longest valid windows is:

```text
[1,2,1,2]
```

It contains exactly two fruit types:

```text
1
2
```

So we can collect `4` fruits.

---

# Understanding `unique`

The variable:

```java id="m5t7da"
int unique = 0;
```

stores the number of different fruit types currently present in the window.

When adding a fruit:

```java id="vh7v5n"
if (freq[fruits[j]] == 0) {
    unique++;
}
```

If its frequency was `0`, it means this fruit type was not currently in the window.

When removing a fruit:

```java id="e5apv8"
if (freq[fruits[i]] == 0) {
    unique--;
}
```

If its frequency becomes `0`, that fruit type has completely disappeared from the window.

---

# Why Do We Use `while (unique > 2)`?

The problem allows only **two fruit types**.

Therefore, whenever:

```text
unique > 2
```

the window is invalid.

We shrink it from the left:

```java id="v4x1om"
while (unique > 2) {
    ...
    i++;
}
```

We continue until:

```text
unique <= 2
```

Then the window becomes valid again.

---

# Window Visualization

For:

```text
fruits = [1,2,1,2,3]
```

Initially:

```text
[1,2,1,2]
```

There are two types:

```text
1 → Basket 1
2 → Basket 2
```

Valid.

When `3` enters:

```text
[1,2,1,2,3]
```

There are three types:

```text
1
2
3
```

Invalid.

So we move the left pointer:

```text
[2,1,2,3]
```

Still three types.

Move again:

```text
[1,2,3]
```

Still three types.

Move again:

```text
[2,3]
```

Now there are only two types.

The window is valid again.

---

# Why Is This a Sliding Window Problem?

The problem asks for the **longest contiguous subarray** satisfying a condition:

```text
At most 2 distinct values
```

This is a classic variable-size Sliding Window pattern.

The pattern is:

```text
Expand
   ↓
Add element
   ↓
Condition violated?
   ↓
   Yes
   ↓
Shrink from left
   ↓
Condition satisfied
   ↓
Update maximum
```

In this problem:

```text
Condition = unique <= 2
```

---

# Why Use a Frequency Array?

Your solution uses:

```java id="e1f2q8"
int[] freq = new int[100001];
```

The frequency array stores how many times each fruit type appears in the current window.

For example:

```text
fruits = [1,2,1,2]
```

The frequency array conceptually contains:

```text
freq[1] = 2
freq[2] = 2
```

This allows us to quickly determine whether a fruit type is:

* entering the window for the first time
* still present
* completely removed

Each frequency lookup/update takes:

```text
O(1)
```

---

# Complexity

## Time Complexity

```text
O(n)
```

The right pointer moves from left to right once.

The left pointer also only moves forward.

Even though there is a `while` loop inside the `for` loop, each element can be removed from the window at most once.

Therefore:

```text
Time = O(n)
```

## Space Complexity

```text
O(1)
```

The frequency array has a fixed size:

```text
100001
```

It does not grow with the input size `n`.

Therefore:

```text
Space = O(1)
```

---

# Key Idea

The entire problem can be reduced to:

```text
Find the longest subarray
        ↓
with at most 2 distinct values
        ↓
Use Sliding Window
        ↓
Track frequencies
        ↓
If unique > 2
        ↓
Move left pointer
        ↓
Update maximum length
```

The most important condition is:

```java id="e5qz5r"
while (unique > 2)
```

This keeps the current window valid.

---

# Final Complexity

```text
Time  : O(n)
Space : O(1)
```

**Pattern Used:** Sliding Window + Frequency Array + At Most K Distinct Elements
