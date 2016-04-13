# scala koans - scala 2.11 + scalatest 3

This is a port of the original [scalakoansexercises](https://bitbucket.org/dmarsh/scalakoansexercises) project to scala 2.11 and scalatest 3.

:interrobang:
> ###Definition of koan
> _a paradox to be meditated upon that is used to train Zen Buddhist monks to abandon ultimate dependence on reason and to force them into gaining sudden intuitive enlightenment_

## Usage
From within your sbt console, run:
```
> ;reload ;update ;clean ;compile
> ~ ;compile ;testOnly org.functionalkoans.forscala.Koans
```

This will make sbt continuously monitor file changes and compile and test your code when it detects any file changes.
When a test is pending or fails, a message will be printed to the console, stating which suite and test needs attention and stop right there.
As soon as you fix a test, it will pass and the next test will be checked. For each test fixed in this way, the suites will progress until you pass all exercises. 
