Hw 3 Summary

About Algorithm
To sort the array concurrently, we used CompletableFuture interface which submit async tasks to different threads. In this case each task will sort one half of the array recursively. The code includes two methods: sort() and parsort(). The former, similar to merge() method in normal mergesort, will first divide the array into two sorted half and merge them to one. The latter will just trigger sort() method in its lambda block.

About Expriment of optimal CUTOFF
I tried cutoff value from 20 to 2000 and plotted this graph:
