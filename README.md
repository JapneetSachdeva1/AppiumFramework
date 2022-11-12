
# AppiumFramework
Uses Java as programming language and tests mobile applications using Appium

Dependencies used for creation of this framework:
1) Selenium support
2) Appium 2.0
3) Commons IO
4) Jackson
5) Extent Report
6) TestNG
7) Maven
8) Profiling using Maven

Structure of Framework :
1) Multilevel Inheritance is used to setup drivers and base actions
2) Driver are setup using beforeClass annotations which help in single implementation of it
3) Activites can be loaded using beforeMethod vice-versa
4) Reports can be generted using extent report helper class and testNGListeners class
5) Base class for webview testing
