/**
 * == 42579. 베스트앨범 ==
 * 입력 : 노래의 장르 배열 genres, 노래별 재생 횟수 배열 plays
 * 출력 : 베스트 앨범에 들어갈 노래의 고유 번호
 */

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int songs = genres.length;  // 총 곡의 개수

        /* hashMap 생성 */
        Map<String, Integer> genrePlaysMap = new HashMap<>();   // 장르별 재생횟수
        Map<Integer, String> songGenreMap = new HashMap<>();    // 노래(고유번호)별 장르

        for (int i = 0; i < songs; i++) {
            genrePlaysMap.put(genres[i], plays[i] + genrePlaysMap.getOrDefault(genres[i], 0));
            songGenreMap.put(i, genres[i]);
        }

        /* 장르별 재생횟수 hashMap 정렬 */
        List<Map.Entry<String, Integer>> genrePlaysList = new ArrayList<>(genrePlaysMap.entrySet());
        Collections.sort(genrePlaysList, Collections.reverseOrder(Map.Entry.comparingByValue()));

        List<String> sortedGenres = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : genrePlaysList) {
            sortedGenres.add(entry.getKey());
        }

        /* 베스트 앨범 */
        List<Integer> bestAlbum = new ArrayList<>();

        for (String genre : sortedGenres) {
            int first = -1; int second = -1;                            // 베스트 두 곡의 고유번호

            for (int i = 0; i < songs; i++) {
                if (!genre.equals(songGenreMap.get(i))) continue;       // 장르가 다르면 continue
                if (first == -1) {
                    first = i;
                    continue;
                }

                if (plays[i] > plays[first]) {                          // 가장 많이 재생한 곡인가?
                    second = first;
                    first = i;
                } else if (plays[i] == plays[first]) {
                    if (first > i) {                                    // 고유번호가 낮은 노래 우선
                        second = first;
                        first = i;
                    } else second = i;
                } else if (second == -1 || plays[i] > plays[second])    // 두번째로 많이 재생한 곡인가?
                    second = i;
                else if (second == -1 || plays[i] == plays[second])
                    second = i < second ? i : second;                   // 고유번호가 낮은 노래 우선
            }

            bestAlbum.add(first);
            if (second != -1 && first != second) bestAlbum.add(second); // 해당 장르에 곡이 한 곡 밖에 없는 경우
        }

        return bestAlbum.stream().mapToInt(Integer::intValue).toArray();
    }
}