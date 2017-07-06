package com.nibmglobal.nibm.Utilities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormatSymbols;

/**
 * Created by mindlabs on 11/26/15.
 */
public class Util {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Context context;
    private static Util instance;

    private String folderName;

    public static Util getUtils() {
        return instance;
    }

    public Util(Context context) {

        this.context = context;
        String prefsFile = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        instance = this;
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    public void delete(String key) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit();
        }
    }


    public void savePref(String key, Object value) {
        delete(key);

        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-primitive preference");
        }

        editor.commit();
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public boolean isPrefExists(String key) {
        return sharedPreferences.contains(key);
    }


   /* public File pathForDocDownload() {

        File localFile = new File(Environment.getExternalStorageDirectory() + Const.FOLDER_NAME + folderName + "/");
        return localFile;

    }*/

    public String pathForWriteDoc() {

        String localFile = Environment.getExternalStorageDirectory() + Const.FOLDER_NAME + folderName + "/";
        return localFile;

    }



    public boolean initSettings(String type) {
        this.folderName = type;
        File localFile_download = new File(pathForWriteDoc());

        if ((!localFile_download.exists()) || (!localFile_download.isDirectory())) {
            localFile_download.mkdirs();

        }
        return true;

    }

    public void setupDownloadingProcedure(String fileUrl, String folderName, Context contextt, int fileFormat) {
        // TODO Auto-generated method stub
        String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);

        if (Util.getUtils().initSettings(folderName)) {
            new DownloadFile(contextt, fileFormat).execute(fileUrl, fileName);
        }

    }


    public void showDocFile(String string, Context cntx) {
        File file = new File(string);
//        PackageManager packageManager = context.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/msword");
//		 List list = packageManager.queryIntentActivities(testIntent,
//		 PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/msword");
        try {
            cntx.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No Application available to view Doc File",
                    Toast.LENGTH_SHORT).show();

            cntx.startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(Const.LINK_DOC)));
        }
    }

    public void showPdfFile(String string, Context cntx) {
        File file = new File(string);
//        PackageManager packageManager = context.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
//		 List list = packageManager.queryIntentActivities(testIntent,
//		 PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        try {
            cntx.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No Application available to view Pdf File",
                    Toast.LENGTH_SHORT).show();

            cntx.startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(Const.LINK_PDF)));
        }
    }

    public void composeEmail(String[] addresses, Context c) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle
        // this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        // intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            c.startActivity(intent);
        }
    }

    public void openWebPage(String url, Context c) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            c.startActivity(intent);
        }
    }
    public void dialPhoneNumber(String phoneNumber, Context c) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            c.startActivity(intent);
        }
    }
}
