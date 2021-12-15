package com.confectinary.app.fragments.confectioner

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
import com.confectinary.app.databinding.FragmentInsertConfectionerBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ConfectionerDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InsertConfectionerFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertConfectionerBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Confectioner.value
    private var confecionaries: List<ConfectionaryDb>? = null

    private val viewModel: ConfectionerViewModel by viewModels{
        createFactory(ConfectionerViewModel(context?.let { AppDB.getDatabase(it) }))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertConfectionerBinding.inflate(inflater, container, false)

        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val selectedConfectionary = chooseConfectionarySpinner.selectedItem.toString()
                    val confectionary = confecionaries?.find { it.address == selectedConfectionary }
                        ?: throw RuntimeException()
                    val newItem = ConfectionerDb(
                        firstname = confectionerFirstnameInput.text.toString(),
                        lastname = confectionerLastnameInput.text.toString(),
                        patronymic = confectionerPatronymicInput.text.toString(),
                        salary = confectionerSalary.text.toString().toInt(),
                        experience = confectionerExperience.text.toString().toInt(),
                        rating = confectionerRating.text.toString().toInt(),
                        confectionaryId = confectionary.confectionaryId!!
                    )
                    viewModel.insert(newItem)

                    findNavController().navigate(
                        //Меняем для разных таблиц
                        R.id.action_insertConfectionerFragment_to_viewConfectionersFragment
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
        viewModel.loadConfectioners()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow1
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { it1 ->
                    confecionaries = it1
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
    }


}