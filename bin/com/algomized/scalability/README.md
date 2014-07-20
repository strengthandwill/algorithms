## Scalability and Memory Limits


### Steps to approach scalability questions:
1. How to solve the problem if there is no memory limitations?
2. How to solve the problem if there is memory limitation? 
   How much data can be fit in one machine?
   How can the data be divided among the machines (e.g. logical grouping)?
   How to identify the machine that the data is on? 
3. Based on Step 1 and 2, solve the problem iteratively


### Ways to Divide Data
1. Order of appearance (PRO: Use least number of machines, CON: Lookup table maybe large and complex)
2. Hash value (PRO: No lookup table needed, CON: Expensive shifting of data is needed if data exceeds capacity) 
3. Actual value (e.g. by country)
4. Arbitrarily (PRO: Simplifies system design, CON: Lookup table maybe large and complex)


### CrackingTheCodingInterviewC10Q1

Imagine you are building some sort of service that will be called by up to 1000
client applications to get simple end-of-day stock price information (open,
close, high, low). You may assume that you already have the data, and you can
store it in any format you wish. How would you design the client-facing service
which provides the information to client applications? You are responsible
for the development, rollout, and ongoing monitoring and maintenance of
the feed. Describe the different methods you considered and why you would
recommend your approach. Your service can use any technologies you wish,
and can distribute the information to the client applications in any mechanism
you choose.

Factors
* Ease to implement for client
* Usefulness for client
* Ease to implement for server
* Maintenance for server
* Flexibility for change in future
* Scalability and Efficiency

Approaches will be evaluate base on implementation, maintenance, query, integration and security.

Approach #1
Text file (FTP)
* Implement: Easy (Text file can be transfer easily between server and clients, and client can also download the file as backup)
* Maintain: Easy (Only need to append the new content to the text file)
* Query: Hard (Need complex parsing to perform query and parsing may need to be changed if new content is added) 
* Security: Low (Depend on FTP, cannot selective secure the content)
* Integration: Hard (Interface need to written for other apps to parse the text file for their use)

Approach #2
XML (FTP)
* Implement: Easy (Text file can be transfer easily between server and clients, and client can also download the file as backup)
* Maintain: Easy (Add new node, add new tag for more new attributes)
* Query: Easy but limited (Most app can parse XML, but query will give the whole file)
* Security: Low (Depend on FTP, cannot selective secured the content)
* Integration: Easy (Most apps can parse XML easily)

Approach #3
SQL DB (Direct)
* Implement: Medium (Need to implement controller layer to allow user to manage the data layer as SQL is not too-human-readable)
* Maintain: Easy and efficient (Using existing standard backup or restore,does not need to reinvent the wheel)
* Query: Easy (Use of SQL statement allow data to be query in all kind of ways by SQL administrator)
* Security: Good (Existing well-defined security levels, care is needed to ensure only the right permission is granted)  
* Integration: Easy (Most apps can integrate with SQL easily)

Approach #4
XML (Web Service)
* Implement: Medium (Need to implement the web service to access the data layer)
* Maintain: Easy (Add new node, add new tag for more new attributes)
* Query: Easy but limited (Most app can parse XML, but query will give the whole file)
* Security: Depend on web service
* Integration: Easy (Most apps can parse XML easily)

Approach #5
SQL DB (Web Service)
* Implement: Medium (Need to implement the web service to access the data layer)
* Maintain: Easy and efficient (Using existing standard backup or restore,does not need to reinvent the wheel)
* Query: Easy (Use of API allow extensive query of SQL DB)
* Security: Depend on web service
* Integration: Easy (Most apps can integrate with web service easily)

Approach #5 will be the best, as it is easy to maintain, query and integrate with other apps. 
SQL also allow extensive query to be performed on the data. Managing the data via web service 
allow extensive query of SQL DB to be performed via APIs. Web service allow clients to connect 
and interact with the server easily over TCP/IP, enabling ease of implementation for the clients.   


