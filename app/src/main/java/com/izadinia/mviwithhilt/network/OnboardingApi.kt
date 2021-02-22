package com.izadinia.mviwithhilt.network

import com.izadinia.mviwithhilt.model.LoginWithEmailRequest
import com.izadinia.mviwithhilt.model.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


interface OnboardingApi {

    @POST("account/email/login")
    suspend fun loginUserByEmail(
        @Body loginWithEmailRequest: LoginWithEmailRequest
    ): LoginResponse

    @POST("account/email/login")
    fun login(
        @Body loginWithEmailRequest: LoginWithEmailRequest
    ): Observable<LoginResponse>
}