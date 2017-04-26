package com.project.seven.balanswing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class PersonalActivity extends AppCompatActivity {

    private LinearLayout mPDRField;
    MYView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        mView = new MYView(this);
        mPDRField = (LinearLayout)findViewById(R.id.personalGraphView);
        mPDRField.addView(mView);

    }

    public void btnClick(View view) {
        switch (view.getId())
        {
            case R.id.btnPersonalBack:
                Intent intent = new Intent(PersonalActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    class MYView extends View {
        int width;
        int height;
        int w10;
        int h10;

        public MYView(Context context){ super(context); }

        public void onSizeChanged(int w, int h, int oldw, int oldh){
            super.onSizeChanged(w,h,oldw,oldh);
            width = w;
            height = h;
            w10 = width/10;
            h10 = height/10;
        }
        public void onDraw(Canvas canvas){
            Paint greenPaint = new Paint();
            greenPaint.setColor(0xff00964c);
            greenPaint.setStrokeWidth(11);

            Paint blackPaint = new Paint();
            blackPaint.setColor(Color.BLACK);
            blackPaint.setStrokeWidth(11);

            Paint linePaint = new Paint();
            linePaint.setColor(0xff404040);
            linePaint.setTextSize(50);

            int basex;
            int basey;


            basex = w10;
            basey = h10;
            canvas.drawLine(basex,basey,basex,height-basey,blackPaint);
            canvas.drawLine(basex,height-basey,width-basex,height-basey,blackPaint);
            canvas.drawLine(width-basex,height-basey,width-basex,basey,blackPaint);
            canvas.drawLine(width-basex,basey,basex,basey,blackPaint);
            canvas.drawLine(basex*2, basey*3, basex*8, basey*3, blackPaint);


            canvas.drawRect(basex*2, basey*3, basex*4, basey*8, greenPaint);
            canvas.drawText("  왼발", basex*2, basey*2, linePaint);

            canvas.drawRect(basex*6, basey*3, basex*8, basey*6, greenPaint);
            canvas.drawText(" 오른발", basex*6, basey*2, linePaint);

            canvas.drawLine(basex*3, basey*8, basex*7, basey*6, blackPaint);
            canvas.drawCircle(basex*3,basey*8, basex/5,blackPaint);
            canvas.drawCircle(basex*7,basey*6, basex/5,blackPaint);


        }
    }

}
