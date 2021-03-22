import Model.Base
import Model.Category
import Model.Ingredient
import Model.Recipe
import kotlin.collections.List as List

const val OptionFinish = 0
val categoryList: List<Category> =
    listOf(
        Category(1, "Agua", listOf(
            Ingredient(1, "Agua")
        )),
        Category(2, "Leche", listOf(
            Ingredient(1, "Leche")
        )),
        Category(3, "Carne", listOf(
            Ingredient(1, "Res"),
            Ingredient(2, "Pollo"),
            Ingredient(3, "Puerco"),)),
        Category(4, "Verduras", listOf(
            Ingredient(1, "Lechuga"),
            Ingredient(2, "Nopales"))),
        Category(5, "Frutas", listOf(
            Ingredient(1, "Naranja"),
            Ingredient(2, "Manzana"),
            Ingredient(3, "Piña"),)),
        Category(6, "Cereales", listOf(
            Ingredient(1, "Maiz"),
            Ingredient(2, "Arroz"),
            Ingredient(3, "Quinoa"),)),
        Category(7, "Huevos", listOf(
            Ingredient(1, "Huevos"))),
        Category(8, "Aceite", listOf(
            Ingredient(1, "Aceite")))
        )

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
                println("*** Receta guardada ***")
                recipeList.add(recipe)
            }
            2 -> ViewRecipes(recipeList)
        }
    }
}

fun MakeRecipe(): Recipe {
    var option  = -1

    val ingredientList = mutableListOf<Ingredient>()
    println("*** Hacer receta ***")

    println("Escriba el nombre de la receta")
    val recipeName : String = readLine() ?: ""
    println("Seleccione por categoría el ingrediente que buscas")

    while (option != OptionFinish){
        printList(categoryList)
        option = readLine()?.toInt() ?: 0
        if(option != OptionFinish) {
            val category = categoryList.firstOrNull { it.id == option } ?: break
            println("*** ${category.name} ***")
            var optionIngredient  = -1
            while (optionIngredient != OptionFinish){
                printList(category.ingredientList)
                optionIngredient = readLine()?.toInt() ?: 0
                if(optionIngredient != OptionFinish){
                    val ingredient = category.ingredientList.firstOrNull { it.id == optionIngredient } ?: break
                    println("*** ${ingredient.name} ***")
                    if(!ingredientList.contains(ingredient))
                        ingredientList.add(ingredient)
                }
            }
        }
    }
    return Recipe(recipeName, ingredientList)
}

fun printList(list: List<Base>){
    println("0. terminar")
    for (item in list){
        println("${item.id}. ${item.name}")
    }
}

fun ViewRecipes(recipeList: List<Recipe>) {
    var option = -1

    while (option != OptionFinish){
        println("0. terminar")
        for ((index, value) in recipeList.withIndex()){
            println("${index + 1}. ${value.recipeName}")
        }
        option = readLine()?.toInt() ?: 0
        if(option > OptionFinish) {
            val recipe = recipeList[option - 1]
            println("*** ${recipe.recipeName} ***")
            println("***ingredientes***")
            for (item in recipe.ingredientList){
                   println(item.name)
            }
            println("*** Fin de la receta ***")
        }
    }
}

fun printMenu(){
    println(":: Bienvenido a Recipe Marker ::")
    println("")
    println("Selecciona la opción deseada")
    println("1. Hacer una receta")
    println("2. Ver mis recetas")
    println("3. Salir")
}