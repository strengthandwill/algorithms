## Brain Teasers

###5 Approaches
1. Examplify
2. Simplify and Generalize
3. Pattern Matching
4. Base Case and Build
5. Worst Case Balancing

### CrackingTheCodingInterviewC6Q1

Approach: Simplify and Generalize

You have 20 bottles of pills. 19 bottles have 1.0 gram pills, but one has pills
of weight 1.1 grams. Given a scale that provides an exact measurement, how
would you find the heavy bottle? You can only use the scale once.

1. Take 1 pill from 1st bottle, 2 pills from 2nd bottle, ... 
   i pills from ith bottle, ... 20 pills from 20th bottle and weight them.
   
2. Weight w is given by the formula below:

   w = (1 + 2 + ... (i - 1)) * 1.0g + i * 1.1g + ((i + 1) + (i + 2) + ... 20) * 1.0g
   w = (1 + 2 + ... 20) * 1.0g + i * 1.1g - i * 1.0g
   i = 10 * (w - 210)

So using the formula, the heavy bottle i can be determined from the weight w.


### CrackingTheCodingInterviewC6Q2

There is an 8x8 chess board in which two diagonally opposite corners have
been cut off. You are given 31 dominos, and a single domino can cover exactly
two squares. Can you use the 31 dominos to cover the entire board? Prove your
answer (by providing an example or showing why it's impossible).

1. Removing 1 block from each corner leaves 32 whites and 30 blacks block.

2. Each domino occupy two block, 1 white and 1 black.

3. Since there is not equal number of whites and blacks, any number of dominos can never 
   fit up the chess board. Hence, 31 dominos is not able to cover the entire board.
   
   
### CrackingTheCodingInterviewC6Q3

You have a five-quart jug, a three-quart jug, and an unlimited supply of water
(but no measuring cups). How would you come up with exactly four quarts of
water? Note that the jugs are oddly shaped, such that filling up exactly "half"of
the jug would be impossible.

1. Filled up five-quart jug to full.

2. Pour five-quart jug into three-quart jug to full.

3. Emptied three-quart jug.

3. Pour the remaining of five-quart jug (two quarts) into three-quart jug.

4. Filled up five-quart jug to full.

5. Pour five-quart jug to full.

6. Pour five-quart jug into three-quart jug until three-quart jug is full (one quart).
   Hence, five-quart jug have four quart remaining.


### CrackingTheCodingInterviewC6Q4
   
A bunch of people are living on an island, when a visitor comes with a strange
order: all blue-eyed people must leave the island as soon as possible. There will
be a flight out at 8:00pm every evening. Each person can see everyone else's
eye color, but they do not know their own (nor is anyone allowed to tell them).
Additionally, they do not know how many people have blue eyes, although they
do know that at least one person does. How many days will it take the blue-eyed
people to leave?

Approach: Base Case and Build

Let n be the number of people with blue eyes.

When n = 1, the person see all others do not have blue eyes, so he must have blue eye, 
so he left on the 1st night.

When n = 2, the two people see each other having blue eyes, n could be 1 or 2. Since no one 
left on the 1st night, n cannot be 1 so it must be 2, so they left on the 2nd night.

Hence, for n number of people with blue eyes will take n nights to leave and they will 
all left together on the same night.


### CrackingTheCodingInterviewC6Q5

There is a building of 100 floors. If an egg drops from the Nth floor or above, it
will break. If it's dropped from any floor below, it will not break. You're given two
eggs. Find N, while minimizing the number of drops for the worst case.

Approach : Worst Case Balancing

Let's traverse by x levels each steps, let x be 10.

Worst case 1: Floor is 10th, use the first egg to traverse 1 step to 10th level and the egg 
is broken. Use the second egg to traverse 9 steps to level by level to reach 10th to confirm. 
So total 1 + 9 = 10 steps.

Worst case 2: Floor is 20th, use the first egg to traverse 2 steps to 100th level and the egg 
is broken. Use the second egg to traverse 9 steps to level by level to reach 10th to confirm. 
So total 2 + 9 = 11 steps.

...

Worst case 10: Floor is 100th, use the first egg to traverse 10 steps to 100th level and the egg 
is broken. Use the second egg to traverse 9 steps to level by level to reach 10th to confirm. 
So total 10 + 9 = 19 steps.

Balancing: Need to balance the one more step for each of the traversal of the first egg, so one step
is required to be subtracted for each additional traversal of the first egg, to ensure all cases
maintain x steps.

So,

Worst case 1 => x levels: 1 step for 1st egg + (x - 1) steps for 2nd egg = x steps

Worst case 2 => x - 1 levels: 2 steps for 1st egg + (x - 2) steps for 2nd egg = x steps

...

Worst case x => 1 level: x steps for 1st egg + 0 step for 2nd egg = x steps 

Total levels  = x + (x - 1) + ... 1 = x (x + 1) / 2 = 100

Hence, x = 14.


### CrackingTheCodingInterviewC6Q6

There are 100 closed lockers in a hallway. A man begins by opening all 100
lockers. Next, he closes every second locker. Then, on his third pass, he toggles
every third locker (closes it if it is open or opens it if it is closed). This process
continues for 100 passes, such that on each pass i, the man toggles every ith
locker. After his 100th pass in the hallway, in which he toggles only locker #100,
how many lockers are open?

Approach 1

1. jth lockers toggle for ith iteration of i is a factor of j.
   e.g. Locker 15 will toggle for 1, 3, 5, 15
   
2. Locker will be opened if it has odd number of factors and closed is it has 
   even number of factors.
   
3. Only perfect squares have odd number of factors as the square root factors is only
   consider as one factor during the toggling.
   e.g. Locker will toggle for 1, 7, 49
   
4. There will be 10 perfect squares <= 100:

   1, 4, 9, 16, 25, 36, 49, 64, 81, 100
   
   So the number of lockers opened is 10.
          

Approach 2

Let's take 10 lockers, O for opened door and X for closed door.

<table>
	<tr><td>LK</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td></tr>  
	<tr><td>01</td><td>O</td><td>O</td><td>O</td><td>O</td><td>O</td><td>O</td><td>O</td><td>O</td><td>O</td><td>O</td></tr>
	<tr><td>02</td><td>O</td><td>X</td><td>O</td><td>X</td><td>O</td><td>X</td><td>O</td><td>X</td><td>O</td><td>X</td></tr>
	<tr><td>03</td><td>O</td><td>X</td><td>X</td><td>X</td><td>O</td><td>O</td><td>O</td><td>X</td><td>X</td><td>X</td></tr>
	<tr><td>04</td><td>O</td><td>X</td><td>X</td><td>O</td><td>O</td><td>O</td><td>O</td><td>O</td><td>X</td><td>X</td></tr>
	<tr><td>05</td><td>O</td><td>X</td><td>X</td><td>O</td><td>X</td><td>O</td><td>O</td><td>O</td><td>X</td><td>O</td></tr>
	<tr><td>06</td><td>O</td><td>X</td><td>X</td><td>O</td><td>X</td><td>X</td><td>O</td><td>O</td><td>X</td><td>O</td></tr>
	<tr><td>07</td><td>O</td><td>X</td><td>X</td><td>O</td><td>X</td><td>X</td><td>X</td><td>O</td><td>X</td><td>O</td></tr>
	<tr><td>08</td><td>O</td><td>X</td><td>X</td><td>O</td><td>X</td><td>X</td><td>X</td><td>X</td><td>X</td><td>O</td></tr>
	<tr><td>09</td><td>O</td><td>X</td><td>X</td><td>O</td><td>X</td><td>X</td><td>X</td><td>X</td><td>O</td><td>O</td></tr>
	<tr><td>10</td><td>O</td><td>X</td><td>X</td><td>O</td><td>X</td><td>X</td><td>X</td><td>X</td><td>O</td><td>X</td></tr>
</table>

From observation, for 10 lockers with 10 iterations, the opened doors are the squares numbers 
that are smaller and equal to 10:

1, 4, 9

Generalizing, for 100 lockers with 100 iterations, the opened doors are the squares number
that are smaller and equal to 100:

1, 4, 9, 16, 25, 36, 49, 64, 81, 100

Hence since there are 10 square numbers, the number of lockers opened are 10.