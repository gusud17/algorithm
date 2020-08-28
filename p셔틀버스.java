package com.gusud;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class p셔틀버스 {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public String solution(int n, int t, int m, String[] timetable) {
        Queue<LocalTime> crews = organizeTimetable(timetable);
        List<Shuttle> shuttles = getShuttles(n, t);

        for (Shuttle shuttle : shuttles) {
            while (!crews.isEmpty()  && shuttle.canRide(crews.peek(), m)) {
                shuttle.ride(crews.poll());
            }

            if (crews.isEmpty()) break;
        }

        LocalTime answer = shuttles.get(shuttles.size() - 1).timeToRide(m);
        return answer.format(dateTimeFormatter);
    }

    private Queue<LocalTime> organizeTimetable(String[] timetable) {
        return Arrays.stream(timetable)
                .filter(t -> !t.equals("24:00"))
                .map(LocalTime::parse)
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private List<Shuttle> getShuttles(int totalNumber, int timeInterval) {
        LocalTime nine = LocalTime.of(9, 0);

        return IntStream.range(0, totalNumber)
                .mapToObj(i -> nine.plusMinutes(i * timeInterval))
                .map(Shuttle::new)
                .collect(Collectors.toList());
    }

    class Shuttle {
        private final LocalTime departureTime;
        private final List<LocalTime> crews;

        public Shuttle(LocalTime departureTime) {
            this.departureTime = departureTime;
            crews = new ArrayList<>();
        }

        public boolean canRide(LocalTime crew, int maxPassenger) {
            return haveSeat(maxPassenger) &&
                    !crew.isAfter(departureTime);
        }

        public void ride(LocalTime passenger) {
            crews.add(passenger);
        }

        public boolean haveSeat(int maxPassenger) {
            return maxPassenger > crews.size();
        }

        public LocalTime timeToRide(int maxPassenger) {
            if (crews.size() < maxPassenger) {
                return departureTime;
            }
            return crews.get(crews.size() - 1).minusMinutes(1);
        }
    }
}
