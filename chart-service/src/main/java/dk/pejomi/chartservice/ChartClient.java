package dk.pejomi.chartservice;

import dk.pejomi.consumingwebservice.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.HashMap;
import java.util.List;

public class ChartClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(ChartClient.class);

    public GetLineChartResponse getLineChartURL(String title, List<String> labels, HashMap<String, List<String>> datasets, Boolean fill) {
        GetLineChartRequest request = new GetLineChartRequest();
        LineChart lineChart = new LineChart();
        lineChart.setTitle(title);
        lineChart.getLabels().addAll(labels);

        for(String key : datasets.keySet()) {
            LineDataSet lineDataSet = new LineDataSet();
            lineDataSet.setLabel(key);
            lineDataSet.getData().addAll(datasets.get(key));
            lineDataSet.setFill(fill);
            lineChart.getDatasets().add(lineDataSet);
        }

        request.setLineChart(lineChart);

        log.info("Requesting line-chart url for " + lineChart.getTitle());

        GetLineChartResponse response = (GetLineChartResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8089/ws/charts", request,
                        new SoapActionCallback(
                                "http://pejomi.dk/chartsoap/xml/GetLineChartRequest"));
        return response;
    }

    public GetDoughnutChartResponse getDoughnutChartURL(String title, List<String> labels, List<List<String>> datasets) {
        GetDoughnutChartRequest request = new GetDoughnutChartRequest();
        DoughnutChart doughnutChart = new DoughnutChart();
        doughnutChart.setTitle(title);
        doughnutChart.getLabels().addAll(labels);

        for (List<String> dataset : datasets) {
            DoughnutDataSet doughnutDataSet = new DoughnutDataSet();
            doughnutDataSet.getData().addAll(dataset);
            doughnutChart.getDatasets().add(doughnutDataSet);
        }

        request.setDoughnutChart(doughnutChart);

        log.info("Requesting doughnut-chart url for " + doughnutChart.getTitle());

        GetDoughnutChartResponse response = (GetDoughnutChartResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8089/ws/charts", request,
                        new SoapActionCallback(
                                "http://pejomi.dk/chartsoap/xml/GetDoughnutChartRequest"));
        return response;
    }

    public GetBarChartResponse getBarChartURL(String title, List<String> labels, HashMap<String, List<String>> datasets) {
        GetBarChartRequest request = new GetBarChartRequest();
        BarChart barChart = new BarChart();
        barChart.setTitle(title);
        barChart.getLabels().addAll(labels);

        for(String key : datasets.keySet()) {
            BarDataSet barDataSet = new BarDataSet();
            barDataSet.setLabel(key);
            barDataSet.getData().addAll(datasets.get(key));
            barChart.getDatasets().add(barDataSet);
        }

        request.setBarChart(barChart);

        log.info("Requesting bar-chart url for " + barChart.getTitle());

        GetBarChartResponse response = (GetBarChartResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8089/ws/charts", request,
                        new SoapActionCallback(
                                "http://pejomi.dk/chartsoap/xml/GetBarChartRequest"));
        return response;
    }
}