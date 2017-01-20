package com.dslDemo.personalLoanEligibility


class RuleEngine {

    def evaluate(PersonDetail personDetail) {

        ageValid(personDetail) && salaryValid(personDetail) && cityValid(personDetail)

    }


    def ageValid = { PersonDetail personDetail ->
        personDetail.age > 23 && personDetail.age < 40
    }

    def salaryValid = { PersonDetail personDetail ->
        if (EmploymentType.SALARIED.equals(personDetail.employmentDetail.employmentType)) {
            personDetail.employmentDetail.monthlySalary > 17500 && personDetail.employmentDetail.totalYearsOfExperience > 3
        } else if (EmploymentType.SELF_EMPLOYED.equals(personDetail.employmentDetail.employmentType)) {
            personDetail.employmentDetail.monthlySalary > 40000 && personDetail.employmentDetail.totalYearsOfExperience > 5
        }
    }

    def cityValid = { PersonDetail personDetail ->
        personDetail.address.city in ['Delhi', 'Noida']
    }


    def evaluate(PersonDetail personDetail, Rule rule) {
        rule.conditions.find {
            println("Condition;::::" + it)
            !(it(personDetail))
        } ? false : true

    }

}
