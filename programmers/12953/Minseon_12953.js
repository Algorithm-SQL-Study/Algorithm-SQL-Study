/**
 * == 12953. N개의 최소공배수 ==
 * 입력 : n개의 숫자를 담은 배열 arr
 * 출력 : 이 수들의 최소공배수
 */

function solution(arr) {
    let answer = arr[0];
    for (let i = 1; i < arr.length; i++) {
        answer = lcm(answer, arr[i]);
    }

    return answer;
}

/* 두 수의 최대공약수 */
function gcd(a, b) {
    if (b === 0) {
        return a;
    } else {
        return gcd(b, a % b);
    }
}

/* 두 수의 최소공배수 */
function lcm(a, b) {
    return (a * b) / gcd(a, b);
}