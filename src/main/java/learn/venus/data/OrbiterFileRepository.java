package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrbiterFileRepository {


    private final String filePath;

    public OrbiterFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Orbiter> findAll() {
        ArrayList<Orbiter> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); //skip the header

            for(String line = reader.readLine(); line!=null; line= reader.readLine()){
                String[] fields = line.split(",",-1);

                if(fields.length == 4){
                    Orbiter orbiter = new Orbiter();
                    orbiter.setOrbiterId(Integer.parseInt(fields[0]));
                    orbiter.setName(fields[1]);
                    orbiter.setType(OrbiterType.valueOf(fields[2]));
                    orbiter.setSponsor(fields[3]);
                    result.add(orbiter);
                }
            }
        } catch (IOException ex) {
            //do nothing
        }

        return result;
    }

    public Orbiter findById(int orbiterId){
        for(Orbiter orbiter:findAll()){
            if(orbiter.getOrbiterId() == orbiterId){
                return orbiter;
            }
        }
        return null;
    }

    public List<Orbiter> findByType(OrbiterType type){
        ArrayList<Orbiter> result = new ArrayList<>();
        for(Orbiter orbiter:findAll()){
            if(orbiter.getType() == type){
                result.add(orbiter);
            }
        }
        return  result;
    }

    public Orbiter add(Orbiter orbiter){
        List<Orbiter> all = findAll();
        int nextId=0;
        for(Orbiter o:all){
            nextId = Math.max(nextId,o.getOrbiterId());
        }
        nextId++;
        orbiter.setOrbiterId(nextId);

        all.add(orbiter);
        writeAll(all);

    }

    public boolean update(Orbiter orbiter){
        return false;
    }

    public boolean deleteById(int orbiterId){
        return false;
    }

    private  void writeAll(List<Orbiter> orbiters){
        try(PrintWriter writer = new PrintWriter(filePath)){
            writer.println("orbiterId,name,type,sponsor");//print header
            for(Orbiter o:orbiters){
                writer.println(serialize(o));
            }

        }catch(IOException ex){
            //
        }
    }

    private  String serialize(Orbiter orbiter){
        return String.format("%s, %s, %s, %s",
                orbiter.getOrbiterId(),
                orbiter.getName(),
                orbiter.getType(),
                orbiter.getSponsor());
    }
}
