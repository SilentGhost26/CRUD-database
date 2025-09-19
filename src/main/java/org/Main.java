package org;

import org.dao.CRUD;
import org.models.MedicalAppointment;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        CRUD crud = new CRUD();
//       insertOperation(crud);
//        updateOperation(crud, 1, "Completado");
//        selectOperation(crud);
//        deleteOperation(crud, 4);
        procedureOperation(crud, 1);
    }

    public static void updateOperation(CRUD crud, int maId, String newState){
        crud.updateStateMedicalAppointment(maId, newState);
    }

    public static void insertOperation(CRUD crud){
        crud.insertMedicalAppointment("2025-08-10 10:00:00", "Consulta rutinaria", "pasado", 4, 4);
    }

    public static void deleteOperation (CRUD crud, int maId){
        crud.deleteMedicalAppointment(maId);
    }

    public static void selectOperation(CRUD crud){
        List<MedicalAppointment> medicalAppointmentList = crud.getMedicalAppointments();
        for (MedicalAppointment medicalAppointment : medicalAppointmentList) {
            System.out.println(medicalAppointment);
        }
    }

    public static void procedureOperation(CRUD crud, int patientId){
        crud.execProPrescriptionAmount(patientId);
    }
}