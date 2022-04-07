package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private EditText countTotalEditText;

    private Button saveButton;
    private Button cancelButton;

    private SaveButtonClickListener saveButtonListener = new SaveButtonClickListener();
    private CancelButtonClickListener cancelButtonListener = new CancelButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        countTotalEditText = (EditText)findViewById(R.id.count_total);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.TOTAL_COUNT)) {
            int count = intent.getIntExtra(Constants.TOTAL_COUNT, -1);
            countTotalEditText.setText(String.valueOf(count));
        }

        saveButton = (Button) findViewById(R.id.save_edit_button);
        cancelButton = (Button) findViewById(R.id.cancel_edit_button);

        saveButton.setOnClickListener(saveButtonListener);
        cancelButton.setOnClickListener(cancelButtonListener);


    }

    private class SaveButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick (View view) {
            setResult(RESULT_OK, null);
        }
    }

    private class CancelButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick (View view) {
            setResult(RESULT_CANCELED, null);
        }
    }
}