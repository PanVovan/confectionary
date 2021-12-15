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
                        firstname = managerNameInput.text.toString(),
                        lastname = managerSurnameInput.text.toString(),
                        patronymic = managerPatronymicInput.text.toString(),
                        phoneNumber = managerPhoneNumber.text.toString(),
                        salary = managerSalary.text.toString().toInt(),
                        experience = managerExperience.text.toString().toInt(),
                        sphere = managerSphere.text.toString()
                    )
                    confectionary.confectionaryId?.let { it1 -> viewModel.insert(newItem, it1) }

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
        viewModel.loadManagers()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow1.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { list ->

                    confecionaries = list
                    val spinnerAdapter = context?.let {
                        ArrayAdapter(
                            context!!,
                            android.R.layout.simple_spinner_item,
                            arrayOf("Кондитерская").union(list.map { it.address }).toTypedArray()
                        )
                    }
                    binding.chooseConfectionarySpinner.adapter = spinnerAdapter
                }
        }

    }
}