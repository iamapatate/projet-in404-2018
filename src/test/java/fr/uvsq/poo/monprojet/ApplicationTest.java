package fr.uvsq.poo.monprojet;

import org.junit.jupiter.api.Test;

import static fr.uvsq.poo.monprojet.Application.APPLICATION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

class ApplicationTest {
    @Test
    void testGreetings() {
        assertThat(APPLICATION.getGreetings(), is(equalTo("Hello !")));
    }
}
