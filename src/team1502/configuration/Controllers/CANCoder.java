package team1502.configuration.Controllers;

import team1502.configuration.Encoder;
import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.ICAN;

public class CANCoder extends Encoder /* implements ICAN */{

    CanInfo canInfo;


    //@Override
    public CanInfo getCanInfo() {
        return canInfo;
    }
}