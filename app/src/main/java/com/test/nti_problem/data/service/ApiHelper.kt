package com.test.nti_problem.data.service

import com.test.nti_problem.data.model.IPServerModel

interface ApiHelper {
    suspend fun fetchIP(): IPServerModel
}