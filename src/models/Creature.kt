package models

// Main class called "Creature" holds the main and common variables like,
// health, attack and position for our hero and enemies.
// Other living creatures like "Hero" and "Enemy" will be inherited from this main class,
// since they have those commonalities.
open class Creature(var health: Int = 0, var attack: Int = 0, var position: Int = 0)