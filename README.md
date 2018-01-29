Please read Instructions.md to better understand project requirements.

# How to run?
- Install a recent version of IntelliJ IDEA. Kotlin is bundled with IntelliJ IDEA starting from version 15.
- You can download the free Community Edition from [JetBrains](http://www.jetbrains.com/idea/download/index.html).
- Download the project as zip file.
- Unzip the downloaded file.
- Open the project folder from from Intellij IDEA.
- Run!

# Project structure
The project has 3 main components.  
- ```main.kt```: Main Kotlin file where the application starts its life.
-  ```controllers```: The package contains logic and engine related files. It consists of 2 objects:
-- ```Data```: For data handling jobs like, reading, writing and mapping.
-- ```Simulation```: Process the valuable information and make the simulation happen.
- ```models```: The package contains the classes. It consists of 5 classes:
-- ```Creature```: Main class holds the main and common variables, other living creatures like Hero" and "Enemy" will be inherited from this main class,
-- ```Enemy```: Class for enemies, inherits from the "Creature" class.
-- ```Hero```: Class for hero, inherits from the "Creature" class.
-- ```Resource```: Class for resource.
-- ```MappedData```: Class for the data that we read from the "input.txt" file.

# How does it work?
This project has developed in Andorid’s official and JVM language “Kotlin”.  The general flow of the application is as it follows.
- Define the variable ```readFileName```, default value is ```input.txt```
- Call the ```readFile()``` function from ```Data``` object by passing the value ```readFileName``` as an input parameter  to read the input file and assign its return value which is an instance of  ```MappedData``` class to the ```mappedData``` variable.
- ```readFile()``` function reads the ```input.txt``` line by line and adds it to ```inputLines ``` list which stores each line of the given input text file as an item. This function returns the instance of ```MappedData``` class by passing the variable ```inputLines ``` as an input parameter to ```dataMapper()``` function.
- ```dataMapper()``` function is actually do the data mining job by extracting the valuable information from the ```inputLines ``` list which is equal to the information in ```input.txt``` file. After organising the necessary information and assigning them to relateed variables, returns it as an instance of ```MappedData``` class by executing ```return MappedData(resource, hero, enemyList) ```.
- We red the file ```input.txt``` and stored the necessary information for simulation in the ```mappedData``` variable which is an instance of ```MappedData``` class. Now we can call the ```start()``` function from ```Simulation``` object by passing the ```mappedData``` variable as a parameter by executing  ```Simulation.start(mappedData) ```.
-  ```start() ``` function initialises to simulation by extracting the wrapped information from ```mappedData``` variable and assigning the values to the related variables and then starts the ```while()``` loop which represents our hero's movement.
- ```while()``` loop continues till the ```hero``` reaches to the ```position``` of the ```resource``` or until he/she dies. Inside the ```while()``` loop there is a ```for()``` loop that loops through the ```enemies``` and calls the ```fight()``` function each time by passing the ```Pair(hero, enemy)``` variable as a parameter if their ```positions``` meets.
- ```fight()``` function simulates the fight between the hero and the enemy. The parameter ``` pair``` holds the instances of ``` Hero``` and, enemy ``` Enemy```  classes. And returns  ```Boolean ``` that represents if the  ```hero ``` won the fight or not.
- In the end we reach to the end of  ```start() ``` function where we call ```writeFile()``` function by passing the ```journeyLog``` variable as a parameter.
- ```writeFile()``` loops through ```journeyLog``` variable and writes it line by line to the text file called ```output.txt```. If the file exists with the same name it numbers the file name starting from ```1```. For example ```output1.txt```, ```output2.txt```, ```output3.txt```...

# License
```license
MIT License

Copyright (c) 2017 Arda Özceviz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
