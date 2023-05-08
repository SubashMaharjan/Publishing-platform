package com.spotlight.platform.userprofile.api.web.resources;


import com.spotlight.platform.userprofile.api.core.profile.UserEventService;
import com.spotlight.platform.userprofile.api.core.profile.UserProfileService;
import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

}
