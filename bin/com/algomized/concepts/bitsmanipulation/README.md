## Bit Manipulation

### CrackingTheCodingInterviewC5Q4

Explain what the following code does: ((n & (n-1)) == 0).

A & B == 0 only occur is A and B does not have same one bit.

So for n that has multiple ones bit, ((n & (n-1)) == 1), for example:
   n = 100
   n - 1 = 1000
   So ((n & (n-1)) == 1)

Only when n is single one bit or zeros, ((n & (n-1)) == 0), for example:
   n = 1000
   n - 1 = 0111
   So ((n & (n-1)) == 0)
   
Hence, ((n & (n-1)) == 0) check whether n is power of 2 (or zero).