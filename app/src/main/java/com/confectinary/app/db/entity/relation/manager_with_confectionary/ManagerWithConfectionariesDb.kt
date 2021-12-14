package com.confectinary.app.db.entity.relation.manager_with_confectionary

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ManagerDb

data class ManagerWithConfectionariesDb(
    @Embedded
    val manager: ManagerDb,
    @Relation(
        parentColumn = "manager_id",
        entityColumn = "confectionary_id",
        associateBy = Junction(
            value = ConfectionaryAndManagerCrossRef::class
        )
    )
    val confectionaries: List<ConfectionaryDb>
)
