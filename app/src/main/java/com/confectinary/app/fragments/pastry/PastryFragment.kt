package com.confectinary.app.fragments.pastry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentViewBinding
import com.confectinary.app.databinding.FragmentViewPastriesBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.db.entity.OrderDb
import com.confectinary.app.db.entity.PastryDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import com.confectinary.app.fragments.manager.ManagerViewModel
import com.confectinary.app.fragments.manager.ManagersAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PastryFragment : Fragment() {
    //FragmentViewPastriesBinding
    private var _binding: FragmentViewPastriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: PastryViewModel by viewModels{
        createFactory(PastryViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Pastry.value
    //Меняем для разных таблиц
    private var adapter: PastryAdapter = PastryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPastriesBinding.inflate(inflater, container, false)


        with(binding) {
            talbeitemsList.adapter = adapter
            addItem.setOnClickListener {
                findNavController().navigate(
                    //Меняем для разных таблиц
                    R.id.action_pastryFragment_to_pastryInsertFragment
                )
            }

            searchBtn.setOnClickListener {
                when (selectSearchFieldSpinner.selectedItem.toString()){
                    "Наименование" -> {
                        val filter = searchInput.text.toString()
                        if(filter != "") {
                            showingPastries =
                                allPastries?.filter {
                                    it.naming.contains(
                                        filter,
                                        ignoreCase = true
                                    )
                                }
                            updateAdapter(showingPastries)
                        } else
                            updateAdapter(allPastries)
                    }
                    "Цена: больше" -> {
                        val filter = searchInput.text.toString().toIntOrNull()
                        if(filter != null) {
                            showingPastries =
                                allPastries?.filter {
                                    it.price >= filter
                                }
                            updateAdapter(showingPastries)
                        }  else
                            updateAdapter(allPastries)
                    }
                    "Цена: меньше" -> {
                        val filter = searchInput.text.toString().toIntOrNull()
                        if(filter != null) {
                            showingPastries =
                                allPastries?.filter {
                                    it.price <= filter
                                }
                            updateAdapter(showingPastries)
                        }  else
                            updateAdapter(allPastries)
                    }
                    "Описание" -> {
                        val filter = searchInput.text.toString()
                        if(filter != "") {
                            try {
                                showingPastries =
                                    allPastries?.filter {
                                        val order =
                                            orders!!.firstOrNull { i -> i.orderId == it.orderId }
                                        order!!.description.contains(filter, ignoreCase = true)
                                    }
                                updateAdapter(showingPastries)
                            } catch (e: Exception){}
                        } else
                            updateAdapter(allPastries)
                    }
                }
            }
        }

        return binding.root
    }

    private var allPastries: List<PastryDb>? = null
    private var orders: List<OrderDb>? = null
    private var showingPastries: List<PastryDb>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(values: List<PastryDb>?){
        values?.let {
            adapter.values = values
            adapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest {
                    allPastries = it
                    showingPastries = allPastries
                    updateAdapter(showingPastries)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow2
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest {
                    adapter.orders = it
                    orders = it
                    adapter.notifyDataSetChanged()
                }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = tableName
        viewModel.loadPastries()
    }

}