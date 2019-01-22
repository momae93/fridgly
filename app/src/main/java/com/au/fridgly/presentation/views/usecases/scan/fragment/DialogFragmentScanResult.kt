package com.au.fridgly.presentation.views.usecases.scan.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.au.fridgly.R
import com.au.fridgly.domain.models.EAliment
import com.au.fridgly.presentation.contracts.scan.IScanResultContract
import com.au.fridgly.presentation.presenters.usecases.scan.ScanResultPresenter
import com.au.fridgly.presentation.views.usecases.MainActivity
import com.au.fridgly.presentation.views.usecases.scan.adapter.AlimentRecyclerAdapter
import com.au.fridgly.presentation.views.usecases.search.fragment.FragmentSearchResults
import javax.inject.Inject


private const val ARG_ALIMENTS = "ARG_ALIMENTS"

class FragmentScanResult : DialogFragment(), IScanResultContract.View {

    @BindView(R.id.fragment_scan_textView_main)
    lateinit var mainTextView: TextView

    @BindView(R.id.fragment_scan_imageView_main)
    lateinit var mainImageView: ImageView

    @BindView(R.id.fragment_scan_recyclerView_extra)
    lateinit var extraRecyclerView: RecyclerView

    private lateinit var aliments: List<String>
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var presenter: ScanResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
        arguments?.let {
            aliments = it.getStringArrayList(ARG_ALIMENTS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_scan_result, container, false)
        ButterKnife.bind(this, view)
        (activity as MainActivity).getComponent().inject(this)

        extraRecyclerView.layoutManager = GridLayoutManager(context, 1, GridLayout.HORIZONTAL, false)
        extraRecyclerView.setHasFixedSize(true)

        this.presenter.getAliments(aliments)

        return view
    }

    //region Inject Fields

    @Inject
    fun setPresenter(presenter: ScanResultPresenter){
        this.presenter = presenter
        this.presenter.setView(this)
    }

    //endregion


    override fun updateMainAliment(aliment: EAliment) {
        mainTextView.text = aliment.description
        val drawable = resources.getDrawable(aliment.image)
        mainImageView.setImageDrawable(drawable)
        mainImageView.setOnClickListener {
            this.dismiss()
            val fragmentSearchResults = FragmentSearchResults.newInstance(aliment.alimentName)
            (activity as MainActivity).replaceFragment(R.id.fragment_search_frameLayout_container, fragmentSearchResults)
        }
    }

    override fun updateExtraAliment(list: List<EAliment>) {
        extraRecyclerView.adapter = AlimentRecyclerAdapter(list, this)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener

    companion object {
        @JvmStatic
        fun newInstance(aliments: ArrayList<String>) =
            FragmentScanResult().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_ALIMENTS, aliments)
                }
            }
    }
}
