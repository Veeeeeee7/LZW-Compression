# LZW Compression

You are to program an LZW Compression (https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch) algorithm in Java using VS Code.

Email a link here to your Github repository, one link per team.

Test Files: lzw-file1.txt Download lzw-file1.txt lzw-file2.txt Download lzw-file2.txt lzw-file3.txt Download lzw-file3.txt

Reminder: Each person will create the repository (using GitKraken, choosing the folder with their project in it as the folder).

Your table of codes CAN have a maximum size, after which you no longer add any more codes to the table; what size that is is up to you, and you may also choose to NOT have a maximum size. Both choices have benefits and drawbacks. You cannot use 8 extra bits as your size.

How you output the codes into the encoded file: also up to you.

What you initialize the table of codes with: up to you.

What data structures you use: up to you.

Lots of things: up to you!

Stealing Code:

-   You may borrow any publicly license code (from Google searching, not AI) for anything other than the algorithm itself (i.e. for number conversion, file reading, data alteration)

-   You have to write the main algorithm yourself

RUBRIC:

You get an E (Essentially Correct) if your encoder:

-   Encodes all five of my test files in a way that shrinks those files via an LZW algorithm (they should be shrinkable by LZW)

-   You get an M (mostly correct) if your encoder misses any of my files, or has confusing variable names or commenting.

-   You get a P (partially correct) if your encoder misses any of my files, AND has confusing variable names or commenting.

-   You get an I (incorrect) if your encoder does not work.
