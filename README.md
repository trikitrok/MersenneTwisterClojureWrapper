# learning-mersenne-rand

Testing to learn how to make a wrapper for MersenneTwister.java created by [Sean Luke](https://cs.gmu.edu/~sean/research/)

The project uses [Midje](https://github.com/marick/Midje/).

## How to run the tests

`lein midje` will run all tests.

`lein midje namespace.*` will run only tests beginning with "namespace.".

`lein midje :autotest` will run all the tests indefinitely. It sets up a
watcher on the code files. If they change, only the relevant tests will be
run again.
