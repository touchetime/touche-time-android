package com.touchetime.presentation.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touchetime.domain.usecase.FightUseCase
import com.touchetime.presentation.mapper.FightResponseMapper
import com.touchetime.presentation.model.FightFinish
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fightUseCase: FightUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _listFight = MutableLiveData<List<FightFinish>>()

    val listFight: LiveData<List<FightFinish>> = _listFight

    fun getListFight() {
        viewModelScope.launch {
            fightUseCase.getListFight()
                .flowOn(dispatcher)
                .catch {
                }
                .map {
                    FightResponseMapper().map(it)
                }
                .collect(::handleListFight)
        }
    }

    private fun handleListFight(listFight: List<FightFinish>) {
        viewModelScope.launch(dispatcher) {
            _listFight.postValue(listFight)
        }
    }
}
