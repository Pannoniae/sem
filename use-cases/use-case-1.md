# USE CASE: 1 Produce a Country Report for several key countries.
## CHARACTERISTIC INFORMATION
## Goal in Context
As a successful traditional Scottish restaurant chain, we want to produce a report on which country we should invest in. We want to invest a big enough country where, if the first restaurant successful, we’d have enough market to open other branches. 

### Scope
Company.

### Level
Primary task.

### Preconditions
The Scottish restaurant chain already has the knowhow of opening a restaurant. The database contains the relevant information about countries around the world. 

### Success End Condition
A Country Report is available to the company with the necessary data. 

### Failed End Condition
No Country Report is produced.

### Primary Actor
Management board.

### Trigger
The Management board requests a set of data of countries around the world. 

## MAIN SUCCESS SCENARIO
1.	Management board requests a Country Report for previously selected countries.
2.	The company system has access to a software that can gather the above-mentioned information.
3.	The software extracts the data requested by the management board.
4.	The software inside the company’s system generates a Country Report for each of the selected countries.
## EXTENSIONS
3. **Selected country does not have up-to-date data**:
    1. The software database is updated with an updated set of data.
## SUB-VARIATIONS
None.

## SCHEDULE
**DUE DATE**: Release v0.1
