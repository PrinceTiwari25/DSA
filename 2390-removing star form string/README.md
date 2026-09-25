# LeetCode 2390 — Removing Stars From a String

## Question

You are given a string `s` containing lowercase English letters and the character `*`.

Perform the following operation repeatedly:

* Choose a `*`.
* Delete the closest **non-star character to its left**.
* Delete the `*` itself.

Return the resulting string after performing all possible operations.

It is guaranteed that the input is valid, meaning there is always a non-star character to the left of every `*`.

### Example

**Input:**

```text
s = "leet**cod*e"
```

**Output:**

```text
"lecoe"
```

### Explanation

Start with:

```text
leet**cod*e
```

Process the characters from left to right.

First `*` removes `t`:

```text
lee*cod*e
```

Second `*` removes `e`:

```text
lecod*e
```

The next `*` removes `d`:

```text
lecoe
```

Therefore:

```text
Answer = "lecoe"
```

---

## LeetCode Link

https://leetcode.com/problems/removing-stars-from-a-string/

## Approach — Stack

We use a **Stack** to simulate the removal process.

A stack follows:

```text
LIFO
Last In → First Out
```

This is exactly what we need because every `*` removes the **most recently added non-star character**.

### Main Idea

While traversing the string:

* If the character is not `*`, add it to the stack.
* If the character is `*`, remove the top character from the stack.

Instead of using Java's `Stack` class, we use:

```java
StringBuilder
```

as a stack.

This works because `StringBuilder` allows us to:

```text
append()       → push
deleteCharAt() → pop
```

---

## Variables

```text
stack → stores characters that have not been removed
ch    → current character
```

The `StringBuilder` acts like:

```text
Stack
 ├── top
 ├── character
 ├── character
 └── bottom
```

---

## Steps

1. Create an empty `StringBuilder`.
2. Traverse every character of the string.
3. If the character is not `*`:

   * Add it to the `StringBuilder`.
4. If the character is `*`:

   * Remove the last character from the `StringBuilder`.
5. After processing the complete string, return the `StringBuilder`.

---

## Code

```java
class Solution {
    public String removeStars(String s) {
        StringBuilder stack = new StringBuilder();

        for(char ch : s.toCharArray()){
            if(ch == '*'){
                stack.deleteCharAt(stack.length()-1);
            }else{
                stack.append(ch);
            }
        }
        return stack.toString();
    }
}
```

## Dry Run

### Input

```text
s = "leet**cod*e"
```

We process the string from left to right.

---

### Step 1

Character:

```text
l
```

It is not `*`.

Add it:

```text
stack = "l"
```

---

### Step 2

Character:

```text
e
```

Add it:

```text
stack = "le"
```

---

### Step 3

Character:

```text
e
```

Add it:

```text
stack = "lee"
```

---

### Step 4

Character:

```text
t
```

Add it:

```text
stack = "leet"
```

---

### Step 5

Character:

```text
*
```

`*` means remove the most recently added character.

Current stack:

```text
leet
   ↑
  top
```

Remove `t`:

```text
stack = "lee"
```

---

### Step 6

Character:

```text
*
```

Remove the most recently added character:

```text
stack = "lee"
             ↑
```

Remove `e`:

```text
stack = "le"
```

---

### Step 7

Character:

```text
c
```

Add it:

```text
stack = "lec"
```

---

### Step 8

Character:

```text
o
```

Add it:

```text
stack = "leco"
```

---

### Step 9

Character:

```text
d
```

Add it:

```text
stack = "lecod"
```

---

### Step 10

Character:

```text
*
```

Remove the top character:

```text
d
```

So:

```text
stack = "leco"
```

---

### Step 11

Character:

```text
e
```

Add it:

```text
stack = "lecoe"
```

---

## Final Result

```text
"lecoe"
```

Therefore:

```text
Answer = "lecoe"
```

---

# Stack Visualization

For:

```text
leet**cod*e
```

The process can be visualized as:

```text
l       → [l]
e       → [l,e]
e       → [l,e,e]
t       → [l,e,e,t]

*       → [l,e,e]
*       → [l,e]

c       → [l,e,c]
o       → [l,e,c,o]
d       → [l,e,c,o,d]

*       → [l,e,c,o]

e       → [l,e,c,o,e]
```

Final stack:

```text
[l,e,c,o,e]
```

Result:

```text
"lecoe"
```

---

# Why Does `StringBuilder` Work as a Stack?

A stack needs two main operations:

```text
Push → Add an element
Pop  → Remove the top element
```

With `StringBuilder`:

### Push

```java
stack.append(ch);
```

Adds the character to the end.

### Pop

```java
stack.deleteCharAt(stack.length() - 1);
```

Removes the last character.

Therefore:

```text
StringBuilder
      ↓
  Stack behavior
```

---

# Why Do We Remove `stack.length() - 1`?

The last character represents the **top of the stack**.

For example:

```text
stack = "abcd"
```

Indexes:

```text
a → 0
b → 1
c → 2
d → 3
```

The last character is:

```text
stack.length() - 1
= 4 - 1
= 3
```

So:

```java
stack.deleteCharAt(stack.length() - 1);
```

removes:

```text
d
```

leaving:

```text
"abc"
```

---

# Why Is This a Stack Problem?

Every `*` removes the **closest character to its left** that has not already been removed.

This means the most recently added character is removed first.

That is exactly:

```text
LIFO
Last In → First Out
```

Example:

```text
a b c *
    ↑
    c is removed
```

Then:

```text
a b *
  ↑
  b is removed
```

So a stack is a natural solution.

---

# Complexity

## Time Complexity

```text
O(n)
```

We traverse the string once.

For every character, we perform either:

```text
append()
```

or:

```text
deleteCharAt()
```

Therefore, the overall time complexity is:

```text
Time = O(n)
```

## Space Complexity

```text
O(n)
```

In the worst case, there are no stars, so all characters are stored in the `StringBuilder`.

For example:

```text
s = "abcdef"
```

The stack stores all `6` characters.

Therefore:

```text
Space = O(n)
```

---

# Key Idea

```text
Character
    ↓
Is it '*'?
  ↙       ↘
 No       Yes
 ↓          ↓
Push       Pop
 ↓          ↓
Continue   Continue
```

Or simply:

```text
Normal character → append()
'*'              → delete last character
```

The most important idea is:

```text
* = remove the most recently added character
```

which makes this a **Stack** problem.

---

# Final Complexity

```text
Time  : O(n)
Space : O(n)
```

**Pattern Used:** Stack / StringBuilder
