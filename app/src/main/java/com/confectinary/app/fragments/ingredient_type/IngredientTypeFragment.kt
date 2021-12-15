package com.confectinary.app.fragments.ingredient_type

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
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import com.confectinary.app.fragments.client.ClientViewModel
import com.confectinary.app.fragments.client.ClientsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class IngredientTypeFragment  : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val viewModel: IngredientTypeViewModel by viewModels{
        createFactory(IngredientTypeViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.IngredientType.value
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewBinding.inflate(inflater, container, false)


        with(binding) {
            talbeitemsList.adapter = adapter
            addItem.setOnClickListener {
                findNavController().navigate(
                    //Меняем для разных таблиц
                    R.id.action_ingredientTypeFragment_to_ingredientTypeInsertFragment
                )
            }
        }

        return binding.root
    }

    private fun deleteItem(item: IngredientTypeDb) {
        viewModel.delete(item)
    }

    //Меняем для разных таблиц
    private var adapter: IngredientTypeAdapter = IngredientTypeAdapter(this::deleteItem)

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
        viewModel.loadIngredientTypes()
    }
}