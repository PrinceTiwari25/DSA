# LeetCode 455 — Assign Cookies

## 🔗 Problem Link

[LeetCode 455 — Assign Cookies](https://leetcode.com/problems/assign-cookies/)

## 📝 Problem Statement

You are given two integer arrays:

- `g[i]` represents the greed factor of the \(i\)-th child.
- `s[j]` represents the size of the \(j\)-th cookie.

A child is content if they receive a cookie whose size is **greater than or equal to** their greed factor.

Each child can receive at most one cookie, and each cookie can be assigned to at most one child.

Return the **maximum number of content children**.

---

## 💡 Example

**Input**
```text
g = [1, 2, 3]
s = [1, 1]
```

**Output**
```text
1
```

**Explanation**

The children need cookie sizes of at least `1`, `2`, and `3`.

We have two cookies, both of size `1`.

Only one child can receive a cookie and become content.

---

## 🚀 Approach: Greedy + Sorting + Two Pointers

We use a **greedy algorithm** with sorting and two pointers.

### Key Idea

> Give the smallest suitable cookie to the least greedy child. This saves larger cookies for children who need them.

First, sort both arrays in ascending order.

- `i` points to the current child.
- `j` points to the current cookie.
- `count` tracks the number of content children.

If the current cookie can satisfy the current child, assign it and move to the next child.

Otherwise, the cookie is too small, so skip it and try the next cookie.





---

## 🔍 Code Explanation

### 1. Sort Both Arrays

```java
Arrays.sort(g);
Arrays.sort(s);
```

Sorting arranges the children and cookies from smallest to largest.

Example:

```text
g = [3, 1, 2] → [1, 2, 3]
s = [2, 1]    → [1, 2]
```

This allows us to satisfy children with the smallest suitable cookies first.

### 2. Initialize Variables

```java
int i = 0, j = 0;
int count = 0;
```

| Variable | Meaning |
|---|---|
| `i` | Index of the current child |
| `j` | Index of the current cookie |
| `count` | Number of content children |

Initially, both pointers start at index `0`.

### 3. While Loop

```java
while (i < g.length && j < s.length)
```

Continue while there are both children and cookies available.

We stop when either every child has been considered or every cookie has been used or skipped.

### 4. Check Whether the Cookie Is Suitable

```java
if (s[j] >= g[i])
```

If the cookie size is greater than or equal to the child's greed factor, the child can be satisfied.

For example:

```text
Child greed = 2
Cookie size = 3
```

Since:

```text
3 >= 2
```

the child is content.

### 5. Assign the Cookie

```java
count++;
i++;
```

- `count++` increases the number of content children.
- `i++` moves to the next child.

### 6. Move to the Next Cookie

```java
j++;
```

Whether the cookie was suitable or too small, we move to the next cookie.

If it was too small, no later child can have a lower greed factor than the current child, because the array is sorted. So that cookie can safely be skipped.

---

## 🧪 Dry Run

Consider:

```text
g = [1, 2, 3]
s = [1, 1]
```

Both arrays are already sorted.

Initially:

```text
i = 0
j = 0
count = 0
```

### Iteration 1

```text
g[i] = 1
s[j] = 1
```

Check:

```text
1 >= 1 → true
```

The child is content.

```text
count = 1
i = 1
j = 1
```

### Iteration 2

```text
g[i] = 2
s[j] = 1
```

Check:

```text
1 >= 2 → false
```

The cookie is too small, so we skip it.

```text
count = 1
i = 1
j = 2
```

Now `j == s.length`, so the loop stops.

### Final Output

```text
1
```

---

## 🧪 Another Example

Consider:

```text
g = [1, 2, 3]
s = [1, 2, 3]
```

| Child greed `g[i]` | Cookie size `s[j]` | Result | Count |
|---:|---:|---|---:|
| 1 | 1 | Content | 1 |
| 2 | 2 | Content | 2 |
| 3 | 3 | Content | 3 |

Output:

```text
3
```

All three children can be satisfied.

---

## ❓ Why Do We Sort the Arrays?

Suppose:

```text
g = [3, 1, 2]
s = [2, 1, 3]
```

After sorting:

```text
g = [1, 2, 3]
s = [1, 2, 3]
```

Now we can match the smallest child with the smallest suitable cookie.

This avoids wasting a large cookie on a child who could have been satisfied with a smaller one.

---

## ❓ Why Do We Move `j` Every Time?

```java
j++;
```

We always move to the next cookie because each cookie can be used at most once.

There are two possibilities:

- **Cookie is suitable:** Assign it, then move to the next child and cookie.
- **Cookie is too small:** Skip it and try the next cookie for the same child.

Notice that `i` only increases when a child is satisfied.

---

## ❓ Why Is This Greedy Approach Optimal?

The smallest remaining child is the easiest to satisfy.

If the smallest remaining cookie can satisfy that child, using it does not waste a larger cookie that could help another child.

If the cookie is too small, it cannot satisfy the current child or any child with a greater greed factor, so skipping it is safe.

This is why sorting and matching greedily produces the maximum number of content children.

---

## ⏱️ Complexity Analysis

Let:

- `n` = number of children (`g.length`)
- `m` = number of cookies (`s.length`)

### Time Complexity

Sorting the arrays takes:

```text
O(n log n + m log m)
```

The two-pointer loop processes each child and cookie at most once:

```text
O(n + m)
```

Therefore, the overall time complexity is:

\[
\boxed{O(n\log n+m\log m)}
\]

### Space Complexity

The two-pointer algorithm uses:

```text
O(1)
```

extra variables.

However, Java's `Arrays.sort(int[])` implementation may use stack space internally. Therefore, `O(1)` is the extra space for the **two-pointer logic**, excluding sorting's implementation-dependent auxiliary space.

---

## 🧠 Key Takeaways

- Sort both arrays in ascending order.
- Use two pointers to track the current child and cookie.
- Assign a cookie only when `s[j] >= g[i]`.
- Increment `i` only when a child is satisfied.
- Increment `j` after every cookie considered.
- Use a greedy strategy to maximize the number of content children.

## 📌 Final Complexity

```text
Time Complexity: O(n log n + m log m)
Two-pointer Auxiliary Space: O(1)
```

### One-Line Idea

**Sort the children and cookies, then use two pointers to give each child the smallest available cookie that satisfies their greed factor.**