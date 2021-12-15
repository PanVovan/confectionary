package com.confectinary.app.db.entity.relation.pastry_with_ingredient

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.ConfectionerDb
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.PastryDb
import com.confectinary.app.db.entity.relation.pastry_with_confectioner.PastryAndConfectionerCrossRef

data class PastryWithIngredientTypesDB(
    @Embedded
    val pastry: PastryDb,
    @Relation(
        parentColumn = "pastry_id",
        entityColumn = "ingredient_type_id",
        associateBy = Junction(
            value = PastryAndIngredientTypeCrossRef::class
        )
    )
    val ingredientTypes: List<IngredientTypeDb>,
)