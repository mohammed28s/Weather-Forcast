# 🌤️ Weather API Testing Guide

## Quick Start: Test the API Instantly

### 🎯 Ready-to-Use Test Endpoints

Copy and paste these URLs to test the API immediately:

```bash
# 🏙️ Dubai
curl "http://localhost:8080/api/weather/current?lat=25.2048&lon=55.2708"

# 🗼 Tokyo  
curl "http://localhost:8080/api/weather/current?lat=35.6895&lon=139.6917"

# 🇬🇧 London
curl "http://localhost:8080/api/weather/current?lat=51.5072&lon=-0.1276"

# 🇰🇷 Seoul
curl "http://localhost:8080/api/weather/current?lat=37.5665&lon=126.9780"
```

---

## 📋 API Reference

### **Endpoint**
```
GET /api/weather/current
```

### **Parameters**
| Parameter | Type | Required | Description | Example |
|-----------|------|----------|-------------|---------|
| `lat` | number | ✅ | Latitude coordinate | `25.2048` |
| `lon` | number | ✅ | Longitude coordinate | `55.2708` |

---

## 🧪 Testing Methods

### **1. Using Browser**
Simply open these URLs in your browser:
```
http://localhost:8080/api/weather/current?lat=25.2048&lon=55.2708
```

### **2. Using curl (Command Line)**
```bash
curl "http://localhost:8080/api/weather/current?lat=25.2048&lon=55.2708"
```

### **3. Using JavaScript Fetch**
```javascript
fetch('http://localhost:8080/api/weather/current?lat=25.2048&lon=55.2708')
  .then(response => response.json())
  .then(data => console.log(data));
```

### **4. Using Postman**
- **Method:** GET
- **URL:** `http://localhost:8080/api/weather/current`
- **Params:**
    - Key: `lat`, Value: `25.2048`
    - Key: `lon`, Value: `55.2708`

---

## 🌍 Test City Coordinates

| City | Latitude | Longitude |
|------|----------|-----------|
| **Dubai** | 25.2048 | 55.2708 |
| **Tokyo** | 35.6895 | 139.6917 |
| **London** | 51.5072 | -0.1276 |
| **Seoul** | 37.5665 | 126.9780 |

---

## 🔗 External API Reference

This service integrates with the Open-Meteo API:

**Base URL:**
```bash
https://api.open-meteo.com/v1/forecast?latitude=51.5&longitude=-0.1&current=temperature_2m,wind_speed_10m
```

---

## ❓ Common Issues

**❌ "Connection refused"**
- Make sure the application is running on port 8080
- Check if another service is using the same port

**❌ "404 Not Found"**
- Verify the endpoint path: `/api/weather/current`
- Check parameter names: `lat` and `lon` (not `latitude`/`longitude`)

**✅ Expected Response Format:**
```json
{
  "temperature": 22.5,
  "conditions": "clear",
  "humidity": 65,
  "windSpeed": 5.2
}
```

---

## 🚀 Quick Validation

Test if your API is working with this one-liner:

```bash
curl -s "http://localhost:8080/api/weather/current?lat=25.2&lon=55.3" | grep -q "temperature" && echo "✅ API is working!" || echo "❌ API is down"
```

**Happy testing!** 🎉