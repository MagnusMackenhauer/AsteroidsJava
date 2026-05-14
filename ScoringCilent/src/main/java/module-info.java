module ScoringClient {
    requires CommonScore;
    requires java.net.http;

    provides dk.sdu.commonscore.ScoreSPI
            with dk.sdu.scoringclient.ScoringClient;
}