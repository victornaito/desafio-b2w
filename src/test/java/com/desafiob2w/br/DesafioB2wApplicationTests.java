package com.desafiob2w.br;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = { "main", "main.Infraestructure", "main.UserInterface.Controller"})
@RunWith(MockitoJUnitRunner.class)
class DesafioB2wApplicationTests {

    @Test
    void contextLoads() {
    }

}
