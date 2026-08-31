# Count Subarrays With XOR K

## Problem

Given an integer array `nums` and an integer `k`, find the **number of subarrays whose XOR is equal to `k`**.

> Note: The classic problem "Count Subarrays With XOR K" is not an exact official LeetCode problem. It is a common DSA problem based on the **Prefix XOR + HashMap** pattern.

---

## Example

**Input:**

```text
nums = [4,2,2,6,4]
k = 6
```

**Output:**

```text
4
```

The subarrays having XOR equal to `6` are:

```text
[4,2]
[2,2,6]
[6]
[4,2,2,6,4]
```

Therefore:

```text
Answer = 4
```

---

# Optimal Approach

## Prefix XOR + HashMap

We maintain:

```text
prefixXor
```

which represents the XOR of all elements from the beginning up to the current index.

The HashMap stores:

```text
prefixXor → frequency
```

---

## Main Idea

Suppose:

```text
currentPrefixXor = X
```

and we want a subarray with XOR `k`.

Let the previous prefix XOR be `P`.

Then:

```text
P ^ X = k
```

Using XOR properties:

```text
P = X ^ k
```

Therefore, at every element we calculate:

```text
required = prefixXor ^ k
```

and check whether `required` already exists in the HashMap.

If it exists, its frequency tells us how many valid subarrays end at the current index.

---

# Why Does This Work?

Suppose:

```text
prefixXor before = P
prefixXor current = X
```

The XOR of the elements between them is:

```text
P ^ X
```

If:

```text
P ^ X = k
```

then we have found a subarray whose XOR is `k`.

Therefore:

```text
P = X ^ k
```

That's why we search for:

```text
prefixXor ^ k
```

---

# Steps

```text
1. Create a HashMap.
2. Put (0, 1) in the map.
3. Set prefixXor = 0.
4. Traverse the array.
5. Update prefixXor using XOR.
6. Calculate:
      required = prefixXor ^ k
7. If required exists:
      add its frequency to answer.
8. Store the current prefixXor frequency.
9. Return the count.
```



---

# Dry Run

### Input

```text
nums = [4,2,2,6,4]
k = 6
```

Initially:

```text
prefixXor = 0
count = 0

map = {0 : 1}
```

---

## Index 0

```text
num = 4
```

Calculate:

```text
prefixXor = 0 ^ 4
          = 4
```

Required:

```text
required = 4 ^ 6
         = 2
```

`2` is not in the map.

Store:

```text
map = {
    0 : 1,
    4 : 1
}
```

---

## Index 1

```text
num = 2
```

```text
prefixXor = 4 ^ 2
          = 6
```

Required:

```text
6 ^ 6 = 0
```

`0` exists once.

Therefore:

```text
count = 1
```

The subarray is:

```text
[4,2]
```

because:

```text
4 ^ 2 = 6
```

Store:

```text
map = {
    0 : 1,
    4 : 1,
    6 : 1
}
```

---

## Index 2

```text
num = 2
```

```text
prefixXor = 6 ^ 2
          = 4
```

Required:

```text
4 ^ 6 = 2
```

`2` is not in the map.

Store frequency of `4`:

```text
map = {
    0 : 1,
    4 : 2,
    6 : 1
}
```

---

## Index 3

```text
num = 6
```

```text
prefixXor = 4 ^ 6
          = 2
```

Required:

```text
2 ^ 6 = 4
```

`4` appears **2 times**.

Therefore:

```text
count += 2
```

Now:

```text
count = 3
```

The two subarrays are:

```text
[2,2,6]
[6]
```

Store:

```text
map = {
    0 : 1,
    4 : 2,
    6 : 1,
    2 : 1
}
```

---

## Index 4

```text
num = 4
```

```text
prefixXor = 2 ^ 4
          = 6
```

Required:

```text
6 ^ 6 = 0
```

`0` appears once.

Therefore:

```text
count = 4
```

This gives:

```text
[4,2,2,6,4]
```

because the XOR of the entire array is:

```text
4 ^ 2 ^ 2 ^ 6 ^ 4 = 6
```

---

# Final Answer

```text
4
```

---

# Why `map.put(0, 1)`?

This line is very important:

```java
map.put(0, 1);
```

It represents a prefix XOR of `0` **before the array starts**.

For example:

```text
nums = [6]
k = 6
```

At the first element:

```text
prefixXor = 6
```

Required:

```text
6 ^ 6 = 0
```

Because `0` is already in the map:

```text
count += 1
```

So `[6]` is correctly counted.

---

# Why Store Frequency?

This problem asks for the **number of subarrays**.

A particular prefix XOR can occur multiple times.

For example:

```text
prefixXor = 4
```

appears `2` times.

If:

```text
required = 4
```

then both occurrences can produce valid subarrays.

Therefore:

```java
count += map.get(required);
```

We add the **frequency**, not just `1`.

---

# Difference From Sum K

For:

### Subarray Sum K

```text
previousSum = prefixSum - k
```

### Subarray XOR K

```text
previousXor = prefixXor ^ k
```

So:

```text
SUM:
prefixSum - k

XOR:
prefixXor ^ k
```

Both use the same overall pattern:

```text
Prefix
   ↓
Find required previous value
   ↓
HashMap
   ↓
Add frequency
```

---

# Complexity Analysis

### Time Complexity

```text
O(n)
```

We traverse the array once.

### Space Complexity

```text
O(n)
```

The HashMap can store up to `n` different prefix XOR values.

---

# Key Takeaway

Remember this formula:

```text
Previous XOR ^ Current XOR = K
```

Therefore:

```text
Previous XOR = Current XOR ^ K
```

So the main line is:

```java
int required = prefixXor ^ k;
```

Then:

```java
count += map.getOrDefault(required, 0);
```

And finally store the current prefix XOR:

```java
map.put(
    prefixXor,
    map.getOrDefault(prefixXor, 0) + 1
);
```

## 🧠 Pattern

```text
Array
  ↓
Prefix XOR
  ↓
prefixXor ^ K
  ↓
Search in HashMap
  ↓
Add frequency
  ↓
Store prefix XOR
```

**Prefix XOR + HashMap → O(n) Time | O(n) Space**
