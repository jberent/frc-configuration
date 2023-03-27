package team1502.configuration;

import java.util.Hashtable;

import team1502.configuration.CAN.ICAN;

public class CANConfiguration extends ConfigurationItem{

    Hashtable<Integer, ICAN> Ids = new  Hashtable<>();
    
    public CANConfiguration Id(int id, ICAN canItem) throws Exception {
        if (Ids.containsKey(id)) {
            throw new Exception("Duplicate CAN ID");
        }
        Ids.put(id, canItem);
        canItem.SetCanId(id);
        return this;
    }

    public CANConfiguration Id(int id, String name) {
        return this;
    }
}
