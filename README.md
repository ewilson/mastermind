A console implementation of Mastermind
======================================

This is a Clojure learning project. I choose the game <a href="http://en.wikipedia.org/wiki/Mastermind_(board_game)">Mastermind</a> since it is complicated enough to demonstrate the ease in which Clojure handles algorithmic situations, and simple enough for a Clojure n00b to approach comfortably.

### Example:

The following is an example game. I entered the code manually, and the solver made the guesses automatically.

    Enter code
    ryyb
    --------------
    |VROV|  |O   |
    --------------
    |GYVB|  |XX  |
    --------------
    |YYRB|  |XXOO|
    --------------
    |RYYB|  |XXXX|

An `X` indicates that some peg is the correct color and position. An `O` indicates that some peg is correct but in the wrong position.

So the first line is indicating that the `R` in the second position is a correct peg, but incorrectly placed, etc.

### Rule variations
* I have chosen to remove the concept of losing the game, as it adds little value, and loses information. Think of it like golf, where you keep swinging until you get the ball in the hole.
* Four colors make up a code by default, but you can specify a different number

### How to build and run
Install [Leinigen](http://leiningen.org/)

    $ lein uberjar
    $ java -jar mastermind-1.0.0-SNAPSHOT-standalone.jar

Use `-h` flag to see usage information.

### Scope
There are currently three ways of interacting with this game:
1. You can play the game (with `-p`) trying to guess the code the program has chosen.
2. You can test the game solver (with `-t`), setting the code, and watching the program discover the code.
3. You can run the solver against all possible code to evaluate effectiveness and performance.

### Solver algorithm
I solve the puzzle in the simplest way that I know: choosing a random code from the list of all of the possible codes that are still possible, based on the previous results. For example: If the first round is the following:

    ------------
    RRBB  |O   |
    ------------

then all future guesses must be consistent with this, meaning:
* No guess will contain two reds or two blues
* No guess will contain both red and blue
* No guess will contain red in the first or second spot, or blue in the third or fourth spot
* All guesses will contain a red or a blue

### Design 

I have chosen to put the assiging of `X` and `O`s to guesses in the category of 'game mechanics' (namespace `wilsonericn.mm`) rather than as an action performed by a player, since it is entirely determanistic. The only disadvantage of this approach is a lack of transparency when a human is setting the code. This seems more than offset by the convenience of seeing the solver in action without having to evaluate and respond to each guess.

### To be implemented
* Computation of effectiveness and performance metrics
* Performance is currently unacceptable, particularly as pegs increase. Improvement is planned
