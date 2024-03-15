package learn.venus.data;

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



}