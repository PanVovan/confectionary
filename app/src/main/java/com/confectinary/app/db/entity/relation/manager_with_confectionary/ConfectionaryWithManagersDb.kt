package com.confectinary.app.db.entity.relation.manager_with_confectionary

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ManagerDb

data class ConfectionaryWithManagersDb (
    @Embedded
    val confectionary: ConfectionaryDb,
    @Relation(
        parentColumn = "confectionary_id",
        entityColumn = "manager_id",
        associateBy = Junction(
            value = ConfectionaryAndManagerCrossRef::class
        )
    )
    val managers: List<ManagerDb>,
)