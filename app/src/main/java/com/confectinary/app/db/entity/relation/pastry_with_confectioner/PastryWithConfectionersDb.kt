package com.confectinary.app.db.entity.relation.pastry_with_confectioner

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.ConfectionerDb
import com.confectinary.app.db.entity.PastryDb

data class PastryWithConfectionersDb(
    @Embedded
    val pastry: PastryDb,
    @Relation(
        parentColumn = "pastry_id",
        entityColumn = "confectioner_id",
        associateBy = Junction(
            value = PastryAndConfectionerCrossRef::class
        )
    )
    val confectioners: List<ConfectionerDb>,
)
