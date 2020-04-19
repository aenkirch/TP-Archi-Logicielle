package server.springboot.models;

public class Global {

    public final Players players;
    public final LocalMap localmap;

    public Global(Players players, LocalMap localmap) {

        this.players = players;
        this.localmap = localmap;
    }

    public Players getPlayers(Players players) { return players; }
    public LocalMap getLocalmap(LocalMap localMap) { return localMap; }
}
