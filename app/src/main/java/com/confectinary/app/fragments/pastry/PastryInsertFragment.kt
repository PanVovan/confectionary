package com.confectinary.app.fragments.pastry

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.confectinary.app.databinding.FragmentInsertPastryBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.OrderDb
import com.confectinary.app.db.entity.PastryDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class PastryInsertFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertPastryBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Pastry.value


    private val viewModel: PastryViewModel by viewModels{
        createFactory(PastryViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    private var ingredientTypes: List<IngredientTypeDb>? = null
    private var orders: List<OrderDb>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertPastryBinding.inflate(inflater, container, false)


        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val selectedIngredient1 = chooseIngredientSpinner1.selectedItem.toString()
                    val selectedIngredient2 = chooseIngredientSpinner2.selectedItem.toString()
                    val selectedOrder = chooseOrderSpinner.selectedItem.toString()

                    val ingredientType1 = ingredientTypes?.find { it.naming == selectedIngredient1 }?.ingredientTypeId
                    var ingredientType2 = ingredientTypes?.find { it.naming == selectedIngredient2 }?.ingredientTypeId
                    if (ingredientType1==ingredientType2) ingredientType2 = null

                    val order = orders?.find { it.description == selectedOrder }
                        ?: throw RuntimeException()



                    val newItem = PastryDb(
                        price = pastryPriceInput.text.toString().toInt(),
                        naming = pastryNamingInput.text.toString(),
                        manufactured = pastryDateInput.text.toString(),
                        orderId = order.orderId!!
                    )
                    viewModel.insert(newItem, ingredientType1, ingredientType2)

                    findNavController().navigate(
                        //Меняем для разных таблиц
                        R.id.action_pastryInsertFragment_to_pastryFragment
                    )
                } catch (e: Exception) {
                    Toast
                        .makeText(context, "Данные введены некорректно!", Toast.LENGTH_SHORT)
                        .show()
                    e.printStackTrace()
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = tableName
        viewModel.loadPastries()
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
                    binding.chooseIngredientSpinner1.adapter = spinnerAdapter
                    binding.chooseIngredientSpinner2.adapter = spinnerAdapter
                }

        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow2
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { list ->

                    orders = list
                    val spinnerAdapter = context?.let {
                        ArrayAdapter(
                            context!!,
                            android.R.layout.simple_spinner_item,
                            arrayOf("Заказ").union(list.map { it.description }).toTypedArray()
                        )
                    }
                    binding.chooseOrderSpinner.adapter = spinnerAdapter
                }

        }

    }
}