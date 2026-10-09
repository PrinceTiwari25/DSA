# LeetCode 78 — Subsets

## 🔗 Problem Link

[LeetCode 78 — Subsets](https://leetcode.com/problems/subsets/)

## 📝 Problem Statement

Given an integer array `nums` containing unique elements, return **all possible subsets** (the power set).

The solution must not contain duplicate subsets. The subsets can be returned in any order.

### Example

**Input**
```text
nums = [1, 2, 3]
```

**Output**
```text
[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
```

### Explanation

Every element has two choices:

1. Include the element in the subset.
2. Exclude the element from the subset.

For an array of length `n`, the total number of subsets is:

\[
2^n
\]

For `[1, 2, 3]`, the total number of subsets is:

\[
2^3 = 8
\]

---

## 🚀 Approach: Recursion and Backtracking

We solve this problem using **recursion**.

For every element, we make two recursive calls:

- **Include:** Add the current element to the list.
- **Exclude:** Remove the current element and continue without it.

When all elements have been processed, we add a copy of the current list to the answer.

### Key Idea

```text
For each element:
    Include it
    Exclude it
```


---

## 🔍 Code Explanation

### 1. Create the answer list

```java
List<List<Integer>> ans = new ArrayList<>();
```

- `List<Integer>` represents one subset.
- `List<List<Integer>>` stores all the subsets.

For example:

```text
ans = [
    [],
    [1],
    [2],
    [1, 2]
]
```

### 2. Start recursion

```java
solve(nums, 0, new ArrayList<>(), ans);
```

We pass four important pieces of information across the recursive calls:

| Variable | Meaning |
|---|---|
| `nums` | Original input array |
| `i` | Index of the current element |
| `list` | Subset currently being constructed |
| `ans` | Stores all completed subsets |

Initially:

```text
i = 0
list = []
ans = []
```

### 3. Base case

```java
if (i == nums.length) {
    ans.add(new ArrayList<>(list));
    return;
}
```

The base case executes when every element has been considered.

For example:

```text
nums = [1, 2]
i = 2
list = [1, 2]
```

Since `i == nums.length`, we save the subset:

```text
ans = [[1, 2]]
```

**Why `new ArrayList<>(list)`?**

It creates a copy of the current subset. If we simply wrote `ans.add(list)`, the answer would keep references to the same mutable list, and later backtracking operations would change previously stored results.

### 4. Include the current element

```java
list.add(nums[i]);
solve(nums, i + 1, list, ans);
```

Suppose:

```text
nums = [1, 2]
i = 0
list = []
```

First:

```java
list.add(nums[0]);
```

Now:

```text
list = [1]
```

Then:

```java
solve(nums, 1, list, ans);
```

We continue to the next element with `1` included.

### 5. Backtrack

```java
list.remove(list.size() - 1);
```

After the include branch finishes, we remove the last element to restore the previous state.

For example:

```text
Before removal: [1, 2]
After removal:  [1]
```

This allows the next recursive call to explore the possibility of excluding `2`.

### 6. Exclude the current element

```java
solve(nums, i + 1, list, ans);
```

After removing the current element, we move to the next index without including it.

For example:

```text
list = [1]
```

The next call processes index `1` with `2` excluded, eventually generating `[1]`.

---

## 🌳 Recursion Tree

For:

```text
nums = [1, 2]
```

The recursion tree is:

```text
                         []
                       /    \
                 Include 1   Exclude 1
                    [1]         []
                   /   \       /   \
             Include 2 Exclude 2  Include 2 Exclude 2
                [1,2]    [1]       [2]       []
```

The leaf nodes represent the final subsets:

```text
[1, 2]
[1]
[2]
[]
```

Therefore, the output contains four subsets.

---

## 🧪 Dry Run

Consider:

```text
nums = [1, 2]
```

Initially:

```text
list = []
ans = []
i = 0
```

### Step 1: Include `1`

```java
list.add(nums[0]);
```

Now:

```text
list = [1]
```

Call:

```java
solve(nums, 1, list, ans);
```

### Step 2: Include `2`

```java
list.add(nums[1]);
```

Now:

```text
list = [1, 2]
```

Call:

```java
solve(nums, 2, list, ans);
```

Since `i == nums.length`, save a copy:

```text
ans = [[1, 2]]
```

### Step 3: Exclude `2`

Backtrack:

```java
list.remove(list.size() - 1);
```

Now:

```text
list = [1]
```

The second recursive call excludes `2`.

Save:

```text
ans = [[1, 2], [1]]
```

### Step 4: Backtrack and exclude `1`

Return to index `0` and remove `1`:

```text
list = []
```

Now explore the branch where `1` is excluded.

Include `2`:

```text
list = [2]
```

Save:

```text
ans = [[1, 2], [1], [2]]
```

Then backtrack and exclude `2`:

```text
list = []
```

Save the empty subset:

```text
ans = [[1, 2], [1], [2], []]
```

### Final Output

```text
[[1, 2], [1], [2], []]
```

The order is different from some examples, but it is valid because the problem allows any order.

---

## ❓ Why Do We Need `list.remove()`?

This line is essential:

```java
list.remove(list.size() - 1);
```

Without it, the element added in the include branch would remain in the list while exploring the exclude branch.

For example, after exploring `[1, 2]`, we must remove `2` before generating `[1]`.

**Backtracking means making a choice, exploring it, and undoing that choice before exploring the alternative.**

---

## ❓ Why Does Every Element Have Two Choices?

For every element, we choose:

```text
Include → 1
Exclude → 0
```

For three elements, the possible choices are:

```text
000
001
010
011
100
101
110
111
```

Each binary pattern represents one subset.

For example:

```text
101 → Include 1, Exclude 2, Include 3
```

This creates:

```text
[1, 3]
```

Since there are two choices per element, the total number of subsets is \(2^n\).

---

## ⏱️ Complexity Analysis

Let `n` be the length of `nums`.

### Time Complexity

There are:

\[
2^n
\]

subsets.

Copying each subset can take up to \(O(n)\) time.

Therefore, the total time complexity is:

\[
\boxed{O(n \cdot 2^n)}
\]

### Auxiliary Space Complexity

The recursion depth is at most `n`, and the current list can contain up to `n` elements.

Excluding the output storage, the auxiliary space complexity is:

\[
\boxed{O(n)}
\]

### Output Space Complexity

The answer contains \(2^n\) subsets, each containing up to `n` elements.

Therefore, the output space is:

\[
\boxed{O(n \cdot 2^n)}
\]

---

## 🧠 Key Takeaways

- Recursion explores the include and exclude choices.
- `i` tracks the current index.
- `list` stores the subset currently being constructed.
- The base case saves a copy when all elements have been processed.
- `list.remove()` undoes a choice during backtracking.
- Every element has two choices, producing \(2^n\) subsets.

### Final Complexity

```text
Time Complexity:            O(n × 2^n)
Auxiliary Space Complexity: O(n)
Output Space Complexity:    O(n × 2^n)
```

**One-line idea:** For every element, recursively explore both possibilities—include it or exclude it—and save the current subset once all elements have been considered.