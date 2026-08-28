# Longest Consecutive Sequence

## LeetCode Problem

[LeetCode 128 — Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)

---

## Problem

Given an unsorted integer array `nums`, return the length of the longest consecutive elements sequence.

The sequence must contain consecutive numbers, and the elements do not need to be next to each other in the original array.

The algorithm must run in **O(n)** time.

### Example

**Input:**

```text
nums = [100,4,200,1,3,2]
```

**Output:**

```text
4
```

The longest consecutive sequence is:

```text
[1,2,3,4]
```

Length:

```text
4
```

---

## Optimal Approach: HashSet

We use a `HashSet` to store all numbers.

### Why HashSet?

A `HashSet` provides approximately **O(1)** average-time lookup.

So we can quickly check:

```text
Does num - 1 exist?
Does num + 1 exist?
```

---

## Main Idea

We should only start building a sequence when the current number is the **first number** of that sequence.

For example:

```text
[1,2,3,4]
```

When we are at `1`:

```text
0 does not exist
```

So `1` is the beginning of the sequence.

But when we are at `2`:

```text
1 exists
```

So `2` is not the beginning.

Therefore:

```text
if (!set.contains(num - 1))
```

means:

> Start counting only when `num` is the beginning of a sequence.

---

## Steps

### Step 1: Store all numbers

```java
HashSet<Integer> set = new HashSet<>();
```

Add every number to the set.

### Step 2: Find sequence beginnings

For every number:

```text
if num - 1 does not exist
```

then `num` is the beginning.

### Step 3: Count consecutive numbers

Starting from `num`:

```text
num
num + 1
num + 2
num + 3
...
```

Continue while the next number exists.

### Step 4: Keep the maximum length

```text
longest = max(longest, count)
```



---

## Dry Run

### Input

```text
nums = [100,4,200,1,3,2]
```

Put everything into the `HashSet`:

```text
{100, 4, 200, 1, 3, 2}
```

---

### Start with `100`

Check:

```text
100 - 1 = 99
```

`99` does not exist.

So `100` is the beginning of a sequence.

Check:

```text
101 exists? No
```

Sequence:

```text
[100]
```

Length:

```text
1
```

So:

```text
longest = 1
```

---

### Start with `4`

Check:

```text
4 - 1 = 3
```

`3` exists.

Therefore `4` is **not** the beginning.

Skip it.

---

### Start with `200`

Check:

```text
199 exists? No
```

So `200` is a beginning.

```text
200 → 201 does not exist
```

Length:

```text
1
```

`longest` remains:

```text
1
```

---

### Start with `1`

Check:

```text
1 - 1 = 0
```

`0` does not exist.

So `1` is the beginning of a sequence.

Now keep checking:

```text
1 → 2 ✓
2 → 3 ✓
3 → 4 ✓
4 → 5 ✗
```

Therefore:

```text
Sequence = [1,2,3,4]
Length = 4
```

Update:

```text
longest = 4
```

---

### `3`

Check:

```text
3 - 1 = 2
```

`2` exists.

Skip.

### `2`

Check:

```text
2 - 1 = 1
```

`1` exists.

Skip.

---

## Final Answer

```text
4
```

The longest consecutive sequence is:

```text
[1,2,3,4]
```

---

## Why Don't We Sort?

A sorting solution would be:

```text
Sort → O(n log n)
```

But the problem asks for an **O(n)** solution.

Using a `HashSet` gives us:

```text
HashSet → O(1) average lookup
```

Therefore, we can solve it in:

```text
O(n)
```

---

## Complexity Analysis

### Time Complexity: O(n)

We insert all elements into the set and process each sequence only from its starting number.

### Space Complexity: O(n)

The `HashSet` stores the elements.

---

## Key Takeaway

The most important condition is:

```java
if (!set.contains(num - 1))
```

It means:

```text
num - 1 doesn't exist
        ↓
num is the START of a sequence
        ↓
Start counting
```

For:

```text
[1,2,3,4]
```

we only start from:

```text
1
```

not:

```text
2
3
4
```

### Remember

```text
HashSet
   ↓
Find sequence START
   ↓
Count num + 1
   ↓
Update longest
```

**HashSet → O(n) Time | O(n) Space**
