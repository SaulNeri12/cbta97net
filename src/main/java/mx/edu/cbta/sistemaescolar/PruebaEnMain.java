package mx.edu.cbta.sistemaescolar;

import mx.edu.cbta.sistemaescolar.academica.model.Aula;
import mx.edu.cbta.sistemaescolar.academica.service.AulaService;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.AulaNoEncontradaException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component hace que Spring la encuentre y la ejecute
@Component
public class PruebaEnMain implements CommandLineRunner {

    // Inyectamos el servicio, igual que en el controlador
    private final AulaService aulaService;

    public PruebaEnMain(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    // Este método se ejecutará al arrancar la app
    @Override
    public void run(String... args) throws Exception {


    }
}