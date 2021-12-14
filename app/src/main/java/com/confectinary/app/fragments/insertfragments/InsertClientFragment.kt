package com.confectinary.app.fragments.insertfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.confectinary.app.databinding.FragmentInsertClientBinding
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.fragments.adapter.entity.TableNames


class InsertClientFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertClientBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Confectionary.value

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertClientBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = tableName

        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val newItem = ClientDb(
                        0,
                        clientNameInput.text.toString(),
                        clientSurnameInput.text.toString(),
                        clientPatronymicInput.text.toString(),
                        clientPhoneNumber.text.toString()
                    )
                    Log.i("<---adding:", newItem.toString())
                    //запись newItem
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