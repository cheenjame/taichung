package com.example.taichung.data.model

import com.google.gson.annotations.SerializedName

data class MarineRecreationResponse(
    @SerializedName("result")
    val result: MarineRecreationResult? = null,
    // 直接的資料列表也支援
    @SerializedName("records")  
    val records: List<MarineRecreationSpot>? = null,
    // 其他可能的格式
    @SerializedName("data")
    val data: List<MarineRecreationSpot>? = null
)

data class MarineRecreationResult(
    @SerializedName("records")
    val spots: List<MarineRecreationSpot>
)

data class MarineRecreationSpot(
    // 海域遊憩實際API欄位名稱
    @SerializedName("景點中文名稱")
    val chineseName: String? = null,
    @SerializedName("景點英文名稱")
    val englishName: String? = null,
    @SerializedName("景點特色簡述(中文)")
    val chineseDescription: String? = null,
    @SerializedName("景點特色簡述(英文)")
    val englishDescription: String? = null,
    @SerializedName("景點中文地址")
    val chineseAddress: String? = null,
    @SerializedName("景點英文地址")
    val englishAddress: String? = null,
    @SerializedName("景點服務電話")
    val servicePhone: String? = null,
    @SerializedName("網址")
    val website: String? = null,
    @SerializedName("(選填)地圖服務連結")
    val mapLink: String? = null,
    @SerializedName("開放時間")
    val openingHours: String? = null,
    @SerializedName("開放時間備註")
    val openingNotes: String? = null,
    @SerializedName("照片連結網址1")
    val photoUrl: String? = null,
    @SerializedName("照片中文說明1")
    val photoDescription: String? = null,
    @SerializedName("緯度")
    val latitude: String? = null,
    @SerializedName("經度")
    val longitude: String? = null,
    @SerializedName("海域活動風式編號")
    val activityCode: String? = null,
    @SerializedName("設施風示編號")
    val facilityCode: String? = null
) {
    // 提供安全的預設值
    val safeName: String get() = chineseName ?: englishName ?: "未知景點"
    val safeAddress: String get() = chineseAddress ?: englishAddress ?: "地址不詳"
    val safeDescription: String get() = chineseDescription ?: englishDescription ?: "描述不詳"
    val safePhone: String get() = servicePhone ?: ""
    val safeWebsite: String get() = website ?: ""
    val safeMapLink: String get() = mapLink ?: ""
    val safeOpeningHours: String get() = openingHours ?: ""
    val safeOpeningNotes: String get() = openingNotes ?: ""
    val safePhotoUrl: String get() = photoUrl ?: ""
    
    // 從地址中提取區域資訊
    val safeDistrict: String get() {
        return when {
            chineseAddress?.contains("大安區") == true -> "大安區"
            chineseAddress?.contains("大甲區") == true -> "大甲區"
            chineseAddress?.contains("清水區") == true -> "清水區"
            chineseAddress?.contains("梧棲區") == true -> "梧棲區"
            chineseAddress?.contains("龍井區") == true -> "龍井區"
            else -> ""
        }
    }
} 