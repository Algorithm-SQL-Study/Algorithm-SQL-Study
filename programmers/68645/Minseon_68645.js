/**
 * == 68645. 삼각 달팽이 ==
 * 입력 : 정수 n
 * 출력 : 밑변의 길이와 높이가 n인 삼각형에서 달팽이 채우기를 진행한 결과
 */

function solution(n) {
    const answer = Array(n).fill().map((_, i) => Array(i+1).fill(0));   // 0으로 채워진 밑변의 길이와 높이가 n인 삼각형 배열
    const total = n * (n+1) / 2;

    let [i, j, cnt] = [0, 0, 1];
    while (cnt <= total) {
        /* 삼각형 왼쪽 변 */
        while (i < n && !answer[i][j]) {
            answer[i++][j] = cnt++;
        }
        i--; j++;       // [n-1][1] 위치로 이동

        /* 삼각형 밑변 */
        while (j < n && !answer[i][j]) {
            answer[i][j++] = cnt++;
        }
        i--; j -= 2;    // [n-2][n-2] 위치로 이동

        /* 삼각형 오른쪽 변 */
        while (i > 0 && j > 0 && !answer[i][j]) {
            answer[i--][j--] = cnt++;
        }
        i += 2; j++;    // 안쪽 삼각형 시작점으로 이동
    }

    return answer.flat();   // 2차원 -> 1차원
}