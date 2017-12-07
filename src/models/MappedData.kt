package models

// The class called "MappedData" is a framework for the data that we read from the "input.txt" file and,
// it is important it to be organised for our simulation.
// This class holds the instances of "Resource" and "Hero" classes,
// as well as the list holds the instances of the "Enemy" class.
// The number of items in the "enemyList" is equal to the number of the enemies on the map.
class MappedData(val resource: Resource,
                 val hero: Hero,
                 val enemyList: List<Enemy>)