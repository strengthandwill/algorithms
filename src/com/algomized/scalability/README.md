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


### CrackingTheCodingInterviewC10Q1

How would you design the data structures for a very large social network like
Facebook or Linkedln? Describe how you would design an algorithm to show
the connection, or path, between two people (e.g., Me -> Bob -> Susan -> Jason
-> You).

Data Structure

* The social network can be implemented using a graph data structure.
* Vertex will represent the people and edge between vertices will represent they know each other.
* The graph will be unidirectional since if person A know person B imply person B knows person A.
* The graph will be a sparse one, most people only know people within their social circle only.
* The graph can be implemented using an adjacent list since it will be sparse and changing frequently.
* The key for the graph will be the person ID which will be unique. The value of the graph will be the details
  of the person.
* When there is a new user, a new personal details will be created and inserted into the graph by person ID.
* When user delete his/her account, the vertex will not be deleted but disabled instead.
* When user A add a new connection to user B, edge will be added from A to B and B to A.
* When user A disconnect from user B, the edges will not be deleted but disabled instead.

Dividing People into Machines

* The data will need to be divided and store to reduce machine jumps to optimization searching of the graph.
* Since people tend to have most friends within the same country, the data can be divided by country, 
  where each machine will store data for each country.
* If there is too many people for a country, the data can be further divided by states.
* So each machine will have the graph of a country.
* The app will have two lookup tables. The first (personID-to-machineID) lookup table to map the machine ID 
  to the machines. The second (machines) lookup to map the personID to the machineID. 

Showing Connection between Two People

* Breadth first search (BFS) of the graph will be performed to show the connection between two people.
* First personID-to-machineID lookup table is used to determine machineID from source personID.
* Then machines lookup table is used to determine the machine where the source person is stored.
* From the machine, BFS is performed on the source person until destination person is reached. Along the way,
  the person vertices can be in different machines.
* A HashMap can be keep track which vertices are traverse during the BFS.


### CrackingTheCodingInterviewC10Q5

If you were designing a web crawler, how would you avoid getting into infinite
loops?

Avoiding Infinite Loops
* Use a graph to represent where the web pages are the vertices and links are the edges.
* To avoid getting into infinite loops, need to detect cycle
* A HashTable can be used to store the visited pages, so only if a page is visited, it will not
  be crawled again, therefore avoid infinite loops.
* Since the web is huge, Breadth First Search will be used to crawl layer by layer from the source.

Defining Visited Page
* Hard to define whether a page is visited or not base on the url and/or content.
* Many pages has dynamically generated url but are the same content.
* Many pages has randomly generated but has the same url.
* The problem can be solve by using signature and crawling with priority.
* Divide a page into sections and record the signature for each section.
* When arrive at a new url, count how many of the signatures are found. The lesser the signatures, 
  the less "visited" the page is. So the crawler will go on to the next page which have the highest priority.
* This will not complete the web, but it will prevent going into infinite loops. 


### CrackingTheCodingInterviewC10Q6

You have 10 billion URLs. How do you detect the duplicate documents? In this
case, assume that "duplicate" means that the URLs are identical.

* Each url: 100 chars = 400 bytes
* 10 billion urls = 2 ^ 30 * 10 * 400 bytes = 4000 * 2 ^ 30 bytes ~= 2 ^ 40 bytes = 4 TB  
* Url example: https://www.google.com.sg/  
* Trimmed Url: google.com.sg

Unlimited Memory
1. Run the urls and add the trimmed url into a HashSet. For each iteration, if the HashSet contains the trimmed url, 
   the url has duplicate, so print out the url.
  
Limited Memory (1 GB) 

Approach #1: One Pass - No storage
1. Run the urls and add the trimmed url into a HashSet while size < 250K. For each iteration, if the HashSet contains 
   the trimmed url, the url has duplicate, so print out the url.
2. When size > 250K, repeat Step 1 starting from the next of the last url, i, until the end and restart from
   the front until the url i - 1. Repeat the process until HashSet contains the last url of the data.
   
Approach #2: Two Passes - Disk Storage
1. First pass: Split the data into 4000 chucks of 1 GB and store to disk. One way is to use the hash value of the url 
   and mod with 4000.
2. Second pass: For each url and for each load file, load the file into the HashSet and the url for check with the HashSet 
   for duplicates.

Approach #3: Two Passes - Multiple Machines
1. First pass: Split the data into 4000 chucks of 1 GB and send to 40 machines. One way is to use the hash value of the url
   and mod with 4000.
2. Second pass: For each url, send the url to each of the machine to check for duplicates.
* Pro: Parallel processing which can cut down processing time.
* Con: Too many machines which increase the complexity and may be hard to handle machine failure.


### CrackingTheCodingInterviewC10Q7

Imagine a web server for a simplified search engine. This system has 100
machines to respond to search queries, which may then call out using
processSearch(string query) to another cluster of machines to actually
get the result. The machine which responds to a given query is chosen at
random, so you can not guarantee that the same machine will always respond
to the same request. The method processSearch is very expensive. Design
a caching mechanism for the most recent queries. Be sure to explain how you
would update the cache when data changes.

Query -> 100 Query Machines (Cache) -> processSearch(String query) -> Search Machines

1. Cache will have three column, query, lastModified and result.
2. Query is sent to machine i, if query is in machine i cache, return result.
3. If query is found in machine i cache, call isModified(lastModified). If is false, return the cache result. 
   If is true, call processSearch() to get result and update result and lastMoified in machine i.
4. If query is not found in machine i cache, call processSearch() to get result and store result and lastMoified 
   in machine i.
5. When the search machines found that the data is updated, it will update its last modified.   