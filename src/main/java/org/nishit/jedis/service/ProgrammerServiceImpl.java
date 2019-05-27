package org.nishit.jedis.service;

import org.nishit.jedis.dao.ProgrammerRepository;
import org.nishit.jedis.model.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProgrammerServiceImpl implements ProgrammerService {

    @Autowired
    ProgrammerRepository repository;

    @Override
    public void setProgrammerAsString(String idKey, String programmer) {
        repository.setProgrammerAsString(idKey,programmer);

    }

    @Override
    public String getProgrammerAsString(String idKey) {
      return repository.getProgrammerAsString(idKey);
    }

    @Override
    public void AddToProgrammersList(Programmer programmer) {
        repository.AddToProgrammersList(programmer);
    }

    @Override
    public List<Programmer> getProgrammersListMembers() {
        return repository.getProgrammersListMembers();
    }

    @Override
    public Long getProgrammersListCount() {
        return repository.getProgrammersListCount();
    }

    @Override
    public void AddToProgrammersSet(Programmer... programmers) {
        repository.AddToProgrammersSet(programmers);
    }

    @Override
    public Set<Programmer> getProgrammersSetMembers() {
        return repository.getProgrammersSetMembers();
    }

    @Override
    public boolean isSetMember(Programmer programmer) {
        return repository.isSetMember(programmer);
    }

    @Override
    public void saveHash(Programmer programmer) {
        repository.saveHash(programmer);
    }

    @Override
    public void updateHash(Programmer programmer) {
         repository.updateHash(programmer);
    }

    @Override
    public Map<Integer, Programmer> findALLHash() {
        return repository.findALLHash();
    }

    @Override
    public Programmer findInHash(int id) {
        return repository.findInHash(id);
    }

    @Override
    public void deleteHash(int id) {
        repository.deleteHash(id);
    }
}
