/**
 * == 12904. 가장 긴 팰린드롬 ==
 * 입력 : 문자열 s
 * 출력 : s의 부분문자열 중 가장 긴 팰린드롬의 길이
 */

function solution(s)
{
    let answer = 0; // 가장 긴 팰린드롬 길이
    const str = s.split("");

    /* 팰린드롬 찾기 */
    if (str.length === 1) return 1;
    for (let i = 0; i < str.length; i++) {
        answer = Math.max(answer, findPalindrome(str, i, i+1), findPalindrome(str, i, i));
    }

    return answer;
}

function findPalindrome(str, left, right) {
    while (left >= 0 && right < str.length
    && str[left] === str[right]) {
        left--; right++;
    }

    return right - left - 1;
}