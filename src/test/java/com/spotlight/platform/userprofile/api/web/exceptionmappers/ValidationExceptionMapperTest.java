package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(DropwizardExtensionsSupport.class)
class ValidationExceptionMapperTest {
    private static final ResourceExtension EXT = ResourceExtension.builder()
                                                                  .addResource(new MockResource())
                                                                  .setRegisterDefaultExceptionMappers(false)
                                                                  .addProvider(new ValidationExceptionMapper())
                                                                  .build();
    private Client client;

    @BeforeEach
    void setUp() {
        client = EXT.client();
    }

    @Test
    void entityNotFound_ResultsIn400() {
        Response response = client.target(MockResource.RESOURCE_URLS.THROW_EXCEPTION)
                                  .request()
                                  .accept(MediaType.APPLICATION_JSON_TYPE)
                                  .post(Entity.json("{}"));


        assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Path("/")
    public static class MockResource {
        public static class RESOURCE_URLS {
            public static final String THROW_EXCEPTION = "/throwValidationException";
        }

        @POST
        @Path(RESOURCE_URLS.THROW_EXCEPTION)
        public void throwException() {
            throw new ValidationException("Collect event only support Collections DataType");
        }
    }
}