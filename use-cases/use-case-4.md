# USE CASE: 4 Produce a Population Report on the selected countries.
## CHARACTERISTIC INFORMATION
### Goal in Context
Among the criteria to choose a country to open a new branch, the Scottish restaurant chain is evaluating other general criteria, in particular population. The Urban/Rural percentage of the population is also emphasized.

### Scope
Company.

### Level
Primary task.

### Preconditions
The database contains the relevant information about population.
### Success End Condition
A Population Report is available to the company with the necessary data. 

### Failed End Condition
No report is produced.

### Primary Actor
Management board.

### Trigger
The Management board requests a set of data about the population of the selected potential candidate countries. 

## MAIN SUCCESS SCENARIO
1.	Management board requests a Population Report for previously selected countries.
2.	The company system has access to a software that can gather the above-mentioned information.
3.	The software extracts the data requested by the management board.
4.	The software inside the company’s system generates a Population Report for each of the selected cities.
## EXTENSIONS
3. **Selected population does not have up-to-date data**:
    1. The software database is updated with an updated set of data.
## SUB-VARIATIONS
None.

## SCHEDULE
**DUE DATE**: Release v0.1
