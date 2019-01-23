package com.au.fridgly.presentation.views.usecases.recipe.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.Group
import android.support.v4.app.DialogFragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.au.fridgly.R
import com.au.fridgly.presentation.contracts.recipe.IRecipeStepContract
import com.au.fridgly.presentation.models.EState
import com.au.fridgly.presentation.models.recipeDetails.StepUI
import com.au.fridgly.presentation.views.usecases.recipe.adapter.RecipeEquipmentRecyclerAdapter
import com.au.fridgly.presentation.views.usecases.recipe.adapter.RecipeIngredientRecyclerAdapter

private const val ARG_STEPS = "ARG_STEPS"

class DialogFragmentStep : DialogFragment(), IRecipeStepContract.View {

    private lateinit var steps: List<StepUI>
    private var currentStep = 1
    private var listener: OnFragmentInteractionListener? = null

    @BindView(R.id.fragment_step_textView_instruction)
    lateinit var instructionTextView: TextView

    @BindView(R.id.fragment_step_textView_steps)
    lateinit var stepsTextView: TextView

    @BindView(R.id.fragment_step_group_equipment)
    lateinit var equipmentGroup: Group

    @BindView(R.id.fragment_step_group_ingredients)
    lateinit var ingredientGroup: Group

    @BindView(R.id.fragment_step_recyclerView_equipment)
    lateinit var equipmentRecyclerView: RecyclerView

    @BindView(R.id.fragment_step_recyclerView_ingredients)
    lateinit var ingredientRecyclerView: RecyclerView

    @BindView(R.id.fragment_step_button_previous)
    lateinit var previousButton: Button

    @BindView(R.id.fragment_step_button_next)
    lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
        arguments?.let {
            steps = it.getParcelableArrayList(ARG_STEPS)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_step, container, false)
        ButterKnife.bind(this, view)

        equipmentRecyclerView.layoutManager = GridLayoutManager(context, 1, GridLayout.HORIZONTAL, false)
        ingredientRecyclerView.layoutManager = GridLayoutManager(context, 1, GridLayout.HORIZONTAL, false)

        previousButton.setOnClickListener { previousStep() }
        nextButton.setOnClickListener { nextStep() }
        updateStepDetails()

        return view
    }


    @SuppressLint("SetTextI18n")
    override fun updateStepDetails() {
        stepsTextView.text = "$currentStep of ${steps.count()}"
        updateNavigationButton()

        steps[currentStep - 1].apply {
            instructionTextView.text = steps[currentStep - 1].description

            if (this.equipments != null && this.equipments.count() > 0){
                equipmentGroup.visibility = View.VISIBLE
                equipmentRecyclerView.adapter = RecipeEquipmentRecyclerAdapter(this.equipments, this@DialogFragmentStep)
            }
            else{
                equipmentGroup.visibility = View.GONE
            }

            if (this.ingredients != null && this.ingredients.count() > 0){
                ingredientGroup.visibility = View.VISIBLE
                ingredientRecyclerView.adapter = RecipeIngredientRecyclerAdapter(this.ingredients, this@DialogFragmentStep)
            }
            else{
                ingredientGroup.visibility = View.GONE
            }
        }
    }


    override fun updateNavigationButton() {
        if (currentStep == steps.count())
            nextButton.text = "Finish"
        else
            nextButton.text = "Next"
        if (currentStep != 1)
            previousButton.visibility = View.VISIBLE
        else
            previousButton.visibility = View.GONE
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

    override fun handleState(state: EState) {
    }

    override fun previousStep(){
        if (currentStep != 1){
            currentStep--
            updateStepDetails()
        }
    }

    override fun nextStep(){
        if (currentStep == steps.count())
            this.dismiss()
        else{
            currentStep++
            updateStepDetails()
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

    interface OnFragmentInteractionListener

    companion object {
        @JvmStatic
        fun newInstance(steps: ArrayList<StepUI>) =
            DialogFragmentStep().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_STEPS, steps)
                }
            }
    }
}
