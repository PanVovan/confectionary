package com.confectinary.app.fragments.viewfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentViewBinding
import com.confectinary.app.fragments.adapter.ClientsAdapter
import com.confectinary.app.fragments.adapter.entity.TableNames

class ViewClientsFragment : Fragment() {

    //Меняем для разных таблиц
    private var _binding: FragmentViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    //Меняем для разных таблиц
    private var tableName = TableNames.TablesEnum.Client.value
    //Меняем для разных таблиц
    private var myAdapter: ClientsAdapter = ClientsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = tableName

        with(binding) {
            talbeitemsList.adapter = myAdapter
            addItem.setOnClickListener {
                findNavController().navigate(
                    //Меняем для разных таблиц
                    R.id.action_viewClientsFragment_to_insertClientFragment
                )
            }
        }

        return binding.root
    }

    //будет срабатывать, когда мы создаем или возвращаемся во фрагмент (то есть всегда)
    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        //получаем данные из бд
        //myAdapter.values = newData
        //myAdapter.notifyDataSetChanged()
    }
}