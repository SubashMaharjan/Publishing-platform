package com.spotlight.platform.userprofile.api.web.resources;


import com.spotlight.platform.userprofile.api.core.profile.UserEventService;
import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserEventResource {


    private final UserEventService userEventService;


    @Inject
    public UserEventResource(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @Path("event")
    @POST
    public UserProfile event(@Valid UserEvent userEvent) {
        return userEventService.handleEvent(userEvent);
    }

    @Path("events")
    @POST
    public List<UserProfile> events(@Valid List<UserEvent> userEvents) {
        return userEventService.handleEvents(userEvents);
    }

}
