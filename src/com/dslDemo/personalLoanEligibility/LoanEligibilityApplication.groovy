package com.dslDemo.personalLoanEligibility

class LoanEligibilityApplication {

    EligibilityCalculator eligibilityCalculator = new EligibilityCalculator()
    RuleMaker ruleMaker = new RuleMaker()

    public static void main(String[] args) {

        LoanEligibilityApplication loanEligibilityApplication = new LoanEligibilityApplication()

        // Eligibility criteria using static rule
        println("Person Is Eligible ${loanEligibilityApplication.isEligible}")

        // Eligibility criteria Dynamic rule creation
        Rule rule = loanEligibilityApplication.personalLoanRule
        println("Person Is Eligible ${loanEligibilityApplication.isEligible1(rule)}")


    }


    Rule personalLoanRule = ruleMaker.createRule {
        alias("employmentType", "employmentDetail.employmentType")
        alias("city", "address.city")
        max("age", 40)
        min("age", 10)
        oneOf("city", ["Noida", "Delhi"])
        eq("employmentType", EmploymentType.SALARIED)
    }

    def isEligible = eligibilityCalculator.eligibilityCriteria {
        personDetail {
            name "Ram"
            age 30
            gender Gender.MALE
            address 'Noida', 'UP'
            employmentDetail {
                employmentType EmploymentType.SALARIED
                totalYearsOfExperience 5
                monthlySalary 20000
            }
        }
        loanAmountRequested 500000
        eligibility

    }

    def isEligible1(Rule rule) {
        eligibilityCalculator.eligibilityCriteria {
            personDetail {
                name "Ram"
                age 30
                gender Gender.MALE
                address 'Noida', 'UP'
                employmentDetail {
                    employmentType EmploymentType.SALARIED
                    totalYearsOfExperience 5
                    monthlySalary 20000
                }
            }
            loanAmountRequested 500000
            getEligibility(rule)

        }
    }


}
