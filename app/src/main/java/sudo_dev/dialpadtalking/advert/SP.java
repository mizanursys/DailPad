package sudo_dev.dialpadtalking.advert;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Atiar on 5/23/18.
 */

public class SP {

   // private static final String defaultServerAddress = "http://vios.datasysbd.net/";
    private static int numberOfLogin = 0;
    private static final String key_numberOfLogin = "totalLogin";

    private static final String PREFS_NAME = "preferenceName";

    private static final String key_PhoneNumber = "phoneNumber";
    private static final String key_APIToken = "apiTOken";
    private static final String key_APITokenID = "apiTOkenID";
    private static final String key_DoctorName = "doctorName";
    private static final String fcm_token = "fcmToken";
    private static final String sub_code = "subcode";


    private static final String ad_stat = "adstatus";
    private static final String sub_stat = "substatus";
    private static final String sub_click = "subclick";


    /*****************************//* Strat shared preferences */

    /******************************/

    public static boolean setPreference(String key, String value) {
        SharedPreferences settings = AppConfig.getcontext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getPreference(String key) {
        SharedPreferences settings = AppConfig.getcontext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "none");
    }

    public static boolean setPreferenceInt(String key, int value) {
        SharedPreferences settings = AppConfig.getcontext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getPreferenceInt(String key) {
        SharedPreferences settings = AppConfig.getcontext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, 0);
    }


    public static boolean setPreferenceBool(String key, Boolean value) {
        SharedPreferences settings = AppConfig.getcontext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getPreferenceBool(String key) {
        SharedPreferences settings = AppConfig.getcontext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }


    public static boolean isFirstTimeLogin(){
        numberOfLogin = getTotalNumberOfLogin();

        boolean isFirstTIme = false;
        isFirstTIme = numberOfLogin == 1 ? true : false ;
        return isFirstTIme;
    }

    public static void setNumberOfLogin() {
        numberOfLogin = getPreferenceInt(key_numberOfLogin);
        numberOfLogin ++;
        setPreferenceInt(key_numberOfLogin,numberOfLogin);    }

    public static int getTotalNumberOfLogin(){
        return numberOfLogin;
    }


    public static void setPhoneNumber(String phoneNumber){
        setPreference(key_PhoneNumber,phoneNumber);
    }

    public static String getPhoneNumber(){
        return getPreference(key_PhoneNumber);
    }

    public static void setApiToken(String apiToken){
        setPreference(key_APIToken,apiToken);

    }

    public static String getApiToken(){
        return "Bearer "+getPreference(key_APIToken);
    }


    public static void setApiTokenID(String apiTokenID){
        setPreference(key_APITokenID,apiTokenID);

    }

    public static String getApiTokenID(){
        return getPreference(key_APITokenID);
    }


    public static void setDoctorName(String doctorName){
        setPreference(key_DoctorName,doctorName);

    }

    public static String getDoctorName(){
        return getPreference(key_DoctorName);
    }






    public static void setadStatus(Boolean status){
        setPreferenceBool(ad_stat,status);

    }

    public static Boolean getAdStatus(){
        return getPreferenceBool(ad_stat);
    }


    public static void setSubscriptionStatus(Boolean status){
        setPreferenceBool(sub_stat,status);

    }

    public static Boolean getSubscriptionStatus(){
        return getPreferenceBool(sub_stat);
    }


    public static void setSubscriptionClicked(Boolean status){
        setPreferenceBool(sub_click,status);

    }

    public static Boolean getSubscriptionClicked(){
        return getPreferenceBool(sub_click);
    }







    public static void setFcmToken(String referalCode){
        setPreference(fcm_token,referalCode);

    }

    public static String getFcmToken(){
        return getPreference(fcm_token);
    }


    public static void setSubCode(String sub){
        setPreference(sub_code,sub);

    }

    public static String getSubCode(){
        if(getPreference(sub_code)==null){
            return "NotSub";
        }else {
            return getPreference(sub_code);
        }
    }





    /*****************************//* End shared preferences *//******************************/


}
