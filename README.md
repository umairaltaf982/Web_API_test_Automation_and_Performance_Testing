# Project Documentations
- [Anas Altaf - F223639 Documentation](/docs/README.html)
- [Umair Altaf - F223737 Documentation](/docs/README.html)

# API Testing & Performance Engineering Project

## 📋 Overview
Comprehensive API testing suite combining functional automation, performance testing, and load testing using multiple frameworks.

## 🏗️ Architecture
- REST API Testing (REST Assured)
- Performance Testing (Apache JMeter)
- Load Testing (k6)
- Test Automation (TestNG)
- CI/CD Integration

## 🛠️ Technology Stack
- Java 17
- REST Assured
- TestNG
- Apache JMeter
- k6
- Maven
- HTML Reports
- Jenkins (CI/CD)

## 📁 Project Structure
```
project/
├── src/
│   └── test/
│       ├── java/
│       │   └── com.restful01/
│       │       ├── models/
│       │       ├── tests/
│       │       └── utils/
│       └── k6/
│           ├── scripts/
│           └── scenarios/
├── load-testing/
│   ├── jmeter/
│   │   ├── scripts/
│   │   └── results/
│   └── k6/
│       ├── scripts/
│       └── results/
└── docs/
```

## 🧪 Test Scenarios

### REST API Tests
```javascript
// Example API Tests
GET    /api/objects     // List all objects
POST   /api/objects     // Create new object
PUT    /api/objects/:id // Update object
DELETE /api/objects/:id // Delete object
```

### Performance Tests (JMeter)
- Load Test: 50 concurrent users, 300s duration
- Stress Test: Ramp up to 100 users
- Endurance Test: 24-hour sustained load

### k6 Load Tests
```javascript
export default function() {
  // Load test scenarios
  group('API Tests', () => {
    http.get('http://api.example.com/objects');
    http.post('http://api.example.com/objects', payload);
  });
}
```

## 🚀 Setup & Execution

### Prerequisites
- Java 17+
- Node.js 14+
- k6
- Apache JMeter
- Maven

### Installation
```bash
# Install k6
winget install k6

# Install dependencies
mvn clean install
```

### Running Tests

#### REST Assured Tests
```bash
mvn test -Dtest=ApiTests
```

#### JMeter Tests
```bash
jmeter -n -t scripts/RestfulAPI_TestPlan.jmx -l results/results.jtl -e -o results/dashboard
```

#### k6 Tests
```bash
k6 run scripts/load-test.js
```

## 📊 Reports & Monitoring

### Test Reports
- REST Assured: `target/surefire-reports`
- JMeter: `load-testing/results/dashboard`
- k6: `load-testing/k6/results`

### Metrics
- Response Times
- Throughput
- Error Rates
- Virtual Users
- Resource Utilization

## 🔄 CI/CD Integration
```yaml
pipeline:
  stages:
    - functional-tests
    - performance-tests
    - load-tests
    - report-generation
```

## 📚 Documentation
- Project Overview
- API Documentation
- [Test Reports](docs/README.html)

## 🤝 Contributing
1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Create Pull Request

## 📝 License
MIT License