package me.jakir.prebuilddatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String DB_NAME = "hello.db";
    String TABLE_NAME = "name";
    DataBaseHelper myDBHelper;
    Button button;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Copy database
        AssetDatabaseOpenHelper assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(this, DB_NAME);
        assetDatabaseOpenHelper.saveDatabase();

        myDBHelper = new DataBaseHelper(this, DB_NAME);


        result = (TextView) findViewById(R.id.resultView);
        button = (Button) findViewById(R.id.btn_view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDBHelper.getAllData(TABLE_NAME);
                if (res.getCount() == 0) {

                    result.setText("No Data found, sorry!");

                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id: " + res.getString(0) + "\n");
                        buffer.append("Name: " + res.getString(1) + "\n");
                    }
                    result.setText(buffer);

                }
            }
        });


    }
}
