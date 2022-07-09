package br.com.viniciuscavalheiro.springbootcommysql;

import br.com.viniciuscavalheiro.springbootcommysql.controller.PessoaController;
import br.com.viniciuscavalheiro.springbootcommysql.controller.dto.PessoaRq;
import br.com.viniciuscavalheiro.springbootcommysql.model.Pessoa;
import br.com.viniciuscavalheiro.springbootcommysql.repository.PessoaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PessoaTest extends  SpringBootComMysqlApplicationTests {
    private MockMvc mockMvc;


    private PessoaController pessoaController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }

    @Test
    public void criarPessoaComNome_RetornarStatusCode201() throws Exception {
        PessoaRq pessoa = new PessoaRq("MAria", "Loiola");

        ObjectMapper mapper = new ObjectMapper();

        String json =  mapper.writeValueAsString(pessoa);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/pessoa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
