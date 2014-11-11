The goals of this module are:

* Introduce unit and function testing in the context of Map-Reduce
* HBase development with Scalding, SpyGlass.  
* Present the Test Driven Development methodology

The _scalatest-maven-plugin_ and the _maven-surefire-plugin_ ensure that we can execute
our integration and unit tests with just:

    basicPipes $ mvn test
    
    
All HBase development is done against a local HBase instance running in kiji-bento-ebi box(kiji.org)