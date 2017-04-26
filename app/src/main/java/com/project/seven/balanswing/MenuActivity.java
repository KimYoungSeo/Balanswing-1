package com.project.seven.balanswing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    ImageButton btnMenu1, btnMenu2, btnMenu3, btnLanguage;
    TextView tvTitle, tvCalendar, tvPersonal, tvCompare;

    SharedPreferences pref;
    int lang, sample;

    String[] select, set, cancel, title, calendar, personal, compare;

    int[] langImg = {R.drawable.kor, R.drawable.eng, R.drawable.jpn, R.drawable.zho};
    int[] langID = {R.id.kor, R.id.eng, R.id.jpn, R.id.zho};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }

    public void init() {

        btnMenu1 = (ImageButton) findViewById(R.id.btnMenu1);
        btnMenu2 = (ImageButton) findViewById(R.id.btnMenu2);
        btnMenu3 = (ImageButton) findViewById(R.id.btnMenu3);
        btnLanguage = (ImageButton) findViewById(R.id.btnLanguage);
        tvTitle = (TextView) findViewById(R.id.menu_title);
        tvCalendar = (TextView) findViewById(R.id.menu_calendar);
        tvPersonal = (TextView) findViewById(R.id.menu_personal);
        tvCompare = (TextView) findViewById(R.id.menu_compare);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        lang = pref.getInt("language", 0);
        sample = pref.getInt("sample", -1);

        select = getResources().getStringArray(R.array.dialog_select);
        set = getResources().getStringArray(R.array.dialog_set);
        cancel = getResources().getStringArray(R.array.dialog_cancel);
        title = getResources().getStringArray(R.array.menu_title);
        calendar = getResources().getStringArray(R.array.menu_calendar);
        personal = getResources().getStringArray(R.array.menu_personal);
        compare = getResources().getStringArray(R.array.menu_compare);

        setLangauge();
    }

    public void setLangauge() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("language", lang);
        editor.commit();

        tvTitle.setText(title[lang]);
        tvCalendar.setText(calendar[lang]);
        tvPersonal.setText(personal[lang]);
        tvCompare.setText(compare[lang]);
        btnLanguage.setImageResource(langImg[lang]);
    }

    public void btnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnMenu1:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMenu2:
                intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMenu3:
                if (sample == -1) {
                    intent = new Intent(this, SelectActivity.class);
                } else {
                    intent = new Intent(this, CompareActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.btnLanguage:
                final LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater.inflate(R.layout.dialog_language, null);

                RadioGroup radioGroup = (RadioGroup) dialogView.findViewById(R.id.langCheck);
                radioGroup.check(langID[lang]);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        switch (checkedId){
                            case R.id.kor: lang=0; break;
                            case R.id.eng: lang=1; break;
                            case R.id.jpn: lang=2; break;
                            case R.id.zho: lang=3; break;
                            default: lang=0; break;
                        }
                    }
                });

                AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
                alert.setTitle("test");
                alert.setPositiveButton("ok",null);

                alert.setTitle(select[lang]);
                alert.setView(dialogView);
                alert.setPositiveButton(set[lang], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLangauge();
                    }
                });
                alert.setNegativeButton(cancel[lang], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lang = pref.getInt("language", 0);
                    }
                });

                AlertDialog dialog = alert.show();
                dialog.show();
                break;
        }
    }
}
