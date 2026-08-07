
<h2><a href="https://leetcode.com/problems/majority-element">Majority Element</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>Given an array <code>nums</code> of size <code>n</code>, return <em>the majority element</em>.</p>

<p>The majority element is the element that appears more than <code>&lfloor;n / 2&rfloor;</code> times. You may assume that the majority element always exists in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,1,1,1,2,2]
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that a majority element will exist in the array.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:</strong> Could you solve the problem in linear time and in <code>O(1)</code> space?

## Solution

### Approach: Boyer-Moore Voting Algorithm

The majority element appears **more than ⌊n / 2⌋ times**, so it can never be completely canceled out by the other elements.

We maintain two variables:

- `ans` → stores the current majority candidate.
- `freq` → stores the vote count of the current candidate.

### Algorithm

1. Initialize `freq = 0` and `ans = 0`.
2. Traverse the array:
   - If `freq == 0`, make the current element the new candidate (`ans = nums[i]`).
   - If `nums[i] == ans`, increment `freq`.
   - Otherwise, decrement `freq`.
3. After the traversal, `ans` contains the majority element.



## Dry Run

**Input**

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

| i | nums[i] | ans | freq |
|---|---------|-----|------|
| 0 | 2 | 2 | 1 |
| 1 | 2 | 2 | 2 |
| 2 | 1 | 2 | 1 |
| 3 | 1 | 2 | 0 |
| 4 | 1 | 1 | 1 |
| 5 | 2 | 1 | 0 |
| 6 | 2 | 2 | 1 |

**Output**

```text
2
```

---

## Complexity Analysis

### Time Complexity: **O(n)**

**Reason:** We traverse the array only once.

### Space Complexity: **O(1)**

**Reason:** Only two extra variables (`ans` and `freq`) are used.
