package ood;

public class callCenter {
    List<Employee> employees;
    List<Calls> activeCalls;
    List<Calls> history;

    public callCenter (List<Employee> employees) {
        this.employees = employees;
    }

    public checkAvailableAgent() {
        // loop through employees and find the next available agent base on rank responder -> manager -> director
    }

    public addEmployees () {

    }

    public removeEmployees() {
        
    }


}

class Employee {
    String position; //responder, manager director
    String employeeID;
    String phoneNumber;
    Boolean available; // when you pick up mark as False, default is True
}

class Call {
    String callerID;
    Employee employee_responded; //who is picking up the phone
    Date StartTime; //when it was pickup?
    Date EndTime; // call finished
    String description;

    public dispatchCall() {
        //check available agent and update the responder employee to that person       
    }
}