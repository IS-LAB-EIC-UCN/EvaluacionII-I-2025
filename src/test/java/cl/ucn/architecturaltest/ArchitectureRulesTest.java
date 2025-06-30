package cl.ucn.architecturaltest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Reglas de arquitectura validadas con ArchUnit.
 * - R1: Filtros no deben acceder a repositorios (excepto el servicio de aplicación).
 * - R2: Cada clase filtro debe implementar RawDataFilter.
 */
public class ArchitectureRulesTest {

    // Cargar todas las clases del paquete base
    private final JavaClasses imported = new ClassFileImporter().importPackages("cl.ucn");

    /**
     * R1: Ningún filtro (salvo el servicio final) debe depender directamente de los repositorios.
     */
    @Test
    public void filtersShouldNotAccessRepositories() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..filters..")
                .should().dependOnClassesThat().resideInAPackage("..repository..")
                .because("los filtros deben ser independientes de la capa de persistencia (Regla R1)");

        rule.check(imported);
    }

    /**
     * R2: Toda clase en el paquete filters debe implementar la interfaz RawDataFilter.
     */
    @Test
    public void allFiltersShouldImplementRawDataFilter() {
        ArchRule rule = classes()
                .that().resideInAPackage("..filters..")
                .and().areNotInterfaces()
                .should().implement(cl.ucn.service.filters.RawDataFilter.class)
                .because("cada filtro debe implementar la interfaz RawDataFilter (Regla R2)");

        rule.check(imported);
    }
}
