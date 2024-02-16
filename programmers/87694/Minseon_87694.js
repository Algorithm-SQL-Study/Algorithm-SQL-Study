/**
 * == 87694. 아이템 줍기 ==
 * 입력 : 직사각형 정보 배열 rectangle, 초기 캐릭터 위치 characterX, characterY, 아이템 위치 itemX, itemY
 * 출력 : 캐릭터가 아이템을 줍기 위해 이동해야 하는 가장 짧은 거
 */

function solution(rectangle, characterX, characterY, itemX, itemY) {
    let maxX = 0; let maxY = 0;
    rectangle.forEach(rect => {
        const [lx, ly, rx, ry] = rect;
        maxX = Math.max(rx*2, maxX);
        maxY = Math.max(ry*2, maxY);
    })

    /* 그래프 초기화 */
    const route = [];
    for (let i = 0; i < maxY+2; i++) {
        route.push(new Array(maxX+2).fill(0));
    }

    /* 직사각형 채우기 */
    rectangle.forEach(rect => {
        const [lx, ly, rx, ry] = rect;
        for (let i = ly*2; i <= ry*2; i++) {
            for (let j = lx*2; j <= rx*2; j++) {
                route[i][j] = 1;
            }
        }
    })

    /* 테두리 찾기 */
    for (let i = 1; i <= maxY; i++) {
        for (let j = 1; j <= maxX; j++) {
            for (let dy = -1; dy < 2; dy++) {
                for (let dx = -1; dx < 2; dx++) {
                    if (route[i][j] === 1 && route[i+dy][j+dx] === 0) {
                        route[i][j] = 2;
                        break;
                    }
                }
            }
        }
    }

    /* bfs */
    const dirs = [[1, 0], [0, 1], [-1, 0], [0, -1]];
    const queue = [[characterY*2, characterX*2, 0]];

    while (queue.length > 0) {
        const [y, x, moves] = queue.shift();
        route[y][x] = 1;
        if (y === itemY*2 && x === itemX*2) return Math.trunc(moves / 2);

        dirs.forEach(dir => {
            const [dy, dx] = dir;
            const yy = y+dy; const xx = x+dx;
            (route[yy][xx] === 2) && queue.push([yy, xx, moves+1]);
        })
    }

    return 0;
}