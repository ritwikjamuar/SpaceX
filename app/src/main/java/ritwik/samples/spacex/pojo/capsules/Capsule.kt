package ritwik.samples.spacex.pojo.capsules

import com.squareup.moshi.Json

data class Capsule(
    @field: Json(name = "capsule_serial") val serial: String,
    @field: Json(name = "capsule_id") val id: String,
    @field: Json(name = "status") val status: String,
    @field: Json(name = "original_launch") val launchTime: String,
    @field: Json(name = "original_launch_unix") val launchTimeUnix: Long,
    @field: Json(name = "missions") val missions: List<Mission>?,
    @field: Json(name = "landings") val landings: Long,
    @field: Json(name = "type") val type: String,
    @field: Json(name = "details") val details: String,
    @field: Json(name = "reuse_count") val reuseCount: Long
) {

    /**Provides the name of the Capsule, which is the combination of Serial Number and Type of Capsule.
     * @return [String] denoting the name of the [Capsule].*/
    fun getCapsuleName(): String = "$serial : $type"

    /**Provides the Count of [Mission].
     * @return [Int] denoting the Size of [Mission].*/
    fun getMissionCount(): Int = missions?.size ?: 0

}

data class Mission(
    @field: Json(name = "name") val name: String,
    @field: Json(name = "flight") val flight: Long
)