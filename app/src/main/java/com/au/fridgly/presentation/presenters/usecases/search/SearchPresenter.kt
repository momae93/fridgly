package com.au.fridgly.presentation.presenters.usecases.search

import android.graphics.Bitmap
import com.au.fridgly.presentation.contracts.search.ISearchContract
import com.au.fridgly.presentation.tensorflow.Classifier
import com.au.fridgly.presentation.views.usecases.BaseActivity
import com.au.fridgly.presentation.views.usecases.scan.fragment.FragmentScanResult
import java.util.ArrayList
import javax.inject.Inject

class SearchPresenter: ISearchContract.Presenter {

    private val INPUT_SIZE = 224
    private val MODEL_PATH = "graph.tflite"
    private val LABEL_PATH = "labels.txt"

    private lateinit var view: ISearchContract.View

    @Inject constructor()

    fun setView(view: ISearchContract.View){
        this.view = view
    }

    override fun analyzeBitmap(bitmap: Bitmap) {
        val imageBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false)
        val mClassifier = Classifier(view.getViewActivity().assets, MODEL_PATH, LABEL_PATH, INPUT_SIZE)
        val list = mClassifier.recognizeImage(imageBitmap).map { it.title }
        (view.getViewActivity() as BaseActivity).showDialog(FragmentScanResult.newInstance(ArrayList(list)))
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {}

}