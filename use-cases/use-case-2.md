# USE CASE: 2 Produce a City Report on the selected cities. 
## CHARACTERISTIC INFORMATION
### Goal in Context
Once one or more countries are selected, several candidate cities are evaluated as potential candidates.
### Scope
Company.

### Level
Primary task.

### Preconditions
The Scottish restaurant chain already has key criteria to consider, to choose a city suitable for a new restaurant. The database contains the relevant information about cities.

### Success End Condition
A Country Report is available to the company with the necessary data. 

### Failed End Condition
No City Report is produced.

### Primary Actor
Management board.

### Trigger
The Management board requests a set of data of the selected potential candidate cities. 

## MAIN SUCCESS SCENARIO
1.	Management board requests a City Report for previously selected cities.
2.	The company system has access to a software that can gather the above-mentioned information.
3.	The software extracts the data requested by the management board.
4.	The software inside the company’s system generates a City Report for each of the selected cities.
## EXTENSIONS
3. **Selected city does not have up-to-date data**:
    1. The software database is updated with an updated set of data.
## SUB-VARIATIONS
None.

## SCHEDULE
**DUE DATE**: release v0.1
