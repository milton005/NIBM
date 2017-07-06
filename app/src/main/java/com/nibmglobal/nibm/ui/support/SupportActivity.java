package com.nibmglobal.nibm.ui.support;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;

/**
 * Created by mindlabs on 12/9/15.
 */
public class SupportActivity extends BaseActivity {

    private Toolbar toolbar;
    private ImageView imageViewWeb;
    private ImageView imageViewMail;
    private ImageView imageViewFacebook;
    private ImageView imageViewCall;
    private AlertDialog.Builder builder;
    private Resources res;

    /*public static void start(Context context) {
        Intent i = new Intent(context, SupportActivity.class);
        context.startActivity(i);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        setActionbar();
        res = getResources();
        initUi();
        initListner();
    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Support");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUi() {
        imageViewWeb = (ImageView) findViewById(R.id.imageWeb);
        imageViewMail = (ImageView) findViewById(R.id.imageMail);
        imageViewFacebook = (ImageView) findViewById(R.id.imageFacebook);
        imageViewCall = (ImageView) findViewById(R.id.imageCall);

    }
    private void initListner() {
        imageViewWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWebPage(Const.WEB_LINK);
            }
        });

        imageViewMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String items[] = new String[]{res.getString(R.string.support_email_1), res.getString(R.string.support_email_2)};
                showDialog(items, "Mail", R.drawable.ic_action_mail, true);

            }
        });
        imageViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWebPage(Const.FB_PAGE);
            }
        });
        imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String items[] = new String[]{res.getString(R.string.support_no_1)};
                showDialog(items, "Call", R.drawable.ic_action_phone_start, false);
            }
        });
    }

    private void showDialog(final String items[], String title, int icon, final boolean isMail) {

        if (builder == null){
            builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        }
        builder.setTitle(title);
        builder.setIcon(icon);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (isMail) {
                            composeEmail(new String[]{items[0]});
                        } else {
                            dialPhoneNumber(items[0]);
                        }

                        break;
                    case 1:
                        if (isMail) {
                            composeEmail(new String[]{items[1]});
                        }/* else {
                            dialPhoneNumber(items[1]);
                        }*/
                        break;
                }

            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
