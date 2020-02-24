package appmoviles.com.clase8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private Button btnRandom;
    private ListView randomList;
    private TextView txtConsole;
    private Toolbar toolbar;

    private ArrayList<String> numbers;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnRandom = findViewById(R.id.btn_random);
        randomList = findViewById(R.id.random_list);
        txtConsole = findViewById(R.id.txt_console);
        numbers = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers);
        randomList.setAdapter(adapter);


        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number = Math.random()*1000;
                numbers.add(""+(int)number);
                adapter.notifyDataSetChanged();
                txtConsole.setText(""+(int)number);
            }
        });

        randomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Toast.makeText(ProfileActivity.this, "Número: "+numbers.get(pos), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit:
                finish();
                return true;

            case R.id.action_clean:
                numbers.clear();
                adapter.notifyDataSetChanged();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
