TRAVELLING SALESMAN PROBLEM HEURISTIC SOLVERS
----------------------------------------------

I have implemented six different solution strategies for the well known travelling salesman problem (TSP). 
TSP is the problem of finding the shortest tour that covers all given cities such that the tour starts and ends at the same city.
	Solutions:
1. Exact Enumaration:	I try all possible tour combinations and find the shortest one.
2. Greedy Method:	The cities will be divided to two sets: Connected and NotConnected. 
At each step, you will add a city from the set NotConnected that is closest to the city that is most recently added to the set Connected.
3. Minimum Spanning Tree (MST) Method:	First, find MST. Then use path that is constructed with DFS.
4. 2OPT:	I start with a valid random solution and use 2OPT algorithm to find a better solution.
5. 3OPT:	I start again with a valid random solution and use 3OPT algorithm to find a better solution.
6. Triangle Hull:	Start with a three city that constitutes largest perimeter, then make cheap insertions until all cities have inserted.

