The results shown below show that the most efficient way to run the search
was when the number of threads matches the number of CPUs in this case 8.
Starting from 1 thread until 4 threads we see great improvement. The reason
being that there are more CPU resources available to search for the string
in the files. Once the number of threads increases beyond 8 the re is no
improvement in the performace. Actually the latency increases this is
probably due to the context switching between the threads that takes place
due to having more threads than cores. 

The design of the search was done using some of the most efficient technologies
available in Java 8. In this case Java NIO was used in the search mainly two
components: FileChannel and DirectoryStream. Those components use memory more
efficiently but letting the OS do most of the work and avoiding loading the
whole file in memory. It shows that eventhough there are very large files under
the windows directory the memory usage was under 0.5 GB. The other reason for
this is the search strategy implemented was an efficient one: Knuth-Morris-Pratt.
This prevented loading the whole file in memory and just using an stream of 
upto 1024 bytes to process each file. A total of 150K files were searched with
a combined size of 31GB a the memory stayed under 0.5 GB.

Multiple concurrent facilites were used from the concurrent package in Java
to ensure that the files could be processed and search in parallel. From the
Designed point of view the Software was design in components that encapsulated
their domain responsability.

Total files/directories found: 180959
Total files/directories un-accessible: 433
Files searched:     : 150120 - Total MB searched   : 31638 - Text found:         : 1069 - Cores count         : 8

1 Threads
Used Memory:        192 MB - Free Memory:        854 MB
Time elapsed (seconds): 207 - Search Rate MB/sec: : 152

2 Threads
Used Memory:        477 MB - Free Memory:       1042 MB
Time elapsed (seconds): 121 - Search Rate MB/sec: : 261

4 Threads
Used Memory:        660 MB - Free Memory:        848 MB
Time elapsed (seconds): 25 - Search Rate MB/sec: : 1265

8 Threads
Used Memory:        277 MB - Free Memory:       1218 MB
Time elapsed (seconds): 18 - Search Rate MB/sec: : 1757

16 Threads
Used Memory:        767 MB - Free Memory:        716 MB
Time elapsed (seconds): 22 - Search Rate MB/sec: : 1438

32 Threads
Used Memory:        157 MB - Free Memory:       1259 MB
Time elapsed (seconds): 27 - Search Rate MB/sec: : 1171

64 Threads
Used Memory:        417 MB - Free Memory:       1059 MB
Time elapsed (seconds): 29 - Search Rate MB/sec: : 1090
