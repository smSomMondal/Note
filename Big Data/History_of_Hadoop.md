# History of Hadoop — Complete Study Notes

---

## Question 1a: What is Hadoop? Explain Its Components (5M)

### What is Hadoop?

**Apache Hadoop** is an open-source, distributed computing framework designed to store and process **massive datasets** (Big Data) across clusters of commodity hardware using simple programming models.

- Originally developed by **Doug Cutting** and **Mike Cafarella** in 2006
- Inspired by Google's **MapReduce** paper (2004) and **Google File System (GFS)** paper (2003)
- Named after Doug Cutting's son's toy elephant
- Part of the **Apache Software Foundation**

### Key Principles:
- **Scale out, not up**: Add more commodity machines instead of expensive servers
- **Move computation to data**: Process data where it lives (locality)
- **Fault tolerance**: Hardware failures are expected and handled automatically
- **Batch processing**: Designed for large-scale offline processing

### Core Components of Hadoop

```
┌──────────────────────────────────────────────────┐
│                  HADOOP ECOSYSTEM                  │
│                                                    │
│  ┌────────────────────────────────────────────┐   │
│  │         PROCESSING LAYER                    │   │
│  │  ┌──────────────┐  ┌────────────────────┐  │   │
│  │  │  MapReduce   │  │   Apache Spark     │  │   │
│  │  │  (Batch)     │  │   (In-memory)      │  │   │
│  │  └──────────────┘  └────────────────────┘  │   │
│  └────────────────────────────────────────────┘   │
│                        ↑                           │
│  ┌────────────────────────────────────────────┐   │
│  │         RESOURCE MANAGEMENT                 │   │
│  │              YARN                           │   │
│  │  (Yet Another Resource Negotiator)          │   │
│  └────────────────────────────────────────────┘   │
│                        ↑                           │
│  ┌────────────────────────────────────────────┐   │
│  │         STORAGE LAYER                       │   │
│  │              HDFS                           │   │
│  │  (Hadoop Distributed File System)           │   │
│  └────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────┘
```

#### 1. HDFS (Hadoop Distributed File System)
- Distributed, fault-tolerant file system
- Stores data across multiple nodes
- Files split into **blocks** (default 128 MB)
- Each block **replicated 3 times** (replication factor)
- **NameNode**: Master — manages metadata
- **DataNode**: Worker — stores actual data blocks

#### 2. MapReduce
- Programming model for parallel data processing
- **Map phase**: Transform input data into key-value pairs
- **Reduce phase**: Aggregate intermediate key-value pairs
- Runs across the cluster in parallel

#### 3. YARN (Yet Another Resource Negotiator)
- Cluster resource management layer (since Hadoop 2.x)
- **ResourceManager**: Global master for cluster resources
- **NodeManager**: Per-node agent managing containers
- Decouples resource management from processing

#### 4. Hadoop Common
- Shared libraries and utilities used by other Hadoop modules
- Includes Java files and scripts required to start Hadoop
- Provides authentication, serialization (Writable), RPC

### Hadoop Ecosystem Tools

| Tool        | Purpose                              |
|-------------|--------------------------------------|
| Hive        | SQL-like queries on HDFS             |
| Pig         | Scripting language for data flows    |
| HBase       | NoSQL database on top of HDFS        |
| Sqoop       | Import/export data between RDBMS and HDFS |
| Flume       | Collecting log data into HDFS        |
| Oozie       | Workflow scheduler for Hadoop jobs   |
| ZooKeeper   | Distributed coordination service     |
| Mahout      | Machine learning on Hadoop           |

---

## Question 1b: How Do You Analyze Data in Hadoop? (7M)

Data analysis in Hadoop follows a structured pipeline using its distributed processing capabilities.

### Step 1: Data Ingestion
Load data into HDFS from various sources:

```
Data Sources:
  Databases    ──[Sqoop]──→
  Log Files    ──[Flume]──→  HDFS
  Files        ──[HDFS CLI]→
  Streams      ──[Kafka]──→
```

Commands:
```bash
# Copy local file to HDFS
hadoop fs -put /local/data.csv /user/hadoop/input/

# Copy from HDFS to local
hadoop fs -get /user/hadoop/output/ /local/results/

# List files
hadoop fs -ls /user/hadoop/
```

### Step 2: Data Storage in HDFS
- Data is split into **128 MB blocks**
- Each block replicated 3× for fault tolerance
- NameNode tracks block locations in memory

### Step 3: Choose the Analysis Approach

#### Approach A: MapReduce (Low-level, Java)
Write custom Mapper and Reducer classes to process data:
```
Input Data → [Mapper] → Intermediate (k,v) pairs
           → [Shuffle & Sort] → Grouped by key
           → [Reducer] → Final output
```

#### Approach B: Apache Hive (SQL-like)
Write HQL (Hive Query Language) for SQL-familiar analysis:
```sql
-- Count words in a document
SELECT word, COUNT(*) AS freq
FROM word_table
GROUP BY word
ORDER BY freq DESC;
```
Hive translates this into MapReduce jobs automatically.

#### Approach C: Apache Pig (Scripting)
High-level scripting language (Pig Latin):
```pig
data = LOAD '/input/data.txt' AS (line:chararray);
words = FOREACH data GENERATE FLATTEN(TOKENIZE(line)) AS word;
grouped = GROUP words BY word;
counts = FOREACH grouped GENERATE group, COUNT(words);
STORE counts INTO '/output/';
```

#### Approach D: Apache Spark (In-memory, faster)
```python
# PySpark example
sc = SparkContext()
counts = sc.textFile("/input/data.txt") \
           .flatMap(lambda x: x.split(" ")) \
           .map(lambda x: (x, 1)) \
           .reduceByKey(lambda a, b: a + b)
counts.saveAsTextFile("/output/")
```

### Step 4: Submit and Monitor Jobs

```bash
# Submit a MapReduce job
hadoop jar myapp.jar com.example.WordCount \
    /user/hadoop/input /user/hadoop/output

# Monitor via Web UI
# ResourceManager UI: http://master:8088
# HDFS NameNode UI:   http://master:50070
```

### Step 5: Retrieve and Visualize Results

```bash
# View output
hadoop fs -cat /user/hadoop/output/part-r-00000

# Download results
hadoop fs -get /user/hadoop/output/ ./results/
```

### Analysis Pipeline Diagram

```
Raw Data (TB/PB)
      ↓
  [HDFS Storage]
      ↓
  [MapReduce / Hive / Pig / Spark]
      ↓
  [Intermediate Results in HDFS]
      ↓
  [Final Aggregation / Reduction]
      ↓
  [Results: Reports, Models, Insights]
      ↓
  [Visualization: Tableau, Power BI, Grafana]
```

---

## Question 2: Mapper Class, Reducer Class, and Scaling Out

### 2a: Mapper Class (5M)

The **Mapper** class is the first phase of MapReduce processing. It processes each input record and emits zero or more **key-value pairs** as intermediate output.

#### Mapper Contract:
```
Input:  (input_key, input_value)
Output: [(intermediate_key, intermediate_value), ...]
```

#### Java Mapper Class Structure:
```java
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.*;

public class WordCountMapper 
    extends Mapper<LongWritable, Text, Text, IntWritable> {
    
    // Input:  LongWritable (byte offset), Text (line of text)
    // Output: Text (word), IntWritable (count=1)
    
    private Text word = new Text();
    private final static IntWritable one = new IntWritable(1);
    
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        
        // Convert Text to String and split by whitespace
        String line = value.toString();
        String[] words = line.split("\\s+");
        
        // Emit each word with count 1
        for (String w : words) {
            word.set(w.toLowerCase());
            context.write(word, one);  // Emit (word, 1)
        }
    }
}
```

#### Mapper Execution Flow:
```
Input File (split into chunks)
        ↓
  InputFormat splits file into InputSplits
  (one split per Mapper task, typically 1 HDFS block)
        ↓
  RecordReader reads each record (key, value)
        ↓
  map(key, value, context) called for each record
        ↓
  Mapper emits (intermediate_key, intermediate_value)
        ↓
  Output written to local disk (not HDFS)
```

#### Mapper Phases:
1. **Setup**: Called once before map() — initialize resources
2. **Map**: Called for each input record — core logic
3. **Cleanup**: Called once after all map() calls — release resources

#### Mapper Example (Word Count):
```
Input line: "Hello World Hello Hadoop"

Mapper emits:
  ("hello", 1)
  ("world", 1)
  ("hello", 1)
  ("hadoop", 1)
```

#### Key Mapper Concepts:

| Concept         | Description                                              |
|-----------------|----------------------------------------------------------|
| Input Split     | Portion of input file processed by one Mapper            |
| RecordReader    | Converts splits into (key, value) pairs for map()        |
| Data Locality   | Mapper runs on node where data block resides             |
| Combiner        | Optional mini-reducer on Mapper output (local aggregation)|
| Partitioner     | Determines which Reducer receives each intermediate key  |

---

### 2b: Reducer Class (5M)

The **Reducer** class processes the intermediate key-value pairs produced by Mappers and aggregates them into final output.

#### Reducer Contract:
```
Input:  (intermediate_key, [list of values])
Output: [(output_key, output_value), ...]
```

#### Java Reducer Class Structure:
```java
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.*;

public class WordCountReducer 
    extends Reducer<Text, IntWritable, Text, IntWritable> {
    
    // Input:  Text (word), Iterable<IntWritable> (list of 1s)
    // Output: Text (word), IntWritable (total count)
    
    private IntWritable result = new IntWritable();
    
    @Override
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context)
            throws IOException, InterruptedException {
        
        int sum = 0;
        
        // Sum all values for this key
        for (IntWritable val : values) {
            sum += val.get();
        }
        
        result.set(sum);
        context.write(key, result);  // Emit (word, totalCount)
    }
}
```

#### Reducer Execution Flow:
```
Intermediate (key, value) pairs from ALL Mappers
        ↓
  SHUFFLE: Transfer data from Mapper nodes to Reducer nodes
        ↓
  SORT: Group all values for the same key together
        ↓
  reduce(key, Iterable<values>, context) called per unique key
        ↓
  Reducer emits final (key, value) to HDFS
```

#### Shuffle and Sort Phase (between Map and Reduce):
```
Mapper 1 output:    Mapper 2 output:    Mapper 3 output:
(hello, 1)          (hadoop, 1)         (hello, 1)
(world, 1)          (hello, 1)          (world, 1)
(hello, 1)          (world, 1)

                    ↓ Shuffle & Sort ↓

Reducer input (sorted by key):
key="hadoop" → values=[1]
key="hello"  → values=[1, 1, 1, 1]
key="world"  → values=[1, 1, 1]

Reducer output:
(hadoop, 1)
(hello, 4)
(world, 3)
```

#### Reducer Phases:
1. **Setup**: Initialize resources (called once)
2. **Reduce**: Process each key and its value list
3. **Cleanup**: Release resources (called once)

---

### 2c: Scaling Out (2M)

**Scaling out** (horizontal scaling) means adding more commodity machines to the cluster to handle larger data and more parallel tasks, rather than upgrading individual machines (vertical scaling).

#### How Hadoop Scales Out:

```
Small Cluster (10 nodes):
[Master] ← 9 Worker Nodes
  → Can process: ~1 TB/hour

Large Cluster (1000 nodes):
[Master] ← 999 Worker Nodes
  → Can process: ~100 TB/hour

Adding more nodes ≈ Linear throughput increase
```

#### Benefits of Scaling Out:
- **Cost-effective**: Commodity hardware (not enterprise servers)
- **Linear scalability**: Double nodes ≈ double throughput
- **Fault tolerance**: Losing one node of 1000 is not critical
- **Flexibility**: Add/remove nodes without downtime

#### Scaling Challenges:
- More nodes → More potential failures → Need robust fault tolerance
- Network bandwidth becomes the bottleneck
- Data locality decreases as cluster grows

---

## Question 3: Failures in MapReduce (12M)

MapReduce is designed to handle failures gracefully. Failures can occur at multiple levels:

### Types of Failures

#### 1. Task Failure (Most Common)

**Causes:**
- JVM crash (OutOfMemoryError, NullPointerException)
- Infinite loop or task timeout
- Hardware fault on worker node

**Detection:**
- Each task reports heartbeat to the Application Master (AM)
- If no heartbeat for **10 minutes** (configurable), AM marks task as failed

**Recovery:**
```
Task fails on Node A
      ↓
Application Master detects failure (heartbeat timeout)
      ↓
AM reschedules task on a DIFFERENT node (Node B, C, or D)
      ↓
Task retried up to mapreduce.map.maxattempts (default=4)
      ↓
If all attempts fail → Job fails
```

**Speculative Execution:**
```
Slow Task (Straggler) on Node A ──→ still running at 80% completion
                                          ↓
                          AM launches speculative copy on Node B
                                          ↓
                     Whichever finishes first → used as result
                     Other task killed
```
Enabled by: `mapreduce.map.speculative=true`

#### 2. Application Master (AM) Failure

**Causes:**
- JVM crash on the AM node
- Node failure hosting the AM

**Detection:**
- YARN ResourceManager monitors AM via heartbeat
- AM heartbeat timeout triggers failure detection

**Recovery:**
```
AM fails
    ↓
ResourceManager detects AM failure
    ↓
RM launches new AM instance on another node
    ↓
New AM consults job history server to determine:
    - Which tasks already completed (read from HDFS — safe)
    - Which tasks were in progress (need to re-run)
    ↓
AM re-runs incomplete tasks only
    ↓
Job continues (may have brief pause)
```

Max AM attempts: `yarn.resourcemanager.am.max-attempts` (default=2)

#### 3. Node Manager Failure

**Causes:**
- Machine crash (hardware failure, OS crash, power loss)
- Network partition (node becomes unreachable)

**Detection:**
- ResourceManager monitors NodeManagers via heartbeat
- Default heartbeat interval: 1 second
- Node considered dead after 10 minutes of no heartbeat

**Recovery:**
```
NodeManager N₃ fails
      ↓
ResourceManager detects failure (heartbeat timeout)
      ↓
RM removes N₃ from available resources pool
      ↓
RM notifies Application Masters running tasks on N₃
      ↓
Each AM reschedules affected tasks on surviving nodes
      ↓
HDFS NameNode detects DataNode on N₃ is down
      ↓
NameNode triggers block re-replication for blocks on N₃
      ↓
Missing replicas copied to other nodes to restore factor=3
```

#### 4. Resource Manager Failure (Most Severe)

**Causes:**
- Master node hardware failure
- RM process crash

**Detection & Recovery (HA Mode):**
```
Active ResourceManager → Fails
          ↓
Standby ResourceManager detects failure via ZooKeeper
          ↓
Standby RM promotes itself to Active
          ↓
Reads application state from shared storage (ZooKeeper/HDFS)
          ↓
Running jobs can recover; submitted jobs resume
```

Without HA: All running jobs must be resubmitted (catastrophic failure).

#### 5. Data Node Failure

**Causes:**
- Hard drive failure
- Network interface failure
- Node crash

**Recovery:**
```
DataNode D₅ fails (stores blocks B₁, B₂, B₃)
          ↓
NameNode detects DataNode missing (heartbeat)
          ↓
NameNode checks replication state of B₁, B₂, B₃
          ↓
If any block now has < 3 replicas:
    NameNode instructs other DataNodes to copy blocks
          ↓
Replication restored to factor=3
```

### Failure Handling Summary Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                    MAPREDUCE FAILURE HANDLING                    │
│                                                                  │
│  Level          Detected By          Recovery Action            │
│  ─────────────────────────────────────────────────────────────  │
│                                                                  │
│  Task Failure → Application Master → Reschedule on other node   │
│                                      (up to 4 attempts)         │
│                                                                  │
│  AM Failure   → ResourceManager   → Launch new AM,             │
│                                      re-run incomplete tasks    │
│                                                                  │
│  NodeManager  → ResourceManager   → Remove from pool,          │
│    Failure                           notify AMs                 │
│                                                                  │
│  DataNode     → NameNode          → Re-replicate lost blocks   │
│    Failure                                                       │
│                                                                  │
│  NameNode     → Secondary NN /    → Manual failover or         │
│    Failure      ZooKeeper (HA)      automatic HA promotion      │
│                                                                  │
│  ResourceMgr  → ZooKeeper (HA)    → Standby RM takes over      │
│    Failure                                                       │
└─────────────────────────────────────────────────────────────────┘
```

### Key Configuration Parameters

| Parameter                           | Default | Description                        |
|-------------------------------------|---------|------------------------------------|
| mapreduce.map.maxattempts           | 4       | Max retry attempts for Map task    |
| mapreduce.reduce.maxattempts        | 4       | Max retry attempts for Reduce task |
| mapreduce.task.timeout              | 600000  | Task timeout in ms (10 min)        |
| mapreduce.map.speculative           | true    | Enable speculative execution       |
| yarn.resourcemanager.am.max-attempts| 2       | Max AM restart attempts            |

---

## Question 4: MapReduce Data Flow with Single and Multiple Reducers (12M)

### Overview of MapReduce Data Flow

```
Input Data (HDFS)
      ↓
  [Input Format] → Splits input into InputSplits
      ↓
  [Map Phase] → Runs one Mapper per InputSplit (in parallel)
      ↓
  [Combiner] → Optional local aggregation (mini-reduce)
      ↓
  [Partitioner] → Routes intermediate (k,v) to correct Reducer
      ↓
  [Shuffle & Sort] → Transfer + sort across network
      ↓
  [Reduce Phase] → Runs Reducers (in parallel)
      ↓
  Output Data (HDFS)
```

---

### Data Flow with Single Reducer

When only **one Reducer** is used, all intermediate output from all Mappers is sent to the single Reducer.

```
Input: "Hello World Hello Hadoop World"
Split into 2 input splits:

InputSplit 1: "Hello World Hello"
InputSplit 2: "Hadoop World"

┌─────────────────────────────────────────────────────────────────┐
│                  SINGLE REDUCER DATA FLOW                        │
│                                                                  │
│  InputSplit 1              InputSplit 2                          │
│       ↓                         ↓                                │
│  [Mapper 1]               [Mapper 2]                            │
│  (hello,1)                (hadoop,1)                            │
│  (world,1)                (world,1)                             │
│  (hello,1)                                                       │
│       │                         │                                │
│       └────────────┬────────────┘                               │
│                    ↓                                             │
│             Shuffle & Sort                                       │
│          (all data to 1 reducer)                                 │
│                    ↓                                             │
│              [Reducer 1]                                         │
│         hadoop → [1]      → (hadoop, 1)                         │
│         hello  → [1,1]    → (hello, 2)                          │
│         world  → [1,1]    → (world, 2)                          │
│                    ↓                                             │
│             HDFS: part-r-00000                                   │
│             hadoop 1                                             │
│             hello  2                                             │
│             world  2                                             │
└─────────────────────────────────────────────────────────────────┘
```

**Characteristics:**
- **One output file**: `part-r-00000`
- All data flows through one Reducer → can be bottleneck
- Total ordering of output is possible
- Suitable for small datasets or when total ordering is required

---

### Data Flow with Multiple Reducers

When **multiple Reducers** are used, intermediate data is partitioned across them using a Partitioner.

```
Default Partitioner formula:
  partition = hash(key) % numReducers
```

```
Input: Large file split into 3 InputSplits
numReducers = 2

┌─────────────────────────────────────────────────────────────────┐
│                MULTIPLE REDUCER DATA FLOW                        │
│                                                                  │
│  Split 1    Split 2    Split 3                                   │
│     ↓          ↓          ↓                                      │
│  [Map 1]   [Map 2]   [Map 3]                                    │
│  (a,1)     (b,1)     (a,1)                                      │
│  (b,1)     (c,1)     (d,1)                                      │
│  (c,1)     (d,1)     (b,1)                                      │
│     │          │          │                                      │
│     └────── PARTITIONER ──┘                                     │
│          hash(key) % 2                                           │
│                                                                  │
│  Partition 0 (even hash):    Partition 1 (odd hash):            │
│  keys: a, b, d              keys: c                             │
│     ↓                            ↓                              │
│  [Reducer 1]                [Reducer 2]                         │
│  Shuffle+Sort               Shuffle+Sort                         │
│  a → [1,1] → (a,2)         c → [1,1] → (c,2)                  │
│  b → [1,1,1]→ (b,3)                                             │
│  d → [1,1] → (d,2)                                              │
│     ↓                            ↓                              │
│  part-r-00000               part-r-00001                         │
│  a 2                        c 2                                  │
│  b 3                                                             │
│  d 2                                                             │
└─────────────────────────────────────────────────────────────────┘
```

**Setting number of reducers:**
```java
job.setNumReduceTasks(2);  // In Java driver

// Or via command line:
// -D mapreduce.job.reduces=4
```

**Characteristics of Multiple Reducers:**
- **Multiple output files**: `part-r-00000`, `part-r-00001`, ...
- Parallel reduction → faster for large datasets
- Each reducer handles a **subset** of keys
- No total ordering across output files (only within each file)
- Better resource utilization

### Combiner (Optional Optimization)

A **Combiner** acts as a local mini-Reducer on the Mapper output, reducing data transferred over the network:

```
Without Combiner:
  Mapper 1 sends: (hello,1),(hello,1),(hello,1),(world,1)
  Network transfer: 4 records

With Combiner:
  Mapper 1 locally combines: (hello,3),(world,1)
  Network transfer: 2 records  ← 50% reduction!
```

```java
job.setCombinerClass(WordCountReducer.class);
// Often same class as Reducer (when operation is associative+commutative)
```

### Full Data Flow Summary

```
HDFS Input
    ↓
InputFormat → InputSplits (one per Mapper)
    ↓
RecordReader → (key, value) pairs
    ↓
map(key, value) → intermediate (k, v)
    ↓
[Combiner — optional local aggregation]
    ↓
Partitioner → assigns each (k,v) to a partition/reducer
    ↓
Mapper output sorted by key (spill to disk if needed)
    ↓
──── NETWORK SHUFFLE ────
    ↓
Reducer fetches its partition from all Mappers
    ↓
Merge-sort all fetched data (sort phase)
    ↓
reduce(key, Iterable<values>) → output (k, v)
    ↓
OutputFormat → writes to HDFS (part-r-NNNNN files)
```

---

## Question 5: Hadoop Streaming and Text Processing (12M)

### What is Hadoop Streaming?

**Hadoop Streaming** is a utility that allows users to write **MapReduce programs in any language** (Python, Ruby, Perl, Shell script, etc.) that can read from standard input (stdin) and write to standard output (stdout).

This removes the requirement to write Java code for MapReduce jobs.

```
Key Concept:
  Mapper = any program that reads lines from STDIN, writes to STDOUT
  Reducer = any program that reads sorted key-value from STDIN, writes to STDOUT
```

### How Hadoop Streaming Works

```
┌─────────────────────────────────────────────────────────────────┐
│                HADOOP STREAMING ARCHITECTURE                     │
│                                                                  │
│  HDFS Input                                                      │
│      ↓                                                           │
│  [Java InputFormat] → reads data                                 │
│      ↓                                                           │
│  [Java Streaming Mapper Wrapper]                                 │
│      │                                                           │
│      ├── Launches: python mapper.py (as subprocess)             │
│      ├── Pipes data to STDIN of mapper.py                       │
│      └── Reads key\tvalue lines from STDOUT of mapper.py        │
│                ↓                                                 │
│         Intermediate key\tvalue pairs                            │
│                ↓                                                 │
│  [Shuffle & Sort — handled by Java framework]                    │
│                ↓                                                 │
│  [Java Streaming Reducer Wrapper]                                │
│      │                                                           │
│      ├── Launches: python reducer.py (as subprocess)            │
│      ├── Pipes sorted data to STDIN of reducer.py               │
│      └── Reads output lines from STDOUT of reducer.py           │
│                ↓                                                 │
│         HDFS Output                                              │
└─────────────────────────────────────────────────────────────────┘
```

### Why Hadoop Streaming is Suited for Text Processing

Text processing is inherently **line-oriented** — which maps perfectly to the stdin/stdout model of Hadoop Streaming.

- **Natural line-by-line processing**: Text files are naturally split at line boundaries
- **No serialization overhead**: Text is already human-readable strings
- **Rich text libraries**: Python/Perl have powerful regex, NLP, tokenization libraries
- **Rapid development**: Scripts are shorter and faster to write than Java programs
- **Log analysis**: Server logs are text files processed line by line

### Text Processing Example 1: Word Count in Python

**mapper.py:**
```python
#!/usr/bin/env python
import sys

for line in sys.stdin:
    line = line.strip()
    words = line.split()
    for word in words:
        print('%s\t%s' % (word.lower(), 1))
        # Emits: word\t1  (tab-separated key-value)
```

**reducer.py:**
```python
#!/usr/bin/env python
import sys

current_word = None
current_count = 0

for line in sys.stdin:
    line = line.strip()
    word, count = line.split('\t', 1)
    count = int(count)
    
    if current_word == word:
        current_count += count
    else:
        if current_word:
            print('%s\t%s' % (current_word, current_count))
        current_word = word
        current_count = count

# Output last word
if current_word:
    print('%s\t%s' % (current_word, current_count))
```

**Running the streaming job:**
```bash
hadoop jar $HADOOP_HOME/share/hadoop/tools/lib/hadoop-streaming-*.jar \
    -input  /user/hadoop/input/text.txt \
    -output /user/hadoop/output/wordcount \
    -mapper mapper.py \
    -reducer reducer.py \
    -file mapper.py \
    -file reducer.py
```

### Text Processing Example 2: Log Analysis

**Apache log format:**
```
192.168.1.1 - - [01/Jan/2024:10:00:00] "GET /index.html HTTP/1.1" 200 1024
```

**log_mapper.py:**
```python
#!/usr/bin/env python
import sys
import re

pattern = r'(\S+) .* "(\S+) (\S+) .*" (\d+) (\d+)'

for line in sys.stdin:
    match = re.match(pattern, line)
    if match:
        ip, method, url, status, size = match.groups()
        print('%s\t%s' % (url, 1))  # Count URL visits
```

**log_reducer.py:**
```python
#!/usr/bin/env python
import sys

current_url = None
total = 0

for line in sys.stdin:
    url, count = line.strip().split('\t')
    count = int(count)
    if current_url == url:
        total += count
    else:
        if current_url:
            print('%s\t%d' % (current_url, total))
        current_url = url
        total = count

if current_url:
    print('%s\t%d' % (current_url, total))
```

### Text Processing Example 3: Sentiment Analysis

**sentiment_mapper.py:**
```python
#!/usr/bin/env python
import sys

positive = {'good','great','excellent','love','best','happy'}
negative = {'bad','terrible','hate','worst','awful','poor'}

for line in sys.stdin:
    words = line.lower().split()
    pos_count = sum(1 for w in words if w in positive)
    neg_count = sum(1 for w in words if w in negative)
    
    if pos_count > neg_count:
        print('positive\t1')
    elif neg_count > pos_count:
        print('negative\t1')
    else:
        print('neutral\t1')
```

### Key Text Processing Capabilities via Streaming

| Capability              | Tool/Technique                          |
|-------------------------|-----------------------------------------|
| Tokenization            | str.split(), NLTK, spaCy                |
| Regular Expression      | Python re module, Perl regex            |
| N-gram extraction       | Custom Python loops                     |
| Encoding handling       | Python codecs, chardet                  |
| CSV/TSV parsing         | Python csv module                       |
| JSON parsing            | Python json module                      |
| Stemming/Lemmatization  | NLTK, spaCy in mapper                   |
| Named Entity Recognition| spaCy NER in mapper                     |

### Hadoop Streaming vs. Java MapReduce

| Aspect          | Hadoop Streaming            | Java MapReduce               |
|-----------------|-----------------------------|------------------------------|
| Language        | Any (Python, Ruby, Bash)    | Java only                    |
| Development Speed | Fast                      | Slower (verbose)             |
| Performance     | Slightly slower (subprocess overhead) | Faster          |
| Type Safety     | None (text only)            | Strong typing (Writables)    |
| Libraries       | Any scripting library       | Java ecosystem               |
| Debugging       | Easy (run script locally)   | Harder (JVM debugging)       |
| Use Case        | Text processing, prototyping| Production, performance-critical |

---

## Question 6: HDFS — NameNode, DataNode, Block, and HDFS Operations (12M)

### What is HDFS?

**HDFS (Hadoop Distributed File System)** is a distributed, fault-tolerant file system designed to store very large files across a cluster of commodity machines.

**Design Goals:**
- Store files of size GB to TB (and beyond)
- High throughput access (batch processing, not low latency)
- Fault tolerance through replication
- Write-once, read-many model

### HDFS Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                    HDFS ARCHITECTURE                             │
│                                                                  │
│   CLIENT                                                         │
│   ┌─────┐                                                        │
│   │App  │──metadata ops──→ ┌──────────────────────────────┐    │
│   │     │                  │         NAMENODE              │    │
│   │     │                  │  (Master)                     │    │
│   │     │                  │  • Namespace tree             │    │
│   │     │←─block locations─│  • Block→DataNode mapping     │    │
│   │     │                  │  • EditLog (persistent)       │    │
│   └──┬──┘                  │  • FsImage (snapshot)         │    │
│      │                     └──────────────────────────────┘    │
│      │                                    ↑                     │
│      │ data                     heartbeat + block report        │
│      │ transfer                           │                     │
│      ↓                         ┌──────────┼──────────┐         │
│  ┌────────────┐         ┌──────┴──┐ ┌─────┴──┐ ┌─────┴──┐     │
│  │ DataNode 1 │         │DataNode2│ │DataNode3│ │DataNode4│    │
│  │ Block A    │         │Block A  │ │Block B  │ │Block B  │    │
│  │ Block B    │         │Block C  │ │Block C  │ │Block A  │    │
│  │ Block C    │         │         │ │         │ │         │    │
│  └────────────┘         └─────────┘ └─────────┘ └─────────┘   │
│   (Replication factor = 3: each block on 3 different DataNodes) │
└─────────────────────────────────────────────────────────────────┘
```

### NameNode

The **NameNode** is the **master node** of HDFS. It manages the file system namespace and controls access to files.

**Responsibilities:**
- Maintains the **namespace tree** (directory and file hierarchy)
- Records **block locations**: which blocks make up each file
- Manages **replication** of blocks
- Processes **namespace operations**: open, close, rename
- Receives regular **heartbeats** from DataNodes
- Receives **block reports** listing all blocks on each DataNode

**Persistent Storage:**
```
FsImage (snapshot of namespace at a point in time)
    +
EditLog (log of all changes since last snapshot)
    =
Current namespace state

At startup: NameNode loads FsImage + replays EditLog
```

**Secondary NameNode** (not a hot standby):
- Periodically merges FsImage + EditLog → new FsImage
- Reduces NameNode restart time
- Saves merged checkpoint to its own disk + sends to NameNode

**NameNode HA (High Availability):**
```
Active NameNode ↔ [Shared EditLog via JournalNodes] ↔ Standby NameNode
                          ↑
                    ZooKeeper (leader election)
```

### DataNode

**DataNodes** are the **slave/worker nodes** that store actual data blocks.

**Responsibilities:**
- Store data blocks on local disk
- Serve read/write requests from clients
- Send **heartbeat** to NameNode every 3 seconds (alive signal)
- Send **block report** to NameNode every hour (list of blocks stored)
- Replicate blocks on NameNode instruction

**DataNode Storage:**
- Each block stored as a file in the local filesystem (ext4, xfs)
- Also stores block **metadata** (checksum for integrity verification)

### Block

A **block** is the fundamental unit of storage in HDFS.

**Default block size**: 128 MB (was 64 MB in older Hadoop versions)

**Why large blocks?**
- Reduces number of metadata entries in NameNode
- Minimizes seek overhead (sequential reads dominate)
- Reduces overhead of reading many small files

```
Example: 512 MB file with 128 MB block size

File: data.csv (512 MB)
    Block 0: 128 MB  → replicated on DN1, DN2, DN4
    Block 1: 128 MB  → replicated on DN2, DN3, DN5
    Block 2: 128 MB  → replicated on DN3, DN4, DN1
    Block 3: 128 MB  → replicated on DN4, DN5, DN2
```

**Block Replication:**
- Default replication factor = **3**
- Placement policy: 1 replica on writer's node, 1 on different rack, 1 on same rack as 2nd
- Ensures survival of rack failure

### HDFS Operations in Detail

#### 1. File Write (Client → HDFS)

```
Step 1: Client calls DistributedFileSystem.create()

Step 2: DistributedFileSystem RPC → NameNode
        NameNode creates new file entry in namespace
        Responds with DFSOutputStream object

Step 3: Client starts writing data to DFSOutputStream
        Data split into packets (64KB each)
        Packets placed in data queue

Step 4: NameNode allocates block + picks 3 DataNodes (pipeline)

Step 5: DataStreamer sends packets through pipeline:
        Client → DN1 → DN2 → DN3
        (each DN forwards to next, stores locally)

Step 6: Each DN sends ack back through pipeline:
        DN3 → DN2 → DN1 → Client

Step 7: When block full, client requests next block
        NameNode allocates new block + new DataNode pipeline

Step 8: Client calls close()
        Remaining data flushed
        NameNode told file is complete

Write Pipeline:
Client ──packet──→ [DN1] ──packet──→ [DN2] ──packet──→ [DN3]
Client ←──ack──── [DN1] ←──ack──── [DN2] ←──ack──── [DN3]
```

#### 2. File Read (HDFS → Client)

```
Step 1: Client calls DistributedFileSystem.open()

Step 2: DistributedFileSystem RPC → NameNode
        NameNode returns block locations (sorted by proximity)

Step 3: Client opens FSDataInputStream

Step 4: Client calls read()
        FSDataInputStream connects to closest DataNode for Block 0

Step 5: Data streamed directly from DataNode to client
        (no data flows through NameNode)

Step 6: When block 0 complete, close connection to DN
        Open connection to DataNode for Block 1

Step 7: Repeat until all blocks read

Step 8: Client calls close()

Read Path (data locality):
If client is on same node as DataNode:
    Read from local disk (fastest!)
Else if on same rack:
    Read from same-rack DataNode
Else:
    Read from any DataNode
```

#### 3. HDFS CLI Operations

```bash
# Create directory
hadoop fs -mkdir /user/hadoop/data

# Upload file
hadoop fs -put localfile.txt /user/hadoop/data/

# Download file
hadoop fs -get /user/hadoop/data/file.txt ./

# List files
hadoop fs -ls /user/hadoop/data/

# View file content
hadoop fs -cat /user/hadoop/data/file.txt

# Delete file
hadoop fs -rm /user/hadoop/data/file.txt

# Delete directory recursively
hadoop fs -rm -r /user/hadoop/data/

# Move/rename
hadoop fs -mv /user/old/path /user/new/path

# Check disk usage
hadoop fs -du -h /user/hadoop/

# Check replication
hadoop fsck /user/hadoop/data/ -files -blocks -locations

# Change replication factor
hadoop fs -setrep -w 3 /user/hadoop/data/file.txt
```

---

## Question 7: Developing a MapReduce Application (12M)

### Development Workflow Overview

```
Requirements
     ↓
Design (Mapper logic, Reducer logic, I/O formats)
     ↓
Code (Java: Mapper, Reducer, Driver)
     ↓
Unit Test (MRUnit framework)
     ↓
Local Test (Local mode, small data)
     ↓
Cluster Test (Pseudo-distributed, then full cluster)
     ↓
Production Deployment
```

### Complete MapReduce Application: Temperature Analysis

**Problem**: Find maximum temperature for each year from weather data.

**Input format:**
```
0067011990999991950051507+0000+  # Year 1950, temp +0
0043011990999991950051512+0022+  # Year 1950, temp +22
0043012650999991949032412+0111+  # Year 1949, temp +111
```

#### Step 1: Write the Mapper

```java
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper
    extends Mapper<LongWritable, Text, Text, IntWritable> {
    
    // Regex to detect missing/invalid temperature
    private static final int MISSING = 9999;
    
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        
        String line = value.toString();
        
        // Extract year (chars 15-19)
        String year = line.substring(15, 19);
        
        // Extract temperature (chars 87-92)
        int airTemperature;
        if (line.charAt(87) == '+') {
            airTemperature = Integer.parseInt(line.substring(88, 92));
        } else {
            airTemperature = Integer.parseInt(line.substring(87, 92));
        }
        
        // Extract quality flag (char 92)
        String quality = line.substring(92, 93);
        
        // Emit only valid temperatures
        if (airTemperature != MISSING && quality.matches("[01459]")) {
            context.write(new Text(year), new IntWritable(airTemperature));
        }
    }
}
```

#### Step 2: Write the Reducer

```java
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer
    extends Reducer<Text, IntWritable, Text, IntWritable> {
    
    @Override
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context)
            throws IOException, InterruptedException {
        
        int maxValue = Integer.MIN_VALUE;
        
        // Find maximum temperature for this year
        for (IntWritable value : values) {
            maxValue = Math.max(maxValue, value.get());
        }
        
        context.write(key, new IntWritable(maxValue));
    }
}
```

#### Step 3: Write the Driver (Job Configuration)

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTemperature {
    
    public static void main(String[] args) throws Exception {
        
        if (args.length != 2) {
            System.err.println("Usage: MaxTemperature <input path> <output path>");
            System.exit(-1);
        }
        
        // Create job with configuration
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Max Temperature");
        
        // Set JAR class (for cluster deployment)
        job.setJarByClass(MaxTemperature.class);
        
        // Set Mapper and Reducer classes
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);
        
        // Set Combiner (optional — same as reducer here)
        job.setCombinerClass(MaxTemperatureReducer.class);
        
        // Set output key/value types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        // Set number of reducers
        job.setNumReduceTasks(1);
        
        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        // Submit job and wait for completion
        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);
    }
}
```

#### Step 4: Build and Package

```bash
# Compile
javac -classpath $(hadoop classpath) \
      -d classes \
      MaxTemperatureMapper.java MaxTemperatureReducer.java MaxTemperature.java

# Package into JAR
jar -cvf maxtemp.jar -C classes .
```

#### Step 5: Test Locally

```bash
# Set up input
hadoop fs -mkdir -p /weather/input
hadoop fs -put weather_data.txt /weather/input/

# Run job
hadoop jar maxtemp.jar MaxTemperature \
    /weather/input \
    /weather/output

# Check results
hadoop fs -cat /weather/output/part-r-00000
```

**Expected output:**
```
1949    111
1950    22
```

### Unit Testing with MRUnit

```java
import org.apache.hadoop.mrunit.mapreduce.*;

public class MaxTemperatureMapperTest {
    
    @Test
    public void processesValidRecord() throws Exception {
        new MapDriver<LongWritable, Text, Text, IntWritable>()
            .withMapper(new MaxTemperatureMapper())
            .withInput(new LongWritable(0),
                new Text("0043012650999991949032412+0111+"))
            .withOutput(new Text("1949"), new IntWritable(111))
            .runTest();
    }
}
```

### Configuration Tuning

```java
// Custom configuration
Configuration conf = new Configuration();

// Memory settings
conf.set("mapreduce.map.memory.mb", "2048");
conf.set("mapreduce.reduce.memory.mb", "4096");

// JVM heap settings
conf.set("mapreduce.map.java.opts", "-Xmx1536m");

// Compression (for intermediate data)
conf.setBoolean("mapreduce.map.output.compress", true);
conf.set("mapreduce.map.output.compress.codec",
         "org.apache.hadoop.io.compress.SnappyCodec");
```

---

## Question 8: MapReduce Job with Classic Java Stream (12M)

### Classic MapReduce Job Execution in Java

The classic (old) API was in `org.apache.hadoop.mapred` package. The new API (since Hadoop 0.20) is in `org.apache.hadoop.mapreduce`. Here we cover how jobs work under the hood.

### Internal Job Execution Flow

```
┌─────────────────────────────────────────────────────────────────┐
│           CLASSIC JAVA MAPREDUCE JOB EXECUTION                   │
│                                                                  │
│  1. Job Submission                                               │
│     Client program calls job.waitForCompletion(true)            │
│          ↓                                                       │
│  2. Job Client Initialization                                    │
│     • Requests new job ID from ResourceManager                   │
│     • Calculates InputSplits locally                             │
│     • Copies job JAR + config + split info to HDFS              │
│          ↓                                                       │
│  3. Job Submission to ResourceManager                            │
│     • submitJob() called on ResourceManager RPC                  │
│          ↓                                                       │
│  4. Job Initialization (ResourceManager side)                    │
│     • RM schedules Application Master container                  │
│     • AM process started on a NodeManager                        │
│          ↓                                                       │
│  5. Task Assignment (Application Master)                         │
│     • AM creates one map task per InputSplit                     │
│     • AM requests containers from RM for tasks                   │
│     • RM assigns containers on NodeManagers                      │
│          ↓                                                       │
│  6. Task Execution (NodeManager)                                 │
│     • NM launches YarnChild JVM process                          │
│     • YarnChild runs Mapper or Reducer task                      │
│          ↓                                                       │
│  7. Progress Reporting                                           │
│     • Tasks report progress to AM every 3 seconds               │
│     • AM reports to RM                                           │
│     • Client polls AM for status                                 │
│          ↓                                                       │
│  8. Job Completion                                               │
│     • AM tells RM job is complete                                │
│     • AM + task containers cleaned up                            │
│     • Job history written to HDFS                                │
└─────────────────────────────────────────────────────────────────┘
```

### Java Execution with Streams (Map Phase Detail)

```
YarnChild JVM starts
      ↓
Localize job JAR from HDFS (distributed cache)
      ↓
Create MapTask object
      ↓
MapTask initializes:
  • InputFormat → creates RecordReader
  • Opens InputSplit (byte range of HDFS file)
      ↓
MapTask calls Mapper.run():
  • setup(context)
  • while (context.nextKeyValue()):
        map(context.getCurrentKey(),
            context.getCurrentValue(),
            context)
  • cleanup(context)
      ↓
Mapper output → MapOutputBuffer (circular byte buffer in RAM)
  Default size: 100 MB (mapreduce.task.io.sort.mb)
      ↓
When buffer reaches 80% full → SPILL to disk:
  1. Sort buffer contents by key (using quicksort)
  2. Apply Combiner if configured
  3. Write sorted spill file to local disk
  4. Optionally compress spill file
      ↓
After all input processed → Merge all spill files:
  (merge-sort producing single sorted output per partition)
      ↓
Output: sorted, partitioned files on local disk
        (one file per Reducer partition)
```

### Shuffle Phase (Network Transfer)

```
Map Phase Complete on all Nodes
          ↓
Reducer fetches its partition from EVERY Mapper node:
  Reducer 0 fetches partition-0 from Mapper1, Mapper2, ...Mapperₙ
  (HTTP GET requests to each Mapper's shuffle handler)
          ↓
Data arrives at Reducer node incrementally
  Small partitions merged in memory
  Large partitions spilled to disk
          ↓
Final merge-sort of all fetched data
  → single sorted stream of (key, [values]) groups
          ↓
Reduce phase begins (reduce() called per key group)
```

### Writable Types for Java Streaming

Hadoop uses **Writable** interface (not Java Serializable) for efficiency:

```java
// Hadoop Writable types:
IntWritable    ← int
LongWritable   ← long
FloatWritable  ← float
DoubleWritable ← double
Text           ← String
BooleanWritable← boolean
BytesWritable  ← byte[]
NullWritable   ← null (no value — use as key or value placeholder)
ArrayWritable  ← array of Writables
MapWritable    ← map of Writables

// Custom Writable:
public class TemperaturePair implements WritableComparable<TemperaturePair> {
    private Text stationId = new Text();
    private IntWritable temperature = new IntWritable();
    
    public void write(DataOutput out) throws IOException {
        stationId.write(out);
        temperature.write(out);
    }
    
    public void readFields(DataInput in) throws IOException {
        stationId.readFields(in);
        temperature.readFields(in);
    }
    
    public int compareTo(TemperaturePair other) {
        // Define sort order
        int cmp = stationId.compareTo(other.stationId);
        if (cmp != 0) return cmp;
        return -temperature.compareTo(other.temperature); // Descending temp
    }
}
```

### Job Counters

Counters track metrics during job execution:

```java
// Enum-based custom counter
enum Temperature {
    MISSING,
    MALFORMED
}

// In Mapper:
if (airTemperature == MISSING) {
    context.getCounter(Temperature.MISSING).increment(1);
    return;
}

// View counters via CLI:
// mapreduce.job.counters in job output
```

### Reading Job Output Programmatically

```java
// After job completes, read output from HDFS
Configuration conf = new Configuration();
FileSystem fs = FileSystem.get(conf);
Path outputPath = new Path("/output/part-r-00000");

try (SequenceFile.Reader reader = new SequenceFile.Reader(fs,
        outputPath, conf)) {
    Text key = new Text();
    IntWritable value = new IntWritable();
    while (reader.next(key, value)) {
        System.out.println(key + "\t" + value);
    }
}
```

---

## Question 9: MapReduce Jobs on YARN (12M)

### What is YARN?

**YARN (Yet Another Resource Negotiator)** was introduced in Hadoop 2.x to separate resource management from the MapReduce programming model.

**Problems with MRv1 (classic):**
- JobTracker did both resource management AND job scheduling (single point of failure + bottleneck)
- Only MapReduce could run on the cluster
- Max ~4000 nodes per cluster

**YARN solution:** Separate concerns:
```
YARN = Resource Management (generic)
MR ApplicationMaster = Job Scheduling (MapReduce-specific)
```

### YARN Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                     YARN ARCHITECTURE                            │
│                                                                  │
│  ┌──────────────────────────────────────────────────────────┐   │
│  │              RESOURCE MANAGER (Master)                    │   │
│  │                                                           │   │
│  │  ┌───────────────────┐  ┌──────────────────────────────┐ │   │
│  │  │   Scheduler       │  │   Applications Manager        │ │   │
│  │  │ (allocates        │  │ (accepts submissions,         │ │   │
│  │  │  resources)       │  │  manages AMs)                 │ │   │
│  │  └───────────────────┘  └──────────────────────────────┘ │   │
│  └──────────────────────────────────────────────────────────┘   │
│                        ↑ heartbeat + resource requests           │
│            ┌───────────┼───────────────┐                        │
│            ↓           ↓               ↓                        │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐           │
│  │ NodeManager 1│ │ NodeManager 2│ │ NodeManager 3│           │
│  │              │ │              │ │              │           │
│  │ ┌──────────┐ │ │ ┌──────────┐ │ │ ┌──────────┐ │           │
│  │ │Container │ │ │ │    AM    │ │ │ │Container │ │           │
│  │ │ (Task)   │ │ │ │(App Mast)│ │ │ │ (Task)   │ │           │
│  │ └──────────┘ │ │ └──────────┘ │ │ └──────────┘ │           │
│  │ ┌──────────┐ │ │ ┌──────────┐ │ │              │           │
│  │ │Container │ │ │ │Container │ │ │              │           │
│  │ │ (Task)   │ │ │ │ (Task)   │ │ │              │           │
│  │ └──────────┘ │ │ └──────────┘ │ │              │           │
│  └──────────────┘ └──────────────┘ └──────────────┘           │
└─────────────────────────────────────────────────────────────────┘
```

### YARN Components

#### 1. ResourceManager (RM)
- **Global master** for the entire cluster
- Two components:
  - **Scheduler**: Allocates resources (containers) to applications. Pluggable: FIFO, Capacity, Fair Scheduler
  - **Applications Manager**: Manages Application Masters (accepts job submissions, tracks AM health)

#### 2. NodeManager (NM)
- **Per-node agent** on every worker machine
- Manages resources on that node (CPU, memory)
- Launches and monitors **containers**
- Reports node health to ResourceManager
- Handles **container log aggregation**

#### 3. Application Master (AM)
- **Per-job process** launched by ResourceManager
- Negotiates resources (containers) from ResourceManager
- Works with NodeManagers to launch/monitor tasks
- Handles task failures and reschedules
- MapReduce AM: `MRAppMaster`

#### 4. Container
- **Unit of resource allocation** in YARN
- Combines: CPU cores + Memory + (optionally disk/network)
- Each Map/Reduce task runs in one container
- Container spec defined in `ContainerLaunchContext`

### MapReduce Job Execution on YARN: Step-by-Step

```
Step 1: Job Submission
─────────────────────
Client → Job.waitForCompletion()
       → Requests new Application ID from RM
       → Computes InputSplits
       → Copies job JAR, config XML, split info to HDFS:
           hdfs:///tmp/hadoop-staging/{user}/{appId}/

Step 2: Application Submission
──────────────────────────────
Client → submitApplication(appId, appContext) → ResourceManager
RM validates request and adds to scheduler queue

Step 3: AM Container Allocation
────────────────────────────────
RM Scheduler allocates container for AM on some NodeManager
RM tells NM to launch AM container:
  Command: java MRAppMaster -Xmx1024m ...

Step 4: AM Initialization
──────────────────────────
MRAppMaster starts on NM₂
AM reads job splits from HDFS
AM creates one MapTask per split + configured number of ReduceTasks
AM calls registerApplicationMaster() → registers with RM

Step 5: Resource Requests
──────────────────────────
AM sends resource requests to RM:
  "I need N map containers (2GB RAM, 1 vCore each)
   near nodes [DN1, DN2, DN3] for data locality"
RM Scheduler responds with container allocations
  (respecting data locality: same node > same rack > any node)

Step 6: Task Launch
────────────────────
AM calls startContainer() on NodeManagers for each container
NM launches YarnChild JVM in container:
  java -Xmx1536m YarnChild -mapreduce.job.id job_xxx -task.id task_xxx

YarnChild downloads job JAR from HDFS (distributed cache)
YarnChild runs MapTask (or ReduceTask)

Step 7: Progress and Heartbeat
───────────────────────────────
Tasks → AM: heartbeat every 3 sec (progress %, status)
AM → RM: heartbeat every 1 sec (resource requests, health)
Client polls AM for status updates

Step 8: Shuffle
────────────────
Reducers fetch map outputs over HTTP
(Map output shuffle handlers run in NodeManager)

Step 9: Task Completion
────────────────────────
Task completes → notifies AM
AM tracks completion of all tasks

Step 10: Job Completion
────────────────────────
All tasks complete → AM calls finishApplicationMaster(SUCCESS)
RM marks application FINISHED
AM container released
Job history written to HDFS by History Server
Client job.waitForCompletion() returns true
```

### YARN Schedulers

#### 1. FIFO Scheduler
- Simple queue, first-in-first-out
- Large jobs block small jobs
- Not suitable for shared production clusters

#### 2. Capacity Scheduler (default in Hadoop)
- Multiple queues with guaranteed capacity
- Each queue has % of cluster resources
- Jobs within a queue share queue's capacity
- Prevents one team from hogging cluster

```
Cluster Capacity: 100 units
  Engineering Queue: 60%  → up to 60 units
  Marketing Queue:   40%  → up to 40 units
```

#### 3. Fair Scheduler (default in CDH)
- Jobs get equal share of resources over time
- Short jobs finish fast even if large jobs are running
- Weighted fair sharing possible

### YARN vs. MRv1 Comparison

| Aspect               | MRv1 (Classic)           | YARN                          |
|----------------------|--------------------------|-------------------------------|
| Resource Management  | JobTracker               | ResourceManager               |
| Job Scheduling       | JobTracker               | Application Master (per job)  |
| Task Execution       | TaskTracker              | NodeManager + Container       |
| Scalability          | ~4,000 nodes             | ~10,000+ nodes                |
| Multi-tenancy        | MapReduce only           | Any framework (Spark, Tez, MR)|
| Fault Tolerance      | JobTracker SPOF          | RM HA with ZooKeeper          |

---

## Question 10: Types of MapReduce and Its Formats (12M)

### Types of MapReduce Jobs

#### 1. Map-Only Jobs (No Reducer)
When only transformation is needed (no aggregation):

```java
job.setNumReduceTasks(0);  // No reducer
```

```
Use cases:
  - File format conversion (Text → SequenceFile)
  - Data filtering
  - ETL transformations
  - Record validation

Flow: Input → [Mapper] → Output (directly to HDFS)
```

#### 2. Reduce-Only Jobs (Identity Mapper)
Use default IdentityMapper, only custom Reducer:

```java
job.setMapperClass(Mapper.class);  // Identity mapper (pass-through)
```

#### 3. Chain MapReduce (Chained Jobs)
Multiple MapReduce jobs chained sequentially:

```
Job 1: Preprocessing
  Input → [Map1] → [Reduce1] → Intermediate HDFS

Job 2: Analysis  
  Intermediate HDFS → [Map2] → [Reduce2] → Final Output
```

Using JobControl:
```java
JobControl control = new JobControl("pipeline");
ControlledJob job1 = new ControlledJob(conf1);
ControlledJob job2 = new ControlledJob(conf2);
job2.addDependingJob(job1);  // job2 runs after job1
control.addJob(job1);
control.addJob(job2);
control.run();
```

#### 4. Iterative MapReduce
For algorithms requiring multiple passes (k-means, PageRank):

```
Iteration 1: Input → MR → Output₁
Iteration 2: Output₁ → MR → Output₂
...
Iteration n: Outputₙ₋₁ → MR → Final Output

(Convergence check between iterations)
```

#### 5. In-Mapper Combining
Use instance variables in Mapper to aggregate locally before emitting:

```java
// More efficient than using a separate Combiner
public class InMapperCombiningMapper 
    extends Mapper<LongWritable, Text, Text, IntWritable> {
    
    Map<String, Integer> counts = new HashMap<>();
    
    public void map(LongWritable key, Text value, Context context) {
        for (String word : value.toString().split("\\s+")) {
            counts.merge(word, 1, Integer::sum);
        }
    }
    
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            context.write(new Text(e.getKey()), new IntWritable(e.getValue()));
        }
    }
}
```

### MapReduce Input Formats

InputFormats define how input data is split and read.

#### 1. TextInputFormat (Default)
- Each line = one record
- Key: `LongWritable` (byte offset of line start)
- Value: `Text` (content of line)
- Splits at line boundaries

```java
job.setInputFormatClass(TextInputFormat.class);  // default
```

#### 2. KeyValueTextInputFormat
- Each line is `key\tvalue` (tab-separated)
- Key: `Text` (before first tab)
- Value: `Text` (after first tab)

```java
job.setInputFormatClass(KeyValueTextInputFormat.class);
conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", "\t");
```

#### 3. SequenceFileInputFormat
- Binary key-value format (Hadoop-specific)
- Supports compression (BLOCK or RECORD level)
- Faster than text for inter-job communication
- Preserves type information

```java
job.setInputFormatClass(SequenceFileInputFormat.class);
```

#### 4. NLineInputFormat
- Each Mapper gets exactly N lines (instead of one split)
- Useful for API calls or tasks needing controlled input size

```java
job.setInputFormatClass(NLineInputFormat.class);
NLineInputFormat.setNumLinesPerSplit(job, 100);
```

#### 5. CombineFileInputFormat
- Packs multiple small files into one split
- Reduces Mapper overhead for many small files (Small File Problem)

```java
job.setInputFormatClass(CombineTextInputFormat.class);
CombineTextInputFormat.setMaxInputSplitSize(job, 67108864); // 64 MB
```

#### 6. DBInputFormat
- Reads from relational databases (via JDBC)
- Splits query results across multiple Mappers

```java
DBInputFormat.setInput(job, MyRecord.class,
    "SELECT id, name FROM employees WHERE dept='engineering'",
    "SELECT COUNT(*) FROM employees WHERE dept='engineering'");
```

#### 7. MultipleInputs
- Different input paths with different InputFormats/Mappers
- Useful for joining datasets from different formats

```java
MultipleInputs.addInputPath(job, weatherPath,
    TextInputFormat.class, WeatherMapper.class);
MultipleInputs.addInputPath(job, stationPath,
    TextInputFormat.class, StationMapper.class);
```

### MapReduce Output Formats

#### 1. TextOutputFormat (Default)
- Writes `key\tvalue\n` per record
- Key and Value converted to string via toString()

```java
job.setOutputFormatClass(TextOutputFormat.class);  // default
```

#### 2. SequenceFileOutputFormat
- Binary format for Hadoop inter-job chaining
- Preserves types, supports compression

```java
job.setOutputFormatClass(SequenceFileOutputFormat.class);
SequenceFileOutputFormat.setCompressOutput(job, true);
SequenceFileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
```

#### 3. MapFileOutputFormat
- Writes sorted MapFile (index + data)
- Supports random lookup by key

#### 4. MultipleOutputs
- Write to different output files based on logic in Reducer:

```java
// In setup:
MultipleOutputs<Text, IntWritable> mos = 
    new MultipleOutputs<>(context);

// In reduce:
if (key.toString().startsWith("2024")) {
    mos.write("year2024", key, value, "year2024/part");
} else {
    mos.write("other", key, value, "other/part");
}
```

#### 5. LazyOutputFormat
- Wrapper that prevents creating empty output files when a Reducer has no output

```java
LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
```

#### 6. DBOutputFormat
- Writes output to a relational database via JDBC

### Compression Codecs

| Codec      | Extension | Splittable | Speed    | Ratio  | Use Case                 |
|------------|-----------|------------|----------|--------|--------------------------|
| GzipCodec  | .gz       | No         | Medium   | High   | Final output, cold data  |
| BZip2Codec | .bz2      | Yes        | Slow     | Highest| Archive data             |
| LzoCodec   | .lzo      | Yes*       | Fast     | Medium | Intermediate data        |
| SnappyCodec| .snappy   | No         | Fastest  | Medium | Map output (speed priority)|
| Lz4Codec   | .lz4      | No         | Very fast| Medium | Performance-critical     |

**Key rule**: Splittable codecs are preferred for input data (each split can be processed independently).

### Summary Table: InputFormats vs OutputFormats

| Format                    | Type   | Key          | Value  | Best For                    |
|---------------------------|--------|--------------|--------|-----------------------------|
| TextInputFormat           | Input  | LongWritable | Text   | Log files, text data        |
| KeyValueTextInputFormat   | Input  | Text         | Text   | Pre-keyed text files        |
| SequenceFileInputFormat   | Input  | Any Writable | Any    | Inter-job data, binary data |
| NLineInputFormat          | Input  | LongWritable | Text   | Fixed-line batch processing |
| CombineFileInputFormat    | Input  | LongWritable | Text   | Many small files            |
| TextOutputFormat          | Output | Any          | Any    | Human-readable output       |
| SequenceFileOutputFormat  | Output | Any Writable | Any    | Job chaining                |
| MultipleOutputs           | Output | Any          | Any    | Multiple output files       |

---

## Quick Reference Summary

| # | Topic | Key Takeaway |
|---|-------|--------------|
| 1a | Hadoop + Components | HDFS + MapReduce + YARN + Hadoop Common; commodity hardware cluster |
| 1b | Data Analysis in Hadoop | Ingest → HDFS → MapReduce/Hive/Pig/Spark → Results |
| 2a | Mapper Class | Processes (k,v) input → emits intermediate (k,v); data locality |
| 2b | Reducer Class | Aggregates (key, [values]) → output (k,v); preceded by Shuffle+Sort |
| 2c | Scaling Out | Add commodity nodes; near-linear throughput scaling |
| 3  | Failures in MR | Task/AM/NodeManager/RM/DataNode failures; all handled automatically |
| 4  | MR Data Flow | Map→Combiner→Partitioner→Shuffle+Sort→Reduce; single vs multi reducer |
| 5  | Hadoop Streaming | Any language via stdin/stdout; ideal for text processing with Python |
| 6  | HDFS | NameNode (metadata master) + DataNode (block storage) + 128MB blocks, replication=3 |
| 7  | Developing MR App | Mapper + Reducer + Driver; compile → JAR → submit → monitor |
| 8  | Classic Java Streams | Writable types, MapOutputBuffer, spill+sort, shuffle phases |
| 9  | MR on YARN | RM + AM + NM + Containers; FIFO/Capacity/Fair schedulers |
| 10 | MR Types & Formats | Map-only/Chain/Iterative; TextInputFormat, SequenceFile, Compression |

---

*End of Notes — History of Hadoop*
