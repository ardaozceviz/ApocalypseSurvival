package controllers

import models.Enemy
import models.Hero
import models.MappedData
import models.Resource
import java.io.BufferedWriter
import java.io.File
import java.io.FileNotFoundException

// An object called "Data" where we use for data handling jobs like,
// reading, writing and mapping.
object Data {
    /**
     * Reads the file content as a list of lines.
     *
     * @param readFileName file name to read.
     * @return instance of a "MappedData" class which holds valuable information.
     */
    fun readFile(readFileName: String): MappedData {
        val inputLines = mutableListOf<String>() // Stores each line of the given input text file as an item.
        // Here we are trying to read the file,
        // And catching the exception to let the user know that file does not exist.
        try {
            val file = File(readFileName)
            val lines: List<String> = file.readLines()
            inputLines += lines
        } catch (e: FileNotFoundException) {
            println("The system cannot find the file specified")
        }
        // In the end we are returning an instance of a "MappedData" class that returns from "dataMapper" function.
        // which holds the extracted valuable information.
        return dataMapper(inputLines)
    }

    /**
     * Writes to the text file called "output.txt" line by line.
     * If "output.txt" exists it will give it a number starting from 1
     * For example "output1.txt", "output2.txt", "output3.txt"
     *
     * @param text information to write for each
     */
    fun writeFile(text: List<String>) {
        fun BufferedWriter.writeLn(line: String) {
            this.write(line)
            this.newLine()
        }

        var file = File("output.txt")
        var i = 1
        while (file.exists()) {
            file = File(String.format("output%d.txt", i))
            i++
        }

        file.bufferedWriter().use { out ->
            text.forEach {
                out.writeLn(it)
            }
        }
    }

    /**
     * Maps the data in such a way so we can use it for simulating.
     *
     * @param inputLines list of lines from "input.txt".
     * @return instance of a "MappedData" class which holds valuable information.
     */
    private fun dataMapper(inputLines: MutableList<String>): MappedData {
        /* "enemyTypeList" is the list that holds the types of enemies in "key,value" pair.
           "key" is the type of the enemy,
           "value" is the instance of an "Enemy" class.
           It's an "HashMap" list because there should not be any duplicate types with different properties.
           In case of duplication that type will be overridden with the latest information provided.
        */
        val enemyTypeList = hashMapOf<String, Enemy>()
        val resource = Resource() // Creating and instance of a Resource class
        val hero = Hero() // Creating and instance of a Hero class
        /* Creating a list holds the instances of "Enemy" class.
           Each item of this list, represents an individual enemy on the map.
         */
        val enemyList = mutableListOf<Enemy>()

        /* Looping through each item of the "inputLines" list.
           Here we are mining the valuable information and assigning it to related variables of related classes.
           I used some "Standard Library" functions that "Kotlin" provides to extract the necessary information.
           Those functions are actually pretty clear and easy to understand from their name.
           Removing prefixes and suffixes and transforming them to integer data types when it needed.
        */
        inputLines.forEach { line ->
            if (line.contains("Resources are")) {
                resource.position = line.removePrefix("Resources are ").removeSuffix(" meters away").toInt()
            }

            if (line.contains("Hero has")) {
                hero.health = line.removePrefix("Hero has ").removeSuffix(" hp").toInt()
            }

            if (line.contains("Hero attack is")) {
                hero.attack = line.removePrefix("Hero attack is ").toInt()
            }

            if (line.contains("is Enemy")) {
                val type = line.removeSuffix(" is Enemy")
                enemyTypeList.put(type, Enemy(type))
            }

            /*
              Here we are looping through each item of "enemyTypeList" and
              for every "key -> type", "value -> enemy" pairs ve are assigning the related information such as,
              health and attack information for the instance of an "Enemy" class.
            */
            enemyTypeList.forEach { type, enemy ->
                if (line.contains(type)) {
                    if (line.contains(type + " has")) {
                        enemy.health = (line.removePrefix(type + " has ").removeSuffix(" hp").toInt())
                    }
                    if (line.contains(type + " attack")) {
                        enemy.attack = (line.removePrefix(type + " attack is ").toInt())
                    }
                }
            }

            /*
              Here we are creating the instances of an "Enemy" class and,
              putting every one of them in to the "enemyList" as a list item.
              In the end this list will be holding instances of an "Enemy" class
              And those instances holds the all the information about enemies.
              Number of items in this list is equals to the number of creatures created.
            */
            if (line.contains("There is a")) {
                val enemyType = line.removePrefix("There is a ").substringBefore("at").trim()
                val enemyPosition = line.substringAfter("position").trim().toInt()
                val storedEnemy = enemyTypeList[enemyType]
                val enemy = Enemy(enemyType)
                if (storedEnemy != null) {
                    enemy.health = storedEnemy.health
                    enemy.attack = storedEnemy.attack
                    enemy.position = enemyPosition
                    enemy.type = storedEnemy.type
                    enemyList.add(enemy)

                }
            }
        }
        // In the end returning an instance of a "MappedData" class which holds the all the necessary for simulation.
        return MappedData(resource, hero, enemyList)
    }
}