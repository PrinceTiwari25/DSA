# LeetCode 2270 — Number of Ways to Split Array

## 🔗 Problem Link

[LeetCode 2270 — Number of Ways to Split Array](https://leetcode.com/problems/number-of-ways-to-split-array/)

## 📝 Problem Statement

You are given a **0-indexed integer array** `nums` of length `n`.

We can split the array at an index `i` where:

- Left array contains elements from index `0` to `i`.
- Right array contains elements from index `i + 1` to `n - 1`.

A split is valid if:

\[
\text{leftSum} \geq \text{rightSum}
\]

Return the total number of valid splits.

**Important:** Both left and right arrays must be non-empty.

---

## 💡 Example

**Input**
```text
nums = [10, 4, -8, 7]
```

**Output**
```text
2
```

**Explanation**

There are three possible splits:

| Split index | Left array | Right array | Left sum | Right sum | Valid? |
|---:|---|---|---:|---:|---|
| 0 | `[10]` | `[4,-8,7]` | 10 | 3 | Yes |
| 1 | `[10,4]` | `[-8,7]` | 14 | -1 | Yes |
| 2 | `[10,4,-8]` | `[7]` | 6 | 7 | No |

Therefore:

```text
Total valid splits = 2
```

---

## 🚀 Approach: Prefix Sum + Total Sum

We use:

- Total sum
- Running left sum
- Right sum calculation

### Key Idea

Instead of calculating the left and right sums separately for every split, we calculate the total sum once.

The formula is:

\[
\text{rightSum} = \text{totalSum} - \text{leftSum}
\]

Then check:

\[
\text{leftSum} \geq \text{rightSum}
\]

If the condition is true, increment the answer.



---

## 🔍 Code Explanation

### 1. Calculate Total Sum

```java
long sum = 0;

for(int num : nums){
    sum += num;
}
```

We calculate the sum of all array elements.

Example:

```text
nums = [10, 4, -8, 7]

sum = 10 + 4 - 8 + 7
    = 13
```

We use `long` instead of `int` to avoid integer overflow when adding large values.

### 2. Initialize Left Sum

```java
long leftsum = 0;
```

This variable maintains the sum of elements on the left side of the current split.

Initially:

```text
leftsum = 0
```

### 3. Traverse the Array

```java
for(int i = 0; i < nums.length - 1; i++)
```

Why `nums.length - 1`?

Because the right subarray must contain at least one element.

We cannot split after the last element.

### 4. Update Left Sum

```java
leftsum += nums[i];
```

We add the current element to the left sum.

For example:

```text
nums = [10, 4, -8, 7]
```

At index `0`:

```text
leftsum = 10
```

At index `1`:

```text
leftsum = 10 + 4 = 14
```

### 5. Calculate Right Sum

```java
long rightsum = sum - leftsum;
```

We already know the total sum.

Therefore:

\[
\text{rightSum}=\text{totalSum}-\text{leftSum}
\]

Example:

```text
total sum = 13
leftsum = 10

rightsum = 13 - 10
         = 3
```

### 6. Check the Valid Condition

```java
if(leftsum >= rightsum){
    splitcount++;
}
```

If the left sum is greater than or equal to the right sum, the split is valid.

We increment `splitcount`.

### 7. Return the Answer

```java
return splitcount;
```

After checking every possible split, return the total number of valid splits.

---

## 🧪 Dry Run

Consider:

```text
nums = [10, 4, -8, 7]
```

### Initial Calculation

```text
Total sum = 10 + 4 - 8 + 7
          = 13

leftsum = 0
splitcount = 0
```

### Iteration 1 — i = 0

Current element:

```text
nums[0] = 10
```

Update left sum:

```text
leftsum = 0 + 10
        = 10
```

Calculate right sum:

```text
rightsum = 13 - 10
         = 3
```

Check:

```text
10 >= 3 → true
```

Therefore:

```text
splitcount = 1
```

Split:

```text
[10] | [4, -8, 7]
```

### Iteration 2 — i = 1

Current element:

```text
nums[1] = 4
```

Update left sum:

```text
leftsum = 10 + 4
        = 14
```

Calculate right sum:

```text
rightsum = 13 - 14
         = -1
```

Check:

```text
14 >= -1 → true
```

Therefore:

```text
splitcount = 2
```

Split:

```text
[10, 4] | [-8, 7]
```

### Iteration 3 — i = 2

Current element:

```text
nums[2] = -8
```

Update left sum:

```text
leftsum = 14 - 8
        = 6
```

Calculate right sum:

```text
rightsum = 13 - 6
         = 7
```

Check:

```text
6 >= 7 → false
```

No increment.

Split:

```text
[10, 4, -8] | [7]
```

### Final Output

```text
2
```

---

## 📊 Dry Run Table

| Index | Current element | Left sum | Right sum | Condition | Count |
|---:|---:|---:|---:|---|---:|
| 0 | 10 | 10 | 3 | 10 ≥ 3 | 1 |
| 1 | 4 | 14 | -1 | 14 ≥ -1 | 2 |
| 2 | -8 | 6 | 7 | 6 ≥ 7 | 2 |

---

## ❓ Why Use `long` Instead of `int`?

```java
long sum = 0;
long leftsum = 0;
```

The total sum of the array can exceed the range of an integer.

Java `int` supports values approximately between:

```text
-2.1 billion to +2.1 billion
```

Using `long` allows us to safely handle much larger sums.

---

## ❓ Why Do We Use `nums.length - 1`?

```java
for(int i = 0; i < nums.length - 1; i++)
```

Suppose:

```text
nums = [1, 2, 3]
```

Possible splits:

```text
[1] | [2, 3]
[1, 2] | [3]
```

We cannot create:

```text
[1, 2, 3] | []
```

because the right subarray would be empty.

Therefore, the last index is excluded.

---

## 🧠 Mathematical Formula

Let:

- `S` = total sum
- `L` = left sum
- `R` = right sum

We know:

\[
S=L+R
\]

Therefore:

\[
R=S-L
\]

The valid condition is:

\[
L\geq R
\]

Substitute:

\[
L\geq S-L
\]

Simplify:

\[
\boxed{2L\geq S}
\]

Your code uses the equivalent comparison:

```java
leftsum >= rightsum
```

---

## ⏱️ Complexity Analysis

Let `n` be the length of the array.

### Time Complexity

First loop calculates the total sum:

```text
O(n)
```

Second loop checks every possible split:

```text
O(n)
```

Total:

\[
O(n)+O(n)=\boxed{O(n)}
\]

### Space Complexity

Only a few variables are used:

- `sum`
- `leftsum`
- `rightsum`
- `splitcount`
- `i`

No additional data structure is created.

\[
\boxed{O(1)}
\]

---

## 🧠 Key Takeaways

- Calculate the total sum once.
- Maintain a running left sum.
- Calculate right sum using `totalSum - leftSum`.
- Check whether `leftSum >= rightSum`.
- Use `nums.length - 1` to ensure both subarrays are non-empty.
- Use `long` to avoid integer overflow.

## 📌 Final Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(1)
```

### One-Line Idea

**Calculate the total sum once, maintain the left prefix sum, derive the right sum by subtraction, and count every split where the left sum is greater than or equal to the right sum.**