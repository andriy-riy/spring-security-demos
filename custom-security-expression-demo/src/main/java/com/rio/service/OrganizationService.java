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
            new Organization("1", "Jappware",
                    List.of(
                            new OrganizationEvent("Strategic session", LocalDateTime.of(2021, 12, 11, 16, 0, 0), LocalDateTime.of(2021, 11, 16, 18, 0, 0)),
                            new OrganizationEvent("New Year party", LocalDateTime.of(2022, 12, 31, 21, 0, 0), LocalDateTime.of(2022, 1, 1, 8, 0, 0)),
                            new OrganizationEvent("Jappware's Winter 2022 Party", LocalDateTime.of(2022, 1, 28, 18, 0, 0), LocalDateTime.of(2022, 1, 29, 2, 0, 0))
                    ),
                    List.of("1", "2", "3")),

            new Organization("2", "Якась організація",
                    List.of(
                            new OrganizationEvent("Якийсь івент", LocalDateTime.of(2021, 12, 31, 21, 0, 0), LocalDateTime.of(2021, 1, 1, 8, 0, 0))
                    ),
                    List.of("4"))
    );

    public Optional<Organization> findById(@NonNull String id) {
        return organizations.stream()
                .filter(organization -> organization.id().equals(id))
                .findFirst();
    }
}
