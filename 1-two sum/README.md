# Two Sum

## LeetCode Problem

[Two Sum](https://leetcode.com/problems/two-sum/)

---

## Problem

Given an array of integers `nums` and an integer `target`, return the **indices of the two numbers** such that they add up to `target`.

You may assume that each input has exactly one solution, and you cannot use the same element twice.

### Example

**Input:**

```text
nums = [2, 7, 11, 15]
target = 9
```

**Output:**

```text
[0, 1]
```

Because:

```text
2 + 7 = 9
```

---

## Solution

### Optimal Approach: HashMap

We use a `HashMap` to store each number along with its index.

Instead of searching for two numbers whose sum is equal to `target`, we calculate the number we **need**.

For every element:

```text
needed = target - current number
```

Then check whether `needed` already exists in the HashMap.

### Steps

1. Create an empty HashMap.
2. Traverse the array from left to right.
3. Calculate:

```text
needed = target - nums[i]
```

4. Check if `needed` exists in the HashMap.
5. If it exists:
   - We found the required pair.
   - Return the stored index and current index.
6. If it doesn't exist:
   - Store the current number and its index in the HashMap.
7. Continue until the pair is found.

---



## Dry Run

### Input

```text
nums = [2, 7, 11, 15]
target = 9
```

Initially:

```text
map = {}
```

### Step 1

Current number:

```text
nums[0] = 2
```

Calculate:

```text
needed = 9 - 2
       = 7
```

Check:

```text
7 exists in map?
```

No.

Store `2` and its index:

```text
map = {
    2 → 0
}
```

---

### Step 2

Current number:

```text
nums[1] = 7
```

Calculate:

```text
needed = 9 - 7
       = 2
```

Check:

```text
2 exists in map?
```

Yes!

The HashMap contains:

```text
2 → 0
```

Current index is:

```text
7 → index 1
```

Therefore:

```text
[0, 1]
```

Because:

```text
2 + 7 = 9
```

---

## Dry Run Table

| i | nums[i] | Needed | HashMap | Result |
|---|---:|---:|---|---|
| 0 | 2 | 7 | `{}` | Store `2 → 0` |
| 1 | 7 | 2 | `{2 → 0}` | Found `2` |

### Final Answer

```text
[0, 1]
```

---

## Complexity Analysis

### Time Complexity: O(n)

**Reason:** We traverse the array only once, and HashMap lookup takes `O(1)` average time.

### Space Complexity: O(n)

**Reason:** In the worst case, we store all elements in the HashMap.

---

## Key Takeaway

The main idea is:

```text
Current Number
      ↓
target - current
      ↓
   Needed Number
      ↓
Check HashMap
   ↙        ↘
Found      Not Found
  ↓            ↓
Return      Store Current
indices       number
```

### Remember

> **Don't search for the second number. Calculate what number you need and check whether you have already seen it.**

```text
needed = target - nums[i]
```

For example:

```text
target = 9
current = 7

needed = 9 - 7
       = 2
```

If `2` is already in the HashMap, we have found the answer.

**HashMap → O(n) Time | O(n) Space**
