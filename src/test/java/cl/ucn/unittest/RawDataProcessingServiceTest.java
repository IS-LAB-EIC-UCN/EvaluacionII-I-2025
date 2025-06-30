package cl.ucn.unittest;


import cl.ucn.domain.CleanData;
import cl.ucn.domain.RawData;
import cl.ucn.repository.CleanDataRepository;
import cl.ucn.repository.RawDataRepository;
import cl.ucn.service.RawDataProcessingService;
import cl.ucn.service.filters.RawDataFilter;
import cl.ucn.service.filters.ValidatorFilter;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test que simula el procesamiento completo usando mocks,
 * y verifica el contenido del objeto limpio generado.
 */
public class RawDataProcessingServiceTest {

    @Test
    public void shouldReturnCleanedDataWithExpectedValues() {
        // Dato de entrada simulado
        RawData input = new RawData("temperature", LocalDateTime.of(2025, 6, 30, 10, 0), 22.0, "C");

        // Mock de RawDataRepository
        RawDataRepository rawRepo = mock(RawDataRepository.class);
        when(rawRepo.findAll()).thenReturn(List.of(input));

        // Repositorio de salida simulado que guarda en lista para verificar con assert
        List<CleanData> cleanStorage = new ArrayList<>();
        CleanDataRepository cleanRepo = new CleanDataRepository(null) {
            @Override
            public void save(CleanData cleanData) {
                cleanStorage.add(cleanData);
            }
        };

        // Filtro real (podrías usar mock si fuera necesario)
        RawDataFilter filter = new ValidatorFilter();

        // Procesar
        RawDataProcessingService service = new RawDataProcessingService(rawRepo, cleanRepo, List.of(filter));
        service.processAll();

        // Assert de contenido
        assertEquals(1, cleanStorage.size());
        CleanData result = cleanStorage.get(0);
        assertEquals("temperature", result.getType());
        assertEquals(22.0, result.getValue(), 0.001);
        assertEquals(LocalDateTime.of(2025, 6, 30, 10, 0), result.getTimestamp());
    }
}
