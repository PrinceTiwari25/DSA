# LeetCode 17 – Letter Combinations of a Phone Number

**Problem Link:** https://leetcode.com/problems/letter-combinations-of-a-phone-number/

## 1. Problem Statement

You are given a string `digits` containing digits from `2` to `9`.

Each digit represents some letters, just like on a phone keypad.

Return all possible letter combinations that the number could represent.

### Phone Keypad Mapping

| Digit | Letters |
|---|---|
| 2 | abc |
| 3 | def |
| 4 | ghi |
| 5 | jkl |
| 6 | mno |
| 7 | pqrs |
| 8 | tuv |
| 9 | wxyz |

**Example:**

Input:
```java
digits = "23"
```

Output:
```java
["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

There are 3 letters for digit `2` and 3 letters for digit `3`.

Total combinations = 3 × 3 = 9.

---

## 2. Approach – Recursion

We use **recursion** to generate all possible combinations.

### Main Logic

1. Start from index `0`.
2. Recursively solve the remaining digits.
3. Get the letters corresponding to the current digit.
4. Add each current letter to every combination returned by recursion.
5. Return the final list.

**Important:** This code processes digits from right to left while building the combinations.

---


---

## 4. Line-by-Line Explanation

### A. Main Function

```java
public List<String> letterCombinations(String digits)
```
- Takes the digit string as input.
- Returns a list of all possible combinations.

```java
if(digits.length()==0)
    return new ArrayList<>();
```
- If input is empty, return an empty list.
- No digits means no combinations.

```java
return getLetterCombinations(digits,0);
```
- Calls the recursive function.
- `0` represents the starting index.

### B. Phone Keypad Mapping

```java
String[] codes = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
```

This array stores the letters for each digit.

For example:
- `codes[2]` → `"abc"`
- `codes[3]` → `"def"`
- `codes[7]` → `"pqrs"`

### C. Recursive Function

```java
public List<String> getLetterCombinations(String digits, int idx)
```
- `digits` = original input.
- `idx` = current index being processed.
- Returns all combinations possible from the current index onward.

### D. Base Case

```java
if(idx == digits.length()){
    List<String> baseResult = new ArrayList<>();
    baseResult.add("");
    return baseResult;
}
```

When `idx` reaches the length of the string:
- Recursion stops.
- Creates a list.
- Adds an empty string `""`.
- Returns that list.

**Why add an empty string?**

It acts as a starting point so the previous recursive calls can add their letters to it.

### E. Recursive Call

```java
List<String> recResult = getLetterCombinations(digits, idx + 1);
```

- Moves to the next digit.
- Solves the remaining digits first.
- Stores their combinations in `recResult`.

### F. Get Current Digit

```java
char ch = digits.charAt(idx);
```

Gets the character at the current index.

For `"23"`:
- `idx = 0` → `'2'`
- `idx = 1` → `'3'`

```java
String code = codes[ch - '0'];
```

Converts the character digit into an integer index.

Example:
- `ch = '2'`
- `'2' - '0' = 2`
- `codes[2] = "abc"`

### G. Nested Loops

```java
for(String recString: recResult){
    for(int i = 0; i < code.length(); i++){
        char chCode = code.charAt(i);
        myResult.add(chCode + recString);
    }
}
```

**Outer loop:**
- Visits every combination returned by recursion.

**Inner loop:**
- Visits every letter of the current digit.

```java
char chCode = code.charAt(i);
```
Gets one letter from the current digit.

```java
myResult.add(chCode + recString);
```
Adds the current letter at the beginning of the recursive combination.

Example:
- `chCode = 'a'`
- `recString = "d"`
- Result = `"ad"`

### H. Return Result

```java
return myResult;
```

Returns all combinations generated at the current recursive level.

---

## 5. Dry Run

**Input:**
```java
digits = "23"
```

The recursion first reaches the last index.

### Step 1: Recursive Calls

```java
getLetterCombinations("23", 0)
    getLetterCombinations("23", 1)
        getLetterCombinations("23", 2)
```

At `idx = 2`:

```java
baseResult.add("");
return baseResult;
```

So:

```java
recResult = [""]
```

### Step 2: Process Digit '3'

At `idx = 1`:

```java
ch = '3'
code = "def"
```

`recResult = [""]`

| Current letter | recString | New combination |
|---|---|---|
| d | "" | d |
| e | "" | e |
| f | "" | f |

Now:

```java
myResult = ["d", "e", "f"]
```

Return this list.

### Step 3: Process Digit '2'

At `idx = 0`:

```java
ch = '2'
code = "abc"
```

Now:

```java
recResult = ["d", "e", "f"]
```

Nested loops generate:

| Current letter | recString | New combination |
|---|---|---|
| a | d | ad |
| a | e | ae |
| a | f | af |
| b | d | bd |
| b | e | be |
| b | f | bf |
| c | d | cd |
| c | e | ce |
| c | f | cf |

Final result:

```java
["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

---

## 6. Recursion Flow

```text
getLetterCombinations("23", 0)
            |
            v
getLetterCombinations("23", 1)
            |
            v
getLetterCombinations("23", 2)
            |
            v
       Base Case
        [""] 
            |
            v
       Process '3'
      ["d","e","f"]
            |
            v
       Process '2'
            |
            v
["ad","ae","af",
 "bd","be","bf",
 "cd","ce","cf"]
```

---

## 7. Time and Space Complexity

Let:
- `n` = number of digits.
- `k` = maximum number of letters for one digit (at most 4).

### Time Complexity

**O(n × 4ⁿ)**

- At most `4ⁿ` combinations are generated.
- Each combination contains `n` characters.
- Therefore, generating and storing all combinations takes O(n × 4ⁿ) time.

### Space Complexity

**O(n × 4ⁿ)** including the output list.

- Output stores up to `4ⁿ` strings.
- Each string has length `n`.
- Recursive call stack takes O(n) additional space.

---

## 8. Important Interview Points

1. This problem uses recursion to generate combinations.
2. `codes[]` stores the phone keypad mapping.
3. `idx` represents the current digit index.
4. Base case returns a list containing an empty string.
5. Recursive call processes the remaining digits first.
6. Nested loops combine current letters with recursive results.
7. `ch - '0'` converts a digit character into an integer.
8. Maximum 4 letters are available for one digit.

### Final Concept

**Recursion solves the remaining digits first, and then the current digit's letters are added to every returned combination.**