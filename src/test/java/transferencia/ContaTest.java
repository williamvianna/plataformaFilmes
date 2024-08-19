package transferencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.spec.EdDSAParameterSpec;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    Cliente xuxa;
    Cliente silvioSantos;
    Conta contaXuxa;
    Conta contaSilvio;

    @BeforeEach
    void setUp() {
        xuxa = new Cliente("Xuxa", "123456789012", "123456987");
        silvioSantos = new Cliente("Silvio Santos", "012345678912", "987445621");

        contaXuxa = new Conta("0025", "1254", 2500.00, xuxa);
        contaSilvio = new Conta("8542", "8571", 3500.00, silvioSantos);
    }

    @Test
    public void realizarTransacao(){
        contaXuxa.realizarTransferencia(1000.00, contaSilvio);
        assertEquals(1500.00, contaXuxa.getSaldo());
        assertEquals(4500.00, contaSilvio.getSaldo());
    }

    @Test
    public void validarTransferenciaInvalida(){
        boolean resultado = contaXuxa.realizarTransferencia(3500.00, contaSilvio);
        assertFalse(resultado);
    }
}