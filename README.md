test-repo
=========
George Vasilopoulos
i-docs test

Comments:
Implemented a naive solution just to get it in within the limited time.
It loads the whole files into memory.
Did not manage to test.

Q/A:

1. Assuming 200MB of memory and an average size of a character in UTF-8 of 4bytes then the total number of strings with
an average of 8 charactes would be 200*1024*1024/32

2. To increase the limit a solution which does not load the whole file in memory could be employed. 
For example sorting externally the files and comparing portions in memory.

3. The program is efficient for speed but can handle limited file sizes.

4. Using TreeSets to load the data should be pretty efficient and the intersection from google should be ok.
