package Model

class Category (id:Int, name: String, val ingredientList: List<Ingredient> = listOf()) : Base(id, name) {
}