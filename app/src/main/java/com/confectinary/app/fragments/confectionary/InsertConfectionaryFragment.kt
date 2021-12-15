package com.confectinary.app.fragments.confectionary

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentInsertConfectionaryBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class InsertConfectionaryFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertConfectionaryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ConfectionaryViewModel by viewModels{
        createFactory(ConfectionaryViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Confectionary.value


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertConfectionaryBinding.inflate(inflater, container, false)


        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val newItem = ConfectionaryDb(
                        address = confectionaryAddressInput.text.toString(),
                        income = confectionaryIncomeInput.text.toString().toInt()
                    )
                    viewModel.insert(newItem)

                    findNavController().navigate(
                        //Меняем для разных таблиц
                        R.id.action_insertConfectionaryFragment_to_viewConfectionariesFragment
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
        viewModel.loadConfectionaries()
    }
}