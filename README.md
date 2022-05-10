# individual-project-hashmithakatta
#### cmpe-202 - Software System Engineering
#### SJSU - id - 015956876
#### Name - Hashmitha Katta

#### PROBLEM STATEMENT
   Created a Java application with inventory data , In order to purchase things from three categories: necessities, luxury, and miscellaneous. The maximum number of goods that may be bought in each area is limited. If the user's request is accepted, we prepare a TXT file with the total amount payable as well as the card information to be used for payment.

#### STEPS TO RUN THE APPLICATION

   * Clone the project repository  
   * Execute the folllowing commands in the terminal
   
    --> javac Billing.java
    
    --> java Billing.java
    
    --> copy the path of input.csv and paste it in the terminal
   
#### DESIGN PATTERNS
##### SINGLETON
   Singleton represents one of the most efficient techniques of object building, this design pattern is classified as a conceptual model pattern. This pattern employs a particular class that is in charge of creating an item while ensuring that just one is produced. The data repository employs the singleton design pattern. As we load the static database in one go, this will produce a single DataRepository object.

#### FACTORY
   Factory Method or Factory Pattern Simply create an interface or abstract class for constructing an item and let the subclasses determine which class to create, according to the pattern. To put it another way, subclasses are in charge of creating instances of the class Class ReaderFactory. The Factory design pattern is implemented in Java.The ReaderFactory may build objects of various reader kinds. ReaderFactory's getInstance function accepts readerType as an argument and returns a new instance of the specified reader.

#### CLASS DIAGRAM
#### SCREENSHOTS OF TEST CASES
<img width="1440" alt="Screen Shot 2022-05-09 at 9 25 54 PM" src="https://user-images.githubusercontent.com/56205828/167557263-836e811b-ebec-4055-a289-2986b9983e34.png">


