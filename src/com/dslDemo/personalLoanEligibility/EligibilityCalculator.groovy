package com.dslDemo.personalLoanEligibility


class EligibilityCalculator {

    PersonDetail personDetail
    Double loanAmountRequested
    Double eligibleLoanAmountSanctioned


    def eligibilityCriteria(Closure closure) {
        EligibilityCalculator eligibilityCalculator = new EligibilityCalculator()
        closure.delegate = eligibilityCalculator
        closure()
    }

    def employmentDetail(Closure closure) {
        personDetail.employmentDetail = new EmploymentDetail()
        closure()
    }

    def personDetail(Closure closure) {

        personDetail = new PersonDetail()
        closure()
    }

    def name(String name) {
        personDetail.name = name
    }

    def age(Integer age) {
        personDetail.age = age
    }

    def gender(Gender gender) {
        personDetail.gender = gender
    }

    def employmentType(EmploymentType employmentType) {
        personDetail.employmentDetail.employmentType = employmentType
    }

    def totalYearsOfExperience(Integer totalYearsOfExperience) {
        personDetail.employmentDetail.totalYearsOfExperience = totalYearsOfExperience
    }

    def monthlySalary(Double salary) {
        personDetail.employmentDetail.monthlySalary = salary
    }

    def address(String city, String state) {
        personDetail.address = new Address(city: city, state: state)
    }

    def loanAmountRequested(Long loanAmount) {
        this.loanAmountRequested = loanAmount
    }

    def eligibleLoanAmountSanctioned(Long loanAmount) {
        this.eligibleLoanAmountSanctioned = loanAmount
    }


    def getEligibility() {
        RuleEngine ruleEngine = new RuleEngine()
        ruleEngine.evaluate(personDetail)
    }

    def getEligibility(Rule rule) {
        RuleEngine ruleEngine = new RuleEngine()
        ruleEngine.evaluate(personDetail,rule)
    }

}
