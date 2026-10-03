# LeetCode 287 — Find the Duplicate Number

## Question

Given an array `nums` containing `n + 1` integers where:

- Each integer is in the range `[1, n]`.
- There is only **one repeated number**, but it may be repeated more than once.

Find the **duplicate number**.

You must solve the problem:

- Without modifying the array.
- Using only constant extra space.

### Example

**Input:**

```text
nums = [1,3,4,2,2]
```

**Output:**

```text
2
```

### Explanation

The number `2` appears more than once:

```text
[1,3,4,2,2]
       ↑ ↑
       2 2
```

Therefore, the duplicate number is:

```text
2
```

---

## LeetCode Link

https://leetcode.com/problems/find-the-duplicate-number/

## Approach — Floyd's Cycle Detection Algorithm

We use **Floyd's Cycle Detection Algorithm**, also known as the **Tortoise and Hare algorithm**.

The important idea is to treat the array like a **linked list**.

For every index:

```text
index → nums[index]
```

This creates a sequence of pointers.

Because the array contains `n + 1` numbers and every number is between `1` and `n`, eventually some values must point to the same location.

This creates a **cycle**.

The duplicate number is the **entrance of that cycle**.

---

# Why Can We Treat the Array Like a Linked List?

Suppose:

```text
nums = [1,3,4,2,2]
```

We can treat each number as the next index.

Starting from index `0`:

```text
0 → nums[0] → 1
1 → nums[1] → 3
3 → nums[3] → 2
2 → nums[2] → 4
4 → nums[4] → 2
```

So the path becomes:

```text
0 → 1 → 3 → 2 → 4
            ↑     |
            |_____|
```

There is a cycle:

```text
2 → 4 → 2 → 4 → ...
```

The entrance of this cycle is:

```text
2
```

which is the duplicate number.

---

# Floyd's Cycle Detection

Floyd's algorithm uses two pointers:

```text
slow → moves one step at a time
fast → moves two steps at a time
```

### Slow

```java
slow = nums[slow];
```

### Fast

```java
fast = nums[nums[fast]];
```

The fast pointer moves twice as quickly as the slow pointer.

Eventually, they meet inside the cycle.

---

# Two Phases

The algorithm has two phases.

### Phase 1 — Find the Meeting Point

Move `slow` one step and `fast` two steps until:

```text
slow == fast
```

This proves that a cycle exists.

### Phase 2 — Find the Cycle Entrance

Reset:

```java
slow = nums[0];
```

Then move both pointers one step at a time:

```java
slow = nums[slow];
fast = nums[fast];
```

When they meet again:

```text
slow == fast
```

that value is the duplicate number.

---



# Dry Run

## Input

```text
nums = [1,3,4,2,2]
```

Let's build the pointer path:

```text
0 → 1 → 3 → 2 → 4
            ↑     |
            |_____|
```

The cycle is:

```text
2 → 4 → 2
```

---

# Phase 1 — Find Meeting Point

Initially:

```text
slow = nums[0] = 1
fast = nums[0] = 1
```

### Iteration 1

Slow moves one step:

```text
slow = nums[1]
     = 3
```

Fast moves two steps:

```text
fast = nums[nums[1]]
     = nums[3]
     = 2
```

Now:

```text
slow = 3
fast = 2
```

---

### Iteration 2

Slow:

```text
slow = nums[3]
     = 2
```

Fast:

```text
fast = nums[nums[2]]
     = nums[4]
     = 2
```

Now:

```text
slow = 2
fast = 2
```

They meet.

Therefore, we have found a point inside the cycle.

```text
Meeting Point = 2
```

---

# Phase 2 — Find Cycle Entrance

Now reset:

```java
slow = nums[0];
```

Therefore:

```text
slow = 1
fast = 2
```

Now both pointers move **one step at a time**.

---

### Iteration 1

Slow:

```text
slow = nums[1]
     = 3
```

Fast:

```text
fast = nums[2]
     = 4
```

Now:

```text
slow = 3
fast = 4
```

---

### Iteration 2

Slow:

```text
slow = nums[3]
     = 2
```

Fast:

```text
fast = nums[4]
     = 2
```

Now:

```text
slow = 2
fast = 2
```

They meet again.

Therefore:

```text
Duplicate = 2
```

---

# Final Answer

```text
2
```

---

# Pointer Visualization

For:

```text
nums = [1,3,4,2,2]
```

The linked-list representation is:

```text
        ┌───────────┐
        ↓           │
0 → 1 → 3 → 2 → 4 ─┘
```

The cycle is:

```text
2 → 4 → 2 → 4 → ...
```

The cycle entrance is:

```text
2
```

Therefore, `2` is the duplicate number.

---

# Understanding `slow`

The slow pointer moves one step:

```java
slow = nums[slow];
```

For example:

```text
slow = 3
```

Then:

```text
slow = nums[3]
```

If:

```text
nums[3] = 2
```

then:

```text
slow = 2
```

So the slow pointer follows:

```text
index → nums[index]
```

---

# Understanding `fast`

The fast pointer moves two steps:

```java
fast = nums[nums[fast]];
```

Suppose:

```text
fast = 1
```

First step:

```text
nums[1] = 3
```

Second step:

```text
nums[3] = 2
```

Therefore:

```text
fast = 2
```

So:

```text
slow → 1 step
fast → 2 steps
```

---

# Why Does a Cycle Exist?

There are `n + 1` elements, but the possible values are only:

```text
1 to n
```

Therefore, at least two positions must contain the same value.

That repeated value creates a situation where two paths point toward the same location, producing a cycle when the array is viewed as a linked list.

The duplicate value becomes the entrance to that cycle.

---

# Why Use `do-while`?

Your code uses:

```java
do {
    slow = nums[slow];
    fast = nums[nums[fast]];
} while (slow != fast);
```

This is useful because we need to move the pointers **at least once** before checking whether they meet.

Initially:

```text
slow = fast = nums[0]
```

If we used a normal `while (slow != fast)`, the loop would not execute because they are initially equal.

The `do-while` guarantees that both pointers make at least one move.

---

# Why Reset `slow`?

After Phase 1:

```text
slow == fast
```

means they have met somewhere **inside the cycle**.

But this meeting point is not necessarily the duplicate.

So we reset:

```java
slow = nums[0];
```

Then both pointers move one step at a time:

```java
slow = nums[slow];
fast = nums[fast];
```

Their next meeting point is the **entrance of the cycle**, which is the duplicate number.

---

# Why Does the Second Phase Work?

Suppose:

```text
Distance from start to cycle entrance = A
```

and:

```text
Distance from cycle entrance to meeting point = B
```

Because the fast pointer travels twice as fast as the slow pointer, when they meet, the remaining distance from the meeting point back to the cycle entrance is related to the distance from the start to the cycle entrance.

Therefore, after resetting `slow` to the beginning and moving both one step at a time, they meet exactly at the cycle entrance.

That entrance represents the duplicate number.

---

# Why Don't We Use a HashSet?

A HashSet solution could detect the duplicate easily:

```text
Add every number to HashSet
        ↓
If already present
        ↓
Duplicate found
```

But that requires:

```text
O(n)
```

extra space.

This problem requires constant extra space.

Floyd's algorithm uses only:

```text
slow
fast
```

Therefore:

```text
O(1)
```

space is achieved.

---

# Complexity

## Time Complexity

```text
O(n)
```

Both phases traverse the array a linear number of times.

Therefore:

```text
Time = O(n)
```

## Space Complexity

```text
O(1)
```

Only two pointers are used:

```text
slow
fast
```

No HashSet, HashMap, or extra array is required.

Therefore:

```text
Space = O(1)
```

---

# Key Idea

The entire solution can be remembered as:

```text
Array
  ↓
Treat values as next pointers
  ↓
Creates a cycle
  ↓
Slow = 1 step
Fast = 2 steps
  ↓
Find meeting point
  ↓
Reset slow
  ↓
Move both 1 step
  ↓
Meeting point = duplicate
```

### Most Important Code

```java
do {
    slow = nums[slow];
    fast = nums[nums[fast]];
} while (slow != fast);
```

Then:

```java
slow = nums[0];

while (slow != fast) {
    slow = nums[slow];
    fast = nums[fast];
}
```

---

# Final Complexity

```text
Time  : O(n)
Space : O(1)
```

**Pattern Used:** Floyd's Cycle Detection / Fast & Slow Pointers