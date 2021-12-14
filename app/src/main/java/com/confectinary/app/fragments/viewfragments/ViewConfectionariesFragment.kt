package com.confectinary.app.fragments.viewfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentViewConfectionariesFragmentBinding
import com.confectinary.app.fragments.adapter.ConfectionariesAdapter
import com.confectinary.app.fragments.adapter.entity.TableNames


class ViewConfectionariesFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentViewConfectionariesFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Confectionary.value
    //Меняем для разных таблиц
    private var myAdapter: ConfectionariesAdapter = ConfectionariesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewConfectionariesFragmentBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = tableName

        with(binding) {
            talbeitemsList.adapter = myAdapter
            addItem.setOnClickListener {
                findNavController().navigate(
                    //Меняем для разных таблиц
                    R.id.action_viewConfectionariesFragment_to_insertConfectionaryFragment
                )
            }
        }

        return binding.root
    }
}