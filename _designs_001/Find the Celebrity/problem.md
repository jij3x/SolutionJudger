Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of others.

Now you want to find out who is the celebrity or verify that there is not one. The only thing you are allowed to do is to ask questions like "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

Formally, you are given a helper function bool knows(a, b) which tells you whether A knows B and you need to implement a function int findCelebrity(n) by calling knows as few as possible (in the asymptotic sense).

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
