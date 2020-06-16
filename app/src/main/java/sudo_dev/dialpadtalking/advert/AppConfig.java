package sudo_dev.dialpadtalking.advert;

import android.app.Application;
import android.content.Context;

public class AppConfig extends Application {

   static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

    }

   public static Context getcontext(){
        return context;
   }

}
