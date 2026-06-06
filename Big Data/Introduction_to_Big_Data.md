# Introduction to Big Data — Complete Study Notes

---

## Question 1a: Conventional Challenges in Big Data (6M)

Big Data refers to extremely large datasets that cannot be processed using traditional data management tools. The conventional challenges arise primarily due to the **3Vs (Volume, Velocity, Variety)** and extend to several more dimensions.

### 1. Volume
The sheer amount of data generated every second is enormous. Organizations collect terabytes and petabytes of data from transactions, sensors, social media, etc. Traditional databases cannot store or query at this scale efficiently.

### 2. Velocity
Data is generated at extremely high speeds (e.g., stock market ticks, Twitter feeds, IoT sensors). Processing this streaming data in real-time is a major challenge for conventional systems.

### 3. Variety
Data comes in multiple formats:
- **Structured** – relational tables (SQL databases)
- **Semi-structured** – JSON, XML
- **Unstructured** – videos, images, text, logs

Legacy systems were designed only for structured data, making multi-format handling difficult.

### 4. Veracity
Uncertainty and inaccuracy in data. Incomplete, inconsistent, or noisy data leads to unreliable analytics. Cleaning and validating data at scale is a massive overhead.

### 5. Value
Extracting meaningful insights from huge raw datasets is expensive and time-consuming. Not all data is equally valuable, and finding relevant patterns requires powerful analytics tools.

### 6. Storage and Infrastructure Costs
Traditional storage solutions like RDBMS are not cost-effective for petabyte-scale data. Organizations need distributed storage solutions (e.g., HDFS, NoSQL).

### 7. Data Security and Privacy
Handling sensitive data (e.g., healthcare records, financial data) at scale introduces serious privacy and compliance concerns (GDPR, HIPAA).

### 8. Lack of Skilled Personnel
Data scientists and big data engineers with expertise in Hadoop, Spark, machine learning, and distributed systems are scarce and expensive.

### Diagram: Big Data Challenge Wheel

```
              +------------------+
              |     VOLUME       |
              | (Storage Scale)  |
              +------------------+
                      |
   +----------+-------+-------+----------+
   | VELOCITY |               | VARIETY  |
   | (Speed)  |               | (Types)  |
   +----------+               +----------+
                      |
              +------------------+
              |    VERACITY      |
              | (Data Quality)   |
              +------------------+
                      |
              +------------------+
              |     VALUE        |
              | (Insights)       |
              +------------------+
```
![](https://1.bp.blogspot.com/-Z2tSUVY96xM/WlkJXyB_XDI/AAAAAAAADvE/ODS7eB0D5ZYuPWB2lTSxuoZ1msCnQwY0wCLcBGAs/s1600/7VBigData.JPG)
---

## Question 1b: Nature of Data (6M)

Understanding the nature of data is fundamental to Big Data analytics.

### 1. Structured Data
- Organized in rows and columns (relational databases, spreadsheets)
- Easily searchable using SQL
- Example: Employee records, sales transactions

### 2. Semi-Structured Data
- Has some organizational properties but not a rigid schema
- Example: XML, JSON, Email headers

### 3. Unstructured Data
- No predefined format or schema
- Constitutes ~80% of all data
- Example: Text documents, videos, audio, social media posts, images

### 4. Data at Rest vs. Data in Motion
- **At Rest**: Stored data waiting to be processed (HDFS, databases)
- **In Motion**: Streaming data being transmitted in real time (Kafka, Spark Streaming)

### 5. Temporal Nature
- Time-series data: stock prices, weather data, IoT sensor logs
- Temporal patterns and trends are crucial for forecasting

### 6. Spatial/Geographic Data
- Data associated with location coordinates
- Used in GPS, logistics, and urban planning

### 7. Data Dimensionality
- High-dimensional data (many features/attributes) causes the **"Curse of Dimensionality"**
- Dimensionality reduction techniques (PCA, LDA) are needed

### 8. Data Sparsity
- Many real-world datasets are sparse (most values are zero or missing)
- Example: User-product rating matrices in recommendation systems

### Nature of Data Classification Table

| Type         | Format        | Example                  | Storage       |
|--------------|---------------|--------------------------|---------------|
| Structured   | Tables/Rows   | SQL Database, CSV        | RDBMS         |
| Semi-struct  | Tags/Nodes    | JSON, XML, HTML          | NoSQL         |
| Unstructured | Raw/Binary    | Images, Video, Text      | HDFS, Object  |
| Streaming    | Continuous    | IoT, Social Media Feed   | Kafka, Spark  |

---

## Question 2: Steps Involved in Support Vector Machine (SVM) Based Inference Methodology (12M)

Support Vector Machine (SVM) is a powerful supervised learning algorithm used for classification and regression. It works by finding the **optimal hyperplane** that best separates data into classes.

### Core Concept
SVM tries to find the hyperplane that maximizes the **margin** — the distance between the hyperplane and the nearest data points of each class (called **support vectors**).

### Steps in SVM-Based Inference

#### Step 1: Data Collection and Preprocessing
- Collect labeled training data (features X, labels y)
- Normalize/scale features (z-score or min-max normalization)
- Handle missing values and outliers

#### Step 2: Feature Selection/Extraction
- Select relevant features
- Apply dimensionality reduction if needed (PCA)
- Transform raw data into a feature vector

#### Step 3: Choose the Kernel Function
SVM uses kernels to handle non-linearly separable data by mapping to a higher-dimensional space.

| Kernel         | Formula                         | Use Case                       |
|----------------|---------------------------------|--------------------------------|
| Linear         | K(x,y) = xᵀy                   | Linearly separable data        |
| Polynomial     | K(x,y) = (xᵀy + c)ᵈ           | Polynomial relationships       |
| RBF (Gaussian) | K(x,y) = exp(-γ‖x−y‖²)        | Non-linear, most common        |
| Sigmoid        | K(x,y) = tanh(αxᵀy + c)       | Neural network-like behavior   |

#### Step 4: Define the Optimization Problem
Maximize the margin by solving:

```
Minimize: (1/2) ||w||²
Subject to: yᵢ(w·xᵢ + b) ≥ 1  for all i
```

Where:
- `w` = weight vector (normal to the hyperplane)
- `b` = bias
- `yᵢ` = class label (+1 or -1)

#### Step 5: Solve Using Lagrangian (Dual Formulation)
- Convert to dual optimization using Lagrange multipliers α
- Use **Quadratic Programming (QP)** solvers to find optimal α values
- Only data points with α > 0 are **support vectors**

#### Step 6: Construct the Decision Boundary
- Compute weight vector: `w = Σ αᵢ yᵢ xᵢ`
- Compute bias: `b = yⱼ - w·xⱼ` (for any support vector xⱼ)
- Decision function: `f(x) = sign(w·x + b)`

#### Step 7: Handle Soft Margin (C-parameter)
For non-perfectly separable data, introduce slack variables ξᵢ:
- **High C** → Small margin, low bias, high variance (overfitting risk)
- **Low C** → Large margin, higher bias, lower variance (underfitting risk)

#### Step 8: Model Training
- Train on the training dataset using the selected kernel and C parameter
- Use cross-validation to tune hyperparameters (C, γ for RBF)

#### Step 9: Inference / Prediction
For a new test point x_new:
```
Prediction = sign(Σ αᵢ yᵢ K(xᵢ, x_new) + b)
```

#### Step 10: Evaluation
- Compute accuracy, precision, recall, F1-score
- Use confusion matrix to analyze classification results
- Apply k-fold cross-validation for robust performance estimation

### SVM Diagram

```
    Class A (+1)         Decision Boundary          Class B (-1)
         *                      |                       o
      *     *               ----+----                o     o
         *       Margin →  /   |   \  ← Margin        o
      *  [SV]          ---/    |    \---            [SV] o
              *           /    |     \           o
                     ----+-----+------+----
                              |
                        Support Vectors (SV)
                        maximize the margin gap
```

---

## Question 3: Bayesian Inference Methodology (12M)

Bayesian inference is a statistical method that updates the probability of a hypothesis as more evidence becomes available. It is grounded in **Bayes' Theorem**.

### Bayes' Theorem

```
P(H | E) = [ P(E | H) × P(H) ] / P(E)
```

Where:
- **P(H)** = Prior probability (belief before seeing evidence)
- **P(E | H)** = Likelihood (probability of evidence given hypothesis)
- **P(E)** = Marginal likelihood / normalizing constant
- **P(H | E)** = Posterior probability (updated belief after evidence)

### Key Concepts

#### 1. Prior Distribution P(H)
- Represents prior knowledge or belief about the parameter before observing data
- Can be **informative** (based on domain expertise) or **non-informative** (uniform, uninformative)

#### 2. Likelihood P(E | H)
- Measures how well the hypothesis explains the observed evidence
- Derived from the statistical model (e.g., normal, Bernoulli, Poisson)

#### 3. Posterior Distribution P(H | E)
- Updated belief after incorporating evidence
- Combines prior and likelihood
- Foundation for all Bayesian decisions and predictions

#### 4. Conjugate Priors
- When the prior and posterior belong to the same distribution family
- Makes computation tractable
- Example: Beta prior + Binomial likelihood → Beta posterior

### Steps in Bayesian Inference

1. **Define the Model**: Choose a parametric model for the data
2. **Specify Prior**: Choose prior distribution P(θ) for the parameter θ
3. **Collect Data**: Observe evidence E (data)
4. **Compute Likelihood**: Calculate P(E | θ)
5. **Apply Bayes' Theorem**: Compute posterior P(θ | E)
6. **Make Inference**: Use posterior mean, mode (MAP estimate), or credible intervals
7. **Update Iteratively**: As new data arrives, the posterior becomes the new prior

### Bayesian vs. Frequentist Comparison

| Aspect            | Bayesian               | Frequentist             |
|-------------------|------------------------|--------------------------|
| Parameter         | Random variable        | Fixed unknown constant   |
| Probability       | Degree of belief       | Long-run frequency       |
| Prior Knowledge   | Incorporated           | Not used                 |
| Uncertainty       | Posterior distribution | Confidence intervals     |
| Small Sample      | Works well             | Unreliable               |

### Applications in Big Data
- Spam filtering (Naive Bayes Classifier)
- Medical diagnosis
- Recommendation systems
- Natural language processing
- A/B testing and experimental design

### Bayesian Inference Flow

```
  Prior Belief        New Evidence         Updated Belief
   P(H)         +    P(E | H)      →       P(H | E)
   [Prior]           [Likelihood]          [Posterior]
     ↓                    ↓                    ↓
 Initial guess     Observed Data        Refined estimate
```

---

## Question 4: Different Types of Inferences in Big Data Analytics (12M)

Inference in big data analytics refers to the process of drawing conclusions, making predictions, or estimating unknown parameters from large datasets.

### 1. Statistical Inference
Drawing conclusions about a population from a sample.

- **Point Estimation**: Estimating a single value for a parameter (e.g., sample mean as estimate of population mean)
- **Interval Estimation**: Estimating a range (confidence interval) within which the true parameter lies
- **Hypothesis Testing**: Testing claims about population parameters (null vs. alternative hypothesis)

### 2. Predictive Inference
Using historical data to predict future outcomes.
- Regression models (Linear, Logistic, Polynomial)
- Time-series forecasting (ARIMA, LSTM)
- Machine learning classifiers (Random Forest, SVM, Neural Networks)

### 3. Causal Inference
Determining cause-and-effect relationships, not just correlations.
- Uses techniques like A/B testing, Randomized Control Trials (RCTs)
- Directed Acyclic Graphs (DAGs) for causal modeling
- Crucial in healthcare, policy-making, and economics

### 4. Bayesian Inference
Updating beliefs based on evidence using Bayes' theorem (detailed in Q3).
- Probabilistic inference with uncertainty quantification
- Posterior distributions used for decision-making

### 5. Frequentist Inference
Based on the frequency of outcomes in repeated experiments.
- p-values, confidence intervals, hypothesis tests
- Does not use prior beliefs

### 6. Descriptive Inference
Summarizing what is in the data:
- Mean, median, mode, variance, standard deviation
- Data visualizations (histograms, box plots)
- Understanding data distribution

### 7. Abductive Inference
Inferring the most likely explanation for observed data (best guess reasoning).
- Used in diagnostics, anomaly detection
- "The data shows spike in errors → most likely a server fault"

### 8. Deductive Inference
Drawing conclusions from general rules applied to specific cases.
- If all A are B, and X is A, then X is B
- Rule-based expert systems in AI

### 9. Inductive Inference
Generalizing from specific observations to general rules.
- Foundation of machine learning
- Training on examples → generalizing to new data

### Summary Table

| Inference Type  | Basis                     | Big Data Application              |
|-----------------|---------------------------|-----------------------------------|
| Statistical     | Sampling theory           | Population analytics               |
| Predictive      | Historical patterns       | Fraud detection, churn prediction |
| Causal          | Cause-effect              | Drug testing, policy analysis      |
| Bayesian        | Prior + Evidence          | NLP, recommendation engines       |
| Descriptive     | Data summary              | Business dashboards               |
| Abductive       | Best explanation          | Anomaly detection                 |
| Deductive       | Logic rules               | Expert systems, rule engines      |
| Inductive       | Pattern generalization    | ML model training                 |

---

## Question 5: Bootstrapping and Its Importance (12M)

### Definition
Bootstrapping is a **resampling technique** that estimates the sampling distribution of a statistic by repeatedly sampling **with replacement** from the observed dataset.

Proposed by **Bradley Efron in 1979**, it is a powerful non-parametric method that requires no assumptions about the underlying population distribution.

### Core Idea
If we have a sample of n observations, we:
1. Draw a new sample of size n **with replacement** from the original
2. Compute the statistic of interest (mean, median, variance, etc.)
3. Repeat steps 1-2 a large number of times (typically B = 1000 or more)
4. Use the distribution of computed statistics to estimate confidence intervals, standard errors, etc.

### Step-by-Step Bootstrap Process

```
Original Sample (n=5): [3, 7, 5, 9, 1]
         |
         ↓
Bootstrap Sample 1: [7, 3, 9, 3, 5]  → Statistic θ̂₁
Bootstrap Sample 2: [1, 7, 7, 9, 5]  → Statistic θ̂₂
Bootstrap Sample 3: [5, 5, 3, 7, 1]  → Statistic θ̂₃
        ...
Bootstrap Sample B: [9, 1, 3, 7, 7]  → Statistic θ̂_B
         |
         ↓
Bootstrap Distribution of θ̂
→ Estimate Standard Error, Confidence Interval, Bias
```

### Types of Bootstrap

#### 1. Non-Parametric Bootstrap
- Samples directly from observed data (no distributional assumption)
- Most commonly used form

#### 2. Parametric Bootstrap
- Assumes a distribution, fits parameters from data
- Samples from the fitted distribution

#### 3. Block Bootstrap
- For time-series or correlated data
- Resamples blocks of consecutive observations to preserve autocorrelation

#### 4. Bayesian Bootstrap
- Weights data points using Dirichlet distribution instead of resampling

### Applications of Bootstrapping

1. **Confidence Interval Estimation**: Without assuming normality
2. **Standard Error Estimation**: For complex estimators with no closed-form formula
3. **Bias Correction**: Estimate and correct bias of an estimator
4. **Hypothesis Testing**: Bootstrap-based permutation tests
5. **Machine Learning**: Bagging (Bootstrap Aggregating) for ensemble models
6. **Model Validation**: Estimate generalization error
7. **Feature Importance**: Stability analysis of selected features

### Bootstrap Confidence Interval Methods

| Method             | Description                                     |
|--------------------|-------------------------------------------------|
| Percentile         | Use 2.5th and 97.5th percentiles of bootstrap   |
| Basic (Pivot)      | Reflect bootstrap distribution around estimate  |
| BCa (Bias-Corrected)| Corrects for bias and skewness                 |
| t-Bootstrap        | Studentized intervals (most accurate)           |

### Importance in Big Data

- Works when theoretical distributions are unknown or complex
- Computationally feasible with distributed computing (Spark, Hadoop)
- Provides robust uncertainty estimates for black-box ML models
- Core component in ensemble methods (Random Forest uses bootstrap sampling)

---

## Question 6: Sampling and Sampling Distribution — Detailed Analysis (12M)

### What is Sampling?
Sampling is the process of selecting a **subset (sample)** from a larger **population** to make inferences about the whole population without examining every element.

### Why Sample?
- Population too large to study entirely (Big Data context)
- Cost and time constraints
- Destructive testing scenarios
- Real-time analytics requirements

### Types of Sampling

#### 1. Probability Sampling (Each unit has known, non-zero probability of selection)

**a. Simple Random Sampling (SRS)**
- Every member has equal probability of being selected
- Use when population is homogeneous
- Can be with or without replacement

**b. Stratified Sampling**
- Divide population into homogeneous subgroups (strata)
- Sample from each stratum proportionally
- Reduces variance, improves representation

**c. Systematic Sampling**
- Select every k-th element from ordered list
- k = Population size / Sample size

**d. Cluster Sampling**
- Divide population into clusters (geographic areas, schools)
- Randomly select entire clusters
- Cost-effective for dispersed populations

**e. Multi-Stage Sampling**
- Combines multiple sampling methods
- Used in large national surveys

#### 2. Non-Probability Sampling (Selection not random)

**a. Convenience Sampling** – Easiest to reach subjects
**b. Purposive/Judgmental Sampling** – Expert selects subjects
**c. Snowball Sampling** – Existing subjects recruit new ones
**d. Quota Sampling** – Fill quotas for subgroups

### Sampling Distribution

A **sampling distribution** is the probability distribution of a given statistic (e.g., sample mean) over many samples drawn from the same population.

#### Key Concept: Central Limit Theorem (CLT)
> "Regardless of the population distribution, the sampling distribution of the sample mean approaches a **normal distribution** as the sample size n → ∞"

```
If X₁, X₂, ..., Xₙ are i.i.d. with mean μ and variance σ²:

x̄ ~ N(μ, σ²/n) as n → ∞

Standard Error of Mean: SE = σ / √n
```

#### Properties of Sampling Distribution

| Property              | Formula / Note                              |
|-----------------------|---------------------------------------------|
| Mean of x̄             | E(x̄) = μ (unbiased)                        |
| Variance of x̄         | Var(x̄) = σ²/n                              |
| Standard Error         | SE = σ/√n                                  |
| Shape (large n)        | Approximately Normal (CLT)                  |
| Effect of larger n     | Narrower distribution, more precise         |

### Sampling Distribution Diagram

```
Population (μ, σ²)
       |
       ├──→ Sample 1 (n=30) → x̄₁
       ├──→ Sample 2 (n=30) → x̄₂
       ├──→ Sample 3 (n=30) → x̄₃
       ...
       └──→ Sample B (n=30) → x̄_B

Distribution of {x̄₁, x̄₂, ..., x̄_B}:
        ____
       /    \
      /      \
─────/        \─────   → Normal (by CLT)
    μ-SE  μ  μ+SE
```

### Sampling in Big Data Context

- **Reservoir Sampling**: For streaming data — maintain a random sample of size k from a stream of unknown total size
- **Stratified Sampling in Spark**: `DataFrame.sampleBy()` for distributed datasets
- **Weighted Sampling**: Account for imbalanced datasets in ML
- **Online Sampling**: Sampling from data arriving in real-time

### Key Terminology

| Term                | Definition                                          |
|---------------------|-----------------------------------------------------|
| Population          | Entire group of interest                            |
| Sample              | Subset selected from population                     |
| Parameter           | Numerical characteristic of population (μ, σ)      |
| Statistic           | Numerical characteristic of sample (x̄, s)          |
| Sampling Frame      | List of all elements from which sample is drawn     |
| Sampling Bias       | Systematic error due to non-representative sample  |
| Standard Error      | Standard deviation of sampling distribution        |

---

## Question 7a: Intelligent Data Analytics (6M)

### Definition
Intelligent Data Analytics (IDA) refers to the use of **artificial intelligence, machine learning, and advanced statistical techniques** to automatically extract insights, patterns, and knowledge from large and complex datasets.

### Key Components

#### 1. Machine Learning (ML)
- Supervised, Unsupervised, Reinforcement Learning
- Models learn from data without explicit programming
- Examples: Decision Trees, Neural Networks, SVM, k-Means

#### 2. Deep Learning
- Multi-layer neural networks (CNNs, RNNs, Transformers)
- Excels at image recognition, NLP, speech processing

#### 3. Natural Language Processing (NLP)
- Extracting information from unstructured text data
- Sentiment analysis, topic modeling, named entity recognition

#### 4. Predictive Analytics
- Forecasting future outcomes based on historical patterns
- Used in demand forecasting, predictive maintenance

#### 5. Prescriptive Analytics
- Not just predicts what will happen, but recommends optimal actions
- Uses optimization algorithms and simulation

#### 6. Cognitive Analytics
- Mimics human reasoning and learning
- Used in virtual assistants, cognitive computing platforms

### IDA Architecture

```
    Raw Data (Structured + Unstructured)
             ↓
    Data Ingestion & Preprocessing
             ↓
    Feature Engineering + Transformation
             ↓
    Intelligent Models (ML / DL / AI)
             ↓
    Insight Generation & Visualization
             ↓
    Decision Support & Action
```

### Applications
- **Healthcare**: Disease prediction, drug discovery
- **Finance**: Fraud detection, algorithmic trading
- **Retail**: Customer segmentation, recommendation engines
- **Manufacturing**: Predictive maintenance, quality control
- **Smart Cities**: Traffic management, energy optimization

---

## Question 7b: Analysis vs. Reporting (6M)

### Reporting
Reporting is the process of **organizing and presenting data** in a standardized, structured format to communicate what happened.

**Characteristics:**
- Descriptive (tells what happened)
- Historical data focus
- Structured, predefined formats
- Answers: "What happened? When? How many?"
- Examples: Sales reports, financial statements, dashboards

### Analysis
Analysis is the process of **examining data in depth** to understand patterns, causes, and implications to support decision-making.

**Characteristics:**
- Explanatory and exploratory
- Combines current and historical data
- Flexible, investigative approach
- Answers: "Why did it happen? What will happen? What should we do?"
- Examples: Root cause analysis, predictive modeling, cohort analysis

### Comparison Table

| Dimension          | Reporting                       | Analysis                              |
|--------------------|----------------------------------|---------------------------------------|
| **Purpose**        | Communicate facts                | Discover insights                     |
| **Orientation**    | Backward-looking                 | Forward-looking                       |
| **Output**         | Tables, charts, summaries        | Models, hypotheses, recommendations   |
| **Audience**       | Executives, stakeholders         | Analysts, data scientists             |
| **Data Type**      | Structured, known metrics        | Structured + unstructured             |
| **Frequency**      | Regular (daily, weekly)          | Ad-hoc, project-based                 |
| **Tools**          | Power BI, Tableau, Excel         | Python, R, Spark, ML platforms        |
| **Skill Required** | Low-moderate                     | High (statistics + domain expertise)  |
| **Question**       | What happened?                   | Why did it happen? What next?         |

### Flow: Reporting → Analysis

```
Data Collection
      ↓
   Reporting  ←────────────────────────┐
  (What happened?)                      │
      ↓                                 │ Feedback
   Analysis                            │
  (Why it happened?)                   │
      ↓                                │
   Insights & Decisions ───────────────┘
```

---

## Question 8: Prediction Error and Regression Techniques (12M)

### Prediction Error

Prediction error quantifies the difference between the predicted value ŷ and the actual value y.

#### Types of Prediction Error

**1. Bias**
- Systematic error: model consistently predicts too high or too low
- High bias = Underfitting (model is too simple)

**2. Variance**
- Sensitivity to fluctuations in training data
- High variance = Overfitting (model memorizes noise)

**3. Irreducible Error (Noise)**
- Inherent randomness in data that no model can remove

#### Bias-Variance Tradeoff

```
Total Error = Bias² + Variance + Irreducible Error

         High Bias           Optimal          High Variance
         (Underfitting)      Region           (Overfitting)
              |                 |                  |
Error ────────●─────────────────●──────────────────●──────
              Simple          Balanced           Complex
              Model           Model              Model
```

#### Common Error Metrics

| Metric   | Formula                             | Description                       |
|----------|-------------------------------------|-----------------------------------|
| MAE      | (1/n) Σ |yᵢ - ŷᵢ|                | Mean Absolute Error               |
| MSE      | (1/n) Σ (yᵢ - ŷᵢ)²               | Mean Squared Error                |
| RMSE     | √MSE                                | Root Mean Squared Error           |
| R²       | 1 - SS_res / SS_tot                 | Coefficient of Determination      |
| MAPE     | (1/n) Σ |(yᵢ - ŷᵢ)/yᵢ| × 100    | Mean Absolute Percentage Error    |

### Regression Techniques

#### 1. Simple Linear Regression
Models a linear relationship between one independent variable X and dependent variable Y.

```
ŷ = β₀ + β₁X + ε

β₁ = Σ(xᵢ - x̄)(yᵢ - ȳ) / Σ(xᵢ - x̄)²
β₀ = ȳ - β₁x̄
```

#### 2. Multiple Linear Regression
Multiple independent variables predicting Y:

```
ŷ = β₀ + β₁X₁ + β₂X₂ + ... + βₙXₙ + ε
```

Estimated using **Ordinary Least Squares (OLS)**: minimizes Σ(yᵢ - ŷᵢ)²

#### 3. Polynomial Regression
Extends linear regression by adding polynomial terms:
```
ŷ = β₀ + β₁X + β₂X² + β₃X³ + ... + ε
```
Captures non-linear relationships while still being a linear model (linear in coefficients).

#### 4. Ridge Regression (L2 Regularization)
Adds penalty on squared coefficients to prevent overfitting:
```
Minimize: Σ(yᵢ - ŷᵢ)² + λΣβⱼ²
```
Shrinks coefficients but doesn't eliminate them; useful when many features are correlated.

#### 5. Lasso Regression (L1 Regularization)
Adds penalty on absolute values of coefficients:
```
Minimize: Σ(yᵢ - ŷᵢ)² + λΣ|βⱼ|
```
Can shrink some coefficients to exactly zero → built-in feature selection.

#### 6. Logistic Regression
Used for binary classification (not continuous prediction):
```
P(Y=1) = 1 / (1 + e^-(β₀ + β₁X))
```
Output is a probability between 0 and 1.

#### 7. Elastic Net
Combines L1 and L2 regularization:
```
Minimize: Σ(yᵢ - ŷᵢ)² + λ₁Σ|βⱼ| + λ₂Σβⱼ²
```

#### Regression Techniques Comparison

| Method           | Handles Non-linearity | Feature Selection | Regularization | Use Case                   |
|------------------|-----------------------|-------------------|----------------|----------------------------|
| Linear           | No                    | No                | No             | Simple relationships       |
| Polynomial       | Yes                   | No                | No             | Curved patterns            |
| Ridge            | No                    | No                | L2             | Correlated features        |
| Lasso            | No                    | Yes               | L1             | Sparse models              |
| Elastic Net      | No                    | Yes               | L1 + L2        | High-dim correlated data   |
| Logistic         | No (decision boundary)| No                | Optional       | Binary classification      |

---

## Question 9: Five Characteristics of Big Data (12M)

Big Data is typically characterized by the **5Vs (or 7Vs)** model. Here are five key characteristics explained in detail:

### 1. Volume
**Definition**: The massive scale of data generated, stored, and processed.

- Data is generated from social media, IoT devices, transactions, sensors, logs
- Scale: Terabytes → Petabytes → Exabytes → Zettabytes
- Traditional databases (MySQL, Oracle) cannot store and query at this scale
- **Solutions**: HDFS (Hadoop Distributed File System), Amazon S3, Google BigQuery

**Example**: Facebook generates ~500 TB of new data every day. NASA collects terabytes per day from satellites.

### 2. Velocity
**Definition**: The speed at which data is generated, collected, and processed.

- Real-time data streams from sensors, financial markets, social media
- Two types: **Batch Processing** (process stored data in bulk) vs **Stream Processing** (process data in real-time)
- Challenges: Low-latency requirements, handling burst traffic
- **Solutions**: Apache Kafka, Apache Spark Streaming, Apache Flink

**Example**: The New York Stock Exchange generates ~1 TB of trading data per day. Twitter processes ~500M tweets daily.

### 3. Variety
**Definition**: The diversity of data types and sources.

```
Data Variety:
┌─────────────────┬────────────────────┬──────────────────┐
│   Structured    │  Semi-Structured   │  Unstructured    │
│ (10-20% of data)│  (5-10% of data)   │ (70-80% of data) │
│ SQL Tables      │ JSON, XML, YAML    │ Text, Images     │
│ Spreadsheets    │ Email headers      │ Videos, Audio    │
│ CRM data        │ HTML pages         │ Social posts     │
└─────────────────┴────────────────────┴──────────────────┘
```

- Integrating heterogeneous data sources is a major engineering challenge
- **Solutions**: Data lakes, schema-on-read approaches, NoSQL databases

### 4. Veracity
**Definition**: The quality, accuracy, and trustworthiness of data.

- Big Data often contains **noise, inconsistencies, duplicates, missing values**
- Sources of poor veracity: human error, sensor malfunction, data corruption, biased collection
- Poor data quality leads to wrong insights ("garbage in, garbage out")
- **Solutions**: Data cleansing pipelines, master data management (MDM), data lineage tracking

**Challenges**:
- Unverified social media data
- Conflicting records across multiple systems
- Data from unreliable IoT sensors

### 5. Value
**Definition**: The business or scientific worth extracted from big data analysis.

- Raw data itself is not valuable — insights derived from it are
- Value comes from: cost reduction, revenue generation, risk mitigation, improved decisions
- Not all data has equal value; organizations must prioritize high-impact data
- **ROI calculation**: Value extracted must outweigh storage, processing, and analytics costs

**Value creation chain**:
```
Raw Data → Processed Data → Information → Knowledge → Value/Decision
```

**Examples of value from Big Data**:
- Netflix saves ~$1B/year through personalized recommendations reducing churn
- Predictive maintenance in aviation saves millions by preventing failures
- Personalized medicine improves patient outcomes

---

## Question 10: Arcing Classifier and Bagging Predictors (12M)

### Background: Ensemble Learning
Both Arcing and Bagging are **ensemble learning techniques** that combine multiple models (weak learners) to produce a stronger, more accurate model.

```
Ensemble Principle:
Multiple Weak Learners + Combination Strategy = Strong Learner
```

---

### Bagging Predictors (Bootstrap Aggregating)

**Proposed by**: Leo Breiman (1996)

#### Definition
Bagging involves:
1. Creating multiple bootstrap samples from the training data
2. Training a separate model on each bootstrap sample
3. Aggregating predictions (voting for classification, averaging for regression)

#### Algorithm Steps

```
Input: Training set D = {(x₁,y₁), ..., (xₙ,yₙ)}, B = number of bootstrap samples

For b = 1 to B:
   1. Draw bootstrap sample Dᵦ of size n (with replacement) from D
   2. Train model fᵦ on Dᵦ

For prediction on new x:
   - Classification: ŷ = majority_vote{f₁(x), f₂(x), ..., f_B(x)}
   - Regression: ŷ = (1/B) Σ fᵦ(x)
```

#### Bagging Diagram

```
  Original        Bootstrap Samples       Trained Models      Aggregation
  Training   →  [D₁ (sampling w/ rep)] → Model 1 → ŷ₁ ─┐
  Data (D)   →  [D₂ (sampling w/ rep)] → Model 2 → ŷ₂ ─┤→ Vote/Average → Final ŷ
             →  [D₃ (sampling w/ rep)] → Model 3 → ŷ₃ ─┘
                         ...
```

#### Why Bagging Works
- Reduces **variance** of the model without increasing bias
- Each bootstrap sample is slightly different → diverse models
- Averaging/voting reduces overfitting
- Particularly effective for high-variance, low-bias models (e.g., deep decision trees)

#### Key Properties

| Property              | Detail                                       |
|-----------------------|----------------------------------------------|
| Resampling            | Bootstrap (with replacement)                 |
| Base Learner          | Any (commonly Decision Trees)                |
| Model Combination     | Majority vote (classification), mean (regression) |
| Goal                  | Reduce variance, improve stability           |
| Out-of-Bag (OOB) Error| ~1/3 of data not in each bootstrap sample; used for free validation |

#### Famous Application: Random Forest
Random Forest = Bagging + Random Feature Selection at each split
- Introduces additional diversity by using random subsets of features
- One of the most powerful and widely used ensemble methods

---

### Arcing Classifier (Adaptive Resampling and Combining)

**Proposed by**: Leo Breiman (1998)

#### Definition
Arcing is a generalization of boosting/bagging where models are trained on **adaptively reweighted versions** of the training data. It stands for **A**daptive **R**esampling and **C**omb**ing**.

#### Core Idea
- Unlike Bagging (random resampling), Arcing **adaptively adjusts weights** based on prediction errors
- Misclassified examples are given **higher weights** in subsequent iterations
- Forces subsequent models to focus on hard examples

#### Arcing vs. Boosting
Arcing is closely related to **AdaBoost (Adaptive Boosting)**. The key algorithmic step:

```
Initialize: w₁ = w₂ = ... = wₙ = 1/n (uniform weights)

For t = 1 to T:
   1. Train model hₜ on weighted data (sampling proportional to wᵢ)
   2. Compute weighted error: εₜ = Σ wᵢ · I(yᵢ ≠ hₜ(xᵢ))
   3. Compute model weight: αₜ = (1/2) ln((1 - εₜ)/εₜ)
   4. Update example weights:
      - If correctly classified: wᵢ ← wᵢ · e^(-αₜ)
      - If misclassified:        wᵢ ← wᵢ · e^(αₜ)
   5. Normalize weights so they sum to 1

Final prediction: H(x) = sign(Σ αₜ hₜ(x))
```

#### Arcing Diagram

```
Training Data
(all equal weights)
      ↓
 Model 1 → Some misclassifications → Increase weights of wrong samples
      ↓
 Model 2 (focuses on harder cases) → More misclassifications → Adjust weights
      ↓
 Model 3 (focuses on hardest cases)
      ↓
      ...
Final Model = Weighted combination of all models
```

#### Comparison: Bagging vs. Arcing

| Feature               | Bagging                        | Arcing                             |
|-----------------------|--------------------------------|------------------------------------|
| Resampling Strategy   | Random (bootstrap)             | Adaptive (weight-based)            |
| Model Focus           | All examples equally           | Harder examples emphasized         |
| Base Learner Training | Independent                    | Sequential                         |
| Combination           | Equal weight vote/average      | Weighted vote (by accuracy)        |
| Bias                  | No significant change          | Reduces bias                       |
| Variance              | Reduces variance               | May increase variance              |
| Overfitting Risk      | Low                            | Higher (needs regularization)      |
| Example               | Random Forest                  | AdaBoost, Gradient Boosting        |

#### Strengths and Weaknesses

**Bagging:**
- ✅ Reduces overfitting effectively
- ✅ Parallelizable (models train independently)
- ✅ Works well with high-variance base learners
- ❌ Less effective when base learner has high bias

**Arcing:**
- ✅ Can reduce both bias and variance
- ✅ Achieves higher accuracy on clean data
- ✅ Automatically focuses on hard examples
- ❌ Sensitive to noisy data and outliers
- ❌ Sequential training is slower

---

## Quick Reference Summary

| Topic                   | Key Takeaway                                                        |
|-------------------------|---------------------------------------------------------------------|
| Big Data Challenges     | 5Vs: Volume, Velocity, Variety, Veracity, Value                     |
| Nature of Data          | Structured, Semi-structured, Unstructured, Streaming                |
| SVM Inference           | Find optimal hyperplane using kernel trick + Lagrangian optimization |
| Bayesian Inference      | P(H|E) = P(E|H)·P(H) / P(E); update beliefs with evidence          |
| Types of Inference      | Statistical, Predictive, Causal, Bayesian, Descriptive, Inductive  |
| Bootstrapping           | Resample with replacement → estimate statistic distribution         |
| Sampling Distribution   | CLT: x̄ ~ N(μ, σ²/n) for large n                                   |
| Intelligent Analytics   | AI + ML + NLP for automated insight discovery                       |
| Analysis vs Reporting   | Reporting = what happened; Analysis = why + what next               |
| Prediction Error        | Bias² + Variance + Irreducible Error                               |
| Regression              | Linear → Ridge/Lasso → Logistic for different scenarios             |
| Big Data 5Vs            | Volume, Velocity, Variety, Veracity, Value                          |
| Bagging                 | Bootstrap samples + majority vote → reduce variance                 |
| Arcing                  | Adaptive reweighting + sequential training → reduce bias+variance   |

---

*End of Notes — Introduction to Big Data*
