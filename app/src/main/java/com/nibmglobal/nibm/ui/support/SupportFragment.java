package com.nibmglobal.nibm.ui.support;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseFragment;
import com.nibmglobal.nibm.ui.main.IntrfceMain;

/**
 * Created by mindlabs on 12/4/15.
 */
public class SupportFragment extends BaseFragment {

    public static String TAG = "support";

    private ImageView imageViewWeb;
    private ImageView imageViewMail;
    private ImageView imageViewFacebook;
    private ImageView imageViewCall;
    private AlertDialog.Builder builder;
    private Resources res;
    private IntrfceMain mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (IntrfceMain) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_support, null);
        mCallback.onActionbarSetTitle("Support");
        res = getActivity().getResources();
        initUi(v);
        initListner();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initUi(View v) {
        imageViewWeb = (ImageView) v.findViewById(R.id.imageWeb);
        imageViewMail = (ImageView) v.findViewById(R.id.imageMail);
        imageViewFacebook = (ImageView) v.findViewById(R.id.imageFacebook);
        imageViewCall = (ImageView) v.findViewById(R.id.imageCall);

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
            builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
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
                        } /*else {
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
