Hw 3 Summary

About Algorithm
To sort the array concurrently, we used CompletableFuture interface which submit async tasks to different threads. In this case each task will sort one half of the array recursively. The code includes two methods: sort() and parsort(). The former, similar to merge() method in normal mergesort, will first divide the array into two sorted half and merge them to one. The latter will just trigger sort() method in its lambda block.

About Expriment of optimal CUTOFF
I tried cutoff value from 20 to 2000 and plotted this graph:

![Alt text](hw3.png?raw=true "Graph")

From the graph we can see that as CUTOFF increases, the total running time decreases, almost in a reverse-proportional manner. Every time it doubles, like from 250 to 500 or from 500 to 1000, the running time dropped significantly. I think this is because the nature of mergesort algorithm: divide and conquer. Every time it becomes a division by power of 2 (1000, 500, 250), the number of stacks the program uses will decrease by one. Thus the time is saved. 
