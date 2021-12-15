package com.confectinary.app.fragments.provider

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentProviderInsertBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.ProviderDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProviderInsertFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentProviderInsertBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Provider.value



    private var ingredientTypes: List<IngredientTypeDb> = emptyList()
    private var confecionaries: List<ConfectionaryDb> = emptyList()

    private val viewModel: ProviderViewModel  by viewModels{
        createFactory(ProviderViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProviderInsertBinding.inflate(inflater, container, false)



        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val selected = chooseIngredientSpinner.selectedItem.toString()
                    val ingredientType = ingredientTypes.find { it.naming == selected }
                        ?: throw RuntimeException()

                    val selectedConfectionary = chooseConfectionarySpinner.selectedItem.toString()
                    val confectionary = confecionaries.find { it.address == selectedConfectionary }
                        ?: throw RuntimeException()

                    val newItem = ProviderDb(
                        naming = providerNamingInput.text.toString(),
                    )
                    viewModel.insert(newItem, ingredientType.ingredientTypeId!!, confectionary.confectionaryId!!)


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

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = tableName
        viewModel.loadProviders()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow1
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { list ->

                    ingredientTypes = list
                    val spinnerAdapter = context?.let {
                        ArrayAdapter(
                            context!!,
                            android.R.layout.simple_spinner_item,
                            arrayOf("Тип ингредиента").union(list.map { it.naming }).toTypedArray()
                        )
                    }
                    binding.chooseIngredientSpinner.adapter = spinnerAdapter
                }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.dataFlow2.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                    .collectLatest { list ->

                        confecionaries = list
                        val spinnerAdapter1 = context?.let {
                            ArrayAdapter(
                                context!!,
                                android.R.layout.simple_spinner_item,
                                arrayOf("Кондитерская").union(list.map { it.address }).toTypedArray()
                            )
                        }
                        binding.chooseConfectionarySpinner.adapter = spinnerAdapter1
                    }
            }

        }

    }


}