package br.com.catolica.crud_spring.controller;

import br.com.catolica.crud_spring.model.Pessoa;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class crudController {
    private static final List<Pessoa> listaPessoas = new ArrayList<>();
    static {
        listaPessoas.add(new Pessoa(1, "João", 30));
        listaPessoas.add(new Pessoa(2, "Maria", 25));
        listaPessoas.add(new Pessoa(3, "José", 40));
    }

    @GetMapping("/pesquisar/{id}")
    public String getPessoa(@PathVariable int id) {
        for (Pessoa pessoa : listaPessoas){
            if(pessoa.getId() == id){
                return "ID: " + pessoa.getId() + ", Nome: " + pessoa.getNome() + ", Idade: " + pessoa.getIdade();
            }
        }
        return "Pessoa não encontrada";
    }

    @PostMapping("/criar")
    public String criarPessoa(@RequestBody Pessoa pessoa){
        pessoa.setId(listaPessoas.size() + 1);
        listaPessoas.add(pessoa);
        return "Pessoa criada: " + pessoa.getNome();
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarPessoa(@PathVariable int id, @RequestBody Pessoa pessoa){
        for (Pessoa p : listaPessoas){
            if(p.getId() == id){
                p.setNome(pessoa.getNome());
                p.setIdade(pessoa.getIdade());
                return "Pessoa atualizada: " + p.getNome();
            }
        }
        return "Pessoa não encontrada para atualização";
    }

    @GetMapping("/remover/{id}")
    public String removerPessoa(@PathVariable int id){
        for(Pessoa p : listaPessoas){
            if(p.getId() == id){
                listaPessoas.remove(p);
                return "ID: " + p.getId() + ", Nome: " + p.getNome() + ", Idade: " + p.getIdade() + ". Foi removida com sucesso";
            }
        }
        return "Pessoa não encontrada";
    }

    @PostMapping("/somar")
    public Number somarNumeros(@PathVariable float int1, @PathVariable float int2){
        return int1 + int2;
    }
}

