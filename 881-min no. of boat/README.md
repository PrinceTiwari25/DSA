# LC 881 — Boats to Save People

## 🔗 Question Link

https://leetcode.com/problems/boats-to-save-people/

---

## 📝 Question

You are given an array `people` where `people[i]` represents the weight of the `i-th` person.

You are also given an integer `limit`, which represents the maximum weight that a boat can carry.

Each boat can carry **at most two people** at the same time, provided that their combined weight does not exceed `limit`.

Return the **minimum number of boats** needed to rescue everyone.

### Example

```text
Input:
people = [3,2,2,1]
limit = 3

Output:
3
```

### Explanation

We can arrange the people as:

```text
Boat 1 → 1 + 2 = 3
Boat 2 → 2
Boat 3 → 3
```

Therefore, we need:

```text
3 boats
```

---

# 🚀 Optimal Approach — Sorting + Two Pointers

First, sort the array.

```text
people = [3,2,2,1]

After sorting:

[1,2,2,3]
```

Use two pointers:

```text
i → lightest person
j → heaviest person
```

Initially:

```text
i = 0
j = people.length - 1
boat = 0
```

The idea is to always try to put the **lightest and heaviest person together**.

---

## 🔄 Pointer Movement

### Case 1 — Both People Can Fit

If:

```text
people[i] + people[j] <= limit
```

Then both people can share one boat.

So:

```text
i++
j--
```

And one boat is used.

---

### Case 2 — They Cannot Fit

If:

```text
people[i] + people[j] > limit
```

The heaviest person cannot go with the lightest person.

Since the lightest person is already the smallest possible person, the heaviest person cannot fit with **anyone else** either.

Therefore, the heaviest person must go alone:

```text
j--
```

And one boat is used.

---

## Why Do We Always Check the Lightest + Heaviest?

Suppose:

```text
[1,2,2,3]
limit = 3
```

For the heaviest person `3`:

```text
3 + 1 = 4 > 3
```

So `3` cannot share a boat with anyone.

We send `3` alone.

But if:

```text
3 + 1 <= 3
```

then the lightest person is the best possible partner for `3`.

This greedy choice gives the minimum number of boats.

---

# 🔍 Dry Run

### Input

```text
people = [3,2,2,1]
limit = 3
```

---

## Step 1 — Sort

```text
[3,2,2,1]
```

After sorting:

```text
[1,2,2,3]
```

Pointers:

```text
 i           j
 ↓           ↓
[1, 2, 2, 3]
```

Initially:

```text
i = 0
j = 3
boat = 0
```

---

## Step 2

Lightest:

```text
people[i] = 1
```

Heaviest:

```text
people[j] = 3
```

Check:

```text
1 + 3 = 4
```

Since:

```text
4 > 3
```

They cannot share a boat.

So the heaviest person goes alone:

```text
j--
```

And:

```text
boat++
```

Now:

```text
i = 0
j = 2
boat = 1
```

---

## Step 3

Now:

```text
people[i] = 1
people[j] = 2
```

Check:

```text
1 + 2 = 3
```

Since:

```text
3 <= 3
```

Both can share a boat.

Move both pointers:

```text
i++
j--
```

And:

```text
boat++
```

Now:

```text
i = 1
j = 1
boat = 2
```

---

## Step 4

Now only one person remains:

```text
people[1] = 2
```

Since:

```text
i <= j
```

one more boat is needed.

```text
boat++
```

Therefore:

```text
boat = 3
```

---

# 📊 Dry Run Table

| `i` | `j` | Lightest | Heaviest | Sum | Action | Boats |
|---:|---:|---:|---:|---:|---|---:|
| 0 | 3 | 1 | 3 | 4 | Heaviest alone → `j--` | 1 |
| 0 | 2 | 1 | 2 | 3 | Both together → `i++`, `j--` | 2 |
| 1 | 1 | 2 | 2 | — | One person → `j--` | 3 |

---

# ✅ Final Answer

```text
3
```

The boats can be:

```text
Boat 1 → [3]
Boat 2 → [1,2]
Boat 3 → [2]
```

Total:

```text
3 boats
```

---

# 🔑 Key Takeaway

The main pattern is:

```text
Sort
  ↓
Lightest + Heaviest
  ↓
Can they fit?
 ↙          ↘
YES         NO
 ↓           ↓
Both        Heaviest
together    alone
 ↓           ↓
i++, j--    j--
```

### Remember

> **Always try to pair the heaviest person with the lightest person.**

If they fit:

```text
i++ and j--
```

If they don't:

```text
j--
```

Every iteration uses exactly **one boat**.

---

# ⏱️ Complexity

### Time Complexity

```text
O(n log n)
```

Sorting takes `O(n log n)` and the two-pointer traversal takes `O(n)`.

### Space Complexity

```text
O(1)
```

Auxiliary space, excluding the sorting implementation.