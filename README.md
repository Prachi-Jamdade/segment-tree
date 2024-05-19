# Segment Tree Introduction

Let's take an example where you're given a task to find a sum of elements lying inside an interval

```arr = [1, 6, 8, 5, 3, 16], range=[2, 5]```

We will traverse the array from the start index to the end and keep counting the sum. It will take time complexity of ```O(N)```.
The ```O(N)``` solution isn't good enough to find the ```O(log N)``` solution.

A solution to such problems? 
## Segment Tree

Segment Tree is a complete Binary Tree where every node has 2 parts - interval information and operation.

**Segment -> Perform a Query i.e. operation on a Range i.e. interval**

```arr = [4, 2, -3, 5, 9, 10, 13, 1]```

Segment Tree Representation of this -

![SegmentTree](https://github.com/Prachi-Jamdade/segment-tree/assets/94190717/0d36255e-21ab-46cf-a3be-fa4d25934aa4)


## **Perform a Query:**
Finding sum between range ```[2, 6]```: ```queryStartIndex = 2, queryEndIndex = 6```

**Case 1:** The current Node interval lies inside the query interval
[3, 5], nodeStartIndex = 3, nodeEndIndex = 5 

if ```(nodeStartIndex >= queryStartIndex) && (nodeEndIndex <= queryEndIndex)``` then return current node data

**Case 2:** The current Node interval lies outside the query interval
[7, 8], nodeStartIndex = 7, nodeEndIndex = 8

if ```(nodeStartIndex > queryEndIndex) && (nodeEndIndex < queryStartIndex)``` then return the default value i.e. 0

**Case 3:** Overlapping of current node interval and query interval
For ex. [0, 3] for [2, 3]

The result after performing a query on the left subtree + Result after performing a query on the right subtree.


**Time Complexity:** ```O(log N)``` where ```N``` is the total number of elements

## **Update a Query:**
Update element at index 4 to 15: index = 4, data = 15

**Case 1:** The index lies inside the interval
```(index >= nodeStartIndex && index <= nodeEndIndex)```

**Case 2:** If the index lies inside the interval then
If a leaf node is encountered, update the query with the current value
Else return the result after performing a query on the **left subtree** + the result after performing a query on the **right subtree**.

**Case 3:** The index lies outside the interval
```(index >= nodeStartIndex && index <= nodeEndIndex)``` is not true, just return the node data


**Time Complexity:** ```O(log N)``` where ```N``` is the total number of elements
