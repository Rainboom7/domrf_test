package rainboom.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CsvRecord(separator = ",")
public class RequestBody {

    @DataField(pos = 1)
    private Long id;

    @DataField(pos = 2)
    private String name;

    @DataField(pos = 3)
    private Long sum;
}
