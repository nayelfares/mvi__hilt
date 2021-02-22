package com.izadinia.mviwithhilt.repository

import com.izadinia.mviwithhilt.model.LoginWithEmailRequest
import com.izadinia.mviwithhilt.model.LoginResponse
import com.izadinia.mviwithhilt.network.OnboardingApi
import com.izadinia.mviwithhilt.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepo(private val onboardingApi: OnboardingApi) {

     fun login(email:String,password:String): Flow<DataState<LoginResponse>> = flow {
        emit(DataState.Loading)
        try {
            val loginRes: LoginResponse = onboardingApi.loginUserByEmail(
                LoginWithEmailRequest(email,password)
            )
            emit(DataState.Success(loginRes))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}