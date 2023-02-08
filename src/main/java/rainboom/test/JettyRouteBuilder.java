package rainboom.test;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.BindyDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.validation.PredicateValidationException;

import java.util.Arrays;
import java.util.List;

public class JettyRouteBuilder extends RouteBuilder {

    private final Long multiplier;

    public JettyRouteBuilder ( Long multiplier ) {
        this.multiplier = multiplier;
    }

    @Override
    public void configure ( ) {
        from ( "jetty:http://localhost:7000" )
                .validate ( header ( "Token" ).isEqualTo ( "SECRET" ) )
                .unmarshal ( )
                .json ( JsonLibrary.Jackson, RequestBody[].class )
                .log ( LoggingLevel.ERROR, "Request size is: ${body.length}" )
                .process ( e ->
                        Arrays.stream ( e.getIn ( ).getBody ( RequestBody[].class ) )
                                .forEach ( i -> i.setSum ( i.getSum ( ) * multiplier ) ) )
                .marshal ( )
                .bindy ( BindyType.Csv, RequestBody.class )
                .to ( "file:E:/test?fileName=result.csv" )
                .setBody ( simple ( "Success" ) );
    }

}
