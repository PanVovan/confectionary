package com.confectinary.app.db.entity.relation.pastry_with_ingredient

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.PastryDb

data class IngredientTypesWithPastries(
    @Embedded
    val ingredientType: IngredientTypeDb,
    @Relation(
        parentColumn = "ingredient_type_id",
        entityColumn = "pastry_id",
        associateBy = Junction(
            value = PastryAndIngredientTypeCrossRef::class
        )
    )
    val pastries: List<PastryDb>,
)
