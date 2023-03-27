package team1502.configuration.CAN;

public interface ICAN {
    CanInfo GetCanInfo();
    default int GetCanId() {return GetCanInfo().DeviceNumber;}
    default void SetCanId(int number) {GetCanInfo().DeviceNumber = number;}
}