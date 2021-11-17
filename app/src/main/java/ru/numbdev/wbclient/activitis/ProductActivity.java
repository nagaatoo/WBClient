package ru.numbdev.wbclient.activitis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

import ru.numbdev.wbclient.R;
import ru.numbdev.wbclient.dto.ListDTO;

// Активити продукта
public class ProductActivity extends AppCompatActivity {

    private ListDTO dto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        dto = (ListDTO) getIntent().getSerializableExtra("dto");
        sampleLineGraph();
    }

    private void sampleLineGraph() {
        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        var pie = AnyChart.line();


        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        pie.data(data);

        anyChartView.setChart(pie);
    }

}
