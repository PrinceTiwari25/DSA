# LeetCode 77 — Combinations

## 🔗 Problem Link

[LeetCode 77 — Combinations](https://leetcode.com/problems/combinations/)

## 📝 Problem Statement

Given two integers `n` and `k`, return all possible combinations of `k` numbers chosen from the range `[1, n]`.

The order of numbers inside a combination does not matter, and duplicate combinations are not allowed.

### Example

**Input**
```text
n = 4, k = 2
```

**Output**
```text
[[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]
```

### Explanation

We must select exactly `2` numbers from:

```text
[1, 2, 3, 4]
```

The possible combinations are:

```text
[1, 2]
[1, 3]
[1, 4]
[2, 3]
[2, 4]
[3, 4]
```

The total number of combinations is:

\[
\binom{n}{k}=\frac{n!}{k!(n-k)!}
\]

For `n = 4` and `k = 2`:

\[
\binom{4}{2}=\frac{4!}{2!2!}=6
\]

---

## 🚀 Approach: Recursion and Backtracking

We use **recursion and backtracking** to generate every valid combination.

For each number `i`, we have two choices:

1. **Include** the number in the current combination.
2. **Exclude** the number and move to the next one.

However, unlike LeetCode 78 (Subsets), we only save a combination when its size becomes exactly `k`.

We also use **pruning** to stop exploring branches that cannot produce a valid combination.

### Key Idea

```text
Choose a number
      ↓
Include it → Explore
      ↓
Remove it (backtrack)
      ↓
Exclude it → Explore
```



---

## 🔍 Code Explanation

### 1. Create the answer list

```java
List<List<Integer>> ans = new ArrayList<>();
```

- `List<Integer>` stores one combination.
- `List<List<Integer>>` stores all valid combinations.

For example:

```text
ans = [
    [1, 2],
    [1, 3],
    [2, 3]
]
```

### 2. Start recursion

```java
comb(1, n, k, new ArrayList<>(), ans);
```

We begin with number `1` and an empty combination.

| Parameter | Meaning |
|---|---|
| `i` | Current number being considered |
| `n` | Largest available number |
| `k` | Required combination size |
| `list` | Current combination |
| `ans` | Stores all completed combinations |

Initially:

```text
i = 1
list = []
ans = []
```

---

## 3. Base Case

```java
if (list.size() == k) {
    ans.add(new ArrayList<>(list));
    return;
}
```

This condition checks whether the current combination contains exactly `k` numbers.

For example, if:

```text
k = 2
list = [1, 3]
```

Then:

```text
list.size() = 2
k = 2
```

The condition is true, so we save a copy:

```text
ans = [[1, 3]]
```

The `return` stops this recursive branch because the combination is complete.

**Why use `new ArrayList<>(list)`?**

It saves a separate copy. Later backtracking changes `list`, but the saved combination must remain unchanged.

---

## 4. Pruning Conditions

```java
if (i > n || list.size() + (n - i + 1) < k) {
    return;
}
```

This line contains two checks.

### Condition A: `i > n`

```java
i > n
```

This means there are no numbers left to consider.

For example:

```text
n = 4
i = 5
```

Since `5 > 4`, the recursion stops.

If the combination has not reached size `k`, this branch cannot produce a valid answer.

### Condition B: Not Enough Numbers Remain

```java
list.size() + (n - i + 1) < k
```

This condition checks whether we can still reach the required combination size.

Let's understand each part:

```java
list.size()
```

The number of elements already selected.

```java
n - i + 1
```

The number of available numbers from `i` through `n`, including both endpoints.

For example:

```text
n = 5
i = 4
list = [1]
k = 3
```

Numbers remaining:

```text
5 - 4 + 1 = 2
```

Maximum possible combination size:

```text
Already selected + Remaining numbers
= 1 + 2
= 3
```

We can still reach `k = 3`, so we should continue.

Now suppose:

```text
n = 5
i = 5
list = [1]
k = 3
```

Numbers remaining:

```text
5 - 5 + 1 = 1
```

Maximum possible size:

```text
1 + 1 = 2
```

But we need `3` numbers.

Therefore:

```text
2 < 3
```

The condition becomes true, and we stop exploring this branch.

**Why is pruning useful?**

It avoids wasting time exploring branches that cannot possibly produce a combination of size `k`.

---

## 5. Include the Current Number

```java
list.add(i);
comb(i + 1, n, k, list, ans);
```

We first choose the current number.

For example:

```text
i = 1
list = []
```

After:

```java
list.add(i);
```

we have:

```text
list = [1]
```

Then we call:

```java
comb(i + 1, n, k, list, ans);
```

This becomes:

```java
comb(2, n, k, list, ans);
```

We move to `2` because each number should be considered only once in a combination.

---

## 6. Backtracking

```java
list.remove(list.size() - 1);
```

After exploring the branch where the current number is included, we remove it.

Example:

```text
Before removal: [1, 2]
After removal:  [1]
```

This restores the previous state so we can explore the alternative choice.

Backtracking means:

> Make a choice, explore it, undo the choice, and explore the alternative.

---

## 7. Exclude the Current Number

```java
comb(i + 1, n, k, list, ans);
```

After removing `i`, we continue without selecting it.

For example:

```text
Current list = [1]
Current number = 2
```

The include branch explores combinations containing `2`.

After backtracking, the exclude branch explores combinations that do not contain `2`.

---

# 🌳 Recursion Tree

Consider:

```text
n = 3
k = 2
```

We must choose two numbers from `[1, 2, 3]`.

```text
                         []
                       /    \
                  Include 1  Exclude 1
                     [1]        []
                    /   \      /   \
               Include 2 Exclude 2 ...
                  [1,2]    [1]
                    /        \
               Include 3    Exclude 3
                  [1,2,3]     [1]
```

Once `[1, 2]` is created, the base case saves it immediately because its size equals `k`.

The recursion then explores the other possibilities.

The valid combinations are:

```text
[1, 2]
[1, 3]
[2, 3]
```

The tree above is a simplified illustration of the include/exclude decisions; completed branches stop at the base case or a pruning condition.

---

# 🧪 Dry Run

Consider:

```text
n = 3
k = 2
```

Initially:

```text
list = []
ans = []
i = 1
```

### Step 1: Include `1`

```java
list.add(1);
```

Now:

```text
list = [1]
```

Call:

```java
comb(2, 3, 2, list, ans);
```

### Step 2: Include `2`

```java
list.add(2);
```

Now:

```text
list = [1, 2]
```

Call:

```java
comb(3, 3, 2, list, ans);
```

The base case is true:

```text
list.size() == k
2 == 2
```

Save the combination:

```text
ans = [[1, 2]]
```

### Step 3: Backtrack and exclude `2`

Remove `2`:

```text
list = [1]
```

The exclude branch moves to `3`.

Include `3`:

```text
list = [1, 3]
```

The base case is true, so save it:

```text
ans = [[1, 2], [1, 3]]
```

### Step 4: Backtrack and exclude `1`

After completing the branches starting with `1`, remove it:

```text
list = []
```

Now explore combinations that do not include `1`.

Include `2`:

```text
list = [2]
```

Then include `3`:

```text
list = [2, 3]
```

Save:

```text
ans = [[1, 2], [1, 3], [2, 3]]
```

### Final Output

```text
[[1, 2], [1, 3], [2, 3]]
```

---

# ❓ Why Do We Use `i + 1`?

```java
comb(i + 1, n, k, list, ans);
```

We always move forward through the numbers.

Suppose we have selected:

```text
[1, 3]
```

We must not select `1` or `3` again, and we must avoid generating the same combination in a different order.

Moving to `i + 1` ensures that each number is considered at most once and combinations are generated in increasing order.

For example, `[1, 3]` is generated, but `[3, 1]` is never generated.

---

# ⚖️ Difference Between LC 78 and LC 77

| Feature | LC 78 — Subsets | LC 77 — Combinations |
|---|---|---|
| Input | Array `nums` | Integers `n`, `k` |
| Goal | Generate every subset | Choose exactly `k` numbers |
| Valid sizes | `0` to `n` | Exactly `k` |
| Base case | `i == nums.length` | `list.size() == k` |
| Pruning | Usually unnecessary | Stops impossible branches |
| Main technique | Recursion and backtracking | Recursion, backtracking and pruning |

---

# ⏱️ Complexity Analysis

Let:

- `n` = total available numbers
- `k` = required size of each combination
- \(C(n,k)=\binom{n}{k}\) = number of valid combinations

### Time Complexity

There are:

\[
\binom{n}{k}
\]

valid combinations, and each combination contains `k` elements.

Copying and storing all valid combinations requires:

\[
O\left(k\binom{n}{k}\right)
\]

The recursion also explores decision branches that do not become valid combinations. With pruning, these unnecessary branches are reduced.

For the standard backtracking algorithm, a useful overall worst-case bound is:

\[
\boxed{O\left(n\binom{n}{k}\right)}
\]

This accounts for exploring the search tree and producing the output.

### Auxiliary Space Complexity

The current combination can contain up to `k` elements, and recursion can go as deep as `n`.

Therefore, auxiliary space is:

\[
\boxed{O(n)}
\]

The recursion stack and current list are both bounded by `O(n)`.

### Output Space Complexity

There are \(\binom{n}{k}\) combinations, each containing `k` elements:

\[
\boxed{O\left(k\binom{n}{k}\right)}
\]

---

# 🧠 Key Takeaways

- `comb()` recursively explores include and exclude choices.
- `list.size() == k` identifies a completed combination.
- `i > n` stops when there are no numbers left.
- `list.size() + (n - i + 1) < k` prunes branches that cannot reach size `k`.
- `list.remove(list.size() - 1)` undoes the previous choice.
- `i + 1` prevents selecting the same number again.

## 📌 Final Complexity

```text
Time Complexity:            O(n × C(n, k)) — worst-case bound
Auxiliary Space Complexity: O(n)
Output Space Complexity:    O(k × C(n, k))
```

### One-Line Idea

**Use include/exclude recursion to build combinations of exactly `k` numbers, and prune any branch that cannot possibly reach the required size.**