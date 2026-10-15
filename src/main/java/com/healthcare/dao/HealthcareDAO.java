package com.healthcare.dao;

import com.healthcare.config.DB;
import java.sql.*;
import java.util.*;

public class HealthcareDAO {
    public record Patient(long id, String name, String phone, String email, String gender, String dob) {}
    public record Doctor(long id, String name, String specialization, String phone, String email, String availability) {}
    public record Appointment(long id, String patient, String doctor, Timestamp date, String reason, String status) {}
    public record Bill(long id, String patient, double amount, String description, String status, Timestamp createdAt) {}

    public int count(String table) throws Exception {
        Set<String> allowed = Set.of("patients", "doctors", "appointments", "bills");
        if (!allowed.contains(table)) throw new IllegalArgumentException("Invalid table");
        try (Connection c = DB.getConnection(); Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM " + table)) {
            rs.next(); return rs.getInt(1);
        }
    }

    public boolean hasUsers() throws Exception { return countUsers() > 0; }
    private int countUsers() throws Exception {
        try (Connection c = DB.getConnection(); Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM users")) { rs.next(); return rs.getInt(1); }
    }

    public void createUser(String username, String hash, String displayName, String role) throws Exception {
        try (Connection c = DB.getConnection(); PreparedStatement p = c.prepareStatement(
            "INSERT INTO users(username,password_hash,display_name,role) VALUES(?,?,?,?)")) {
            p.setString(1, username); p.setString(2, hash); p.setString(3, displayName); p.setString(4, role); p.executeUpdate();
        }
    }

    public Map<String,String> findUser(String username) throws Exception {
        try (Connection c = DB.getConnection(); PreparedStatement p = c.prepareStatement(
            "SELECT id,username,password_hash,display_name,role FROM users WHERE username=?")) {
            p.setString(1, username);
            try (ResultSet r = p.executeQuery()) {
                if (!r.next()) return null;
                Map<String,String> m = new HashMap<>();
                for (String k : List.of("id","username","password_hash","display_name","role")) m.put(k, r.getString(k));
                return m;
            }
        }
    }

    public void addPatient(String name, String dob, String gender, String phone, String email, String address, String notes) throws Exception {
        String sql = "INSERT INTO patients(full_name,date_of_birth,gender,phone,email,address,medical_notes) VALUES(?,NULLIF(?,''),?,?,?,?,?)";
        try (Connection c = DB.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1,name); p.setString(2,dob); p.setString(3,gender); p.setString(4,phone);
            p.setString(5,email); p.setString(6,address); p.setString(7,notes); p.executeUpdate();
        }
    }

    public List<Patient> patients() throws Exception {
        List<Patient> out = new ArrayList<>();
        try (Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(
            "SELECT id,full_name,phone,email,gender,DATE_FORMAT(date_of_birth,'%Y-%m-%d') dob FROM patients ORDER BY id DESC");
             ResultSet r=p.executeQuery()) {
            while(r.next()) out.add(new Patient(r.getLong("id"),r.getString("full_name"),r.getString("phone"),
                r.getString("email"),r.getString("gender"),r.getString("dob")));
        } return out;
    }

    public void addDoctor(String name, String specialization, String phone, String email, String availability) throws Exception {
        try (Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(
            "INSERT INTO doctors(full_name,specialization,phone,email,availability) VALUES(?,?,?,?,?)")) {
            p.setString(1,name); p.setString(2,specialization); p.setString(3,phone); p.setString(4,email); p.setString(5,availability); p.executeUpdate();
        }
    }

    public List<Doctor> doctors() throws Exception {
        List<Doctor> out=new ArrayList<>();
        try(Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(
            "SELECT id,full_name,specialization,phone,email,availability FROM doctors ORDER BY id DESC"); ResultSet r=p.executeQuery()) {
            while(r.next()) out.add(new Doctor(r.getLong("id"),r.getString("full_name"),r.getString("specialization"),
                r.getString("phone"),r.getString("email"),r.getString("availability")));
        } return out;
    }

    public void addAppointment(long patientId, long doctorId, String date, String reason) throws Exception {
        try(Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(
            "INSERT INTO appointments(patient_id,doctor_id,appointment_date,reason) VALUES(?,?,?,?)")) {
            p.setLong(1,patientId); p.setLong(2,doctorId); p.setTimestamp(3,Timestamp.valueOf(date.replace("T"," ")+ (date.length()==16?":00":"")));
            p.setString(4,reason); p.executeUpdate();
        }
    }

    public List<Appointment> appointments() throws Exception {
        List<Appointment> out=new ArrayList<>();
        try(Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(
            "SELECT a.id,p.full_name patient,d.full_name doctor,a.appointment_date,a.reason,a.status FROM appointments a JOIN patients p ON p.id=a.patient_id JOIN doctors d ON d.id=a.doctor_id ORDER BY a.appointment_date DESC");
            ResultSet r=p.executeQuery()) {
            while(r.next()) out.add(new Appointment(r.getLong("id"),r.getString("patient"),r.getString("doctor"),
                r.getTimestamp("appointment_date"),r.getString("reason"),r.getString("status")));
        } return out;
    }

    public void addBill(long patientId, double amount, String description) throws Exception {
        try(Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(
            "INSERT INTO bills(patient_id,amount,description) VALUES(?,?,?)")) {
            p.setLong(1,patientId); p.setDouble(2,amount); p.setString(3,description); p.executeUpdate();
        }
    }

    public List<Bill> bills() throws Exception {
        List<Bill> out=new ArrayList<>();
        try(Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(
            "SELECT b.id,p.full_name patient,b.amount,b.description,b.payment_status,b.created_at FROM bills b JOIN patients p ON p.id=b.patient_id ORDER BY b.id DESC");
            ResultSet r=p.executeQuery()) {
            while(r.next()) out.add(new Bill(r.getLong("id"),r.getString("patient"),r.getDouble("amount"),
                r.getString("description"),r.getString("payment_status"),r.getTimestamp("created_at")));
        } return out;
    }
}
