#!/usr/bin/python

import skip
import json
schem = skip.Schematic('pcb.kicad_sch')

sheetJson = json.load(open('sheets.json'))

# for sheetID of schem.sheet:
for i, sheet in enumerate(schem.sheet):
    name = sheet.property.Sheetname.value
    if name == 'MCU':
        continue

    newPage = sheetJson[sheet.uuid.value] 
    print('Changing "' + name + '" uuid: "' + sheet.uuid.value + '" from page ' + sheet.instances.project.path.page.value + ' to ' + newPage)
    schem.sheet[i].instances.project.path.page.value = newPage

schem.write('pcb.kicad_sch')
