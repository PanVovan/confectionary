package com.confectinary.app.fragments.confectionary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentInsertConfectionaryBinding
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.fragments.adapter.entity.TableNames


class InsertConfectionaryFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertConfectionaryBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Confectionary.value

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertConfectionaryBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = tableName

        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {
                    val newItem = ConfectionaryDb(
                        0,
                        confectionaryAddressInput.text.toString(),
                        confectionaryIncomeInput.text.toString().toInt()
                    )
                    Log.i("<---adding:", newItem.toString())
                    //запись newItem
                } catch (e: Exception) {
                    Toast
                        .makeText(context, "Данные введены некорректно!", Toast.LENGTH_SHORT)
                        .show()
                }

                findNavController().navigate(
                    //Меняем для разных таблиц
                    R.id.action_insertConfectionaryFragment_to_viewConfectionariesFragment
                )
            }
        }

        return binding.root
    }


}