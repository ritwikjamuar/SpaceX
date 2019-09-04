package ritwik.samples.spacex.pojo.launches

import com.squareup.moshi.Json

data class RocketSecondStage (
	@field: Json ( name = "block" )		val block :		Int?,
	@field: Json ( name = "payloads" )	val payloads :	List <Payload>?
)