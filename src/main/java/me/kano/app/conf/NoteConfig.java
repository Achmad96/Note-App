package me.kano.app.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

@SuppressWarnings("All")
public class NoteConfig {

    private final String fileName = "note-app/Notes.json";
    private final File file = new File(fileName);
    private JSONArray jsonArray = new JSONArray();
    private static NoteConfig instance = null;
    private Integer index;

    public void loadData() {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else if (!file.exists()) file.createNewFile();
            final FileWriter fw = new FileWriter(file);
            final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            fw.write(mapper.writeValueAsString(mapper.readTree(jsonArray.toJSONString())));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDataFile(){
        if (!file.getParentFile().exists() || !file.exists() || file.length() == 0) return;
        try (FileReader reader = new FileReader(file)) {
            final JSONParser jsonParser = new JSONParser();
            jsonArray = (JSONArray) jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public static NoteConfig getInstance(){
        if (instance == null) instance = new NoteConfig();
        return instance;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}