package com.confectinary.app.fragments.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentViewBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.OrderDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import com.confectinary.app.fragments.manager.ManagerViewModel
import com.confectinary.app.fragments.manager.ManagersAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class OrderFragment : Fragment() {

    private var _binding: FragmentViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: OrderViewModel by viewModels{
        createFactory(OrderViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Order.value
    //Меняем для разных таблиц
    private var adapter: OrdersAdapter = OrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewBinding.inflate(inflater, container, false)


        with(binding) {
            talbeitemsList.adapter = adapter
            addItem.setOnClickListener {
                findNavController().navigate(
                    //Меняем для разных таблиц
                    R.id.action_orderFragment_to_insertOrderFragment
                )
            }
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest {
                    adapter.values = it
                    adapter.notifyDataSetChanged()
                }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = tableName
        viewModel.loadOrders()
    }

}