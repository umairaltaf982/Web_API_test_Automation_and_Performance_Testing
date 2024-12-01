//to run: k6 run scenario2.js
//to generate report: k6 run --out json=../../results/reports/scenario2.json scenario2.js


import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '1m', target: 200 },
        { duration: '3m', target: 500 },
        { duration: '1m', target: 0 },
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

    sleep(0.5);
}
