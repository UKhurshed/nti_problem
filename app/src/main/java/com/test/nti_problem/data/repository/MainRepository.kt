package com.test.nti_problem.data.repository

import com.test.nti_problem.data.service.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun fetchIP() = apiHelper.fetchIP()
}