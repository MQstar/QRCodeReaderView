package com.example.qr_readerexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

public class PointsOverlayView extends View {

  PointF[] points;
  String text;
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
    textPaint.setTextSize(40);
    paint = new Paint();
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.BLACK);
    paint.setStyle(Paint.Style.FILL);
    paint.setAlpha(80);


  }

  public void setPoints(PointF[] points) {
    this.points = points;
    invalidate();
  }

  @Override public void draw(Canvas canvas) {
    super.draw(canvas);
    if (points != null && text != null) {
      canvas.drawRect(points[0].x-170,(points[1].y + points[0].y + 35)/2,points[0].x+490,(points[1].y + points[0].y - 70)/2,paint);
      canvas.drawText(text,points[0].x-150,(points[1].y + points[0].y)/2,textPaint);
    }
  }

  public void setText(String text) {
    if (text.length()>30)
      this.text = text.substring(0,30) + "...";
    else
      this.text = text;
  }




}
