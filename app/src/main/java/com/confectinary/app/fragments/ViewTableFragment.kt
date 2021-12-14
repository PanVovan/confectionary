package com.confectinary.app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.confectinary.app.databinding.FragmentViewTableBinding
import com.confectinary.app.fragments.adapter.ConfectionariesAdapter
import com.confectinary.app.fragments.adapter.entity.TableNames
import com.google.android.material.tabs.TabLayout


class ViewTableFragment : Fragment() {

    private var _binding: FragmentViewTableBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*
     * К большому сожалению, адаптеры наследуются от
     * параметризованного класса, поэтому мы не можем сделать
     * переменную adapter, который будет хранить любой из адаптеров.
     * Поэтому будем обрашаться binding.talbeitemsList.adapter
     */

    private lateinit var tableName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewTableBinding.inflate(inflater, container, false)

        tableName = arguments?.getString(TableNames.TABLE_NAME_PARAM)?:""

        (activity as AppCompatActivity).supportActionBar?.title = tableName



        with (binding.talbeitemsList) {
            when (tableName) {
                TableNames.TablesEnum.Confectionary.value -> adapter = ConfectionariesAdapter()
                else -> {}
            }
        }

        return binding.root
    }


}