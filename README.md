# OOP_2bar-oria 

# Design and implementation of directed and weighted graphs 

![This is an image](https://user-images.githubusercontent.com/92825016/145035678-cd125e45-64d7-4055-91bb-646ddfbf99ba.png)  

## Getting Started
**libraries:**
1. import com.google.gson.Gson;
2. import com.google.gson.GsonBuilder;
3. import java.io.File;
4. import java.io.FileNotFoundException;
5. import java.io.FileReader;
6. import java.io.PrintWriter;
7. import java.util.HashMap;
8. import java.util.List;
9. import javax.swing.*;
10. import java.awt.*;
11. import java.awt.event.ActionEvent;
12. import java.awt.event.ActionListener;

## class
1. Graph - this class create object from type graph.
2. Node - this class create object from type node.
3. Edge - this class create object from type edge.
4. Ex2 -  this class the linked to class MyGui,my_Algo and create DirectedWeightedGraph  .
5. my_Algo - this class include  function that we use on graph.
6. jsonToFile - in this class we create  json object from json file. 
7. MyGui - graphic interface.


## uml :
![image](https://user-images.githubusercontent.com/92825016/145040004-afc75f74-b14a-4bd6-8967-52d6488b4aa3.png)  


## my_Algo - function:
#### **init()**
Inits the graph on which this set of algorithms operates on.

#### **DirectedWeightedGraph()**
Returns the underlying graph of which this class works.

#### **Copy()**
Computes a deep copy of this weighted graph.

#### **isConnected()** 
Returns true if and only if (iff) there is a valid path from each node to each
other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
 - this function based on **DAG** algorithm. 
1. Use of DFS algorithm.
2. Topological classification
3. Use of DFS algorithm , Choosing the largest value we got from 2 .
4. The result of the algorithm activated in step 3 is forest. Each of the trees in the forest is one of the well-connected elements in the graph, and they all appear in it.
* This site was built using [Wikipedia Pages](https://en.wikipedia.org/wiki/Directed_acyclic_graph).
#### **shortestPathDist()**
Computes the length of the shortest path between src to dest
Note: if no such path --> returns -1.
 - this function based on **Diastra** algorithm. 
0. For each vertex, it is marked whether they visited it or not and what is the distance from the vertex of the source, which we will mark in S. At first all the codes are marked as not visited, and distance is defined as infinity.
Algorithm loop:
1. As long as there are codes we did not visit:
2. Mark X (the current vertex. In this first iteration the vertex of the source S) as the vertex visited.
3. For any code that is X and we have not yet visited it:
Y is updated so that its distance is equal to the minimum value between two values: between the current distance, the weight of the arc connecting X and Y and the distance between S and X.
4. Make a new vertex X according to a code that this distance from the source node S is the shortest of all the vertices in the graph that we have not yet visited.
* This site was built using [Wikipedia Pages](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm).
#### **shortestPath()**
Computes the the shortest path between src to dest - as an ordered List of nodes:
src--> n1-->n2-->...dest
Note if no such path --> returns null;

This function is based on the function **shortestPathDist()** and return shorter List of nodes.

#### **center()**
 Finds the NodeData which minimizes the max distance to all the other nodes.
 Assuming the graph isConnected, elese return null. 
 return the Node data to which the max shortest path to all the other nodes is minimized
1. for all node in the graph we find the biggest **shortestPathDist()**.
2. we find the node that the smaller  biggest **shortestPathDist()** .
3. this node is the center node. 
* This site was built using [Wikipedia Pages]( https://en.wikipedia.org/wiki/Graph_center).

#### **tsp()**
Computes a list of consecutive nodes which go over all the nodes in cities.
the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
the lower the better.
  - this function based on **greedy** algorithm. 
1. we choosing start node and we find the next node  with the shorter distans .
2. for the node that we found (1) we found agen the next node  with the shorter distans and repeating .
3. from the last node we found the shorter distans to start node. 
* This site was built using [Wikipedia Pages]( https://en.wikipedia.org/wiki/Travelling_salesman_problem).

#### **save()**
 Saves this weighted (directed) graph to the given
 file name - in JSON format.
#### **load()**
This method loads a graph to this graph algorithm.
param file - file name of JSON file

## Run times :

**Building large graphs:**

- 1,000 Vertices 10,000 Edges: 153 ms
- 10,000 Vertices 100,000 Edges: 511 ms
- 100,000 Vertices 1,000,000 Edges: 5 sec 422 ms

**Running Algorithms:**
**isConnected + load file**:

- 1,000 Vertices 10,000 Edges: 185 ms
- 10,000 Vertices 100,000 Edges: 579 ms
- 100,000 Vertices 2,000,000 Edges: 5 sec 708 ms

**shorterpas + load file**:

- 1,000 Vertices 10,000 Edges: 201 ms
- 10,000 Vertices 100,000 Edges: 1 min 216 sec
- 100,000 Vertices 1,000,000 Edges: timeout

## GUI - graphic interface :
When you open 'GUI' from Ex2 class will open panel thet creat the graph from the json file thet you laod:
![image](https://user-images.githubusercontent.com/92825016/146669687-37d49901-5646-43c1-9d24-3980251b31e1.png)

in menu bar you can chose to laod other graph or to operate the functuion(from my algo) on the graph.
![image](https://user-images.githubusercontent.com/92825016/146669762-5f947775-d11b-49cc-b72a-2cb5c2e9e388.png)

![image](https://user-images.githubusercontent.com/92825016/146669783-a6cb445f-76cf-4a75-9a89-54a1de17af95.png)

When you finish you can to save the graph or to exit from 'GUI'.

**BarNahmias&OriaZadock**
