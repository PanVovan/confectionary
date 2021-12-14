package com.confectinary.app.fragments.manager

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
import com.confectinary.app.databinding.FragmentInsertManagerBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InsertManagerFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertManagerBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Manager.value

    private val viewModel: ManagerViewModel by viewModels{
        createFactory(ManagerViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    private var confecionaries: List<ConfectionaryDb>? = null
    private var newId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertManagerBinding.inflate(inflater, container, false)


        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val selectedConfectionary = chooseConfectionarySpinner.selectedItem.toString()
                    val confectionary = confecionaries?.find { it.address == selectedConfectionary }
                        ?: throw RuntimeException()
                    val newItem = ManagerDb(
                        newId,
                        managerNameInput.text.toString(),
                        managerSurnameInput.text.toString(),
                        managerPatronymicInput.text.toString(),
                        managerPhoneNumber.text.toString(),
                        managerSalary.text.toString().toInt(),
                        managerExperience.text.toString().toInt(),
                        managerSphere.text.toString()
                    )
                    viewModel.insert(newItem, confectionary.confectionaryId)

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
        viewModel.loadConfectionaries()
        viewModel.loadManagers()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow1
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { it1 ->
                    confecionaries = it1
                    Log.i("<---", it1[0].toString())
                    val spinnerAdapter = context?.let {
                        ArrayAdapter(
                            context!!,
                            android.R.layout.simple_spinner_item,
                            arrayOf("Кондитерская").union(it1.map { i->i.address }).toTypedArray()
                        )
                    }
                    binding.chooseConfectionarySpinner.adapter = spinnerAdapter
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest {
                    newId = it.maxOfOrNull { i->i.managerId }?:0
                    newId += 1
                }
        }
    }
}