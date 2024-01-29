/**
 * == 64064. 불량 사용자 ==
 * 입력 : 이벤트 응모자 아이디 목록 user_id, 불량 사용자 아이디 목록 banned_id
 * 출력 : 제재 아이디 목록의 경우의 수 개수
 */

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

class Solution {
    HashSet<List<String>> idList = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        boolean[] visited = new boolean[user_id.length];

        dfs(0, user_id, banned_id, visited, new ArrayList<>());

        return idList.size();
    }

    /* 순열 */
    public void dfs(int idx, String[] user_id, String[] banned_id, boolean[] visited, List<String> ids) {
        if (idx == banned_id.length) {
            Collections.sort(ids);
            idList.add(new ArrayList<>(ids));
            return;
        }

        for (int j = 0; j < user_id.length; j++) {
            if (!visited[j] && compareId(user_id[j], banned_id[idx])) {
                visited[j] = true;
                ids.add(user_id[j]);
                dfs(idx+1, user_id, banned_id, visited, ids);
                ids.remove(user_id[j]);
                visited[j] = false;
            }
        }
    }

    public boolean compareId(String u_id, String b_id) {
        if (u_id.length() != b_id.length()) return false;   // 길이
        for (int i = 0; i < u_id.length(); i++) {      // 문자열 비교
            if (b_id.charAt(i) == '*') continue;
            if (u_id.charAt(i) != b_id.charAt(i)) return false;
        }
        return true;
    }
}