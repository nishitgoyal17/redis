package org.nishit.jedis.service;

import org.nishit.jedis.model.Programmer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProgrammerService {
    void setProgrammerAsString(String idKey,String programmer);
    String getProgrammerAsString(String idKey);

    void AddToProgrammersList(Programmer programmer);
    List<Programmer> getProgrammersListMembers();
    Long getProgrammersListCount();

    void AddToProgrammersSet(Programmer... programmers);
    Set<Programmer> getProgrammersSetMembers();
    boolean isSetMember(Programmer programmer);

    void saveHash(Programmer programmer);
    void updateHash(Programmer programmer);
    Map<Integer,Programmer> findALLHash();
    Programmer findInHash(int id);
    void deleteHash(int id);
}
