# LeetCode 3969 — Valid Subarrays With Matching Sum Digits I

## 🔗 Problem Link

[LeetCode 3969 — Valid Subarrays With Matching Sum Digits I](https://leetcode.com/problems/valid-subarrays-with-matching-sum-digits-i/)

## 📝 Problem Statement

You are given:
- An integer array `nums`.
- An integer digit `x`.

A non-empty continuous subarray is valid if the sum of its elements satisfies both conditions:

1. The first digit of the sum is equal to `x`.
2. The last digit of the sum is equal to `x`.

Return the total number of valid subarrays.

### Example

**Input**
```text
nums = [1, 100, 1]
x = 1
```

**Output**
```text
4
```

**Explanation**

The valid subarrays are:

| Subarray | Sum | First digit | Last digit |
|---|---:|---:|---:|
| `[1]` | 1 | 1 | 1 |
| `[1, 100]` | 101 | 1 | 1 |
| `[100, 1]` | 101 | 1 | 1 |
| `[1]` | 1 | 1 | 1 |

Total valid subarrays = `4`.

---

## 🚀 Approach: Prefix Sum + Brute Force

We use two concepts:

- Prefix Sum
- Nested Loops (Brute Force)

### Key Idea

Instead of calculating the sum of every subarray again and again, we first build a prefix sum array.

The formula is:

\[
\text{subarraySum}(l,r)=prefix[r+1]-prefix[l]
\]

This allows us to calculate any subarray sum in **O(1)** time.

Then we generate all possible subarrays using two loops and check whether the first and last digits of their sum are equal to `x`.

### Conditions

**1. Find the first digit**

```java
int firstDigit = Long.toString(sum).charAt(0) - '0';
```

**2. Find the last digit**

```java
int lastDigit = (int) (sum % 10);
```

**3. Check both conditions**

```java
if (firstDigit == x && lastDigit == x) {
    count++;
}
```


---

## 🔍 Code Explanation

### 1. Calculate Array Length

```java
int n = nums.length;
```

Stores the total number of elements in the array.

### 2. Create Prefix Sum Array

```java
long[] prefix = new long[n + 1];
```

We create an array of size `n + 1`.

Why `n + 1`?

Because `prefix[0] = 0` represents the sum before the array starts.

For example:

```text
nums = [1, 100, 1]

prefix = [0, 1, 101, 102]
```

### 3. Build Prefix Sum

```java
for (int i = 0; i < n; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
```

Each position stores the sum of all elements from index `0` to `i`.

Example:

```text
nums:    [1, 100, 1]

Index:    0    1    2
Value:    1   100   1

Prefix:   0    1   101  102
```

### 4. Initialize Count

```java
int count = 0;
```

Stores the total number of valid subarrays.

### 5. Generate All Subarrays

```java
for (int l = 0; l < n; l++) {
    for (int r = l; r < n; r++) {
```

- `l` represents the starting index.
- `r` represents the ending index.

The inner loop starts from `l` because a subarray cannot end before it starts.

For an array of three elements, the generated subarrays are:

```text
[1]
[1, 100]
[1, 100, 1]

[100]
[100, 1]

[1]
```

Total subarrays:

\[
\frac{n(n+1)}{2}
\]

### 6. Calculate Subarray Sum

```java
long sum = prefix[r + 1] - prefix[l];
```

Formula:

\[
\boxed{sum=prefix[r+1]-prefix[l]}
\]

Example:

```text
prefix = [0, 1, 101, 102]
```

For subarray `[1, 100]`:

```text
l = 0
r = 1

sum = prefix[2] - prefix[0]
    = 101 - 0
    = 101
```

No additional loop is needed to calculate the sum.

### 7. Find the First Digit

```java
int firstDigit = Long.toString(sum).charAt(0) - '0';
```

Let's understand this step by step.

Suppose:

```text
sum = 101
```

**Step 1: Convert long to String**

```java
Long.toString(sum)
```

Result:

```text
"101"
```

**Step 2: Get the first character**

```java
.charAt(0)
```

Result:

```text
'1'
```

**Step 3: Convert character into integer**

```java
- '0'
```

Result:

```text
1
```

Therefore:

```text
firstDigit = 1
```

### 8. Find the Last Digit

```java
int lastDigit = (int) (sum % 10);
```

The modulo operator `%` gives the remainder after division.

Example:

```text
sum = 101

101 % 10 = 1
```

Therefore:

```text
lastDigit = 1
```

### 9. Check Both Conditions

```java
if (firstDigit == x && lastDigit == x) {
    count++;
}
```

Both conditions must be true.

For example:

```text
sum = 101
x = 1

firstDigit = 1
lastDigit = 1
```

Both match, so:

```text
count++;
```

### 10. Return the Answer

```java
return count;
```

Returns the total number of valid subarrays.

---

## 🧪 Dry Run

Consider:

```text
nums = [1, 100, 1]
x = 1
```

### Step 1: Build Prefix Sum

```text
nums   = [1, 100, 1]

prefix = [0, 1, 101, 102]
```

### Step 2: Generate Subarrays

| `l` | `r` | Subarray | Sum | First digit | Last digit | Valid? |
|---:|---:|---|---:|---:|---:|---|
| 0 | 0 | `[1]` | 1 | 1 | 1 | Yes |
| 0 | 1 | `[1,100]` | 101 | 1 | 1 | Yes |
| 0 | 2 | `[1,100,1]` | 102 | 1 | 2 | No |
| 1 | 1 | `[100]` | 100 | 1 | 0 | No |
| 1 | 2 | `[100,1]` | 101 | 1 | 1 | Yes |
| 2 | 2 | `[1]` | 1 | 1 | 1 | Yes |

### Step 3: Count Valid Subarrays

```text
[1]       → Valid
[1,100]   → Valid
[1,100,1] → Invalid
[100]     → Invalid
[100,1]   → Valid
[1]       → Valid
```

Total:

```text
count = 4
```

### Final Output

```text
4
```

---

## 📊 Prefix Sum Visual Understanding

Original array:

```text
Index:  0    1    2
Nums:   1   100   1
```

Prefix sum:

```text
Index:   0    1    2    3
Prefix:  0    1   101  102
```

To find the sum of any subarray:

```text
Subarray [0, 1]

prefix[2] - prefix[0]
= 101 - 0
= 101
```

```text
Subarray [1, 2]

prefix[3] - prefix[1]
= 102 - 1
= 101
```

Both sums start and end with digit `1`.

---

## ❓ Why Use Prefix Sum?

Without prefix sum, we would need another loop to calculate every subarray sum.

That would make the time complexity:

\[
O(n^3)
\]

With prefix sum, each subarray sum is calculated in constant time:

\[
O(1)
\]

We still generate all subarrays, so the total time complexity becomes:

\[
O(n^2)
\]

## ❓ Why Use `long`?

```java
long[] prefix = new long[n + 1];
```

According to the constraints, each array element can be as large as \(10^9\).

Adding many elements can exceed the range of Java `int`.

Therefore, we use `long` for prefix sums and subarray sums to avoid integer overflow.

## ❓ Why Do We Use `r + 1`?

```java
prefix[r + 1] - prefix[l]
```

Because `prefix[i]` stores the sum of the first `i` elements.

For example:

```text
nums = [1, 100, 1]

prefix = [0, 1, 101, 102]
```

For the subarray from index `0` to index `1`:

```text
prefix[2] - prefix[0]
= 101 - 0
= 101
```

---

## ⏱️ Complexity Analysis

Let `n` be the length of `nums`.

### Time Complexity

**1. Building prefix sum**

```text
O(n)
```

**2. Generating all subarrays**

There are:

\[
\frac{n(n+1)}{2}
\]

possible subarrays.

Therefore:

```text
O(n²)
```

**3. Checking the digits**

Getting the first and last digits involves a string conversion. The sum has at most a constant number of digits under the given constraints, so this takes \(O(1)\) per subarray.

Overall:

\[
\boxed{O(n^2)}
\]

### Space Complexity

The prefix array stores `n + 1` elements.

\[
\boxed{O(n)}
\]

---

## 🧠 Key Takeaways

- Use prefix sum to calculate subarray sums quickly.
- Use two nested loops to generate every possible subarray.
- `l` represents the starting index.
- `r` represents the ending index.
- `prefix[r + 1] - prefix[l]` calculates the subarray sum.
- `Long.toString(sum).charAt(0) - '0'` extracts the first digit.
- `sum % 10` extracts the last digit.
- Both digits must equal `x` to count the subarray.

## 📌 Final Complexity

```text
Time Complexity:  O(n²)
Space Complexity: O(n)
```

### One-Line Idea

**Build prefix sums, generate every subarray, calculate its sum in O(1), and count it if its first and last digits are both equal to x.**