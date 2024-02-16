/**
 * == 17679. 프렌즈4블록 ==
 * 입력 : 판의 높이 m, 폭 n, 판의 배치 정보 board
 * 출력 : 지워질 블록의 개수
 */

function solution(m, n, board) {
    const blocks = new Set();  // 지운 블록
    let answer = 0; // 지운 블록 개수

    while (isExist(m, n, board)) {
        /* 2x2 블록 찾기 */
        for (let i = 0; i < m-1; i++) {
            for (let j = 0; j < n-1; j++) {
                if (is2by2(i, j, board)) {
                    /* 블록 지우기 */
                    blocks.add(`${i} ${j}`);
                    blocks.add(`${i} ${j+1}`);
                    blocks.add(`${i+1} ${j}`);
                    blocks.add(`${i+1} ${j+1}`);
                }
            }
        }

        /* 블록 내리기 */
        blocks.forEach(block => {
            let [x, y] = block.split(' ').map(str => parseInt(str));
            for (let r = x; r > 0; r--) {
                board[r] = replaceCharAtIndex(board[r], y, board[r-1].charAt(y));
            }
            board[0] = replaceCharAtIndex(board[0], y, ' ');
        });

        answer += blocks.size;
        blocks.clear();
    }

    return answer;
}

function is2by2(x, y, board) {
    return (board[x][y] !== ' ' &&
        board[x][y] === board[x][y+1] &&
        board[x][y] === board[x+1][y] &&
        board[x][y] === board[x+1][y+1]);
}

function isExist(m, n, board) {
    for (let i = 0; i < m-1; i++) {
        for (let j = 0; j < n-1; j++) {
            if (is2by2(i, j, board)) return true;
        }
    }
    return false;
}

function replaceCharAtIndex(str, index, char) {
    return str.substring(0, index) + char + str.substring(index + 1);
}