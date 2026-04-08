Here’s a **deep, interview-ready explanation** of **JWT (JSON Web Token)** and **bcrypt**, focused on concepts, internal working, and real-world usage 👇

---

# 🔐 PART 1: JWT (JSON Web Token)

## 📌 What is JWT?

JWT is a **compact, URL-safe token** used for **authentication & authorization**.

👉 Instead of storing sessions on server, JWT enables **stateless authentication**

---

## 🧠 Structure of JWT (VERY IMPORTANT)

A JWT has **3 parts**:

```
HEADER.PAYLOAD.SIGNATURE
```

---

## 🔹 1. Header

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

* `alg` → algorithm used for signing
* `typ` → token type

---

## 🔹 2. Payload (Data)

```json
{
  "id": "12345",
  "role": "user",
  "exp": 1712345678
}
```

### 💡 Contains:

* User data (id, email, role)
* `exp` → expiry time

👉 IMPORTANT:
❌ Not encrypted
✔ Only encoded (Base64)

---

## 🔹 3. Signature (Security Layer)

```
HMACSHA256(
  base64UrlEncode(header) + "." + base64UrlEncode(payload),
  SECRET_KEY
)
```

---

## 🔥 Internal Working

### Step-by-step:

1. User logs in
2. Server creates JWT:

   ```js
   jwt.sign({ id: user._id }, SECRET)
   ```
3. Token sent to client
4. Client stores token (localStorage/cookie)
5. Client sends token in every request:

   ```
   Authorization: Bearer <token>
   ```
6. Server verifies:

   ```js
   jwt.verify(token, SECRET)
   ```

---

## 🔥 Stateless Authentication

### Traditional (Session-Based):

* Server stores session
* Needs memory/database

### JWT:

* No storage on server
* Token itself contains identity

👉 Scalable & fast

---

## 🔥 Advantages

✔ Stateless (no DB lookup)
✔ Scalable (good for microservices)
✔ Fast authentication

---

## ⚠️ Disadvantages

❌ Cannot revoke easily
❌ Large payload → slower
❌ If stolen → full access

---

## 🔥 Interview Questions

### ❓ JWT vs Session?

| Feature     | JWT    | Session |
| ----------- | ------ | ------- |
| Storage     | Client | Server  |
| Scalability | High   | Low     |
| Security    | Medium | High    |

---

### ❓ Why use expiry (`exp`)?

✔ Prevent token misuse

---

### ❓ Where to store JWT?

* HttpOnly cookie (best)
* localStorage (less secure)

---

### ❓ What is Bearer token?

```
Authorization: Bearer <JWT>
```

---
```Js
const generateToken = (id) => {
    return jwt.sign({ id }, process.env.JWT_SECRET, {
        expiresIn: process.env.JWT_EXPIRES_IN,
    });
}


const decoded = jwt.verify(token, process.env.JWT_SECRET);
                req.user = await User.findById(decoded.id).select("-password");
                next();
```
---

# 🔐 PART 2: bcrypt (Password Hashing)

## 📌 What is bcrypt?

bcrypt is a **password hashing algorithm** used to securely store passwords.

👉 Converts:

```
password → hashed_password
```

---

## 🧠 Why Hashing?

### ❌ Wrong:

Store plain password

```
123456
```

### ✔ Correct:

```
$2b$10$XyZ...hashedvalue
```

---

## 🔥 Hashing vs Encryption

| Feature    | Hashing   | Encryption    |
| ---------- | --------- | ------------- |
| Reversible | ❌ No      | ✔ Yes         |
| Use case   | Passwords | Data transfer |

---

## 🔥 How bcrypt Works Internally

### Step 1: Salt Generation

* Random string added to password

### Step 2: Hashing

```
hash = bcrypt(password + salt)
```

---

## 🔥 Example

```js
const hashed = await bcrypt.hash("123456", 10);
```

### Output:

```
$2b$10$abcxyz.......
```

---

## 🔥 Salt Rounds

```js
bcrypt.hash(password, 10)
```

### 💡 Meaning:

* 10 → number of hashing iterations
* Higher = more secure but slower

---

## 🔥 Password Verification

```js
bcrypt.compare(inputPassword, storedHash)
```

### 💡 Why not direct comparison?

❌

```js
input === storedHash
```

✔

```js
bcrypt.compare()
```

```js
userSchema.pre("save", async function(next){
    if(this.isModified("password")){
        this.password = await bcrypt.hash(this.password, 10);
    }
    next();
});

userSchema.methods.comparePassword = async function(pass){
    return await bcrypt.compare(pass, this.password);
}
```

---

## 🔥 Interview Questions

### ❓ Why bcrypt over MD5/SHA?

✔ bcrypt is slow → prevents brute force
✔ built-in salt

---

### ❓ What is salting?

✔ Adding random value to password before hashing

---

### ❓ Why hashing is one-way?

✔ Cannot retrieve original password

---

---

# 🔥 JWT + bcrypt Combined Flow (VERY IMPORTANT)

## 🧠 Complete Authentication Flow:

### 1. Register

* User enters password
* bcrypt hashes it
* Stored in DB

---

### 2. Login

* User enters password
* bcrypt compares
* If valid → generate JWT

---

### 3. Access Protected Routes

* Client sends JWT
* Server verifies
* Grants access

---

# 🔥 Real Interview Scenario Question

### ❓ What happens if JWT is stolen?

✔ Attacker can access until expiry
✔ Solution:

* Use short expiry
* Use refresh tokens
* Use HTTPS

---

### ❓ Can we decode JWT?

✔ Yes (Base64)
❌ But cannot modify (signature fails)

---

### ❓ Why not store password in JWT?

✔ Security risk

---

# 🚀 PRO TIPS (Interview Gold)

✔ Never store plain password
✔ Always use `bcrypt.compare()`
✔ Keep JWT payload minimal
✔ Use expiry (`exp`)
✔ Store secret in `.env`

---

If you want next level 🚀
I can:
✅ Draw **full authentication diagram (very helpful for viva)**
✅ Give **real interview answers (1–2 line crisp)**
✅ Add **Refresh Token system (advanced topic, high chance in interviews)**
