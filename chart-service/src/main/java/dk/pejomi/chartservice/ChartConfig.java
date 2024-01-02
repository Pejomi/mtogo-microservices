package dk.pejomi.chartservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ChartConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("dk.pejomi.consumingwebservice.wsdl");
        return marshaller;
    }

    @Bean
    public ChartClient chartClient(Jaxb2Marshaller marshaller) {
        ChartClient client = new ChartClient();
        client.setDefaultUri("http://localhost:8089/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
