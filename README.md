# Lynx HIB (Hardware Interface Board) Schematic
This repo contains a reverse engineered schematic of the Lynx HIB (REV Robotics Expansion Hub, and the Expansion Hub part of the Control Hub). This was done with a multimeter; it may have some errors.

The schematic itself is in the `pcb` directory. Other info can be found in the `info` directory.

## Note on PCB revisions
This schematic was created from a revision 2.3 Lynx HIB. This revision uses a Bosch BNO055 IMU and still includes an IMU in Expansion Hubs. Control Hubs and Expansion Hubs of this revision only differ by the inclusion of a compute board. Newer Control Hubs (sold after September 2022) seem to use a revision 2.4c HIB and have an Bosch BHI260AP IMU, so this schematic is not accurate for the IMU section of a revision 2.4c board. Other sections seem to be the same. I have not seen inside an Expansion Hub without an IMU (those sold after December 1, 2021), so I don't know how accurate this schematic is for them/how they have changed from revision 2.3.

### References
- [Expansion Hub IMU Removal Notice](https://www.revrobotics.com/blog/expansion-hub-IMU-updates)
- [Control Hub and IMU differences](https://docs.revrobotics.com/duo-control/sensors/i2c/imu)
- [96Boards CE Specification Version 1.0](https://raw.githubusercontent.com/96boards/documentation/master/Specifications/96Boards-CE-Specification.pdf)
  - The compute board of the Control Hub is based off this specification, see https://gm0.org/en/latest/docs/software/adv-control-system/control-system-internals.html#control-hub for details

## Components
- A large chunk of the resistors are in a 0603 package
- Aux Shunt resistors are 20 mΩ (milliohm) in a 2512 package

### ICs
- Main MCU: [Texas Instruments TM4C123GH6PGEI7](https://www.ti.com/product/TM4C123GH6PGE/part-details/TM4C123GH6PGEI7) ([Documentation](https://www.ti.com/product/TM4C123GH6PGE#tech-docs))
- Motor controller: [STMicroelectronics VNH5050AE](https://www.st.com/en/automotive-analog-and-power/vnh5050a-e.html) ([Datasheet](https://www.st.com/resource/en/datasheet/vnh5050a-e.pdf))
- USB to UART: [FTDI FT230XQ](https://ftdichip.com/products/ft230xq/) ([Datasheet](https://ftdichip.com/wp-content/uploads/2025/06/DS_FT230X.pdf))
- RS485 transceiver: [STMicroelectronics ST3485EB](https://www.st.com/en/interfaces-and-transceivers/st3485eb.html) ([Datasheet](https://www.st.com/resource/en/datasheet/st3485eb.pdf))
- Bus transceiver: [Texas Instruments SN74LVC8T245](https://www.ti.com/product/SN74LVC8T245) ([Datasheet](https://www.ti.com/lit/gpn/sn74lvc8t245))
- Op Amp for shunts K176 SOT 23-5: [STMicroelectronics LMV321RILT](https://www.st.com/en/amplifiers-and-comparators/lmv321.html) ([Datasheet](https://www.st.com/resource/en/datasheet/lmv321.pdf))
- 5V Buck converter driver: [Texas Instruments TPS54527](https://www.ti.com/product/TPS54527) ([Datasheet](https://www.ti.com/lit/gpn/tps54527))
- 3.3V Buck converter driver: [TPS562209](https://www.ti.com/product/TPS562209) ([Datasheet](https://www.ti.com/lit/gpn/tps562209))
- 701 IMU: [Bosch BNO055](https://www.bosch-sensortec.com/en/products/smart-sensor-systems/bno055) ([Datasheet](https://www.bosch-sensortec.com/media/boschsensortec/downloads/datasheets/bst-bno055-ds000.pdf))
- AAXXX U17 Adjustable shunt voltage regulator: [Diodes Incorporated TL431ASA](https://www.diodes.com/part/view/TL431) ([Datasheet](https://www.diodes.com/datasheet/download/TL431.pdf))
- High side current monitor U25: [Diodes Incorporated ZXCT1010E5TA](https://www.diodes.com/part/view/ZXCT1010) ([Datasheet](https://www.diodes.com/datasheet/download/ZXCT1010.pdf))
- USB OTG ESD Diodes U19: [Nexperia PUSBM5V5X4-TL](https://www.nexperia.com/product/PUSBM5V5X4-TL) ([Datasheet](https://assets.nexperia.com/documents/data-sheet/PUSBMXX4-TL_SER.pdf))

### Diodes
- Big Zener diode for motor drives D4: [onsemi 1SMB5931BT3G](https://www.onsemi.com/products/discrete-power-modules/zener-diodes/1smb59) ([Datasheet](https://www.onsemi.com/download/data-sheet/pdf/1smb5913bt3-d.pdf))
- Tiny ESD diode D5: [Nexperia PESD15VS1UL](https://www.nexperia.com/product/PESD15VS1UL) ([Datasheet](https://assets.nexperia.com/documents/data-sheet/PESD15VS1UL.pdf))
- ESD Protection E5U Chip: [Comchip CPDV5-5V0U(-HF?)](https://www.mouser.com/ProductDetail/Comchip-Technology/CPDV5-5V0U-HF?qs=2qJf6qQ4IOKyXnxzuK20Gw%3D%3D) ([Datasheet](https://www.mouser.com/datasheet/3/819/1/CPDV5_5V0U_HF_RevC472094.pdf))
- ESD Protection E3V3 Chip: [Comchip CPDV5-3V3UP(-HF?)](https://www.mouser.com/ProductDetail/Comchip-Technology/CPDV5-3V3UP-HF?qs=NTN7dFTirrveEvJddmRgzA%3D%3D) ([Datasheet](https://www.mouser.com/datasheet/3/819/1/QW-JP023%20CPDV5-3V3UP-HF%20RevC.pdf))
- Status LED D1: [Lite-On LTST-G683GEBW](https://optoelectronics.liteon.com/en-global/Led/LED-Component/Detail/777/0/0/12/168) ([Datasheet](https://optoelectronics.liteon.com/upload/download/DS22-2011-0291/LTST-G683GEBW.PDF))

### Transistors
- Reverse current protection big MOSFET: [onsemi FDD9409-F085](https://www.onsemi.com/products/discrete-power-modules/mosfets/low-medium-voltage-mosfets/fdd9409_f085) ([Datasheet](https://www.onsemi.com/download/data-sheet/pdf/fdd9409_f085-d.pdf))
- NPN Transistor 43: [ROHM DTC143XKA](https://www.rohm.com/products/transistors/digital-transistors/standard/dtc143xka-product) ([Datasheet](https://fscdn.rohm.com/en/products/databook/datasheet/discrete/transistor/digital/dtc143xe3-e.pdf))
- N channel MOSFET WJ3: [Nexperia BSH103](https://www.nexperia.com/product/BSH103) ([Datasheet](https://assets.nexperia.com/documents/data-sheet/BSH103.pdf))
- P channel MOSFET KFH 5V servo power enable: [Toshiba SSM3J328R](https://toshiba.semicon-storage.com/us/semiconductor/product/mosfets/detail.SSM3J328R.html) ([Datasheet](https://toshiba.semicon-storage.com/info/SSM3J328R_datasheet_en_20250220.pdf?did=2429&prodName=SSM3J328R))
- P channel MOSFET phone charging X73L: [Alpha and Omega Semiconductor AO3407A](https://www.aosmd.com/products/mosfets/p-channel-mosfets-8v-60v/ao3407a) ([Datasheet](https://www.aosmd.com/sites/default/files/res/datasheets/AO3407A.pdf))
- N channel MOSFET K38: **BSS138W**, has multiple producers:
  - [omsemi](https://www.onsemi.com/products/discrete-power-modules/mosfets/small-signal-mosfets/bss138w) ([Datasheet](https://www.onsemi.com/download/data-sheet/pdf/bss138w-d.pdf))
  - [Diodes Incorporated](https://www.diodes.com/part/view/BSS138W) ([Datasheet](https://www.diodes.com/datasheet/download/BSS138W.pdf))
- N channel MOSFET N32 GN (Last few characters may be different) Q13 and others: [Alpha and Omega Semiconductor AO3434A](https://www.aosmd.com/products/mosfets/low-voltage-mosfets-12v-30v/ao3434a) ([Datasheet](https://www.aosmd.com/sites/default/files/res/datasheets/AO3434A.pdf))

### Connectors
- XT30 Male: [Amass XT30UPB-M](https://www.tme.com/ux/en-us/details/xt30upb-m/dc-power-connectors/amass/)
- XT30 Female: [Amass XT30UPB-F](https://www.tme.com/ux/en-us/details/xt30upb-f/dc-power-connectors/amass/)
- Mini USB B: [MUSBS5FBM1RA](https://leoco.com.tw/product/mini-usb-connector/)
  - That part number exists nowhere on the internet.
  - It also is Leoco product series 0850 and P/N 0850BFBD111. The F could be J or K as the gold plating thickness is unknown
  - TE Connectivity P/N 1734035-1 seems to have the same pad dimensions, so it may work
- JTAG Connector Female Molex [53398-0671](https://www.molex.com/en-us/products/part-detail/533980671)
- JTAG Connector Male Cable Molex [15134-0605](https://www.molex.com/en-us/products/part-detail/151340605)
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
VREFA+ is connected to a circuit that makes 2.994V. This is derived from the [TL431A datasheet](https://www.diodes.com/datasheet/download/TL431.pdf) by using the formula `Vout = Vref * (1 + (R1 / R2))`, which in the case of the HIB is `2.994 = 2.495 * (1 + (2000 / 10000))`

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
