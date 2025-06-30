package cl.ucn.unittest;


import cl.ucn.domain.RawData;
import cl.ucn.service.filters.ValidatorFilter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Test que valida que el filtro ValidatorFilter detecta entradas inválidas.
 * Usa `@Test(expected = ...)` para verificar error en tipo nulo.
 */
public class ValidatorFilterTest {

    private ValidatorFilter filter;

    @Before
    public void setUp() {
        filter = new ValidatorFilter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTypeIsNull() throws Exception {
        RawData data = new RawData(null, LocalDateTime.now(), 25.0, "C");
        filter.apply(data);
    }
}
