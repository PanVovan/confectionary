package com.confectinary.app.fragments.insertfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentInsertClientBinding
import com.confectinary.app.databinding.FragmentInsertManagerBinding
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.fragments.adapter.entity.TableNames

class InputManagerFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentInsertManagerBinding? = null
    private val binding get() = _binding!!

    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Manager.value

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertManagerBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = tableName

        with(binding) {
            addItemBtn.setOnClickListener {
                //Меняем для разных таблиц
                try {

                    val newItem = ManagerDb(
                        0,
                        managerNameInput.text.toString(),
                        managerSurnameInput.text.toString(),
                        managerPatronymicInput.text.toString(),
                        managerPhoneNumber.text.toString(),
                        managerSalary.text.toString().toInt(),
                        managerExperience.text.toString().toInt(),
                        managerSphere.text.toString()
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
                    R.id.action_inputManagerFragment_to_viewManagerFragment
                )
            }
        }

        return binding.root
    }


}