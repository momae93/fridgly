package com.au.fridgly.presentation.views.usecases.search.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife

import com.au.fridgly.R
import com.au.fridgly.presentation.contracts.search.ISearchContract
import com.au.fridgly.presentation.models.EState
import com.au.fridgly.presentation.presenters.usecases.search.SearchPresenter
import com.au.fridgly.presentation.views.usecases.MainActivity
import javax.inject.Inject


class FragmentSearch : Fragment(), ISearchContract.View {

    private val REQUEST_IMAGE_CAPTURE = 1

    @BindView(R.id.fragment_search_searchView_search)
    lateinit var searchView: SearchView

    @BindView(R.id.fragment_search_imageButton_scan)
    lateinit var scanImageButton: ImageButton

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        ButterKnife.bind(this, view)

        (activity as MainActivity).getComponent().inject(this)
        (activity as MainActivity).replaceFragment(R.id.fragment_search_frameLayout_container, FragmentSearchRandom())

        searchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            @SuppressLint("CheckResult")
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isBlank())
                {
                    val fragmentSearchResults = FragmentSearchResults.newInstance(query)
                    (activity as MainActivity).replaceFragment(R.id.fragment_search_frameLayout_container, fragmentSearchResults)
                    (activity as MainActivity).closeKeyboard()
                }
                else
                {
                    val message =  "Oups, avez-vous oublié de saisir un produit ?"
                    showToast(message)
                }
                return true
            }
        })

        scanImageButton.setOnClickListener {
            takePicture()
        }

        return view
    }

    private fun takePicture() {
        when (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.CAMERA)) {
            PackageManager.PERMISSION_GRANTED -> {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(activity?.packageManager)?.apply {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            }
            PackageManager.PERMISSION_DENIED -> {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    val message = "Your camera is required for the scan"
                    showToast(message)
                }
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_IMAGE_CAPTURE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (permissions[0] == Manifest.permission.CAMERA && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val message = "Thanks for accepting !"
                showToast(message)
            }
        }
    }

    //region Inject Fields

    @Inject
    fun setPresenter(presenter: SearchPresenter){
        this.presenter = presenter
        this.presenter.setView(this)
    }

    //endregion

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null){
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val imageBitmap = data.extras.get("data") as Bitmap
                this.presenter.analyzeBitmap(imageBitmap)
            }
        }
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

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

    override fun handleState(state: EState) {
    }


    interface OnFragmentInteractionListener
}
