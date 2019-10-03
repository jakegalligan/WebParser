# WebParser
### Author: Jake Galligan

## Introduction
WebParser is a console based application that allows users to input a URL and recieve an ordered list of the top 25 words contained on the webpage along with the number of times the word appears.

## Runing the application
### 1. Executable Jar File
Clone repository and create an executable jar file which will then be able to run the application. **NOTE:** Jar files can be sent directly to user upon request. To run jar file:
```bash
java -jar [name of jar file]
```

### 2. Within Eclipse
Clone respository and open the application within Eclipse. Open Main file and run application. The Eclipse console will then facilitate the application.
### 3. Command Line
Maven *must* be installed locally in order to run the application from the command line.Install Maven dependencies, then navigate to **target/classes** directory and run:
```bash
java Main
```

## Notes
### Author Notes
This was my first time working with Java and was a great learning experience in exploring the syntax and structure of Java as well as JSoup and its capabilities
### Challenges
Some challenges I had to address along the way:
1. I encountered that parentheses and punctuation marks would be attached to words when splitting the string into an array of strings. Had to use regex expressions to remove any unwanted characters 

2. Finding the best structure to store the word and its count. I settled on a hashmap as it allowed me to save a lot of processing power when updating the count for each word as I knew there would be lots of occurrences of certain words and looping each time to update it’s count would be expensive. However, I ran into an issue when trying to sort since hashmaps are unsorted by nature. I tried to look for other data structures to store the data but none seemed to be both inexpensive and sorted, so I stuck with Hashmaps and decided that since sorting would only occur once at the end it wouldn’t be too costly.

3. Since I was unable to use a standard for loop to iterate the hashmap I had to find a way to iterate through only the first 25 entries(therefore, a for each wouldn’t work).I found java “iterators” and they provided an efficient way to iterate through the map.


