
employee_management/
├── main.py
├── models/
│   └── employee.py
└── services/
    └── employee_service.py


models/employee.py
class Employee:
    def __init__(self, emp_id, name, age, salary):
        self.emp_id = emp_id
        self.name = name
        self.age = age
        self.salary = salary

    def __str__(self):
        return f"{self.emp_id}: {self.name}, Age: {self.age}, Salary: ${self.salary}"



#services/employee_service.py
class EmployeeService:
    def __init__(self):
        self.employees = []

    def add_employee(self, employee):
        """Aggiunge un dipendente alla lista."""
        self.employees.append(employee)

    def list_employees(self):
        """Restituisce la lista dei dipendenti."""
        return self.employees

    def sort_employees_by_salary(self):
        """Ordina i dipendenti per stipendio in ordine decrescente."""
        self.employees.sort(key=lambda x: x.salary, reverse=True)



from models.employee import Employee
from services.employee_service import EmployeeService

def main():
    service = EmployeeService()

    # Aggiunta di dipendenti
    service.add_employee(Employee(1, "Mario Rossi", 30, 3500))
    service.add_employee(Employee(2, "Luca Bianchi", 25, 4200))
    service.add_employee(Employee(3, "Giulia Verdi", 28, 4000))

    print("Dipendenti iniziali:")
    for emp in service.list_employees():
        print(emp)

    # Ordinamento per stipendio
    service.sort_employees_by_salary()

    print("\nDipendenti ordinati per stipendio:")
    for emp in service.list_employees():
        print(emp)

if __name__ == "__main__":
    main()
