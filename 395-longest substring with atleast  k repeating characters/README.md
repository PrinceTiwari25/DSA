# LeetCode 395 — Longest Substring with At Least K Repeating Characters

## Question

Given a string `s` and an integer `k`, return the length of the longest substring of `s` such that **every character appears at least `k` times**.

If no such substring exists, return `0`.

### Example

**Input:**

```text
s = "aaabb"
k = 3
```

**Output:**

```text
3
```

### Explanation

The substring:

```text
"aaa"
```

contains:

```text
a → 3 times
```

Since every character appears at least `3` times, it is valid.

Therefore:

```text
Answer = 3
```

### Another Example

**Input:**

```text
s = "ababbc"
k = 2
```

**Output:**

```text
5
```

The longest valid substring is:

```text
"ababb"
```

Frequencies:

```text
a → 2
b → 3
```

Both characters appear at least `2` times.

---

## LeetCode Link

https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/

## Approach — Sliding Window + Unique Character Count

This problem is more difficult than normal Sliding Window problems because we don't know how many **unique characters** the answer should contain.

For example, a valid substring might contain:

```text
1 unique character
```

or:

```text
2 unique characters
```

or:

```text
3 unique characters
```

and so on.

Since the string contains lowercase English letters, there can be at most **26 unique characters**.

Therefore, we try every possible number of unique characters from:

```text
1 → 26
```

For each value, we use a Sliding Window.

---

## Main Idea

Suppose:

```text
requiredUnique = 3
```

This means:

> Find the longest substring containing exactly 3 unique characters where each of those 3 characters appears at least `k` times.

We maintain two important variables:

```text
unique
```

Number of unique characters currently inside the window.

And:

```text
atLeastK
```

Number of unique characters whose frequency is at least `k`.

A window is valid when:

```text
unique == requiredUnique
```

and:

```text
atLeastK == requiredUnique
```

This means every unique character in the window appears at least `k` times.

---

## Variables

```text
requiredUnique → number of unique characters allowed in the window

freq           → frequency of each character

left           → left boundary of the window

right          → right boundary of the window

unique         → number of unique characters in the window

atLeastK       → number of characters whose frequency >= k

maxLength      → longest valid substring found
```

---

## Steps

### Step 1 — Try Every Possible Unique Count

```text
for requiredUnique = 1 to 26
```

We consider:

```text
1 unique character
2 unique characters
3 unique characters
...
26 unique characters
```

### Step 2 — Expand the Window

Move `right` through the string.

When a new character enters:

```text
freq[ch]++
```

If its previous frequency was `0`:

```text
unique++
```

If its frequency becomes exactly `k`:

```text
atLeastK++
```

### Step 3 — Shrink the Window

If:

```text
unique > requiredUnique
```

the window contains too many unique characters.

So we remove characters from the left.

```text
left++
```

While removing a character:

* If its frequency was `k`, decrement `atLeastK`.
* Decrease its frequency.
* If its frequency becomes `0`, decrement `unique`.

### Step 4 — Check Valid Window

A window is valid when:

```text
unique == requiredUnique
```

and:

```text
atLeastK == requiredUnique
```

Then every character in the window appears at least `k` times.

Update:

```text
maxLength
```



# Dry Run

## Input

```text
s = "aaabb"
k = 3
```

We need every character in the substring to appear at least `3` times.

---

## `requiredUnique = 1`

We are looking for a substring containing exactly **1 unique character**.

Start:

```text
unique = 0
atLeastK = 0
left = 0
right = 0
```

### Add First `a`

```text
Window = "a"

freq[a] = 1
unique = 1
atLeastK = 0
```

`a` has not appeared `3` times yet.

---

### Add Second `a`

```text
Window = "aa"

freq[a] = 2
unique = 1
atLeastK = 0
```

Still not valid.

---

### Add Third `a`

```text
Window = "aaa"

freq[a] = 3
unique = 1
atLeastK = 1
```

Now:

```text
unique == requiredUnique
1 == 1
```

and:

```text
atLeastK == requiredUnique
1 == 1
```

Therefore, the window is valid.

Length:

```text
right - left + 1
= 2 - 0 + 1
= 3
```

Update:

```text
maxLength = 3
```

---

### Add First `b`

```text
Window = "aaab"

freq[b] = 1

unique = 2
```

But:

```text
unique > requiredUnique
2 > 1
```

So we shrink the window.

Remove the first `a`:

```text
freq[a] = 2
left = 1
```

Now:

```text
unique = 2
```

Still too many unique characters.

Remove another `a`:

```text
freq[a] = 1
left = 2
```

Still:

```text
unique = 2
```

Remove another `a`:

```text
freq[a] = 0
left = 3
```

Now:

```text
unique = 1
```

Window:

```text
"b"
```

---

### Add Second `b`

```text
Window = "bb"

freq[b] = 2
unique = 1
atLeastK = 0
```

Not valid yet.

---

### Add Third `b`

```text
Window = "bbb"

freq[b] = 3
unique = 1
atLeastK = 1
```

Now:

```text
unique == requiredUnique
1 == 1
```

and:

```text
atLeastK == requiredUnique
1 == 1
```

Valid.

Length:

```text
3
```

So:

```text
maxLength = 3
```

---

# Why Do We Try `1` to `26` Unique Characters?

A lowercase English string can contain at most:

```text
26
```

different characters.

We don't know how many unique characters the optimal substring contains.

For example:

```text
"aaa"
```

has:

```text
1 unique character
```

while:

```text
"ababb"
```

has:

```text
2 unique characters
```

Therefore, we try every possibility:

```text
1, 2, 3, ..., 26
```

This converts the unknown condition into a fixed Sliding Window problem.

---

# Understanding `atLeastK`

This is the most important part of the solution.

Suppose:

```text
requiredUnique = 3
```

and the current window has:

```text
a → 4
b → 3
c → 1
```

Then:

```text
unique = 3
```

but:

```text
atLeastK = 2
```

because:

```text
a → 4 ≥ k
b → 3 ≥ k
c → 1 < k
```

Therefore, the window is **not valid**.

We require:

```text
unique == requiredUnique
```

and:

```text
atLeastK == requiredUnique
```

Only then do all characters satisfy the `k` requirement.

---

# Why Do We Check `freq[ch] == k`?

When adding a character:

```java
freq[ch]++;
```

we check:

```java
if (freq[ch] == k) {
    atLeastK++;
}
```

We only increase `atLeastK` when the frequency reaches `k` for the first time.

For example, if:

```text
k = 3
```

then:

```text
frequency: 1 → 2 → 3 → 4 → 5
                    ↑
              atLeastK++
```

Once the frequency is greater than `k`, it is already counted.

---

# Why Do We Check `freq[leftChar] == k` While Removing?

When shrinking the window:

```java
if (freq[leftChar] == k) {
    atLeastK--;
}
```

Suppose:

```text
k = 3
freq[a] = 3
```

If we remove one `a`:

```text
freq[a] = 2
```

Now `a` no longer satisfies:

```text
frequency >= k
```

Therefore:

```text
atLeastK--
```

If the frequency was `4`:

```text
4 → 3
```

the character still satisfies the condition, so `atLeastK` does not change.

---

# Window Visualization

Suppose:

```text
s = "aaabb"
k = 3
```

For:

```text
requiredUnique = 1
```

The window grows:

```text
[a]
[aa]
[aaa] → valid
[aaab] → too many unique characters
```

We shrink:

```text
[aaab]
 ↓
[aab]
 ↓
[ab]
 ↓
[b]
```

Then continue:

```text
[b]
[bb]
[bbb] → valid
```

The longest valid length is:

```text
3
```

---

# Why Is This Still Sliding Window?

The pattern is:

```text
Expand
   ↓
Add character
   ↓
Update frequency
   ↓
Too many unique characters?
   ↓
   Yes
   ↓
Shrink from left
   ↓
Check valid window
   ↓
Update answer
```

The difference from simpler Sliding Window problems is that we repeat this process for every possible number of unique characters.

```text
requiredUnique = 1
requiredUnique = 2
requiredUnique = 3
...
requiredUnique = 26
```

---

# Complexity

## Time Complexity

```text
O(26 × n)
```

For every possible number of unique characters from `1` to `26`, we traverse the string using the Sliding Window.

Therefore:

```text
26 × O(n) = O(26n)
```

Since `26` is a constant:

```text
O(26n) = O(n)
```

So the effective time complexity is:

```text
O(n)
```

## Space Complexity

```text
O(1)
```

We use:

```java
int[] freq = new int[256];
```

The array has a fixed size.

The other variables also require constant space.

Therefore:

```text
Space = O(1)
```

---

# Key Idea

The most important condition is:

```text
unique == requiredUnique
        &&
atLeastK == requiredUnique
```

Meaning:

```text
Number of unique characters
            =
Number of characters appearing at least k times
```

Therefore, **every character in the window appears at least `k` times**.

---

# Final Complexity

```text
Time  : O(26n) → O(n)
Space : O(1)
```

**Pattern Used:** Sliding Window + Frequency Array + Fixed Number of Unique Characters
