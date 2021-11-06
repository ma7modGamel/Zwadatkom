package com.safwa.zwadatkom.Network;


import com.safwa.zwadatkom.Models.LoginModel;
import com.safwa.zwadatkom.Models.ResendOtpModel;
import com.safwa.zwadatkom.Models.UserRegisterModel;
import com.safwa.zwadatkom.Models.VerifyOtpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceApi {


    @FormUrlEncoded
    @POST("signup")
    Call<UserRegisterModel> onRegester(
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("password_confirmation") String repassword
    );

    @FormUrlEncoded
    @POST("otp/validate")
    Call<VerifyOtpModel> onOtpValidate(
            @Field("otp") String otp,
            @Header("Authorization") String Authorization
    );

    @FormUrlEncoded
    @POST("otp/generate")
    Call<ResendOtpModel> onOtpٌResend(
            @Field("mobile") String mobile,
            @Header("Authorization") String Authorization
    );


//    @FormUrlEncoded
//    @POST("updatepassword")
//    Call<RepeatOrder>changePass(@Field("mobile") String mobile,
//                         @Field("password") String password);
//
//    @FormUrlEncoded
//    @POST("cart/orders")
//    Call<BaseModel>onPay(@Field("notes") String notes,
//                         @Field("time") String time,
//                         @Field("lat") String lat,
//                         @Field("long") String longtude,
//                         @Field("carts") String jsonArray,
//                         @Header("Authorization") String Authorization);
//
//
//    @GET("notification/{notification_id}/delete")
//    Call<DeletNotiModel>onDeleteNotification(@Path("notification_id") String notification_id,
//                                             @Header("Authorization") String Authorization);
//
//    @FormUrlEncoded
//    @POST("ratetyaar")
//    Call<RateDelegateModel>onRatePoilets(@Field("delegate_id") String delegate_id,
//                                         @Field("comment") String comment,
//                                         @Field("stars") String stars,
//                                         @Header("Authorization") String Authorization);
//    @FormUrlEncoded
//    @POST("auth/is_active")
//    Call<VerifyModel>onActiveUser(@Field("mobile") String mobile);
//
//
//    @FormUrlEncoded
//    @POST("ratetyaar")
//    Call<ProviderRateActivity>onRateProvider(@Field("supermarket_id") String supermarket_id,
//                                             @Field("comment") String comment,
//                                             @Field("stars") String stars,
//                                             @Header("Authorization") String Authorization);
//
//
//    @FormUrlEncoded
//    @POST("device_token")
//    Call<BaseResponce> getDeviceToken(@Field("device_token") String token, @Query("lang") String lang,
//                                      @Header("Authorization") String header);
//
//
//    @GET("wallet")
//    Call<WalletModel> onGetWallet(@Header("Authorization") String Authorization);
//
//    @FormUrlEncoded
//    @POST("fast")
//    Call<FastOrderModel> onSendFastOrder(
//            @Field("address") String address,
//            @Field("mobile") String mobile,
//            @Field("lat") String lat,
//            @Field("long") String longetude,
//            @Field("fast_order") String fast_order,
//            @Header("Authorization") String Authorization);
//
//
//    @GET("categories/mostcommon")
//    Call<CategoryModel> onGetCategories(@Query("page") long page,@Query("lang") String lang);
//
//    @POST("cart/repeatOrder/{id}")
//    Call<RepeatOrder> onGetRepeatOrder(@Path(value = "id", encoded = true) String id_, @Header("Authorization") String Authorization);
//
//
//    @GET("search")
//    Call<SearchResultModel> onGetSearchResult(@Query("text") String word,@Query("page") long page, @Query("lang") String lang);
//
//    @GET("orders/{id}")
//    Call<DetailsOrderModel> onGetDetailsOrder(@Path(value = "id", encoded = true) String id_, @Query("lang") String lang,@Header("Authorization") String Authorization);
//    @GET("auth/notifications")
//    Call<List<GetNotification>> onGetNotifications(@Query("lang") String lang,@Header("Authorization") String Authorization);
//    @GET("categories")
//    Call<com.mrerror.parachut.Models.AllCattegoryModel.CategoryModel> onGetAllCategories(@Query("lang") String lang,@Query("page") long page);
//    @GET("supermarket")
//    Call<SuperMarketModel> onGetSuperMarkets(@Query("lang") String lang,@Query("page") long page);
//    @GET("allsupermarket")
//    Call<com.mrerror.parachut.Models.AllSuperMarket.SuperMarketModel> onGetAllSuperMarkets(@Query("lang") String lang,@Query("page") long page);
//    @GET("products-offers/mostcommen")
//    Call<OffersModel> onGetOffersModel(@Query("lang") String lang,@Query("page") long page);
//    @GET("products-offers")
//    Call<AllOffersModel> onGetAllOffersModel(@Query("lang") String lang,@Query("page") long page);
//    @GET("products-offers/max/price")
//    Call<AllOffersModel> onGetMAXAllOffersModel(@Query("supermarket_id")String supermarket_id,@Query("lang") String lang,@Query("page") long page);
//    @GET("products-offers/min/price")
//    Call<AllOffersModel> onGetMINAllOffersModel(@Query("supermarket_id")String supermarket_id,@Query("lang") String lang,@Query("page") long page );
//    @GET("products-offers/mostcommen")
//    Call<AllOffersModel> onGetMostCOMMONOffersModel(@Query("supermarket_id")String supermarket_id,@Query("lang") String lang,@Query("page") long page);
//    @GET("products/{id}")
//    Call<DetailsProductModel> onGetProductsModel(@Path(value = "id", encoded = true) String id_,@Query("lang") String lang);
//    @GET("products/similar/{id}")
//    Call<SimilarProductsModel> onGetSimilarProductsModel(@Path(value = "id", encoded = true) String id_,@Query("lang") String lang);
//
//    @GET("categories/{id}/products")
//    Call<AllProductCategories> onGetAllProductCategory(@Path(value = "id", encoded = true) String id_,@Query("lang") String lang,@Query("page") long page);
//    @GET("categories/{id}/products/max/price")
//    Call<AllProductCategories> onGetMAXAllProductCategory(@Path("id")String id_,@Query("lang") String lang,@Query("page") long page);
//    @GET("categories/{id}/products/min/price")
//    Call<AllProductCategories> onGetMINAllProductCategory(@Path("id") String id_,@Query("lang") String lang,@Query("page") long page );
//    @GET("categories/{id}/products/mostcommon")
//    Call<AllProductCategories> onGetMostCOMMONProductCategory(@Path("id") String id_,@Query("lang") String lang,@Query("page") long page);
//
//
///////////////
////******
////////////////
////////////
//
//
//    @GET("supermarket/{id}/products")
//    Call<AllProductCategories> onGetAllProductStores(@Path(value = "id", encoded = true) String id_,@Query("lang") String lang,@Query("page") long page);
//    @GET("supermarket/{id}/products/max/price")
//    Call<AllProductCategories> onGetMAXAllProductStores(@Path(value = "id", encoded = true) String id_,@Query("lang") String lang,@Query("page") long page);
//    @GET("supermarket/{id}/products/min/price")
//    Call<AllProductCategories> onGetMINAllProductStores(@Path(value = "id", encoded = true) String id_,@Query("lang") String lang,@Query("page") long page );
//    @GET("supermarket/{id}/products/mostcommon")
//    Call<AllProductCategories> onGetMostCOMMONProductStores(@Path(value = "id", encoded = true) String id_, @Query("lang") String lang, @Query("page") long page);
//
//    @GET("delivery/price")
//    Call<PriceModel> onGetPriceModel(@Query("page") long page);
//
//    @GET("about_us")
//    Call<AboutUsModel> OnGetAboutUs(@Query("lang") String lang);
//    @FormUrlEncoded
//    @POST("messages")
//    Call<ContactUsModel> onGetMessage(@Field("messages") String messages,
//                                      @Header("Authorization") String Authorization);
//    @GET("restaurant/orders/finished")
//    Call<FinishedOrdersModel> onGetFinishedOrders(@Query("lang") String lang, @Query("page") long page, @Header("Authorization") String Authorization);
//    @GET("restaurant/orders/pending")
//    Call<PendingOrdersModel> onGetPendingOrders(@Query("lang") String lang, @Query("page") long page, @Header("Authorization") String Authorization);
//    //MH
//    @GET("user")
//    Call<GetUserData> onGetUserData(@Header("Authorization") String Authorization );
//
////    @Headers({"Accept:application/json", "Content-Type:application/json;"})
//    @FormUrlEncoded
//    @POST("user/update")
//    Call<UpdateUserData> onUpdateUser(
//            @Field("name") String name,
//            @Field("mobile") String mobile,
//            @Field("email") String email,
//            @Field("address") String address,
//            @Field( "password") String password,
//            @Field("lat") String lat,
//            @Field("lng") String longitude,
//            @Header("Authorization") String Authorization
//    );
//
//
//    @FormUrlEncoded
//    @POST("auth/register")
//    Call<UserRegisterModel> onRegester(
//            @Field("name") String name,
//            @Field("mobile") String mobile,
//            @Field("address") String address,
//            @Field("lat") String lat,
//            @Field("long") String longitude,
//            @Field("password") String password,
//            @Field("password_confirmation") String repassword);
    @FormUrlEncoded
    @POST("signin")
    Call<LoginModel> onLogin(
            @Field("email") String mobile,
            @Field("password") String password
    );

//    @GET("supports/tyaar")
//    Call<contact_data> contact_data(@Header("Authorization") String Authorization );
//    @FormUrlEncoded
//    @POST("send-to-whatssapp")
//    Call<send_whats> send_wats(@Field("message") String message);
//
//    @Multipart
//    @POST("tyaar/order_tyaar")
//    Call<delgateOrder> delgate_order(@Part("order_price") String order_price ,
//                                     @Part("recipient_name") String recipient_name ,
//                                     @Part("recipient_phone") String recipient_phone ,
//                                     @Part("from_place") String from_place ,
//                                     @Part("to_place") String to_place ,
//                                     @Part("from_lat") String from_lat ,
//                                     @Part("from_long") String from_long ,
//                                     @Part("to_lat")String to_lat ,
//                                     @Part("to_long") String to_long ,
//                                     @Part("notes") String notes ,
//                                     @Part("fast_order_content") String fast_order_content ,
//                                     @Part("type") String type  ,
//                                     @Part MultipartBody.Part  image,
//                                     @Header("Authorization") String Authorization );
//
//    @GET("tyaar/user_orders_tyaar")
//    Call<AllOrdersDelgate> all_order_delgate(@Header("Authorization") String Authorization );
//
//    @GET("tyaar/order_details/{input}")
//    Call<DetailsOrdersDelgate> details_order_delgate(@Path("input") String input, @Header("Authorization") String Authorization );
//

}
