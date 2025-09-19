package org.dao;

import org.models.MedicalAppointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    private Connection connection;

    public CRUD () throws SQLException {
        connection = DBConnection.getConnection();
    }

    public void insertMedicalAppointment(String datetime, String reason, String state, int patient_id, int doctor_id){
        String sql = "INSERT INTO Medical_appointment VALUES (?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(datetime));
            pstmt.setString(2, reason);
            pstmt.setString(3, state);
            pstmt.setInt(4, patient_id);
            pstmt.setInt(5, doctor_id);
            pstmt.executeUpdate();
            System.out.println("Datos insertados exitosamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateStateMedicalAppointment(int maId, String newState){
        String sql = "UPDATE Medical_appointment SET state_ma = ? WHERE Medical_appointment.appointment_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, newState);
            pstmt.setInt(2, maId);
            pstmt.executeUpdate();
            System.out.println("estado actualizado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteMedicalAppointment(int maId){
        String sql = "DELETE FROM Medical_appointment WHERE appointment_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, maId);
            pstmt.executeUpdate();
            System.out.println("registro eliminado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<MedicalAppointment>  getMedicalAppointments(){
        String sql = "SELECT * FROM Medical_appointment";
        List<MedicalAppointment> medicalAppointmentList = new ArrayList<>();
        try (Statement pstmt = connection.createStatement(); ResultSet result = pstmt.executeQuery(sql)){

            while (result.next()){
                medicalAppointmentList.add(new MedicalAppointment(
                        result.getInt("appointment_id"),
                        result.getString("datetime_ma"),
                        result.getString("reason"),
                        result.getString("state_ma"),
                        result.getInt("patient_id"),
                        result.getInt("doctor_id")
                ));
            }
            System.out.println("Regsitros obtenidos exitosamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return medicalAppointmentList;
    }

    public void execProPrescriptionAmount(int patientId){
        String sql = "{call pro_pres_amount(?)}";
        try(CallableStatement stmt = connection.prepareCall(sql)){
            stmt.setInt(1, patientId);

            if(stmt.execute()){
                try(ResultSet result = stmt.getResultSet()) {
                    if(result.next()){
                        String name = result.getString("name");
                        int amount = result.getInt("prescription_amount");
                        System.out.println("paciente: " + name + " - cantidad de citas medicas: " + amount);
                    }
                }
            } else {
                System.out.println("paciente menor de edad o registro no disponible");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
