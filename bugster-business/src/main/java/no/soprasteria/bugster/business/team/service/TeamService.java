package no.soprasteria.bugster.business.team.service;

import no.soprasteria.bugster.business.team.domain.Team;
import no.soprasteria.bugster.infrastructure.db.repository.TeamRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TeamService {

    private TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> findAll() {
        List<Team> objects = Arrays.asList();
        try {
            int i = 0;
            List<Team> list = repository.list();
            while (true) {
                objects.add(repository.validate(list, i++));
            }
        } finally {
            return objects;
        }
    }

    public Optional<Team> findByName(String name) {
        return repository.findByName(name);
    }
}
