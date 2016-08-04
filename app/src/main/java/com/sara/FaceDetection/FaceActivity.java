package com.sara.FaceDetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;
import com.sara.newconcepts.R;

public class FaceActivity extends AppCompatActivity {

    ImageView img_face;
    Canvas canvas;
    FaceDetector faceDetector;
    Bitmap template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        img_face = (ImageView) findViewById(R.id.img_face);

        SetupFace();
        DrawCanvas();

    }

    private void SetupFace() {

        faceDetector = new FaceDetector.Builder(this)
                .setTrackingEnabled(false).setLandmarkType
                        (FaceDetector.ALL_LANDMARKS).build();

    }

    private void DrawCanvas() {

        Bitmap myBitmap = GetBitmap();
        template = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(),
                Bitmap.Config.RGB_565);
        canvas = new Canvas(template);
        canvas.setBitmap(template);
        canvas.drawBitmap(myBitmap, 0, 0, null);
    }

    private Bitmap GetBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource
                (getResources(), R.drawable.sara);
        return bitmap;
    }

    public void GetImage(View view) {
        GetFace();
    }

    private void GetFace() {

        Frame frame = new Frame.Builder().setBitmap(GetBitmap())
                .build();

        SparseArray<Face> faces = faceDetector.detect(frame);
        for (int i = 0; i < faces.size(); i++) {
            Face face = faces.get(i);

            float x1 = face.getPosition().x;
            float y1 = face.getPosition().y;

            float x2 = x1 + face.getWidth();
            float y2 = y1 + face.getHeight();

            GetLandMark(face);

            //canvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, GetRectangle());

        }
        img_face.setImageDrawable(new BitmapDrawable(getResources(), template));
    }

    private void GetLandMark(Face face) {

        double scale = Math.min(canvas.getWidth() / template.getWidth(),
                canvas.getHeight() / template.getHeight());
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
       // paint.setStrokeWidth(5);

        for (Landmark landmark : face.getLandmarks()) {
            int cx = (int) (landmark.getPosition().x * scale);
            int cy = (int) (landmark.getPosition().y * scale);

           // if (landmark.getType() == Landmark.LEFT_EYE) {
                Log.e("left_ear", "enter");
                canvas.drawCircle(cx, cy, 10, paint);
           // }
        }

    }

    private Paint GetRectangle() {
        Paint myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(5);
        myRectPaint.setColor(Color.RED);
        myRectPaint.setStyle(Paint.Style.STROKE);

        return myRectPaint;
    }
}
