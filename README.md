A console implementation of Mastermind
======================================

This is a Clojure learning project. I choose the game <a href="http://en.wikipedia.org/wiki/Mastermind_(board_game)">Mastermind</a> since it is complicated enough to demonstrate the ease in which Clojure handles algorithmic situations, and simple enough for a Clojure n00b to approach comfortably.

### Rule variations
* I have chosen to remove the concept of losing the game, as it adds little value, and loses information. Think of it like golf, where you keep swinging until you get the ball in the hole.
* Four colors make up a code by default, but you can specify a different number

### How to build and run

Install [Leinigen](http://leiningen.org/)

    $ lein uberjar
    $ java -jar mastermind-1.0.0-SNAPSHOT-standalone.jar

Use `-h` flag to see usage information.

### Play options

There are currently three ways of interacting with this game:
1. You can play the game (with `-p`) trying to guess the code the program has chosen
2. You can test the game solver (with `-t`), setting the code, and watching the program discover the code
3. You can run the solver against all possible code to evaluate effectiveness and performance

### To be implemented
* The ability to vary the number of 'pegs'.
* Computation of effectiveness and performance metrics
