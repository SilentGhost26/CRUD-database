package org.models;

public class MedicalAppointment {

    private int id;
    private String datetime;
    private String reason;
    private String state;
    private int patient_id;
    private int doctor_id;

    public MedicalAppointment(int id, String datetime, String reason, String state, int patient_id, int doctor_id){
        this.id = id;
        this.datetime = datetime;
        this.reason = reason;
        this.state = state;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
    }

    public String toString (){
        return "id: " + id + " fecha: " + datetime + " razon: " + reason + " estado: " + state + " idPaciente: " + patient_id + " idMedico: " + doctor_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getReason() {
        return reason;
    }

    public String getState() {
        return state;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }
}
