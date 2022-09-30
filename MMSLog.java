

import java.util.LinkedHashMap;
import java.util.Map;

public class MMSLog {

    private Map<String, MMS> logs;
    private Utils utils = new Utils();
    private int version = 1;

    public MMSLog() {
        logs = new LinkedHashMap<>();
    }

    public String addMMS(Supermarket supermarket, MMS mms){
        String recordId = supermarket.getName() + this.version;
        MMS persisted = this.logs.get(recordId);
        if(persisted == null){
            this.logs.put(recordId, mms);
            this.version += 1;
            return recordId;
        } else {
            logs.put(recordId, mms);
            this.version += 1;
            String newRecordId = supermarket.getName() + this.version;
            logs.put(newRecordId, persisted);
            return newRecordId;
        }
    }

    public void retrieveMMS(String recordId){
        MMS mms = this.logs.get(recordId);
        if(mms != null){
            mms.printMMSReport();
        } else {
            System.out.println("No MMS is recorded as: " + recordId);
        }
    }

    public void printMMSArchive(){
        this.utils.logHeader();
        int mmsValue = 1;
        for (Map.Entry<String, MMS> entry : this.logs.entrySet()) {
            String mmsName = "MMS " + mmsValue;
            System.out.format(this.utils.logFormat, mmsName, entry.getKey());
            mmsValue ++;
        }
        this.utils.LogTableEnd();
    }

    public Map<String, MMS> getLogs() {
        return logs;
    }

    public void setLogs(Map<String, MMS> logs) {
        this.logs = logs;
    }
}

