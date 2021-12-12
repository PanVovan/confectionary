package com.confectinary.app.db.entity.relation.provider_with_confectionary

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ProviderDb

data class ConfectionaryWithProvidersDb(
    @Embedded
    val confectionary: ConfectionaryDb,
    @Relation(
        parentColumn = "confectionary_id",
        entityColumn = "confectionary_id",
        associateBy = Junction(
            value = ProviderAndConfectionaryCrossRef::class
        )
    )
    val providers: List<ProviderDb>,
)
