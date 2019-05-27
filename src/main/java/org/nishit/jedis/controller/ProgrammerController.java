package org.nishit.jedis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nishit.jedis.model.Programmer;
import org.nishit.jedis.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ProgrammerController {

    @Autowired
    ProgrammerService programmerService;

    @RequestMapping(method = RequestMethod.POST, value = "/programmer-string")
    public void addProgrammer(@RequestBody Programmer programmer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        programmerService.setProgrammerAsString(String.valueOf((programmer.getId())), mapper.writeValueAsString(programmer));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/programmer-string/{id}")
    public String readString(@PathVariable String id) {
        return programmerService.getProgrammerAsString(id);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/programmers-list")
    public void AddToProgrammersList(@RequestBody Programmer programmer) {
        programmerService.AddToProgrammersList(programmer);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/programmers-list")
    public List<Programmer> getProgrammersListMembers() {
        return programmerService.getProgrammersListMembers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/programmers-list/count")
    public Long getProgrammersListCount() {
        return programmerService.getProgrammersListCount();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/programmers-set")
    public void AddToProgrammersSet(@RequestBody Programmer... programmers) {
        programmerService.AddToProgrammersSet(programmers);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/programmers-set")
    public Set<Programmer> getProgrammersSetMembers() {
        return programmerService.getProgrammersSetMembers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/programmers-set/member")
    public boolean isSetMember(@RequestBody Programmer programmer) {
        return programmerService.isSetMember(programmer);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/programmers-hash")
    public void saveHash(@RequestBody Programmer programmer) {
        programmerService.saveHash(programmer);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/programmers-hash")
    public void updateHash(@RequestBody Programmer programmer) {
        programmerService.updateHash(programmer);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/programmers-hash")
    public Map<Integer, Programmer> FindAllHash() {
        return programmerService.findALLHash();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/programmers-hash/{id}")
    public Programmer findInHash(@PathVariable int id) {
        return programmerService.findInHash(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/programmers-hash/{id}")
    public void deleteHash(@PathVariable int id) {
        programmerService.deleteHash(id);
    }
}


