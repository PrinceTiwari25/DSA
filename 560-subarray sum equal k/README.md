# Subarray Sum Equals K

## LeetCode Problem

[LeetCode 560 — Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

---

## Problem

Given an integer array `nums` and an integer `k`, return the **total number of continuous subarrays whose sum equals `k`**.

### Example

**Input:**

```text
nums = [1,1,1]
k = 2
```

**Output:**

```text
2
```

The valid subarrays are:

```text
[1,1]
[1,1]
```

Both have sum:

```text
1 + 1 = 2
```

---

## Optimal Approach: Prefix Sum + HashMap

We use:

```text
Prefix Sum + HashMap
```

The HashMap stores:

```text
prefixSum → frequency
```

### Main Idea

Suppose the current prefix sum is:

```text
currentSum
```

We need a previous prefix sum such that:

```text
currentSum - previousSum = k
```

Rearranging:

```text
previousSum = currentSum - k
```

Therefore, at every index we check:

```java
map.containsKey(prefixSum - k)
```

If it exists, its frequency tells us how many subarrays ending at the current index have sum `k`.

---

## Steps

```text
1. Create a HashMap.
2. Put (0, 1) in the map.
3. Traverse the array.
4. Calculate the prefix sum.
5. Find prefixSum - k in the map.
6. Add its frequency to the answer.
7. Store the current prefix sum and its frequency.
```



---

## Dry Run

### Input

```text
nums = [1,1,1]
k = 2
```

Initially:

```text
prefixSum = 0
count = 0

map = {0 : 1}
```

---

### Step 1

Current number:

```text
1
```

Prefix sum:

```text
prefixSum = 1
```

We need:

```text
prefixSum - k
= 1 - 2
= -1
```

`-1` is not present.

Store:

```text
map = {0:1, 1:1}
```

---

### Step 2

Current number:

```text
1
```

Prefix sum:

```text
prefixSum = 2
```

We need:

```text
2 - 2 = 0
```

`0` exists once.

Therefore:

```text
count += 1
```

Now:

```text
count = 1
```

This represents:

```text
[1,1]
```

Store:

```text
map = {0:1, 1:1, 2:1}
```

---

### Step 3

Current number:

```text
1
```

Prefix sum:

```text
prefixSum = 3
```

We need:

```text
3 - 2 = 1
```

`1` exists once.

Therefore:

```text
count += 1
```

Now:

```text
count = 2
```

This represents the second:

```text
[1,1]
```

---

## Final Answer

```text
2
```

---

## Why `map.put(0, 1)`?

This is an important step:

```java
map.put(0, 1);
```

It represents a prefix sum of `0` **before the array starts**.

For example:

```text
nums = [3]
k = 3
```

At the first element:

```text
prefixSum = 3
```

We need:

```text
3 - 3 = 0
```

Because `0` is already in the map:

```text
count += 1
```

So `[3]` is correctly counted as a subarray with sum `3`.

---

## Why Store Frequency?

This problem asks:

> **How many subarrays have sum K?**

A prefix sum can occur multiple times.

Suppose:

```text
map = {
    5 : 3
}
```

This means prefix sum `5` has appeared **3 times**.

If:

```text
currentPrefixSum - k = 5
```

then all 3 occurrences can create a valid subarray.

Therefore:

```java
count += map.get(prefixSum - k);
```

---

## Important Difference: LC 525 vs LC 560

### LC 525 — Contiguous Array

Find the **longest** valid subarray.

```text
Store → First Index
```

Then:

```text
length = currentIndex - firstIndex
```

### LC 560 — Subarray Sum Equals K

Find the **number** of valid subarrays.

```text
Store → Frequency
```

Then:

```text
count += frequency
```

### Remember

```text
Longest → Store First Index

Count → Store Frequency
```

---

## Complexity Analysis

### Time Complexity: O(n)

We traverse the array once.

HashMap operations take `O(1)` average time.

```text
O(n)
```

### Space Complexity: O(n)

The HashMap can contain up to `O(n)` different prefix sums.

```text
O(n)
```

---

## Key Takeaway

The main formula is:

```text
currentPrefixSum - previousPrefixSum = k
```

Therefore:

```text
previousPrefixSum = currentPrefixSum - k
```

So:

```text
Prefix Sum
     ↓
prefixSum - k
     ↓
Search in HashMap
     ↓
Found?
     ↓
Add its frequency
```

### Remember

```java
count += map.getOrDefault(prefixSum - k, 0);
```

and:

```java
map.put(prefixSum,
        map.getOrDefault(prefixSum, 0) + 1);
```

**Prefix Sum + HashMap → O(n) Time | O(n) Space**
