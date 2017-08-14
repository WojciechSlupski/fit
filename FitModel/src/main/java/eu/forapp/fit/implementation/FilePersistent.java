package eu.forapp.fit.implementation;

import com.google.gson.reflect.TypeToken;
import eu.forapp.fit.entity.Value;
import eu.forapp.fit.entity.Values;
import eu.forapp.fit.interfaces.Persistable;
import eu.forapp.util.JsonUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class FilePersistent implements Persistable {
    private String fileName;

    public FilePersistent(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Values read() {
        Values result;
        String json = readString();
        if(json != null){
            Type type = new TypeToken<TreeMap<LocalDate, Value>>(){}.getType();
            TreeMap<LocalDate, Value> temp = (TreeMap<LocalDate, Value>) JsonUtil.fromJson(json, type);
            result = new Values(temp);
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public boolean write(Values values) {
        String json = JsonUtil.toJson(values);
        return writeString(json);
    }

    private boolean writeString(String data) {
        boolean result = false;
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))){
            writer.write(data);
            result = true;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return result;
    }

    private String readString() {
        String result = null;
        Path path = Paths.get(fileName);
        if(Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {
                result = reader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
