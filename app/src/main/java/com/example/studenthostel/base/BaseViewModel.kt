package com.example.studenthostel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<
        State : BaseViewModel.Status,
        Event : BaseViewModel.Event,
        Effect : BaseViewModel.Effect>(
    initialState: State
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state get() = _state.value

    val currentState get() = _state

    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect: SharedFlow<Effect> get() = _effect

    abstract fun event(event: Event)

    protected fun setState(state: State) = _state.update { state }

    protected fun sendEffect(effect: Effect) = viewModelScope.launch { _effect.emit(effect) }

    interface Status
    interface Effect
    interface Event
}