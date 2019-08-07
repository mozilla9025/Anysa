package app.anysa.domain.pojo.response

import android.os.Parcelable
import app.anysa.domain.pojo.CurrentUser
import app.anysa.domain.pojo.InfoChangesStamp
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckInfoChangesResponse(
        @SerializedName("myinfo") val myinfo: Int,
        @SerializedName("gflist") val gflist: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("glist") val glist: Int,
        @SerializedName("flist") val flist: Int,
        @SerializedName("ginfo") val ginfo: Int,
        @SerializedName("finfo") val finfo: Int,
        @SerializedName("gfinfo") val gfinfo: Int,
        @SerializedName("self_info") var currentUser: CurrentUser
) : Parcelable {

}

fun CheckInfoChangesResponse.toInfoChangesStamp(): InfoChangesStamp {
    return InfoChangesStamp(
            myinfo = myinfo,
            gflist = gflist,
            total = total,
            glist = glist,
            flist = flist,
            ginfo = ginfo,
            finfo = finfo,
            gfinfo = gfinfo)
}

//"code": 0, "rest": "{\"myinfo\": 1,
//\"gflist\": 0, " +
//"\"group_info\": [], " +
//"\"total\": 1, " +
//"\"group_user\": [], " +
//"\"self_info\": {\"user_id\": 1256, \"nick_name\": \"13444444446\", \"instruction\": \"lalala@gmail.com\", \"user_type\": 1, \"telephone\": \"13444444446\", \"email\": \"\", \"head_image\": \"\"}," +
//"\"group_user_detail\": []," +
//" \"glist\": 0," +
//" \"flist\": 0, " +
//"\"ginfo\": 0," +
//" \"group_apply\": []," +
//" \"friend_apply\": [], " +
//"\"finfo\": 0, " +
//"\"friends\": [{\"nickname\": \"system\", \"remark\": null, \"friend_type\": 3, \"friend_uid\": 202, \"nick_name\": \"\\u5ba2\\u670d\", \"instruction\": \"\", \"telephone\": \"system\", \"user_type\": 6, \"email\": \"system@gmail.com\", \"head_image\": \"http://47.88.231.69/MsgServer/HeadImage/thumb_201808301728082933.jpg\"}, " +
//"             {\"nickname\": \"yc258569\", \"remark\": null, \"friend_type\": 3, \"friend_uid\": 242, \"nick_name\": \"vip\\u8d44\\u7ba1\\u5ba2\\u670d\", \"instruction\": \"\", \"telephone\": \"\", \"user_type\": 6, \"email\": \"vip_ziguan@qq.com\", \"head_image\": \"http://47.88.231.69/MsgServer/HeadImage/thumb_201805181120462582.jpg\"}], " +
//"\"gfinfo\": 0}"