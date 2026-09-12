# LC 2491 — Divide Players Into Teams of Equal Skill

## 🔗 Question Link

https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/

---

# 📝 Question

You are given an integer array `skill` where `skill[i]` represents the skill level of a player.

There are an even number of players.

You must divide the players into teams of **2 players each** such that:

- Every player belongs to exactly one team.
- The sum of skill levels of every team must be the **same**.

The **chemistry** of a team is:

```text
skill of player 1 × skill of player 2
```

Return the **sum of chemistry of all teams**.

If it is impossible to divide the players into teams with equal skill sums, return:

```text
-1
```

---

## Example

```text
Input:
skill = [3,2,5,1,3,4]

Output:
22
```

### Explanation

First sort the array:

```text
[1,2,3,3,4,5]
```

Pair the smallest with the largest:

```text
1 + 5 = 6
2 + 4 = 6
3 + 3 = 6
```

Every team's skill sum is `6`, so the teams are valid.

Chemistry:

```text
1 × 5 = 5
2 × 4 = 8
3 × 3 = 9
```

Total chemistry:

```text
5 + 8 + 9 = 22
```

---

# 🚀 Optimal Approach — Sorting + Two Pointers

The main idea is to **sort the skill array**.

After sorting:

```text
[1,2,3,3,4,5]
```

Use two pointers:

```text
i → smallest skill
j → largest skill
```

Initially:

```text
i = 0
j = skill.length - 1
```

---

## Step 1 — Find Target Sum

After sorting, the first possible team is formed using:

```text
smallest + largest
```

So calculate:

```text
targetSum = skill[i] + skill[j]
```

This is the skill sum that **every team must have**.

For the example:

```text
targetSum = 1 + 5
          = 6
```

---

## Step 2 — Check Every Pair

Now pair:

```text
smallest + largest
```

then:

```text
second smallest + second largest
```

and continue moving toward the middle.

For every pair, calculate:

```text
currentSum = skill[i] + skill[j]
```

If:

```text
currentSum != targetSum
```

then it is impossible to create valid teams.

Return:

```text
-1
```

---

## Step 3 — Calculate Chemistry

If the pair is valid:

```text
chemistry = skill[i] × skill[j]
```

Add it to:

```text
totalChemistry
```

Then move both pointers:

```text
i++
j--
```

Continue until all players are paired.

---

# 🔍 Dry Run

### Input

```text
skill = [3,2,5,1,3,4]
```

---

## Step 1 — Sort

```text
[3,2,5,1,3,4]
```

After sorting:

```text
[1,2,3,3,4,5]
```

Pointers:

```text
 i              j
 ↓              ↓
[1, 2, 3, 3, 4, 5]
```

Initially:

```text
totalChemistry = 0
```

Calculate target sum:

```text
targetSum = skill[i] + skill[j]
          = 1 + 5
          = 6
```

So every team must have:

```text
skill sum = 6
```

---

## Step 2 — First Team

```text
skill[i] = 1
skill[j] = 5
```

Current sum:

```text
currentSum = 1 + 5
           = 6
```

Check:

```text
6 == 6
```

Valid team ✅

Chemistry:

```text
1 × 5 = 5
```

Update:

```text
totalChemistry = 5
```

Move pointers:

```text
i++
j--
```

Now:

```text
i = 1
j = 4
```

---

## Step 3 — Second Team

```text
skill[i] = 2
skill[j] = 4
```

Current sum:

```text
2 + 4 = 6
```

Check:

```text
6 == 6
```

Valid team ✅

Chemistry:

```text
2 × 4 = 8
```

Update:

```text
totalChemistry = 5 + 8
               = 13
```

Move pointers:

```text
i++
j--
```

Now:

```text
i = 2
j = 3
```

---

## Step 4 — Third Team

```text
skill[i] = 3
skill[j] = 3
```

Current sum:

```text
3 + 3 = 6
```

Check:

```text
6 == 6
```

Valid team ✅

Chemistry:

```text
3 × 3 = 9
```

Update:

```text
totalChemistry = 13 + 9
               = 22
```

Move pointers:

```text
i++
j--
```

Now:

```text
i = 3
j = 2
```

Since:

```text
i < j
```

is false, the loop ends.

---

# 📊 Dry Run Table

| `i` | `j` | Pair | `currentSum` | `targetSum` | Chemistry | `totalChemistry` |
|---:|---:|---|---:|---:|---:|---:|
| 0 | 5 | `(1,5)` | 6 | 6 | 5 | 5 |
| 1 | 4 | `(2,4)` | 6 | 6 | 8 | 13 |
| 2 | 3 | `(3,3)` | 6 | 6 | 9 | 22 |

---

# ✅ Final Answer

```text
22
```

The teams are:

```text
(1,5) → chemistry = 5
(2,4) → chemistry = 8
(3,3) → chemistry = 9
```

Total:

```text
5 + 8 + 9 = 22
```

---

# ❌ When Do We Return -1?

Suppose:

```text
skill = [1,2,3,4]
```

After sorting:

```text
[1,2,3,4]
```

Target sum:

```text
1 + 4 = 5
```

First pair:

```text
1 + 4 = 5 ✅
```

Second pair:

```text
2 + 3 = 5 ✅
```

So this is valid.

But if we have:

```text
skill = [1,2,3,5]
```

Target sum:

```text
1 + 5 = 6
```

Next pair:

```text
2 + 3 = 5
```

Since:

```text
5 != 6
```

the teams cannot have equal skill.

Therefore:

```text
Answer = -1
```

---

# 🔑 Key Takeaway

The pattern is:

```text
Sort
  ↓
Smallest + Largest
  ↓
Set their sum as targetSum
  ↓
Check every pair
  ↓
If pair sum != targetSum → return -1
  ↓
Calculate skill[i] × skill[j]
  ↓
Move both pointers
```

### One-Line Memory Trick

> **Sort → pair smallest with largest → all pair sums must be equal → add their products.**

---

# ⏱️ Complexity

### Time Complexity

```text
O(n log n)
```

Sorting takes `O(n log n)` and the two-pointer traversal takes `O(n)`.

Overall:

```text
O(n log n)
```

### Space Complexity

```text
O(1)
```

Auxiliary space, excluding the space used internally by the sorting implementation.