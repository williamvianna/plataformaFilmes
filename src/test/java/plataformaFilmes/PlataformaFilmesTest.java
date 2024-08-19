package plataformaFilmes;

import Utils.RestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import maps.LoginMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlataformaFilmesTest {

    @BeforeAll
    public static void validarLogin() {
        RestUtils.setBaseURI("http://localhost:8080/");

        LoginMap.initLogin();

        Response response = RestUtils.post(LoginMap.getLogin(), ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
        LoginMap.token = response.jsonPath().get("token");
        System.out.println(response.jsonPath().get().toString());
    }

    @Test
    public void validarConsultaCategorias() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + LoginMap.token);

        Response response = RestUtils.get(header, "categorias");

        assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().get().toString());

        assertEquals("Terror", response.jsonPath().get("tipo[2]"));
        assertEquals(5, (int) response.jsonPath().get("id[4]"));
        List<String> listTipo = response.jsonPath().get("tipo");

        assertTrue(listTipo.contains("Terror"));
    }

    @Test
    public void validarListaCategorias() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + LoginMap.token);

        Response response = RestUtils.get(header, "categorias");

        assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().get().toString());

        List<String> listTipo = new ArrayList<>();
        listTipo.add("Aventura");
        listTipo.add("Acao");
        listTipo.add("Terror");
        listTipo.add("Drama");
        listTipo.add("Comedia");
        listTipo.add("Sobrevivencia");
        listTipo.add("Crime");
        listTipo.add("Thriller");

        assertEquals(listTipo, response.jsonPath().get("tipo"));
    }

    @Test
    public void validarIdCategorias() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + LoginMap.token);

        Response response = RestUtils.get(header, "categorias");

        assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().get().toString());

        List<Integer> listTipo = new ArrayList<>();
        listTipo.add(1);
        listTipo.add(2);
        listTipo.add(3);
        listTipo.add(4);
        listTipo.add(5);
        listTipo.add(6);
        listTipo.add(7);
        listTipo.add(8);

        assertEquals(listTipo, response.jsonPath().get("id"));
    }

    @Test
    public void validarTamanhoLista() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + LoginMap.token);

        Response response = RestUtils.get(header, "categorias");

        assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().get().toString());

        List<Integer> listTipo = new ArrayList<>();
        listTipo.add(1);
        listTipo.add(2);
        listTipo.add(3);
        listTipo.add(4);
        listTipo.add(5);
        listTipo.add(6);
        listTipo.add(7);
        listTipo.add(8);

        assertEquals(listTipo, response.jsonPath().get("id"));
        List<Integer> listId = response.jsonPath().get("id");
        assertEquals(8, listId.size());
    }
}