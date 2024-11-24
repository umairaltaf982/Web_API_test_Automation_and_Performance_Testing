//to run: k6 run scenario1.js
//to generate report: k6 run --out json=../../results/reports/scenario1.json scenario1.js


import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 50,           // Number of virtual users
    duration: '1m',    // Test duration
};

export default function () {
    const res = http.post('https://restful-booker.herokuapp.com/auth', JSON.stringify({
        username: 'admin',
        password: 'password123',
    }), {
        headers: { 'Content-Type': 'application/json' },
    });

    check(res, {
        'status is 200': (r) => r.status === 200,
        'token exists': (r) => r.json('token') !== undefined,
    });

    sleep(1); // Simulate user think time
}
