package com.imvishal.tapthegray;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,start;
    TextView score;
    static int marks = 0;
    Random r;
    LinearLayout ll;
    Handler hd;
    Timer timer;
    MyTask task;
    int idx;
    int flag = 1;
    AlertDialog.Builder builder1;
    AlertDialog alert11;
    boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        b1 = (Button)findViewById(R.id.first);
        b2 = (Button)findViewById(R.id.second);
        b3 = (Button)findViewById(R.id.third);
        b4 = (Button)findViewById(R.id.fourth);
        start = (Button)findViewById(R.id.start);
        score = (TextView)findViewById(R.id.score);

        score.setText("Score "+marks);

        builder1 = new AlertDialog.Builder(this);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        b1.setBackgroundColor(getResources().getColor(R.color.red));
                        b2.setBackgroundColor(getResources().getColor(R.color.blue));
                        b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                        b4.setBackgroundColor(getResources().getColor(R.color.green));
                        score.setText("Score = 0");
                        flag = 1;
                        running = false;
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Cancle",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        b1.setBackgroundColor(getResources().getColor(R.color.red));
                        b2.setBackgroundColor(getResources().getColor(R.color.blue));
                        b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                        b4.setBackgroundColor(getResources().getColor(R.color.green));
                        score.setText("Score = 0");
                        flag = 1;
                        running = false;
                        dialog.cancel();
                    }
                });
        timer = new Timer();
        task = new MyTask();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marks = 0;
                if (timer != null) {
                    timer.cancel();

                }
                running = true;
                timer = new Timer();
                task = new MyTask();
                b1.setBackgroundColor(getResources().getColor(R.color.red));
                b2.setBackgroundColor(getResources().getColor(R.color.blue));
                b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                b4.setBackgroundColor(getResources().getColor(R.color.green));
                timer.schedule(task, 1000, 1000);
            }
        });





    }

    class MyTask extends TimerTask {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {


                @Override
                public void run() {
                    Random rnd = new Random();
                    if (flag == 0) {
                        if (timer != null) {
                            timer.cancel();
                            timer.purge();
                            timer = null;
                            running = false;
                            b1.setBackgroundColor(getResources().getColor(R.color.red));
                            b2.setBackgroundColor(getResources().getColor(R.color.blue));
                            b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                            b4.setBackgroundColor(getResources().getColor(R.color.green));
                            //score.setText("Game Over");
                            builder1.setMessage("Game Over.. \nYour Score is : " + marks);
                            alert11 = builder1.create();
                            alert11.show();
                        }
                    }
                    flag = 0;
                    idx = new Random().nextInt(4) + 1;
                    if (idx == 1) {
                        b1.setBackgroundColor(getResources().getColor(R.color.gray));
                        b2.setBackgroundColor(getResources().getColor(R.color.blue));
                        b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                        b4.setBackgroundColor(getResources().getColor(R.color.green));

                    } else if (idx == 2) {
                        b1.setBackgroundColor(getResources().getColor(R.color.red));
                        b2.setBackgroundColor(getResources().getColor(R.color.gray));
                        b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                        b4.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (idx == 3) {
                        b1.setBackgroundColor(getResources().getColor(R.color.red));
                        b2.setBackgroundColor(getResources().getColor(R.color.blue));
                        b3.setBackgroundColor(getResources().getColor(R.color.gray));
                        b4.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (idx == 4) {
                        b1.setBackgroundColor(getResources().getColor(R.color.red));
                        b2.setBackgroundColor(getResources().getColor(R.color.blue));
                        b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                        b4.setBackgroundColor(getResources().getColor(R.color.gray));
                    }

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (idx == 1 && running) {
                                marks++;
                                score.setText("Score = " + marks);
                                flag = 1;
                            } else {
                                if (timer != null) {
                                    timer.cancel();
                                    timer.purge();
                                    timer = null;
                                    running = false;
                                    b1.setBackgroundColor(getResources().getColor(R.color.red));
                                    b2.setBackgroundColor(getResources().getColor(R.color.blue));
                                    b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                                    b4.setBackgroundColor(getResources().getColor(R.color.green));
                                    //score.setText("Game Over");
                                    builder1.setMessage("Game Over.. \nYour Score is : " + marks);
                                    alert11 = builder1.create();
                                    alert11.show();
                                }
                            }
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (idx == 2 && running) {
                                marks++;
                                score.setText("Score = " + marks);
                                flag = 1;
                            } else {
                                if (timer != null) {
                                    timer.cancel();
                                    timer.purge();
                                    timer = null;
                                    running = false;
                                    b1.setBackgroundColor(getResources().getColor(R.color.red));
                                    b2.setBackgroundColor(getResources().getColor(R.color.blue));
                                    b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                                    b4.setBackgroundColor(getResources().getColor(R.color.green));
                                    //score.setText("Game Over");
                                    builder1.setMessage("Game Over.. \nYour Score is : " + marks);
                                    alert11 = builder1.create();
                                    alert11.show();
                                }
                            }
                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (idx == 3 && running) {
                                marks++;
                                score.setText("Score = " + marks);
                                flag = 1;

                            } else {
                                if (timer != null) {
                                    timer.cancel();
                                    timer.purge();
                                    timer = null;
                                    running = false;
                                    b1.setBackgroundColor(getResources().getColor(R.color.red));
                                    b2.setBackgroundColor(getResources().getColor(R.color.blue));
                                    b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                                    b4.setBackgroundColor(getResources().getColor(R.color.green));
                                    //score.setText("Game Over");
                                    builder1.setMessage("Game Over.. \nYour Score is : " + marks);
                                    alert11 = builder1.create();
                                    alert11.show();
                                }

                            }
                        }
                    });
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (idx == 4 && running) {
                                marks++;
                                score.setText("Score = " + marks);
                                flag = 1;
                            } else {
                                if (timer != null) {
                                    timer.cancel();
                                    timer.purge();
                                    timer = null;
                                    running = false;
                                    b1.setBackgroundColor(getResources().getColor(R.color.red));
                                    b2.setBackgroundColor(getResources().getColor(R.color.blue));
                                    b3.setBackgroundColor(getResources().getColor(R.color.yellow));
                                    b4.setBackgroundColor(getResources().getColor(R.color.green));
                                    // score.setText("Game Over");
                                    builder1.setMessage("Game Over.. \nYour Score is : " + marks);
                                    alert11 = builder1.create();
                                    alert11.show();
                                }
                            }
                        }
                    });

                }

            });
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        b1.setBackgroundColor(getResources().getColor(R.color.red));
//        b2.setBackgroundColor(getResources().getColor(R.color.blue));
//        b3.setBackgroundColor(getResources().getColor(R.color.yellow));
//        b4.setBackgroundColor(getResources().getColor(R.color.green));
//        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
