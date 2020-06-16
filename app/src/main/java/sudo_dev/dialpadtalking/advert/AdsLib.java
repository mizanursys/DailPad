package sudo_dev.dialpadtalking.advert;

import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sudo_dev.dialpadtalking.advert.datamodel.Status;
import sudo_dev.dialpadtalking.advert.retrofit.APIInterface;
import sudo_dev.dialpadtalking.advert.retrofit.RetrofitClientInstance;

public class AdsLib {

    // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713

    public static String ADMOB_ID = "ca-app-pub-3940256099942544~3347511713";
    public static String ADMOB_BANNER_DEMO_ID = "ca-app-pub-3940256099942544/6300978111";
    //public static String ADMOB_INTERSTITIAL_DEMO_ID = "ca-app-pub-3940256099942544/1033173712";
    public static String ADMOB_INTERSTITIAL_DEMO_ID = "ca-app-pub-3940256099942544/1033173712";

    // INTERSTITIAL


//    public static void initAd(Context context)
//
//    {
//        MobileAds.initialize(context, ADMOB_ID);
//    }



    public static void checkSubStatus(String code) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Call<Status> call = apiInterface.getStatus(code);


        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                Log.e("response", response.toString());
                if (response.body() != null) {
                    if (response.body().getStat()) {
                        SP.setSubscriptionStatus(false);
                        Toast.makeText(AppConfig.getcontext(), "Subscription Status True", Toast.LENGTH_SHORT).show();
                    } else {
                        SP.setSubscriptionStatus(true);
                        Toast.makeText(AppConfig.getcontext(), "not a valid subscriber", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("Test",t.toString());
                SP.setSubscriptionStatus(true);
                Toast.makeText(AppConfig.getcontext(), "Status Getting Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


}

