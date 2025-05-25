package com.example.NotificacionStock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NotificacionStock.Model.NotificacionInventario;

@Repository
public interface NotificacionRepositorio extends JpaRepository<NotificacionInventario, Long> {

}
