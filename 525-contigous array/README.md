# LeetCode 525 — Contiguous Array

## 🔗 Problem Link

[LeetCode 525 — Contiguous Array](https://leetcode.com/problems/contiguous-array/)

## 📝 Problem Statement

Given a binary array `nums` containing only `0` and `1`, return the **maximum length of a contiguous subarray with an equal number of `0`s and `1`s**.

### Example

**Input**
```text
nums = [0, 1, 0, 1]
```

**Output**
```text
4
```

**Explanation**

The entire array contains two zeros and two ones, so the maximum length is `4`.

---

## 🚀 Approach: Prefix Sum + HashMap

The main idea is to replace:

```text
0 → -1
1 → +1
```

Now, a subarray has equal numbers of zeros and ones exactly when its transformed sum is `0`.

**Key observation:** If the same prefix sum occurs at two indices, the subarray between those indices has a sum of zero.

We use a HashMap to store:

```text
Prefix sum → Earliest index
```



---

## 🔍 Code Explanation

### 1. Create the HashMap

```java
HashMap<Integer, Integer> map = new HashMap<>();
```

Stores the first index where each prefix sum appears.

Example:

```text
sum → index
 0  → -1
-1  →  0
 1  →  2
```

### 2. Initialize Variables

```java
int maxLen = 0;
int sum = 0;
```

- `maxLen`: longest valid subarray found so far.
- `sum`: running prefix sum.

### 3. Initialize the Map

```java
map.put(0, -1);
```

This represents a prefix sum of zero before the array begins.

It allows us to count a valid subarray starting at index `0`.

For example, if the running sum becomes zero at index `2`:

```text
length = 2 - (-1) = 3
```

### 4. Convert Each Element

```java
sum += (nums[i] == 0) ? -1 : 1;
```

The ternary operator means:

```text
If nums[i] == 0:
    Add -1
Otherwise:
    Add +1
```

For example:

```text
nums = [0, 1, 1, 0]
Converted = [-1, 1, 1, -1]
```

### 5. Check Whether the Sum Exists

```java
if (map.containsKey(sum)) {
```

If the sum already exists, the elements between the previous occurrence and the current index have a sum of zero.

Therefore, the subarray contains equal numbers of zeros and ones.

### 6. Calculate the Length

```java
int length = i - map.get(sum);
```

Example:

```text
Current index = 5
Earliest index = 1

Length = 5 - 1 = 4
```

### 7. Update the Maximum

```java
maxLen = Math.max(maxLen, length);
```

Keep the larger of the current maximum and the newly found valid length.

### 8. Store Only the First Occurrence

```java
else {
    map.put(sum, i);
}
```

If the sum has never appeared before, store its index.

We do not overwrite an existing index because the earliest index gives the longest possible subarray when the sum appears again.

---

## 🧪 Dry Run

Consider:

```text
nums = [0, 1, 0, 1]
```

Initially:

```text
sum = 0
maxLen = 0
map = {0 = -1}
```

| Index `i` | `nums[i]` | Running sum | Previous index | Length | `maxLen` |
|---:|---:|---:|---:|---:|---:|
| 0 | 0 | -1 | Not found | — | 0 |
| 1 | 1 | 0 | -1 | 2 | 2 |
| 2 | 0 | -1 | 0 | 2 | 2 |
| 3 | 1 | 0 | -1 | 4 | 4 |

### Step-by-Step

**Index 0**

```text
sum = 0 + (-1) = -1
```

The sum is new, so store:

```text
map = {0 = -1, -1 = 0}
```

**Index 1**

```text
sum = -1 + 1 = 0
```

Sum `0` already exists at index `-1`.

```text
length = 1 - (-1) = 2
maxLen = 2
```

**Index 2**

```text
sum = 0 + (-1) = -1
```

Sum `-1` first appeared at index `0`.

```text
length = 2 - 0 = 2
maxLen = 2
```

**Index 3**

```text
sum = -1 + 1 = 0
```

Sum `0` first appeared at index `-1`.

```text
length = 3 - (-1) = 4
maxLen = 4
```

### Final Output

```text
4
```

---

## 🌳 Visual Understanding

Original array:

```text
Index:       0   1   2   3
nums:        0   1   0   1
```

After conversion:

```text
             -1  +1  -1  +1
```

Prefix sums, including the initial sum before index `0`:

```text
Index:       -1   0   1   2   3
Prefix sum:   0  -1   0  -1   0
```

Sum `0` occurs at indices `-1`, `1`, and `3`.

Using the earliest occurrence:

```text
Length = 3 - (-1) = 4
```

Thus, the entire array is a valid subarray.

---

## ❓ Why Do Equal Prefix Sums Work?

Suppose two prefix sums are equal:

\[
P_i=P_j
\]

Then their difference is zero:

\[
P_i-P_j=0
\]

This difference represents the sum of the elements between those prefix positions.

Because zeros were converted to `-1` and ones to `+1`, a sum of zero means there are equal numbers of zeros and ones.

## ❓ Why Keep the Earliest Index?

Suppose a sum first appears at index `1` and appears again at index `5`.

```text
Length = 5 - 1 = 4
```

If it appears again at index `7`:

```text
Length = 7 - 1 = 6
```

Keeping the earliest index allows us to find the longest subarray.

---

## ⏱️ Complexity Analysis

Let `n` be the length of `nums`.

**Time Complexity:**

The array is traversed once, and HashMap operations take expected \(O(1)\) time.

\[
\boxed{O(n)}
\]

**Space Complexity:**

In the worst case, the map stores up to `n + 1` prefix sums.

\[
\boxed{O(n)}
\]

---

## 🧠 Key Takeaways

- Convert `0` to `-1` and `1` to `+1`.
- Equal prefix sums indicate a valid subarray.
- Store `prefix sum → earliest index` in the HashMap.
- Initialize with `map.put(0, -1)` to handle subarrays starting at index `0`.
- Use `i - map.get(sum)` to calculate the subarray length.
- Keep the earliest index to maximize the length.

## 📌 Final Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(n)
```

**One-line idea:** Convert zeros to `-1`, ones to `+1`, and use a prefix-sum HashMap to find the longest subarray whose sum is zero.