# Window Management System
This project is part of Virginia Tech CS5040 course with Prof. Tessema Mengistu.

# Project Description
Many applications areas such as computer graphics, geographic information systems, and VLSI design require the ability to store and query a collection of rectangles. In 2D, typical queries include the ability to find all rectangles that cover a query point or query rectangle, and to report all intersections from among the set of rectangles. Adding and removing rectangles from the collection are also fundamental operations.

For this project, you will create a simple spatial database for handling inserting, deleting, and performing queries on a collection of rectangles. The data structure used to store the collection will be the Skip List (see the "Skip Lists" of the openDSA textbook (Chapter 25 (25.4) )for more information about Skip Lists). The Skip List fills the same role as a Binary Search Tree in applications that need to insert, remove, and search for data objects based on some search key such as a name. The Skip List is roughly as complex as a BST to implement, but it generally gives better performance since its worst case behavior depends purely on chance, not on the order of insertion for the data. Thus, the Skip List provides a good organization for answering non-spatial queries on the collection (in particular, for organizing the objects by name). However, as you will discover, it is difficult and inefficient for the Skip List to accomplish spatial queries.

# Project Requirements
## Invocation and Input/Output

The program will be invoked from the command-line as:

   %> java RectangleDB {commandFile}
where:  RectangleDB is then name of the program. commandFile is the name of the command file to read.  It is a text file. 
The program will read from the text file {command-file} a series of commands, with one command per line. The program terminates after reading the end of the file. The commands are free-format in that any number of spaces may come before, between, or after the command name and its parameters. All commands should generate the required output message. All output should be written to the standard output console.

The command file may contain any mix of the following additional commands. In the following description, terms in { } are parameters to the command.  The curly-brace characters '{' and '}' seen below will not actually be present. However, the output of several commands will include parentheses.

### Insert
Insert a rectangle named name with upper left corner (x, y), width w and height h. It is permissible for two or more rectangles to have the same name, and it is permissible for two or more rectangles to have the same spatial dimensions and position. The name must begin with a letter, and may contain letters, digits, and underscore characters. Names are case sensitive. A rectangle should be rejected for insertion if its height or width are not greater than 0. All rectangles must fit within the “world box” that is 1024 by 1024 units in size and has upper left corner at (0, 0). If a rectangle is all or partly out of this world box, it should be rejected for insertion.

| Command Format 	| insert {name} {x} {y} {w} {h} 	|
|---	|---	|
| Command Example 	| insert hello 2 5 1 5 	|
| Output on Success Example 	| Rectangle accepted: (hello, 2, 5, 1, 5) 	|
| Output on Failure Example 	| Rectangle rejected: (hello, 1020, 1020, 1, 7) 	|

### Remove-by-name
Remove the rectangle with name name. If two or more rectangle have the same name, then any one such rectangle may be removed. If no rectangle exists with this name, it should be so reported.

| Command Format 	| remove {name} 	|
|---	|---	|
| Command Example 	| remove someRect 	|
| Output on Success Example 	| Display Nothing 	|
| Output on Failure Example 	| Rectangle rejected: (rectangleB) 	|

### Remove-by-coords
Remove the rectangle with the specified dimensions. If two or more rectangles have the same dimensions, then any one such rectangle may be removed. If no rectangle exists with these dimensions, it should be so reported.

| Command Format 	| remove {x} {y} {w} {h} 	|
|---	|---	|
| Command Example 	| remove   2  5  1     5 	|
| Output on Success Example 	| Display Nothing 	|
| Output on Failure Example 	| Rectangle rejected: (2, 5, 1, 5) 	|
| Output on Failure Example2 	| Rectangle rejected: (-15, 5, 1, 5) 	|

### Region Search
Report all rectangles currently in the database that intersect the query rectangle specified by the regionsearch parameters. For each such rectangle, list out its name and coordinates. A regionsearch command should be rejected if the height or width are not greater than 0. However, it is (syntactically) acceptable for the regionsearch rectangle to be all or partly outside of the 1024 by 1024 world box.

| Command Format 	| regionsearch {x} {y} {w} {h} 	|
|---	|---	|
| Command Example 	| regionsearch  -900 5 5000 20 	|
| Output on Success Example 	| Rectangles intersecting region (-900, 5, 5000, 20):<br>(a, 45, 0, 10, 10)<br>(b, 400, 0, 100, 310) 	|
| Output on Failure Example 	| Display Nothing 	|

### Intersections
Report all pairs of rectangles within the database that intersect.

| Command Format 	| intersections 	|
|---	|---	|
| Command Example 	| intersections 	|
| Output on Success Example 	| Intersection pairs:<br>(goodRect, 5, 3, 56, 56 \| goodRect3, 25, 3, 6, 6 	|
| Output on Failure Example 	| Intersection pairs: 	|

### Search
Return the information about the rectangles(s), if any, that have name {name}.

| Command Format 	| search {name} 	|
|---	|---	|
| Command Example 	| search   mainRect 	|
| Output on Success Example 	| Rectangles found: (mainRect, 25, 3, 6, 6) 	|
| Output on Success Example2 (multiple matches) 	| Rectangles found: (mainRect, 25, 3, 6, 6)<br>Rectangles found: (mainRect, 111, 23, 16, 16) 	|
| Output on Failure Example 	| Rectangle not found: mainRect 	|

### Dump
Return a “dump” of the BST. The BST dump should print out each BST node (use the *in-order traversal* ). For each BST node, print that node’s depth and value (rectangle info). At the end, please print out the size of the BST.

| Command Format 	| dump 	|
|---	|---	|
| Command Example 	| dump 	|
| Output on Success Example 	| BST dump:<br>Node has depth 0, Value (name1, 4, 6, 2, 2)<br>Node has depth 1, Value (name2, 1, 0, 2, 4)<br>Node has depth 2, Value (name3, 1, 2, 1023, 4)<br>BST size is: 3 	|
| Output on Success Example2 	| BST dump:<br>Node has depth 0, Value (null)<br>BST size is: 0 	|
                              
