package com.test.nti_problem.data.service

import com.test.nti_problem.data.model.IPServerModel
import retrofit2.http.GET

interface IPService {
    @GET("awstest-service/")
    suspend fun fetchIP(): IPServerModel
}