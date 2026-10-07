# LeetCode 49 — Group Anagrams

## 🔗 Problem

**LeetCode:** 49. Group Anagrams

### Problem Statement

Given an array of strings `strs`, group the **anagrams** together.

Two strings are anagrams if they contain the same characters with the same frequencies, but their order can be different.

Return the groups in any order.

---

# 💡 Example

### Example 1

```text
Input:
strs = ["eat","tea","tan","ate","nat","bat"]

Output:
[
    ["bat"],
    ["nat","tan"],
    ["ate","eat","tea"]
]
```

### Explanation

The following strings are anagrams:

```text
"eat" → e, a, t
"tea" → t, e, a
"ate" → a, t, e
```

All three contain:

```text
a → 1
e → 1
t → 1
```

Therefore, they belong to the same group.

Similarly:

```text
"tan"
"nat"
```

are anagrams.

`"bat"` has a different character frequency, so it forms its own group.

---

# 🚀 Approach

We use:

- `HashMap`
- Frequency array of size `26`
- A unique frequency-based key

The main idea is:

> **Anagrams always have the same frequency of every character.**

For every word, we count how many times each letter from `'a'` to `'z'` occurs.

Then we convert that frequency array into a `String` and use it as the HashMap key.

---

# 🧠 Why Frequency Array?

Consider:

```text
"eat"
```

The character frequencies are:

```text
a = 1
e = 1
t = 1
```

Now:

```text
"tea"
```

has exactly the same frequencies:

```text
a = 1
e = 1
t = 1
```

Even though the character order is different, the frequency array is identical.

Therefore, both strings get the same key.

---

# 🔑 Key Idea

We create:

```java
int[] freq = new int[26];
```

Each position represents one lowercase English letter.

```text
Index:
0 → a
1 → b
2 → c
3 → d
...
25 → z
```

For a character:

```java
freq[ch - 'a']++;
```

For example:

```text
ch = 'c'

'c' - 'a' = 2
```

So:

```text
freq[2]++
```



---

# 🔍 Code Explanation

## 1. Create HashMap

```java
HashMap<String, List<String>> map = new HashMap<>();
```

The HashMap stores:

```text
frequency key → list of anagrams
```

For example:

```text
"[1, 0, 0, 0, 1, ...]" → ["eat", "tea", "ate"]
```

The key represents the frequency of each character.

---

# 2. Traverse Every Word

```java
for (String word : strs) {
```

We process each string one by one.

Example:

```text
"eat"
"tea"
"tan"
"ate"
"nat"
"bat"
```

---

# 3. Create Frequency Array

```java
int[] freq = new int[26];
```

Initially:

```text
freq = [0, 0, 0, 0, ..., 0]
```

There are 26 positions because there are 26 lowercase English letters.

---

# 4. Count Characters

```java
for (char ch : word.toCharArray()) {
    freq[ch - 'a']++;
}
```

Suppose:

```text
word = "eat"
```

### `'e'`

```text
'e' - 'a' = 4
```

So:

```text
freq[4]++
```

### `'a'`

```text
'a' - 'a' = 0
```

So:

```text
freq[0]++
```

### `'t'`

```text
't' - 'a' = 19
```

So:

```text
freq[19]++
```

The resulting frequency array has:

```text
a → 1
e → 1
t → 1
```

---

# 5. Create the Key

```java
String key = Arrays.toString(freq);
```

The integer array is converted into a String.

For example, conceptually:

```text
[1, 0, 0, 0, 1, 0, ..., 1, ...]
```

This becomes the HashMap key.

Why?

Because two anagrams will produce exactly the same frequency array and therefore the same key.

---

# 6. Add Word to Its Group

```java
map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
```

This line does two things.

### If key doesn't exist

Create a new list:

```text
key → []
```

Then add the word:

```text
key → ["eat"]
```

### If key already exists

Use the existing list and add the word:

```text
key → ["eat", "tea"]
```

---

# 🧠 Understanding `computeIfAbsent`

This:

```java
map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
```

is basically equivalent to:

```java
if (!map.containsKey(key)) {
    map.put(key, new ArrayList<>());
}

map.get(key).add(word);
```

So:

```java
computeIfAbsent()
```

is just a shorter way to create the list when necessary.

---

# 🧪 Dry Run

Consider:

```text
strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
```

---

## Step 1 — `"eat"`

Frequency:

```text
a = 1
e = 1
t = 1
```

Key:

```text
K1
```

Map:

```text
K1 → ["eat"]
```

---

## Step 2 — `"tea"`

Frequency:

```text
a = 1
e = 1
t = 1
```

Same key:

```text
K1
```

Map:

```text
K1 → ["eat", "tea"]
```

---

## Step 3 — `"tan"`

Frequency:

```text
a = 1
n = 1
t = 1
```

Different key:

```text
K2
```

Map:

```text
K1 → ["eat", "tea"]
K2 → ["tan"]
```

---

## Step 4 — `"ate"`

Frequency:

```text
a = 1
e = 1
t = 1
```

Same as `"eat"`.

```text
K1 → ["eat", "tea", "ate"]
```

---

## Step 5 — `"nat"`

Frequency:

```text
a = 1
n = 1
t = 1
```

Same as `"tan"`.

```text
K2 → ["tan", "nat"]
```

---

## Step 6 — `"bat"`

Frequency:

```text
a = 1
b = 1
t = 1
```

New key:

```text
K3
```

Final map:

```text
K1 → ["eat", "tea", "ate"]

K2 → ["tan", "nat"]

K3 → ["bat"]
```

---

# 📊 Visualization

```text
                Frequency Key
                     │
        ┌────────────┼────────────┐
        ↓            ↓            ↓
      K1             K2           K3
       │              │            │
       ↓              ↓            ↓
 eat, tea, ate    tan, nat        bat
```

All strings with the same frequency key go into the same group.

---

# ❓ Why Not Sort Every Word?

Another common approach is:

```text
"eat" → "aet"
"tea" → "aet"
"ate" → "aet"
```

Then use `"aet"` as the key.

That works, but sorting each word takes:

```text
O(k log k)
```

where `k` is the length of the word.

Our frequency-array approach takes:

```text
O(k)
```

to count characters.

Therefore, the frequency approach is more efficient.

---

# ⚡ Important Line

The most important line is:

```java
freq[ch - 'a']++;
```

It converts a character into an array index.

For example:

```text
'a' → 0
'b' → 1
'c' → 2
'd' → 3
...
'z' → 25
```

So every character gets its own frequency counter.

---

# ⏱️ Complexity

Let:

- `n` = number of strings
- `k` = maximum length of a string

## Time Complexity

For every string, we scan all its characters:

```text
O(k)
```

For `n` strings:

```text
O(n × k)
```

`Arrays.toString(freq)` takes only 26 positions:

```text
O(26) = O(1)
```

Therefore:

```text
Time = O(n × k)
```

---

## Space Complexity

The frequency array:

```java
int[] freq = new int[26];
```

takes:

```text
O(26) = O(1)
```

But the HashMap stores all groups and their strings.

Therefore, the overall auxiliary/result-related space is:

```text
O(n × k)
```

depending on how space is counted for the returned groups.

The HashMap itself stores up to `n` keys/groups:

```text
O(n)
```

---

# 🧠 Key Takeaway

The core idea is:

```text
Anagrams
   ↓
Same character frequencies
   ↓
Same frequency array
   ↓
Same HashMap key
   ↓
Same group
```

### One-Line Idea

> **Count the frequency of each of the 26 letters and use that frequency pattern as a HashMap key to group all anagrams together.**

---

# ⭐ Pattern to Remember

This problem teaches an important HashMap pattern:

```text
Object
   ↓
Create a unique signature/key
   ↓
Use HashMap<Key, List<Object>>
```

Here:

```text
String
   ↓
Character frequency
   ↓
Arrays.toString(freq)
   ↓
HashMap<String, List<String>>
```

---

# 📌 Final Complexity

```text
Time Complexity:  O(n × k)
Space Complexity: O(n × k) for storing the groups
```

Where:

```text
n = number of strings
k = maximum length of a string
```

### Final Key Idea

```text
Same character frequencies = Anagrams
```