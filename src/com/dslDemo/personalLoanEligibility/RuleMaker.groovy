package com.dslDemo.personalLoanEligibility


class RuleMaker {


    Rule rule = new Rule()

    Map aliases = [:]

    Rule createRule(Closure ruleClosure) {
        RuleMaker ruleMaker = new RuleMaker()
        ruleClosure.delegate = ruleMaker
        ruleClosure()
    }

    def alias(String aliasName, String completePath) {
        aliases.put(aliasName, completePath)
    }

    def max(String propertyName, Object value) {
        rule.conditions.add({ def object -> getValue(object, propertyName) < value })
        rule

    }

    def min(String propertyName, Object value) {
        rule.conditions.add({ def object ->

            getValue(object, propertyName) > value
        })
        rule
    }

    def oneOf(String propertyName, Object value) {
        rule.conditions.add({ def object -> getValue(object, propertyName) in value })
        rule
    }

    def eq(String propertyName, Object value) {
        rule.conditions.add({ def object -> getValue(object, propertyName) == value })
        rule
    }


    def getValue(Object rootClassReference, String propertyName) {

        String aliasPropertyName = aliases.get(propertyName)
        if (aliasPropertyName) {
            propertyName = aliasPropertyName
        }
        if (propertyName.contains('.')) {
            List<String> properties = propertyName.tokenize('.')
            properties.each {
                rootClassReference = rootClassReference?."${it}"
            }
            return rootClassReference
        }
        return (rootClassReference?."${propertyName}")

    }

}
