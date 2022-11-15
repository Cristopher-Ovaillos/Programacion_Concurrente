package Babuinos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cuerda {

    private int contSubidos, totalBabuinosDer, totalBabuinosIzq;
    private final int TOTAL_CUERDA = 5;
    private Semaphore terminar, simulaSync, lugarCuerdaDer, lugarCuerdaIzq;
    private boolean puedenPasarDer = false, puedenPasarIzq = false, avisarBloqueo = true;

    public Cuerda(int der, int izq) {
        totalBabuinosDer = der;
        totalBabuinosIzq = izq;
        lugarCuerdaDer = new Semaphore(0);
        lugarCuerdaIzq = new Semaphore(0);
        terminar = new Semaphore(0);
        simulaSync = new Semaphore(1);// semaforo en comun entre los dos tipos de babuinos

    }

    public void esperarQuePasenDer() throws InterruptedException {
        simulaSync.acquire();// tomo la llave
        while (puedenPasarDer) {
            simulaSync.release();// "simulaSync" libero para que pase otro babuino (sin esto el semaforo de abajo si no tiene permiso lo va a estar bloqueando el semaforo de simulaSync)
            lugarCuerdaDer.acquire();// espera
            simulaSync.acquire();

        }
             

        System.out.println("El babuino " + Thread.currentThread().getName() + " intenta pasar (DER A IZQ) ");
        if (avisarBloqueo) {
            // solo el primero que entra es el que avisa y como tiene la llave de

            avisarBloqueo = false;
            puedenPasarIzq = true;
        }
        contSubidos++;

        if (contSubidos == TOTAL_CUERDA) {
            puedenPasarDer = true;// estan fuera del rango
            terminar.release();
        }
        simulaSync.release();// dejo la llave
    }

    public void esperarQuePasenIzq() throws InterruptedException {
        simulaSync.acquire();// tomo la llave
        while (puedenPasarIzq) {
            simulaSync.release();// "simulaSync" libero para que pase otro babuino (sin esto el semaforo de abajo si no tiene permiso lo va a estar bloqueando el semaforo de simulaSync)
            lugarCuerdaIzq.acquire();// espera
            simulaSync.acquire();

        }
        
        System.out.println("El babuino " + Thread.currentThread().getName() + " intenta pasar (IZQ-DER)   ");
        if (avisarBloqueo) {
            // solo el primero que entra es el que avisa y como tiene la llave de

            avisarBloqueo = false;
            puedenPasarDer = true;
        }
        contSubidos++;

        if (contSubidos == TOTAL_CUERDA) {
            puedenPasarIzq = true;// estan fuera del rango
            terminar.release();
        }

        simulaSync.release();// dejo la llave
    }

    public void pasaCuerda() throws InterruptedException {
        terminar.acquire();
        Random x = new Random();
        int i = x.nextInt(2);
        simulaSync.acquire();
        contSubidos--;
        System.out.println("el Babuino termina de pasar");
        simulaSync.release();

        if (contSubidos == 0) {
            avisarBloqueo = true;
            puedenPasarDer = false;
            puedenPasarIzq = false;
            // luego libero a un babuino

            //if (i == 0) {
               
                    lugarCuerdaDer.release(5);
                 
            //} else {
               
                    lugarCuerdaIzq.release(5);
             
            //}
       

        }
        terminar.release();
    }
/*
 *  public void pasaCuerda() throws InterruptedException {
        terminar.acquire();
        Random x = new Random();
        int i = x.nextInt(2);
        simulaSync.acquire();
        contSubidos--;
        System.out.println("el Babuino termina de pasar");
        simulaSync.release();
        if (contSubidos == 0) {
            int permisosDer = 0, permisosIzq = 0;
            avisarBloqueo = true;
            puedenPasarDer = false;
            puedenPasarIzq = false;
            // luego libero a un babuino
            while (permisosDer != 5 && totalBabuinosDer != 0) {
                permisosDer++;
                totalBabuinosDer--;
               
            }
            // darPermisoParaizquierdo
            while (permisosIzq != 5 && totalBabuinosIzq != 0) {
                permisosIzq++;
                totalBabuinosIzq--;
            }
            if (i == 0) {
                if (noDarPermisosDer) {
                    lugarCuerdaDer.release(permisosDer);
                    totalBabuinosIzq=totalBabuinosIzq+permisosIzq;
                } else {
                    lugarCuerdaIzq.release(permisosIzq);
                }
                if (totalBabuinosDer == 0) {
                    noDarPermisosDer = false;
                }
            } else {
                if (noDarPermisosIzq) {
                    lugarCuerdaIzq.release(permisosIzq);
                    totalBabuinosDer=totalBabuinosDer+permisosDer;
                } else {
                    lugarCuerdaDer.release(permisosDer);
                }
                if (totalBabuinosIzq == 0) {
                    noDarPermisosIzq = false;
                }
            }
       
        }
        terminar.release();
    }
 * 
 */
}

