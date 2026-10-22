# LeetCode 523 — Continuous Subarray Sum

## 🔗 Problem Link

[LeetCode 523 — Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)

## 📝 Problem Statement

Given an integer array `nums` and an integer `k`, return `true` if the array has a **continuous subarray** of at least two elements whose sum is a multiple of `k`.

Otherwise, return `false`.

A sum is a multiple of `k` if:

\[
\text{sum} \bmod k = 0
\]

A continuous subarray consists of consecutive elements.

### Example

**Input**
```text
nums = [23, 2, 4, 6, 7]
k = 6
```

**Output**
```text
true
```

**Explanation**

The subarray `[2, 4]` has a sum of:

\[
2 + 4 = 6
\]

Since:

\[
6 \bmod 6 = 0
\]

and the subarray contains two elements, the answer is `true`.

---

## 🚀 Approach: Prefix Sum + Modulo + HashMap

We use three concepts:

- Prefix sum
- Modulo (`%`)
- HashMap

### Key Idea

> If two prefix sums have the same remainder when divided by `k`, the sum of the elements between those two prefix positions is divisible by `k`.

Suppose:

\[
P_i \bmod k = P_j \bmod k
\]

Then:

\[
(P_i-P_j)\bmod k=0
\]

Therefore, the elements between those prefix positions have a sum that is a multiple of `k`.

We store each remainder's **earliest index** in a HashMap.


## 🔍 Code Explanation

### 1. Create the HashMap

```java
HashMap<Integer, Integer> map = new HashMap<>();
```

The map stores:

```text
remainder → earliest index
```

For example:

```text
0 → -1
5 → 0
2 → 1
```

If a remainder appears again, we check the distance between the current index and its earliest stored index.

### 2. Initialize the Map

```java
map.put(0, -1);
```

This is an important initialization.

It represents a prefix sum of zero before the array begins.

For example, if the first two elements sum to a multiple of `k`, we need to recognize that subarray.

Suppose:

```text
nums = [3, 3]
k = 3
```

At index `1`:

```text
sum = 6
rem = 6 % 3 = 0
```

The map already contains remainder `0` at index `-1`.

Therefore:

```text
i - map.get(rem)
= 1 - (-1)
= 2
```

The subarray has length two, so the answer is `true`.

### 3. Calculate the Prefix Sum

```java
int sum = 0;
```

Then, for every element:

```java
sum += nums[i];
```

`sum` stores the sum of all elements from index `0` through index `i`.

For example:

```text
nums = [23, 2, 4, 6]

Index:       0   1   2   3
Value:      23   2   4   6
Prefix sum: 23  25  29  35
```

### 4. Calculate the Remainder

```java
int rem = sum % k;
```

For example:

```text
sum = 29
k = 6

rem = 29 % 6
    = 5
```

The remainder is `5`.

### 5. Check Whether the Remainder Exists

```java
if (map.containsKey(rem)) {
```

If the remainder has appeared before, the difference between the two prefix sums is divisible by `k`.

For example:

```text
Earlier prefix sum remainder = 5
Current prefix sum remainder = 5
```

Their difference is divisible by `k`.

Now we must also check that the subarray has at least two elements.

### 6. Check the Subarray Length

```java
if (i - map.get(rem) >= 2) {
    return true;
}
```

Suppose:

```text
Current index = 3
Earliest index = 1
```

Then:

```text
Length = 3 - 1
       = 2
```

Since the length is at least `2`, we return `true`.

### 7. Store Only the Earliest Index

```java
else {
    map.put(rem, i);
}
```

We store a remainder only if it has not appeared before.

**Why?**

The earliest index gives us the longest possible distance to a later occurrence of that remainder. This maximizes our chance of finding a subarray of length at least two.

---

## 🧪 Dry Run

Consider:

```text
nums = [23, 2, 4, 6, 7]
k = 6
```

Initially:

```text
map = {0 = -1}
sum = 0
```

| `i` | `nums[i]` | Prefix sum | `sum % k` | Earlier index | Result |
|---:|---:|---:|---:|---:|---|
| 0 | 23 | 23 | 5 | Not found | Store `5 → 0` |
| 1 | 2 | 25 | 1 | Not found | Store `1 → 1` |
| 2 | 4 | 29 | 5 | 0 | `2 - 0 = 2`, return `true` |

At index `2`:

```text
sum = 23 + 2 + 4
    = 29

rem = 29 % 6
    = 5
```

Remainder `5` was first seen at index `0`.

Therefore:

```text
i - map.get(rem)
= 2 - 0
= 2
```

The subarray is:

```text
nums[1...2] = [2, 4]
```

Its sum is:

```text
2 + 4 = 6
```

And:

```text
6 % 6 = 0
```

**Final Output**

```text
true
```

---

## 🧠 Visual Understanding

For:

```text
nums = [23, 2, 4, 6, 7]
k = 6
```

The prefix sums are:

```text
Index:       0    1    2    3    4
Nums:       23    2    4    6    7
Prefix sum:  23   25   29   35   42
Remainder:   5    1    5    5    0
```

Remainder `5` appears at indices `0` and `2`.

The difference between the prefix sums is:

\[
29-23=6
\]

So the intervening subarray is:

```text
[2, 4]
```

Its sum is divisible by `6`.

---

## ❓ Why Do Equal Remainders Matter?

Suppose two prefix sums are:

```text
Earlier sum = 25
Current sum = 37
k = 6
```

Their remainders are:

```text
25 % 6 = 1
37 % 6 = 1
```

Their difference is:

```text
37 - 25 = 12
```

Since:

```text
12 % 6 = 0
```

the elements between those prefix positions have a sum divisible by `6`.

In general:

\[
a\bmod k=b\bmod k
\implies (a-b)\bmod k=0
\]

This is the mathematical foundation of the solution.

---

## ❓ Why Do We Keep the Earliest Index?

Suppose remainder `2` first appears at index `0` and appears again at index `3`.

The distance is:

```text
3 - 0 = 3
```

If we overwrite the stored index with `2` when the remainder appears again, a later occurrence at index `3` would give:

```text
3 - 2 = 1
```

That would miss the longer subarray.

Keeping the earliest index gives the greatest possible distance to any later occurrence.

---

## ⚠️ Important Note About `k`

The given code assumes `k` is **positive and nonzero**, as in the standard LeetCode 523 constraints.

If `k` can be negative, use `Math.abs(k)` for the modulus logic. If `k == 0` is allowed, modulo cannot be used directly; instead, check whether a qualifying subarray has sum zero.

---

## ⏱️ Complexity Analysis

Let `n` be the length of `nums`.

### Time Complexity

We traverse the array once.

HashMap lookups and insertions take expected \(O(1)\) time each.

Therefore:

\[
\boxed{O(n)}
\]

### Space Complexity

In the worst case, the HashMap stores a remainder for each index.

Therefore:

\[
\boxed{O(n)}
\]

---

## 🧠 Key Takeaways

- `sum` maintains the prefix sum.
- `rem = sum % k` calculates the current remainder.
- The HashMap stores `remainder → earliest index`.
- Equal remainders indicate a subarray sum divisible by `k`.
- `map.put(0, -1)` handles qualifying subarrays starting at index `0`.
- `i - map.get(rem) >= 2` ensures the subarray contains at least two elements.
- Only the earliest index for each remainder is stored.

## 📌 Final Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(n)
```

### One-Line Idea

**Use prefix sums and a HashMap to find two prefix sums with the same remainder, ensuring their index distance is at least two.**