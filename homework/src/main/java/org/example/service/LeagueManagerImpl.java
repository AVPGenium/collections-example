package org.example.service;

import org.example.model.League;
import org.example.model.Player;
import org.example.model.Race;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LeagueManagerImpl implements LeagueManager {
    private Map<String, Player> nameToPlayerIndex = new ConcurrentHashMap<>();

    @Override
    public void addPlayer(Player player) {
        nameToPlayerIndex.putIfAbsent(player.getNickName(), player);
    }

    @Override
    public void removePlayer(Player player) {
        nameToPlayerIndex.remove(player.getNickName());
    }

    @Override
    public Player getPlayer(String name) {
        return nameToPlayerIndex.get(name);
    }

    @Override
    public Player[] getAllPlayers() {
        return nameToPlayerIndex.values().toArray(new Player[0]);
    }

    @Override
    public Player[] getPlayers(League league) {
        return nameToPlayerIndex.values()
                .stream().filter(player -> league.equals(player.getLeague()))
                .distinct()
                .toArray(Player[]::new);
    }

    @Override
    public Player[] getPlayers(Race race) {
        return nameToPlayerIndex.values()
                .stream().filter(player -> race.equals(player.getRace()))
                .distinct()
                .toArray(Player[]::new);
    }

    @Override
    public void addPoints(String name, int points) {
        Player player = nameToPlayerIndex.get(name);
        player.setPoints(player.getPoints() + points);
    }
}
