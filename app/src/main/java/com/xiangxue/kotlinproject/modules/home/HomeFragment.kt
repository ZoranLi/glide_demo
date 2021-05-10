package com.xiangxue.kotlinproject.modules.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xiangxue.kotlinproject.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 同学们这是：首页的Fragment
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "首页", Toast.LENGTH_SHORT).show()
        val root: View? = /*inflater.inflate(R.layout.fragment_home, container, false)*/ null
        return root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    init {
        setHasOptionsMenu(true);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.personal_menu, menu)
    }
}
