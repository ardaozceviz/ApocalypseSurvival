import controllers.Data
import controllers.Simulation

fun main(args: Array<String>) {

    // This is the name of the file that we want to read from.
    // Change this value in order to read from file a with a different name.
    val readFileName = "input.txt"

    // Calling custom read function from Data class and storing the return value on a variable.
    // This will start the chain of processes.
    // Those processes are;
    // 1- Reading the file from the text.
    // 2- Extracting the valuable information from it.
    // 3- Return the instance of a "MappedData" class.
    val mappedData = Data.readFile(readFileName)

    // Once we have the necessary information for the simulation,
    // we can pass this information to the "controllers.Simulation" class and,
    // call the "start()" function in order to start the simulation.
    Simulation.start(mappedData)
}