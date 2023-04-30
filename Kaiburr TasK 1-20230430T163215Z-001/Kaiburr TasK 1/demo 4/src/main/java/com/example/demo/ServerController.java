package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("http://localhost:3000")
public class ServerController {

    @Autowired
    private ServerRepository serverRepository;

    @PostMapping("/add")
    public void insertData(@RequestBody Server server) {
        serverRepository.save(server);
    }

    /*@GetMapping("/findAll")
    public List<Server> getAllData(@RequestParam("id") String id) {
        if (id == null) {
            return serverRepository.findAll();
        } else {
            if (serverRepository.existsById(id)) {
                Server server = serverRepository.findById(id).get();
                List<Server> servers = new ArrayList<>();
                servers.add(server);
                return servers;
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }*/

    @GetMapping("/findAll")
    public List<Server>getAllData(){
        return serverRepository.findAll();
    }

    @DeleteMapping("deleteById")
    public void deleteData(@RequestParam("id") String id) {
        serverRepository.deleteById(id);
    }

    @GetMapping("findByName")
    public List<Server> getData(@RequestParam("name") String name) {
        List<Server> servers = new ArrayList<>();
        List<Server> serverList = serverRepository.findAll();
        for (Server server : serverList) {
            if (server.getName().contains(name))
                servers.add(server);
        }
        if (servers.isEmpty())
            throw new NoSuchElementException();
        else return servers;
    }
}
