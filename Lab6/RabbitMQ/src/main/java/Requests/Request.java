package Requests;

public class Request {
    private final String doctor;
    private final RequestType requestType;
    private final String patientName;

    public Request(String doctor, RequestType requestType, String patientName) {
        this.doctor = doctor;
        this.requestType = requestType;
        this.patientName = patientName;
    }

    public Request(String request) {
        String[] parts = request.split(" ");
        this.doctor = parts[0];
        this.requestType = RequestType.valueOf(parts[1].toUpperCase());
        this.patientName = parts[2];
    }

    public String getDoctor() {
        return doctor;
    }
    public RequestType getRequestType() {
        return requestType;
    }

    public String getPatientName() {
        return patientName;
    }

    public String toString() {
        return doctor + " " + requestType + " " + patientName;
    }
}
