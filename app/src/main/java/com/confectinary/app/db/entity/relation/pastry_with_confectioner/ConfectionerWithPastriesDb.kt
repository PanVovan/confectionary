package com.confectinary.app.db.entity.relation.pastry_with_confectioner

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.ConfectionerDb
import com.confectinary.app.db.entity.PastryDb

data class ConfectionerWithPastriesDb(
    @Embedded
    val confectioner: ConfectionerDb,
    @Relation(
        parentColumn = "confectioner_id",
        entityColumn = "pastry_id",
        associateBy = Junction(
            value = PastryAndConfectionerCrossRef::class
        )
    )
    val pastries: List<PastryDb>,
)
