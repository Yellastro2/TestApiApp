package com.yelldev.testapiapp.api

import com.yelldev.testapiapp.data.NeoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("neo/rest/v1/feed")
	suspend fun getItemsData(@Query("start_date")startDate: String = "2015-09-07",
							 @Query("end_date")endDate: String = "2015-09-08",
							 @Query("api_key")apiKey: String = "NOv8Y1KQccRS7VJxeTOPPFbGSlCidULSUiifRxI4"): Response<NeoResponse>
}