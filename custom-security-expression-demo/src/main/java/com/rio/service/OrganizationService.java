package com.rio.service;

import com.rio.entity.Organization;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final List<Organization> organizations = List.of(
            new Organization("1", "Organization1",
                    List.of(
                            new OrganizationEvent("Initial event", LocalDateTime.of(2020, 10, 1, 19, 0, 0), LocalDateTime.of(2020, 10, 1, 20, 0, 0)),
                            new OrganizationEvent("Knowledge sharing", LocalDateTime.of(2020, 12, 11, 16, 0, 0), LocalDateTime.of(2020, 11, 16, 18, 0, 0)),
                            new OrganizationEvent("New Year party", LocalDateTime.of(2020, 12, 31, 21, 0, 0), LocalDateTime.of(2021, 1, 1, 8, 0, 0))
                    ),
                    List.of("1", "2")),

            new Organization("2", "Organization2",
                    List.of(
                            new OrganizationEvent("New Year party", LocalDateTime.of(2020, 12, 31, 21, 0, 0), LocalDateTime.of(2021, 1, 1, 8, 0, 0))
                    ),
                    List.of("3"))
    );

    public Optional<Organization> findById(@NonNull String id) {
        return organizations.stream()
                .filter(organization -> organization.id().equals(id))
                .findFirst();
    }
}
