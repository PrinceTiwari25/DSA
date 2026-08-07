# Majority Element

## Solution

### Approach: Boyer-Moore Voting Algorithm

The majority element appears **more than ⌊n / 2⌋ times**, so it can never be completely canceled out by the other elements.

We maintain two variables:

- `ans` → stores the current majority candidate.
- `freq` → stores the vote count of the current candidate.

### Algorithm

1. Initialize `freq = 0` and `ans = 0`.
2. Traverse the array:
   - If `freq == 0`, make the current element the new candidate (`ans = nums[i]`).
   - If `nums[i] == ans`, increment `freq`.
   - Otherwise, decrement `freq`.
3. After the traversal, `ans` contains the majority element.



## Dry Run

**Input**

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

| i | nums[i] | ans | freq |
|---|---------|-----|------|
| 0 | 2 | 2 | 1 |
| 1 | 2 | 2 | 2 |
| 2 | 1 | 2 | 1 |
| 3 | 1 | 2 | 0 |
| 4 | 1 | 1 | 1 |
| 5 | 2 | 1 | 0 |
| 6 | 2 | 2 | 1 |

**Output**

```text
2
```

---

## Complexity Analysis

### Time Complexity: **O(n)**

**Reason:** We traverse the array only once.

### Space Complexity: **O(1)**

**Reason:** Only two extra variables (`ans` and `freq`) are used.
