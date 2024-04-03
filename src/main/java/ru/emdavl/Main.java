package ru.emdavl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@Component
class Runner implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, String> templ;

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (!Objects.equals(sc.nextLine(), "q")) {
            templ.executePipelined((RedisCallback<?>) connection -> {
               connection.stringCommands().mSet(Map.of(
                       "test".getBytes(), "val1".getBytes(),
                       "test2".getBytes(), "val2".getBytes(),
                       "test3".getBytes(), "val3".getBytes()
               ));
                return null;
            });
        }
    }
}
