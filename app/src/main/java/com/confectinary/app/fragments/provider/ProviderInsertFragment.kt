package com.confectinary.app.fragments.provider

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentInsertClientBinding
import com.confectinary.app.databinding.FragmentProviderInsertBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ProviderDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import com.confectinary.app.fragments.client.ClientViewModel
import kotlinx.coroutines.launch

class ProviderInsertFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentProviderInsertBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Provider.value

    private val viewModel: ProviderViewModel  by viewModels{
        createFactory(ProviderViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProviderInsertBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = tableName

        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val selected = chooseIngredientSpinner.selectedItem.toString()
                    val confectionary = viewModel.ingredientTypes.find { it.naming == selected }
                        ?: throw RuntimeException()

                    val newItem = ProviderDb(
                        0,
                        providerNamingInput.text.toString(),
                    )
                    viewModel.insert(newItem, confectionary.ingredientTypeId!!)


                    findNavController().navigate(
                        //Меняем для разных таблиц
                        R.id.action_providerInsertFragment_to_providerFragment
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            val spinnerAdapter = context?.let {
                ArrayAdapter(
                    context!!,
                    android.R.layout.simple_spinner_item,
                    arrayOf("Кондитерская").union(viewModel.ingredientTypes.map { it.naming }).toTypedArray()
                )
            }
            binding.chooseIngredientSpinner.adapter = spinnerAdapter
        }

    }


}