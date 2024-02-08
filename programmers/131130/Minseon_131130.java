/**
 * == 131130. 혼자 놀기의 달인 ==
 * 입력 : 상자 안에 들어있는 카드 번호가 순서대로 담긴 배열 cards
 * 출력 : 이 게임에서 얻을 수 있는 최고 점수
 */

class Solution {
    public int solution(int[] cards) {
        int maxCard1 = 0; int maxCard2 = 0;

        /* 카드 그룹 찾기 */
        boolean[] visited = new boolean[cards.length];
        for (int i = 0; i < cards.length; i++) {
            if (visited[i]) continue;
            int cnt = dfs(i, 0, cards, visited);
            if (cnt >= maxCard1) {
                maxCard2 = maxCard1;
                maxCard1 = cnt;
            } else if (cnt >= maxCard2) {
                maxCard2 = cnt;
            }
        }

        return maxCard1 * maxCard2;
    }

    public int dfs(int idx, int count, int[] cards, boolean[] visited) {
        if (visited[idx]) return count;
        visited[idx] = true;
        count = dfs(cards[idx] - 1, count + 1, cards, visited);
        return count;
    }
}