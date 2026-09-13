# LC 2401 — Longest Nice Subarray

## 🔗 Question Link

https://leetcode.com/problems/longest-nice-subarray/

---

# 📝 Question

You are given an array of positive integers `nums`.

A subarray is called **nice** if the bitwise AND of every pair of different elements in the subarray is equal to `0`.

In simple words:

> No two different numbers in the subarray can have a common set bit.

Return the length of the **longest nice subarray**.

### Example

```text
Input:
nums = [1,3,8,48,10]

Output:
3
```

The longest nice subarray is:

```text
[1,8,48]
```

Because:

```text
1  = 000001
8  = 001000
48 = 110000
```

For every pair:

```text
1 & 8  = 0
1 & 48 = 0
8 & 48 = 0
```

Therefore, the length is:

```text
3
```

---

# 🚀 Optimal Approach — Sliding Window + Bit Manipulation

We use a **Sliding Window** with two pointers:

```text
i → left pointer
j → right pointer
```

We also maintain:

```text
usedbit
```

`usedbit` stores all the set bits currently present in the window.

---

## Step 1 — Expand the Window

The right pointer `j` moves through the array:

```text
for(int j = 0; j < nums.length; j++)
```

For every new number:

```text
nums[j]
```

we check whether it has any bit in common with the current window.

The check is:

```text
usedbit & nums[j]
```

---

## Step 2 — Check for Bit Conflict

### No Conflict

If:

```text
(usedbit & nums[j]) == 0
```

there is no common set bit.

So we can add the current number to the window:

```text
usedbit |= nums[j]
```

---

### Conflict Exists

If:

```text
(usedbit & nums[j]) != 0
```

the current number shares at least one set bit with a number already inside the window.

Therefore, the current window is not nice.

We remove numbers from the left:

```text
usedbit ^= nums[i]
i++
```

We continue doing this until the conflict disappears.

---

# 💡 Why `&` Detects a Conflict

Suppose:

```text
usedbit = 0011
nums[j] = 0101
```

Perform AND:

```text
  0011
& 0101
------
  0001
```

Result is not `0`.

Therefore, they share a set bit.

So we have a conflict.

If:

```text
usedbit & nums[j] = 0
```

then there is no common set bit.

---

# 💡 Why `|` Adds the Bits

After there is no conflict:

```text
usedbit |= nums[j]
```

This adds the set bits of the current number to `usedbit`.

For example:

```text
usedbit = 0010
nums[j] = 1000
```

Then:

```text
0010
  |
1000
----
1010
```

Now `usedbit` represents all the bits currently used by the window.

---

# 💡 Why XOR Removes the Left Element

When a number is removed:

```text
usedbit ^= nums[i]
```

Because the current window is nice, no two numbers share a set bit.

Therefore, the bits belonging to `nums[i]` are not being used by another number in the window.

XOR turns those bits back to `0`.

---

# 🔍 Dry Run

### Input

```text
nums = [1,3,8,48,10]
```

Initially:

```text
i = 0
usedbit = 0
maxlength = 0
```

---

## Step 1 — `j = 0`

```text
nums[j] = 1
```

Check:

```text
usedbit & nums[j]
= 0 & 1
= 0
```

No conflict.

Add the number:

```text
usedbit |= 1
```

```text
usedbit = 1
```

Window:

```text
[1]
```

Length:

```text
j - i + 1
= 0 - 0 + 1
= 1
```

So:

```text
maxlength = 1
```

---

## Step 2 — `j = 1`

```text
nums[j] = 3
```

Binary:

```text
1 = 001
3 = 011
```

Check:

```text
1 & 3 = 1
```

Conflict exists.

So remove `nums[i]`.

```text
nums[i] = 1
```

Remove its bits:

```text
usedbit ^= 1
```

```text
1 ^ 1 = 0
```

Move left:

```text
i++
```

Now:

```text
i = 1
```

Check again:

```text
0 & 3 = 0
```

No conflict.

Add `3`:

```text
usedbit |= 3
```

```text
usedbit = 3
```

Window:

```text
[3]
```

Length:

```text
1
```

`maxlength` remains:

```text
1
```

---

## Step 3 — `j = 2`

```text
nums[j] = 8
```

Binary:

```text
3 = 0011
8 = 1000
```

Check:

```text
3 & 8 = 0
```

No conflict.

Add `8`:

```text
usedbit = 3 | 8
         = 11
```

Window:

```text
[3,8]
```

Length:

```text
2
```

Update:

```text
maxlength = 2
```

---

## Step 4 — `j = 3`

```text
nums[j] = 48
```

Binary:

```text
8  = 001000
48 = 110000
```

Current used bits:

```text
3 | 8
```

There is no common bit between the current window and `48`.

Therefore:

```text
usedbit |= 48
```

Window:

```text
[3,8,48]
```

Length:

```text
3
```

Update:

```text
maxlength = 3
```

---

## Step 5 — `j = 4`

```text
nums[j] = 10
```

Binary:

```text
10 = 001010
```

There is a conflict with the current window.

So enter the `while` loop.

### Remove `nums[i]`

Currently:

```text
i = 1
nums[i] = 3
```

Remove:

```text
usedbit ^= 3
```

Then:

```text
i++
```

Now:

```text
i = 2
```

There is still a conflict, so remove:

```text
nums[2] = 8
```

Again:

```text
usedbit ^= 8
i++
```

Now:

```text
i = 3
```

The conflict is removed.

Add `10`:

```text
usedbit |= 10
```

Current window:

```text
[48,10]
```

Length:

```text
2
```

`maxlength` remains:

```text
3
```

---

# 📊 Dry Run Table

| `j` | `nums[j]` | Window | Action | Length | `maxlength` |
|---:|---:|---|---|---:|---:|
| 0 | 1 | `[1]` | Add 1 | 1 | 1 |
| 1 | 3 | `[3]` | Remove 1, add 3 | 1 | 1 |
| 2 | 8 | `[3,8]` | Add 8 | 2 | 2 |
| 3 | 48 | `[3,8,48]` | Add 48 | 3 | **3** |
| 4 | 10 | `[48,10]` | Remove 3, 8, add 10 | 2 | **3** |

---

# ✅ Final Answer

```text
3
```

The longest nice subarray is:

```text
[3,8,48]
```

Its length is:

```text
3
```

---

# 🔑 Key Takeaway

The complete pattern is:

```text
Expand right
     ↓
Check bit conflict
     ↓
Conflict?
 ↙          ↘
YES         NO
 ↓           ↓
Remove      Add
left        current
 ↓           ↓
i++      usedbit |= nums[j]
     \       /
      Update
       max
```

### Important Conditions

```text
(usedbit & nums[j]) != 0
```

means:

```text
Bit conflict → remove from left
```

And:

```text
usedbit |= nums[j]
```

means:

```text
Add current number's bits
```

### One-Line Memory Trick

> **Sliding window where no two numbers are allowed to share a set bit.**

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Although there is a `for` loop containing a `while` loop, the left pointer `i` only moves forward.

Across the entire algorithm:

```text
j → moves at most n times
i → moves at most n times
```

Therefore:

```text
O(n + n)
= O(n)
```

### Space Complexity

```text
O(1)
```

Only a few variables are used.

---

# 📌 Final Pattern

```text
LC 2401
   ↓
Sliding Window
   +
Bit Manipulation
   +
Two Pointers
   ↓
O(n) Time
O(1) Space
```