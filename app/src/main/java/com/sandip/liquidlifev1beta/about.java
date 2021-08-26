package com.sandip.liquidlifev1beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class about extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.menuHome:
                Log.i("Item Selected","menuHome");
                Intent intentHome = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intentHome);
                //Toast.makeText(this, "Settings Tapped", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuRegister:
                Log.i("Item Selected","menuRegister");
                Intent intentRegister = new Intent(getApplicationContext(),register.class);
                startActivity(intentRegister);
                return true;
            case R.id.menuLogin:
                Log.i("Item Selected","menuLogin");
                Intent intentLogin = new Intent(getApplicationContext(),login.class);
                startActivity(intentLogin);
                return true;
            case R.id.menuSearch:
                Log.i("Item Selected","menuSearch");
                Intent intentSearch = new Intent(getApplicationContext(),search.class);
                startActivity(intentSearch);
                return true;
            case R.id.menuAbout:
                Log.i("Item Selected","menuAbout");
                Intent intentAbout = new Intent(getApplicationContext(),about.class);
                startActivity(intentAbout);
                return true;
            default:
                return false;
        }
    }

    // MENU END

    //Initialize variable
    RelativeLayout relativeLayout;
    TextView textView;
    ImageView imageView;
    SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Assign variable
        relativeLayout=findViewById(R.id.wvAboutUs);
        imageView = findViewById(R.id.ivAboutUs);
        swipeListener=new SwipeListener(relativeLayout);
    }

    private class SwipeListener implements View.OnTouchListener{

        //Initialize variable
        GestureDetector gestureDetector;


        //Create constructor
        SwipeListener(View view){
            //Initialize threshold value
            final int threshold=100;
            final int velocity_threshold=100;

            //Initialize simple gesture listener
            GestureDetector.SimpleOnGestureListener listener=new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff=e2.getX() - e1.getX();
                    float yDiff=e2.getY() - e1.getY();
                    try {
                        //Check oondition
                        if(Math.abs(xDiff)>Math.abs(yDiff)){
                            if(Math.abs(xDiff) > threshold && Math.abs(velocityX)>velocity_threshold){
                                if(xDiff>0){
//                                    textView.setText("You Swiped Right");
                                    imageView.setImageResource(R.drawable.sandip);
                                }
                                else{
//                                    textView.setText("You Swiped Left");
                                    imageView.setImageResource(R.drawable.khyati);
                                }
                                return true;
                            }
                        }
                        else{
                            if(Math.abs(yDiff)>threshold && Math.abs(velocityY)>velocity_threshold){
                                if(yDiff>0){
//                                    textView.setText("You Swiped Down");
                                    imageView.setImageResource(R.drawable.logoc);
                                }
                                else {
//                                    textView.setText("You Swiped Up");
                                    imageView.setImageResource(R.drawable.nikita);
                                }
                                return true;
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                }
            };

            gestureDetector=new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }
}