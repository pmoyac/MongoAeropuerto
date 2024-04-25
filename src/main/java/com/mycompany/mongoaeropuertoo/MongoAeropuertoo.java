package com.mycompany.mongoaeropuertoo;

import daos.DAOAerolineas;
import java.util.ArrayList;
import modelos.Aerolinea;

/**
 *
 * @author pedro
 */
public class MongoAeropuertoo {

    public static void main(String[] args) {
        DAOAerolineas aeroDAO = new DAOAerolineas();
        ArrayList<Aerolinea> lista = aeroDAO.obtenerAerolineas();
        Aerolinea aerolinea = new Aerolinea("Aeromexico", "MX", "MXN", true); 
        
        
        for (Aerolinea arg : lista) {
            System.out.println(arg.toString());
        }
        
       
//        aeroDAO.agregarAerolinea(aerolinea);
//        System.out.println("Aerolinea agregada");
       
        
//        String eliminar = "6621cb5c70ea5f5a8550f123";
//        aeroDAO.eliminarAerolinea(eliminar);
//        System.out.println("Aerolinea eliminada");
    }
}
