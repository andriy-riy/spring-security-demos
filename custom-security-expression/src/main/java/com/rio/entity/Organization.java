package com.rio.entity;

import com.rio.service.OrganizationEvent;

import java.util.List;

public record Organization(String id, String name, List<OrganizationEvent> events, List<String> userIds) {
}
