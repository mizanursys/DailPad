package sudo_dev.dialpadtalking.advert.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import sudo_dev.dialpadtalking.advert.datamodel.Status;

public interface APIInterface {



/*    @Headers({"Accept: application/json"})
    @POST("api/patient_register")
    Call<PatientModel> getInitialLoginData(@Query("phone") String phone, @Query("onesignal_id") String fcm_token);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/patient_profile_get")
    Call<PatientProfileModel> getPatientProfile(@Query("id") String id, @Header("Authorization") String auth);



   // @Headers({"Accept: application/json"})
    @Multipart
    @POST("api/patient_update")
    Call<UpdateProfileDataModel> updateProfile(@PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file, @Header("Authorization") String auth);


    @Headers({"Accept: application/json"})
    @GET("api/nearby_doctors")
    Call<List<NearbyDoctorModel>> getNearByDoctors(@Query("longitude") String longitude, @Query("latitude") String latitude, @Query("category_id") String catid, @Header("Authorization") String auth);

    @Headers({"Accept: application/json"})
    @GET("api/categories")
    Call<List<CategoriesModel>> getMenuCategories(@Header("Authorization") String auth);

    @Headers({"Accept: application/json"})
    @POST("api/consultation")
    Call<ConsultModel> consultDoc(@Query("doctor_id") String doc_id, @Query("patient_id") String pat_id, @Header("Authorization") String auth);*/


    //tobechanged

    @Headers({"Accept: application/json"})
    @GET("api/sub_check")
    Call<Status> getStatus(@Query("code") String code);

/*    @Headers({"Accept: application/json"})
    @GET("api/submit_code")
    Call<Boolean> checkCode(@Query("fahimvai") String code);*/


}


