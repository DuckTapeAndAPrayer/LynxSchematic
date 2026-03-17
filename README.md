# Lynx HIB (Hardware Interface Board) Schematic
This repo contains a reverse engineered schematic of the Lynx HIB (REV Robotics Expansion Hub, and the Expansion Hub part of the Control Hub). This was done with a multimeter; it may have some errors.

The schematic itself is in the `pcb` directory. Photos can be found in the `photos` directory.

## Note on PCB revisions
This schematic was created from a revision 2.3 Lynx HIB. This revision uses a Bosch BNO055 IMU and still includes an IMU in Expansion Hubs. Control Hubs and Expansion Hubs of this revision only differ by the inclusion of a compute board. Newer Control Hubs (sold after September 2022) seem to use a revision 2.4c or 2.5 HIB and have an Bosch BHI260AP IMU, so this schematic is not accurate for the IMU section of the newer boards. Other sections seem to be the same. I have not seen inside an Expansion Hub without an IMU (those sold after December 1, 2021), so I don't know how accurate this schematic is for them and how were changed from revision 2.3.

### References
- [Expansion Hub IMU Removal Notice](https://www.revrobotics.com/blog/expansion-hub-IMU-updates)
- [Control Hub and IMU differences](https://docs.revrobotics.com/duo-control/sensors/i2c/imu)

## Components
- A large chunk of the resistors are in a 0603 package
- Aux Shunt resistors are 20 mΩ (milliohm) in a 2512 package

### ICs
- Main MCU: Texas Instruments TM4C123GH6PGEI7
- Motor controller: STMicroelectronics VNH5050AE
- USB to UART: FTDI FT230XQ
- RS485 transceiver: STMicroelectronics ST3485EB
- Compute Board Bus transceiver: Texas Instruments SN74LVC8T245
- Op Amp for shunts labeled K176 SOT 23-5: STMicroelectronics LMV321RILT
- 5V Buck converter driver: Texas Instruments TPS54527
- 3.3V Buck converter driver: Texas Instruments TPS562209
- IMU for revisions <= 2.3 labeled 701: Bosch BNO055
- AREF adjustable shunt voltage regulator U17 labeled AAXXX: Diodes Incorporated TL431ASA
- High side current monitor U25: Diodes Incorporated ZXCT1010E5TA
- USB OTG ESD Diodes U19: Nexperia PUSBM5V5X4-TL
- SPI flash for Revisions > 2.3: Winbond W25Q64JWSSIQ 


### Diodes
- Big Zener diode for motor drives D4: onsemi 1SMB5931BT3G
- Tiny ESD diode D5: Nexperia PESD15VS1UL
- ESD protection diodes labeled E5U: Comchip CPDV5-5V0U(-HF?)
- ESD protection diodes labeled E3V3: Comchip CPDV5-3V3UP(-HF?)
- Status LED D1: Lite-On LTST-G683GEBW

### Transistors
- Reverse current protection big MOSFET: onsemi FDD9409-F085
- NPN Transistor 43: ROHM DTC143XKA
- N channel MOSFET labeled WJ3: Nexperia BSH103
- P channel MOSFET 5V servo power enable labeled KFH: Toshiba SSM3J328R
- P channel MOSFET phone charging labeled X73L: Alpha and Omega Semiconductor AO3407A
- N channel MOSFET labeled K38: Diodes Incorporated BSS138W
- N channel MOSFET labeled N32 GN (Last few characters may be different) Q13 and others

### Connectors
- XT30 Male: Amass XT30UPB-M
- XT30 Female: Amass XT30UPB-F
- Mini USB B: MUSBS5FBM1RA
  - That part number exists nowhere on the internet.
  - It also is Leoco product series 0850 and P/N 0850BFBD111. The F could be J or K as the gold plating thickness is unknown
  - TE Connectivity P/N 1734035-1 seems to have the same pad dimensions, so it may work
- JTAG Connector Female Molex 53398-0671
- JTAG Connector Male Cable Molex 15134-0605
- All shrouded external connectors: JST PH
- All motor connectors: JST VH

### Fuses
All from Bel Power? Bel Fuse?  
They are green  
b2 Fuse - 2A hold current - 0ZCJ0200FF2C  
bS Fuse - 1.5A hold current - 0ZCH0150FF2E  
b1 Fuse - 1A hold current - 0ZCJ0100FF2E  
bM Fuse - 0.5A hold current - 0ZCK0050FF2E  

### Mystery Components
D2  
D3 Maybe ESD Protection Diode LittelFuse SMF17A  
All the capacitors
 
## Notes about the board
The board seems to have more than two layers, so the creation of a schematic by observation wasn't possible.

### Compute Board Header (J18)
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

### Bus Transceivers
A is Compute Board Header - 1.8V  
B is System - 3.3V  
Eight channels

### MCU Connections
Overall temp sensor - in MCU  
3.3V voltage sense - doesn't exist

### FTDI FT230XQ
PB1 (Bootconfig enter bootloader) FT230X Pin 11 (CBUS1) and TP3 (NPRG)  
RST FT230X Pin 12 (CBUS0) and TP2 (NRST)  
PJ7 FT230X Pin 5 (CBUS2)  
PK5 FT230X Pin 16 (RTS) and Compute Board Header 36

### Digital GPIO
GPIOs are set as open drain pins and have pull up resistors  
The GPIO has a pull up resistor  
Output and input are from the perspective of the lynx  

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
