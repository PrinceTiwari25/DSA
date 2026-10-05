# LeetCode 3761 — Minimum Mirror Pair Distance

## 🔗 Problem

**LeetCode:** 3761. Minimum Mirror Pair Distance

### Problem Statement

You are given an integer array `nums`.

A **mirror pair** is a pair of indices `(i, j)` such that:

- `i < j`
- `reverse(nums[i]) == nums[j]`

Return the **minimum distance** between any mirror pair:

```text
j - i
```

If no mirror pair exists, return `-1`.

---

## 💡 Example

### Example 1

```text
Input:
nums = [12, 21]

Output:
1
```

### Explanation

```text
nums[0] = 12
reverse(12) = 21

nums[1] = 21
```

So:

```text
reverse(nums[0]) == nums[1]
```

Therefore, the mirror pair is:

```text
(0, 1)
```

Distance:

```text
1 - 0 = 1
```

---

# 🚀 Approach

We can solve this problem efficiently using:

- `HashMap`
- Number reversal
- One pass through the array

The main idea is:

> For every current number `nums[i]`, check whether its value already exists in the HashMap as a reversed value of some previous number.

---

# 🧠 Why HashMap?

Suppose:

```text
nums = [12, 21]
```

At index `0`:

```text
nums[0] = 12
reverse(12) = 21
```

So we store:

```text
map = {
    21 -> 0
}
```

Now at index `1`:

```text
nums[1] = 21
```

We check:

```java
map.get(21)
```

It gives:

```text
0
```

That means:

```text
reverse(nums[0]) = nums[1]
```

So we found a mirror pair.

Distance:

```text
1 - 0 = 1
```

---

# 🔑 Important Idea

The HashMap stores:

```text
reversed value → index
```

Not:

```text
original value → index
```

For example:

```text
nums[i] = 123
reverse(123) = 321
```

We store:

```text
321 → i
```

Later, if we encounter:

```text
nums[j] = 321
```

we immediately know:

```text
reverse(nums[i]) == nums[j]
```

---

# 📝 Algorithm

For every index `i`:

### Step 1

Check whether the current number exists in the map:

```java
Integer index = map.get(nums[i]);
```

If it exists, we have found a mirror pair.

---

### Step 2

Calculate its distance:

```java
i - index
```

Update the minimum:

```java
result = Math.min(result, i - index);
```

---

### Step 3

Reverse the current number and store it:

```java
map.put(reverse(nums[i]), i);
```

This allows future elements to find the current element.



---

# 🔍 Code Explanation

## 1. HashMap

```java
HashMap<Integer, Integer> map = new HashMap<>(nums.length * 2);
```

The map stores:

```text
reversed number → index
```

For example:

```text
12 → reverse = 21
```

We store:

```text
21 → 0
```

---

## 2. Result

```java
int result = Integer.MAX_VALUE;
```

We initially assume that no mirror pair has been found.

Whenever we find one:

```java
result = Math.min(result, i - index);
```

we keep the smallest distance.

---

# 🔄 Main Loop

```java
for (int i = 0; i < nums.length; i++) {
```

We process every element from left to right.

---

## 3. Search for Previous Mirror

```java
Integer index = map.get(nums[i]);
```

The current number is:

```text
nums[i]
```

We ask:

> Did we previously encounter a number whose reverse equals `nums[i]`?

If yes, the map contains its index.

---

## 4. Check if Pair Exists

```java
if (index != null) {
```

If `index` is not `null`, a previous mirror number exists.

For example:

```text
map = {
    21 → 0
}
```

Current:

```text
nums[i] = 21
```

Then:

```java
map.get(21)
```

returns:

```text
0
```

---

## 5. Calculate Distance

```java
result = Math.min(result, i - index);
```

If:

```text
i = 4
index = 2
```

then:

```text
distance = 4 - 2
         = 2
```

We compare it with the previous minimum.

---

# 🔁 Store Current Reversed Number

```java
map.put(reverse(nums[i]), i);
```

This is the most important line.

Suppose:

```text
nums[i] = 123
```

Then:

```text
reverse(123) = 321
```

We store:

```text
321 → i
```

So if `321` appears later, we can detect the mirror pair.

---

# 🔢 Reverse Function

```java
private int reverse(int num) {
    int rev = 0;

    while (num > 0) {
        rev = rev * 10 + num % 10;
        num /= 10;
    }

    return rev;
}
```

This reverses the digits of a number.

### Example

```text
num = 123
```

### First iteration

```text
num % 10 = 3

rev = 0 * 10 + 3
    = 3

num = 123 / 10
    = 12
```

### Second iteration

```text
num % 10 = 2

rev = 3 * 10 + 2
    = 32

num = 12 / 10
    = 1
```

### Third iteration

```text
num % 10 = 1

rev = 32 * 10 + 1
    = 321
```

Therefore:

```text
reverse(123) = 321
```

---

# 🧪 Dry Run

Consider:

```text
nums = [12, 34, 21, 43]
```

### Initial

```text
map = {}
result = ∞
```

---

### i = 0

```text
nums[0] = 12
```

Check:

```text
map.get(12)
```

Nothing found.

Reverse:

```text
reverse(12) = 21
```

Store:

```text
map = {
    21 → 0
}
```

---

### i = 1

```text
nums[1] = 34
```

Check:

```text
map.get(34)
```

Nothing found.

Reverse:

```text
reverse(34) = 43
```

Store:

```text
map = {
    21 → 0,
    43 → 1
}
```

---

### i = 2

```text
nums[2] = 21
```

Check:

```text
map.get(21)
```

Found:

```text
index = 0
```

Distance:

```text
2 - 0 = 2
```

Therefore:

```text
result = 2
```

Now reverse current number:

```text
reverse(21) = 12
```

Store:

```text
map = {
    21 → 0,
    43 → 1,
    12 → 2
}
```

---

### i = 3

```text
nums[3] = 43
```

Check:

```text
map.get(43)
```

Found:

```text
index = 1
```

Distance:

```text
3 - 1 = 2
```

Update:

```text
result = min(2, 2)
       = 2
```

Therefore:

```text
Output = 2
```

---

# 📊 Visualization

For:

```text
nums = [12, 34, 21, 43]
```

We have:

```text
Index:   0    1    2    3
         ↓    ↓    ↓    ↓
nums:   12   34   21   43
         ↕         ↕
       reverse    reverse
         ↓         ↓
        21        34
```

Actually, the mirror relationships are:

```text
12 → 21

34 → 43
```

Therefore:

```text
(0, 2) → distance = 2
(1, 3) → distance = 2
```

Minimum:

```text
2
```

---

# ⚠️ Why Do We Check Before `map.put()`?

The code does:

```java
Integer index = map.get(nums[i]);

if (index != null) {
    ...
}

map.put(reverse(nums[i]), i);
```

We check first because the mirror pair must satisfy:

```text
i < j
```

The map should contain only information from earlier indices.

This ensures that when we find:

```text
map.get(nums[i])
```

the stored index is always before the current index.

---

# ❓ Why Not Store `nums[i]` Directly?

Suppose:

```text
nums[i] = 12
```

We need to find:

```text
reverse(12) = 21
```

So we need to remember:

```text
21 → i
```

not:

```text
12 → i
```

That's why we use:

```java
map.put(reverse(nums[i]), i);
```

---

# ⏱️ Complexity

Let:

- `n` = number of elements
- `d` = number of digits in a number

### Time Complexity

For every element, we reverse its digits.

Reversing a number takes:

```text
O(d)
```

For `n` elements:

```text
O(n × d)
```

For normal integer values, `d` is bounded by a constant, so this is commonly considered:

```text
O(n)
```

### Space Complexity

The HashMap can contain up to `n` entries:

```text
O(n)
```

Therefore:

```text
Time:  O(n × d) ≈ O(n)
Space: O(n)
```

---

# 🧠 Key Takeaway

The entire solution is based on one simple idea:

```text
Store reverse(nums[i]) → i
```

Then for the next element:

```text
if nums[j] exists in map
```

we know:

```text
reverse(nums[i]) == nums[j]
```

and therefore:

```text
distance = j - i
```

---

# ⭐ Important Pattern

This problem is a good example of the:

### HashMap + Transformation Pattern

Instead of storing the original value:

```text
value → index
```

we store a transformed value:

```text
reverse(value) → index
```

Then future elements can quickly find their required match.

---

# 📌 Final Complexity

```text
Time Complexity:  O(n × d) ≈ O(n)
Space Complexity: O(n)
```

### One-Line Idea

> **For every number, store its reversed value with its index; when the current number is already in the map, calculate the distance and keep the minimum.**