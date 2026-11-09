package com.example.my_application.repository
import android.content.Context
import com.example.my_application.model.ScheduleResponse
import com.example.my_application.network.RetrofitClient

public class ScheduleRepository(context:Context) {
    private val api by lazy { RetrofitClient.getInstance(context) }
    suspend fun getScheduleByDay(day: String): List<ScheduleResponse> {
        return api.getScheduleByDay(day)
    }
}
