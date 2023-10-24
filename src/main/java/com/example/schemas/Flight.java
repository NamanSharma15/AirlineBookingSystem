package com.example.schemas;

public class Flight {
    public String flight_no, destination, time, terminal, gate_no, status;
    public Flight(String flight_no, String destination, String time, String terminal, String gate_no, String status) {
        this.flight_no = flight_no;
        this.destination = destination;
        this.time = time;
        this.terminal = terminal;
        this.gate_no = gate_no;
        this.status = status;
    }
}
