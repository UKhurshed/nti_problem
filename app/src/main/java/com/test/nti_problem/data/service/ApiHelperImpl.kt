package com.test.nti_problem.data.service

import com.test.nti_problem.data.model.IPServerModel
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val ipService: IPService) : ApiHelper {
    override suspend fun fetchIP(): IPServerModel {
        return ipService.fetchIP()
    }
}