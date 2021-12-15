package com.confectinary.app.fragments.order

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
import com.confectinary.app.databinding.FragmentInsertManagerBinding
import com.confectinary.app.databinding.FragmentInsertOrderBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.db.entity.OrderDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import com.confectinary.app.fragments.manager.ManagerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InsertOrderFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertOrderBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Order.value

    private val viewModel: OrderViewModel by viewModels {
        createFactory(OrderViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    private var clients: List<ClientDb>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertOrderBinding.inflate(inflater, container, false)


        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val selectedConfectionary = chooseClientSpinner.selectedItem.toString()
                    val client = clients?.find { it.lastname == selectedConfectionary }
                        ?: throw RuntimeException()
                    val newItem = client.clientId?.let { it1 ->
                        OrderDb(
                            description = descriptionInput.text.toString(),
                            clientId = it1
                        )
                    }
                    if (newItem != null) {
                        viewModel.insert(newItem)
                    }

                    findNavController().navigate(
                        //Меняем для разных таблиц
                        R.id.action_inputManagerFragment_to_viewManagerFragment
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
        viewModel.loadOrders()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow1.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { list ->

                    clients = list
                    val spinnerAdapter = context?.let {
                        ArrayAdapter(
                            context!!,
                            android.R.layout.simple_spinner_item,
                            arrayOf("Заказ").union(list.map { it.lastname }).toTypedArray()
                        )
                    }
                    binding.chooseClientSpinner.adapter = spinnerAdapter
                }
        }

    }
}