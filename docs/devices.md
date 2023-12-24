For configuration support

All devices
  * CAN devices (Controllers)
  * Powered by PDP

So how to build instance from "template"?
(repetative otherwise? not for single items, though?)

Both? Build an instance or build a "Builder/Factory/Installer"?
How about initalizing the installed devices? Just supply values or handle init?

Equipment
  + template
    + CAN info -- mfr, type
    + PDP info -- peak power
  + instance
    + CAN Id
    + PDP channel, other profile
    + configuration properties

* PDP
  * All devices
* Controllers (CAN)
  * Ensure CAN devices are unique
* non CAN
  * Motors - power, gears, etc
  * Drive Train - wheel diameter, etc

Definitions - Equipment
Define the configurationItem, e.g., manufacturer, capabilities
Declare the device: CAN.Id, PDP.Channel, properties, gears, etc

Instances? Put them on CAN and/or PDP

variables/references or "Names" ??
The code explicitly communicates with the Controller, most of 1502 is CAN
The code also needs to know about non-CAN configuration properties

```
Max Speed
Go Straight Gain

  Module
    Translation2d (base width/length)
    gear ratio
    wheel diameter
    motor PIDs
    ramp rate (drive motor)
    current limit (drive motor)
  Drive
    Controller : ICAN (SparkMax) -- all control is through e.g., CANSparkMax
      ConfigurationItem (Motor) -- physical properties
        Reversed?
        IdleMode
        brushless?
        gear ratio
    ["hidden"] Controller : ICAN (SparkMax) RelativeEncoder
      ConfigurationItem (Encoder)
      offset
      direction (false == CCW)

  Turn
        Reversed?
        IdleMode
        brushless?
        gear ratio
        [-PI to +PI]
    CANcoder
        Direction
        Offset


```


Blinken LED Driver
REV-11-1105

add-on 
* 12V RBG LED Strip - 5m
* 5V Adressable LED Strip - 1m

2023 - 5V LED


```java
public class LedSubsystem extends SubsystemBase {
  public static final double WHITE = 0.93;
  public static final double YELLOW = 0.69;
  public static final double VIOLET = 0.91;
  
  public Spark left_blinkin = new Spark(0);
  public Spark right_blinkin = new Spark(1);

  public LedSubsystem(){
    left_blinkin.set(WHITE);
    right_blinkin.set(WHITE);
  }

  public void setYellow(){
    left_blinkin.set(YELLOW);
    right_blinkin.set(YELLOW);
  }

  public void setViolet(){
    left_blinkin.set(VIOLET);
    right_blinkin.set(VIOLET);
  }
}
```

# radio
[Radio -- WPILib](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/radio-programming.html)

The Radio Configuration Utility programs a number of configuration settings into the radio when run. These settings apply to the radio in all modes (including at events). These include:

Set a static IP of 10.TE.AM.1

Set an alternate IP on the wired side of 192.168.1.1 for future programming

Bridge the wired ports so they may be used interchangeably

The LED configuration noted in the graphic above.

4Mb/s bandwidth limit on the outbound side of the wireless interface (may be disabled for home use)

QoS rules for internal packet prioritization (affects internal buffer and which packets to discard if bandwidth limit is reached). These rules are:

Robot Control and Status (UDP 1110, 1115, 1150)

Robot TCP & NetworkTables (TCP 1735, 1740)

Bulk (All other traffic). (disabled if BW limit is disabled)

DHCP server enabled. Serves out:

10.TE.AM.11 - 10.TE.AM.111 on the wired side

10.TE.AM.138 - 10.TE.AM.237 on the wireless side

Subnet mask of 255.255.255.0

Broadcast address 10.TE.AM.255

DNS server enabled. DNS server IP and domain suffix (.lan) are served as part of the DHCP.

At home only:

SSID may have a “Robot Name” appended to the team number to distinguish multiple networks.

Firewall option may be enabled to mimic the field firewall rules (open ports may be found in the Game Manual)