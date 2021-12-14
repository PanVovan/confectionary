package com.confectinary.app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.confectinary.app.R
import com.confectinary.app.fragments.adapter.TableNamesAdapter
import com.confectinary.app.fragments.adapter.entity.TableNames

class RoleFragment : Fragment() {

    private lateinit var myView: View
    private lateinit var tableNamesList: RecyclerView
    private lateinit var chooseRoleSpinner: Spinner
    private val tableNamesAdapter = TableNamesAdapter(this::startViewTableFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_role, container, false)

        with(myView) {
            tableNamesList = findViewById(R.id.list)
            tableNamesList.layoutManager = LinearLayoutManager(context)
            tableNamesList.adapter = tableNamesAdapter

            chooseRoleSpinner = findViewById(R.id.choose_role_spinner)
            chooseRoleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    when (position) {
                        0 -> {
                            tableNamesAdapter.values = TableNames.adminTableNames
                        }
                        1 -> tableNamesAdapter.values = TableNames.clientTableNames
                        else -> tableNamesAdapter.values = TableNames.adminTableNames
                    }
                    tableNamesAdapter.notifyDataSetChanged()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) { }
            }
        }
        return myView
    }


    private fun startViewTableFragment(tableName: String) {
        when (tableName) {
            TableNames.TablesEnum.Confectionary.value ->
                findNavController().navigate(R.id.action_roleFragment_to_viewConfectionariesFragment)
            TableNames.TablesEnum.Client.value ->
                findNavController().navigate(R.id.action_roleFragment_to_viewClientsFragment)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
