import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PPM {

    private Map<Integer, MaintenanceTask> tasks;

    public PPM() {
        tasks = new HashMap<>();
    }

    public void addMaintenanceTask(int assetId, String taskType, String taskDescription, Date dueDate) {
        MaintenanceTask task = new MaintenanceTask(assetId, taskType, taskDescription, dueDate);
        tasks.put(assetId, task);
    }

    public Map<Integer, MaintenanceTask> getTasks() {
        return tasks;
    }

    public void saveTasks() throws IOException {
        File file = new File("ppm.json");
        FileWriter writer = new FileWriter(file);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(tasks);
        writer.write(json);
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        PPM ppm = new PPM();
        ppm.addMaintenanceTask(1, "Hard Drive Replacement", "The hard drive on this asset is failing.", new Date());
        ppm.addMaintenanceTask(2, "Memory Upgrade", "The memory on this asset is not enough to run the latest software.", new Date());
        ppm.saveTasks();
    }
}

class MaintenanceTask {

    private int assetId;
    private String taskType;
    private String taskDescription;
    private Date dueDate;

    public MaintenanceTask(int assetId, String taskType, String taskDescription, Date dueDate) {
        this.assetId = assetId;
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
    }

    public int getAssetId() {
        return assetId;
    }

    public String getTaskType() {
        return taskType;
    }
