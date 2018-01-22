# HubrickBEChallenge

# Question:

https://github.com/hubrick/hubrick-backend-challenge.git

# Solution:

### **Things followed from the Assignment Question:**

1. **"GenerateReports"** is a Eclipse Java Project which can be checked out from the Github repository and imported into Eclipse and run as a Java Project.

    Repository Clone URL : https://github.com/MohamedSujahath/HubrickBEChallenge.git
    
2. **"GenerateReports.java"** under the folder (GenerateReports/src/com/hubrick/reports/GenerateReports.java) is the main Java Class which when executed by right click and Run As Java Application reads all the 3 input data CSV files and generates the 4 reports after the calculation.

3. The code is compilable with **Oracle JDK 1.8** and path to the directory containing the input data files is not necessary as all the input data CSV files are kept in the same directory as the Main Java class "GenericReports.java" that is enough.

4. Only libraries that are part of Oracle Java Runtime are used.

5. The Java class **"GenerateReports.java"** reads the following 3 input CSV files: (Input files have to be in the same directory as Main Java class "GenericReports.java)

      1. employees.csv
      2. departments.csv
      3. ages.csv
      
      Generates the following **4 reports as output in the Root directory of the Eclipse Project** (under GenerateReports folder)
      (with Header columns)
      
      1. AvgIncomeByAgeRange.csv
      2. MedianAgeByDepartment.csv
      3. MedianIncomeByDepartment.csv
      4. Percentile95IncomeByDepartment.csv
      
 6. The Department CSV files is **alphabetically sorted** after reading from the input csv file departments.csv and then only used for calculation.


### **Instructions for Execution:**

1. Clone the Github Repository using the following URL:

    https://github.com/MohamedSujahath/HubrickBEChallenge.git
    
2. You will get the Eclipse Java Project in your local desktop namely "GenerateReports".

3. Import the "GenerateReports" Java Project into your Eclipse Workspace.

4. Do a clean and build of the project.

5. Run the Main Java Class "GenerateReports.java" under the folder (GenerateReports/src/com/hubrick/reports/GenerateReports.java) to run the applciation. This is a standalone Java Program with a Main function.

6. It will read all the data input csv files kept under the same directory as the Java class and generate the 4 output reports in the Root folder of the Java project (/GenerateReports).

7. No need to pass the input CSV data file directory path separately to the application.

8. Now, you can open the output reports CSV files using a proper CSV editor to view the files or you can use any text editor.

9. All the CSV files contains a header for all the columns and the requested report data.


### **Other Files in the Project:**

1. Data Model classes for both the input CSV files and the Output CSV files.

2. CSVFileWriter.java - This Utility Java class helps in the writing the output CSV reports files
 
 
### **Technologies used:**

1. Java 8 (1.8)
2. Java 8 - **Streams API, Lambda expressions, filters, map, Sorting using comparators, Collectors, Functions, FileInputStream Readers, Buffered Reader, File Writer.**


###  **Calculation Mathematical Formula used:**

1. #### **Median Formula:**

    **First** the data set list is **sorted in ascending order** from lowest to highest and then,
      
      **If n is odd then Median (M) = value of ((n + 1)/2)th item term.**
      
      **If n is even then Median (M) = value of [((n)/2)th item term + ((n)/2 + 1)th item term ]/2**
    
    where n is the number of observations in the data list.
    
    You can use this following calculator to verify the Median Value for the data set.
    
     [Median Calculator] (http://www.alcula.com/calculators/statistics/median/).
    
2. #### **95th Percentile Formula:**

     **First** the data set list is **sorted in ascending order** from lowest to highest and then,

    **Index = p / 100 * n**
    
    where p is the p-th percentile which is 95 in this case and
    n is number of items in the data set list.
    
    Round up the Index i after the calculation to the nearest integer.
    
    The percentile is at the position of index after the rounding up of the decimal.
     
    You can use this following calculator to verify the percentile Value for the data set.
    
    [Percentile Calculator] (http://www.emathhelp.net/calculators/probability-statistics/percentile-calculator/?i=1%2C4%2C-3%2C2%2C-9%2C-7%2C0%2C-4%2C-1%2C2%2C1%2C-5%2C-3%2C10%2C10%2C5&p=95&steps=on).

3. #### **Average Formula:**

     **First** the data set list is **sorted in ascending order** from lowest to highest and then,
     
     
      **Average = (sum of all the values in the data set)/ n**
      
      where n - total number of items in the data set
      
      





    
