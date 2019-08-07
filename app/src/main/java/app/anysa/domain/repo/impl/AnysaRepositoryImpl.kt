package app.anysa.domain.repo.impl

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.CurrentUser
import app.anysa.domain.pojo.request.CheckInfoChangesRequest
import app.anysa.domain.pojo.response.CheckInfoChangesResponse
import app.anysa.domain.pojo.response.toInfoChangesStamp
import app.anysa.domain.repo.AnysaRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.domain.storage.UserStorage
import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.AuthApi
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnysaRepositoryImpl @Inject constructor(
        private val api: AuthApi,
        private val gson: Gson,
        private val authStorage: AuthStorage,
        private val userStorage: UserStorage)
    : AnysaRepository {

    override fun getCurrentUser(): Single<CurrentUser> {
        return userStorage.getUser()
    }

    override fun checkInfoChanges(): Single<BaseResponse<CheckInfoChangesResponse>> {
        val infoChangesStamp = userStorage.getInfoChangesStampRaw()
        val authInfo = authStorage.getAuthInfo()!!
        val checkInfoChangesRequest = CheckInfoChangesRequest(gson.toJson(infoChangesStamp), authInfo.id)
        val encryptedRequestBody = EncryptedRequestBody(checkInfoChangesRequest, method = EncryptedRequestBody.Method.ENCRYPT_MAIN,
                session = authInfo.session, password = authInfo.password)


        return api.checkInfoChanges(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
                .flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()
                    if (data.myinfo > infoChangesStamp.myinfo) {
                        return@flatMap userStorage.saveUser(data.currentUser)
                                .flatMap {
                                    Single.just(response)
                                }
                    }
                    return@flatMap Single.just(response)
                }.flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()

                    if (data.gflist > infoChangesStamp.gflist) { //todo
                        return@flatMap Single.just(response)
                    }
                    return@flatMap Single.just(response)
                }.flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()

                    if (data.total > infoChangesStamp.total) { //todo
                        return@flatMap Single.just(response)
                    }
                    return@flatMap Single.just(response)
                }.flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()

                    if (data.glist > infoChangesStamp.glist) { //todo
                        return@flatMap Single.just(response)
                    }
                    return@flatMap Single.just(response)
                }.flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()

                    if (data.flist > infoChangesStamp.flist) { //todo
                        return@flatMap Single.just(response)
                    }
                    return@flatMap Single.just(response)
                }.flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()

                    if (data.ginfo > infoChangesStamp.ginfo) { //todo
                        return@flatMap Single.just(response)
                    }
                    return@flatMap Single.just(response)
                }.flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()

                    if (data.finfo > infoChangesStamp.finfo) { //todo
                        return@flatMap Single.just(response)
                    }
                    return@flatMap Single.just(response)
                }.flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()

                    if (data.gfinfo > infoChangesStamp.gfinfo) { //todo
                        return@flatMap Single.just(response)
                    }
                    return@flatMap Single.just(response)
                }.flatMap { response ->
                    val data = response.data<CheckInfoChangesResponse>()
                    return@flatMap userStorage.saveInfoChangesStamp(data.toInfoChangesStamp())
                            .flatMap {
                                Single.just(response)
                            }
                }
    }
}
//{
//    "code": 0, "rest": "{\"myinfo\": 1,
//    \"gflist\": 0, " +
//        "\"group_info\": [], " +
//        "\"total\": 1, " +
//        "\"group_user\": [], " +
//        "\"self_info\": {\"user_id\": 1256, \"nick_name\": \"13444444446\", \"instruction\": \"lalala@gmail.com\", \"user_type\": 1, \"telephone\": \"13444444446\", \"email\": \"\", \"head_image\": \"\"}," +
//        "\"group_user_detail\": []," +
//        " \"glist\": 0," +
//        " \"flist\": 0, " +
//        "\"ginfo\": 0," +
//        " \"group_apply\": []," +
//        " \"friend_apply\": [], " +
//        "\"finfo\": 0, " +
//        "\"friends\": [{\"nickname\": \"system\", \"remark\": null, \"friend_type\": 3, \"friend_uid\": 202, \"nick_name\": \"\\u5ba2\\u670d\", \"instruction\": \"\", \"telephone\": \"system\", \"user_type\": 6, \"email\": \"system@gmail.com\", \"head_image\": \"http://47.88.231.69/MsgServer/HeadImage/thumb_201808301728082933.jpg\"}, " +
//        "             {\"nickname\": \"yc258569\", \"remark\": null, \"friend_type\": 3, \"friend_uid\": 242, \"nick_name\": \"vip\\u8d44\\u7ba1\\u5ba2\\u670d\", \"instruction\": \"\", \"telephone\": \"\", \"user_type\": 6, \"email\": \"vip_ziguan@qq.com\", \"head_image\": \"http://47.88.231.69/MsgServer/HeadImage/thumb_201805181120462582.jpg\"}], " +
//        "\"gfinfo\": 0}"
//}
