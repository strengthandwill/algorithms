## Mathematics and Probability

### Dependent Events
1. P(A ^ B) = P(A) * P(B | A)
2. P(A V B) = P(A) + P(B) - P(A ^ B)


### Independent Events
3. P(A ^ B) = P(A) * P(B), since P(B | A) = P(B)


### Mutually Exclusive Events
4. P(A V B) = P(A) + P(B), since P(A ^ B) = 0


### CrackingTheCodingInterviewC7Q1

P(G1) = p
P(G2) = p^3 + 3 * p^2 * (1 - p)
      = p^3 + 3p^2 - 3p^3
      = 3p^2 - 2p^3

For G1 to have higher chance, P(G1) > P(G2)
Therefore,
p > 3p^2 - 2p^3
1 > 3p - 2p^2
2p^2 - 3p + 1 > 0
(2p - 1)(p - 1) > 0
p < 1/2 or p > 1 (N.A.)
Hence, p < 1/2.


### CrackingTheCodingInterviewC7Q2

For n = 3,
1. LLL (Same direction)
2. LLR
3. LRL
4. LRR
5. RLL
6. RLR
7. RRL
8. RRR (Same direction)
Total combinations = 2^3 = 8
No collision combinations = 2
P(No collision) = 2/8 = 1/4
P(Collision) = 1 - 1/4 = 3/4

For n,
Total combinations = 2^n
No collision combinations = 2
P(No collision) = 2 / 2^n = 2^-(n-1)
P(Collision) = 1 - 2^-(n-1)