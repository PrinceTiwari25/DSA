# LeetCode 3747 — Count Distinct Integers After Removing Zeros

## 🔗 Problem Link

[LeetCode 3747 — Count Distinct Integers After Removing Zeros](https://leetcode.com/problems/count-distinct-integers-after-removing-zeros/)

## 📝 Problem Statement

Given a positive integer `n`, count the positive integers less than or equal to `n` that do not contain the digit `0`.

Return the count of such integers.

**Note:** This solution counts numbers containing no zero digits from `1` through `n`.

---

## 💡 Example

**Input**
```text
n = 20
```

**Output**
```text
18
```

**Explanation**

The positive integers from `1` to `20` that do not contain zero are:

```text
1, 2, 3, 4, 5, 6, 7, 8, 9,
11, 12, 13, 14, 15, 16, 17, 18, 19
```

Total:

```text
9 + 9 = 18
```

---

## 🚀 Approach: Digit DP Concept + Mathematics

We use:

- String conversion
- Digit-by-digit traversal
- Powers of `9`
- Mathematical counting

### Key Idea

We need to count positive integers that do not contain the digit `0`.

For a number with `len` digits:

- The first digit has 9 choices: `1` to `9`.
- Every remaining digit also has 9 choices: `1` to `9`.

Therefore, the number of valid integers with exactly `len` digits is:

\[
9^{len}
\]

For example:

| Number of digits | Valid numbers |
|---|---:|
| 1 | \(9\) |
| 2 | \(9^2 = 81\) |
| 3 | \(9^3 = 729\) |
| 4 | \(9^4 = 6561\) |

However, we only need to count numbers up to `n`, not all numbers of the same length.

So we first count all valid numbers with fewer digits, then count valid numbers of the same length that are less than or equal to `n`.

---



---

## 🔍 Code Explanation

### 1. Convert Number to String

```java
String s = String.valueOf(n);
int len = s.length();
```

We convert the number into a string so that we can process each digit separately.

Example:

```text
n = 345

s = "345"
len = 3
```

### 2. Initialize Variables

```java
long ans = 0;
long pow = 1;
```

- `ans`: stores the count of valid numbers.
- `pow`: stores the current power of 9.

### 3. Count Numbers With Fewer Digits

```java
for (int i = 1; i < len; i++) {
    pow *= 9;
    ans += pow;
}
```

This loop counts all valid numbers having fewer digits than `n`.

Suppose:

```text
n = 345
len = 3
```

We count:

**One-digit numbers**

\[
9^1=9
\]

**Two-digit numbers**

\[
9^2=81
\]

Total:

\[
ans=9+81=90
\]

These are all valid positive integers with fewer than three digits.

### 4. Calculate the Initial Power

```java
pow = 1;

for (int i = 1; i < len; i++) {
    pow *= 9;
}
```

For `len = 3`:

```text
pow = 9 × 9
    = 81
```

This represents the number of possible combinations for the remaining two digits when choosing the first digit.

In general:

\[
pow=9^{len-1}
\]

### 5. Traverse Every Digit

```java
for (int i = 0; i < len; i++) {
    int digit = s.charAt(i) - '0';
```

We process the digits from left to right.

For:

```text
n = 345
```

The digits are:

```text
3 → 4 → 5
```

The expression:

```java
s.charAt(i) - '0'
```

converts a character digit into an integer.

### 6. Check for Zero

```java
if (digit == 0) {
    return ans;
}
```

If the current digit is zero, no valid number can continue matching the prefix of `n` without introducing a zero at this position.

Therefore, we return the count accumulated so far.

### 7. Count Smaller Choices

```java
ans += (digit - 1) * pow;
```

This is the most important line.

At each digit position, we count the possible non-zero digits smaller than the current digit.

For example:

```text
Current digit = 4
```

Possible smaller non-zero digits:

```text
1, 2, 3
```

Number of choices:

```text
4 - 1 = 3
```

If two positions remain after choosing this digit, each choice can be followed by:

\[
9^2=81
\]

possible combinations.

Therefore:

```text
3 × 81 = 243
```

We add these possibilities to `ans`.

### 8. Reduce the Power

```java
pow /= 9;
```

After processing one digit, one fewer digit remains.

Therefore, we divide the power by `9`.

Example:

```text
81 → 9 → 1
```

### 9. Include the Number Itself

```java
return ans + 1;
```

If every digit of `n` is non-zero, then `n` itself is also valid.

So we add `1` to the answer.

---

# 🧪 Dry Run

Consider:

```text
n = 345
```

Initially:

```text
s = "345"
len = 3
ans = 0
```

### Step 1: Count Smaller Digit Lengths

For one-digit numbers:

```text
9^1 = 9
```

For two-digit numbers:

```text
9^2 = 81
```

Therefore:

```text
ans = 9 + 81
    = 90
```

### Step 2: Initialize Power

```text
pow = 9^(3 - 1)
    = 81
```

### Step 3: Process Digit `3`

Current digit:

```text
digit = 3
pow = 81
```

Smaller non-zero choices:

```text
1, 2
```

Count:

```text
(3 - 1) × 81
= 2 × 81
= 162
```

Update:

```text
ans = 90 + 162
    = 252
```

Reduce power:

```text
pow = 81 / 9
    = 9
```

### Step 4: Process Digit `4`

Current digit:

```text
digit = 4
pow = 9
```

Smaller non-zero choices:

```text
1, 2, 3
```

Count:

```text
(4 - 1) × 9
= 3 × 9
= 27
```

Update:

```text
ans = 252 + 27
    = 279
```

Reduce power:

```text
pow = 9 / 9
    = 1
```

### Step 5: Process Digit `5`

Current digit:

```text
digit = 5
pow = 1
```

Smaller non-zero choices:

```text
1, 2, 3, 4
```

Count:

```text
(5 - 1) × 1
= 4
```

Update:

```text
ans = 279 + 4
    = 283
```

Reduce power:

```text
pow = 1 / 9
    = 0
```

### Step 6: Add `n` Itself

All digits of `345` are non-zero.

Therefore:

```text
ans + 1 = 283 + 1
        = 284
```

### Final Output

```text
284
```

---

## 📊 Dry Run Table

For `n = 345`:

| Step | Digit | Power | Smaller choices | Added count | Total |
|---|---:|---:|---:|---:|---:|
| Fewer digits | — | — | — | 90 | 90 |
| First digit | 3 | 81 | 2 | 162 | 252 |
| Second digit | 4 | 9 | 3 | 27 | 279 |
| Third digit | 5 | 1 | 4 | 4 | 283 |
| Include `345` | — | — | — | 1 | 284 |

---

## ⚠️ Important Case: When a Digit Is Zero

Consider:

```text
n = 120
```

First, count all valid numbers with fewer digits:

```text
9 + 81 = 90
```

Initial power:

```text
pow = 9
```

Process the first digit, `1`:

```text
ans += (1 - 1) × 9
ans = 90
```

Process the second digit, `2`:

```text
ans += (2 - 1) × 1
ans = 91
```

Now the third digit is `0`.

The code immediately returns:

```text
91
```

We cannot include `120` because it contains zero.

The valid numbers are:

```text
1 to 9
10 to 19 → only 11 to 19
20 to 99 → excluding numbers containing zero
100 to 120 → valid numbers counted by the prefix calculation
```

The algorithm counts all valid positive integers up to `120` without explicitly generating them.

---

## 🧠 Why Does `(digit - 1) * pow` Work?

Suppose:

```text
n = 345
```

At the first digit:

```text
Current digit = 3
```

We can choose smaller non-zero digits:

```text
1, 2
```

That gives two choices.

For each choice, the remaining two digits can have nine choices each:

```text
9 × 9 = 81
```

Therefore:

```text
2 × 81 = 162
```

General formula:

\[
\boxed{(\text{digit}-1)\times 9^{\text{remaining digits}}}
\]

This counts all valid numbers that become smaller than `n` at the current digit.

---

## ⏱️ Complexity Analysis

Let `d` be the number of digits in `n`.

### Time Complexity

- Convert `n` to a string: `O(d)`.
- Count smaller digit lengths: `O(d)`.
- Calculate initial power: `O(d)`.
- Traverse each digit: `O(d)`.

Overall:

\[
\boxed{O(d)}
\]

### Space Complexity

The string stores `d` digits.

\[
\boxed{O(d)}
\]

Excluding the string conversion, the algorithm uses:

\[
O(1)
\]

extra variables.

---

## 🧠 Key Takeaways

- Every digit must be between `1` and `9`.
- A number with `d` digits has \(9^d\) possible zero-free combinations.
- Count all valid numbers with fewer digits first.
- For each digit, count smaller non-zero choices.
- Multiply the choices by the possible combinations of remaining digits.
- If a zero appears, stop because the matching prefix cannot continue.
- If every digit is non-zero, include `n` itself.

## 📌 Final Complexity

```text
Time Complexity:  O(d)
Space Complexity: O(d)
```

### One-Line Idea

**Count all zero-free numbers with fewer digits, then process each digit of `n` to count smaller non-zero choices multiplied by the possibilities for the remaining digits.**