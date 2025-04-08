package com.example.studenthostel.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * A base class for all fragments following the MVI architecture.
 *
 * @param Binding The type of [ViewBinding] used by the fragment.
 * @param State The type representing the UI state.
 * @param Event The type representing user/system events.
 * @param Effect The type representing one-time side effects (navigation, toasts, etc.).
 */
abstract class BaseFragment<
        Binding : ViewBinding,
        State : BaseViewModel.Status,
        Event : BaseViewModel.Event,
        Effect : BaseViewModel.Effect> : Fragment() {

    // Backing property for ViewBinding. Cleared in onDestroyView.
    private var _binding: Binding? = null

    /**
     * Non-nullable access to the view binding.
     * Safe to use only between onCreateView and onDestroyView.
     */
    val binding: Binding
        get() = _binding!!

    /**
     * Navigation controller for this fragment.
     */
    protected lateinit var navController: NavController

    /**
     * ViewModel that handles state, events, and effects for the fragment.
     */
    abstract val viewModel: BaseViewModel<State, Event, Effect>

    /**
     * Inflates the view using the provided ViewBinding.
     */
    final override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    /**
     * Called when the view is created. Initializes navigation and observers.
     */
    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = binding // Ensures non-null binding
        navController = findNavController()
        setContent()
        observeStateWithLifeCycle(viewModel.currentState)
        observeEffectWithLifeCycle(viewModel.effect)
    }

    /**
     * Called when the view is destroyed. Clears the binding reference to avoid memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Sets up UI components and initial screen logic.
     */
    abstract fun setContent()

    /**
     * Creates the ViewBinding instance. Must be implemented in subclasses.
     *
     * @param inflater LayoutInflater to use for inflating the view.
     * @param container Optional view container.
     * @return A ViewBinding instance.
     */
    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    /**
     * Called whenever the UI state is updated.
     *
     * @param viewState The new UI state.
     */
    abstract fun onUiStateChange(viewState: State)

    /**
     * Called whenever a one-time side effect is emitted.
     *
     * @param viewEffect The emitted effect (e.g., navigation, toast).
     */
    abstract fun onEffect(viewEffect: Effect)

    /**
     * Observes state changes from the ViewModel using lifecycle-aware collection.
     */
    private fun observeStateWithLifeCycle(state: StateFlow<State>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                state.collect {
                    Log.e("Tag viewState", it.toString())
                    onUiStateChange(it)
                }
            }
        }
    }

    /**
     * Observes one-time effects from the ViewModel using lifecycle-aware collection.
     */
    private fun observeEffectWithLifeCycle(effect: SharedFlow<Effect>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                effect.collect {
                    onEffect(it)
                }
            }
        }
    }
}
