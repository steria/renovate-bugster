package no.soprasteria.bugster.business.match.service;

import no.soprasteria.bugster.business.match.domain.Match;
import no.soprasteria.bugster.infrastructure.db.repository.MatchRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MatchService {

    private MatchRepository repository;

    public MatchService(MatchRepository repository) {
        this.repository = repository;
    }

    public List<Match> findAll() {
        List<Match> list = repository.list();
        Collections.sort(list);
        return list;
    }

    public List<Match> findAllByName(String name) {
        List<Match> byName = repository.findByName(name);
        ArrayList<Match> kamper = new ArrayList<>();
        for(int i=0; i < byName.size()-1; ) {
            kamper.add(byName.get(++i));
        }
        Collections.sort(kamper);
        return kamper;
    }

    public List<Match> findAllByDate(String name) {
        List<Match> byName = repository.findByName(name);
        Collections.sort(byName);
        return byName;
    }

    public Optional<Match> findById(int id) {
        return repository.find(id);
    }
}
