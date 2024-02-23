/**
 * == 154540. 무인도 여행 ==
 * 입력 : 지도를 나타내는 문자열 배열 maps
 * 출력 : 각 섬에서 최대 머무를 수 있는 일 수 오름차순 배열
 */

const graph = [];
const visited = []; // 방문 여부
let days = 0;

function solution(maps) {
    maps.forEach(m => graph.push(m.split("")));
    let islands = true;
    for (let i = 0; i < graph.length; i++) {
        for (let j = 0; j < graph[0].length; j++) {
            if (graph[i][j] !== 'X') {
                islands = false; break;
            }
        }
    }
    if (islands) return [-1];

    const answer = [];
    for (let i = 0; i < graph.length; i++) {
        const temp = [];
        for (let j = 0; j < graph[0].length; j++) {
            temp.push(false);
        }
        visited.push(temp);
    }

    /* 무인도 탐색 */
    for (let i = 0; i < graph.length; i++) {
        days = 0;
        for (let j = 0; j < graph[0].length; j++) {
            if (graph[i][j] !== 'X' && !visited[i][j]) {
                dfs(i, j);
                answer.push(days);
                days = 0;
            }
        }
    }

    return answer.sort((a, b) => a - b);
}

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

/* dfs */
function dfs(i, j) {
    let x, y;
    visited[i][j] = true;
    days += parseInt(graph[i][j]);

    for (let c = 0; c < 4; c++) {
        x = i + dx[c]; y = j + dy[c];
        if (x >= 0 && x < graph.length
            && y >= 0 && y < graph[0].length
            && !visited[x][y] && graph[x][y] !== 'X') {
            dfs(x, y);
        }
    }
}