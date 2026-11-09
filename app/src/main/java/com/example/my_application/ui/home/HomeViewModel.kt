package com.example.my_application.ui.home

import androidx.lifecycle.*
import android.util.Log
import com.example.my_application.repository.ScheduleRepository
import com.example.my_application.model.ScheduleResponse
import kotlinx.coroutines.launch
class HomeViewModel(private val repository: ScheduleRepository) :
    ViewModel() {
    private val _schedules = MutableLiveData<List<ScheduleResponse>>()
    val schedules: LiveData<List<ScheduleResponse>> get() = _schedules
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage
    fun getScheduleByDay(day: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getScheduleByDay(day)
                _schedules.value = response
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to load schedules"
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun clearError() {
        _errorMessage.value = null
    }
}