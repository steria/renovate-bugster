package no.soprasteria.bugster.infrastructure.db.repository;

import no.soprasteria.bugster.business.bet.domain.Odds;
import no.soprasteria.bugster.business.match.domain.Result;
import no.soprasteria.bugster.infrastructure.db.Database;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class OddsRepository extends Repository<Odds> {

    public OddsRepository() {
        super();
    }

    @Override
    public List<Odds> list() {
        throw new UnsupportedOperationException("ikke implementert");
    }

    @Override
    public Optional<Odds> findById(int id) {
        return database.queryForSingle("SELECT * FROM ODDS WHERE id = ?", id, this::toOdds);
    }

    @Override
    public void insert(Odds odds) {
        database.doInTransaction(() -> {
            int id = database.insert("insert into odds (match_id, result, value, timestamped_at) values (?, ?, ?, ?)",
                    odds.getMatchId(),
                    odds.getResult(),
                    odds.getValue(),
                    Timestamp.valueOf(odds.getTimestampedAt()));
            odds.setId(id);
        });
    }

    @Override
    public void update(Odds update) {
        throw new NotImplementedException();
    }

    public List<Odds> findBy(Integer matchId) {
        return database.queryForList("select id, match_id, result, value from odds o1" +
                " where o1.timestamped_at = (select max(timestamped_at from odds o2 where o1.id = o2.id) " +
                " and match_id = ?", this::toOdds);
    }

    private Odds toOdds(Database.Row row) throws SQLException {
        Odds odds = new Odds(
                row.getInt("match_id"),
                Result.valueOf(row.getString("result")),
                row.getDouble("value"));
        odds.setId(row.getInt("id"));
        return odds;
    }
}
