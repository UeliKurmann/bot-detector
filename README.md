# bot-detector

## Maven Dependency
```
<dependency>
  <groupId>ch.javacamp.bot-detector</groupId>
  <artifactId>bot-detector</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```
## Quickstart
### Detect a bot without verification
````java
package ch.javacamp.botdetector.examples;

import ch.javacamp.botdetector.Assessment;
import ch.javacamp.botdetector.Bots;
import ch.javacamp.botdetector.RequestDescriptor;

public class DetectionExample {

    public static void main(String[] args) {
        RequestDescriptor descriptor = RequestDescriptor.create("66.249.79.195", "... Googlebot/2.1; ...");
        Assessment actual = Bots.detector().detect(descriptor);
        if(actual.isBot()){
            System.out.println("a bot...");
        }
    }
}

````
### Detect a bot with identification and verification
```java
package ch.javacamp.botdetector.examples;

import ch.javacamp.botdetector.BotDescription;
import ch.javacamp.botdetector.Bots;
import ch.javacamp.botdetector.RequestDescriptor;

import java.util.Optional;

public class DetectionIdentificationExample {

    public static void main(String[] args) {
        RequestDescriptor descriptor = RequestDescriptor.create("66.249.79.195", "... Googlebot/2.1; ...");
        Optional<BotDescription> actual = Bots.detector().detect(descriptor).identifyVerified();

        actual.ifPresent(a -> System.out.println(a.name()));
        actual.ifPresent(a -> System.out.println(a.verification()));
    }
}
```
## List of Verification Rules
The available verification rules can be seen here: 
[Link](https://github.com/UeliKurmann/bot-detector/tree/main/src/main/java/ch/javacamp/botdetector/impl/verifiers)
