package dk.pejomi.chartservice.exception;

import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;

@SoapFault(faultCode = FaultCode.CLIENT)
public class MissingFieldException extends Exception {

    public MissingFieldException(String field) {
        super("Required field: '" + field + "' is missing in request.");
    }
}
