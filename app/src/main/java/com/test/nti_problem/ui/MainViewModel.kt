package com.test.nti_problem.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nti_problem.data.model.IPServerModel
import com.test.nti_problem.data.repository.MainRepository
import com.test.nti_problem.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    private val _result = MutableLiveData<Result<IPServerModel>>()

    val result: LiveData<Result<IPServerModel>>
        get() = _result

    init {
        getIP()
    }

    private fun getIP() = viewModelScope.launch {
        _result.postValue(Result.loading(null))
        mainRepository.fetchIP().let {
            try {
                _result.postValue(Result.success(it))
            }catch (error: Exception){
                _result.postValue(error.message?.let { error -> Result.error(error, null) })
            }
        }
    }
}