package cl.ucn.repository;

import cl.ucn.domain.RawData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * Repository class responsible for accessing {@link RawData} entries
 * from the {@code raw_readings} table in the database.
 *
 * <p>This class serves as a data access layer and provides methods
 * to query and retrieve raw sensor readings before they are filtered
 * or transformed.</p>
 *
 * <p>It is expected that the {@link EntityManagerFactory} is initialized
 * externally (e.g., in the main application class) and injected into this
 * repository upon construction.</p>
 *
 * <p>Methods such as {@link #findAll()} are intended to be implemented
 * by the student as part of the practical exercise.</p>
 *
 * @author Daniel San Martín
 */
public class RawDataRepository {

    /**
     * Factory used to create {@link jakarta.persistence.EntityManager} instances
     * for interacting with the persistence context.
     */
    private final EntityManagerFactory emf;

    /**
     * Constructs the repository using the given {@link EntityManagerFactory}.
     *
     * @param emf the factory used for creating entity managers
     */
    public RawDataRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Retrieves all raw data entries from the database.
     *
     * @return a list of all raw data records in the database
     */
    public List<RawData> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM RawData r", RawData.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Closes the EntityManagerFactory if open.
     */
    public void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
