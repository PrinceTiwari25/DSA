# LeetCode 2295 — Replace Elements in an Array

## Question

You are given an integer array `nums` and a 2D integer array `operations`.

Each operation is represented as:

```text
[oldValue, newValue]
```

For every operation:

- Find the element `oldValue` in `nums`.
- Replace it with `newValue`.

Return the final array after performing all operations in order.

It is guaranteed that `oldValue` exists in the array when the operation is performed.

### Example

**Input:**

```text
nums = [1,2,4,6]
operations = [[1,3],[4,7],[6,1]]
```

**Output:**

```text
[3,2,7,1]
```

### Explanation

Start:

```text
[1,2,4,6]
```

Operation 1:

```text
[1,3]
```

Replace `1` with `3`:

```text
[3,2,4,6]
```

Operation 2:

```text
[4,7]
```

Replace `4` with `7`:

```text
[3,2,7,6]
```

Operation 3:

```text
[6,1]
```

Replace `6` with `1`:

```text
[3,2,7,1]
```

Therefore:

```text
Answer = [3,2,7,1]
```

---

## LeetCode Link

https://leetcode.com/problems/replace-elements-in-an-array/

## Approach — HashMap

The main challenge is finding the index of `oldValue` efficiently.

If we search through the array every time, each operation could take `O(n)`.

Instead, we use a `HashMap`:

```text
value → index
```

For example:

```text
nums = [1,2,4,6]
```

The map stores:

```text
1 → 0
2 → 1
4 → 2
6 → 3
```

Now if an operation says:

```text
[4,7]
```

we can immediately find:

```text
map.get(4) → 2
```

So we know that `4` is at index `2`.

Then:

```text
nums[2] = 7
```

and update the map.

---

## Variables

```text
map       → stores value → index
oldvalue  → value that needs to be replaced
newvalue  → replacement value
index     → position of oldvalue in nums
```

---

## Steps

### Step 1 — Build the HashMap

Traverse `nums` and store:

```text
value → index
```

For:

```text
nums = [1,2,4,6]
```

we get:

```text
map = {
    1 → 0,
    2 → 1,
    4 → 2,
    6 → 3
}
```

### Step 2 — Process Each Operation

For every operation:

```text
[oldvalue, newvalue]
```

1. Get the index of `oldvalue`.
2. Replace `nums[index]` with `newvalue`.
3. Remove `oldvalue` from the map.
4. Add `newvalue` with the same index.

---



---

# Dry Run

## Input

```text
nums = [1,2,4,6]

operations = [
    [1,3],
    [4,7],
    [6,1]
]
```

---

## Step 1 — Create HashMap

Initially:

```text
nums = [1,2,4,6]
```

Traverse the array.

```text
i = 0
nums[0] = 1
```

Store:

```text
1 → 0
```

Next:

```text
2 → 1
```

Next:

```text
4 → 2
```

Next:

```text
6 → 3
```

Final map:

```text
map = {
    1 → 0,
    2 → 1,
    4 → 2,
    6 → 3
}
```

---

# Operation 1

```text
[1,3]
```

So:

```text
oldvalue = 1
newvalue = 3
```

Find the index:

```java
int index = map.get(oldvalue);
```

Therefore:

```text
map.get(1) = 0
```

So:

```text
index = 0
```

Replace:

```java
nums[index] = newvalue;
```

Array becomes:

```text
[3,2,4,6]
```

Remove old value:

```java
map.remove(1);
```

Add new value:

```java
map.put(3,0);
```

Map becomes:

```text
{
    2 → 1,
    4 → 2,
    6 → 3,
    3 → 0
}
```

---

# Operation 2

```text
[4,7]
```

So:

```text
oldvalue = 4
newvalue = 7
```

Find index:

```text
map.get(4) = 2
```

Therefore:

```text
index = 2
```

Replace:

```text
nums[2] = 7
```

Array:

```text
[3,2,7,6]
```

Remove:

```text
map.remove(4)
```

Add:

```text
map.put(7,2)
```

Map becomes:

```text
{
    2 → 1,
    6 → 3,
    3 → 0,
    7 → 2
}
```

---

# Operation 3

```text
[6,1]
```

So:

```text
oldvalue = 6
newvalue = 1
```

Find index:

```text
map.get(6) = 3
```

Therefore:

```text
index = 3
```

Replace:

```text
nums[3] = 1
```

Array becomes:

```text
[3,2,7,1]
```

Remove:

```text
map.remove(6)
```

Add:

```text
map.put(1,3)
```

Final map:

```text
{
    1 → 3,
    2 → 1,
    3 → 0,
    7 → 2
}
```

---

# Final Answer

```text
[3,2,7,1]
```

---

# Understanding `map.put(nums[i], i)`

This line:

```java
map.put(nums[i], i);
```

stores:

```text
value → index
```

For example:

```text
nums[2] = 4
```

So:

```java
map.put(4, 2);
```

means:

```text
4 is located at index 2
```

This allows us to find an element's position quickly.

---

# Understanding `map.get(oldvalue)`

Suppose:

```text
oldvalue = 4
```

and the map contains:

```text
4 → 2
```

Then:

```java
int index = map.get(oldvalue);
```

gives:

```text
index = 2
```

Now we can directly update:

```java
nums[2] = newvalue;
```

No array traversal is required.

---

# Why Do We Remove `oldvalue`?

After:

```text
[4,7]
```

the value `4` no longer exists at that position.

Therefore, we remove it:

```java
map.remove(oldvalue);
```

Then add the new value:

```java
map.put(newvalue,index);
```

So the map always represents the **current state of the array**.

---

# Important Idea

Suppose:

```text
nums = [1,2,4,6]
```

and operation:

```text
[4,7]
```

Before:

```text
4 → 2
```

After replacement:

```text
7 → 2
```

The index doesn't change.

Only the value changes.

Therefore:

```text
Old:
4 → index 2

New:
7 → index 2
```

This is the key idea behind the solution.

---

# Why HashMap Instead of Searching?

Without a HashMap, for every operation we could search the array:

```text
for every operation
    search for oldvalue
```

This can take:

```text
O(n)
```

for each operation.

If there are `m` operations:

```text
O(n × m)
```

With a HashMap:

```text
map.get(oldvalue)
```

takes `O(1)` average time.

So each operation can be processed efficiently.

---

# Complexity

Let:

```text
n = length of nums
m = number of operations
```

## Time Complexity

### Building the HashMap

We traverse `nums` once:

```text
O(n)
```

### Processing Operations

Each operation performs:

```text
get()
put()
remove()
```

Each HashMap operation is `O(1)` on average.

For `m` operations:

```text
O(m)
```

Therefore:

```text
Total = O(n + m)
```

---

## Space Complexity

The HashMap stores every element and its index.

Therefore:

```text
O(n)
```

extra space.

---

# Key Idea

```text
Build Map
    ↓
Value → Index
    ↓
Get oldvalue's index
    ↓
Replace nums[index]
    ↓
Remove oldvalue from Map
    ↓
Add newvalue → same index
    ↓
Next Operation
```

The most important mapping is:

```text
value → index
```

For example:

```text
4 → 2
```

means:

```text
value 4 is currently at index 2
```

After replacing `4` with `7`:

```text
7 → 2
```

---

# Final Complexity

```text
Time  : O(n + m) average
Space : O(n)
```

**Pattern Used:** HashMap — Value to Index Mapping