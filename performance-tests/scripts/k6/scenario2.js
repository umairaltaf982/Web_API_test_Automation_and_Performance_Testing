//to run: k6 run scenario2.js

import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '1m', target: 200 },  // Ramp-up to 200 users
        { duration: '3m', target: 500 }, // Stay at 1000 users
        { duration: '1m', target: 0 },    // Ramp-down
    ],
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

    sleep(0.5); // Simulate user think time
}
