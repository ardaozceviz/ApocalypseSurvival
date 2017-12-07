package controllers

import models.Enemy
import models.Hero
import models.MappedData


// This is the "Simulation" object where we process the valuable information and make the simulation happen.
object Simulation {
    private var isHeroSurvived = true // Boolean variable holds the state (alive or dead) of our hero.
    // This is the list of "Strings" holds the log for the our hero's journey,
    // each item of the list represents a single line for the "output.txt" file.
    private val journeyLog = mutableListOf<String>()

    /**
     * Initialises to simulation.
     *
     * @param mappedData an instance of an "MappedData" class holds the necessary information for simulation
     */
    fun start(mappedData: MappedData) {
        // Assigning "resource" variable to an instance of a "Resource" class stored in "mappedData" parameter.
        val resource = mappedData.resource
        // Assigning "hero" variable to an instance of a "Hero" class stored in "mappedData" parameter.
        val hero = mappedData.hero
        // Assigning "enemyList" variable to a list holds instances of an "Enemy" class stored in "mappedData" parameter.
        val enemyList = mappedData.enemyList
        // First line of the "output.txt" file. Adding this "String" to the "journeyLog" variable.
        journeyLog.add("Hero started journey with ${hero.health} HP!")
        /*
          This loop demonstrates our hero's movement on the map.
          We can imagine our path to the resource as a line since our hero moves only one direction.
          Our hero moves one by one till his/her position crosses with an enemy.
          When hero and enemy meet we call a function "fight()" to simulate to fight.
          Depending on the return value from the function "fight()" either our loop continues,
          means our hero continue walking and alive or loop stops which means our hero is dead.
        */
        mainLoop@ while (hero.position < resource.position) {
            for (enemy in enemyList) {
                if (hero.position == enemy.position) {
                    if (!fight(Pair(hero, enemy))) {
                        break@mainLoop
                    }
                }
            }
            hero.position++
        }
        if (isHeroSurvived) {
            journeyLog.add("Hero Survived!")
        }

        // When we reach this point, it means we finished the journey and,
        // we can write the journey log to the "output.txt" file by,
        // calling the function "writeFile" from the "Data" object.
        Data.writeFile(journeyLog)
    }

    /**
     * Simulates the fight between the hero and the enemy.
     *
     * @param pair holds the instance of our hero from "Hero" class and, instance of the enemy from "Enemy" class
     * @return Boolean value represents if the hero win the fight or not.
     */
    private fun fight(pair: Pair<Hero, Enemy>): Boolean {
        // Extracting the values from the "Pair" and assigning them to related variables.
        val hero = pair.first
        val enemy = pair.second

        val heroRemainingHealth = hero.health // Represents our hero's remaining health.

        // "enemyTotalDamage" represents the total damage that enemy can take before it dies.
        val enemyTotalDamage = enemy.attack * Math.ceil((enemy.health.toDouble() / hero.attack)).toInt()
        hero.health -= enemyTotalDamage // Our hero loosing from his/her health by taking damage from enemy.
        if (hero.health > 0) {
            // Our hero is alive and he/she beat the enemy.
            journeyLog.add("Hero defeated ${enemy.type} with ${hero.health} HP remaining")
        } else {
            // Our hero is killed by the enemy.
            val heroTotalDamage = hero.attack * Math.ceil((heroRemainingHealth.toDouble() / enemy.attack)).toInt()
            enemy.health -= heroTotalDamage // Enemy is loosing from it's health by taking damage from our hero.
            journeyLog.add("${enemy.type} defeated Hero with ${enemy.health} HP remaining")
            journeyLog.add("Hero is Dead! Last seen at position ${enemy.position}!!")
            isHeroSurvived = false
            return false
        }
        return true
    }
}