/**
 * == 12941. 최솟값 만들기 ==
 * 입력 : 길이가 같은 배열 A, B
 * 출력 : 누적된 최솟값
 */

function solution(A,B){
    let answer = 0;

    A.sort((a,b) => a - b); // 오름차순 정렬
    B.sort((a,b) => b - a); // 내림차순 정렬

    A.forEach((a, idx) => {
        answer += a * B[idx];
    });

    return answer;
}