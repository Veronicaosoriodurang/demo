package com.example.demo;

import com.example.demo.model.EstadoHabitacion;
import com.example.demo.model.Habitacion;
import com.example.demo.repository.HabitacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final HabitacionRepository habitacionRepository;

    public DataInitializer(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    @Override
    public void run(String... args) {
        if (habitacionRepository.count() > 0) {
            return;
        }

        List<Habitacion> habitaciones = List.of(
                Habitacion.builder()
                        .numero("101")
                        .tipo("Simple")
                        .precioPorNoche(80000.0)
                        .imagenUrl("https://images.unsplash.com/photo-1631049307264-da0ec9d70304?w=400")
                        .estado(EstadoHabitacion.DISPONIBLE)
                        .build(),
                Habitacion.builder()
                        .numero("102")
                        .tipo("Doble")
                        .precioPorNoche(150000.0)
                        .imagenUrl("https://images.unsplash.com/photo-1582719478250-c89cae4dc85b?w=400")
                        .estado(EstadoHabitacion.DISPONIBLE)
                        .build(),
                Habitacion.builder()
                        .numero("201")
                        .tipo("Suite")
                        .precioPorNoche(280000.0)
                        .imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?w=400")
                        .estado(EstadoHabitacion.DISPONIBLE)
                        .build(),
                Habitacion.builder()
                        .numero("202")
                        .tipo("Suite")
                        .precioPorNoche(280000.0)
                        .imagenUrl("https://images.unsplash.com/photo-1564501049412-61c2a3083791?w=400")
                        .estado(EstadoHabitacion.DISPONIBLE)
                        .build(),
                Habitacion.builder()
                        .numero("301")
                        .tipo("Presidencial")
                        .precioPorNoche(500000.0)
                        .imagenUrl("https://images.unsplash.com/photo-1578683010236-d716f9a3f461?w=400")
                        .estado(EstadoHabitacion.DISPONIBLE)
                        .build()
        );

        habitacionRepository.saveAll(habitaciones);
    }
}
