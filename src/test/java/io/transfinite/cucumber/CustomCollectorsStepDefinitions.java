package io.transfinite.cucumber;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.transfinite.collectors.CustomCollectors;
import java.util.List;

public class CustomCollectorsStepDefinitions {

    private List<Character> inputCharacters;
    private List<Character> result;

    @Given("I have a list of characters {string}")
    public void iHaveAListOfCharacters(String characterList) {
        inputCharacters = characterList.chars()
            .filter(c -> Character.isLetterOrDigit(c))
            .mapToObj(c -> (char) c)
            .toList();
    }

    @When("I collect duplicate elements")
    public void iCollectDuplicateElements() {
        result = inputCharacters.stream()
            .collect(CustomCollectors.duplicateElements());
    }

    @When("I collect consecutive duplicate elements")
    public void iCollectConsecutiveDuplicateElements() {
        result = inputCharacters.stream()
            .collect(CustomCollectors.duplicateConsecutiveElements());
    }

    @Then("the result should contain {string} and {string}")
    public void theResultShouldContainAnd(String first, String second) {
        assertThat(result).hasSameElementsAs(
            List.of(first.charAt(0), second.charAt(0))
        );
    }

    @Then("the result should contain only {string}")
    public void theResultShouldContainOnly(String expected) {
        assertThat(result).hasSameElementsAs(
            List.of(expected.charAt(0))
        );
    }

    @Then("the result should be empty")
    public void theResultShouldBeEmpty() {
        assertThat(result).isEmpty();
    }
}
