package com.example.qr_readerexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class PointsOverlayView extends View {

  ArrayList<PointF[]> pointsList;
  ArrayList<String> textList;
    private Paint paint;
    private Paint textPaint;

    public PointsOverlayView(Context context) {
        super(context);
        init();
    }

    public PointsOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointsOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(80);


    }

  public void setPointsList(ArrayList<PointF[]> pointsList) {
    this.pointsList = pointsList;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    Log.i("PointsOverlayView","draw()");
    if (pointsList == null || textList == null) {
      Log.e("PointsOverlayView","draw():pointsList is null or textList is null");
      return;
    }
    if (pointsList.size() != textList.size()) {
      Log.e("PointsOverlayView","draw():pointsList size is not equal to textList");
      return;
    }
    for (PointF[] points: pointsList) {
      String text;
      text = (String) textList.get(pointsList.indexOf(points));
      if (points != null && text != null) {
            Bitmap bitmap = null;
            if (text.toLowerCase().startsWith("https://u.wechat.com")) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.weixin);
            } else if (text.toLowerCase().startsWith("https://qr.alipay.com")) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.alipay);
            }else if (text.toLowerCase().startsWith("taobao://")) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.taobao);
            }
            else if (text.toLowerCase().startsWith("http://qm.qq.com")) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qq);
            } else if (text.toLowerCase().startsWith("http://m.jd.com")) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jd);
            } else if (text.toLowerCase().startsWith("tel:")) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tel);
            } else if (text.toLowerCase().startsWith("http://")) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.url);
            } else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_translate);
            }
//hello
            canvas.drawBitmap(bitmap, points[0].x - 170, (points[1].y + points[0].y) / 2 - bitmap.getHeight() - 50, textPaint);
            canvas.drawRect(points[0].x - 170, (points[1].y + points[0].y + 35) / 2, points[0].x + 490, (points[1].y + points[0].y - 70) / 2, paint);
            canvas.drawText(text, points[0].x - 150, (points[1].y + points[0].y) / 2, textPaint);
        }
    }
  }

  public void setTextList(ArrayList<String> textList) {
    ArrayList<String> tmpList = new ArrayList<String>();
    for (String text: textList) {
      String text1;
      if (text.length() > 30)
        text1 = text.substring(0, 30) + "...";
      else
        text1 = text;
      tmpList.add(text1);
    }
    this.textList = tmpList;
    }


}
