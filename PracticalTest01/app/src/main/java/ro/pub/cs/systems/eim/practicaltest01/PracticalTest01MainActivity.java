package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private Button navigateButton;
    private EditText leftEditText;
    private EditText rightEditText;
    private Button leftButton;
    private Button rightButton;

    private NavigateToActivitiesClickListener navigateButtonListener = new NavigateToActivitiesClickListener();
    private LeftButtonClickListener leftButtonListener = new LeftButtonClickListener();
    private RightButtonClickListener rightButtonListener = new RightButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        leftEditText = (EditText)findViewById(R.id.left_edit_text);
        rightEditText = (EditText)findViewById(R.id.right_edit_text);
        leftEditText.setText("0");
        rightEditText.setText("0");

        navigateButton = (Button) findViewById(R.id.navigate_edit_button);
        navigateButton.setOnClickListener(navigateButtonListener);

        leftButton = (Button) findViewById(R.id.left_button);
        leftButton.setOnClickListener(leftButtonListener);

        rightButton = (Button) findViewById(R.id.right_button);
        rightButton.setOnClickListener(rightButtonListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.LEFT_COUNT, leftEditText.getText().toString());
        savedInstanceState.putString(Constants.RIGHT_COUNT, rightEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
            leftEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
        }
        else {
            leftEditText.setText("0");
        }

        if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
            rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
        }
        else {
            rightEditText.setText("0");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The result is " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    private class NavigateToActivitiesClickListener implements View.OnClickListener {
        @Override
        public void onClick (View view) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
            int count = Integer.parseInt(leftEditText.getText().toString()) + Integer.parseInt(rightEditText.getText().toString());
            intent.putExtra(Constants.TOTAL_COUNT, count);

            startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
        }
    }

    private class LeftButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick (View view) {
            int leftNr = Integer.parseInt(leftEditText.getText().toString());
            leftNr++;
            leftEditText.setText(String.valueOf(leftNr));
        }
    }

    private class RightButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick (View view) {
            int rightNr = Integer.parseInt(rightEditText.getText().toString());
            rightNr++;
            rightEditText.setText(String.valueOf(rightNr));
        }
    }
}