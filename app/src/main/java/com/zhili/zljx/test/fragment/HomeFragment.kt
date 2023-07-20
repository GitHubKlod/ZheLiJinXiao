package com.zhili.zljx.test.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.databinding.FragmentHomeBinding

class HomeFragment : BaseMvvmFragment<HomeViewModel, FragmentHomeBinding>() {

    private var adapterA = TestAdapter()

//    private var totalDy = 0
//    private var index = -1
    override fun initView(savedInstanceState: Bundle?) {

        adapterA.addOnItemChildClickListener(R.id.content) { adapter, view, position ->

            showToast("点击了-")
//            mViewModel.postValueChange(position)
            var list =adapter.items[position]
            list.content = "点击$5"
            adapter[position] = list
//            adapter.notifyItemChanged(position)

        }
        mViewBind.rv.apply {
            layoutManager = LinearLayoutManager(mContext)
            this.adapter = adapterA

        }
    }

    override fun lazyLoadData() {

    }

    override fun createObserver() {

        mViewModel.listData.observe(this){

            showToast("数据刷新了+${it[0].content}")
            adapterA.submitList(it)

//            adapterA.notifyDataSetChanged()
//            adapterA.notifyItemChanged(1)
//            adapterA[0] = TestBean()

        }

    }

}