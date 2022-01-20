package com.rio.controller;

import com.rio.entity.Organization;
import com.rio.service.OrganizationEvent;
import com.rio.service.OrganizationService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/api/v1/organizations/{id}/events")
    public List<OrganizationEvent> getOrganizationEvents(@PathVariable String id) {
        return organizationService.findById(id)
                .map(Organization::events)
                .orElseThrow();
    }
}