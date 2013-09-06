L20n Java implementation
========================

This is a first try of a Java implementation for L20n.

L20n is a new way to localize software, brought by Mozilla. For more
information, visit [L20n.org](http://l20n.org).

Current implementation
----------------------

The current implementation is at the stage of a proof of concept. Here is what
currently works:

 - Declaring resources and compiling them.
 - Simple entities with only strings.
 - Entites with hashes (even nested).
 - Basic usage of indexes made of strings only.
 
There are some limitations to the current implementation:

 - It currently manages a single have a single locale named "default", there is no real handling of multiple locales.
 - The index implementation does not use real expressions. It just understands plain strings.
 
Missing features
----------------

A lot is still to be done to catch up with the specification:

 - Real implementation of expressions.
 - Complex strings.
 - Allowing to provide context data when retrieving a value.
 - Real management of locales.
 - ...
 
Java-specific features
----------------------

Features that would helpful in the context of Java could be added in the future,
when the implementation is complete:

 - Provide tools helping to move away from Properties files.
 - Integration with Spring/Spring MVC.
 - Async implementation.

Building
--------

l20n-java requires Java 6 or above. The project itself is meant to be compatible with Java 6.

To run the tests, from the command line run:

    ./gradlew test

Or under Windows :

    gradlew.bat test
