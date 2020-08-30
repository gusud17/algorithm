import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class p캐시 {
    public int solution(int cacheSize, String[] cities) {
        List<String> cache = new LinkedList<>();

        int answer = cities.length;
        for (String city : cities) {
            city = city.toLowerCase();

            if (cache.contains(city)) {
                cache.remove(city);
            } else {
                answer += 4;
                if (cacheSize > 0 && cache.size() >= cacheSize) {
                    cache.remove(0);
                }
            }
            
            if (cache.size() < cacheSize) cache.add(city);
        }

        return answer;
    }
}