package com.au.fridgly.domain.models

import com.au.fridgly.R
import java.lang.Exception

enum class EAliment(val alimentName: String, val description: String, val image: Int) {
    ALMOND("almond", "almond", R.drawable.al_almond),
    APPLE("apple", "apple", R.drawable.al_apple),
    APRICOT("apricot", "apricot", R.drawable.al_apricot),
    ARTICHOKE("artichoke", "artichoke", R.drawable.al_artichoke),
    ASPARAGUS("asparagus", "asparagus", R.drawable.al_asparagus),
    AVOCADO("avocado", "avocado", R.drawable.al_avocado),
    BANANA("banana", "banana", R.drawable.al_banana),
    BEAN("bean", "bean", R.drawable.al_beans),
    BEET("beet", "beet", R.drawable.al_beet),
    BLUEBERRY("blueberry", "blueberry", R.drawable.al_blueberries),
    BREAD("bread", "bread", R.drawable.al_bread),
    BROCCOLI("broccoli", "broccoli", R.drawable.al_broccoli),
    CABBAGE("cabbage", "cabbage", R.drawable.al_cabbage),
    CARROT("carrot", "carrot", R.drawable.al_carrot),
    CAULIFLOWER("cauliflower", "cauliflower", R.drawable.al_cauliflower),
    CELERY("celery", "celery", R.drawable.al_celery),
    CHEESE("cheese", "cheese", R.drawable.al_cheese),
    CHERRY("cherry", "cherry", R.drawable.al_cherries),
    CHICKPEA("chickpea", "chickpea", R.drawable.al_missing),
    CHILLI_PEPPER("chili pepper", "chili pepper", R.drawable.al_chili),
    CHIVE("chive", "chive", R.drawable.al_chives),
    CHOCOLATE("chocolate", "chocolate", R.drawable.al_chocolate),
    COCONUT("coconut", "coconut", R.drawable.al_coconut),
    COFFEE("coffee", "coffee", R.drawable.al_coffee),
    CORN("corn", "corn", R.drawable.al_corn),
    CUCUMBER("cucumber", "cucumber", R.drawable.al_cucumber),
    DATES("dates", "dates", R.drawable.al_missing),
    EGG("egg", "egg", R.drawable.al_egg),
    EGGPLANT("eggplant", "eggplant", R.drawable.al_missing),
    ENDIVE("endive", "endive", R.drawable.al_missing),
    FIG("fig", "fig", R.drawable.al_fig),
    HAZELNUT("hazelnut", "hazelnut", R.drawable.al_hazelnuts),
    LEEK("leek", "leek", R.drawable.al_leek),
    LEMON("lemon", "lemon", R.drawable.al_lemon),
    LETTUCE("lettuce", "lettuce", R.drawable.al_lettuce),
    MANGO("mango", "mango", R.drawable.al_mango),
    MELON("melon", "melon", R.drawable.al_melon),
    MUSHROOM("mushroom", "mushroom", R.drawable.al_mushrooms),
    OLIVE("olive", "olive", R.drawable.al_olives),
    ORANGE("orange", "orange", R.drawable.al_orange),
    PASSION_FRUIT("passion fruit", "passion fruit", R.drawable.al_passion_fruit),
    PEACH("peach", "peach", R.drawable.al_peach),
    PEAR("pear", "pear", R.drawable.al_pear),
    PEPPER("pepper", "pepper", R.drawable.al_pepper),
    PICKLE("pickle", "pickle", R.drawable.al_missing),
    PINEAPPLE("pineapple", "pineapple", R.drawable.al_pineapple),
    PISTACHIO("pistachio", "pistachio", R.drawable.al_pistachio),
    POTATO("potato", "potato", R.drawable.al_potato),
    PUMPKIN("pumpkin", "pumpkin", R.drawable.al_pumpkin),
    RASPBERRY("raspberry", "raspberry", R.drawable.al_raspberry),
    RICE("rice", "rice", R.drawable.al_rice),
    SPINACH("spinach", "spinach", R.drawable.al_spinach),
    STRAWBERRY("strawberry", "strawberry", R.drawable.al_strawberry),
    TOMATO("tomato", "tomato", R.drawable.al_tomato),
    TURNIP("turnip", "turnip", R.drawable.al_missing),
    WATERMELON("watermelon", "watermelon", R.drawable.al_watermelon),
    ZUCCHINI("zucchini", "zucchini", R.drawable.al_zucchini);

    companion object {
        private val list: Array<EAliment> = EAliment.values()

        fun find(name: String): EAliment = list.find { it.alimentName == name } ?:
                throw Exception()
    }
}