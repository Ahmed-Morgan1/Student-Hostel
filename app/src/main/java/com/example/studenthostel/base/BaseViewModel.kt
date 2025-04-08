package com.example.studenthostel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * A base class for implementing the MVI (Model-View-Intent) pattern in a ViewModel.
 *
 * @param State The UI state that holds the screen's data.
 * @param Event The user or system actions that trigger changes.
 * @param Effect One-time side effects such as navigation or toasts.
 * @param initialState The default state when the ViewModel is initialized.
 */
abstract class BaseViewModel<
        State : BaseViewModel.Status,
        Event : BaseViewModel.Event,
        Effect : BaseViewModel.Effect>(
    initialState: State
) : ViewModel() {

    // Mutable state holder for the current screen state
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)

    /**
     * Returns the latest value of the UI state.
     */
    val state: State
        get() = _state.value

    /**
     * A StateFlow stream of UI state, used to collect state updates.
     */
    val currentState = _state.asStateFlow()

    // Mutable shared flow for one-time effects (e.g., navigation, toast messages)
    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()

    /**
     * A read-only SharedFlow to observe emitted side effects from the ViewModel.
     */
    val effect = _effect.asSharedFlow()

    /**
     * Handles UI events such as button clicks or input changes.
     *
     * Must be implemented in subclasses to handle each type of Event.
     *
     * @param event An event triggered by the user or system.
     */
    abstract fun event(event: Event)

    /**
     * Updates the current UI state.
     *
     * @param state The new state to be applied.
     */
    protected fun setState(state: State) {
        _state.update { state }
    }

    /**
     * Emits a one-time side effect (like navigation or showing a message).
     *
     * @param effect The effect to emit.
     */
    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    /**
     * Marker interface for UI State types.
     */
    interface Status

    /**
     * Marker interface for UI Effect types (navigation, toasts, etc).
     */
    interface Effect

    /**
     * Marker interface for UI Event types (user actions).
     */
    interface Event
}
