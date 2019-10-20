package com.example.timechecker;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timechecker.data.DayType;
import com.example.timechecker.data.EntityBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<String> cached_shown = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EntityBuilder.activity = this;
        EntityBuilder.cacheAllEntities();
    }

    public void onClickedCheckButton(View view) {

        TextView textView = findViewById(R.id.text_results);

        String lineBreak = "\n====================\n";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // TODO: get string from list of available options
            StringBuilder sb = new StringBuilder();
            Map<DayType, List<Instant>> applicableEntries =
                    EntityBuilder.getApplicableTimesAllDays(Instant.now(), Const.entity_1_name);
            sb.append(Const.entity_1_name + "\n");
            sb.append(lineBreak);

            if (cached_shown.size() >= applicableEntries.size())
                cached_shown.clear();

            for (Map.Entry<DayType, List<Instant>> entry : applicableEntries.entrySet()) {
                if (cached_shown.contains(entry.getKey().toString()))
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
