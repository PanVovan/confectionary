package com.confectinary.app.db.entity.relation.provider_with_ingredient

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.ProviderDb

data class IngredientTypesWithProvidersDb(
    @Embedded
    val ingredientType: IngredientTypeDb,
    @Relation(
        parentColumn = "ingredient_type_id",
        entityColumn = "ingredient_type_id",
        associateBy = Junction(
            value = ProviderAndIngredientTypeCrossRef::class
        )
    )
    val providers: List<ProviderDb>,
)
