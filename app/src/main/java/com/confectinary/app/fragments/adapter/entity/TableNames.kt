package com.confectinary.app.fragments.adapter.entity



class TableNames {

    companion object{
        val adminTableNames = listOf(
            TablesEnum.Confectionary.value,
            TablesEnum.Client.value,
            TablesEnum.Manager.value,
            TablesEnum.Confectioner.value,
            TablesEnum.Provider.value,
            TablesEnum.Pastry.value,
            TablesEnum.IngredientType.value,
            TablesEnum.Order.value,
            //TablesEnum.DiscountCards.value
        )

        val clientTableNames = listOf(
            TablesEnum.Order.value,
            TablesEnum.Pastry.value
        )

        val confectionerTableNames = listOf(
            TablesEnum.Order.value,
            TablesEnum.Pastry.value,
            TablesEnum.IngredientType.value
        )

        val TABLE_NAME_PARAM = "TABLE_NAME"
    }


    enum class TablesEnum(val value: String) {
        Confectionary("Кондитерские"),
        Client("Клиенты"),
        Manager("Менеджеры"),
        Confectioner("Кондитеры"),
        Provider("Поставщики"),
        Pastry("Изделия"),
        IngredientType("Ингредиенты"),
        Order("Заказы"),
        //DiscountCards("Скидочные карты");


    }
}
