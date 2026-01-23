#!/usr/bin/python

#
# This scrip searches through the schematic to check for components that don't have a reference number,
# have the same reference number, and for ones that are not in sequence.
#

import os
import re
from kiutils.schematic import Schematic

directory = os.fsencode(".")
schematic = ''

references = {
    # 'C' : ['C1', 'C1', 'C2', 'C3']
}

print("Indexing files ...")
for file in os.listdir(directory):
    # break
    

    filename = os.fsdecode(file)
    if not filename.endswith(".kicad_sch"): 
        continue

    # if not filename == 'analog_in_connector.kicad_sch':
    #     continue

    print("Found file '" + filename + "'")
    schematic = Schematic().from_file(file)

    for symbol in schematic.schematicSymbols:
        # exit(0)
        instanceReferences = []

        for instance in symbol.instances:
            for path in instance.paths:
                matches = re.search('^([A-Z]+)(\\d*)(\\?*)$', path.reference)
                if not matches: 
                    continue

                instanceReferences.append(path.reference)
                # print(path.reference)
                
                key = matches.group(1)
                if not key in references:
                    references[key] = []

                numberStr = matches.group(2)

                if matches.group(3) == '?' and numberStr == '':
                    references[key].append(-1)
                    continue
                
                references[key].append(int(matches.group(2)))
        
        for property in symbol.properties:
            if property.key == "Reference" and not property.value.startswith("#"):
                if not property.value in instanceReferences:
                    print("WARNING: \"" + property.value + "\" not in instance references")

    # break

print("\nChecking references ...")
# exit(0)

for key, value in references.items():
    print("Checking references of type '" + key + "'")
    value.sort()

    # if key == 'C':
    #     print(value)
    
    previous = 0
    for reference in value:
        if reference == -1:
            print("WARNING: Found unreferenced symbol: " + key + "?")
            continue

        # print("Reference: " + str(reference) + " Previous: " + str(previous))
        if reference - 1 != previous:
            if reference == previous:
                print("Duplicate reference " + str(reference))
            else:    
                for i in range(previous+1, reference):
                    print("Missing reference " + key + str(i))

        previous = reference
    
    print("Last reference " + key + str(value[-1]) + '\n')


