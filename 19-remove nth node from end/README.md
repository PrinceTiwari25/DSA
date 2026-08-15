# Remove Nth Node From End of List

## LeetCode Problem

[Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

---

## Problem

Given the `head` of a linked list, remove the `nth` node from the end of the list and return the head of the modified linked list.

### Example

**Input:**

```text
head = [1,2,3,4,5]
n = 2
```

The 2nd node from the end is `4`.

After removing it:

```text
1 → 2 → 3 → 5
```

**Output:**

```text
[1,2,3,5]
```

---

## Solution

### Optimal Approach: Two Pointers

We use two pointers:

```text
slow
fast
```

and a **dummy node** before the head.

The main idea is to keep `fast` exactly `n` nodes ahead of `slow`.

### Steps

1. Create a dummy node and connect it to `head`.
2. Set both `slow` and `fast` to `dummy`.
3. Move `fast` `n` steps forward.
4. Move both `slow` and `fast` together until `fast.next` becomes `null`.
5. Now `slow` is pointing to the node **before the node we want to remove**.
6. Skip the target node using:

```text
slow.next = slow.next.next
```

7. Return `dummy.next`.

This two-pointer approach solves the problem in one pass with `O(n)` time and `O(1)` extra space. :contentReference[oaicite:1]{index=1}



## Dry Run

### Input

```text
1 → 2 → 3 → 4 → 5 → null

n = 2
```

### Step 1: Add Dummy Node

```text
dummy → 1 → 2 → 3 → 4 → 5 → null
  ↑
slow
fast
```

Both pointers start at `dummy`.

---

### Step 2: Move `fast` 2 Steps

After moving `fast` two times:

```text
dummy → 1 → 2 → 3 → 4 → 5 → null
  ↑         ↑
 slow      fast
```

Now `fast` is 2 nodes ahead of `slow`.

---

### Step 3: Move Both Pointers

Move both one step at a time.

```text
slow = 1
fast = 3
```

Then:

```text
slow = 2
fast = 4
```

Then:

```text
slow = 3
fast = 5
```

Now:

```text
fast.next == null
```

So we stop.

---

### Step 4: Remove the Node

Currently:

```text
1 → 2 → 3 → 4 → 5
        ↑
       slow
```

`slow` is at `3`.

The node we want to remove is:

```text
4
```

We use:

```java
slow.next = slow.next.next;
```

This means:

```text
3.next = 4.next
```

So:

```text
3 → 5
```

The `4` node is skipped.

---

## Final Answer

```text
1 → 2 → 3 → 5
```

```text
[1,2,3,5]
```

---

## Complexity Analysis

### Time Complexity: O(n)

**Reason:** We traverse the linked list using the two pointers.

### Space Complexity: O(1)

**Reason:** We only use `slow`, `fast`, and `dummy` pointers. No extra array or linked list is created.

---

## Key Takeaway

The main idea is:

```text
Fast → n nodes ahead
Slow → node before the target
```

When `fast` reaches the end:

```text
slow.next
```

is the node that needs to be removed.

Remove it using:

```java
slow.next = slow.next.next;
```

### Remember

```text
Dummy Node
    ↓
Move Fast n steps
    ↓
Move Slow + Fast together
    ↓
Slow reaches node before target
    ↓
Skip Slow.next
```

**Two Pointers + Dummy Node = O(n) Time and O(1) Space**
