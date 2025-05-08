# 🔍 Dictionary Search Comparison Tool (AVL Tree vs Binary Search)

This Java console application compares two fundamental data structure search techniques:
- **AVL Tree Search**
- **Binary Search on a Sorted Array**

It's designed as an educational experiment to observe and compare the number of comparisons made when searching for words from a query file.

---

## 📦 Features

- Load a dictionary (`dict.txt`) and search queries (`query.txt`)
- Insert all dictionary entries into:
  - An **AVL Tree** (custom class)
  - A **TreeSet** (Java's built-in binary search tree)
- Search for words using both AVL and binary search
- Display the number of comparisons made for each word
- Write binary search comparisons to a file (`Comparisons.txt`)
- Run direct search comparisons to see which method is more efficient for a given word

---

## 📁 Files Needed

- `dict.txt` — A list of words, one per line (your dictionary).
- `queries.txt` — A list of words to search for (space- or line-separated).

Ensure these are placed in the same directory or provide the full path when prompted.

---

## 🚀 How to test

1. **Compile/Run the Program DictionarySearch.java**
And ensure that you first load files Dict.text, queries.txt by choosing option **1**
