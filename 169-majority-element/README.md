# Majority Element

## Solution

### Approach: Boyer-Moore Voting Algorithm

The majority element appears **more than ⌊n / 2⌋ times** in the array.

The Boyer-Moore Voting Algorithm works by maintaining:

- **candidate** → current possible majority element.
- **count** → frequency balance of the candidate.

### Algorithm

1. Initialize `candidate` and `count = 0`.
2. Traverse the array:
   - If `count == 0`, set the current element as the new `candidate`.
   - If the current element equals the `candidate`, increment `count`.
   - Otherwise, decrement `count`.
3. After the traversal, the `candidate` is the majority element.

---

## Dry Run

**Input**

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

| Element | Candidate | Count |
|---------|-----------|------:|
| 2 | 2 | 1 |
| 2 | 2 | 2 |
| 1 | 2 | 1 |
| 1 | 2 | 0 |
| 1 | 1 | 1 |
| 2 | 1 | 0 |
| 2 | 2 | 1 |

**Final Answer**

```text
2
```

---

## Complexity Analysis

### Time Complexity: **O(n)**

**Reason:** We traverse the array exactly once.

### Space Complexity: **O(1)**

**Reason:** We only use two variables (`candidate` and `count`), regardless of the input size.
