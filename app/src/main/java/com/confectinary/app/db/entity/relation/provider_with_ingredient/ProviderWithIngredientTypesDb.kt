package com.confectinary.app.db.entity.relation.provider_with_ingredient

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.ProviderDb

data class ProviderWithIngredientTypesDb(
    @Embedded
    val provider: ProviderDb,
    @Relation(
        parentColumn = "provider_id",
        entityColumn = "provider_id",
        associateBy = Junction(
            value = ProviderAndIngredientTypeCrossRef::class
        )
    )
    val ingredientTypes: List<IngredientTypeDb>,
)
