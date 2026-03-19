# Lynx HIB (Hardware Interface Board) Schematic
This repo contains a reverse engineered schematic of and info on the Lynx HIB (REV Robotics Expansion Hub, and the Expansion Hub part of the Control Hub). This was done with a multimeter; it may have some errors.

The schematic itself is in the `pcb` directory. Photos can be found in the `photos` directory. Notes on repairs can be found in the `repair notes` directory

## PCB versions
This schematic was created from a revision 2.3 Lynx HIB. This revision uses a Bosch BNO055 IMU and still includes an IMU in Expansion Hubs. Control Hubs and Expansion Hubs of this revision only differ by the inclusion of a compute board. Newer Control Hubs (sold after September 2022) seem to use a revision 2.4c or 2.5 HIB and have an Bosch BHI260AP IMU. The IMU section of the schematic has both in revisions's in it. Other sections seem to be the same aside from some component supplier changes and some additional zero ohm resistors around the compute board header. These zero ohm resistors aren't included in the schematic. I have not seen inside an Expansion Hub without an IMU (those sold after December 1, 2021), so I don't know how accurate this schematic is for them and how were changed from revision 2.3.

### References
- [Expansion Hub IMU Removal Notice](https://www.revrobotics.com/blog/expansion-hub-IMU-updates)
- [Control Hub and IMU differences](https://docs.revrobotics.com/duo-control/sensors/i2c/imu)

## Firmware

### MCU
The MCU firmware can be found on [REV's website](https://docs.revrobotics.com/duo-control/managing-the-control-system/updating-firmware/firmware-changelog).

#### First Upload
The first upload must be done with JTAG. <!-- ADD: JTAG upload instructions --> It may be possible to do over UART with the bootloader, but it hasn't been tested yet. 

A method for uploading over JTAG follows:
1) If your HIB's revision is > 2.3, solder on a JTAG connector or some leads
2) Acquire a "FTDI FT2232H Mini Module" to use as a JTAG debug probe. A genuine one from FTDI has a better chance of working reliably.
3) Connect the Mini Module to the HIB. A clean way of doing this is by making an adapter out of some 0.1" pitch header and half of one of the the pre terminated cables Molex sells that connect to the HIB's JTAG connector. See below for the part number. The pinout of the JTAG connector can be found in the schematic. Connect  
    - GND to GND
    - TCK to ADBUS0
    - TDI to ADBUS1
    - TDO to ADBUS2
    - TMS to ADBUS3
4) Download [OpenOCD](https://openocd.org/)
5) Download the config files for the Lynx and the Mini Module [here](https://github.com/DuckTapeAndAPrayer/DuckLynx/tree/master/openocd%20configs)
6) Run `openocd -f ftdi_lynx.cfg -c "program <path to firmware file> reset; shutdown`

#### Subsequent uploads
If firmware has be uploaded before it can be upgraded the same way or with the bootloader by using REV Hardware Client, the FtcRobotController app, or [DuckUpdate](https://github.com/DuckTapeAndAPrayer/DuckLynx/tree/master/DuckUpdate). [TI's LMFlashProgrammer](https://www.ti.com/tool/LMFLASHPROGRAMMER) will also work if the MCU is restarted into the bootloader. This can be done with DuckUpdate or with [REV's node-expansion-hub-ftdi](https://github.com/REVrobotics/node-expansion-hub-ftdi).

### FT230XQ
There is no firmware for this device, but it needs to have its config updated. Its config holds the serial number for the HIB, so when writing the same config to many devices be careful to not overwrite the serial number field. As well, it is connected to the reset and bootloader entry pins of the MCU and by default it will drive these pins to ground which will hold the MCU in reset forever. To fix this the CBUS pin modes of need to be changed to GPIO. The easiest way to do this is with [FTDI's FT_Prog](https://ftdichip.com/utilities/) utility, but it also can be done with [libftdi](https://www.intra2net.com/en/developer/libftdi/index.php) or [FTDI's d2xx](https://ftdichip.com/drivers/d2xx-drivers/) library. To update the modes with FT_Prog, connect the device to a computer running the program, edit `Hardware Specific` -> `CBUS Signals` to all be `GPIO`, and save the new config to the device.

### BNO055 & BHI260AP IMUs
Both IMU's have some firmware on them which can uploaded and read over SWD. I don't know if it's just the stock firmware or something custom.

### W25Q64JWSSIQ SPI Flash
This may not be used, but it may also have unique calibration data. It is yet to be tested.

## Components
- A large chunk of the passives are in a 0603 package
- Aux Shunt resistors are 20 mΩ (milliohm) in a 2512 package. P/N - Panasonic ERJ-M1WSF20MU

### ICs
| Description | Part Number | Additional Info |
| ----------- | ----------- | -------- |
| Main MCU | Texas Instruments TM4C123GH6PGEI7 |
| Motor controller | STMicroelectronics VNH5050AE |
| USB to UART | FTDI FT230XQ |
| RS485 transceiver | STMicroelectronics ST3485EB |
| Compute board bus transceiver | Texas Instruments SN74LVC8T245 |
| Op Amp for shunts | STMicroelectronics LMV321RILT | For AUX Shunts. Labeled K176. SOT 23-5 |
| 5V Buck converter driver | Texas Instruments TPS54527 | 
| 3.3V Buck converter driver | Texas Instruments TPS562209 |
| Adjustable shunt voltage regulator | Diodes Incorporated TL431ASA | For AREF. U17. labeled AAXXX |
| High side current monitor | Diodes Incorporated ZXCT1010E5TA | U25 |
| USB OTG ESD Diodes | Nexperia PUSBM5V5X4-TL | U19 |
| IMU | Bosch BNO055 | Labeled 701. Revision == 2.3 |
| IMU | Bosch BHI260AP | Revisions > 2.3 |
| 1.8V LDO | MCP1700T-1802E/TT | Revisions > 2.3 |
| Geomagnetic Sensor | Bosch BMM150 | Revisions > 2.3 |
| SPI flash | Winbond W25Q64JWSSIQ | Revisions > 2.3. For IMU |

### Diodes
| Description | Part Number | Additional Info |
| ----------- | ----------- | -------- |
| Status LED | Lite-On LTST-G683GEBW | D1 |
| Zener diode | onsemi 1SMB5931BT3G | For motor drives. D4 |
| Tiny ESD diode | Nexperia PESD15VS1UL | D5 |
| ESD protection diode array | Comchip CPDV5-5V0U(-HF?) | Labeled E5U. SOT-353 |
| ESD protection diode array | Comchip CPDV5-3V3UP(-HF?) | Labeled E3V3. SOT-353 |

### Transistors
Many of these have labels with more characters listed, but those parts are date codes.
| Description | Part Number | Additional Info |
| ----------- | ----------- | -------- |
| Big N channel MOSFET | onsemi FDD9409-F085 | Q29. For reverse current protection |
| NPN Transistor | ROHM DTC143XKA | Labeled 43. LED Driver |
| N channel MOSFET | Nexperia BSH103 | Labeled WJ3. Q15, Q19 |
| P channel MOSFET | Toshiba SSM3J328R | Labeled KFH. 5V servo power enable. Q20 |
| P channel MOSFET | Alpha and Omega AO3407A | Labeled X73L. Used throughout the board. Q22, Q23, Q14, Q27 |
| N channel MOSFET | Diodes Incorporated BSS138 | Labeled K38. Q28. Revision == 2.3 |
| N channel MOSFET | onsemi BSS138K | Labeled SK. Q28. Revision > 2.3 |
| N channel MOSFET | onsemi BSS138 | Labeled SS. Q30 & Q31. Only on revision > 2.3 |
| N channel MOSFET | Alpha and Omega AO3434A | Labeled Y4. Different unknown P/N and labeled N3 on revision == 2.3. Used for GPIO and servo ports |

### Connectors
| Description | Part Number | Additional Info |
| ----------- | ----------- | -------- |
| XT30 Male | Amass XT30UPB-M |
| XT30 Female | Amass XT30UPB-F |
| Mini USB B | MUSBS5FBM1RA | This part number exists nowhere on the internet. It is Leoco product series 0850 and P/N 0850BFBD111. The F could be J or K as the gold plating thickness is unknown. TE Connectivity P/N 1734035-1 seems to have the same pad dimensions, so it may work |
| JTAG Connector Female Molex | 53398-0671 | Connector not included on revision > 2.3. Same part should fit on SPI connector |
| JTAG Connector Male Cable Molex | 15134-0605 | Not on Lynx, but helpful for connecting a debugger |
| All shrouded external connectors | JST PH |
| All motor connectors | JST VH |

### Fuses
All from Bel Power? Bel Fuse? They are green.
| Description | Part Number | Additional Info |
| ----------- | ----------- | -------- |
| b2 Fuse | 0ZCJ0200FF2C | 2A hold current. Used on servo ports |
| bS Fuse | 0ZCH0150FF2E | 1.5A hold current. Used for USB port |
| b1 Fuse | 0ZCJ0100FF2E | 1A hold current. Used for GPIO ports |
| bM Fuse | 0ZCK0050FF2E | 0.5A hold current. Used for I2C, analog, and encoder ports |

### Mystery Components
- D2 - Different from revision == 2.3 to revision > 2.3
- D3 - Maybe ESD Protection Diode LittelFuse SMF17A
- All the capacitors
- L1
- L2
- Y2 - 16 MHz main oscillator
- Y1 - revision 2.3 IMU oscillator. 32.768 KHz
 
## Notes about the board
The board seems to have more than two layers, so the creation of a schematic by observation wasn't possible.

### Compute board header (J18)
The header that connects to the compute board (if equipped).

Pin numbering:
```
 1 - - 40
 2 - - 39
 3 - - 38
 :     :
 :     :
20 - - 21
```

The pinout of the connector on the compute board can be found on page 16 of the [96Boards CE Specification Version 1.0](https://raw.githubusercontent.com/96boards/documentation/master/Specifications/96Boards-CE-Specification.pdf).
  - The compute board of the Control Hub is based off this specification, see [gm0](https://gm0.org/en/latest/docs/software/adv-control-system/control-system-internals.html#control-hub) for details

### Bus transceivers
A is Compute Board Header - 1.8V  
B is System - 3.3V  
Eight channels

### MCU connections
Overall temp sensor - in MCU  
3.3V voltage sense - doesn't exist

### FTDI FT230XQ
PB1 (Bootconfig enter bootloader) FT230X Pin 11 (CBUS1) and TP3 (NPRG)  
RST FT230X Pin 12 (CBUS0) and TP2 (NRST)  
PJ7 FT230X Pin 5 (CBUS2)  
PK5 FT230X Pin 16 (RTS) and Compute Board Header 36

### Digital GPIO
- GPIOs are set as open drain pins and have pull up resistors  
- Output and input are from the perspective of the lynx  

### Analog GPIO
VCCA and GNDA are just connected to VCC and GND  
VREFA- is connected to GND  
VREFA+ is connected to a circuit that makes 2.994V. This is derived from the TL431A datasheet by using the formula `Vout = Vref * (1 + (R1 / R2))`, which in the case of the HIB is `2.994 = 2.495 * (1 + (2000 / 10000))`

### 5V rail
Voltage monitoring AIN23 (PP0) - Through resistor divider with two 10K resistors to divide voltage by 2.  

### Servo
Servo 5V enable PC6 - Has pull down  

### Battery
Voltage - connected to AIN22 (PP1) via a 1/6 resistor divider with 10K and 2K resistors  
Current - Uses a high side current monitor IC with a 0.003 ohm shunt and 3K Rout resistor into AIN13 (PD2)  

### External oscillator
Called main oscillator by the datasheet  
16 MHz  
