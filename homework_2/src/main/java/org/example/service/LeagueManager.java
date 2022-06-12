package org.example.service;

import org.example.model.League;
import org.example.model.Player;
import org.example.model.Race;

/**
 * TODO: реализовать интерфейс LeagueManager. Реализация должна хранить данные об игроках эффективным образом
 * для осуществления быстрого поиска, а также добавления/удаления игроков согласно представленному интерфейсу методов
 * TODO: сделать реализацию интерфейса LeagueManager потокобезопасной:
 * должно быть обеспечено безопасное добавление/изменение данных об игроках в многопоточном режиме
 */
public interface LeagueManager {
    void addPlayer(Player player);
    void removePlayer(Player player);
    Player getPlayer(String name);
    Player[] getAllPlayers();
    Player[] getPlayers(League league);
    Player[] getPlayers(Race race);
    void addPoints(String name, int points);
}

