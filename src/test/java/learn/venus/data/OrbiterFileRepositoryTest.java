package learn.venus.data;

import learn.venus.models.OrbiterType;
import org.junit.Test;
import learn.venus.models.Orbiter;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class OrbiterFileRepositoryTest {
    private final OrbiterFileRepository repository = new OrbiterFileRepository("./data/orbiters.csv");

    @Test
    public void shouldFindFiveOrbiters(){
        List<Orbiter> actual = repository.findAll();
        assertNotNull(actual);

        assertEquals(5,actual.size());

    }

    @Test
    public void shouldFindExistingOrbiter(){
        Orbiter celestyn = repository.findById(4);
        assertNotNull(celestyn);
        assertEquals("Celestyn Kamien",celestyn.getName());
    }
    @Test
    public void shouldNotFindExistingOrbiter(){
        Orbiter nope = repository.findById(1000);
        assertNull(nope);
    }

    @Test
    public void shouldFindOneOfEachType(){
        List<Orbiter> modules = repository.findByType(OrbiterType.MODULE);
        assertNotNull(modules);
        assertEquals(1,modules.size());


        List<Orbiter> modulesWithDocks = repository.findByType(OrbiterType.MODULE_WITH_DOCK);
        assertNotNull(modulesWithDocks);
        assertEquals(1,modulesWithDocks.size());

        List<Orbiter> astronauts = repository.findByType(OrbiterType.ASTRONAUT);
        assertNotNull(astronauts);
        assertEquals(1,astronauts.size());


        List<Orbiter> shuttles = repository.findByType(OrbiterType.SHUTTLE);
        assertNotNull(shuttles);
        assertEquals(1,shuttles.size());

        List<Orbiter> venusians = repository.findByType(OrbiterType.VENUSIAN);
        assertNotNull(venusians);
        assertEquals(1,venusians.size());



    }



}