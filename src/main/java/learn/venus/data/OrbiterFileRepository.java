package learn.venus.data;

import learn.venus.models.Orbiter;
import java.util.List;

public class OrbiterFileRepository {


    private final String filePath;
    public OrbiterFileRepository(String filePath) {
        this.filePath = filePath;
    }
    public List<Orbiter> findAll(){
        return null;
    }
}
