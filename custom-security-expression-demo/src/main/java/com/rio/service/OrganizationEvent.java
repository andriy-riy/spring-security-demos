package com.rio.service;

import java.time.LocalDateTime;

public record OrganizationEvent(String name, LocalDateTime startDateTime, LocalDateTime endDateTime) {
}
