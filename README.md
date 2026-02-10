# Lynx HIB (Hardware Interface Board) Schematic
This repo contains a reverse engineered schematic of the Lynx HIB (REV Robotics Expansion Hub and the Expansion Hub part of the Control Hub). This was done with a multimeter. It may have some errors.

The schematic itself is in the `pcb` directory. Other info can be found in the `info` directory.

## Note on PCB revisions
This schematic was created from a rev 2.3 Lynx HIB. This revision uses a Bosch BNO055 IMU and still includes an IMU in Expansion Hubs. Control Hubs and Expansion Hubs of this revision only differ by the inclusion of an Android Board. Newer Control Hubs (sold after September 2022) seem to use a rev 2.4c HIB and have an Bosch BHI260AP IMU, so this schematic is not accurate for the IMU section of a rev 2.4c board. Other sections seem to be the same. I have not seen inside a  Expansion Hub without an IMU (those sold after December 1, 2021), so I don't know how they have changed from rev 2.3 and how accurate this schematic is for them.

### References
[Expansion Hub](https://www.revrobotics.com/blog/expansion-hub-IMU-updates)  
[Control Hub and IMU differences](https://docs.revrobotics.com/duo-control/sensors/i2c/imu)

## Components
A large chunk of the resistors are 0603  
Aux Shunt resistors are 20 milli ohm in a 2512 package

### ICs
Main MCU [TM4C123GH6PGEI7](https://www.ti.com/product/TM4C123GH6PGE/part-details/TM4C123GH6PGEI7) [docs](https://www.ti.com/product/TM4C123GH6PGE#tech-docs)  
Motor controller VNH5050AE full bridge motor controller  
USB to UART FT230XQ  
RS485 transceiver ST3485EB  
Bus transceiver SN74LVC8T245  
Op Amp for shunts K176 SOT 23-5 ST LMV321RILT  
5V Buck converter driver TPS54527  
3.3V Buck converter driver TPS562209  
701 IMU Bosch BNO055  
AAXXX U17 Adjustable shunt voltage regulator TL431ASA  
High side current monitor U25 ZXCT1010E5TA  
USB OTG ESD Diodes U19 PUSBM5V5X4-TL

### Diodes
Big zener diode for motor drives D4 1SMB5931BT3G  
Tiny ESD diode D5 PESD15VS1UL  
ESD Protection E5U Chip CPDV5-5V0U  
ESD Protection E3V3 Chip CPDV5-3V3UP  
Status LED D1 LTST-G683GEBW

### Transistors
Reverse current protection big MOSFET FDD9409-F085  
NPN Transistor 43 DTC143XKA  
N channel MOSFET WJ3 BSH103  
P channel MOSFET KFH 5V servo power enable SSM3J328R  
P channel MOSFET phone charging X73L AO3407A  
N channel MOSFET K38 BSS138W  
N channel MOSFET N32 GN (Last few characters may be different) Q13 and others AO3434A

### Connectors
XT30 Male Amass [XT30UPB-M](https://www.tme.com/ux/en-us/details/xt30upb-m/dc-power-connectors/amass/)  
XT30 Female Amass [XT30UPB-F](https://www.tme.com/ux/en-us/details/xt30upb-f/dc-power-connectors/amass/)  
Mini USB B [MUSBS5FBM1RA](https://leoco.com.tw/product/mini-usb-connector/)  
 - That part number exists nowhere on the internet.
 - It also is Leoco product series 0850 and P/N 0850BFBD111. The F could be J or K as the gold plating thickness is unknown
 - TE Connectivity P/N 1734035-1 seems to have the same pad dimensions, so it may work  
JTAG Connector Female Molex [53398-0671](https://www.molex.com/en-us/products/part-detail/533980671)  
JTAG Connector Male Cable Molex [15134-0605](https://www.molex.com/en-us/products/part-detail/151340605)  
All of shrouded external connectors are JST PH  
All the motor connectors are JST VH  

### Fuses
All from Bel Power? Bel Fuse?  
They are green  
b2 Fuse - 2A hold current - 0ZCJ0200FF2C  
bS Fuse - 1.5 A hold current - 0ZCH0150FF2E  
b1 Fuse - 1A hold current - 0ZCJ0100FF2E  
bM Fuse - 0.5A hold current - 0ZCK0050FF2E  

### Mystery Components
D2  
D3 Maybe ESD Protection Diode LittelFuse SMF17A  
 
## Notes about the board
The board seems to have more than two layers, so the creation of a schematic by observation wasn't possible

### Android Header (J18)
The header that connects to a Android board (if equipped)  
Pin numbering:
```
 1 - - 40
 2 - - 39
 3 - - 38
 :     :
 :     :
20 - - 21
```

### Bus Transceivers
A is Android Header - 1.8V  
B is System - 3.3V  
Eight channels

### MCU Connections
Overall temp sensor - in MCU  
3.3V voltage sense - doesn't exist

### FT230XQ
PB1 (Bootconfig enter bootloader) FT230X Pin 11 (CBUS1) and TP3 (NPRG)  
RST FT230X Pin 12 (CBUS0) and TP2 (NRST)  
PJ7 FT230X Pin 5 (CBUS2)  
PK5 FT230X Pin 16 (RTS) and Android Header 36

### Digital GPIO
GPIOs are set as open drain pins and have pull up resistors  
The GPIO has a pull up resistor  
Output and input are from the perspective of the lynx  

### Analog GPIO
VCCA and GNDA are just connected to VCC and GND  
VREFA- is connected to GND  
VREFA+ is connected to a circuit that makes 2.994V. This is derived from the TL431A datasheet by using the formula Vout = Vref * (1 + (R1/R2)) which in the case   of the HIB is 2.994 = 2.495 * (1 + (2000 / 10000))  

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
