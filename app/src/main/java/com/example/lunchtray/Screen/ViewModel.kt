package com.example.lunchtray.Screen

import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.Dish

private const val TAX = 0.84

class ViewModel: ViewModel(){

    val entreeList = listOf<Dish>(
        Dish("Cauliflower", "Whole cauliflower, brined, roasted, and deep fried", 7.00 ),
        Dish("Mushroom Pasta", "Penne pasta, mushrooms, basil, with plum tomatoes cooked in garlic \" +\n" +
                        "                    \"and olive oil", 5.50 ),
        Dish("Spicy Black Bean Skillet", "Heirloom tomatoes, butter lettuce, peaches, avocado, balsamic dressing", 2.50 ),
    )
    val sideDishMenuItemsList = listOf<Dish>(
        Dish("Summer Salad", "Heirloom tomatoes, butter lettuce, peaches, avocado, balsamic dressing", 2.50 ),
        Dish("Butternut Squash Soup", "Roasted butternut squash, roasted peppers, chili oil", 2.00 ),
        Dish("Coconut Rice", "Rice, coconut milk, lime, and sugar", 1.50 ),
    )
    val accompanimentMenuItemsList =  listOf<Dish>(
        Dish("Lunch Roll", "Fresh baked roll made in house", 1.50 ),
        Dish("Mixed Berriesp", "Strawberries, blueberries, raspberries, and huckleberries", 2.00 ),
        Dish("Pickled Veggies", "Pickled cucumbers and carrots, made in house", 0.50 ),
    )

    val selectedItemList = mutableListOf<Dish>()
}