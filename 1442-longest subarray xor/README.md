# Count Triplets That Can Form Two Arrays of Equal XOR

## LeetCode Problem

[LeetCode 1442 — Count Triplets That Can Form Two Arrays of Equal XOR](https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/)

---

## Problem

Given an array `arr`, choose three indices `i`, `j`, and `k` such that:

```text
0 <= i < j <= k < arr.length
```

Define:

```text
a = arr[i] ^ arr[i+1] ^ ... ^ arr[j-1]
```

and:

```text
b = arr[j] ^ arr[j+1] ^ ... ^ arr[k]
```

The goal is to count the number of triplets `(i, j, k)` for which:

```text
a == b
```

---

## Example

### Input

```text
arr = [2,3,1,6,7]
```

### Output

```text
4
```

---

# Optimal Approach

## Prefix XOR

The key observation is:

```text
a == b
```

where:

```text
a = arr[i] ^ ... ^ arr[j-1]
b = arr[j] ^ ... ^ arr[k]
```

If:

```text
a == b
```

then:

```text
a ^ b = 0
```

Therefore:

```text
arr[i] ^ arr[i+1] ^ ... ^ arr[k] = 0
```

So instead of directly comparing `a` and `b`, we can find a subarray from `i` to `k` whose XOR is `0`.

---

## Important Observation

If:

```text
arr[i] ^ arr[i+1] ^ ... ^ arr[k] = 0
```

then **every possible `j` between `i+1` and `k`** creates a valid triplet.

The number of possible `j` values is:

```text
k - i
```

Therefore:

```text
count += k - i
```

---

# Approach Using Two Loops

We can fix the starting index `i` and keep calculating the XOR while moving `k`.

### Steps

```text
1. Start from every index i.
2. Set xor = 0.
3. Move k from i to the end.
4. Keep updating:
      xor = xor ^ arr[k]
5. If xor becomes 0:
      add k - i to the answer.
6. Return the total count.
```

---

# Why `k - i`?

Suppose:

```text
i = 0
k = 3
```

and the XOR from `i` to `k` is `0`.

Possible `j` values are:

```text
j = 1
j = 2
j = 3
```

That's:

```text
3
```

possibilities.

And:

```text
k - i = 3 - 0 = 3
```

Therefore:

```text
count += k - i
```

---

# Dry Run

### Input

```text
arr = [2,3,1,6,7]
```

We start with:

```text
count = 0
```

---

## i = 0

Start:

```text
xor = 0
```

### k = 0

```text
xor = 0 ^ 2
     = 2
```

Not zero.

```text
count = 0
```

---

### k = 1

```text
xor = 2 ^ 3
     = 1
```

Not zero.

```text
count = 0
```

---

### k = 2

```text
xor = 1 ^ 1
     = 0
```

Now XOR is zero.

Therefore:

```text
count += k - i
       += 2 - 0
       += 2
```

```text
count = 2
```

Possible `j` values:

```text
j = 1
j = 2
```

---

### k = 3

```text
xor = 0 ^ 6
     = 6
```

Not zero.

---

### k = 4

```text
xor = 6 ^ 7
     = 1
```

Not zero.

---

# i = 1

Reset:

```text
xor = 0
```

### k = 1

```text
xor = 0 ^ 3
     = 3
```

Not zero.

### k = 2

```text
xor = 3 ^ 1
     = 2
```

Not zero.

### k = 3

```text
xor = 2 ^ 6
     = 4
```

Not zero.

### k = 4

```text
xor = 4 ^ 7
     = 3
```

Not zero.

No addition.

```text
count = 2
```

---

# i = 2

Start:

```text
xor = 0
```

### k = 2

```text
xor = 1
```

Not zero.

### k = 3

```text
xor = 1 ^ 6
     = 7
```

Not zero.

### k = 4

```text
xor = 7 ^ 7
     = 0
```

XOR is zero.

Therefore:

```text
count += 4 - 2
       += 2
```

Now:

```text
count = 4
```

Possible `j` values:

```text
j = 3
j = 4
```

---

## Final Answer

```text
4
```

---

# Understanding One Valid Case

Take:

```text
i = 0
k = 2
```

We found:

```text
2 ^ 3 ^ 1 = 0
```

Therefore:

```text
a ^ b = 0
```

which means:

```text
a = b
```

Possible `j` values:

```text
j = 1
j = 2
```

### j = 1

```text
a = [2]

b = [3,1]
```

```text
2
```

and:

```text
3 ^ 1 = 2
```

So:

```text
a = b
```

Valid triplet:

```text
(0,1,2)
```

### j = 2

```text
a = [2,3]

b = [1]
```

```text
2 ^ 3 = 1
```

So again:

```text
a = b
```

Valid triplet:

```text
(0,2,2)
```

That's why one zero-XOR range can produce multiple triplets.

---

# Why XOR = 0 Is Enough

We have:

```text
a == b
```

XOR both sides with `b`:

```text
a ^ b = b ^ b
```

Since:

```text
b ^ b = 0
```

we get:

```text
a ^ b = 0
```

And `a ^ b` is exactly the XOR of the entire range from `i` to `k`.

Therefore:

```text
Total XOR from i to k = 0
```

is the condition we need.

---

# Key Formula

The most important formula is:

```text
If XOR(i ... k) == 0

then:

count += k - i
```

Why?

Because every:

```text
i < j <= k
```

produces a valid triplet.

---

# Complexity Analysis

### Time Complexity

We use two nested loops:

```text
O(n²)
```

### Space Complexity

Only a few variables are used:

```text
O(1)
```

---

# Common Mistake

Do **not** confuse this problem with the classic:

```text
Count Subarrays With XOR K
```

Here there is **no given K**.

The condition is:

```text
a == b
```

which becomes:

```text
XOR(i ... k) == 0
```

So the key is:

```text
No K
   ↓
Find zero XOR ranges
   ↓
For every zero XOR range
   ↓
Add k - i
```

---

# 🧠 Remember

```text
a == b
  ↓
a ^ b = 0
  ↓
XOR(i ... k) = 0
  ↓
Every j between i+1 and k works
  ↓
count += k - i
```

### One-line memory trick

> **When the XOR of the whole range `i...k` is zero, all `k-i` possible middle positions `j` form valid triplets.**

**Two Pointers/Loops + Running XOR → O(n²) Time | O(1) Space**
