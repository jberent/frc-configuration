
[DOCS](https://forums.ni.com/t5/FIRST-Robotics-Competition/roboRIO-Details-and-Specifications/ta-p/3494658)


[SPECS](https://www.ni.com/docs/en-US/bundle/roborio-frc-specs/page/specs.html)

How to get info about devices?

netstat
```
  Proto  Local Address          Foreign Address        State
  TCP    10.15.2.171:50394      10.15.2.129:domain     TIME_WAIT
  TCP    10.15.2.171:50649      10.15.2.129:domain     TIME_WAIT
  TCP    10.15.2.171:51418      10.15.2.129:domain     TIME_WAIT
  TCP    10.15.2.171:51827      10.15.2.129:domain     TIME_WAIT
  TCP    10.15.2.171:55023      10.15.2.129:domain     TIME_WAIT
  TCP    10.15.2.171:60811      10.15.2.129:domain     TIME_WAIT
  TCP    10.15.2.171:61684      10.15.2.129:domain     TIME_WAIT
  TCP    10.15.2.171:61859      10.15.2.129:domain     TIME_WAIT
  ```

  10.15.2.2 is web page

2 2222 2222 1111 1111 11
8 7654 3210 9876 5432 1098 7654 3210
|  5   |    8    |  6   | 4  |  6  |
|    5 |       4 |      |    |   10|
  CAN 5,4, ?, ?, 10 = 05 04 ?? A

  504140A 7x4 = 28
  0101 0000 0100 0001 0100 0000 1010
  00101 00000100 000101 0000 001010
      5        4      5    0     10 == CTRE accel [50] #10
  Accel     CTRE    API  IDX    Num

The RoboRIO is a robotics-oriented update to the cRIO industrial controller (shown at the right), which has been employed in previous FRC contests, and which uses the same NI Real-Time Linux distribution and LabVIEW reconfigurable I/O architecture and toolset.

  The RoboRIO is faster, lighter, and smaller than the cRIO, and is designed specifically for FRC, says NI. It uses a faster version of the Xilinx Zynq system-on-chip, which integrates dual ARM Cortex-A9 cores and an FPGA.

# 2015
The RoboRIO integrates 256MB RAM and 512MB flash, and offers a Fast Ethernet port. USB 2.0 host and device ports are available, along with DIO, I2C, SPI, and analog I/O. There’s also a CAN port and PWM connector, as well as a relay port, robot signal port, and the Custom Electronics Port for MXP expansion.

RoboRIO
side view
Specifications listed for the RoboRIO include:

Processor — Xilinx Zynq-7020 (2x Cortex-A9 cores @ 667MHz); Artix-7 level FPGA
Memory — 256MB DDR3 RAM; 512MB flash
Networking — 10/100 Ethernet port
Other I/O:
USB 2.0 host port
USB 2.0 device port
16x DIO expansion port with 1x UART
I2C connector with 2x lanes
SPI connector with 4x lanes
CAN port
Analog input (12-bit)
Analog output (2x 12-bit channels on expansion port)
PWM connector with 10x output lines
Relay port (4x forward, 4x reverse)
RSL (robot signal light) port
Custom Electronics Port for MXP expansion boards
Other features — Reset and user buttons; LEDs
Power:
7 to 16 VDC power supply
3.3V, 5V, or 6V output
5W idle, 45W max. consumption
Ruggedization:
Operating temperature — 0 to 40°C
Vibration resistance (random) — 5 grms, 10Hz to 500Hz
Shock resistance — 50 g, 3ms half sine, 30 g, 11ms half sine, 18x shocks at 6x orientations
EMC certifications including EN 61326-1 (IEC 61326-1): Class A
Weight — 330 g (11.64 oz)
Operating system — NI Real-Time Linux; requires Windows PC to program and control