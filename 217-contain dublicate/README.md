# LeetCode 217 — Contains Duplicate

## Question

Given an integer array `nums`, return `true` if any value appears **at least twice** in the array.

Return `false` if every element is unique.

### Example 1

**Input:**

```text
nums = [1,2,3,1]
```

**Output:**

```text
true
```

### Explanation

The number `1` appears twice:

```text
[1,2,3,1]
 ↑     ↑
```

Therefore, the array contains a duplicate.

---

### Example 2

**Input:**

```text
nums = [1,2,3,4]
```

**Output:**

```text
false
```

All elements appear only once, so there is no duplicate.

---

## LeetCode Link

https://leetcode.com/problems/contains-duplicate/

## Approach — HashSet

We use a **HashSet** to keep track of the elements that we have already seen.

A `HashSet` stores only **unique values**.

The important property we use is:

```java
set.add(num)
```

This method returns:

* `true` → if the element was not already present.
* `false` → if the element already exists in the set.

Therefore, we can detect duplicates directly while adding elements.

### Steps

1. Create an empty `HashSet`.
2. Traverse every number in the array.
3. Try to add the number to the set.
4. If `set.add(num)` returns `false`, the number already exists.
5. Therefore, return `true`.
6. If we finish the entire array without finding a duplicate, return `false`.

---

## Code

```java
import java.util.HashSet;

class containdublicate {
    public boolean containsDuplicate(int[] nums) {

        HashSet<Integer> set = new HashSet<>(nums.length * 2);

        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }

        return false;
    }
}
```

---

# Understanding `HashSet`

A `HashSet` stores unique elements.

For example:

```text
nums = [1,2,3,1]
```

The set changes like this:

```text
Add 1 → [1]
Add 2 → [1,2]
Add 3 → [1,2,3]
Add 1 → already exists
```

Therefore, we know that `1` is a duplicate.

---

# Important Part — `set.add(num)`

The most important line in your solution is:

```java
if (!set.add(num)) {
    return true;
}
```

Normally, we might write:

```java
if (set.contains(num)) {
    return true;
}

set.add(num);
```

But your solution combines both operations into one.

### What does `set.add(num)` return?

If the number is new:

```java
set.add(5)
```

returns:

```text
true
```

If the number already exists:

```java
set.add(5)
```

returns:

```text
false
```

Therefore:

```java
!set.add(num)
```

means:

```text
The number already exists
```

So we found a duplicate.

---

# Dry Run

## Input

```text
nums = [1,2,3,1]
```

Initially:

```text
set = {}
```

---

### Step 1

```text
num = 1
```

Try to add:

```java
set.add(1)
```

Result:

```text
true
```

So:

```text
set = {1}
```

Continue.

---

### Step 2

```text
num = 2
```

Try:

```java
set.add(2)
```

Result:

```text
true
```

Set becomes:

```text
set = {1,2}
```

Continue.

---

### Step 3

```text
num = 3
```

Try:

```java
set.add(3)
```

Result:

```text
true
```

Set becomes:

```text
set = {1,2,3}
```

Continue.

---

### Step 4

```text
num = 1
```

Try:

```java
set.add(1)
```

But `1` already exists.

Therefore:

```text
set.add(1) → false
```

The condition:

```java
if (!set.add(num))
```

becomes:

```text
if (!false)
if (true)
```

So:

```java
return true;
```

---

# Final Answer

```text
true
```

because `1` appears more than once.

---

# Another Dry Run

## Input

```text
nums = [1,2,3,4]
```

### Process

```text
1 → set = {1}
2 → set = {1,2}
3 → set = {1,2,3}
4 → set = {1,2,3,4}
```

No `set.add()` operation returned `false`.

Therefore:

```java
return false;
```

Final answer:

```text
false
```

---

# Why Do We Use `HashSet`?

Without a HashSet, we could compare every element with every other element.

That would take:

```text
O(n²)
```

time.

Using a HashSet gives us approximately:

```text
O(1)
```

average time for insertion and lookup.

Therefore, we can solve the problem in:

```text
O(n)
```

average time.

---

# Why `nums.length * 2`?

Your code creates the HashSet as:

```java
new HashSet<>(nums.length * 2);
```

This gives the HashSet an initial capacity based on the expected number of elements.

It can reduce the need for resizing/rehashing as elements are added.

However, it does **not change the overall Big-O complexity**.

A simpler version could also be:

```java
HashSet<Integer> set = new HashSet<>();
```

Your version is still correct.

---

# Complexity

## Time Complexity

```text
O(n)
```

We traverse the array once.

Each `HashSet` insertion takes **O(1) average time**.

Therefore:

```text
Time = O(n)
```

### Important Note

HashSet operations are `O(1)` **on average**, not guaranteed in every theoretical worst case.

---

## Space Complexity

```text
O(n)
```

In the worst case, if there are no duplicates, the HashSet stores all `n` elements.

Therefore:

```text
Space = O(n)
```

---

# Key Idea

The main idea is:

```text
Traverse Array
      ↓
Add Number to HashSet
      ↓
Was it already present?
   ↙          ↘
 Yes           No
  ↓             ↓
Duplicate     Continue
  ↓
return true
```

Using:

```java
set.add(num)
```

we can check and insert at the same time.

### Most Important Line

```java
if (!set.add(num)) {
    return true;
}
```

Meaning:

```text
If adding the number fails,
the number already exists,
so a duplicate is found.
```

---

# Final Complexity

```text
Time  : O(n) average
Space : O(n)
```

**Pattern Used:** HashSet / Duplicate Detection
