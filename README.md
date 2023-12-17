# My solution to [Advent of Code - Day 6](https://adventofcode.com/2023/day/6) in Clojure!

I decided to challenge myself and attempt to write a solution in a programming language I am not accustomed to. 

When I read the problem description, I immediately thought that a functional language with higher order functions on lists would be ideal for it. 

Hence, I picked [Clojure (a modern Lisp for the JVM)](https://clojure.org/index). I've gotta say that the fact that the syntax relies excessively on parentheses and on the Polish/prefix notation threw me off at first. Haskell definitely has clearer syntax.


Problem description: https://adventofcode.com/2023/day/6


## Solution description

To find the distances which are better for the provided one, I calculate the median for the time race and then multiply all possible button time holds from 0 to the median times the remaining time i.e. in python pseudocode i*(t-i) for i in range(0, median(t)) where i*(t-i) > distance record. Then I multiply by two the number of better distances found to get the number of ways to beat the record.
