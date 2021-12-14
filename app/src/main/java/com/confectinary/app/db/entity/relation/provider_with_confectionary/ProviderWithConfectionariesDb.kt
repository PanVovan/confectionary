package com.confectinary.app.db.entity.relation.provider_with_confectionary

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ProviderDb

data class ProviderWithConfectionariesDb (
    @Embedded
    val provider: ProviderDb,
    @Relation(
        parentColumn = "provider_id",
        entityColumn = "confectionary_id",
        associateBy = Junction(
            value = ProviderAndConfectionaryCrossRef::class
        )
    )
    val confectionaries: List<ConfectionaryDb>
)