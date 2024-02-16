/**
 * == 12951. JadenCase 문자열 만들기 ==
 * 입력 : 문자열 s
 * 출력 : s를 JadenCase로 바꾼 문자열
 */

function solution(s) {
    let answer = '';
    let str = s.split('');

    str.forEach((c, index) => {
        if (c === " " || c.charCodeAt(0) < 58) answer += c; // 숫자, 공백은 그대로
        else isFirstChar(str, index) ?
            answer += c.toUpperCase() : answer += c.toLowerCase();
    })

    return answer;
}

/* 첫번째 문자 or 공백 다음 문자 */
function isFirstChar(str, index) {
    if (index === 0) return true;
    else if (str[index-1] === ' ' && str[index] !== ' ') return true;
    return false;
}