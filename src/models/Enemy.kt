package models

// The class called "Enemy" for enemies and this class inherits from the "Creature" class.
// "type" variable represents the type of the enemy such as "Zombie", "Mutant", "Bug" ect...
class Enemy(var type: String) : Creature()