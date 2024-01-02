package dk.pejomi.chartservice;

import dk.pejomi.consumingwebservice.wsdl.GetBarChartResponse;
import dk.pejomi.consumingwebservice.wsdl.GetDoughnutChartResponse;
import dk.pejomi.consumingwebservice.wsdl.GetLineChartResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class ChartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChartServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(ChartClient chartClient) {
        return args -> {
            //line
            List<String> labels = List.of("January", "February", "March", "April", "May", "June", "July");
            HashMap<String, List<String>> datasets = new HashMap<>();
            datasets.put("Dataset 1", List.of("65", "59", "80", "81", "56", "55", "40"));
            datasets.put("Dataset 2", List.of("28", "48", "40", "19", "86", "27", "90"));
            datasets.put("Dataset 3", List.of("12", "48", "77", "50", "96", "67", "90"));
            GetLineChartResponse response = chartClient.getLineChartURL("Hello", labels, datasets, false);
            System.err.println(response.getUrl());

            //doughnut
            List<String> labels2 = List.of("Red", "Blue", "Yellow");
            List<List<String>> datasets2 = new ArrayList<>();
            datasets2.add(List.of("300", "50", "100"));
            GetDoughnutChartResponse response2 = chartClient.getDoughnutChartURL("Hello", labels2, datasets2);
            System.err.println(response2.getUrl());

            //bar
            List<String> labels3 = List.of("Red", "Blue", "Yellow");
            HashMap<String, List<String>> datasets3 = new HashMap<>();
            datasets3.put("Dataset 1", List.of("65", "59", "80", "81", "56", "55", "40"));
            datasets3.put("Dataset 2", List.of("28", "48", "40", "19", "86", "27", "90"));
            datasets3.put("Dataset 3", List.of("28", "48", "40", "19", "86", "27", "90"));

            GetBarChartResponse response3 = chartClient.getBarChartURL("Hello", labels3, datasets3);
            System.err.println(response3.getUrl());
        };
    }
}
