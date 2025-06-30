package cl.ucn.unittest;


import cl.ucn.domain.RawData;
import cl.ucn.service.filters.UnitNormalizerFilter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Test que verifica conversión de Fahrenheit a Celsius.
 */
public class UnitNormalizerFilterTest {

    private UnitNormalizerFilter filter;

    @Before
    public void setUp() {
        filter = new UnitNormalizerFilter();
    }

    @Test
    public void shouldConvertFahrenheitToCelsius() throws Exception {
        RawData fahrenheit = new RawData("temperature", LocalDateTime.now(), 68.0, "F");
        RawData result = filter.apply(fahrenheit);
        assertEquals(20.0, result.getValue(), 0.1);
        assertEquals("C", result.getUnit());
    }
}

