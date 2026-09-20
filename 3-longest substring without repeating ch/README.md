# LeetCode 3 — Longest Substring Without Repeating Characters

## Question

Given a string `s`, find the length of the **longest substring without repeating characters**.

A substring must contain characters that are all **unique**.

### Example

**Input:**

```text
s = "abcabcbb"
```

**Output:**

```text
3
```

### Explanation

The longest substring without repeating characters is:

```text
"abc"
```

Its length is:

```text
3
```

Other possible substrings include:

```text
"bca" → 3
"cab" → 3
```

Therefore, the answer is `3`.

## LeetCode Link

https://leetcode.com/problems/longest-substring-without-repeating-characters/

## Approach — Sliding Window + Frequency Array

We use the **Sliding Window** technique.

The idea is to maintain a window containing characters with **no duplicates**.

We use:

```text
freq[256]
```

to store the frequency of each character.

### Variables

```text
left       → left boundary of the window
right      → right boundary of the window
freq       → frequency of each character
maxLength  → longest valid window found
```

### Steps

1. Create a frequency array of size `256`.
2. Start both pointers at `0`.
3. Move `right` through the string.
4. Add the current character to the frequency array.
5. If the frequency becomes greater than `1`, a duplicate exists.
6. Move `left` forward and decrease the frequency of characters until the duplicate is removed.
7. Calculate the current window length:

```text
right - left + 1
```

8. Update `maxLength`.
9. Continue until the entire string is processed.



## Dry Run

### Input

```text
s = "abcabcbb"
```

We maintain a sliding window containing only unique characters.

---

### Step 1

```text
right = 0
ch = 'a'
```

Add `a`:

```text
freq[a] = 1
```

Window:

```text
"a"
```

Length:

```text
0 - 0 + 1 = 1
```

```text
maxLength = 1
```

---

### Step 2

```text
right = 1
ch = 'b'
```

Add `b`:

```text
freq[b] = 1
```

Window:

```text
"ab"
```

Length:

```text
1 - 0 + 1 = 2
```

```text
maxLength = 2
```

---

### Step 3

```text
right = 2
ch = 'c'
```

Add `c`:

```text
freq[c] = 1
```

Window:

```text
"abc"
```

Length:

```text
2 - 0 + 1 = 3
```

```text
maxLength = 3
```

---

### Step 4

```text
right = 3
ch = 'a'
```

Add `a`:

```text
freq[a] = 2
```

There is a duplicate `a`.

Current window:

```text
"abca"
```

We move `left`.

Remove `a` at index `0`:

```text
freq[a] = 1
left = 1
```

Now the window is:

```text
"bca"
```

No duplicates.

Length:

```text
3 - 1 + 1 = 3
```

```text
maxLength = 3
```

---

### Step 5

```text
right = 4
ch = 'b'
```

`b` becomes duplicated.

```text
freq[b] = 2
```

Remove the character at `left`.

Remove `b`:

```text
freq[b] = 1
left = 2
```

Window becomes:

```text
"cab"
```

Length:

```text
4 - 2 + 1 = 3
```

```text
maxLength = 3
```

---

### Step 6

```text
right = 5
ch = 'c'
```

`c` becomes duplicated.

Remove the old `c`:

```text
freq[c] = 1
left = 3
```

Window:

```text
"abc"
```

Length:

```text
5 - 3 + 1 = 3
```

```text
maxLength = 3
```

---

### Step 7

```text
right = 6
ch = 'b'
```

`b` becomes duplicated.

Remove characters from the left until the duplicate is removed.

```text
left = 4
```

Window:

```text
"cb"
```

Length:

```text
6 - 4 + 1 = 3
```

```text
maxLength = 3
```

---

### Step 8

```text
right = 7
ch = 'b'
```

Again, `b` becomes duplicated.

Move `left`:

```text
left = 5
```

Remove `c`:

```text
freq[c] = 0
```

Still duplicate `b`, so move again:

```text
left = 6
```

Remove the previous `b`:

```text
freq[b] = 1
```

Current window:

```text
"b"
```

Length:

```text
7 - 6 + 1 = 2
```

`maxLength` remains:

```text
3
```

## Final Answer

```text
3
```

The longest substring without repeating characters has length `3`.

Examples:

```text
"abc"
"bca"
"cab"
```

## How the Sliding Window Works

Think of the window as:

```text
left                    right
 ↓                        ↓
[a   b   c   a]
```

When a duplicate appears:

```text
[a   b   c   a]
 ↑           ↑
old a      new a
```

We move `left` until the duplicate disappears:

```text
      left      right
       ↓          ↓
[a   b   c   a]
```

After removing the old `a`:

```text
        left    right
         ↓        ↓
[a   b   c   a]
    └────────────┘
       "bca"
```

The window is valid again.

## Why Do We Use `while`?

We use:

```java
while (freq[ch] > 1)
```

instead of `if` because sometimes we need to remove **multiple characters** from the left before the duplicate is removed.

The goal is always:

```text
freq[ch] <= 1
```

so that the current window contains unique characters.

## Why `right - left + 1`?

The current window goes from index `left` to index `right`.

Therefore, its length is:

```text
right - left + 1
```

For example:

```text
left = 2
right = 5
```

Then:

```text
5 - 2 + 1 = 4
```

So the window contains `4` characters.

## Why Use `int[256]`?

We use:

```java
int[] freq = new int[256];
```

Each index represents a character's ASCII value.

For example:

```text
'a' → 97
'b' → 98
'c' → 99
```

So:

```java
freq[ch]++;
```

increases the frequency of that character.

And:

```java
freq[s.charAt(left)]--;
```

removes its occurrence from the current window.

## Complexity

### Time Complexity

```text
O(n)
```

The `right` pointer moves from left to right once.

Although there is a `while` loop, the `left` pointer also only moves forward and never goes backward.

Therefore, each character is processed a constant number of times.

```text
Time = O(n)
```

### Space Complexity

```text
O(1)
```

The frequency array always has a fixed size of `256`.

Therefore:

```text
Space = O(1)
```

## Key Idea

```text
Expand Window
     ↓
Add Character
     ↓
Duplicate?
   ↙       ↘
 No        Yes
 ↓          ↓
Continue   Move left
             ↓
        Remove duplicate
             ↓
        Valid Window
             ↓
     Update maxLength
```

The main condition is:

```text
freq[ch] > 1
```

If true, shrink the window.

If false, the window contains unique characters.

## Final Complexity

```text
Time  : O(n)
Space : O(1)
```

**Pattern Used:** Sliding Window + Frequency Array
