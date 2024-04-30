/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import objetos.Aerolinea;

/**
 *
 * @author Pedro
 */
public interface IAerolineasDAO {
    public boolean agregar(Aerolinea a);
    public boolean actualizar(Aerolinea a);
    public boolean eliminar(Aerolinea a);
    
    public Aerolinea consultar(Aerolinea a);
    public List<Aerolinea> consultarTodos();
}
