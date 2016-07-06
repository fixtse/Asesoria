package com.ulima.sw.Asesorias.beans;

/**
 * Created by fixt on 15/06/16.
 */
public class Mensaje {


        private String hora;
        private int estado,id;

        public Mensaje(String hora, int estado, int id) {
            this.hora = hora;
            this.estado = estado;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }



    }


