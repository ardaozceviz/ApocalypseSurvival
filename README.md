# Story
In the 23rd century the war between two empires led to a nuclear apocalypse which led to extinction of nearly all civilization and animal life. As we are the only survivors we are trying to build a new civilization but resources required to sustain life are rare. The radioactivity makes outdoors dangerous. We are living in an old bunker that is left from WW2. Volunteers need to get out to the dangerous lands and get to the places where they can get resources. Luckily the bunker we are living has an old radar that can find creatures on our path to the resources. As a surviving engineer you are required to write a simulation that can simulate if a volunteer can reach to resources. Be aware that our radar indicates that there are dangerous creatures and even zombies on the wasteland.

# Problem
Write a simulation that find outs if the hero would survive or not. You can use the following sample input and output as a reference. If the volunteer hero faces an enemy he needs to fight against it until one of them dies. To simulate fights you can accept that enemy and the hero attack at the same time. hp represents health points. Each attack decreases health points equal to attack. To avoid radioactivity volunteer hero wears a special heavy armor that makes him walk meter by meter.

### Sample - 1
#### Input
```
Resources are 5000 meters away
Hero has 1000 hp
Hero attack is 10
Bug is Enemy
Lion is Enemy
Zombie is Enemy
Bug has 50 hp
Bug attack is 2
Lion has 100 hp
Lion attack is 15
Zombie has 300 hp
Zombie attack is 7
There is a Zombie at position 1681
There is a Bug at position 276
There is a Bug at position 489
There is a Lion at position 1527
There is a Lion at position 2865
There is a Zombie at position 3523
```
#### Output
```
Hero started journey with 1000 HP!
Hero defeated Bug with 990 HP remaining
Hero defeated Bug with 980 HP remaining
Hero defeated Lion with 830 HP remaining
Hero defeated Zombie with 620 HP remaining
Hero defeated Lion with 470 HP remaining
Hero defeated Zombie with 260 HP remaining
Hero Survived!
```

### Sample - 2
#### Input
```
Resources are 7500 meters away
Hero has 500 hp
Hero attack is 9
ZombieDog is Enemy
Mutant is Enemy
Zombie is Enemy
Mutant has 400 hp
Mutant attack is 8
ZombieDog has 75 hp
ZombieDog attack is 10
Zombie has 300 hp
Zombie attack is 7
There is a Zombie at position 1687
There is a Mutant at position 274
There is a ZombieDog at position 486
There is a ZombieDog at position 1897
There is a Mutant at position 5332
```
#### Output
```
Hero started journey with 500 HP!
Hero defeated Mutant with 140 HP remaining
Hero defeated ZombieDog with 50 HP remaining
Zombie defeated Hero with 228 HP remaining
Hero is Dead!! Last seen at position 1687!!
```

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