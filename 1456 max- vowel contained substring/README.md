# 1456. Maximum Number of Vowels in a Substring of Given Length

## Question

Given a string `s` and an integer `k`, return the maximum number of vowels in any substring of length `k`.

Vowels are: `a, e, i, o, u`.

## LeetCode Link

https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/

## Approach

### Fixed Size Sliding Window

We use the **Sliding Window** technique because every substring has the same length `k`.

1. First, calculate the number of vowels in the first `k` characters.
2. Store this count in `window`.
3. Store the first window's vowel count in `res`.
4. Start sliding the window:
   - Remove the vowel contribution of the character at index `i`.
   - Add the vowel contribution of the character at index `j`.
   - Update `res` with the maximum vowel count.
5. Move both pointers `i` and `j` forward.
6. Return `res`.

The `isvowel()` function returns:

- `1` → if the character is a vowel
- `0` → if the character is not a vowel

So when the window moves:

```text
window -= isvowel(s.charAt(i));
window += isvowel(s.charAt(j));


## Dry Run

### Input

```text
s = "abciiidef"
k = 3
```

### First Window

```text
Window = "abc"

a → vowel     → +1
b → consonant → +0
c → consonant → +0

window = 1
res = 1
```

### Sliding the Window

```text
"abc" → "bci"

Remove: a → vowel → -1
Add:    i → vowel → +1

window = 1 - 1 + 1 = 1
res = max(1, 1) = 1
```

```text
"bci" → "cii"

Remove: b → consonant → -0
Add:    i → vowel → +1

window = 1 - 0 + 1 = 2
res = max(1, 2) = 2
```

```text
"cii" → "iii"

Remove: c → consonant → -0
Add:    i → vowel → +1

window = 2 - 0 + 1 = 3
res = max(2, 3) = 3
```

```text
"iii" → "iid"

Remove: i → vowel → -1
Add:    d → consonant → +0

window = 3 - 1 + 0 = 2
res = max(3, 2) = 3
```

```text
"iid" → "ide"

Remove: i → vowel → -1
Add:    e → vowel → +1

window = 2 - 1 + 1 = 2
res = max(3, 2) = 3
```

```text
"ide" → "def"

Remove: i → vowel → -1
Add:    f → consonant → +0

window = 2 - 1 + 0 = 1
res = max(3, 1) = 3
```

### Final Answer

```text
3
```

The substring `"iii"` contains the maximum number of vowels.

## Complexity

### Time Complexity

```text
O(n)
```

Each character is processed at most twice — once when it enters the window and once when it leaves.

### Space Complexity

```text
O(1)
```

Only a constant number of variables are used.

## Key Idea

```text
Remove outgoing character
          +
Add incoming character
          ↓
Update vowel count
          ↓
Update maximum
          ↓
Slide the window
```

### Final Complexity

```text
Time  : O(n)
Space : O(1)
```
