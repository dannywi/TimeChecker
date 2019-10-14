package com.example.timechecker;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.timechecker.data.DayType;
import com.example.timechecker.data.Retriever;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Retriever retriever = new Retriever();
    List<String> cached_shown = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickedCheckButton(View view) {
        TextView textView = findViewById(R.id.text_results);

        String lineBreak = "\n====================\n";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // TODO: get string from list of available options
            StringBuilder sb = new StringBuilder();
            Map<DayType, List<Instant>> applicableEntries =
                    retriever.getApplicableTimesAllDays(Instant.now(), "I96ShinagawaEki");
            sb.append("I96ShinagawaEki\n");
            sb.append(lineBreak);

            if(cached_shown.size() >= applicableEntries.size())
                cached_shown.clear();

            for(Map.Entry<DayType, List<Instant>> entry : applicableEntries.entrySet()) {
                if(cached_shown.contains(entry.getKey().toString()))
                    continue;
                sb.append(entry.getKey());
                sb.append(lineBreak);
                sb.append(Utils.convertToTimeAndDistance(entry.getValue(), Instant.now()));
                cached_shown.add(entry.getKey().toString());
                break;
            }

            textView.setText(sb.toString());
        } else {
            textView.setText("ERROR !!!");
        }
    }
}