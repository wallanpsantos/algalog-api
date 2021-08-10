package com.algaworks.algalog;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AlgalogApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testeMock() {
        List<String> listMock = Mockito.mock(ArrayList.class);

        Mockito.when(listMock.size()).thenReturn(20);

        int tamanho = listMock.size();

        Assertions.assertThat(tamanho).isEqualTo(20);
    }

}
