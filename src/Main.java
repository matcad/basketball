

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static String nbaCup(String resultSheet, String toFind) {
        String[] results = resultSheet.split(",");
        HashMap<String, Integer> throwsGained = new HashMap<>();
        HashMap<String, Integer> throwsLost = new HashMap<>();
        HashMap<String, Integer> matchPoints = new HashMap<>();
        HashMap<String, Integer> wins = new HashMap<>();
        HashMap<String, Integer> draws = new HashMap<>();
        HashMap<String, Integer> losses = new HashMap<>();
        for (int i = 0; i < results.length; i++) {
            String[] result = results[i].split(" ");
            String team = "";
            String team1 = "";
            int points = 0;
            int pointsGained = 0;
            int pointsLost = 0;
            int points1 = 0;
            int points1Gained = 0;
            int points1Lost = 0;
            int k = 0;
            int pkt = 0;
            int pkt1 = 0;
            int w = 0;
            int w1 = 0;
            int d = 0;
            int d1 = 0;
            int l = 0;
            int l1 = 0;
            double pointWithDot = 0;
            double pointWithDot1 = 0;

            for (int j = 0; j < result.length; j++) {
                if (!result[j].matches("[0-9]+") && (!result[j].contains("."))) {
                    team += result[j] + " ";
                    k = j + 1;
                } else break;
            }

            for (int j = k; j < result.length; j++) {
                if (result[j].contains(".")) {
                    pointWithDot = Double.valueOf(result[j]);
                    k = j + 1;
                } else break;
            }

            for (int j = k; j < result.length; j++) {
                if (result[j].matches("[0-9]+")) {
                    points = Integer.valueOf(result[j]);
                    k = j + 1;
                } else break;
            }

            for (int j = k; j < result.length; j++) {
                if (!result[j].matches("[0-9]+") && (!result[j].contains("."))) {
                    team1 += result[j] + " ";
                    k = j + 1;
                } else break;
            }

            for (int j = k; j < result.length; j++) {
                if (result[j].contains(".")) {
                    pointWithDot1 = Double.valueOf(result[j]);
                    k = j + 1;
                } else break;
            }

            for (int j = k; j < result.length; j++) {
                if (result[j].matches("[0-9]+")) {
                    points1 = Integer.valueOf(result[j]);
                } else break;
            }
            team = team.trim();
            team1 = team1.trim();

            if (pointWithDot != 0 && pointWithDot1 == 0)
                return "Error(float number):" + team + " " + pointWithDot + " " + team1 + " " + points1;
            else if (pointWithDot == 0 && pointWithDot1 != 0)
                return "Error(float number):" + team + " " + points + " " + team1 + " " + pointWithDot1;
            else if (pointWithDot != 0 && pointWithDot1 != 0)
                return "Error(float number):" + team + " " + pointWithDot + " " + team1 + " " + pointWithDot1;


            if (throwsGained.containsKey(team)) {
                if (points > points1) {
                    pkt = matchPoints.get(team) + 3;
                    matchPoints.put(team, pkt);
                    w = wins.get(team) + 1;
                    wins.put(team, w);
                } else if (points == points1) {
                    pkt = matchPoints.get(team) + 1;
                    matchPoints.put(team, pkt);
                    d = draws.get(team) + 1;
                    draws.put(team, d);
                } else {
                    pkt = matchPoints.get(team);
                    matchPoints.put(team, pkt);
                    l = losses.get(team) + 1;
                    losses.put(team, l);
                }
                pointsGained = throwsGained.get(team) + points;
                pointsLost = throwsLost.get(team) + points1;
                throwsGained.put(team, pointsGained);
                throwsLost.put(team, pointsLost);
            } else {
                if (points > points1) {
                    matchPoints.put(team, 3);
                    wins.put(team, 1);
                    draws.put(team, 0);
                    losses.put(team, 0);
                } else if (points < points1) {
                    matchPoints.put(team, 0);
                    wins.put(team, 0);
                    draws.put(team, 0);
                    losses.put(team, 1);
                } else {
                    matchPoints.put(team, 1);
                    wins.put(team, 0);
                    draws.put(team, 1);
                    losses.put(team, 0);
                }
                throwsGained.put(team, points);
                throwsLost.put(team, points1);
            }

            if (throwsGained.containsKey(team1)) {
                if (points > points1) {
                    pkt1 = matchPoints.get(team1);
                    matchPoints.put(team1, pkt1);
                    l1 = losses.get(team1) + 1;
                    losses.put(team1, l1);
                } else if (points == points1) {
                    pkt1 = matchPoints.get(team1) + 1;
                    matchPoints.put(team1, pkt1);
                    d1 = draws.get(team1) + 1;
                    draws.put(team1, d1);
                } else if (points < points1) {
                    pkt1 = matchPoints.get(team1) + 3;
                    matchPoints.put(team1, pkt1);
                    w1 = wins.get(team1) + 1;
                    wins.put(team1, w1);
                }
                points1Gained = throwsGained.get(team1) + points1;
                points1Lost = throwsLost.get(team1) + points;
                throwsGained.put(team1, points1Gained);
                throwsLost.put(team1, points1Lost);
            } else {
                if (points > points1) {
                    matchPoints.put(team1, 0);
                    wins.put(team1, 0);
                    draws.put(team1, 0);
                    losses.put(team1, 1);
                } else if (points < points1) {
                    matchPoints.put(team1, 3);
                    wins.put(team1, 1);
                    draws.put(team1, 0);
                    losses.put(team1, 0);
                } else {
                    matchPoints.put(team1, 1);
                    wins.put(team1, 0);
                    draws.put(team1, 1);
                    losses.put(team1, 0);
                }
                throwsGained.put(team1, points1);
                throwsLost.put(team1, points);
            }


        }


        String teamStats = toFind + ":W=" + wins.get(toFind) + ";D=" + draws.get(toFind) + ";L=" + losses.get(toFind) + ";Scored=" + throwsGained.get(toFind) + ";Conceded=" + throwsLost.get(toFind) + ";Points=" + matchPoints.get(toFind);
        //if
        if (toFind.equals("")) return "";
        if (throwsGained.containsKey(toFind)) return teamStats;
        else return toFind + ":This team didn't play!";
    }

    public static void main(String[] args) {

        String r = "Los Angeles Clippers 10.4 Dallas Mavericks 8.8,New York Knicks 121 Atlanta Hawks 112,Indiana Pacers 103 Memphis Grizzlies 112,"
                + "Los Angeles Lakers 111 Minnesota Timberwolves 112,Phoenix Suns 121 Dallas Mavericks 111,Portland Trail Blazers 112 New Orleans Pelicans 94,"
                + "Sacramento Kings 104 Los Angeles Clippers 111,Houston Rockets 85 Denver Nuggets 105,Memphis Grizzlies 150 Cleveland Cavaliers 106,"
                + "Milwaukee Bucks 97 New York Knicks 122,Oklahoma City Thunder 112 San Antonio Spurs 106,Boston Celtics 112 Philadelphia 76ers 95,"
                + "Brooklyn Nets 100 Chicago Bulls 115,Detroit Pistons 92 Utah Jazz 87,Miami Heat 104 Charlotte Hornets 94,"
                + "Toronto Raptors 106 Indiana Pacers 99,Orlando Magic 87 Washington Wizards 88,Golden State Warriors 111 New Orleans Pelicans 95,"
                + "Atlanta Hawks 94 Detroit Pistons 106,Chicago Bulls 97 Cleveland Cavaliers 97,";


        System.out.println(nbaCup(r, "Philadelphia 76ers"));

    }
}


// for (int i : args) gdzie args  to tablica