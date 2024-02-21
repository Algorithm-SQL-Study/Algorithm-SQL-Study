/**
 * == 131129. 카운트 다운 ==
 * 입력 : 목표 점수 target
 * 출력 : [최선의 경우 던질 다트 수, 그 때의 싱글+불을 맞춘 횟수]
 */

function solution(target) {
    const dp = [];
    dp.push([0, 0]);
    for (let i = 1; i <= target; i++) {
        dp.push([100000, 0]);    // 던질 다트 수, 싱글+불 맞춘 횟수
    }

    for (let i = 0; i < target; i++) {
        if (i + 50 <= target) {   // bull
            if (dp[i][0] + 1 < dp[i + 50][0]) {
                dp[i + 50][0] = dp[i][0] + 1;
                dp[i + 50][1] = dp[i][1] + 1;
            } else if (dp[i][0] + 1 === dp[i + 50][0]) {
                dp[i + 50][1] = Math.max(dp[i + 50][1], dp[i][1] + 1);
            }
        }

        for (let j = 1; j <= 20; j++) {
            for (let k = 1; k <= 3; k++) {  // single, double, triple
                if (i+j*k <= target) {
                    if (dp[i][0] + 1 < dp[i+j*k][0]) {
                        dp[i+j*k][0] = dp[i][0] + 1;
                        dp[i+j*k][1] = k === 1 ? dp[i][1] + 1 : dp[i][1];
                    } else if (dp[i][0] + 1 === dp[i+j*k][0]) {
                        dp[i+j*k][1] = k === 1
                            ? Math.max(dp[i+j*k][1], dp[i][1] + 1)
                            : Math.max(dp[i+j*k][1], dp[i][1]);
                    }
                }
            }
        }
    }

    return dp[target];
}