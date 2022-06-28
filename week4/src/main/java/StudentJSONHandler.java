import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class StudentJSONHandler extends StudentList{
    private File jsonFile;
    private ObjectMapper mapper;

    public StudentJSONHandler(String fileName){
        try {
            jsonFile = new File(fileName);
            // Create the JSON file if it does not exist, otherwise do nothing.
            jsonFile.createNewFile();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void readJSONFile(){
        mapper = new ObjectMapper();
        try {
            // Map the parsed values into a list of students and assign it to studentList.
            StudentList.studentList = mapper.readValue(jsonFile, new TypeReference<List<Student>>(){});
        } catch (IOException e){
            System.out.println("Invalid \"student_list.json\" file, discarding...");
        }
    }

    public void writeJSONFile() throws IOException {
        mapper = new ObjectMapper();
        // Create JSON string from the studentList data.
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentList);
        try {
            // Write the JSON string to student_list.json.
            BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(jsonFile));
            jsonWriter.write(json);
            jsonWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
