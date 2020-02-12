package com.example.timechecker;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timechecker.data.DayType;
import com.example.timechecker.data.DayTypeChecker;
import com.example.timechecker.data.EntityBuilder;
import com.example.timechecker.holiday.HolidayDates;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    List<DayType> cached_shown = new ArrayList<>();
    long epochMilliLastShow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EntityBuilder.activity = this; // TODO: fix this hack
        EntityBuilder.cacheAllEntities();
        HolidayDates.ensureLatestFile(this);
        onClickedCheckButton(null);
    }

    public void onClickedCheckButton(View view) {
        //  Otherwise auto select DayType (https://qiita.com/Chrowa3/items/93c55d83b972d0ddfa10)
        TextView textView = findViewById(R.id.text_results);

        String lineBreak = "\n====================\n";
        // TODO: get string from list of available options
        StringBuilder sb = new StringBuilder();
        Map<DayType, List<Instant>> applicableEntries =
                EntityBuilder.getApplicableTimesAllDays(Instant.now(), Const.ENTITY_1_NAME);
        sb.append(Const.ENTITY_1_NAME + "\n");
        sb.append(lineBreak);

        DayType toShow = toggleDayTypeToShow(applicableEntries.keySet());
        sb.append(toShow);
        sb.append(lineBreak);
        sb.append(Utils.convertToTimeAndDistance(applicableEntries.get(toShow), Instant.now()));

        textView.setText(sb.toString());
    }

    private DayType toggleDayTypeToShow(Set<DayType> applicableEntries) {
        boolean toggle = System.currentTimeMillis() - epochMilliLastShow < 3 * 1000;
        epochMilliLastShow = System.currentTimeMillis();

        if (toggle) {
            if (cached_shown.size() >= applicableEntries.size())
                cached_shown.clear();

            for (DayType entry : applicableEntries) {
                if (cached_shown.contains(entry))
                    continue;
                cached_shown.add(entry);
                return entry;
            }
        }

        DayType ret = new DayTypeChecker().getCurrentDayType(this);
        cached_shown.clear();
        cached_shown.add(ret);
        return ret;
    }

    public void onClickedReloadButton(View view) {
        // TODO: make this choose from options list
        EntityBuilder.downloadEntityInfo(Const.ENTITY_1_NAME);
    }

}
