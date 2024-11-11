package seg3x02.employeeGql.resolvers

import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput

import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.stereotype.Controller

import java.util.*

@Controller
class EmployeesResolver(private val employeeRepository: EmployeesRepository) {

    @QueryMapping
    fun employees(): List<Employee> {
        return employeeRepository.findAll()
    }

    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput): Employee {
        if(input.name != null && input.salary != null && input.birthday != null && input.city != null) {
            val emp = Employee(
                name = input.name,
                salary = input.salary,
                email = input.email,
                birthday = input.birthday,
                city = input.city,
                gender = input.gender
            )
            emp.id = UUID.randomUUID().toString()
            return employeeRepository.save(emp)
        }
        else {
            throw Exception("Error: required fields have not been submitted.")
        }
    }
}
