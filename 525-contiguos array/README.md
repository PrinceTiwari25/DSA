# Contiguous Array

## LeetCode Problem

[LeetCode 525 — Contiguous Array](https://leetcode.com/problems/contiguous-array/)

---

## Problem

Given a binary array `nums`, return the maximum length of a contiguous subarray with an equal number of `0` and `1`.

### Example

**Input:**

```text
nums = [0,1,0,1]
```

**Output:**

```text
4
```

The entire array contains:

```text
0 → 2 times
1 → 2 times
```

Therefore, the longest subarray length is:

```text
4
```

---

## Optimal Approach: Prefix Sum + HashMap

The main trick is to convert:

```text
0 → -1
1 → +1
```

Then the problem becomes:

> Find the longest subarray whose sum is `0`.

### Why?

Consider:

```text
[0,1,0,1]
```

After conversion:

```text
[-1,+1,-1,+1]
```

If a subarray has an equal number of `0`s and `1`s:

```text
(-1) + (+1) = 0
```

So we need to find the longest subarray with sum `0`.

---

## Prefix Sum

We maintain:

```text
prefixSum
```

At every index:

```text
0 → prefixSum--
1 → prefixSum++
```

We store the **first index** where each prefix sum occurs in a `HashMap`.

### Important Idea

If the same prefix sum occurs at two different indices:

```text
prefixSum[i] == prefixSum[j]
```

then the sum between those indices is `0`.

Therefore, that subarray contains an equal number of `0`s and `1`s.

---

## Steps

```text
1. Create a HashMap.
2. Store prefixSum 0 at index -1.
3. Convert 0 to -1 and 1 to +1.
4. Calculate prefix sum.
5. If the prefix sum was seen before:
      calculate the subarray length.
6. Otherwise:
      store its first index.
7. Keep the maximum length.
```



---

## Dry Run

### Input

```text
nums = [0,1,0,1]
```

Convert:

```text
0 → -1
1 → +1
```

So:

```text
[-1,+1,-1,+1]
```

Initially:

```text
prefixSum = 0
maxLength = 0

map = {0 : -1}
```

---

### Index 0

```text
nums[0] = 0
```

So:

```text
prefixSum = -1
```

`-1` is not in the map.

Store:

```text
map = {
    0 : -1,
   -1 : 0
}
```

---

### Index 1

```text
nums[1] = 1
```

So:

```text
prefixSum = 0
```

`0` already exists at index `-1`.

Therefore:

```text
length = 1 - (-1)
       = 2
```

Update:

```text
maxLength = 2
```

The subarray is:

```text
[0,1]
```

---

### Index 2

```text
nums[2] = 0
```

So:

```text
prefixSum = -1
```

`-1` already exists at index `0`.

Therefore:

```text
length = 2 - 0
       = 2
```

`maxLength` remains:

```text
2
```

---

### Index 3

```text
nums[3] = 1
```

So:

```text
prefixSum = 0
```

`0` already exists at index `-1`.

Therefore:

```text
length = 3 - (-1)
       = 4
```

Update:

```text
maxLength = 4
```

---

## Final Answer

```text
4
```

The longest subarray is:

```text
[0,1,0,1]
```

It contains:

```text
0 → 2
1 → 2
```

---

## Why Store Only the First Occurrence?

Suppose a prefix sum occurs at:

```text
index 2
index 5
index 8
```

For index `8`, using the earliest occurrence gives:

```text
8 - 2 = 6
```

while:

```text
8 - 5 = 3
```

So the **first occurrence always gives the longest possible subarray**.

That's why:

```java
if (!map.containsKey(prefixSum)) {
    map.put(prefixSum, i);
}
```

---

## Why Do We Put `0 : -1`?

This is an important trick.

Consider:

```text
nums = [0,1]
```

After conversion:

```text
[-1,+1]
```

At index `1`:

```text
prefixSum = 0
```

The entire array has sum `0`.

To calculate its length:

```text
1 - (-1) = 2
```

That's why we initially store:

```java
map.put(0, -1);
```

It represents a prefix sum of `0` **before the array starts**.

---

## Complexity Analysis

### Time Complexity: O(n)

We traverse the array once.

HashMap lookup and insertion take `O(1)` average time.

```text
O(n)
```

### Space Complexity: O(n)

The HashMap can store up to `O(n)` different prefix sums.

```text
O(n)
```

---

## Key Takeaway

The main trick is:

```text
0 → -1
1 → +1
```

Then:

```text
Equal number of 0s and 1s
             ↓
        Sum becomes 0
             ↓
      Same prefix sum
             ↓
      Calculate length
```

### Remember

```text
map.put(0, -1)
```

and:

```text
if (map.containsKey(prefixSum)) {
    length = i - map.get(prefixSum);
}
```

### Pattern

```text
Binary Array
     ↓
0 = -1, 1 = +1
     ↓
Prefix Sum
     ↓
HashMap
     ↓
Same Prefix Sum
     ↓
Maximum Length
```

**Prefix Sum + HashMap → O(n) Time | O(n) Space**
