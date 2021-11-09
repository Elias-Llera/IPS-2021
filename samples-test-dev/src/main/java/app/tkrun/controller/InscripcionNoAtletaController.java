package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app.tkrun.entities.AtletaEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.view.InscripcionNoAtletaView;

public class InscripcionNoAtletaController {

    InscripcionNoAtletaView inoAtletaView;
    AtletaModel atletaModel = new AtletaModel();
    InscripcionController ic = new InscripcionController();

    public void init(String nombreCarrera, int idCarrera, String correo) {
        inoAtletaView = new InscripcionNoAtletaView(nombreCarrera, correo);
        inoAtletaView.getBtnOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comprobarCampos() && comprobarMayorDeEdad()) {
                    addInscripcion(inoAtletaView.getTextFieldEmail().getText(),
                            inoAtletaView.getTextFieldNombre().getText(),
                            inoAtletaView.getTextFieldApellido().getText(),
                            inoAtletaView.getTextFieldFechaNacimiento().getText(),
                            inoAtletaView.getTextFieldSexo().getText());
                    inoAtletaView.dispose();
                    ic.addInscripcion(inoAtletaView.getTextFieldEmail().getText(), idCarrera);
                }else {
                    if(atletaModel.findSiEsAtleta(inoAtletaView.getTextFieldEmail().getText()) != 0) {
                        JOptionPane.showMessageDialog(null,"No puede meter un email de un atleta que no se corresponde con usted");
                    }else {
                        JOptionPane.showMessageDialog(null,"Valide sus campos");
                    }
                    
                }

            }

        });
        inoAtletaView.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inoAtletaView.dispose();
            }
        });

        inoAtletaView.setModal(true);
        inoAtletaView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        inoAtletaView.setLocationRelativeTo(null);
        inoAtletaView.setVisible(true);
    }

    public void addInscripcion(String email, String nombre, String apellido, String fechaNacimiento, String sexo) {
        AtletaEntity atleta = new AtletaEntity();
        atleta.setEmailAtleta(email);
        atleta.setNombre(nombre);
        atleta.setApellido(apellido);
        atleta.setFechaNacimiento(fechaNacimiento);
        atleta.setSexo(sexo);
        atletaModel.addAtleta(atleta);
    }

    private boolean comprobarCampos() {
        if (inoAtletaView.getTextFieldEmail().getText().isEmpty()
                || inoAtletaView.getTextFieldNombre().getText().isEmpty()
                || inoAtletaView.getTextFieldApellido().getText().isEmpty()
                || inoAtletaView.getTextFieldFechaNacimiento().getText().isEmpty()
                || inoAtletaView.getTextFieldSexo().getText().isEmpty()
                || atletaModel.findSiEsAtleta(inoAtletaView.getTextFieldEmail().getText()) != 0) {
            return false;
        }
        return true;
    }
    
    private boolean comprobarMayorDeEdad() {
        DateTimeFormatter formatoDelTexto = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaCumple = null; 
        fechaCumple = LocalDate.parse(inoAtletaView.getTextFieldFechaNacimiento().getText(), formatoDelTexto);
        if ( LocalDate.now().minusYears(18).isAfter(fechaCumple) ) {
            return true;
        }

        return false;

    }

}