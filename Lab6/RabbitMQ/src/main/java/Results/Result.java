package Results;

import Requests.RequestType;

public class Result {
    private final String technician;
    private final RequestType requestType;
    private final String patientName;
    private final boolean isDone;

    public Result(String technician, RequestType requestType, String patientName) {
        this.technician = technician;
        this.requestType = requestType;
        this.patientName = patientName;
        this.isDone = true;
    }

    public Result(String request) {
        String[] parts = request.split(" ");
        this.technician = parts[0];
        this.requestType = RequestType.valueOf(parts[1].toUpperCase());
        this.patientName = parts[2];
        this.isDone = Boolean.parseBoolean(parts[3]);
    }

    public String getTechnician() {
        return technician;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getPatientName() {
        return patientName;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toString() {
        return technician + " " + requestType + " " + patientName + " " + isDone;
    }
}
