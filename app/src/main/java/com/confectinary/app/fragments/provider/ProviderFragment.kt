package com.confectinary.app.fragments.provider

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
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProviderFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val viewModel: ProviderViewModel by viewModels{
        createFactory(ProviderViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Client.value
    //Меняем для разных таблиц
    private var adapter: ProviderAdapter = ProviderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = tableName

        with(binding) {
            talbeitemsList.adapter = adapter
            addItem.setOnClickListener {
                findNavController().navigate(
                    //Меняем для разных таблиц
                    R.id.action_providerFragment_to_providerInsertFragment
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
        viewModel.loadProviders()
    }
}