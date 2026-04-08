Here’s a **complete, interview-focused guide to HTTP Status Codes** — explained clearly with meanings, use-cases, and when to use them in your backend 👇

---

# 🌐 What are HTTP Status Codes?

HTTP status codes are **3-digit numbers** returned by the server to indicate the result of a client request.

👉 They are grouped into **5 categories**

---

# 🟢 1xx — Informational (Rare)

* Request received, still processing

| Code | Meaning  |
| ---- | -------- |
| 100  | Continue |

👉 Rarely used in backend interviews

---

# 🟢 2xx — Success (VERY IMPORTANT ✅)

## 🔥 Most Asked Codes

| Code | Name       | Meaning             | When to Use                |
| ---- | ---------- | ------------------- | -------------------------- |
| 200  | OK         | Request successful  | GET, PUT                   |
| 201  | Created    | Resource created    | POST (signup, add product) |
| 204  | No Content | Success but no data | DELETE                     |

---

## 📌 Example

```js
res.status(200).json({ message: "Success" });
res.status(201).json({ message: "User created" });
res.status(204).send();
```

---

# 🟡 3xx — Redirection

| Code | Meaning                    |
| ---- | -------------------------- |
| 301  | Moved Permanently          |
| 302  | Found (Temporary redirect) |

👉 Mostly used in frontend/SEO, not common in APIs

---

# 🔴 4xx — Client Errors (VERY IMPORTANT ⚠️)

👉 Client made mistake

---

## 🔥 Most Important Codes

| Code | Name                 | Meaning          | Example        |
| ---- | -------------------- | ---------------- | -------------- |
| 400  | Bad Request          | Invalid input    | Missing fields |
| 401  | Unauthorized         | Not logged in    | No token       |
| 403  | Forbidden            | No permission    | Wrong role     |
| 404  | Not Found            | Resource missing | User not found |
| 409  | Conflict             | Duplicate data   | Email exists   |
| 422  | Unprocessable Entity | Validation error | Wrong format   |

---

## 📌 Examples

```js
res.status(400).json({ message: "Missing fields" });
res.status(401).json({ message: "Token required" });
res.status(403).json({ message: "Access denied" });
res.status(404).json({ message: "User not found" });
```

---

# 🔴 5xx — Server Errors (VERY IMPORTANT 💥)

👉 Server failed

---

## 🔥 Important Codes

| Code | Name                  | Meaning                     |
| ---- | --------------------- | --------------------------- |
| 500  | Internal Server Error | General error               |
| 502  | Bad Gateway           | Server got invalid response |
| 503  | Service Unavailable   | Server down                 |

---

## 📌 Example

```js
res.status(500).json({ message: "Server error" });
```

---

# 🔥 Most Important Codes for Your Project (E-Commerce)

## 🛒 Common Usage

| Scenario                      | Status Code |
| ----------------------------- | ----------- |
| Product created               | 201         |
| Login success                 | 200         |
| Wrong password                | 401         |
| Not authorized (seller issue) | 403         |
| Product not found             | 404         |
| Duplicate email               | 409         |
| Validation error              | 400         |
| Server crash                  | 500         |

---

# 🧠 Interview Tricks (VERY IMPORTANT)

## ❓ 401 vs 403

| Code | Meaning                            |
| ---- | ---------------------------------- |
| 401  | Not authenticated (login required) |
| 403  | Authenticated but not allowed      |

👉 Example:

* No token → **401**
* Wrong role → **403**

---

## ❓ 400 vs 422

| Code | Meaning                             |
| ---- | ----------------------------------- |
| 400  | Missing/invalid request             |
| 422  | Correct format but validation fails |

---

## ❓ 200 vs 201

| Code | Meaning              |
| ---- | -------------------- |
| 200  | General success      |
| 201  | New resource created |

---

# 🚀 Best Practices (INTERVIEW GOLD)

✔ Always use proper status codes
✔ Don’t always send 200 ❌
✔ Combine with meaningful messages

---

## ❌ Bad Practice

```js
res.status(200).json({ error: "User not found" });
```

## ✔ Good Practice

```js
res.status(404).json({ message: "User not found" });
```

---

# 🎯 Perfect Interview Answer (Short)

> HTTP status codes indicate the result of a request.
> 2xx = success, 4xx = client error, 5xx = server error.
> Common ones: 200, 201, 400, 401, 403, 404, 500.

---

# 🔥 Quick Memory Trick

👉 **2 → Success**
👉 **4 → Client mistake**
👉 **5 → Server mistake**

---

If you want next level 🚀
I can:
✅ Give **real API design examples using correct status codes**
✅ Create **mock interview questions (very useful for TCS)**
✅ Review your project APIs and fix status code mistakes
