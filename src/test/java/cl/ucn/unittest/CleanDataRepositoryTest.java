package cl.ucn.unittest;

import cl.ucn.domain.CleanData;
import cl.ucn.repository.CleanDataRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Test que inserta un dato en la tabla clean_readings usando JPA.
 */
public class CleanDataRepositoryTest {

    private EntityManagerFactory emf;
    private CleanDataRepository repo;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("environment");
        repo = new CleanDataRepository(emf);
    }

    @After
    public void tearDown() {
        emf.close();
    }

    @Test
    public void shouldPersistCleanData() {
        CleanData data = new CleanData("temperature", LocalDateTime.now(), 22.5);
        repo.save(data); // no se lanza excepción => éxito
    }
}
