package com.rio.service;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrganizationEvent {

  private final String name;
  private final LocalDateTime startDateTime;
  private final LocalDateTime endDateTime;
}
