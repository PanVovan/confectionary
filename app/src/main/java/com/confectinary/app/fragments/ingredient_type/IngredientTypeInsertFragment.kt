package com.confectinary.app.fragments.ingredient_type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentInsertIngredientTypeBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames

class IngredientTypeInsertFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertIngredientTypeBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.IngredientType.value

    private val viewModel: IngredientTypeViewModel by viewModels{
        createFactory(IngredientTypeViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertIngredientTypeBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = tableName

        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val newItem = IngredientTypeDb(
                        naming = ingredientTypeNaming.text.toString()
                    )
                    viewModel.insert(newItem)

                    findNavController().navigate(
                        //Меняем для разных таблиц
                        R.id.action_insertClientFragment_to_viewClientsFragment
                    )
                } catch (e: Exception) {
                    Toast
                        .makeText(context, "Данные введены некорректно!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        return binding.root
    }
}