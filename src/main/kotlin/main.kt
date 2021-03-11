import kotlin.collections.List as List

val ingredientes: List<String> = listOf("Agua", "Leche", "Carne", "verduras", "Frutas", "Cereal", "Huevos", "Aceite")

fun main() {
    var option = 0
    val recipeList = mutableListOf<Recipe>()
    while (option != 3){
        print("\u001b[H\u001b[2J")
        printMenu()
        option = readLine()?.toInt() ?: 3
        when(option){
            1 -> {
                val recipe = MakeRecipe()
                recipeList.add(recipe)
            }
            2 -> WatchRecipes(recipeList)
        }
    }
}

fun MakeRecipe(): Recipe {
    var ingredientList = mutableListOf<String>()
    val optionExit = ingredientes.size
    var option  = 0

    println("Escriba el nombre de la receta")
    val recipeName : String = readLine() ?: ""
    println("Seleccione ingredientes")
    while (option != optionExit){
        var ingredient = ""
        printList(ingredientes)
        option = readLine()?.toInt() ?: optionExit
        if(option != 8) {
            ingredient = ingredientes[option]
            if(!ingredientList.contains(ingredient))
                ingredientList.add(ingredient)
        }
    }
    return Recipe(recipeName, ingredientList)
}

fun printList(list:List<String>){
    for ((index, value) in list.withIndex()){
        println("${index}. $value")
    }
    val optionExit = list.size
    println("${optionExit}. terminar")
}

fun WatchRecipes(recipeList: MutableList<Recipe>) {
    val optionExit = recipeList.size
    var option  = 0
    println("Elija la receta para ver los ingredientes")
    while (option != optionExit){
        PrintRecipeList(recipeList)
        option = readLine()?.toInt() ?: optionExit
        if(option != optionExit){
            val recipe = recipeList[option]
            println("ingredientes: ${recipe._ingredientList}")
        }
    }
}
fun PrintRecipeList(recipeList: MutableList<Recipe>){
    for ((index, value) in recipeList.withIndex()){
        println("${index}. ${value._recipeName}")
    }
    val optionExit = recipeList.size
    println("${optionExit}. Salir")
}


fun printMenu(){
    println(":: Bienvenido a Recipe Marker ::")
    println("")
    println("Selecciona la opción deseada")
    println("1. Hacer una receta")
    println("2. Ver mis recetas")
    println("3. Salir")
}

class Recipe(receipName: String, ingredientList: List<String>){
    val _recipeName: String = receipName
    val _ingredientList:  List<String> = ingredientList
}