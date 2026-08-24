# Count of Smaller Numbers After Self

## LeetCode Problem

[Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/)

---

## Problem

Given an integer array `nums`, return an integer array `answer` where:

```text
answer[i]
```

is the number of elements to the **right of `nums[i]`** that are **strictly smaller than `nums[i]`**.

### Example

**Input:**

```text
nums = [5,2,6,1]
```

**Output:**

```text
[2,1,1,0]
```

### Explanation

For `5`:

```text
Right side = [2,6,1]

Smaller numbers:
2, 1

Count = 2
```

For `2`:

```text
Right side = [6,1]

Smaller number:
1

Count = 1
```

For `6`:

```text
Right side = [1]

Smaller number:
1

Count = 1
```

For `1`:

```text
Right side = []

Count = 0
```

Therefore:

```text
[2,1,1,0]
```

---

## Optimal Approach: Merge Sort

A brute-force approach checks every element with all elements to its right.

That takes:

```text
O(n²)
```

We can do better using **Merge Sort**.

### Main Idea

During the merge step, the left half and right half are already sorted.

For an element in the left half, if an element from the right half is smaller than it, then that element contributes to the answer.

We maintain:

```text
smallerCount
```

which tells us how many elements from the right half have already moved before the current left element.

### Example

Suppose:

```text
Left  = [5, 6]
Right = [1, 2]
```

When we compare:

```text
1 < 5
```

we move `1` first.

Now:

```text
smallerCount = 1
```

Then:

```text
2 < 5
```

Move `2`.

Now:

```text
smallerCount = 2
```

Therefore, `5` has:

```text
2
```

smaller elements on its right.

---

## Steps

1. Store every number along with its original index.
2. Apply Merge Sort.
3. During merging, count how many right-half elements are smaller.
4. Add that count to the answer of the current left-half element.
5. Continue until the entire array is sorted.

The original index is important because the final answer must be in the **original array order**.



## Dry Run

### Input

```text
nums = [5,2,6,1]
```

Initially:

```text
Index:  0  1  2  3
Value:  5  2  6  1
```

We keep the original index:

```text
[5,index 0]
[2,index 1]
[6,index 2]
[1,index 3]
```

---

### Merge `[5]` and `[2]`

Compare:

```text
2 < 5
```

So `2` moves first.

```text
smallerCount = 1
```

Therefore:

```text
answer[0] += 1
```

So:

```text
answer = [1,0,0,0]
```

---

### Merge `[6]` and `[1]`

Compare:

```text
1 < 6
```

So:

```text
smallerCount = 1
```

Therefore:

```text
answer[2] += 1
```

Now:

```text
answer = [1,0,1,0]
```

---

### Final Merge

The two sorted halves are:

```text
Left  = [2,5]
Right = [1,6]
```

First:

```text
1 < 2
```

So:

```text
smallerCount = 1
```

Now `2` has one smaller element:

```text
answer[1] += 1
```

Then:

```text
2 < 6
```

No new smaller right element.

Finally:

```text
5 < 6
```

So `5` also has one smaller element on its right:

```text
answer[0] += 1
```

Final:

```text
[2,1,1,0]
```

---

## Complexity Analysis

### Time Complexity: O(n log n)

Merge Sort divides the array into smaller parts and merges them efficiently.

```text
O(n log n)
```

### Space Complexity: O(n)

We use temporary arrays and store values with their original indices.

```text
O(n)
```

---

## Key Takeaway

The main idea is:

```text
Merge Sort
    ↓
Sort while merging
    ↓
Count smaller right-side elements
    ↓
Store count using original index
```

During merging:

```text
right value < left value
        ↓
smallerCount++
```

When taking a left value:

```text
answer[originalIndex] += smallerCount
```

### Remember

> **Every right-side element that moves before a left-side element is smaller than it, so it contributes to that left element's count.**

**Merge Sort → O(n log n) Time | O(n) Space**
