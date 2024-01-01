package dk.pejomi.chartsoap.service;

import dk.pejomi.chartsoap.exception.MissingFieldException;
import dk.pejomi.chartsoap.xml.BarChart;
import dk.pejomi.chartsoap.xml.DoughnutChart;
import dk.pejomi.chartsoap.xml.LineChart;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ChartService {

    private String generateQuickChartURL(JSONObject jsonObject) {
        return "https://quickchart.io/chart?c=" + jsonObject;
    }
    public String generateChartURL(LineChart lineChart) throws MissingFieldException {
        if (lineChart == null) throw new MissingFieldException("lineChart");
        JSONObject json = getLineChartAsJSONObject(lineChart);
        return generateQuickChartURL(json);
    }
    public String generateChartURL(DoughnutChart doughnutChart) throws MissingFieldException {
        if (doughnutChart == null) throw new MissingFieldException("doughnutChart");
        JSONObject json = getDoughnutChartAsJSONObject(doughnutChart);
        return generateQuickChartURL(json);
    }
    public String generateChartURL(BarChart barChart) throws MissingFieldException {
        if (barChart == null) throw new MissingFieldException("barChart");
        JSONObject json = getBarChartAsJSONObject(barChart);
        return generateQuickChartURL(json);
    }
    private JSONObject getLineChartAsJSONObject(LineChart lineChart) {
        JSONObject json = new JSONObject();
        json.put("type", "line");
        json.put("data", new JSONObject()
                .put("labels", lineChart.getLabels())
                .put("datasets", lineChart.getDatasets())
        );
        if (lineChart.getTitle() != null) {
            json.put("options", new JSONObject()
                    .put("title", new JSONObject()
                            .put("display", true)
                            .put("text", lineChart.getTitle())
                    )
            );
        }
        return json;
    }
    private JSONObject getDoughnutChartAsJSONObject(DoughnutChart doughnutChart) {
        JSONObject json = new JSONObject();
        json.put("type", "doughnut");
        json.put("data", new JSONObject()
                .put("labels", doughnutChart.getLabels())
                .put("datasets", doughnutChart.getDatasets())
        );
        if (doughnutChart.getTitle() != null) {
            json.put("options", new JSONObject()
                    .put("title", new JSONObject()
                            .put("display", true)
                            .put("text", doughnutChart.getTitle())
                    )
            );
        }
        return json;
    }

    private JSONObject getBarChartAsJSONObject(BarChart barChart) {
        JSONObject json = new JSONObject();
        json.put("type", "bar");
        json.put("data", new JSONObject()
                .put("labels", barChart.getLabels())
                .put("datasets", barChart.getDatasets())
        );
        if (barChart.getTitle() != null) {
            json.put("options", new JSONObject()
                    .put("title", new JSONObject()
                            .put("display", true)
                            .put("text", barChart.getTitle())
                    )
                    .put("plugins", new JSONObject()
                            .put("datalabels", new JSONObject())
                    )
            );
        }
        return json;
    }
}
