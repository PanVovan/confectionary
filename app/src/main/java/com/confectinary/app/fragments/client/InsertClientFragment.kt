package com.confectinary.app.fragments.client

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentInsertClientBinding
import com.confectinary.app.db.AppDB
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.extentions.createFactory
import com.confectinary.app.fragments.adapter.entity.TableNames


class InsertClientFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertClientBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Client.value

    private val viewModel: ClientViewModel by viewModels{
        createFactory(ClientViewModel(context?.let { AppDB.getDatabase(it) }))
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = tableName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertClientBinding.inflate(inflater, container, false)


        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val newItem = ClientDb(
                        null,
                        clientNameInput.text.toString(),
                        clientSurnameInput.text.toString(),
                        clientPatronymicInput.text.toString(),
                        clientPhoneNumber.text.toString()
                    )
                    viewModel.insert(newItem)

                    findNavController().navigate(
                        //Меняем для разных таблиц
                        R.id.action_insertClientFragment_to_viewClientsFragment
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


}