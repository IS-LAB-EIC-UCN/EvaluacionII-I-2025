package cl.ucn.unittest;


import cl.ucn.domain.RawData;
import cl.ucn.service.filters.ExtremeValueFilter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Test que verifica que se rechaza temperatura fuera del rango permitido.
 */
public class ExtremeValueFilterTest {

    private ExtremeValueFilter filter;

    @Before
    public void setUp() {
        filter = new ExtremeValueFilter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectExtremeTemperature() throws Exception {
        RawData extreme = new RawData("temperature", LocalDateTime.now(), -100.0, "C");
        filter.apply(extreme);
    }
}
