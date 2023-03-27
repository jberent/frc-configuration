package team1502.configuration;

import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.ICAN;

public class CANCoder extends Encoder implements ICAN {

    CanInfo canInfo;


    @Override
    public CanInfo GetCanInfo() {
        return canInfo;
    }
}