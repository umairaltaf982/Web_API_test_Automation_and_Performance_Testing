//to run: k6 run auth-test.js
//to generate report: k6 run --out json=../../results/reports/auth-test.json auth-test.js


import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
stages: [
        { duration: '30s', target: 50 },
        { duration: '1m', target: 50 },
        { duration: '30s', target: 0 },
        ],
        };

export default function () {
    const url = 'https://restful-booker.herokuapp.com/auth';
    const payload = JSON.stringify({
            username: 'admin',
            password: 'password123',
    });

    const params = {
            headers: {
        'Content-Type': 'application/json',
    },
    };

    const res = http.post(url, payload, params);

    check(res, {
            'status is 200': (r) => r.status === 200,
            'token exists': (r) => r.json('token') !== undefined,
    });

    sleep(1);
}
