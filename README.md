In this Homework we are asked to write some code to simulate the behavior of Random Walk. I edited the move(), randomWalk() and distance() methods so the x and y can now reflect the actual position of the drunken man.

We are also expected to draw the relationship of distance to n. For this part I wrote few test cases (10 steps, 100 steps, 1000 steps, etc.) to simulate the actual mapping. Each scenario will be run 1000 times and result is the average distance of all times. The results are listed below:

Testing 10 steps random walk: 
The average distance after 10 steps is: 2.763710271748661

Testing 100 steps random walk: 
The average distance after 100 steps is: 9.026532546544143

Testing 1000 steps random walk: 
The average distance after 1000 steps is: 28.01914388555361

Testing 10000 steps random walk: 
The average distance after 10000 steps is: 86.63181985192213

Based on the observation, the stance is approximately proportional to sqrt(n), where n is the number of steps.

That's it,
Nan