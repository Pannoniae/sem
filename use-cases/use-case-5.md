# USE CASE: 5 Produce a General Report on cities and countries with an emphasis on the language spoken
## CHARACTERISTIC INFORMATION
### Goal in Context
Having already commissioned a report on Countries, Cities, Capital Cities and Population, a general report with an emphasis on the language spoken is now requested. This, in order to have more information on the language requirements of the possible customers. Such data are useful when building apps or creating menus.

### Scope
Company.

### Level
Primary task.

### Preconditions
The database contains the relevant information about population, and in particular their native language.

### Success End Condition
A Population Report is available to the company with the necessary data, including the extra linguistic data.

### Failed End Condition
No report is produced.

### Primary Actor
Management board.

### Trigger
The Management board requests a set of data about the population of the selected potential candidate countries. 

## MAIN SUCCESS SCENARIO
1.	Management board requests a General Report for previously selected countries.
2.	The company system has access to a software that can gather the above-mentioned information.
3.	The software extracts the data requested by the management board.
4.	The software inside the company’s system generates a General Report for each of the selected country, with the corresponding linguistic details.
## EXTENSIONS
3. **Selected city does not have up-to-date data**:
    i. The software database is updated with an updated set of data.
## SUB-VARIATIONS
None.

## SCHEDULE
**DUE DATE**: release v0.1
