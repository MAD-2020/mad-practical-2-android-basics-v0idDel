package sg.edu.np.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int Score = 0;
    private TextView scoreTrack;
    //Textview should be used for uneditable text which user wants to read
    private Button Button1;
    private Button Button2;
    private Button Button3;

    private static final String TAG = "ActivityText" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Capture buttons from layout
        Button1 = (Button) findViewById(R.id.Button1);
        Button2 = (Button) findViewById(R.id.Button2);
        Button3 = (Button) findViewById(R.id.Button3);
        //Register the onClick listener with the implementation above
        Button1.setOnClickListener(buttonListener);
        Button2.setOnClickListener(buttonListener);
        Button3.setOnClickListener(buttonListener);
        scoreTrack = (TextView) findViewById(R.id.score);

        Log.v(TAG, "Whack-A-Mole!");

    }

    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");
    }

    public void setNewMole()
    {
        Button[] buttonArray = {Button1, Button2, Button3};
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        //Android studio suggested me to change from for loop to foreach loop
        for (Button button : buttonArray) {
            Button moleButton = buttonArray[randomLocation];
            if (button == moleButton) {
                button.setText("*");
            } else {
                button.setText("O");
            }
        }
    }
    private View.OnClickListener buttonListener = new View.OnClickListener(){
        public void onClick(View v){
            Button userButton = (Button) v;
            switch(v.getId()){
                case R.id.Button1:
                    Log.v(TAG, "Button Left Clicked!");
                    break;
                case R.id.Button2:
                    Log.v(TAG, "Button Middle Clicked!");
                    break;
                case R.id.Button3:
                    Log.v(TAG, "Button Right Clicked!");
                    break;
            }
            String buttonText = userButton.getText().toString();
            switch (buttonText){
                case "O":
                    Log.v(TAG, "Missed point deducted!");
                    Score = Score - 1;
                    if (Score < 0){
                        Score = 0;
                        Log.v(TAG, "Come on! Try harder!");
                    }
                    setNewMole();
                    break;
                case "*":
                    Log.v(TAG, "Hit, score added!");
                    Score = Score + 1;
                    setNewMole();
                    break;
            }
            String count = String.valueOf(Score);
            scoreTrack.setText(count);
        }
    };

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TAG, "Resuming...");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Pausing...");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopping...");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TAG, "Destroying!!!");
    }
}
