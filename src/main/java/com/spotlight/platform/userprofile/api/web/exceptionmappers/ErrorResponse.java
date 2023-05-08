package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(@JsonProperty String message) {
}
