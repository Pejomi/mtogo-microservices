package dk.pejomi.chartsoap.endpoint;

import dk.pejomi.chartsoap.service.ChartService;
import dk.pejomi.chartsoap.exception.MissingFieldException;
import dk.pejomi.chartsoap.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class ChartEndpoint {
    private static final String NAMESPACE_URI = "http://pejomi.dk/chartsoap/xml";

    @Autowired
    private ChartService chartService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLineChartRequest")
    @ResponsePayload
    public GetLineChartResponse getLineChart(@RequestPayload GetLineChartRequest request) throws MissingFieldException {
        GetLineChartResponse response = new GetLineChartResponse();
        response.setUrl(chartService.generateChartURL(request.getLineChart()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDoughnutChartRequest")
    @ResponsePayload
    public GetDoughnutChartResponse getDoughnutChart(@RequestPayload GetDoughnutChartRequest request) throws MissingFieldException {
        GetDoughnutChartResponse response = new GetDoughnutChartResponse();
        response.setUrl(chartService.generateChartURL(request.getDoughnutChart()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBarChartRequest")
    @ResponsePayload
    public GetBarChartResponse getBarChart(@RequestPayload GetBarChartRequest request) throws MissingFieldException {
        GetBarChartResponse response = new GetBarChartResponse();
        response.setUrl(chartService.generateChartURL(request.getBarChart()));
        return response;
    }
}
