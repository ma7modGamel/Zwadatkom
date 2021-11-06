package com.safwa.zwadatkom.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static final String LOCATION_PREFS = "Location_Prefs";

    public static void setLocale(Context context,String lang) {

        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(lang)); // API 17+ only.
        }
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }

    public static String intentToString(Intent intent) {
        if (intent == null) {
            return null;
        }

        return intent.toString() + " " + bundleToString(intent.getExtras());
    }
//    public static String FixPhoneNumber(Context ctx, String rawNumber)
//    {
//        String      fixedNumber = "";
//
//        // get current location iso code
//        TelephonyManager telMgr = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
//        String              curLocale = telMgr.getNetworkCountryIso().toUpperCase();
//
//        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
//        Phonenumber.PhoneNumber     phoneNumberProto;
//
//        // gets the international dialling code for our current location
//        String              curDCode = String.format("%d", phoneUtil.getCountryCodeForRegion(curLocale));
//        String              ourDCode = "";
//
//        if(rawNumber.indexOf("+") == 0)
//        {
//            int     bIndex = rawNumber.indexOf("(");
//            int     hIndex = rawNumber.indexOf("-");
//            int     eIndex = rawNumber.indexOf(" ");
//
//            if(bIndex != -1)
//            {
//                ourDCode = rawNumber.substring(1, bIndex);
//            }
//            else if(hIndex != -1)
//            {
//                ourDCode = rawNumber.substring(1, hIndex);
//            }
//            else if(eIndex != -1)
//            {
//                ourDCode = rawNumber.substring(1, eIndex);
//            }
//            else
//            {
//                ourDCode = curDCode;
//            }
//        }
//        else
//        {
//            ourDCode = curDCode;
//        }
//
//        try
//        {
//            phoneNumberProto = phoneUtil.parse(rawNumber, curLocale);
//        }
//
//        catch (NumberParseException e)
//        {
//            return rawNumber;
//        }
//
//        if(curDCode.compareTo(ourDCode) == 0)
//            fixedNumber = phoneUtil.format(phoneNumberProto, PhoneNumberFormat.NATIONAL);
//        else
//            fixedNumber = phoneUtil.format(phoneNumberProto, PhoneNumberFormat.INTERNATIONAL);
//
//        return fixedNumber.replace(" ", "");
//    }

    public static String bundleToString(Bundle bundle) {
        StringBuilder out = new StringBuilder("Bundle[");

        if (bundle == null) {
            out.append("null");
        } else {
            boolean first = true;
            for (String key : bundle.keySet()) {
                if (!first) {
                    out.append(", ");
                }

                out.append(key).append('=');

                Object value = bundle.get(key);

                if (value instanceof int[]) {
                    out.append(Arrays.toString((int[]) value));
                } else if (value instanceof byte[]) {
                    out.append(Arrays.toString((byte[]) value));
                } else if (value instanceof boolean[]) {
                    out.append(Arrays.toString((boolean[]) value));
                } else if (value instanceof short[]) {
                    out.append(Arrays.toString((short[]) value));
                } else if (value instanceof long[]) {
                    out.append(Arrays.toString((long[]) value));
                } else if (value instanceof float[]) {
                    out.append(Arrays.toString((float[]) value));
                } else if (value instanceof double[]) {
                    out.append(Arrays.toString((double[]) value));
                } else if (value instanceof String[]) {
                    out.append(Arrays.toString((String[]) value));
                } else if (value instanceof CharSequence[]) {
                    out.append(Arrays.toString((CharSequence[]) value));
                } else if (value instanceof Parcelable[]) {
                    out.append(Arrays.toString((Parcelable[]) value));
                } else if (value instanceof Bundle) {
                    out.append(bundleToString((Bundle) value));
                } else {
                    out.append(value);
                }

                first = false;
            }
        }

        out.append("]");
        return out.toString();
    }


    public  static String randomColor(){
        Random random = new Random();
        String myRandomColor = String.format("#%06x", random.nextInt(256 * 256 * 256));
        Log.e("SS",myRandomColor);
        return  myRandomColor;
    }

    public static void requestFocus(View view, Window window) {
        if (view.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

        public static String timeAgo(String time) {
            try
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
                Date past = format.parse(time);
                Date now = new Date();
                long seconds=TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
                long minutes=TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
                long hours=TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
                long days= TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());
//
//          System.out.println(TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime()) + " milliseconds ago");
//          System.out.println(TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago");
//          System.out.println(TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago");
//          System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");

                if(seconds<60)
                {
                    return (seconds+" seconds ago");
                }
                else if(minutes<60)
                {
                    return (minutes+" minutes ago");
                }
                else if(hours<24)
                {
                    return (hours+" hours ago");
                }
                else
                {
                    return (days+" days ago");
                }
            }
            catch (Exception j){
Log.e("cc",j.getMessage());                return "now";
            }
        }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.i("hideKeyboeard", "yes");
        }
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String imageToString64(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }


    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static Bitmap convertToBitmap(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",") + 1),
                Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String ConvertMiliSecToDateFormat(long timeInMilli) {

        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMilli);
        String format = formatter.format(calendar.getTime());
        return format;
    }


    public static String ConvertDateToClock(Date date) {

        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm", Locale.US);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();

        String format = formatter.format(date);
        return format;
    }
}
