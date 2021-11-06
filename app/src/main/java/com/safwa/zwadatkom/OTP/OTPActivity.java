package com.safwa.zwadatkom.OTP;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.safwa.zwadatkom.Home.MainActivity;
import com.safwa.zwadatkom.Models.LoginModel;
import com.safwa.zwadatkom.Models.ResendOtpModel;
import com.safwa.zwadatkom.Models.VerifyOtpModel;
import com.safwa.zwadatkom.Network.RetroWeb;
import com.safwa.zwadatkom.Network.ServiceApi;
import com.safwa.zwadatkom.R;
import com.safwa.zwadatkom.Utils.GlobalPrefrencies;
import com.safwa.zwadatkom.Utils.Utils;
import com.safwa.zwadatkom.map.MapsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {


    EditText digit1, digit2, digit3, digit4, digit5, digit6;
    private AppCompatButton confirm_btn;
    RelativeLayout rel1, rel2, rel3, rel4, rel5, rel6;
    TextView textViewEnterCode;
    GlobalPrefrencies globalPrefrencies;
    TextView textViewResendCode;
    private String phone, pass, name;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);


        globalPrefrencies = new GlobalPrefrencies(this);
        Utils.setLocale(this, globalPrefrencies.getLanguage());
        textViewEnterCode = findViewById(R.id.plzEnterCode);
        textViewResendCode = findViewById(R.id.resendCode);


        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        pass = getIntent().getStringExtra("password");


        if(phone != null) {
            textViewEnterCode.setText("تم ارسال كود التفعيل الي رقم جوال : " + phone);
        }else {
            textViewEnterCode.setVisibility(View.INVISIBLE);
        }
        initial();
        set_digit();
        confirm();

        textViewResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(OTPActivity.this, "اعادة ارسال الكود", Toast.LENGTH_SHORT).show();
                sendVerificationCode(phone);

            }
        });
    }

    private void sendVerificationCode(String phone) {

      RetroWeb.getClient().create(ServiceApi.class).onOtpٌResend(phone,globalPrefrencies.getApi_token()).enqueue(new Callback<ResendOtpModel>() {
          @Override
          public void onResponse(Call<ResendOtpModel> call, Response<ResendOtpModel> response) {
              if(response.body().getStatus()){
                  Toast.makeText(OTPActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
              }
          }

          @Override
          public void onFailure(Call<ResendOtpModel> call, Throwable t) {

          }
      });

    }


    private void initial() {

        digit1 = findViewById(R.id.digit1);
        digit2 = findViewById(R.id.digit2);
        digit3 = findViewById(R.id.digit3);
        digit4 = findViewById(R.id.digit4);
        digit5 = findViewById(R.id.digit5);
        digit6 = findViewById(R.id.digit6);
        rel5 = findViewById(R.id.rel5);
        rel4 = findViewById(R.id.rel4);
        rel3 = findViewById(R.id.rel3);
        rel2 = findViewById(R.id.rel2);
        rel1 = findViewById(R.id.rel1);
        rel6 = findViewById(R.id.rel6);

        confirm_btn = findViewById(R.id.confirm_cod);


    }

    public void set_digit() {


        digit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // add a condition to check length here - you can give here length according to your requirement to go to next EditTexts.
                if (digit1.getText().toString().trim().length() > 0) {
                    digit1.clearFocus();
                    digit2.requestFocus();

                }
            }
        });

        digit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // add a condition to check length here - you can give here length according to your requirement to go to next EditTexts.
                if (digit2.getText().toString().trim().length() > 0) {
                    digit2.clearFocus();
                    digit3.requestFocus();

                }
            }
        });
        digit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // add a condition to check length here - you can give here length according to your requirement to go to next EditTexts.
                if (digit3.getText().toString().trim().length() > 0) {
                    digit3.clearFocus();
                    digit4.requestFocus();
                }
            }
        });
        digit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // add a condition to check length here - you can give here length according to your requirement to go to next EditTexts.
                if (digit4.getText().toString().trim().length() > 0) {

                    digit4.clearFocus();
                    digit5.requestFocus();


                }
            }
        });

        digit5.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // add a condition to check length here - you can give here length according to your requirement to go to next EditTexts.
                if (digit5.getText().toString().trim().length() > 0) {

                    digit5.clearFocus();
                    digit6.requestFocus();
                }
            }
        });
        digit6.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // add a condition to check length here - you can give here length according to your requirement to go to next EditTexts.
                if (digit6.getText().toString().trim().length() > 0) {
                    //  digit5.clearFocus();
                }
            }
        });


        digit1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {

                    if (digit1.isFocused()) {
                        digit1.setText("");
                        digit1.requestFocus();
                    }

                }
                return false;
            }

        });

        digit2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (digit2.getText().toString() == null || digit2.getText().toString().equals("")) {
                        digit1.requestFocus();
                    } else {

                        digit2.setText("");
                    }


                }
                return false;
            }

        });
        digit3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {

                    if (digit3.getText().toString() == null || digit3.getText().toString().equals("")) {
                        {
                            digit2.requestFocus();
                        }
                    } else {
                        digit3.setText("");
                    }


                }
                return false;
            }

        });
        digit4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (digit4.getText().toString() == null || digit4.getText().toString().equals("")) {
                        digit3.requestFocus();
                    } else {
                        digit4.setText("");
                    }


                }
                return false;
            }

        });
        digit5.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {

                    if (digit5.getText().toString() == null || digit5.getText().toString().equals("")) {
                        digit4.requestFocus();
                    } else {
                        digit5.setText("");
                    }


                }
                return false;
            }

        });
        digit6.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {

                    if (digit6.getText().toString() == null || digit6.getText().toString().equals("")) {
                        digit5.requestFocus();
                    } else {
                        digit6.setText("");
                    }


                }
                return false;
            }

        });

    }

    public void confirm() {
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

                String verify = digit1.getText().toString().trim() + digit2.getText().toString().trim() +
                        digit3.getText().toString().trim() +
                        digit4.getText().toString().trim();

                verify = verify + digit5.getText().toString().trim();

                verify = verify + digit6.getText().toString().trim();



                    if (verify.length() < 6 || verify.equals("") ) {
                        Toast.makeText(OTPActivity.this, "يجب ان يتكون الرمز من 6 ارقام", Toast.LENGTH_SHORT).show();
                    } else {

                        virefyOtpCode(verify);

                    }
                }
        });

    }

    private void virefyOtpCode(String verify) {

        RetroWeb.getClient().create(ServiceApi.class).onOtpValidate(verify,globalPrefrencies.getApi_token()).enqueue(new Callback<VerifyOtpModel>() {
            @Override
            public void onResponse(Call<VerifyOtpModel> call, Response<VerifyOtpModel> response) {
                if(response.body().getSuccess()){
                    LoginAfterOtp();

                    Toast.makeText(OTPActivity.this, ""+response.body().getData().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OTPActivity.this, MapsActivity.class));
                    OTPActivity.this.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    Toast.makeText(OTPActivity.this, "مرحبا بك " + globalPrefrencies.getNameAR(), Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(OTPActivity.this, ""+response.body().getData().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyOtpModel> call, Throwable t) {

            }
        });
    }

    AlertDialog.Builder builder;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {

            builder = new AlertDialog.Builder(this);


            //Setting message manually and performing action on button click
            builder.setMessage("هل تريد الخروج من التطبيق ؟")
                    .setCancelable(false)
                    .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();

                        }
                    })
                    .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();

                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("مغادرة التطبيق");
            alert.show();


        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void LoginAfterOtp() {
        RetroWeb.getClient().create(ServiceApi.class).onLogin(phone, pass).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                int id = response.body().getUser().getId();
                String nameAr = response.body().getUser().getName();

                String phone = response.body().getUser().getMobile();
                String api_token = response.body().getToken();
//                if (globalPrefrencies.getLanguage().equals("ar")) {
//                    startActivity(new Intent(OTPActivity.this, MapsActivity.class));
//                    //  this.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                    Toast.makeText(OTPActivity.this, "مرحبا بك " + nameAr, Toast.LENGTH_LONG).show();
//                    OTPActivity.this.finish();
//                }
                globalPrefrencies.storeLoginStatus(true);
                globalPrefrencies.storeUserId(id);

                globalPrefrencies.storeNameAr(nameAr);
                globalPrefrencies.storePhone(phone);
                globalPrefrencies.storeAddress(response.body().getUser().getAddress().get(0) + "");
                globalPrefrencies.storeLat(response.body().getUser().getLatitude() + "");
                globalPrefrencies.storeLong(response.body().getUser().getLongitude() + "");
                globalPrefrencies.storeApi_token(api_token);
                globalPrefrencies.storeLoginStatus(true);
                // onActiveUser(phone);

                Intent intent = new Intent(OTPActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                finish();

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });

    }

    public void closeOtpActivity(View view) {

        finish();

    }
}






