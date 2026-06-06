# Introduction to Stream Concepts — Complete Study Notes

---

## Question 1a: What is a Data Stream? (2M)

A **data stream** is a continuous, unbounded, ordered sequence of data items that arrive over time at high speed and cannot be stored entirely in memory for repeated processing.

### Key Characteristics:
- **Unbounded**: No fixed start or end
- **High Velocity**: Arrives at high speed, sometimes millions of records per second
- **Ordered**: Items arrive in temporal sequence
- **One-pass Processing**: Typically processed once as it arrives (no revisiting)
- **Volatile**: Data may be discarded after processing

### Examples:
- Network traffic logs
- Social media feeds (Twitter, Facebook)
- Stock market tick data
- IoT sensor readings
- GPS location updates
- Click-stream data from websites

```
Data Stream Model:

Time →  t₁   t₂   t₃   t₄   t₅   t₆  ...  ∞
        [x₁] [x₂] [x₃] [x₄] [x₅] [x₆] ──────→ Infinite Stream

         ↓
   [Stream Processor]  ← Limited Memory (cannot store all)
         ↓
   [Query / Summary / Alert]
```

---

## Question 1b: 14 Insights of InfoSphere in Data Streams (10M)

IBM's **InfoSphere Streams** is a platform for real-time streaming data processing. The 14 key insights are:

### 1. Continuous Query Processing
Unlike databases (which answer one-time queries), InfoSphere supports **standing queries** — queries that run continuously and update results as new data arrives.

### 2. Low Latency Processing
InfoSphere processes data in **microseconds to milliseconds**, enabling near-instantaneous analytics. This is critical for fraud detection, algorithmic trading, and real-time monitoring.

### 3. High Throughput
InfoSphere handles **millions of events per second** by leveraging parallel and distributed processing, far exceeding what traditional databases can handle.

### 4. Data in Motion vs. Data at Rest
Traditional analytics works on **data at rest** (stored databases). InfoSphere works on **data in motion** — analyzing data as it flows, before storing it.

### 5. Operator Graph Model
Processing logic is represented as a **directed acyclic graph (DAG)** of operators (sources, processing nodes, sinks). Each operator performs a specific function on the stream.

```
  Source → Filter → Transform → Aggregate → Sink
    ↓         ↓         ↓           ↓         ↓
  Stream    Remove   Enrich      Window    Output
  Input     Noise    Data        Stats     Result
```

### 6. Windowing
InfoSphere supports **window operations** to process bounded subsets of the stream:
- **Tumbling Window**: Non-overlapping fixed-size windows
- **Sliding Window**: Windows that move forward with overlap
- **Session Window**: Windows defined by activity gaps

### 7. Adaptive Processing
The system **dynamically adapts** to changing data rates, automatically scaling resources up or down to maintain performance under variable loads.

### 8. Fault Tolerance and Reliability
InfoSphere provides **checkpointing and recovery** mechanisms. If a processing node fails, the system recovers from the last checkpoint without losing data.

### 9. Integration with Existing Systems
InfoSphere integrates with **databases, Hadoop, message queues (Kafka), and enterprise systems** (CRM, ERP) to create hybrid real-time + historical analytics pipelines.

### 10. Parallel and Distributed Processing
Stream processing is **parallelized** across multiple cores and nodes, allowing horizontal scaling for very high data rates.

### 11. Complex Event Processing (CEP)
InfoSphere can detect **patterns across multiple events over time** — for example, detecting a fraudulent transaction pattern across 5 events within 2 minutes.

### 12. Data Fusion
Combines data from **multiple heterogeneous streams** (sensors, logs, transactions) in real time to produce enriched, correlated insights.

### 13. Visualization and Alerting
Provides **real-time dashboards, alerts, and notifications** when specific conditions are met in the stream (threshold crossing, anomaly detection).

### 14. Domain-Specific Toolkits
InfoSphere offers pre-built toolkits for specific domains:
- **Finance Toolkit**: Market data processing
- **Geospatial Toolkit**: GPS and location analytics
- **Text Analytics Toolkit**: NLP on streaming text
- **Mining Toolkit**: Online machine learning

### Summary Table

| Insight # | Feature                     | Benefit                                  |
|-----------|-----------------------------|------------------------------------------|
| 1         | Continuous Query            | Always-on analytics                      |
| 2         | Low Latency                 | Real-time decisions                      |
| 3         | High Throughput             | Millions of events/second                |
| 4         | Data in Motion              | Process before storage                   |
| 5         | Operator Graph              | Modular, reusable processing             |
| 6         | Windowing                   | Time-bounded aggregation                 |
| 7         | Adaptive Processing         | Handles variable load                    |
| 8         | Fault Tolerance             | No data loss on failure                  |
| 9         | Integration                 | Hybrid real-time + historical            |
| 10        | Parallel Processing         | Horizontal scaling                       |
| 11        | Complex Event Processing    | Pattern detection across events          |
| 12        | Data Fusion                 | Multi-source correlation                 |
| 13        | Visualization & Alerting    | Actionable real-time insights            |
| 14        | Domain Toolkits             | Industry-specific solutions              |

---

## Question 2: Applications of Data Streams in Detail (12M)

Data streams power some of the most critical real-time systems in the modern world. Key application domains:

### 1. Financial Services and Stock Markets
- Real-time stock price feeds and order book processing
- **Algorithmic trading**: Buy/sell decisions in microseconds based on streaming market data
- **Fraud detection**: Detect unusual transaction patterns in real time (e.g., multiple ATM withdrawals from different cities)
- Credit card transaction monitoring

```
Transaction Stream
     ↓
[Pattern Detector] → Normal → Allow
     ↓
  Anomaly → Block + Alert Customer
```

### 2. Network Monitoring and Cybersecurity
- **Intrusion Detection Systems (IDS)**: Analyze network packet streams for suspicious patterns (DDoS attacks, port scans)
- Monitor bandwidth usage in real time
- Detect zero-day attacks from traffic anomalies
- Log aggregation and SIEM (Security Information and Event Management)

### 3. Social Media Analytics
- Twitter/Facebook stream analysis for **trending topics, sentiment analysis**
- Real-time hashtag tracking and viral content detection
- Brand monitoring: detecting mentions and sentiment about products
- Event detection (e.g., earthquake detected from Twitter before seismographs)

### 4. Internet of Things (IoT) and Sensor Networks
- Smart home devices streaming temperature, motion, power consumption data
- Industrial IoT: factory sensor streams for **predictive maintenance**
- Smart city: traffic sensors, pollution monitors, energy grids
- Healthcare: continuous ECG, blood pressure, glucose monitoring streams

```
IoT Device Streams:
  Sensor₁ (Temp) ──┐
  Sensor₂ (Vibr) ──┤→ [Stream Processor] → Anomaly Alert / Dashboard
  Sensor₃ (Press)──┘
```

### 5. Telecommunications
- Call Detail Record (CDR) stream analysis
- Real-time **network congestion detection** and rerouting
- Detecting SIM card fraud by analyzing call patterns in real time
- 5G network slice management based on demand streams

### 6. E-Commerce and Web Analytics
- Real-time clickstream analysis to personalize user experience
- **Dynamic pricing** based on demand and competitor pricing streams
- Shopping cart abandonment detection for instant re-targeting
- A/B test result streaming for rapid decisions

### 7. Healthcare and Medical Monitoring
- ICU patient monitoring: continuous vital sign streams
- Early warning systems for deteriorating patients
- Epidemic outbreak detection from hospital admission streams
- Wearable device data streams (Apple Watch, Fitbit)

### 8. Transportation and Logistics
- GPS tracking streams for real-time fleet management
- **Uber/Ola**: matching drivers and riders based on location streams
- Traffic signal optimization using vehicle density streams
- Airline flight tracking and delay prediction

### 9. Energy and Smart Grids
- Real-time electricity consumption monitoring
- Load balancing across the grid based on demand streams
- Detecting power theft or meter tampering
- Integrating renewable energy (solar/wind) into the grid dynamically

### 10. Scientific and Environmental Monitoring
- **Climate monitoring**: temperature, CO₂, sea level sensor streams
- Earthquake early warning systems (seismic data streams)
- Telescope data streams for astronomical event detection (e.g., LIGO detecting gravitational waves)
- Genomic data streams in bioinformatics

### Application Domains Summary

| Domain              | Stream Source               | Key Use Case                        |
|---------------------|-----------------------------|-------------------------------------|
| Finance             | Trading platforms, ATMs     | Fraud detection, algo trading       |
| Cybersecurity       | Network packets, logs       | Intrusion detection                 |
| Social Media        | Twitter, Facebook API       | Sentiment, trending topics          |
| IoT                 | Sensors, actuators          | Predictive maintenance              |
| Telecom             | CDR, network devices        | Fraud, congestion management        |
| Healthcare          | Medical devices, wearables  | Patient monitoring, early warning   |
| Transportation      | GPS, traffic sensors        | Fleet management, routing           |
| Energy              | Smart meters, grid sensors  | Load balancing, theft detection     |

---

## Question 3: Stream Model and Data Stream Management System (DSMS) Architecture (12M)

### The Stream Model

The **data stream model** defines how data arrives and is processed. Unlike the database model (data is stored and queried repeatedly), the stream model processes data on-the-fly.

#### Key Assumptions of the Stream Model:
1. Data arrives continuously at high rate
2. The entire stream cannot be stored in memory
3. Each data item is processed once (or a limited number of times)
4. The order of arrival may be important
5. Approximate answers are acceptable in many cases

#### Formal Definition:
A stream S is an ordered sequence of tuples:
```
S = ⟨s₁, s₂, s₃, ..., sₙ, ...⟩

Where each sᵢ = (timestamp, attribute₁, attribute₂, ..., attributeₖ)
```

#### Types of Streams:
- **Append-only streams**: New data only (no updates or deletes)
- **Time-series streams**: Ordered by timestamp
- **Transaction streams**: Each item represents an event

#### Window Models:
```
Sliding Window (size=4):
Time:   1  2  3  4  5  6  7
       [1  2  3  4]             Window at t=4
          [2  3  4  5]          Window at t=5
             [3  4  5  6]       Window at t=6

Tumbling Window (size=3):
       [1  2  3] [4  5  6] [7  8  9]   Non-overlapping
```

---

### Data Stream Management System (DSMS) Architecture

A DSMS is software infrastructure for managing and querying continuous data streams. Unlike a DBMS (designed for stored data), a DSMS is optimized for streaming data.

#### Architecture Diagram:

```
┌──────────────────────────────────────────────────────────────────┐
│                    DATA STREAM MANAGEMENT SYSTEM                  │
│                                                                    │
│  ┌─────────────┐    ┌──────────────────────────────────────────┐  │
│  │  Data       │    │           QUERY PROCESSOR                 │  │
│  │  Sources    │    │                                           │  │
│  │             │    │  ┌─────────────┐  ┌──────────────────┐   │  │
│  │ • Sensors   │→───│→ │  Query      │  │  Query Plan      │   │  │
│  │ • Network   │    │  │  Parser     │→ │  Optimizer       │   │  │
│  │ • Feeds     │    │  └─────────────┘  └──────────────────┘   │  │
│  │ • Logs      │    │         ↓                  ↓              │  │
│  └─────────────┘    │  ┌─────────────────────────────────────┐ │  │
│                      │  │       EXECUTION ENGINE              │ │  │
│  ┌─────────────┐    │  │                                     │ │  │
│  │  Stream     │    │  │  ┌──────────┐   ┌───────────────┐  │ │  │
│  │  Input      │    │  │  │ Operator │   │   Synopses /  │  │ │  │
│  │  Manager    │    │  │  │ Network  │←→ │   Summaries   │  │ │  │
│  │ (buffering) │    │  │  │ (DAG)    │   │   (Sketches)  │  │ │  │
│  └─────────────┘    │  │  └──────────┘   └───────────────┘  │ │  │
│                      │  └─────────────────────────────────────┘ │  │
│  ┌─────────────┐    │         ↓                                  │  │
│  │  Scratch    │    │  ┌─────────────┐                           │  │
│  │  Storage    │←──→│  │  Output     │→ Results / Alerts         │  │
│  │  (limited)  │    │  │  Manager    │                           │  │
│  └─────────────┘    │  └─────────────┘                           │  │
│                      └──────────────────────────────────────────┘  │
│                                                                    │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │  APPLICATION QUERIES (Continuous Query Language - CQL)       │  │
│  │  SELECT AVG(price) FROM StockStream [RANGE 5 MIN]            │  │
│  └──────────────────────────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────────┘
```

#### Components Explained:

**1. Stream Input Manager**
- Receives incoming data from multiple sources
- Handles buffering and rate control
- Timestamps incoming data items
- Manages multiple stream schemas

**2. Query Processor**
- **Parser**: Parses continuous queries (written in CQL — Continuous Query Language)
- **Optimizer**: Converts queries into efficient operator networks
- Manages multiple standing (continuous) queries simultaneously

**3. Execution Engine**
- Implements the actual processing operators (filter, join, aggregate, window)
- Maintains a **Directed Acyclic Graph (DAG)** of operators
- Uses **synopses/summaries** (compact data structures) to approximate answers when full data cannot be stored

**4. Synopses (Sketches)**
- Compact summaries of stream data: sketches, histograms, wavelets, samples
- Enable approximate query answering within memory limits
- Examples: Count-Min Sketch, Bloom Filters, FM Sketch

**5. Scratch Storage**
- Limited fast storage (RAM/SSD) for temporary intermediate results
- Much smaller than the full stream; managed carefully

**6. Output Manager**
- Delivers query results to applications
- Triggers alerts when conditions are met
- Supports push-based delivery (unlike pull-based databases)

#### DSMS vs. DBMS

| Feature              | DBMS                        | DSMS                          |
|----------------------|-----------------------------|-------------------------------|
| Data Location        | Stored on disk              | Flowing in real time          |
| Query Type           | One-time queries            | Continuous standing queries   |
| Processing           | Pull (user requests query)  | Push (results delivered live) |
| Data Volume          | Bounded (finite DB)         | Unbounded (infinite stream)   |
| Memory               | Large disk storage          | Limited memory/scratch space  |
| Accuracy             | Exact answers               | Approximate answers accepted  |
| Schema               | Schema on write             | Schema on read                |
| Examples             | MySQL, Oracle, PostgreSQL   | Apache Kafka, Flink, Storm    |

---

## Question 4: Counting Ones in a Window Using DGIM Algorithm (12M)

### Problem Statement
Given a stream of bits (0s and 1s), maintain a count of the number of **1s in the last N bits** (sliding window of size N) using only **O(log² N)** memory.

### Challenge
- We cannot store all N bits (N can be very large)
- We need an approximate count with error ≤ 50%

### DGIM Algorithm (Datar-Gionis-Indyk-Motwani, 2002)

#### Core Idea: Store Buckets
Instead of storing individual bits, store **buckets** where each bucket represents a run of 1s.

#### Bucket Definition:
Each bucket has:
- **Timestamp** of the most recent 1 in the bucket (the right edge)
- **Size** = number of 1s in the bucket (must be a power of 2)

#### DGIM Rules:
1. Each bucket size is a **power of 2** (1, 2, 4, 8, 16, ...)
2. At any time, there are **either 1 or 2 buckets of each size**
3. Bucket sizes increase moving from right (recent) to left (older)
4. Buckets with older timestamps cannot be larger than newer buckets

#### Example Walkthrough:

Suppose N = 8 (window size), and the stream is:
```
Stream (right = most recent):
Position: 8  7  6  5  4  3  2  1
Bits:      1  0  1  1  0  1  1  1

Bucket Representation:
[Size=4, timestamp=8] [Size=2, timestamp=5] [Size=1, timestamp=3] [Size=1, timestamp=1]

Visual:
───────────────────────────────────→ time
  Old                            New
  [  B₃=4  ][   B₂=2  ][ B₁=1 ][ B₀=1 ]
   t=1        t=5        t=6     t=7
```

#### Counting Ones in Last N Bits:
1. Find all buckets whose **timestamp falls within [current_time - N + 1, current_time]**
2. Sum up the sizes of all fully contained buckets
3. For the **oldest bucket** (may be partially in the window), count **half its size**

```
Estimate = (sum of all complete bucket sizes) + (size of oldest partial bucket / 2)
```

**Error Bound**: The estimate is within **±50%** of the true count.

#### Updating the DGIM Structure (When a new bit arrives):

**If new bit = 0:**
- Do nothing (no new 1 to record)
- Drop any buckets that have fallen outside the window

**If new bit = 1:**
```
Step 1: Create a new bucket of size 1 at current timestamp
Step 2: Check if there are now 3 buckets of size 1
        If yes → merge the two oldest size-1 buckets into one size-2 bucket
Step 3: Check if there are now 3 buckets of size 2
        If yes → merge the two oldest size-2 buckets into one size-4 bucket
Step 4: Continue propagating merges up the size chain as needed
Step 5: Drop any buckets whose timestamp < current_time - N + 1
```

#### DGIM Merge Example:

```
Before new 1 arrives:
[Size=4,t=1] [Size=2,t=5] [Size=1,t=7] [Size=1,t=8]

New 1 arrives at t=9:
[Size=4,t=1] [Size=2,t=5] [Size=1,t=7] [Size=1,t=8] [Size=1,t=9]
                                         ↑ Now 3 buckets of size=1 → Merge oldest two
↓
[Size=4,t=1] [Size=2,t=5] [Size=2,t=8] [Size=1,t=9]
              ↑ Now 2 buckets of size=2 → OK (≤2 allowed)
Final state:
[Size=4,t=1] [Size=2,t=5] [Size=2,t=8] [Size=1,t=9]
```

#### Memory Complexity:
- At most 2 buckets per size class
- Sizes range from 1 to N (at most log₂N different sizes)
- Total memory: **O(log² N)** bits

#### Accuracy Improvement:
To reduce error from 50% to ~(1/k)%, use **at most k or k+1 buckets per size** instead of 1 or 2. This costs O(k · log N / log(1+1/k)) memory.

#### Summary Table

| Aspect           | Detail                                              |
|------------------|-----------------------------------------------------|
| Problem          | Count 1s in last N bits of a bit stream             |
| Memory Used      | O(log² N)                                           |
| Error Bound      | ≤ 50% (can be reduced with more buckets)            |
| Bucket Sizes     | Powers of 2 (1, 2, 4, 8, ...)                      |
| Buckets per Size | At most 2                                           |
| Merge Trigger    | When 3 buckets of same size exist                   |
| Old Bucket Drop  | When timestamp falls outside window                 |

---

## Question 5i: Counting Distinct Elements in a Stream (6M)

### Problem
Given a stream of elements from a universe U, estimate the number of **distinct** elements seen so far. Storing all elements exactly requires O(|U|) memory — impractical for large U.

### Flajolet-Martin (FM) Algorithm (Approximate)

#### Core Idea:
Use **hash functions** and the position of **trailing zeros** in binary hash values to estimate distinct count.

#### Intuition:
If we hash elements to binary numbers uniformly:
- ~1/2 of hashes end in 0 (at least one trailing zero)
- ~1/4 of hashes end in 00 (at least two trailing zeros)
- ~1/2^r of hashes end in r trailing zeros

So if the **maximum trailing zeros observed = r**, then the number of distinct elements ≈ **2^r**.

#### Algorithm Steps:

```
Initialize: bitmap R[0..B-1] = all 0s

For each element x in stream:
   1. Compute h(x) → binary representation
   2. Find ρ(h(x)) = position of lowest-order 1-bit
      (i.e., number of trailing zeros)
   3. Set R[ρ(h(x))] = 1

Estimate = 2^R  where R = position of leftmost 0 in bitmap
           (i.e., max trailing zeros seen + 1 approximately)
```

#### Example:

```
Stream: [a, b, c, a, d, b]

Hash values (32-bit):
h(a) = ...10100  → ρ = 2 (2 trailing zeros) → R[2] = 1
h(b) = ...01110  → ρ = 1 (1 trailing zero)  → R[1] = 1
h(c) = ...10001  → ρ = 0 (0 trailing zeros) → R[0] = 1
h(a) = ...10100  → ρ = 2 (already seen)     → R[2] = 1
h(d) = ...11000  → ρ = 3 (3 trailing zeros) → R[3] = 1
h(b) = ...01110  → ρ = 1 (already seen)     → R[1] = 1

Bitmap R: [1, 1, 1, 1, 0, 0, ...]
Max trailing zero position = 3
Estimate = 2³ = 8  (True distinct count = 4)
```

#### Improving Accuracy:
- Use **multiple hash functions** and take the **median of estimates** from groups of estimators
- **HyperLogLog** (modern version): uses harmonic mean across many hash functions, achieves ~1.04/√m error with m registers

#### Memory:
- Only O(log log N) bits per hash function
- HyperLogLog uses ~1.5 KB for millions of distinct elements

---

## Question 5ii: Finding Most Popular Elements Using Decaying Window (6M)

### Problem
In a data stream, find the most frequently occurring elements. Because the stream is infinite, we use a **decaying window** to give more importance to recent elements.

### Decaying Window Model
Instead of counting all occurrences equally, apply an **exponential decay** to older counts:

```
Score(x) at time t = Σ c · d^(t - tᵢ)

Where:
  c   = weight of each occurrence (usually 1)
  d   = decay factor (0 < d < 1, e.g., 0.9 or 0.5)
  tᵢ  = time of the i-th occurrence of x
  t   = current time
```

**Effect**: Recent occurrences contribute more than older ones.

### Algorithm: Sticky Sampling / Lossy Counting with Decay

#### Simplified Decaying Count Algorithm:

```
Initialize: empty hash table T (element → decayed_count)

For each new element x at time t:
   1. Multiply all existing counts by decay factor d:
      For all y in T: T[y] ← T[y] × d
   2. Add 1 for the new element:
      T[x] ← T[x] + 1
   3. Remove elements with count below threshold ε:
      Delete y if T[y] < ε
```

#### Example:

```
Stream: [A, B, A, C, A, B, C, C]
Decay d = 0.5, ε = 0.1

After A: {A: 1.0}
After B: {A: 0.5, B: 1.0}
After A: {A: 1.5, B: 0.5}
After C: {A: 0.75, B: 0.25, C: 1.0}
After A: {A: 1.375, B: 0.125, C: 0.5}
After B: {A: 0.69, B: 1.06, C: 0.25}
After C: {A: 0.34, B: 0.53, C: 1.125}
After C: {A: 0.17, B: 0.26, C: 1.56}

Most popular: C (1.56) > B (0.26) > A (0.17)
```

### Misra-Gries Algorithm (Finding Frequent Items)
For finding elements appearing more than n/k times:
1. Maintain at most k-1 counters
2. For each new element, increment its counter (or add with count 1)
3. If k counters full, decrement all by 1 and remove zeros
4. Elements remaining are candidates for most frequent

### Applications
- **Trending topics** on Twitter/social media
- **Hot products** on e-commerce platforms
- **Popular search queries** in search engines
- **Network anomaly detection** (most frequent IP addresses)

---

## Question 6: Filters in Big Data — Bloom Filter with Example (12M)

### What is a Filter in Big Data?
A **filter** is a data structure or algorithm used to quickly test membership — whether an element belongs to a set — without storing the entire set. Filters trade **perfect accuracy for speed and memory efficiency**.

### Common Filters:
- Bloom Filter (most widely used)
- Cuckoo Filter
- Counting Bloom Filter
- Quotient Filter

---

### Bloom Filter

**Invented by**: Burton Howard Bloom (1970)

#### Definition:
A Bloom Filter is a **space-efficient probabilistic data structure** that tests whether an element is a member of a set. It may give **false positives** (say an element is present when it isn't) but **never gives false negatives** (never says absent when it is present).

```
Result Guarantees:
  "NOT IN SET" → Definitely not in set (100% accurate)
  "IN SET"     → Probably in set (may be false positive)
```

#### Structure:
- A **bit array** of m bits, all initialized to 0
- **k independent hash functions**: h₁, h₂, ..., hₖ
- Each hash function maps an element to one of m positions

#### Operations:

**INSERT(x):**
```
For i = 1 to k:
    Compute hᵢ(x) → position p
    Set bit[p] = 1
```

**QUERY(x):**
```
For i = 1 to k:
    Compute hᵢ(x) → position p
    If bit[p] == 0:
        Return "DEFINITELY NOT IN SET"
Return "PROBABLY IN SET"
```

#### Visual Example:

Suppose m = 10 bits, k = 3 hash functions (h₁, h₂, h₃)

**Inserting "cat":**
```
h₁("cat") = 1   → set bit[1] = 1
h₂("cat") = 5   → set bit[5] = 1
h₃("cat") = 9   → set bit[9] = 1

Bit array: [0, 1, 0, 0, 0, 1, 0, 0, 0, 1]
Position:   0  1  2  3  4  5  6  7  8  9
```

**Inserting "dog":**
```
h₁("dog") = 2   → set bit[2] = 1
h₂("dog") = 5   → set bit[5] = 1  (already 1)
h₃("dog") = 7   → set bit[7] = 1

Bit array: [0, 1, 1, 0, 0, 1, 0, 1, 0, 1]
Position:   0  1  2  3  4  5  6  7  8  9
```

**Query "cat":**
```
h₁("cat") = 1   → bit[1] = 1 ✓
h₂("cat") = 5   → bit[5] = 1 ✓
h₃("cat") = 9   → bit[9] = 1 ✓
All bits = 1 → "PROBABLY IN SET" ✓ (correct)
```

**Query "fish":**
```
h₁("fish") = 3  → bit[3] = 0 ✗
→ "DEFINITELY NOT IN SET" ✓ (correct)
```

**False Positive Example — Query "bird":**
```
h₁("bird") = 2  → bit[2] = 1 ✓
h₂("bird") = 7  → bit[7] = 1 ✓
h₃("bird") = 1  → bit[1] = 1 ✓
All bits = 1 → "PROBABLY IN SET" ✗ (FALSE POSITIVE! bird was never inserted)
```

#### False Positive Rate Formula:
```
FPR ≈ (1 - e^(-kn/m))^k

Where:
  n = number of elements inserted
  m = size of bit array
  k = number of hash functions
  
Optimal k = (m/n) × ln(2) ≈ 0.693 × (m/n)
```

#### Design Decisions:

| Parameter | Effect                                     |
|-----------|--------------------------------------------|
| Larger m  | Lower FPR, more memory                     |
| More k    | Lower FPR up to optimal k, then increases  |
| More n    | Higher FPR (more collisions)               |

**Rule of thumb**: For 1% FPR, use ~10 bits per element.

#### Advantages:
- Extremely memory-efficient (no element stored, only bits)
- O(k) insertion and query time (fast)
- No false negatives
- Easily parallelizable

#### Limitations:
- Cannot delete elements (use Counting Bloom Filter for deletion)
- False positives exist
- Cannot retrieve stored elements

#### Applications in Big Data:

| Application             | How Bloom Filter is Used                              |
|-------------------------|-------------------------------------------------------|
| **Google Bigtable**     | Avoid disk lookups for non-existent rows/columns      |
| **Apache Cassandra**    | Reduce disk I/O by filtering non-existent keys        |
| **Web Crawlers**        | Avoid re-visiting already crawled URLs                |
| **Spam Filters**        | Quick check against known spam email list             |
| **Network Routers**     | Fast IP address lookup                                |
| **Databases**           | Query optimization — skip partitions that lack data   |
| **Cryptocurrency**      | Bitcoin uses Bloom filters for SPV wallet syncing     |

---

## Question 7: Decaying Window and Its Performance in Data Analytics (12M)

### Definition
A **decaying window** (also called an **exponentially weighted window** or **fading window**) is a technique for processing data streams where **older data is given progressively less weight** than newer data, simulating a "memory" that fades over time.

```
Weight of event at time tᵢ when current time is t:
    w(tᵢ) = d^(t - tᵢ)

Where d = decay factor, 0 < d < 1
```

### Why Decaying Windows?

In real streams, recent events are often more relevant than old ones:
- A product trending today is more relevant than one trending 6 months ago
- Network attacks happening now matter more than old attacks
- Recent user behavior better predicts future behavior

### Types of Windows in Stream Processing

```
1. Landmark Window (from start to now):
   ────────────────────────────────→
   | All past data counted equally  |
   
2. Sliding Window (last N items):
                    ┌─────────────┐
   ────────────────→│  Last N     │→ Now
                    └─────────────┘
   
3. Tumbling/Hopping Window:
   [W₁][W₂][W₃][W₄] → Non-overlapping fixed windows
   
4. Decaying Window (exponential fade):
   Past ←──────────────────────── Now
   Weight: 0.1  0.2  0.4  0.8  1.0 (d=0.5)
```

### Mathematical Formulation

For a stream element xᵢ arriving at time tᵢ, the decaying sum at current time t is:

```
S(t) = Σ d^(t - tᵢ) · f(xᵢ)

Where f(xᵢ) is the contribution of element xᵢ (e.g., 1 for counting, xᵢ for sum)
```

**Recursive update formula** (efficient computation):
```
When new element x arrives at time t:
    S(t) = d · S(t-1) + f(x)

This requires only ONE stored value S (no full history needed!)
```

### Choosing the Decay Factor d

| Decay Factor d | Half-Life (n where d^n = 0.5) | Behavior                |
|----------------|-------------------------------|-------------------------|
| 0.99           | ~69 time steps                | Slow decay (long memory) |
| 0.9            | ~7 time steps                 | Moderate decay          |
| 0.5            | 1 time step                   | Fast decay (short memory)|
| 0.1            | <1 time step                  | Very fast decay         |

**Half-life**: The time n such that d^n = 0.5 → n = log(0.5)/log(d)

### Decaying Window for Counting

**Problem**: How many 1s have appeared recently in a bit stream?

```
Decaying count update:
    c(t) = d · c(t-1) + x(t)   where x(t) ∈ {0, 1}
    
If d = 0.5:
  Effectively counts 1s in recent stream with exponential decay
  Equivalent to: recent 1s contribute ~1, older ones contribute less
```

### Decaying Window for Finding Heavy Hitters

```
For each element e, maintain decayed frequency:
    freq(e, t) = d^(t - last_update) · old_freq + 1

Periodically remove elements with freq < threshold ε
Report elements with highest freq as heavy hitters
```

### Implementation Considerations

**Space Efficiency:**
- Store only current decayed scores for active elements
- Prune elements whose score drops below ε
- Expected number of active elements: O(1/ε)

**Time Efficiency:**
- O(1) update per element
- O(k) to retrieve top-k elements if using a heap

### Applications in Analytics

1. **Trending Topics**: Twitter-like system using decaying counts per hashtag
2. **Anomaly Detection**: Decaying window of error rates; spike = anomaly
3. **Recommendation Systems**: Decay user's old interaction history
4. **Network Monitoring**: Decaying packet count per IP to detect DDoS
5. **Financial Analytics**: Exponentially weighted moving average (EWMA) for stock prices
6. **Churn Prediction**: Track user engagement with decaying weights

### Decaying Window vs. Sliding Window

| Aspect            | Sliding Window               | Decaying Window               |
|-------------------|------------------------------|-------------------------------|
| Memory            | O(N) — stores N items        | O(1) — just one running value |
| Recency Emphasis  | All N items equal weight     | Exponential weight to recent  |
| Boundary Effect   | Sharp cutoff at N items ago  | Smooth decay                  |
| Computation       | Subtract oldest, add newest  | Multiply by d and add new     |
| Use Case          | Fixed lookback period needed | Smooth trend tracking         |

---

## Question 8a: FM Algorithm and Its Applications (6M)

### Flajolet-Martin (FM) Algorithm

**Proposed by**: Philippe Flajolet and G. Nigel Martin (1985)

#### Purpose:
Estimate the number of **distinct elements** in a stream using a single pass with sublinear memory.

#### Algorithm Steps:

**Initialization:**
- Choose a hash function h: Universe → {0, 1, ..., 2^L - 1}
- Create a bitmap BITMAP[0..L-1], initialized to all 0s

**Processing each stream element x:**
```
1. Compute hash value r = h(x)
2. Find ρ(r) = position of the lowest-order 1-bit in binary(r)
   (equivalently: number of trailing zeros in binary(r))
3. If BITMAP[ρ(r)] == 0: set BITMAP[ρ(r)] = 1
```

**Estimation:**
```
R = position of leftmost 0 in BITMAP
Estimate of distinct count = 2^R / φ

Where φ ≈ 0.77351 (a correction constant)
```

#### Why Trailing Zeros Work:

```
For a uniform hash function over 2^L values:
  P(trailing zeros ≥ 0) = 1      (every number)
  P(trailing zeros ≥ 1) = 1/2    (even numbers)
  P(trailing zeros ≥ 2) = 1/4    (divisible by 4)
  P(trailing zeros ≥ k) = 1/2^k

If d distinct elements seen, we expect:
  BITMAP[k] = 1  when 2^k << d  (likely seen)
  BITMAP[k] = 0  when 2^k >> d  (unlikely seen)
  Transition around k ≈ log₂(d)
```

#### FM Algorithm Diagram:

```
Stream: [a, b, a, c, d, b, e]

Hash → Binary → Trailing Zeros → Bitmap Position
h(a) = 12 = 1100₂ → 2 trailing zeros → BITMAP[2] = 1
h(b) = 6  = 0110₂ → 1 trailing zero  → BITMAP[1] = 1
h(a) = 12 = 1100₂ → 2 trailing zeros → BITMAP[2] = 1 (no change)
h(c) = 9  = 1001₂ → 0 trailing zeros → BITMAP[0] = 1
h(d) = 4  = 0100₂ → 2 trailing zeros → BITMAP[2] = 1 (no change)
h(b) = 6  = 0110₂ → 1 trailing zero  → BITMAP[1] = 1 (no change)
h(e) = 8  = 1000₂ → 3 trailing zeros → BITMAP[3] = 1

BITMAP: [1, 1, 1, 1, 0, 0, ...]
         0  1  2  3  4
R = 4 (leftmost 0)
Estimate = 2⁴ / 0.773 ≈ 20.7 (True = 5, single hash gives rough estimate)
```

#### Improving Accuracy:
Use **multiple independent hash functions** h₁, h₂, ..., hₖ:
- Run FM algorithm in parallel with each hash function
- Group estimates into groups of ~10
- Take **mean** within each group
- Take **median** across group means
- Reduces variance significantly

#### Memory Complexity:
- Single FM: O(log N) bits
- k independent FMs: O(k log N) bits

#### Applications:
1. **Database cardinality estimation**: Estimate distinct values in SQL queries
2. **Network traffic analysis**: Count distinct source/destination IPs
3. **Web analytics**: Count unique visitors to websites
4. **Social media**: Estimate unique users engaging with content
5. **Search engines**: Index size estimation

---

## Question 8b: AMS Algorithm and Its Applications (6M)

### Alon-Matias-Szegedy (AMS) Algorithm

**Proposed by**: Noga Alon, Yossi Matias, and Mario Szegedy (1996, Turing Award-winning work)

#### Purpose:
Estimate **frequency moments** of a data stream, particularly the **second moment F₂** (also called surprise number), which measures the skewness of element frequencies.

#### Frequency Moments Definition:
```
If element i appears fᵢ times in a stream of length n:

F₀ = number of distinct elements
F₁ = total number of elements = n
F₂ = Σ fᵢ²  (Second moment — measures concentration)
Fₖ = Σ fᵢᵏ
```

**F₂ (Second Moment)** measures how "uneven" the distribution is:
- F₂ is small if elements appear uniformly
- F₂ is large if a few elements dominate

#### AMS Sketch for Estimating F₂:

**Setup:**
- Pick a random 4-wise independent hash function h: {1..n} → {-1, +1}
  (assigns each distinct element a random ±1 value)
- Maintain a single counter Z = 0

**Processing:**
```
For each stream element x at position i:
    Z ← Z + h(x)
```

**Estimation:**
```
F₂ ≈ Z²
```

#### Why Z² Estimates F₂:

```
Z = Σ fᵢ · h(i)   (sum over distinct elements, weighted by frequency)

E[Z²] = E[(Σ fᵢ · h(i))²]
       = Σ fᵢ²  (cross terms cancel due to independence of h)
       = F₂
```

So Z² is an **unbiased estimator** of F₂!

#### AMS Algorithm Diagram:

```
Stream: [1, 3, 2, 1, 3, 1]

Hash assignments: h(1)=+1, h(2)=-1, h(3)=+1

Processing:
  See 1: Z = 0 + (+1) = 1
  See 3: Z = 1 + (+1) = 2
  See 2: Z = 2 + (-1) = 1
  See 1: Z = 1 + (+1) = 2
  See 3: Z = 2 + (+1) = 3
  See 1: Z = 3 + (+1) = 4

Estimate F₂ = Z² = 16

True F₂ = f₁² + f₂² + f₃² = 3² + 1² + 2² = 9 + 1 + 4 = 14
(Reasonable estimate!)
```

#### Improving Accuracy:
- Run **k independent AMS sketches** in parallel
- Take **median of means** approach
- With O(1/ε² · log(1/δ)) sketches, achieve (1±ε) approximation with probability ≥ 1-δ

#### Count-Min Sketch (Extension of AMS idea):
A more practical variant:
- Use a 2D array of counters (d rows × w columns)
- d independent hash functions, one per row
- For query: return minimum count across all rows for that element

#### Applications of AMS:
1. **Network traffic analysis**: Detect heavy hitter flows (F₂ measures traffic concentration)
2. **Database query optimization**: Estimate join sizes
3. **Anomaly detection in streams**: Sudden increase in F₂ signals a flash crowd or attack
4. **Natural language processing**: Document similarity using sketch vectors
5. **Genomics**: Comparing frequency profiles of k-mers in DNA sequences
6. **Database systems**: Histogram construction for query planners

---

## Question 9: Real-Time Analytics — Technologies in Detail (12M)

### Definition of Real-Time Analytics
**Real-Time Analytics** (RTA) is the process of analyzing data **immediately as it is generated or received**, enabling organizations to make instantaneous decisions or trigger automated responses.

```
Traditional Analytics:
  Data → Store → Batch Process → Insights (hours/days later)

Real-Time Analytics:
  Data → Process Instantly → Insights (milliseconds/seconds)
```

### Why Real-Time Analytics?

| Scenario                | Latency Need | Consequence of Delay          |
|-------------------------|--------------|-------------------------------|
| Credit card fraud       | <100ms       | Financial loss, customer harm  |
| Stock trading           | <1ms         | Missed trades, arbitrage loss  |
| Network intrusion       | <1s          | Data breach                   |
| Patient vital monitoring| <5s          | Patient safety risk           |
| E-commerce personalization| <200ms    | Lost sale, poor experience    |

### Key Technologies

#### 1. Apache Kafka
A **distributed event streaming platform** for high-throughput, fault-tolerant real-time data pipelines.

```
Architecture:
  Producers → [Kafka Topics (Partitioned)] → Consumers

Features:
  - Handles millions of messages/second
  - Persistent storage (configurable retention)
  - Replay capability
  - Horizontal scaling
  - Exactly-once semantics
  
Use Case: Buffer between data sources and stream processors
```

#### 2. Apache Flink
A **stateful stream processing framework** for exactly-once, low-latency processing.

```
Flink Architecture:
  Data Source → [JobManager] → [TaskManagers] → Data Sink
                                    ↓
                               Stateful Operators
                               (windows, joins, aggregations)

Key Features:
  - True streaming (not micro-batch)
  - Event time processing (handles late arrivals)
  - Exactly-once guarantees
  - Millisecond latency
  - Complex event processing (CEP) library
```

#### 3. Apache Spark Streaming / Structured Streaming
Spark's extension for **micro-batch stream processing**.

```
Spark Streaming Model:
  Live Stream → Mini-batches (0.5s–5s intervals) → Batch RDD Processing → Output

Structured Streaming (newer):
  Treats stream as an unbounded table
  SELECT * FROM stream WHERE condition → Continuous query
  
Trade-off: Higher throughput, slightly higher latency vs. Flink
```

#### 4. Apache Storm
One of the earliest real-time stream processing systems, known for **sub-millisecond latency**.

```
Storm Topology:
  Spouts (data sources) → Bolts (processing) → Output

  [Kafka Spout] → [Filter Bolt] → [Aggregation Bolt] → [Dashboard Bolt]
  
Features:
  - At-least-once processing
  - Very low latency
  - Language agnostic (JVM + Thrift)
```

#### 5. Apache Samza
A **distributed stream processing framework** tightly integrated with Kafka.

- State stored in **RocksDB** (fast local key-value store)
- Fault tolerant via Kafka changelog
- Used at LinkedIn at massive scale

#### 6. Amazon Kinesis
AWS managed streaming service, similar to Kafka.

- **Kinesis Data Streams**: Real-time data ingestion
- **Kinesis Data Analytics**: SQL queries on streams
- **Kinesis Data Firehose**: Load streams to S3/Redshift

#### 7. Google Cloud Dataflow / Apache Beam
- Unified **batch + stream processing** model
- Beam SDK abstracts underlying runner (Flink, Spark, Dataflow)
- **Watermarks** for handling late data
- Windowing and triggering mechanisms

#### 8. Complex Event Processing (CEP) Engines
- Detect patterns across multiple events over time
- Tools: **Esper**, **Drools Fusion**, **Flink CEP**

```
Example CEP Rule:
  "Alert if same user makes 3+ failed logins within 2 minutes"
  
  Pattern: (login_fail) → (login_fail) → (login_fail)
           within 2 minutes
           for same user_id
```

#### 9. Time-Series Databases (TSDB)
Store and query real-time metric streams:
- **InfluxDB**: IoT and metrics
- **TimescaleDB**: SQL + time-series
- **OpenTSDB**: Built on HBase

#### 10. Real-Time Visualization Tools
- **Grafana**: Real-time dashboards connected to streaming sources
- **Kibana**: Real-time log analytics (ELK Stack)
- **Apache Superset**: Interactive analytics

### Real-Time Analytics Architecture

```
┌────────────────────────────────────────────────────────────────┐
│                   REAL-TIME ANALYTICS PIPELINE                  │
│                                                                  │
│  Data Sources         Ingestion         Processing              │
│  ┌──────────┐        ┌─────────┐       ┌──────────────────┐    │
│  │ IoT      │──────→ │         │──────→│  Stream Processor │    │
│  │ Sensors  │        │  Kafka  │       │  (Flink/Storm)    │    │
│  ├──────────┤        │  /      │       ├──────────────────┤    │
│  │ Web Logs │──────→ │ Kinesis │──────→│  CEP Engine      │    │
│  ├──────────┤        │         │       ├──────────────────┤    │
│  │ APIs     │──────→ │         │──────→│  ML Scoring      │    │
│  └──────────┘        └─────────┘       └────────┬─────────┘    │
│                                                  │              │
│  Storage              Serving           Display │              │
│  ┌──────────┐        ┌─────────┐       ┌────────↓─────────┐    │
│  │ Time-    │←──────→│ Cache   │──────→│  Grafana/Kibana  │    │
│  │ Series DB│        │ (Redis) │       │  Dashboard       │    │
│  └──────────┘        └─────────┘       └──────────────────┘    │
└────────────────────────────────────────────────────────────────┘
```

### Technology Comparison

| Technology        | Latency    | Throughput | State     | Guarantee        | Best For               |
|-------------------|------------|------------|-----------|------------------|------------------------|
| Apache Kafka      | ms         | Very High  | Log-based | At-least-once    | Ingestion, buffering   |
| Apache Flink      | ms         | High       | Rich      | Exactly-once     | Complex stream logic   |
| Spark Streaming   | seconds    | Very High  | Medium    | Exactly-once     | Batch + stream hybrid  |
| Apache Storm      | sub-ms     | High       | Limited   | At-least-once    | Ultra-low latency      |
| Amazon Kinesis    | seconds    | High       | Managed   | At-least-once    | AWS ecosystem          |

---

## Question 10: Three Categories of Prediction Methodologies (12M)

Prediction methodologies in data stream analytics and machine learning are broadly classified into three categories: **Statistical Methods**, **Machine Learning Methods**, and **Deep Learning / Neural Methods**.

---

### Category 1: Statistical Prediction Methods

These methods are grounded in **classical statistics** and probability theory. They make explicit assumptions about data distributions.

#### 1a. Linear Regression
Models linear relationship between input features and continuous output.
```
ŷ = β₀ + β₁X₁ + β₂X₂ + ... + βₙXₙ + ε
```
- Assumes: linearity, independence, normality of residuals
- Interpretable coefficients

#### 1b. Time Series Methods

**ARIMA (AutoRegressive Integrated Moving Average):**
```
ARIMA(p, d, q):
  p = number of autoregressive terms (past values)
  d = degree of differencing (make stationary)
  q = number of moving average terms (past errors)
  
  Xₜ = c + φ₁Xₜ₋₁ + ... + φₚXₜ₋ₚ + θ₁εₜ₋₁ + ... + θqεₜ₋q + εₜ
```

**Exponential Smoothing (ES):**
```
  Sₜ = α·Xₜ + (1-α)·Sₜ₋₁
  
  Simple (SES): For data with no trend or seasonality
  Holt's: Adds trend component
  Holt-Winters: Adds trend + seasonality
```

#### 1c. Bayesian Prediction
- Prior knowledge + observed data → posterior predictive distribution
- Provides uncertainty bounds with predictions
- Used in medical diagnosis, A/B testing

#### 1d. Logistic Regression (for classification)
```
P(Y=1|X) = 1 / (1 + e^-(β₀ + β₁X₁ + ... + βₙXₙ))
```

#### Statistical Methods Summary:

```
  Statistical Methods
       ├── Regression (Linear, Logistic, Polynomial)
       ├── Time Series (ARIMA, SARIMA, Exponential Smoothing)
       ├── Bayesian Methods (Naive Bayes, Bayesian Networks)
       └── Hypothesis Testing / Confidence Intervals
```

---

### Category 2: Machine Learning Prediction Methods

ML methods **learn patterns from data** without requiring explicit distributional assumptions.

#### 2a. Decision Trees
- Split data recursively on features that maximize information gain (or minimize Gini impurity)
- **Interpretable** but prone to overfitting

```
       Is Age > 30?
      /            \
   Yes              No
  Income > 50K?   Education?
  /       \        /       \
 High     Low   College   High School
 Risk     Risk  Low Risk   High Risk
```

#### 2b. Random Forest
- **Ensemble** of decision trees using bagging
- Each tree trained on bootstrap sample with random feature subset
- Reduces variance significantly, handles high-dimensional data

#### 2c. Gradient Boosting (XGBoost, LightGBM, CatBoost)
- Sequentially build trees, each correcting errors of previous
- State-of-the-art for tabular data prediction

```
Final Model = Tree₁ + η·Tree₂ + η²·Tree₃ + ...
Where η = learning rate (shrinkage)
```

#### 2d. Support Vector Machines (SVM)
- Find optimal hyperplane separating classes
- Kernel trick for non-linear separation
- Effective in high-dimensional spaces

#### 2e. k-Nearest Neighbors (kNN)
- Predict based on k most similar training examples
- Non-parametric, lazy learner
- Sensitive to irrelevant features and curse of dimensionality

#### 2f. Online Learning (for streams)
Traditional ML requires all data upfront. **Online learning** updates the model with each new example:

```
For each stream element (x, y):
    ŷ = model.predict(x)
    loss = compute_loss(y, ŷ)
    model.update(x, y, loss)  ← Incremental update

Algorithms: Stochastic Gradient Descent (SGD), 
            Perceptron, FTRL (Follow The Regularized Leader),
            Hoeffding Tree (for streaming decision trees)
```

#### ML Methods Summary:

```
  Machine Learning Methods
       ├── Tree-Based (Decision Tree, Random Forest, XGBoost)
       ├── Kernel Methods (SVM, SVR)
       ├── Lazy Learners (kNN)
       ├── Linear Methods (SGD, Ridge, Lasso)
       └── Online Learning (Hoeffding Tree, SGD variants)
```

---

### Category 3: Deep Learning / Neural Network Methods

Neural methods automatically learn hierarchical feature representations from raw data, excelling at complex patterns.

#### 3a. Feedforward Neural Networks (FNN / MLP)
- Multiple layers of neurons with non-linear activations
- Universal function approximators

```
Input Layer → Hidden Layer 1 → Hidden Layer 2 → Output Layer
  [X₁]           [H₁₁]           [H₂₁]           [ŷ]
  [X₂]    →      [H₁₂]    →      [H₂₂]    →
  [X₃]           [H₁₃]           [H₂₃]
```

#### 3b. Recurrent Neural Networks (RNN) and LSTM
Designed for **sequential/time-series data** — maintain internal state (memory).

```
RNN:
  hₜ = f(Wₕ · hₜ₋₁ + Wₓ · xₜ + b)
  ŷₜ = g(Wᵧ · hₜ)

LSTM (Long Short-Term Memory):
  Solves vanishing gradient problem using gates:
  - Forget Gate: What to erase from memory
  - Input Gate: What new info to store
  - Output Gate: What to output
  
  Excellent for: Stock prediction, NLP, sensor streams
```

#### 3c. Convolutional Neural Networks (CNN)
- Excels at spatial/temporal pattern recognition
- 1D CNNs applied to time-series streams
- 2D CNNs for image streams (video analytics, surveillance)

#### 3d. Transformer Models / Attention Mechanism
- State-of-the-art for NLP streams
- Self-attention captures long-range dependencies
- **BERT, GPT** for text stream analytics
- **Temporal Fusion Transformer** for multi-horizon forecasting

#### 3e. Autoencoders (for Anomaly Detection)
```
Normal data:
  Input → Encoder → Bottleneck → Decoder → Reconstructed Input
  Low reconstruction error → Normal

Anomaly:
  Input → Encoder → Bottleneck → Decoder → Poor Reconstruction
  High reconstruction error → ANOMALY DETECTED
```

#### Deep Learning Methods Summary:

```
  Deep Learning Methods
       ├── Sequential Models (RNN, LSTM, GRU)
       ├── Spatial Models (CNN 1D/2D)
       ├── Attention Models (Transformer, BERT)
       ├── Generative Models (VAE, GAN)
       └── Anomaly Detection (Autoencoder, One-class NN)
```

---

### Comparison of Three Categories

| Aspect              | Statistical Methods         | ML Methods                | Deep Learning Methods        |
|---------------------|-----------------------------|---------------------------|------------------------------|
| **Data Required**   | Small-moderate              | Moderate-large            | Very large                   |
| **Interpretability**| High                        | Medium                    | Low (black box)              |
| **Feature Eng.**    | Required                    | Partially required        | Automatic                    |
| **Training Time**   | Fast                        | Moderate                  | Slow (GPU often needed)      |
| **Inference Time**  | Very fast                   | Fast                      | Moderate to fast             |
| **Distribution Assumptions** | Strong       | Weak                      | None                         |
| **Best For**        | Structured, small data      | Tabular, medium data      | Images, text, sequences      |
| **Stream Support**  | ARIMA, online regression    | Online learning, Hoeffding| LSTM, online deep learning   |
| **Examples**        | ARIMA, Logistic Reg.        | Random Forest, XGBoost    | LSTM, Transformer            |

### Prediction Methodology Decision Flow:

```
Is data small (<10K samples)?
    Yes → Statistical Methods (Logistic Reg, ARIMA, Bayesian)
    No  ↓
Is data tabular (structured features)?
    Yes → ML Methods (XGBoost, Random Forest, SVM)
    No  ↓
Is data sequential/temporal?
    Yes → Deep Learning: LSTM, GRU, Transformer
Is data image/video?
    Yes → Deep Learning: CNN, Video Transformers
Is data text/NLP?
    Yes → Deep Learning: BERT, GPT, Transformer
```

---

## Quick Reference Summary

| # | Topic                        | Key Takeaway                                                               |
|---|------------------------------|----------------------------------------------------------------------------|
| 1a | Data Stream                 | Unbounded, high-velocity, one-pass sequence of data items                  |
| 1b | InfoSphere 14 Insights      | Low latency, windowing, CEP, fault tolerance, domain toolkits              |
| 2  | Stream Applications         | Finance, IoT, social media, healthcare, telecom, transportation            |
| 3  | DSMS Architecture           | Query processor + execution engine + synopses + scratch storage            |
| 4  | DGIM Algorithm              | O(log² N) memory for counting 1s; buckets of sizes 2^i; merge on 3-of-same|
| 5i | Distinct Count              | FM Algorithm: hash to bit position; trailing zeros estimate log₂(distinct) |
| 5ii| Popular Elements            | Decaying window: w(t) = d^(t-tᵢ); recent items weighted higher             |
| 6  | Bloom Filter                | m-bit array + k hashes; no false negatives; FPR ≈ (1-e^(-kn/m))^k        |
| 7  | Decaying Window             | S(t) = d·S(t-1) + f(x); O(1) update; smooth recency emphasis              |
| 8a | FM Algorithm                | Estimate distinct count; trailing zeros → log₂(distinct); O(log N) bits   |
| 8b | AMS Algorithm               | Estimate F₂ = Σfᵢ²; random ±1 hash; Z² is unbiased estimator             |
| 9  | Real-Time Analytics         | Kafka + Flink/Storm/Spark + TSDB + Grafana; ms to sub-ms latency           |
| 10 | Prediction Methodologies   | Statistical (ARIMA) + ML (XGBoost) + Deep Learning (LSTM, Transformer)    |

---

*End of Notes — Introduction to Stream Concepts*
