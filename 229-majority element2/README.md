# Majority Element II

## LeetCode Problem

[Majority Element II](https://leetcode.com/problems/majority-element-ii/)

---

## Problem

Given an integer array `nums` of size `n`, return all elements that appear **more than `n / 3` times**.

The answer can contain at most **two elements**.

### Example

**Input:**

```text
nums = [3,2,3]
```

**Output:**

```text
[3]
```

### Another Example

**Input:**

```text
nums = [1,1,1,3,3,2,2,2]
```

**Output:**

```text
[1,2]
```

Because:

```text
1 → 3 times
2 → 3 times
n / 3 = 8 / 3 = 2
```

Both `1` and `2` occur more than `2` times.

---

## Optimal Approach: Boyer-Moore Voting Algorithm

For the normal **Majority Element** problem, we use one candidate.

Here, an element must appear more than `n / 3` times.

There can be **at most two majority elements**.

So we use:

```text
candidate1
candidate2
count1
count2
```

### Why at most two?

If there were three different elements appearing more than `n / 3` times:

```text
> n/3 + > n/3 + > n/3
```

Their total frequency would be greater than `n`, which is impossible.

Therefore, we only need **two candidates**.

---

## Approach

### Step 1: Find Two Possible Candidates

Traverse the array.

For every number:

```text
If it equals candidate1:
    count1++

Else if it equals candidate2:
    count2++

Else if count1 == 0:
    candidate1 = number
    count1 = 1

Else if count2 == 0:
    candidate2 = number
    count2 = 1

Else:
    count1--
    count2--
```

The last case means:

> We found a number different from both candidates, so cancel one occurrence of each candidate.

---

### Step 2: Verify the Candidates

The voting process only gives us **possible candidates**.

So we traverse the array again and count:

```text
candidate1
candidate2
```

Then add a candidate to the answer only if:

```text
count > n / 3
```



## Dry Run

### Input

```text
nums = [1,1,1,3,3,2,2,2]
```

Here:

```text
n = 8
n / 3 = 2
```

We need elements appearing **more than 2 times**.

Initially:

```text
candidate1 = -
candidate2 = -
count1 = 0
count2 = 0
```

### Process `1`

```text
candidate1 = 1
count1 = 1
```

### Process `1`

```text
count1 = 2
```

### Process `1`

```text
count1 = 3
```

### Process `3`

`3` is different from both candidates and `count2 = 0`.

So:

```text
candidate2 = 3
count2 = 1
```

### Process `3`

```text
count2 = 2
```

### Process `2`

`2` is different from both candidates.

So cancel one count from each:

```text
count1 = 2
count2 = 1
```

### Process `2`

Again:

```text
count1 = 1
count2 = 0
```

### Process `2`

Since `count2 == 0`:

```text
candidate2 = 2
count2 = 1
```

Possible candidates are:

```text
candidate1 = 1
candidate2 = 2
```

---

## Verification

Count their actual frequencies.

```text
1 → 3 times
2 → 3 times
```

Required:

```text
n / 3 = 2
```

Both satisfy:

```text
3 > 2
```

Therefore:

```text
[1,2]
```

---

## Complexity Analysis

### Time Complexity: O(n)

We traverse the array twice:

```text
First pass → find candidates
Second pass → verify candidates
```

Therefore:

```text
O(n)
```

### Space Complexity: O(1)

We use only:

```text
candidate1
candidate2
count1
count2
```

The output list is not considered extra working space.

---

## Key Takeaway

For **Majority Element II**:

```text
More than n/3
       ↓
At most 2 majority elements
       ↓
Use 2 candidates
       ↓
Boyer-Moore Voting
       ↓
Verify candidates
```

### Remember

```text
Same candidate → increase count

Empty candidate → select new candidate

Different from both → decrease both counts
```

And always remember:

> **Candidate selection is not enough — verify the candidates in a second pass.**

**Boyer-Moore Voting → O(n) Time | O(1) Extra Space**
