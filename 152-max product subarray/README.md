# Maximum Product Subarray

## LeetCode Problem

[LeetCode 152 — Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)

---

## Problem

Given an integer array `nums`, find the contiguous subarray that has the largest product and return its product.

### Example

**Input:**

```text
nums = [2,3,-2,4]
```

**Output:**

```text
6
```

The subarray with the maximum product is:

```text
[2,3]
```

Product:

```text
2 × 3 = 6
```

---

## Optimal Approach: Track Maximum and Minimum

For every element, maintain two values:

```text
maxProduct
minProduct
```

### Why do we need both?

Because a negative number can change the maximum into the minimum and the minimum into the maximum.

For example:

```text
minProduct = -6
current = -4

-6 × -4 = 24
```

So a **minimum negative product can become the maximum product**.

---

## Approach

For every number, we have three possibilities:

```text
1. Start a new subarray
   → nums[i]

2. Continue using the previous maximum
   → nums[i] × maxProduct

3. Continue using the previous minimum
   → nums[i] × minProduct
```

Therefore:

```text
newMax = max(
    nums[i],
    nums[i] × maxProduct,
    nums[i] × minProduct
)
```

And:

```text
newMin = min(
    nums[i],
    nums[i] × maxProduct,
    nums[i] × minProduct
)
```

Finally:

```text
answer = max(answer, maxProduct)
```

### Important

Calculate `newMax` and `newMin` first because both must use the **previous** `maxProduct` and `minProduct`.



---

## Dry Run

### Input

```text
nums = [2,3,-2,4]
```

Initially:

```text
maxProduct = 2
minProduct = 2
answer = 2
```

### `num = 3`

Possible products:

```text
3
3 × 2 = 6
3 × 2 = 6
```

Therefore:

```text
maxProduct = 6
minProduct = 3
answer = 6
```

---

### `num = -2`

Possible products:

```text
-2
-2 × 6  = -12
-2 × 3  = -6
```

Therefore:

```text
maxProduct = -2
minProduct = -12
answer = 6
```

---

### `num = 4`

Possible products:

```text
4
4 × (-2)  = -8
4 × (-12) = -48
```

Therefore:

```text
maxProduct = 4
minProduct = -48
answer = 6
```

Final answer:

```text
6
```

---

## Why `minProduct` is Important

Consider:

```text
nums = [-2,3,-4]
```

After processing `3`:

```text
maxProduct = 3
minProduct = -6
```

Now process `-4`:

```text
-4 × 3  = -12
-4 × -6 = 24
```

So:

```text
maxProduct = 24
```

The maximum product comes from:

```text
[-2,3,-4]
```

```text
(-2) × 3 × (-4) = 24
```

This is why tracking only `maxProduct` would fail.

---

## Complexity Analysis

### Time Complexity: O(n)

We traverse the array only once.

### Space Complexity: O(1)

We use only a few variables.

---

## Key Takeaway

For **Maximum Product Subarray**, remember:

```text
Negative × Negative = Positive
```

Therefore, we must track:

```text
maxProduct
minProduct
```

At every element:

```text
newMax = max(num, num × maxProduct, num × minProduct)

newMin = min(num, num × maxProduct, num × minProduct)
```

Then:

```text
answer = max(answer, maxProduct)
```

### Pattern

```text
Maximum Product Subarray
          ↓
Track Max + Min
          ↓
Handle Negative Numbers
          ↓
O(n) Time
O(1) Space
```

**Maximum Product Subarray → O(n) Time | O(1) Space**
